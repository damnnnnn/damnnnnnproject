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
 
 alter  table  t_userinfo add faceimg  varchar2(50);
 
 select  * from   t_userinfo  
 
 update  t_userinfo  set faceimg='http://127.0.0.1:8500/images/cz.png'  where id =2;
 commit;
 
 select  * from   t_userinfo  u  where u.uname='����';
 
 
 
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
  
  
  -- �����û���������ѯ���û���Ȩ��
     select  u.uname,r.rname,p.pname,p.purl from  t_userinfo  u   inner  join t_user_role ur
  on u.id=ur.userid  inner  join  t_userrole  r  on  r.rid=ur.roleid
  inner  join  t_role_pr  rp on r.rid=rp.rid  
  inner  join  t_privilege   p   on  p.pid=rp.pid
  where  u.uname='�����';
  
  
  
  
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
  
  -- �洢���̣�����������û��������ҳ����Ĳ������ƣ�����ż�������͵�ַ��
  create   or  replace   procedure   p_queryByUserNameInfo
  (
     v_uname  in  varchar2,
     v_dname  out varchar2,
     v_wname  out  varchar2,
     v_add  out  varchar2
  )
  as
  begin
    --1. �����û������������ҵ����ŵı�ţ�ͨ�����ű�ſ��������ѯ��������
    select   d.dname  into  v_dname  from  t_userinfo  u  inner   join
    t_depts  d  on u.did =d.did  where u.uname=v_uname;
    
    --2. �����û�����,�ҵ���ͥ�ı��
    select  f.fname,f.faddress  into v_wname,  v_add  from    t_userinfo  u  
    inner  join  t_fam f on  u.id=f.userid  where u.uname=v_uname;
exception
  when  others  then
  v_dname:='û��';
  v_wname:='û��' ;
  v_add:='û��';
  end;
  
  
  -- �����α�
  
create   or  replace  procedure  p_queryall
(
   v_tableName   in   varchar2,
   v_datas   out  sys_refcursor
)
as
v_sql varchar2(1000);
begin
  v_sql:='select  *  from  '||v_tableName;
  open  v_datas for  v_sql;
end;

  
  
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

select  * from  t_userinfo

alter  table t_emp modify  epwd  char(8);

create  sequence  seq_log
start with  1
increment  by 2
nocache;

select  count(*)  from  t_userinfo u  where u.uname=#{}  and  u.upwd=#{}

select  count(*)  from  t_userinfo  uwhere u.uname=#{aa}


create  table  t_leftmenus
(
   id  int  primary key,
   cname  varchar2(50),
   curl   varchar2(100),
   cimg  varchar2(100),
   ctime  date  default  sysdate,
   cauthor  varchar2(20),
   flag  int  -- �Ƿ���������˵�

)

drop  table t_leftmenus

insert  into   t_leftmenus   values(1,'Ա����Ϣ','/emps/infos','http://127.0.0.1:8500/images/f1.png',default,'admin',1);
insert  into   t_leftmenus   values(2,'������Ϣ','/depts/infos','http://127.0.0.1:8500/images/f2.png',default,'admin',1);
insert  into   t_leftmenus   values(3,'��ɫ��Ϣ','/roles/infos','http://127.0.0.1:8500/images/f3.png',default,'admin',1);
insert  into   t_leftmenus   values(4,'Ȩ��ά��','/check/infos','http://127.0.0.1:8500/images/f4.png',default,'admin',1);
insert  into   t_leftmenus   values(5,'��־����','/logs/infos','http://127.0.0.1:8500/images/f5.png',default,'admin',1);
insert  into   t_leftmenus   values(6,'���ݱ���','/datas/infos','http://127.0.0.1:8500/images/f6.png',default,'admin',1);
insert  into   t_leftmenus   values(7,'С�������','/smallproject/infos','http://127.0.0.1:8500/images/f7.png',default,'admin',1);
insert  into   t_leftmenus   values(8,'�̶��ʲ�','/fixeds/infos','http://127.0.0.1:8500/images/f8.png',default,'admin',1);
insert  into   t_leftmenus   values(9,'��Ϣά��','/infos/infos','http://127.0.0.1:8500/images/f9.png',default,'admin',1);
insert  into   t_leftmenus   values(10,'�����ĵ�','/words/infos','http://127.0.0.1:8500/images/f10.png',default,'admin',1);
insert  into   t_leftmenus   values(11,'��Ϣͨ��','/message/infos','http://127.0.0.1:8500/images/f11.png',default,'admin',1);

commit;

update t_leftmenus  set curl='/manager/infos'  where id=7;
commit;

select  *  from  t_leftmenus

select  * from  t_userinfo

-- �ֲ�ͼ��
select  *  from   t_swiper

insert  into   t_swiper  values(6,'������','http://localhost:8500/images/m6.jpg',5000,
to_date('2019-07-01','yyyy-MM-dd'));
commit;
 

update t_swiper  set ttime=sysdate, turl='http://localhost:8500/images/6.jpg'  where  tid=6;
commit;

-- �ֲ�ͼѡ���Ĳ��ԣ��۸���ߵ���λ��ʱ���ǵ���

   select *  from  (select  rownum , s.* from   t_swiper s  where   
   trunc(ttime)=trunc(sysdate) order by tmoney desc) t where rownum<=3;
   
   select  * from  t_emp
   
   insert into   t_emp  values(11,'������','123','��ͨԱ��',5000,500,null,sysdate,'13913321098','�Ͼ�','��ʿ',10,'��');
   commit;
   
   insert into   t_emp  values(12,'��ҫ��','123','��ͨԱ��',5000,500,null,sysdate,'13913321099','�Ͼ�','��ʿ',10,'��');
   insert into   t_emp  values(13,'����','123','��ͨԱ��',5000,500,null,sysdate,'13913321098','�Ͼ�','����',10,'��');
   insert into   t_emp  values(14,'����','123','��ͨԱ��',5000,500,null,sysdate,'13913321098','�Ͼ�','��ʿ',10,'��');
   insert into   t_emp  values(15,'�ν���','123','��ͨԱ��',5000,500,null,sysdate,'13913321098','�Ͼ�','��ʿ',10,'��');
   insert into   t_emp  values(16,'����','123','��ͨԱ��',5000,500,null,sysdate,'13913321098','�Ϻ�','����',10,'��');
   insert into   t_emp  values(17,'������','123','��ͨԱ��',5000,500,null,sysdate,'13913321098','�Ͼ�','����',10,'��');
   insert into   t_emp  values(18,'������','123','��ͨԱ��',5000,500,null,sysdate,'13913321098','����','��ʿ',10,'��');
   insert into   t_emp  values(19,'������','123','��ͨԱ��',5000,500,null,sysdate,'13913321098','�Ͼ�','����',10,'Ů');
   insert into   t_emp  values(20,'�����','123','��ͨԱ��',5000,500,null,sysdate,'13913321098','�Ͼ�','����',10,'Ů');
   commit;
   insert into   t_emp  values(11,'������','123','��ͨԱ��',5000,500,null,sysdate,'13913321098','�Ͼ�','��ʿ',10,'��');
   
   select  * from   ( select rownum  rw, e.*  from  t_emp e   where rownum<=6) tmp where  tmp.rw>3
   
   
   -- jQuery mobile
   
   select  *  from  t_emp
   
  create  or  replace  procedure  p_emp_login
  (
     v_name  in   varchar2,
     v_pwd  in  varchar2,
     v_msg  out varchar2
  
  )
  as
  v_count  int;
  begin
  
    select  count(*)  into  v_count  from t_emp e where e.ename=v_name  and trim(e.epwd)=v_pwd;
    
    if v_count>0 then
       v_msg:=v_name||','||'��¼�ɹ�';
    else
        v_msg:=v_name||','||'��¼ʧ��';
    end if;
    
    
    insert  into  t_log  values(seq_log.nextval,v_name,sysdate,v_msg);
    commit;
  
  end;
  
  alter   table   t_log  modify  result  varchar2(200);
  
  
  
  select  * from  t_depts

select  *  from  t_userinfo

insert  into  t_userinfo  values(seq_userinfo.nextval,'����','234','��',sysdate,'���Ƹ�',1,100,null);
insert  into  t_userinfo  values(seq_userinfo.nextval,'������','234','��',sysdate,'���Ƹ�',1,101,null);
insert  into  t_userinfo  values(seq_userinfo.nextval,'����','234','��',sysdate,'���Ƹ�',1,100,null);
insert  into  t_userinfo  values(seq_userinfo.nextval,'��ҫ��','234','��',sysdate,'���Ƹ�',1,101,null);
insert  into  t_userinfo  values(seq_userinfo.nextval,'�����','234','Ů',sysdate,'��ͨ',1,null,null);

commit;



-- ��ѯÿ�����ŵ�Ա������Ϣ
select  d.dname,u.uname,d.dlocation,d.dphone 
from t_userinfo u inner join  t_depts  d  on u.did=d.did  


--��ѯÿ�����ŵ�Ա�����Ա������
select   count(u.usex),nvl(d.dname,'ͳ��ëѽ������û����'),u.usex
from t_userinfo u left join  t_depts  d  on u.did=d.did  
group  by  d.dname,u.usex

execute immerdate

-- mybatis��̬sql

-- �ǻ���mybatis���

select   * from  t_userinfo

-- ��������ȥ��ѯԱ����Ϣ
select  * from   t_userinfo  where uname='����';

--���ݵ�ַȥ��ѯԱ������Ϣ
select  * from   t_userinfo where uaddress='̩��';

--�����Ա�ȥ��ѯԱ����Ϣ

select  * from   t_userinfo where usex='��';

--����Ա���������͵�ַ��ѯ��Ϣ
update  t_userinfo set  uaddress='̩��'  where  id=4;
commit;

select  * from   t_userinfo  where uname='����' and   uaddress='̩��';

select * from t_userinfo where 1=1 and uname=? and uaddress=? and trim(usex)=? 
select  * from   t_userinfo  where 1=1  


select  * from  t_depts

-- �����ѯ������ƥ�������  Ա���������������� ��ַ
select  * from   t_userinfo  u  inner join  t_depts d  on u.did=d.did
where u.uname='����'  and  d.dname=' ������';

update  t_depts  d  set d.dname=?,  where id=?


--��ѯѧ������� 2,6,8,10ѧ����Ϣ
select  *  from  t_userinfo

select  *  from  t_userinfo  where id in (2,4,8,10);

--ɾ��2,4,6,8
delete  from  t_userinfo  where id in (2,4,8,10);

-- �������벿������
select  * from  t_depts

-- ���ݿ⶯̬sql�����������?

-- 1.ͨ��||��ƴ��sql��䣬�����ѯ���ص���һ��ֵ�� execute  immediate   ��̬sql   into  ������
--2.����ǲ�ѯ������һ��������أ�open �α�  for  ��̬sql;

select  * from   t_log

-- ���ű������1000������
declare
begin

   for v_i in  1..1000 loop
   
    if  mod(v_i,2)=0 then
    
       insert  into   t_log  values(seq_log.nextval,'��'||v_i,sysdate,'��¼ʧ��');
      
    else
      insert  into   t_log  values(seq_log.nextval,'��'||v_i,to_date('1998-12-30','yyyy-MM-dd'),'��¼�ɹ�');
    
    end  if;
    
 
end loop;
   commit;
end;

select  count(*) from   t_log

select   * from   t_log

insert  into   t_depts   values(dept_seq.nextval,#{dname},#{dphone},#{dlocation}),

select   * from   t_depts

delete  from   t_depts

commit;
insert into t_depts values(did,dname,dphone,dlocation) UNION ALL (?,?,?,?) UNION ALL (?,?,?,?) UNION ALL (?,?,?,?) UNION ALL (?,?,?,?) UNION ALL (?,?,?,?) 
   

insert  into t_depts(did,dname,dphone,dlocation) values    
(dept_seq.nextval,'11','11','11')    
union all     (dept_seq.nextval,'22','22','22')  
union all     (dept_seq.nextval,'33','33','33')   
union all     (dept_seq.nextval,'44','44','44')   
union all     (dept_seq.nextval,'55','55','55');

commit;


select  * from   t_depts


-- �����������
delete  from  t_fmenu;
commit;

delete  from  t_cmenu;
commit;

select  *   from  t_fmenu

select    * from   t_cmenu

alter  table t_cmenu add   mobileview  varchar2(50);




-- �����Ĳ˵����ƶ��նˣ�jsp��ͼ

select  * from  t_fmenu  f  inner  join  t_cmenu c  on f.fid=c.cfid;

select  * from  t_fmenu

select  * from  t_cmenu  c  where  cfid= 

insert  into t_cmenu values(17,'Ա��ͨ��','telphones',1,sysdate,'empphone.html');
commit;

select  * from  t_userinfo ;

update   t_userinfo  set  phone='13913321080'  where id=2;
update   t_userinfo  set  phone='13913321081'  where id=4;
update   t_userinfo  set  phone='13913321082'  where id=6;
update   t_userinfo  set  phone='13913321083'  where id=8;
update   t_userinfo  set  phone='13913321084'  where id=10;
update   t_userinfo  set  phone='13913321085'  where id=12;
update   t_userinfo  set  phone='13913321086'  where id=14;

commit;
insert  into   t_userinfo  values(seq_userinfo.nextval,'�����','1234','��',sysdate,'����',1,100,null,'13913321098');

alter  table  t_userinfo  add  phone  char(11);

select  * from   t_userinfo
select  * from  t_cmenu

select  count(uname)  from   t_userinfo  where uname=? and  trim(upwd)=?

-- �û���
select  * from  t_userinfo

--�û���ɫ��
select  *  from t_user_role

--��ɫ��
select  * from  t_userrole

--��ɫȨ�ޱ�
select  * from t_role_pr

--Ȩ�ޱ�
select  * from   t_privilege

-- ��¼�û�����������

select  r.rname,  u.uname,p.purl,p.pname   from  t_userinfo  u   inner  join  t_user_role ur
on  u.id=ur.userid   inner  join  t_userrole  r  
on  ur.roleid=r.rid  inner  join t_role_pr  rp
on r.rid=rp.rid  inner  join  t_privilege  p
on rp.pid=p.pid
where u.uname='����'

-- ����һ�ź�������
create  table  t_black
(
   id  int  primary key,
   cip  varchar2(20),
   de   varchar2(100)

)

insert   into  t_black  values(1,'192.168.255.90','̫��');
insert   into  t_black  values(2,'192.168.255.166','������');
commit;

delete  from   t_black;
commit;

select  *  from   t_black

select   * from  t_gridmenu







