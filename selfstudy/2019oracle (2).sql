-- 创建表
-- 单表的增删改查
-- 查询技术  70%来自于查询,熟练,熟练,熟练
-- 多表查询
-- 数据库块编程，oracle,pl-sql块

--sql的语句块，多条sql为完成一个复杂的业务逻辑在一起执行
--块不能被编程语言所调用，只能运行在数据库端。
--块的结构，固定的一个结构
--declare
 --定义变量
--begin

 --多条sql，一条条执行。

--end;

-- 输入一个数，判断这个数是偶数还是奇数，并输出
--分支语句
accept num  prompt '请输入一个数:'
declare
   inputnum  number:=&num;  -- 定义变量 ，变量名字和类型，赋值:=
begin
   
    if  mod(inputnum,2)=0 then  -- 是一个等于
       dbms_output.put_line('这个数:'||inputnum||',是偶数');
       
    else
       dbms_output.put_line('这个数:'||inputnum||',是奇数');
    
    end  if;
  
end;

--循环语句1  for循环

declare
dsum number:=0;
begin
 
   for  num  in 1..100  loop
     dsum:=dsum+num;
   end  loop;
   dbms_output.put_line('1.100的和为:'||dsum);

end;


--循环语句2  while循环

declare
v_num number:=1;
dsum number:=0;
begin
  while v_num<=100 loop
      dsum:=dsum+v_num;
      v_num:=v_num+1;
  end loop;
 dbms_output.put_line('1.100的和为:'||dsum);
end;


-- PL-SQL流程控制，赋值

select   *   from  t_emp

select  * from  t_dept

-- 1.输入员工的姓名，找出这个员工的部门的名称
-- 链表查询解决方案
select d.dname  from  t_emp  e  inner  join t_dept  d  on  e.eid=d.id  where e.ename='王长林';

-- pl-sql块的解决方案
accept  ename   prompt '请输入员工姓名:'
declare
v_name  varchar2(50):='&ename';
v_eid  t_emp.eid%type;  --和数据库中表的字段的类型保持一致

v_dname t_dept.dname%type;
begin
  
    -- 根据姓名得到员工的部门编号, sql语句中的赋值必须使用into关键字，into只能附一个值，如果是多个值就是游标（结果集),游标不能
    --用into来赋值
   
     select  e.eid   into  v_eid  from  t_emp e where e.ename=v_name;
    dbms_output.put_line('部门编号为:'||v_eid);
    
    -- .根据查出的部门的编号，去匹配部门表
    select  d.dname  into v_dname    from  t_dept  d  where  d.id= v_eid;
     dbms_output.put_line('部门名称为:'||v_dname);
     
exception  -- 异常处理块
   when  others  then
     dbms_output.put_line('对不起,没有这个员工');
   
end;

-- 
select  * from  t_role_function

select  * from t_emp

select  * from  t_role

--  找出王长林员工的功能名称，就是权限。
-- 子查询 ，效率低
select rf.operatorname
from  t_role_function  rf  where  rf.rid=(select r.id  from  
t_role r where  r.rname= (select  e.ejob from  t_emp e  where e.ename='王长林'))
and  flag=1;

-- 连接查询
select   rf.operatorname  from  t_role_function  rf  inner   join 
(select  r.id   rid from  t_emp e   inner   join  t_role  r   on  e.ejob=r.rname  where e.ename='王长林')  tmp
on tmp.rid=rf.rid  where flag=1;

-- pl-SQL块处理
accept  ename  prompt  '输入员工姓名'
declare
v_ename  t_emp.ename%type:='&ename';
v_job  t_emp.ejob%type;
v_rid  t_role.id%type;

begin

   select  e.ejob  into v_job  from  t_emp  e  where e.ename=v_ename;
   dbms_output.put_line(v_job);
   
   select r.id   into v_rid  from  t_role  r  where r.rname=v_job;
   dbms_output.put_line(v_rid);
   
    --多个值是不能into赋值的，多个值是游标是结果集
    --select rf.operatorname into  v_fname  from  t_role_function  rf  where rf.rid=v_rid  and  flag=1;
    --dbms_output.put_line(v_ename||',拥有功能名称为'||v_fname);
    
    --datas row对象
   for  datas in  (select rf.operatorname  fname   from  t_role_function  rf  where rf.rid=v_rid  and  flag=1)  loop
       dbms_output.put_line('拥有功能名称为'||datas.fname);
   end loop;
end;

-- pl-sql
-- 
select  * from  t_emp

update  t_emp  set esalary=500  where  ename='王长林';
update  t_emp  set esalary=1500  where  ename='黄飞';
commit;

select   * from  t_dept

-- 输入部门名称，这个部门员工收入小于2000的,加工资500，并输出更新后员工的基本信息。

accept  dname  prompt '请输入部门名称'

declare
v_dname  t_dept.dname%type:='&dname';
v_did  t_dept.id%type;
v_str varchar2(100);
v_s t_emp.esalary%type;
begin
   
     select  d.id   into  v_did  from  t_dept  d  where d.dname=v_dname;
         dbms_output.put_line('部门id为:'|| v_did);
        
         
        for  datas in  (select  e.id  eeid,e.ename  eename,e.esalary+nvl(e.ecomm,0)  eem 
        from  t_emp  e  where e.eid=v_did)  loop
           dbms_output.put_line(datas.eeid||','||datas.eename||','||datas.eem);
           
            if  datas.eem<2000 then
            
               update  t_emp e  set e.esalary=e.esalary+500  where   e.id=datas.eeid;
               
               select   e.esalary+nvl(e.ecomm,0)  into   v_s from   t_emp  e   where   e.id=datas.eeid;
               
               v_str:=datas.eename||',更新后的收入'||v_s;
            
            end  if;
        
        end loop;
        
        commit;
        
      dbms_output.put_line('信息为:'||v_str);   
 exception
 
   when  others  then
   v_str:='对不起，你输入有错误，没有这个部门，请核实';
    dbms_output.put_line('信息为:'||v_str);  
end;

-- 建立一张表，给我这个表，收到造100W条数据。

--pl-sql块的局限，不能够被编程语言调用，只能运行在数据库端。

select  * from t_emp
-- 存储过程，一个存储的名字+过程化的语句块
--  存储过程能够被编程语言调用，预编译过的，编译一次后面调用不需要在编译，性能很好。
create   or  replace   procedure  p_login
(
   v_name  in varchar2, 
   v_pwd   in  varchar2,
   v_result  out varchar2
)
as
v_count   int;
begin

    --1.登录验证
    select count(*) into  v_count from t_emp  e  where e.ename=v_name  and trim(e.epwd)=v_pwd;
    
    if v_count>0 then
      v_result:='登录成功';
    else
      v_result:='登录失败';
    end  if;
    
    insert  into  t_log   values(seq_log.nextval,v_name,sysdate,v_result);
    commit;
    
    dbms_output.put_line(v_result);

end;

-- 带有游标的存储过程

select   *  from  t_emp

select  *  from  t_dept

-- 输入参数部门名称，得到这个部门的男性员工的信息
create  or   replace  procedure   p_queryempmen
(
    v_dname  in  varchar2,
    v_datas out  sys_refcursor  --结果集  系统游标
)
as
begin

   --直接返回结果集
   open  v_datas  for   select  e.*  from  t_dept d inner  join t_emp  e
   on  e.eid=d.id   where d.dname=v_dname
   and  e.sex='男';
  
end;

-- 存储过程可以输入和返回多个值，返回的是一个结果集，游标，sys_refcursor，java端的接受oracle.jdbc.OracleTypes.CURSOR
-- 游标内部的遍历，for  ..loop  ...end  loop;

-- 动态sql   sql是一个动态的拼接的语句。 *****

-- 查询一个表的条数
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
  
   --动态sql执行，如果返回是一个值execute  immediate
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

  --动态sql执行,如果是游标类型
  open   v_datas  for  v_sql;

end;

-- 动态sql是个游标类型，在内部遍历
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
       exit  when  v_datas%notfound;  -- 结果集没有数据
        v_msg:=v_msg||v_id||','||v_dname||','||v_loc;
    end  loop;    
    dbms_output.put_line(v_msg);
    
end;

-- 自定义函数  特殊的存储过程，只能返回一个值
create  or  replace  function  f_querycount
(
  v_tableName  in    varchar2
)
return int  --不能有;号
as
v_sql  varchar2(1000);
v_count  int;
begin
 v_sql:='select  count(*)  from   ' ||v_tableName;
 
 execute  immediate  v_sql  into  v_count;
 
 return v_count;

end;

 -- 存储过程和自定义函数的区别，关键是自定义函数有且只能有一个返回值。
 
 
 
 -- 分页的存储过程，通用性，100张表都能分页
 create  or  replace  procedure  p_pages
 (
    v_tableName  in  varchar2, -- 表名
    v_pagenum  in  number,  -- 第几页
    v_pagefixedsize in  number, -- 每页条数
    v_count  out  number,  -- 总共多少条
    v_size   out number,-- 总共多少页，
    v_datas  out  sys_refcursor --分页的结果集
  
 )
 as
 v_endnumber number:=v_pagenum*v_pagefixedsize;
 v_startnumber  number:=(v_pagenum-1)*v_pagefixedsize;
v_sql varchar2(1000);
 begin
 
    --1.总共多少条
    v_sql:='select  count(*) from  '||v_tableName;
    
    execute  immediate   v_sql  into  v_count;
    
    --2.知道多少页
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
 
 
 -- oracle建立权限关系模型表
 
 -- 业务
 
 -- 用户表，角色表 ，用户和角色(多个用户多个角色)，权限表， 角色和权限表(一个角色有多个权限)
 
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
 
 insert  into   t_userinfo  values(seq_userinfo.nextval,'曹正','1234','男',to_date('1997-12-16','yyyy-MM-dd'),'泰州',1);
  insert  into   t_userinfo  values(seq_userinfo.nextval,'李乃昊','123456','男',to_date('1995-12-16','yyyy-MM-dd'),'盐城',1);
  commit;
  
  select   * from  t_userinfo;
 
 create  table  t_userrole
 (
    rid   int   primary  key,
    rname  varchar2(20)
 )
 
 insert  into   t_userrole(rid ,rname)  values(1,'管理员');
  insert  into   t_userrole(rid ,rname)  values(2,'普通员工');
  commit;
  
  select   * from   t_userrole
 
  -- 创建用户角色表  中间关系表
  create   table  t_user_role
  (
     userid  int ,
     roleid  int
  )
  
  insert  into   t_user_role  values(2,1);
  insert  into   t_user_role  values(2,2);
  insert  into   t_user_role  values(4,2);
  
  select   * from  t_user_role;
  
  -- 员工编号是2的员工的姓名和角色名称
  select  u.uname,r.rname from  t_userinfo  u   inner  join t_user_role ur
  on u.id=ur.userid  inner  join  t_userrole  r  on  r.rid=ur.roleid where  u.id=2;
  
  
  -- 员工表-----多对多关系[中间关系表，员工和角色关系表]-----角色表
  
  
  --建立一张权限表
  create   table  t_privilege
  (
     pid  int  primary key ,-- 权限id
     pname  varchar2(20), --权限名称
     purl  varchar2(100)  --权限动作

  )
  
  create  sequence  seq_privilege
  start  with 10
  increment  by 3
  nocache;
  
  
  insert  into   t_privilege   values(seq_privilege.nextval,'查询员工信息','queryuser');
  insert  into   t_privilege   values(seq_privilege.nextval,'增加员工信息','adduser');
  insert  into   t_privilege   values(seq_privilege.nextval,'删除员工信息','deluser');
  insert  into   t_privilege   values(seq_privilege.nextval,'更新员工信息','updateuser');
  insert  into   t_privilege   values(seq_privilege.nextval,' 查询系统日志','syslog');
  insert  into   t_privilege   values(seq_privilege.nextval,' 查询公司利润','querymoney');
  commit;
  
  select  * from   t_privilege
  
  -- 角色和权限建立了 中间关系表  t_role_pr
 
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
  
  
  --  查询员工编号是2的员工姓名，角色和权限名称，动作
   select  u.uname,r.rname,p.pname,p.purl from  t_userinfo  u   inner  join t_user_role ur
  on u.id=ur.userid  inner  join  t_userrole  r  on  r.rid=ur.roleid
  inner  join  t_role_pr  rp on r.rid=rp.rid  
  inner  join  t_privilege   p   on  p.pid=rp.pid
  where  u.id=4;
  
  
  -- 建立部门表  部门和员工是一对多关系
  
  create   table  t_depts
  (
     did  int  primary  key,
     dname  varchar2(20),
     dphone  char(11),
     dlocation  varchar2(20)
  
  )
  
  insert  into    t_depts  values(100,'综合部','13912221098','南京');
  insert  into    t_depts  values(101,'生产部','13912221099','越南');
  commit;
  
  
  -- 建立   一个家庭信息
  
  
  -- mybatis 一对一，一对多，多对多
  
  select  *  from  t_depts 
  
  select  * from  t_userinfo
  
  
  -- 每个部门的员工的信息(一对多)
  
  select  d.dname, d.dphone, u.uname,u.uaddress,u.usex
  from  t_depts  d  inner  join  t_userinfo  u  on  d.did=u.did
  
  
  -- 家庭信息表
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
  
  insert  into   t_fam  values(seq_fam.nextval,'王某','北京',2);
  insert  into   t_fam  values(seq_fam.nextval,'张某','上海',4);
  commit;
  
  select  * from   t_fam
  
  -- 曹正老婆的名字和地址
  select  f.fname,f.faddress,u.uaddress,u.uname
  from  t_userinfo  u  inner join  t_fam   f  on   u.id=f.userid  where u.uname='曹正'
  
  
-------------------------------------------------------------------------------------------------------------

-- 登录，日志表，记录登录的情况
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








