-- mysql数据库之旅

-- 数据库分类

-- 关系型数据库   oracle  mysql  db2  sqlserver  
-- 非关系型数据库  Mongodb redis  memcached      NOSQL

-- 关系型数据库 统一的标准的sql
--  DDL语言 数据定义语言  create(创建)  alter(修改)  drop(删除) 只能执行一次
--  DML 语言 数据操纵语言  select(查询)  update(更新) insert(插入)  delete(删除) 可以反复执行
--  DCL语言  数据控制语言  grant(授予)  revoke(撤销)
--  TCL语言  事务控制语言  savepoint(保存点)  rollback（回滚） commit（提交）

-- 数据是保存在数据库中，保存在数据库中，保存的形式就是:表
-- 数据库  库名

-- 创建数据库
CREATE   DATABASE    d_thzm_two;

-- 存储数据的形式 表(table)

-- 三范式 创建表的一个规范
-- 1.表中的列名是最小单位，不能分割  本地化。
-- 2.表中的列名不能由其它字段通过计算得出。（年龄，出生年月）
-- 3.表中的字段只能参照一个主键字段。

-- 创建表
CREATE  TABLE  t_students
(
    sid  INT  PRIMARY KEY  AUTO_INCREMENT, -- 学生编号   -- PRIMARY KEY(主键，唯一不能重复，不能为空) AUTO_INCREMENT(自增长,1,1)
    sname  VARCHAR(20) , -- 学生姓名
    spwd  VARCHAR(8), -- 学生密码
    ssex  CHAR(4),  -- 学生性别
    sbirthday DATETIME, -- 出生年月
    money  FLOAT  -- 余额
)


CREATE   TABLE  t_menu
(
    id  INT  PRIMARY KEY  AUTO_INCREMENT,
    mname  VARCHAR(100)
)

INSERT  INTO   t_menu(mname)   VALUES("Java频道");
INSERT  INTO   t_menu(mname)   VALUES("Python频道");
INSERT  INTO   t_menu(mname)   VALUES("Dart频道");
INSERT  INTO   t_menu(mname)   VALUES("JavaScript频道");
-- DML  select语句

SELECT   *  FROM t_students WHERE sid=90
SELECT  *  FROM  t_menu
-- insert  into

INSERT  INTO t_students(sname,spwd,ssex,sbirthday,money)
 VALUES  ('陈明','123456','男','1998-12-30',1000.98);

 
 INSERT  INTO t_students(sname,spwd,ssex,sbirthday,money)
 VALUES  ('刘东正','654321','男','1993-12-30',2000.50);
 
 -- update 更新
 UPDATE t_students SET spwd='000000'  WHERE sid=1;
 
 -- delete 删除
 DELETE  FROM  t_students WHERE sid=2;
 
 -- 聚合函数  统计函数 
 --  sum()  avg()  max()  min()  count()

 -- 内置函数:FORMAT(值，位数),IFNULL(为空，默认值)
 -- SUBSTR()

SELECT   SUM(money)  FROM t_students 
 
SELECT  FORMAT(SUM(money),2)  FROM t_students 
 
SELECT   AVG(money)  FROM t_students 
 
SELECT  FORMAT(AVG(money),2)  FROM t_students 

SELECT   MAX(money)  FROM t_students 

SELECT   MIN(money)  FROM t_students  

SELECT  COUNT(sname) FROM t_students


-- 查询技术  条件查询，比较查询，in，or，and
-- 内置函数

-- 1.找出这个学生表中谁最有钱的学生姓名?
-- 子查询，一个查询的结果作为另一个查询的条件
SELECT  sname  FROM  t_students  
WHERE money=(SELECT   MAX(money)  FROM t_students)

-- 2.用户名(李乃昊)和密码(123456),检查出是否是合法用户？
SELECT  COUNT(*) FROM  t_students
WHERE sname='李乃昊'  AND spwd='654321';

-- 查询所有 *
SELECT  * FROM  t_students

SELECT  sname FROM  t_students

SELECT  sname,sbirthday  FROM  t_students

-- 查询带条件where   查询李乃昊的余额
SELECT  money  FROM  t_students  WHERE sname='李乃昊';

-- 修改这个表增加奖金字段
ALTER  TABLE t_students  ADD  bonus  FLOAT;


SELECT   * FROM  t_students

-- 统计李乃昊的收入
SELECT  money+bonus FROM  t_students  
WHERE sname='李乃昊'

-- 统计黑客的收入
SELECT  FORMAT((money+IFNULL(bonus,0)),2) 
FROM  t_students  
WHERE sname='黑客';

-- 查询奖金不为空的学生的姓名
SELECT   sname,bonus  FROM  t_students  WHERE 
bonus IS NOT NULL;

-- 查询奖金不为空的学生的姓名
SELECT   sname,bonus  FROM  t_students  WHERE 
bonus IS  NULL;

-- 查询money大于1000的学生的姓名
SELECT  sname ,money FROM  t_students 
 WHERE money>1000
 
-- 查询收入大于2000的学生的姓名

SELECT  sname ,money+IFNULL(bonus,0) '收入' FROM  t_students 
 WHERE money+IFNULL(bonus,0)>2000
 
 --  <,=,>=,<= 自行练习
 
 -- money>20,小于2000.5的学生的姓名
 SELECT sname,money  FROM  t_students WHERE money>20 
 AND money<2000.5;
 
 -- BETWEEN..and 在什么之间 >= and <=
 SELECT sname,money  FROM  t_students WHERE money
 BETWEEN 20 AND 2000.5;
 
 -- or 或  学生money是20，或2000.5学生的姓名
 SELECT sname,money  FROM  t_students WHERE money=20
 OR  money=2000.5
 
  
 -- or 或  学生money是2000.5，或性别是女的 学生的姓名
 SELECT sname,money ,ssex FROM  t_students 
 WHERE money=2000.5
 OR  ssex='女';
 
 -- in 在什么范围 ???  float
 -- 学生的钱数是20,2000.5,9000的学生的姓名
 SELECT  sname ,money FROM  t_students  WHERE money
 IN(20,2000.5,9999.9)
 
 -- 分组
 --  学生的男女的数量
 SELECT COUNT(ssex),ssex
  FROM  t_students  GROUP  BY  ssex
  
 -- 学生姓氏的数量
 SELECT  SUBSTR(sname,1,1),COUNT(SUBSTR(sname,1,1))  FROM  t_students
 GROUP  BY  SUBSTR(sname,1,1)
 
 -- 学生姓氏的数量超过1个的,分组带条件,having关键字
SELECT  SUBSTR(sname,1,1),COUNT(SUBSTR(sname,1,1)) 'namecount' FROM  t_students
GROUP  BY  SUBSTR(sname,1,1) HAVING namecount>1;

-- 
SELECT  *  FROM  t_students
-- 现在的时间
SELECT  NOW()  FROM  t_students
SELECT  SUBSTR(NOW(),1,4)-SUBSTR(sbirthday,1,4)
 FROM  t_students
 
 -- 学生各个年龄段的数量
 SELECT  SUBSTR(NOW(),1,4)-SUBSTR(sbirthday,1,4),
 COUNT(SUBSTR(NOW(),1,4)-SUBSTR(sbirthday,1,4))
 FROM  t_students  
 GROUP  BY SUBSTR(NOW(),1,4)-SUBSTR(sbirthday,1,4)

 
 -- 排序 order  by 
 SELECT  sname, money FROM  
 t_students  ORDER  BY  money  ASC
 
  SELECT  sname, money FROM  
 t_students  ORDER  BY  money  DESC
 
 

 -- 排序+分组  学生各个年龄段的数量按照升序
 -- 先分组后排序
 SELECT  SUBSTR(NOW(),1,4)-SUBSTR(sbirthday,1,4),
 COUNT(SUBSTR(NOW(),1,4)-SUBSTR(sbirthday,1,4))
 FROM  t_students  
 GROUP  BY SUBSTR(NOW(),1,4)-SUBSTR(sbirthday,1,4)
 ORDER BY COUNT(SUBSTR(NOW(),1,4)-SUBSTR(sbirthday,1,4))
 DESC
 
 -- 模糊查询  like
 
SELECT  *  FROM   t_students  WHERE sname  LIKE '李%';
 
SELECT  *  FROM   t_students  WHERE sname  LIKE '李_';
  
SELECT  *  FROM   t_students  WHERE sname  LIKE '%琴';
 
 -- 分页查询
 SELECT  *  FROM   t_students 
 
 -- limit关键字  limit ?,? 
 -- 第一个参数从第几条开始，第二个参数是每页几条，
 
 -- 每页几条是固定 4条
 -- 第一页 ：大于(第一页1-1)*每页条数 

SELECT  * FROM  t_students 
 
 SELECT  *  FROM  t_students  LIMIT 0,4
 
  SELECT  *  FROM  t_students  LIMIT 4,4
 
 SELECT  *  FROM  t_students  LIMIT 8,4
 --
 
 
SELECT  * FROM  t_students 
 
 -- limit:第一个参数(当前页数-1)*每页几条
 SELECT  *  FROM  t_students  LIMIT 0,5
 
 -- 查询学生表的第2条到第8条的数据,分别减去1.
 SELECT  *  FROM  t_students  LIMIT 1,7
 

 





 
 
 
 -- 项目脚本
 
 -- 创建一张轮播图表
 CREATE  TABLE  t_swiperimg
 (
    sid  INT  PRIMARY  KEY  AUTO_INCREMENT,
    sname  VARCHAR(50),  -- 图片名称
    spath  VARCHAR(100),  -- 轮播图路径
    surl    VARCHAR(100),  -- 页面路径
    flag   CHAR(4)  -- 是否启用这个图
    
 )
 
 SELECT  *  FROM   t_swiperimg
 
 UPDATE    t_swiperimg  SET  flag=0  WHERE sid=1;
 
  UPDATE    t_swiperimg  SET  spath='http://88aem2.natappfree.cc/images/m1.jpg'  WHERE sid=2;
  UPDATE    t_swiperimg  SET  spath='http://88aem2.natappfree.cc/images/m2.jpg'  WHERE sid=3;
  UPDATE    t_swiperimg  SET  spath='http://88aem2.natappfree.cc/images/m3.jpg'  WHERE sid=4;
  
  SELECT  *  FROM  t_swiperimg
  DELETE  FROM   t_swiperimg
  
  INSERT  INTO  t_swiperimg(sname,spath,surl,flag)
 VALUES('淘宝','http://192.168.255.42:8888/images/s1.jpg','../index/index',1);
 
  INSERT  INTO  t_swiperimg(sname,spath,surl,flag)
 VALUES('京东','http://192.168.255.42:8888/images/s2.jpg','../logs/logs',1);
 
  INSERT  INTO  t_swiperimg(sname,spath,surl,flag)
 VALUES('苏宁','http://192.168.255.42:8888/images/s3.jpg','../two/two',1);
  INSERT  INTO  t_swiperimg(sname,spath,surl,flag)
 VALUES('腾讯','http://192.168.255.42:8888/images/s4.jpg','../two/two',1);
 
 
 INSERT  INTO  t_swiperimg(sname,spath,surl,flag)
 VALUES('淘宝','http://lnh520.natapp1.cc/images/s1.jpg','../index/index',1);
 
  INSERT  INTO  t_swiperimg(sname,spath,surl,flag)
 VALUES('京东','http://lnh520.natapp1.cc/images/s2.jpg','../logs/logs',1);
 
  INSERT  INTO  t_swiperimg(sname,spath,surl,flag)
 VALUES('苏宁','http://lnh520.natapp1.cc/images/s3.jpg','../two/two',1);
  INSERT  INTO  t_swiperimg(sname,spath,surl,flag)
 VALUES('腾讯','http://lnh520.natapp1.cc/images/s4.jpg','../two/two',1);