-- ������
-- �������ɾ�Ĳ�
-- ��ѯ����  70%�����ڲ�ѯ,����,����,����
-- ����ѯ
-- ���ݿ���̣�oracle,pl-sql��

--sql�����飬����sqlΪ���һ�����ӵ�ҵ���߼���һ��ִ��
--�鲻�ܱ�������������ã�ֻ�����������ݿ�ˡ�
--��Ľṹ���̶���һ���ṹ
--declare
 --�������
--begin

 --����sql��һ����ִ�С�

--end;

-- ����һ�������ж��������ż�����������������
--��֧���
accept num  prompt '������һ����:'
declare
   inputnum  number:=&num;  -- ������� ���������ֺ����ͣ���ֵ:=
begin
   
    if  mod(inputnum,2)=0 then  -- ��һ������
       dbms_output.put_line('�����:'||inputnum||',��ż��');
       
    else
       dbms_output.put_line('�����:'||inputnum||',������');
    
    end  if;
  
end;

--ѭ�����1  forѭ��

declare
dsum number:=0;
begin
 
   for  num  in 1..100  loop
     dsum:=dsum+num;
   end  loop;
   dbms_output.put_line('1.100�ĺ�Ϊ:'||dsum);

end;


--ѭ�����2  whileѭ��

declare
v_num number:=1;
dsum number:=0;
begin
  while v_num<=100 loop
      dsum:=dsum+v_num;
      v_num:=v_num+1;
  end loop;
 dbms_output.put_line('1.100�ĺ�Ϊ:'||dsum);
end;


-- PL-SQL���̿��ƣ���ֵ

select   *   from  t_emp

select  * from  t_dept

-- 1.����Ա�����������ҳ����Ա���Ĳ��ŵ�����
-- �����ѯ�������
select d.dname  from  t_emp  e  inner  join t_dept  d  on  e.eid=d.id  where e.ename='������';

-- pl-sql��Ľ������
accept  ename   prompt '������Ա������:'
declare
v_name  varchar2(50):='&ename';
v_eid  t_emp.eid%type;  --�����ݿ��б���ֶε����ͱ���һ��

v_dname t_dept.dname%type;
begin
  
    -- ���������õ�Ա���Ĳ��ű��, sql����еĸ�ֵ����ʹ��into�ؼ��֣�intoֻ�ܸ�һ��ֵ������Ƕ��ֵ�����α꣨�����),�α겻��
    --��into����ֵ
   
     select  e.eid   into  v_eid  from  t_emp e where e.ename=v_name;
    dbms_output.put_line('���ű��Ϊ:'||v_eid);
    
    -- .���ݲ���Ĳ��ŵı�ţ�ȥƥ�䲿�ű�
    select  d.dname  into v_dname    from  t_dept  d  where  d.id= v_eid;
     dbms_output.put_line('��������Ϊ:'||v_dname);
     
exception  -- �쳣�����
   when  others  then
     dbms_output.put_line('�Բ���,û�����Ա��');
   
end;

-- 
select  * from  t_role_function

select  * from t_emp

select  * from  t_role

--  �ҳ�������Ա���Ĺ������ƣ�����Ȩ�ޡ�
-- �Ӳ�ѯ ��Ч�ʵ�
select rf.operatorname
from  t_role_function  rf  where  rf.rid=(select r.id  from  
t_role r where  r.rname= (select  e.ejob from  t_emp e  where e.ename='������'))
and  flag=1;

-- ���Ӳ�ѯ
select   rf.operatorname  from  t_role_function  rf  inner   join 
(select  r.id   rid from  t_emp e   inner   join  t_role  r   on  e.ejob=r.rname  where e.ename='������')  tmp
on tmp.rid=rf.rid  where flag=1;

-- pl-SQL�鴦��
accept  ename  prompt  '����Ա������'
declare
v_ename  t_emp.ename%type:='&ename';
v_job  t_emp.ejob%type;
v_rid  t_role.id%type;

begin

   select  e.ejob  into v_job  from  t_emp  e  where e.ename=v_ename;
   dbms_output.put_line(v_job);
   
   select r.id   into v_rid  from  t_role  r  where r.rname=v_job;
   dbms_output.put_line(v_rid);
   
    --���ֵ�ǲ���into��ֵ�ģ����ֵ���α��ǽ����
    --select rf.operatorname into  v_fname  from  t_role_function  rf  where rf.rid=v_rid  and  flag=1;
    --dbms_output.put_line(v_ename||',ӵ�й�������Ϊ'||v_fname);
    
    --datas row����
   for  datas in  (select rf.operatorname  fname   from  t_role_function  rf  where rf.rid=v_rid  and  flag=1)  loop
       dbms_output.put_line('ӵ�й�������Ϊ'||datas.fname);
   end loop;
end;

-- pl-sql
-- 
select  * from  t_emp

update  t_emp  set esalary=500  where  ename='������';
update  t_emp  set esalary=1500  where  ename='�Ʒ�';
commit;

select   * from  t_dept

-- ���벿�����ƣ��������Ա������С��2000��,�ӹ���500����������º�Ա���Ļ�����Ϣ��

accept  dname  prompt '�����벿������'

declare
v_dname  t_dept.dname%type:='&dname';
v_did  t_dept.id%type;
v_str varchar2(100);
v_s t_emp.esalary%type;
begin
   
     select  d.id   into  v_did  from  t_dept  d  where d.dname=v_dname;
         dbms_output.put_line('����idΪ:'|| v_did);
        
         
        for  datas in  (select  e.id  eeid,e.ename  eename,e.esalary+nvl(e.ecomm,0)  eem 
        from  t_emp  e  where e.eid=v_did)  loop
           dbms_output.put_line(datas.eeid||','||datas.eename||','||datas.eem);
           
            if  datas.eem<2000 then
            
               update  t_emp e  set e.esalary=e.esalary+500  where   e.id=datas.eeid;
               
               select   e.esalary+nvl(e.ecomm,0)  into   v_s from   t_emp  e   where   e.id=datas.eeid;
               
               v_str:=datas.eename||',���º������'||v_s;
            
            end  if;
        
        end loop;
        
        commit;
        
      dbms_output.put_line('��ϢΪ:'||v_str);   
 exception
 
   when  others  then
   v_str:='�Բ����������д���û��������ţ����ʵ';
    dbms_output.put_line('��ϢΪ:'||v_str);  
end;

-- ����һ�ű�����������յ���100W�����ݡ�

--pl-sql��ľ��ޣ����ܹ���������Ե��ã�ֻ�����������ݿ�ˡ�

select  * from t_emp
-- �洢���̣�һ���洢������+���̻�������
--  �洢�����ܹ���������Ե��ã�Ԥ������ģ�����һ�κ�����ò���Ҫ�ڱ��룬���ܺܺá�
create   or  replace   procedure  p_login
(
   v_name  in varchar2, 
   v_pwd   in  varchar2,
   v_result  out varchar2
)
as
v_count   int;
begin

    --1.��¼��֤
    select count(*) into  v_count from t_emp  e  where e.ename=v_name  and trim(e.epwd)=v_pwd;
    
    if v_count>0 then
      v_result:='��¼�ɹ�';
    else
      v_result:='��¼ʧ��';
    end  if;
    
    insert  into  t_log   values(seq_log.nextval,v_name,sysdate,v_result);
    commit;
    
    dbms_output.put_line(v_result);

end;

-- �����α�Ĵ洢����

select   *  from  t_emp

select  *  from  t_dept

-- ��������������ƣ��õ�������ŵ�����Ա������Ϣ
create  or   replace  procedure   p_queryempmen
(
    v_dname  in  varchar2,
    v_datas out  sys_refcursor  --�����  ϵͳ�α�
)
as
begin

   --ֱ�ӷ��ؽ����
   open  v_datas  for   select  e.*  from  t_dept d inner  join t_emp  e
   on  e.eid=d.id   where d.dname=v_dname
   and  e.sex='��';
  
end;

-- �洢���̿�������ͷ��ض��ֵ�����ص���һ����������α꣬sys_refcursor��java�˵Ľ���oracle.jdbc.OracleTypes.CURSOR
-- �α��ڲ��ı�����for  ..loop  ...end  loop;

-- ��̬sql   sql��һ����̬��ƴ�ӵ���䡣 *****

-- ��ѯһ���������
select  count(*)  from  t_emp;
select  count(*)  from  t_dept;

create  or  replace  procedure p_count
(
 v_tableName  in  varchar2,
 v_count  out int
)
as
v_sql varchar2(1000);
begin

  v_sql:='select  count(*)  from    '||v_tableName;
  
   --��̬sqlִ�У����������һ��ֵexecute  immediate
   execute  immediate v_sql   into  v_count;
   
end;

create  or  replace  procedure p_result
(
   v_tableName  in  varchar2,
   v_datas  out   sys_refcursor
)
as
v_sql varchar2(1000);
begin

v_sql:='select  *  from    '||v_tableName;

  --��̬sqlִ��,������α�����
  open   v_datas  for  v_sql;

end;

-- ��̬sql�Ǹ��α����ͣ����ڲ�����
select  *  from  t_dept


create  or  replace  procedure p_resultstr
(
   v_tableName  in  varchar2,
   v_msg  out  varchar2
   
)
as
v_sql varchar2(1000);
v_datas  sys_refcursor;
v_id t_dept.id%type;
v_dname t_dept.dname%type;
v_loc  t_dept.loc%type;
begin

    v_sql:='select  *  from    '||v_tableName;
    open   v_datas  for  v_sql;
  
    
    loop    fetch  v_datas into   v_id,v_dname,v_loc;
       exit  when  v_datas%notfound;  -- �����û������
        v_msg:=v_msg||v_id||','||v_dname||','||v_loc;
    end  loop;    
    dbms_output.put_line(v_msg);
    
end;

-- �Զ��庯��  ����Ĵ洢���̣�ֻ�ܷ���һ��ֵ
create  or  replace  function  f_querycount
(
  v_tableName  in    varchar2
)
return int  --������;��
as
v_sql  varchar2(1000);
v_count  int;
begin
 v_sql:='select  count(*)  from   ' ||v_tableName;
 
 execute  immediate  v_sql  into  v_count;
 
 return v_count;

end;

 -- �洢���̺��Զ��庯�������𣬹ؼ����Զ��庯������ֻ����һ������ֵ��
 
 
 
 -- ��ҳ�Ĵ洢���̣�ͨ���ԣ�100�ű��ܷ�ҳ
 create  or  replace  procedure  p_pages
 (
    v_tableName  in  varchar2, -- ����
    v_pagenum  in  number,  -- �ڼ�ҳ
    v_pagefixedsize in  number, -- ÿҳ����
    v_count  out  number,  -- �ܹ�������
    v_size   out number,-- �ܹ�����ҳ��
    v_datas  out  sys_refcursor --��ҳ�Ľ����
  
 )
 as
 v_endnumber number:=v_pagenum*v_pagefixedsize;
 v_startnumber  number:=(v_pagenum-1)*v_pagefixedsize;
v_sql varchar2(1000);
 begin
 
    --1.�ܹ�������
    v_sql:='select  count(*) from  '||v_tableName;
    
    execute  immediate   v_sql  into  v_count;
    
    --2.֪������ҳ
    if  mod(v_count,v_pagefixedsize)=0 then
       v_size:=v_count/v_pagefixedsize;
    else
       v_size:=floor(v_count/v_pagefixedsize)+1;
    end  if;
    
    dbms_output.put_line(v_size);

   
    v_sql:='select  * from   ( select rownum  rw, e.*  from  '||v_tableName||' e   where rownum<='||v_endnumber||') tmp where  tmp.rw>'||v_startnumber;
    dbms_output.put_line(v_sql);
    
    open  v_datas  for v_sql;
  
 
 end;
 
 
 -- oracle����Ȩ�޹�ϵģ�ͱ�
 
 -- ҵ��
 
 -- �û�����ɫ�� ���û��ͽ�ɫ(����û������ɫ)��Ȩ�ޱ� ��ɫ��Ȩ�ޱ�(һ����ɫ�ж��Ȩ��)
 
 create  table  t_userinfo
 (
    id  int  primary key,
    uname varchar2(20),
    upwd  char(6),
    usex   char(4),
    ubirthday  date,
    uaddress  varchar2(20),
    ustate  int
 )
 
 alter  table  t_userinfo  add  did  int;
 
 select  * from   t_userinfo
 
 update  t_userinfo  set  did=100 where id=2;
  update  t_userinfo  set  did=101 where id=4;
  commit;
  
  
 create  sequence  seq_userinfo
 start  with  2
 increment  by 2
 nocache;
 
 insert  into   t_userinfo  values(seq_userinfo.nextval,'����','1234','��',to_date('1997-12-16','yyyy-MM-dd'),'̩��',1);
  insert  into   t_userinfo  values(seq_userinfo.nextval,'�����','123456','��',to_date('1995-12-16','yyyy-MM-dd'),'�γ�',1);
  commit;
  
  select   * from  t_userinfo;
 
 create  table  t_userrole
 (
    rid   int   primary  key,
    rname  varchar2(20)
 )
 
 insert  into   t_userrole(rid ,rname)  values(1,'����Ա');
  insert  into   t_userrole(rid ,rname)  values(2,'��ͨԱ��');
  commit;
  
  select   * from   t_userrole
 
  -- �����û���ɫ��  �м��ϵ��
  create   table  t_user_role
  (
     userid  int ,
     roleid  int
  )
  
  insert  into   t_user_role  values(2,1);
  insert  into   t_user_role  values(2,2);
  insert  into   t_user_role  values(4,2);
  
  select   * from  t_user_role;
  
  -- Ա�������2��Ա���������ͽ�ɫ����
  select  u.uname,r.rname from  t_userinfo  u   inner  join t_user_role ur
  on u.id=ur.userid  inner  join  t_userrole  r  on  r.rid=ur.roleid where  u.id=2;
  
  
  -- Ա����-----��Զ��ϵ[�м��ϵ��Ա���ͽ�ɫ��ϵ��]-----��ɫ��
  
  
  --����һ��Ȩ�ޱ�
  create   table  t_privilege
  (
     pid  int  primary key ,-- Ȩ��id
     pname  varchar2(20), --Ȩ������
     purl  varchar2(100)  --Ȩ�޶���

  )
  
  create  sequence  seq_privilege
  start  with 10
  increment  by 3
  nocache;
  
  
  insert  into   t_privilege   values(seq_privilege.nextval,'��ѯԱ����Ϣ','queryuser');
  insert  into   t_privilege   values(seq_privilege.nextval,'����Ա����Ϣ','adduser');
  insert  into   t_privilege   values(seq_privilege.nextval,'ɾ��Ա����Ϣ','deluser');
  insert  into   t_privilege   values(seq_privilege.nextval,'����Ա����Ϣ','updateuser');
  insert  into   t_privilege   values(seq_privilege.nextval,' ��ѯϵͳ��־','syslog');
  insert  into   t_privilege   values(seq_privilege.nextval,' ��ѯ��˾����','querymoney');
  commit;
  
  select  * from   t_privilege
  
  -- ��ɫ��Ȩ�޽����� �м��ϵ��  t_role_pr
 
 create  table   t_role_pr
 (
   rid  int ,
   pid  int  
 )
 
 select  *  from   t_role_pr;
 
 
 insert  into   t_role_pr  values(1,22);
  insert  into   t_role_pr  values(1,25);
  insert  into   t_role_pr  values(2,10);
  insert  into   t_role_pr  values(2,13);
  insert  into   t_role_pr  values(2,16);
  insert  into   t_role_pr  values(2,19);
  commit;
  
  
  --  ��ѯԱ�������2��Ա����������ɫ��Ȩ�����ƣ�����
   select  u.uname,r.rname,p.pname,p.purl from  t_userinfo  u   inner  join t_user_role ur
  on u.id=ur.userid  inner  join  t_userrole  r  on  r.rid=ur.roleid
  inner  join  t_role_pr  rp on r.rid=rp.rid  
  inner  join  t_privilege   p   on  p.pid=rp.pid
  where  u.id=4;
  
  
  -- �������ű�  ���ź�Ա����һ�Զ��ϵ
  
  create   table  t_depts
  (
     did  int  primary  key,
     dname  varchar2(20),
     dphone  char(11),
     dlocation  varchar2(20)
  
  )
  
  insert  into    t_depts  values(100,'�ۺϲ�','13912221098','�Ͼ�');
  insert  into    t_depts  values(101,'������','13912221099','Խ��');
  commit;
  
  
  -- ����   һ����ͥ��Ϣ
  
  
  -- mybatis һ��һ��һ�Զ࣬��Զ�
  
  select  *  from  t_depts 
  
  select  * from  t_userinfo
  
  
  -- ÿ�����ŵ�Ա������Ϣ(һ�Զ�)
  
  select  d.dname, d.dphone, u.uname,u.uaddress,u.usex
  from  t_depts  d  inner  join  t_userinfo  u  on  d.did=u.did
  
  
  -- ��ͥ��Ϣ��
  create table t_fam
  (
     fid  int  primary key,
     fname varchar2(20),
     faddress  varchar2(20)
  )
  
  alter   table   t_fam add   userid  int;
  
    create  sequence  seq_fam
  start  with 1
  increment  by 2
  nocache;
  
  insert  into   t_fam  values(seq_fam.nextval,'��ĳ','����',2);
  insert  into   t_fam  values(seq_fam.nextval,'��ĳ','�Ϻ�',4);
  commit;
  
  select  * from   t_fam
  
  -- �������ŵ����ֺ͵�ַ
  select  f.fname,f.faddress,u.uaddress,u.uname
  from  t_userinfo  u  inner join  t_fam   f  on   u.id=f.userid  where u.uname='����'
  
  
-------------------------------------------------------------------------------------------------------------

-- ��¼����־����¼��¼�����
-- 
create  table  t_log
(
   tid  int  primary key,
   tname  varchar2(20),
   logintime  date,
   result  varchar2(20)

)

select  * from  t_log

select  * from  t_emp

alter  table t_emp modify  epwd  char(8);

create  sequence  seq_log
start with  1
increment  by 2
nocache;








