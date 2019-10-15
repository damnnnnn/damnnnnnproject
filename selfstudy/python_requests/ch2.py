import requests
from pyquery import PyQuery

name=input("请输入你的名字")

url='http://m.uustv.com/'
datas={"word":name,"sizes":"60","fonts":"qmt.ttf","fontcolor":"#000000"}

result=requests.post(url,datas)
result.encoding="utf-8"
#print(result.text)

datas=PyQuery(result.text)

obj=datas(".tu  img")
print(obj.attr("src"))

print(url+obj.attr("src"))
