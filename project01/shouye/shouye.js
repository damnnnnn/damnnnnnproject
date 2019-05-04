let app = getApp();
Page({
  data: {
    num:0,
    shouye:[],
    shouyeselect:[],
    music:[],
    jiugongge:[],
    currentyemian:0,
    url:"http://damnnnnn.natapp1.cc/audio/haoxiangdashengshuoaini.mp3",
    img:"http://damnnnnn.natapp1.cc/images/haoxiangdashengshuoaini.JPG",
    author:"茶理理",
    name:"君が好きだと叫びたい"
  },
  clickone(e){
    console.log(e.currentTarget.dataset.num);
    let num = e.currentTarget.dataset.num;
    this.setData({num:num,currentyemian:num});
    // if(num==0){
    //   this.setData({currentyemian:num})
    // }
    // if(num==1){
    //   this.setData({currentyemian:num})
    // }
    // if(num==2){
    //   this.setData({currentyemian:num})
    // }
  },
  clicktwo(e){
    console.log(e.currentTarget.dataset);
    let url = e.currentTarget.dataset.url;
    let name = e.currentTarget.dataset.name;
    let author = e.currentTarget.dataset.author;
    let img = e.currentTarget.dataset.img;
    this.setData({url:url,img:img,author:author,name:name});
    let audioplay = my.createAudioContext("ad",this)
    audioplay.play();
  },
  clickthree(e){
    console.log(e.currentTarget.dataset.jiugonggeid);
    let jiugonggeid=e.currentTarget.dataset.jiugonggeid;
    if(jiugonggeid==0){
       my.navigateTo({
         url:"../xinwen/xinwen"
       });
    }
    if(jiugonggeid==1){
       my.navigateTo({
         url:"../yinyue/yinyue"
       });
    }
    if(jiugonggeid==2){
       my.navigateTo({
         url:"../sousuo/sousuo"
       });
    }
    if(jiugonggeid==3){
       my.navigateTo({
         url:"../shiping/shiping"
       });
    }
    if(jiugonggeid==4){
       my.navigateTo({
         url:"../shoucang/shoucang"
       });
    }
    if(jiugonggeid==5){
       my.navigateTo({
         url:"../tianqi/tianqi"
       });
    }
    if(jiugonggeid==6){
       my.navigateTo({
         url:"../map/map"
       });
    }
    if(jiugonggeid==7){
       my.navigateTo({
         url:"../daka/daka"
       });
    }
    if(jiugonggeid==8){
       my.navigateTo({
         url:"../xunzhang/xunzhang"
       });
    }
  },
  clickfour(){
    my.scan({
      type:"qr",
      success:(res)=>{
        my.alert({
          content:res.code
        });
      }
    });
  },
  onLoad() {
    let shouye = app.globalData.shouye;
    let shouyeselect=app.globalData.shouyeselect;
    let music=app.globalData.music;
    let jiugongge=app.globalData.jiugongge;
    console.log(shouye);
    if(shouye==undefined){
     my.request({
       url: 'http://damnnnnn.natapp1.cc/text/firstproject.json', // 目标服务器url
       success: (res) => {
         console.log(res.data.shouye);
         this.setData({shouye:res.data.shouye})
       },
     });
    }
    else{
      console.log(shouye);
      this.setData({shouye:shouye})
    }
    if(shouyeselect==undefined){
     my.request({
       url: 'http://damnnnnn.natapp1.cc/text/firstproject.json', // 目标服务器url
       success: (res) => {
         console.log(res.data.shouyeselect);
         this.setData({shouyeselect:res.data.shouyeselect})
       },
     });
    }
    else{
      console.log(shouyeselect);
      this.setData({shouyeselect:shouyeselect})
    }
    if(music==undefined){
     my.request({
       url: 'http://damnnnnn.natapp1.cc/text/firstproject.json', // 目标服务器url
       success: (res) => {
         console.log(res.data.music);
         this.setData({music:res.data.music})
       },
     });
    }
    else{
      console.log(music);
      this.setData({music:music})
    }
    if(jiugongge==undefined){
     my.request({
       url: 'http://damnnnnn.natapp1.cc/text/firstproject.json', // 目标服务器url
       success: (res) => {
         console.log(res.data.jiugongge);
         this.setData({jiugongge:res.data.jiugongge})
       },
     });
    }
    else{
      console.log(jiugongge);
      this.setData({jiugongge:jiugongge})
    }
  },
});
