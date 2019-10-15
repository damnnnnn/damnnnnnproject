import  requests

url="http://127.0.0.1:8100/users"
options={"User-Agent":"Mozilla"}
result=requests.post(url,headers=options)
print(result)
print(result.text)