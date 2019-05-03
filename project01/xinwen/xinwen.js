let app =getApp();
Page({
  data: {
    xinwentitle:[],
    xinwenyemian:0,
    xinwen:10,
    xinwentoutiao:[],
    xinwenshehui:[],
    xinwenjunshi:[],
    xinwenkeji:[],
    xinwenguonei:[],
    xinwenguoji:[],
    xinwenshishang:[],
    xinwentiyu:[],
    xinwenyule:[],
    xinwencaijing:[],
    // xinwenlaiyuan:"top"
  },
  clickone(e){
    let that = this;
    console.log(e.currentTarget.dataset.num);
    let num = e.currentTarget.dataset.num;
    if (num==0){
      that.setData({xinwenyemian:0})
    }
    if (num==1){
      that.setData({xinwenyemian:1})
    }
    if (num==2){
      that.setData({xinwenyemian:2})
    }
    if (num==3){
      that.setData({xinwenyemian:3})
    }
    if (num==4){
      that.setData({xinwenyemian:4})
    }
    if (num==5){
      that.setData({xinwenyemian:5})
    }
    if (num==6){
      that.setData({xinwenyemian:6})
    }
    if (num==7){
      that.setData({xinwenyemian:7})
    }
    if (num==8){
      that.setData({xinwenyemian:8})
    }
    if (num==9){
      that.setData({xinwenyemian:9})
    }
  },
  onLoad() {
    let xinwentitle = app.globalData.xinwentitle;
    let xinwenlaiyuan =xinwenlaiyuan;
    let that = this;
    console.log(xinwentitle)
    if (xinwentitle==undefined){
      my.request({
        url: 'http://damnnnnn.natapp1.cc/text/firstproject.json', // 目标服务器url
        success: (res) => {
          console.log(res.data.xinwentitle);
          that.setData({xinwentitle:res.data.xinwentitle});
        },
      });
    }
    else{
      console.log("----------已经请求到了数据----------");
      console.log(xinwentitle);
      that.setData({xinwentitle:xinwentitle});
    }
  my.request({
        url: 'http://v.juhe.cn/toutiao/index?type=top&key=e79de06ab95e2b39dc62b5783bb42739', // 目标服务器url
        success: (res) => {
          console.log(res.data.result.data);
          this.setData({xinwentoutiao:res.data.result.data})
        },
      });
  my.request({
        url: 'http://v.juhe.cn/toutiao/index?type=shehui&key=e79de06ab95e2b39dc62b5783bb42739', // 目标服务器url
        success: (res) => {
          console.log(res.data.result.data);
          this.setData({xinwenshehui:res.data.result.data})
        },
      });
   my.request({
        url: 'http://v.juhe.cn/toutiao/index?type=junshi&key=e79de06ab95e2b39dc62b5783bb42739', // 目标服务器url
        success: (res) => {
          console.log(res.data.result.data);
          this.setData({xinwenjunshi:res.data.result.data})
        },
      });
   my.request({
        url: 'http://v.juhe.cn/toutiao/index?type=keji&key=e79de06ab95e2b39dc62b5783bb42739', // 目标服务器url
        success: (res) => {
          console.log(res.data.result.data);
          this.setData({xinwenkeji:res.data.result.data})
        },
      });
     my.request({
        url: 'http://v.juhe.cn/toutiao/index?type=guonei&key=e79de06ab95e2b39dc62b5783bb42739', // 目标服务器url
        success: (res) => {
          console.log(res.data.result.data);
          this.setData({xinwenguonei:res.data.result.data})
        },
      });
   my.request({
        url: 'http://v.juhe.cn/toutiao/index?type=guoji&key=e79de06ab95e2b39dc62b5783bb42739', // 目标服务器url
        success: (res) => {
          console.log(res.data.result.data);
          this.setData({xinwenguoji:res.data.result.data})
        },
      });
     my.request({
        url: 'http://v.juhe.cn/toutiao/index?type=shishang&key=e79de06ab95e2b39dc62b5783bb42739', // 目标服务器url
        success: (res) => {
          console.log(res.data.result.data);
          this.setData({xinwenshishang:res.data.result.data})
        },
      });
   my.request({
        url: 'http://v.juhe.cn/toutiao/index?type=tiyu&key=e79de06ab95e2b39dc62b5783bb42739', // 目标服务器url
        success: (res) => {
          console.log(res.data.result.data);
          this.setData({xinwentiyu:res.data.result.data})
        },
      });
     my.request({
        url: 'http://v.juhe.cn/toutiao/index?type=yule&key=e79de06ab95e2b39dc62b5783bb42739', // 目标服务器url
        success: (res) => {
          console.log(res.data.result.data);
          this.setData({xinwenyule:res.data.result.data})
        },
      });
   my.request({
        url: 'http://v.juhe.cn/toutiao/index?type=caijing&key=e79de06ab95e2b39dc62b5783bb42739', // 目标服务器url
        success: (res) => {
          console.log(res.data.result.data);
          this.setData({xinwencaijing:res.data.result.data})
        },
      });
  },

});
