import  pymysql


def  adddatas(pname,purl):
      conn=pymysql.connect(host="127.0.0.1",port=3306,database="qxdb",user="root",password="123456",charset="utf8")
      print(conn)
      sql="insert  into  t_csdnblog(bname,burl)   values(%s,%s)"
      dbcursor=conn.cursor()
      dbcursor.execute(sql,(pname,purl))
      conn.commit()


