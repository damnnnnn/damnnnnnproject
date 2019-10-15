import requests
import os
from  pyquery  import PyQuery

old_url = "http://www.mzitu.com/123114"

#前一个头作为请求网站，后一个头作为破解盗链使用
Hostreferer = {
    'User-Agent':'Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)',
    'Referer':'http://www.mzitu.com'
}

Picreferer = {
    'User-Agent':'Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)',
    'Referer':'http://i.meizitu.net'
}


def get_html(url):#获得页面html代码
    req = requests.get(url, headers=Hostreferer)
    html = req.text
    return html

def get_page_name(url):#获得图集最大页数和名称
    html = get_html(url)
    #print(html)
    docs=PyQuery(html)
    print(docs(".pagenavi span").eq(6).text())
    print(docs(".main-title").text())
    page =docs(".pagenavi span").eq(6).text()
    name=docs(".main-title").text()
    return  page,name

def get_img_url(url, name):
    html = get_html(url)
    soup = PyQuery(html)
    img_url = soup.find('img')
    print( img_url.attr("src"))
    return img_url.attr("src")

def save_img(img_url, count, name):
    req = requests.get(img_url, headers=Picreferer)
    with open(name+'/'+str(count)+'.jpg', 'wb') as f:
        f.write(req.content)


def main():
    old_url = "http://www.mzitu.com/123114"
    page, name = get_page_name(old_url)
    os.mkdir(name)
    for i in range(1, int(page) + 1):
        url = old_url + "/" + str(i)
        print(url)
        img_url = get_img_url(url, name)
        save_img(img_url, i, name)
        print('保存第' + str(i) + '张图片成功')

main()