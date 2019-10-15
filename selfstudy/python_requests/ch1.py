import  requests
from  pyquery  import PyQuery
from python_requests.db.db import *

url="https://blog.csdn.net/weiqifa0"
result=requests.post(url)
print(result)
#如果出现中文路那么
#result.encoding="utf-8"
content=result.text
#print(content)

#把这个内容转换成PyQuery对象
datas=PyQuery(content)
items=datas(".article-list a")


csdndatas=[]

for  item  in  items:
      obj={}
      #print(item)
      lineObj=PyQuery(item)
      #print(lineObj)
      title=lineObj.text()
      link=lineObj.attr("href")
      obj["title"]=title
      obj["link"]=link
      csdndatas.append(obj)




print(csdndatas)

