import  requests
from  pyquery  import PyQuery


url="https://blog.csdn.net/weiqifa0"
result=requests.post(url)
print(result)
#如果出现中文路那么
#result.encoding="utf-8"
content=result.text
#print(content)

#把这个内容转换成PyQuery对象
datas=PyQuery(content)
items=datas(".article-list .article-item-box")
for   item in  items:
      #print(item)
      itemobj= PyQuery(item)
      #print(itemobj.text())

      aobj=itemobj("a")
      dateobj=itemobj(".date")
      numobj=itemobj(".read-num")
      print(aobj.text(),aobj.attr("href"),dateobj.text(),numobj.text())