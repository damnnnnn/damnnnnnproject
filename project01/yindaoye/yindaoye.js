let app =getApp();
Page({
  data: {
    yindaoye:[]
  },
  clickone(){
    my.navigateTo({
      url:"../shouye/shouye"
    });
  },
  onLoad() {
    let yindaoye =app.globalData.yindaoye;
    console.log(yindaoye);
    if(yindaoye==undefined){
     my.request({
       url: 'http://damnnnnn.natapp1.cc/text/firstproject.json', // 目标服务器url
       success: (res) => {
         console.log(res.data.yindaoye);
         this.setData({yindaoye:res.data.yindaoye})
       },
     });
    }
    else{
      console.log("已经从app页面拿到");
      console.log(yindaoye);
      this.setData({yindaoye:yindaoye})
    }
  },
});
