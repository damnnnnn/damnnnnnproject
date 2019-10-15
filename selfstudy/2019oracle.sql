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
 
 alter  table  t_userinfo add faceimg  varchar2(50);
 
 select  * from   t_userinfo  
 
 update  t_userinfo  set faceimg='http://127.0.0.1:8500/images/cz.png'  where id =2;
 commit;
 
 select  * from   t_userinfo  u  where u.uname='曹正';
 
 
 
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
  
  
  -- 根据用户的姓名查询该用户的权限
     select  u.uname,r.rname,p.pname,p.purl from  t_userinfo  u   inner  join t_user_role ur
  on u.id=ur.userid  inner  join  t_userrole  r  on  r.rid=ur.roleid
  inner  join  t_role_pr  rp on r.rid=rp.rid  
  inner  join  t_privilege   p   on  p.pid=rp.pid
  where  u.uname='李乃昊';
  
  
  
  
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
  
  -- 存储过程，输入参数是用户姓名，找出它的部门名称，和配偶的姓名和地址。
  create   or  replace   procedure   p_queryByUserNameInfo
  (
     v_uname  in  varchar2,
     v_dname  out varchar2,
     v_wname  out  varchar2,
     v_add  out  varchar2
  )
  as
  begin
    --1. 根据用户姓名，可以找到部门的编号，通过部门编号可以联表查询部门名称
    select   d.dname  into  v_dname  from  t_userinfo  u  inner   join
    t_depts  d  on u.did =d.did  where u.uname=v_uname;
    
    --2. 根据用户姓名,找到家庭的编号
    select  f.fname,f.faddress  into v_wname,  v_add  from    t_userinfo  u  
    inner  join  t_fam f on  u.id=f.userid  where u.uname=v_uname;
exception
  when  others  then
  v_dname:='没有';
  v_wname:='没有' ;
  v_add:='没有';
  end;
  
  
  -- 返回游标
  
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
   flag  int  -- 是否启用这个菜单

)

drop  table t_leftmenus

insert  into   t_leftmenus   values(1,'员工信息','/emps/infos','http://127.0.0.1:8500/images/f1.png',default,'admin',1);
insert  into   t_leftmenus   values(2,'部门信息','/depts/infos','http://127.0.0.1:8500/images/f2.png',default,'admin',1);
insert  into   t_leftmenus   values(3,'角色信息','/roles/infos','http://127.0.0.1:8500/images/f3.png',default,'admin',1);
insert  into   t_leftmenus   values(4,'权限维护','/check/infos','http://127.0.0.1:8500/images/f4.png',default,'admin',1);
insert  into   t_leftmenus   values(5,'日志管理','/logs/infos','http://127.0.0.1:8500/images/f5.png',default,'admin',1);
insert  into   t_leftmenus   values(6,'数据报表','/datas/infos','http://127.0.0.1:8500/images/f6.png',default,'admin',1);
insert  into   t_leftmenus   values(7,'小程序管理','/smallproject/infos','http://127.0.0.1:8500/images/f7.png',default,'admin',1);
insert  into   t_leftmenus   values(8,'固定资产','/fixeds/infos','http://127.0.0.1:8500/images/f8.png',default,'admin',1);
insert  into   t_leftmenus   values(9,'信息维护','/infos/infos','http://127.0.0.1:8500/images/f9.png',default,'admin',1);
insert  into   t_leftmenus   values(10,'档案文档','/words/infos','http://127.0.0.1:8500/images/f10.png',default,'admin',1);
insert  into   t_leftmenus   values(11,'消息通信','/message/infos','http://127.0.0.1:8500/images/f11.png',default,'admin',1);

commit;

update t_leftmenus  set curl='/manager/infos'  where id=7;
commit;

select  *  from  t_leftmenus

select  * from  t_userinfo

-- 轮播图表
select  *  from   t_swiper

insert  into   t_swiper  values(6,'智码堂','http://localhost:8500/images/m6.jpg',5000,
to_date('2019-07-01','yyyy-MM-dd'));
commit;
 

update t_swiper  set ttime=sysdate, turl='http://localhost:8500/images/6.jpg'  where  tid=6;
commit;

-- 轮播图选出的策略，价格最高的三位并时间是当天

   select *  from  (select  rownum , s.* from   t_swiper s  where   
   trunc(ttime)=trunc(sysdate) order by tmoney desc) t where rownum<=3;
   
   select  * from  t_emp
   
   insert into   t_emp  values(11,'陈宇涛','123','普通员工',5000,500,null,sysdate,'13913321098','南京','博士',10,'男');
   commit;
   
   insert into   t_emp  values(12,'李耀辉','123','普通员工',5000,500,null,sysdate,'13913321099','南京','博士',10,'男');
   insert into   t_emp  values(13,'刘坚','123','普通员工',5000,500,null,sysdate,'13913321098','南京','本科',10,'男');
   insert into   t_emp  values(14,'陈明','123','普通员工',5000,500,null,sysdate,'13913321098','南京','博士',10,'男');
   insert into   t_emp  values(15,'段金旺','123','普通员工',5000,500,null,sysdate,'13913321098','南京','博士',10,'男');
   insert into   t_emp  values(16,'曹正','123','普通员工',5000,500,null,sysdate,'13913321098','上海','本科',10,'男');
   insert into   t_emp  values(17,'何鹏程','123','普通员工',5000,500,null,sysdate,'13913321098','南京','本科',10,'男');
   insert into   t_emp  values(18,'刘东正','123','普通员工',5000,500,null,sysdate,'13913321098','北京','博士',10,'男');
   insert into   t_emp  values(19,'梁晓琪','123','普通员工',5000,500,null,sysdate,'13913321098','南京','本科',10,'女');
   insert into   t_emp  values(20,'万慧琴','123','普通员工',5000,500,null,sysdate,'13913321098','南京','本科',10,'女');
   commit;
   insert into   t_emp  values(11,'陈宇涛','123','普通员工',5000,500,null,sysdate,'13913321098','南京','博士',10,'男');
   
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
       v_msg:=v_name||','||'登录成功';
    else
        v_msg:=v_name||','||'登录失败';
    end if;
    
    
    insert  into  t_log  values(seq_log.nextval,v_name,sysdate,v_msg);
    commit;
  
  end;
  
  alter   table   t_log  modify  result  varchar2(200);
  
  
  
  select  * from  t_depts

select  *  from  t_userinfo

insert  into  t_userinfo  values(seq_userinfo.nextval,'刘坚','234','男',sysdate,'连云港',1,100,null);
insert  into  t_userinfo  values(seq_userinfo.nextval,'陈宇涛','234','男',sysdate,'连云港',1,101,null);
insert  into  t_userinfo  values(seq_userinfo.nextval,'陈明','234','男',sysdate,'连云港',1,100,null);
insert  into  t_userinfo  values(seq_userinfo.nextval,'李耀辉','234','男',sysdate,'连云港',1,101,null);
insert  into  t_userinfo  values(seq_userinfo.nextval,'万慧琴','234','女',sysdate,'南通',1,null,null);

commit;



-- 查询每个部门的员工的信息
select  d.dname,u.uname,d.dlocation,d.dphone 
from t_userinfo u inner join  t_depts  d  on u.did=d.did  


--查询每个部门的员工的性别的数量
select   count(u.usex),nvl(d.dname,'统计毛呀，，还没部门'),u.usex
from t_userinfo u left join  t_depts  d  on u.did=d.did  
group  by  d.dname,u.usex

execute immerdate

-- mybatis动态sql

-- 是基于mybatis框架

select   * from  t_userinfo

-- 根据姓名去查询员工信息
select  * from   t_userinfo  where uname='曹正';

--根据地址去查询员工的信息
select  * from   t_userinfo where uaddress='泰州';

--根据性别去查询员工信息

select  * from   t_userinfo where usex='男';

--根据员工的姓名和地址查询信息
update  t_userinfo set  uaddress='泰州'  where  id=4;
commit;

select  * from   t_userinfo  where uname='曹正' and   uaddress='泰州';

select * from t_userinfo where 1=1 and uname=? and uaddress=? and trim(usex)=? 
select  * from   t_userinfo  where 1=1  


select  * from  t_depts

-- 联表查询，经常匹配的条件  员工姓名，部门名称 地址
select  * from   t_userinfo  u  inner join  t_depts d  on u.did=d.did
where u.uname='曹正'  and  d.dname=' 质量部';

update  t_depts  d  set d.dname=?,  where id=?


--查询学生编号是 2,6,8,10学生信息
select  *  from  t_userinfo

select  *  from  t_userinfo  where id in (2,4,8,10);

--删除2,4,6,8
delete  from  t_userinfo  where id in (2,4,8,10);

-- 批量插入部门数据
select  * from  t_depts

-- 数据库动态sql是如何描述的?

-- 1.通过||来拼接sql语句，如果查询返回的是一个值， execute  immediate   动态sql   into  变量。
--2.如果是查询返回是一个结果集呢？open 游标  for  动态sql;

select  * from   t_log

-- 这张表给我做1000条数据
declare
begin

   for v_i in  1..1000 loop
   
    if  mod(v_i,2)=0 then
    
       insert  into   t_log  values(seq_log.nextval,'王'||v_i,sysdate,'登录失败');
      
    else
      insert  into   t_log  values(seq_log.nextval,'李'||v_i,to_date('1998-12-30','yyyy-MM-dd'),'登录成功');
    
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


-- 批量数据入库
delete  from  t_fmenu;
commit;

delete  from  t_cmenu;
commit;

select  *   from  t_fmenu

select    * from   t_cmenu

alter  table t_cmenu add   mobileview  varchar2(50);




-- 联动的菜单，移动终端，jsp视图

select  * from  t_fmenu  f  inner  join  t_cmenu c  on f.fid=c.cfid;

select  * from  t_fmenu

select  * from  t_cmenu  c  where  cfid= 

insert  into t_cmenu values(17,'员工通信','telphones',1,sysdate,'empphone.html');
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
insert  into   t_userinfo  values(seq_userinfo.nextval,'赵玉斌','1234','男',sysdate,'安庆',1,100,null,'13913321098');

alter  table  t_userinfo  add  phone  char(11);

select  * from   t_userinfo
select  * from  t_cmenu

select  count(uname)  from   t_userinfo  where uname=? and  trim(upwd)=?

-- 用户表
select  * from  t_userinfo

--用户角色表
select  *  from t_user_role

--角色表
select  * from  t_userrole

--角色权限表
select  * from t_role_pr

--权限表
select  * from   t_privilege

-- 登录用户如果是李乃昊

select  r.rname,  u.uname,p.purl,p.pname   from  t_userinfo  u   inner  join  t_user_role ur
on  u.id=ur.userid   inner  join  t_userrole  r  
on  ur.roleid=r.rid  inner  join t_role_pr  rp
on r.rid=rp.rid  inner  join  t_privilege  p
on rp.pid=p.pid
where u.uname='曹正'

-- 创建一张黑名单表
create  table  t_black
(
   id  int  primary key,
   cip  varchar2(20),
   de   varchar2(100)

)

insert   into  t_black  values(1,'192.168.255.90','太懒');
insert   into  t_black  values(2,'192.168.255.166','不听话');
commit;

delete  from   t_black;
commit;

select  *  from   t_black

select   * from  t_gridmenu







