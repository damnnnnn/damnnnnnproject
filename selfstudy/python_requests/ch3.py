import  requests
from  pyquery  import  PyQuery


def  parsecsdn(i):
      url="https://blog.csdn.net/weiqifa0/article/list/"+str(i)
      result = requests.post(url)

      content = result.text
      # 把这个内容转换成PyQuery对象
      datas = PyQuery(content)
      items = datas(".article-list a")

      for item in items:
          obj = {}
          # print(item)
          lineObj = PyQuery(item)
          # print(lineObj)
          title = lineObj.text()
          link = lineObj.attr("href")

          print(title,link)


for  i in  range(1,23):
     parsecsdn(i)