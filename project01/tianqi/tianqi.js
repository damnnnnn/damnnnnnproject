Page({
  data: {
    name:"",
    result:[],
    future:[],
    weather01:"",
    weather02:"",
    weather03:"",
    weather04:"",
    weather05:"",
    weather06:"",
    weather07:"",
    image01:"",
    image02:"",
    image03:"",
    image04:"",
    image05:"",
    image06:"",
    image07:""
  },
  input(e){
    // console.log(e.detail.value);
    let name = e.detail.value;
    this.setData({name:name});
    // console.log(name)
  },
  clickone(e){
    console.log(e);
    let name =this.data.name;
    let weather01=this.data.weather01;
    let weather02=this.data.weather02;
    let weather03=this.data.weather03;
    let weather04=this.data.weather04;
    let weather05=this.data.weather05;
    let weather06=this.data.weather06;
    let weather07=this.data.weather07;
    // console.log(name);
    my.request({
      url: 'http://v.juhe.cn/weather/index?format=2&cityname='+encodeURI(name)+'&key=6a959238090cfa286ccba7c7836d0b43', // 目标服务器url
      success: (res) => {
        console.log(res.data.result);
        console.log(res.data.result.future[0].weather);
        this.setData({result:res.data.result,future:res.data.result.future,weather01:res.data.result.future[0].weather,
                      weather02:res.data.result.future[1].weather,
                      weather03:res.data.result.future[2].weather,
                      weather04:res.data.result.future[3].weather,
                      weather05:res.data.result.future[4].weather,
                      weather06:res.data.result.future[5].weather,
                      weather07:res.data.result.future[6].weather});
      },
   
    });
    if(weather01=="晴"){
      this.setData({image01:"http://damnnnnn.natapp1.cc/images/qing.png"})
    }
    if(weather01=="多云转晴"){
      this.setData({image01:"http://damnnnnn.natapp1.cc/images/duoyunzhuanqing.png"})
    }
    if(weather01=="晴转多云"){
      this.setData({image01:"http://damnnnnn.natapp1.cc/images/duoyunzhuanqing.png"})
    }
    if(weather01=="阴"){
      this.setData({image01:"http://damnnnnn.natapp1.cc/images/yin.png"})
    }
    if(weather01=="阴转多云"){
      this.setData({image01:"http://damnnnnn.natapp1.cc/images/duoyunzhuanyin.png"})
    }
    if(weather01=="多云转阴"){
      this.setData({image01:"http://damnnnnn.natapp1.cc/images/duoyunzhuanyin.png"})
    }
     if(weather01=="多云"){
      this.setData({image01:"http://damnnnnn.natapp1.cc/images/duoyun.png"})
    }
    if(weather01=="多云转小雨"){
      this.setData({image01:"http://damnnnnn.natapp1.cc/images/yu.png"})
    }
    if(weather01=="小雨"){
      this.setData({image01:"http://damnnnnn.natapp1.cc/images/yu.png"})
    }
     if(weather01=="中雨"){
      this.setData({image01:"http://damnnnnn.natapp1.cc/images/yu.png"})
    }
    if(weather01=="大雨"){
      this.setData({image01:"http://damnnnnn.natapp1.cc/images/yu.png"})
    }
     if(weather01=="暴雨"){
      this.setData({image01:"http://damnnnnn.natapp1.cc/images/yu.png"})
    }


    if(weather02=="晴"){
      this.setData({image02:"http://damnnnnn.natapp1.cc/images/qing.png"})
    }
    if(weather02=="多云转晴"){
      this.setData({image02:"http://damnnnnn.natapp1.cc/images/duoyunzhuanqing.png"})
    }
    if(weather02=="晴转多云"){
      this.setData({image02:"http://damnnnnn.natapp1.cc/images/duoyunzhuanqing.png"})
    }
    if(weather02=="阴"){
      this.setData({image02:"http://damnnnnn.natapp1.cc/images/yin.png"})
    }
    if(weather02=="阴转多云"){
      this.setData({image02:"http://damnnnnn.natapp1.cc/images/duoyunzhuanyin.png"})
    }
    if(weather02=="多云转阴"){
      this.setData({image02:"http://damnnnnn.natapp1.cc/images/duoyunzhuanyin.png"})
    }
     if(weather02=="多云"){
      this.setData({image02:"http://damnnnnn.natapp1.cc/images/duoyun.png"})
    }
    if(weather02=="多云转小雨"){
      this.setData({image02:"http://damnnnnn.natapp1.cc/images/yu.png"})
    }
    if(weather02=="小雨"){
      this.setData({image02:"http://damnnnnn.natapp1.cc/images/yu.png"})
    }
     if(weather02=="中雨"){
      this.setData({image02:"http://damnnnnn.natapp1.cc/images/yu.png"})
    }
    if(weather02=="大雨"){
      this.setData({image02:"http://damnnnnn.natapp1.cc/images/yu.png"})
    }
     if(weather02=="暴雨"){
      this.setData({image02:"http://damnnnnn.natapp1.cc/images/yu.png"})
    }


    if(weather03=="晴"){
      this.setData({image03:"http://damnnnnn.natapp1.cc/images/qing.png"})
    }
    if(weather03=="多云转晴"){
      this.setData({image03:"http://damnnnnn.natapp1.cc/images/duoyunzhuanqing.png"})
    }
    if(weather03=="晴转多云"){
      this.setData({image03:"http://damnnnnn.natapp1.cc/images/duoyunzhuanqing.png"})
    }
    if(weather03=="阴"){
      this.setData({image03:"http://damnnnnn.natapp1.cc/images/yin.png"})
    }
    if(weather03=="阴转多云"){
      this.setData({image03:"http://damnnnnn.natapp1.cc/images/duoyunzhuanyin.png"})
    }
    if(weather03=="多云转阴"){
      this.setData({image03:"http://damnnnnn.natapp1.cc/images/duoyunzhuanyin.png"})
    }
     if(weather03=="多云"){
      this.setData({image03:"http://damnnnnn.natapp1.cc/images/duoyun.png"})
    }
    if(weather03=="多云转小雨"){
      this.setData({image03:"http://damnnnnn.natapp1.cc/images/yu.png"})
    }
    if(weather03=="小雨"){
      this.setData({image03:"http://damnnnnn.natapp1.cc/images/yu.png"})
    }
     if(weather03=="中雨"){
      this.setData({image03:"http://damnnnnn.natapp1.cc/images/yu.png"})
    }
    if(weather03=="大雨"){
      this.setData({image03:"http://damnnnnn.natapp1.cc/images/yu.png"})
    }
     if(weather03=="暴雨"){
      this.setData({image03:"http://damnnnnn.natapp1.cc/images/yu.png"})
    }


    if(weather04=="晴"){
      this.setData({image04:"http://damnnnnn.natapp1.cc/images/qing.png"})
    }
    if(weather04=="多云转晴"){
      this.setData({image04:"http://damnnnnn.natapp1.cc/images/duoyunzhuanqing.png"})
    }
    if(weather04=="晴转多云"){
      this.setData({image04:"http://damnnnnn.natapp1.cc/images/duoyunzhuanqing.png"})
    }
    if(weather04=="阴"){
      this.setData({image04:"http://damnnnnn.natapp1.cc/images/yin.png"})
    }
    if(weather04=="阴转多云"){
      this.setData({image04:"http://damnnnnn.natapp1.cc/images/duoyunzhuanyin.png"})
    }
    if(weather04=="多云转阴"){
      this.setData({image04:"http://damnnnnn.natapp1.cc/images/duoyunzhuanyin.png"})
    }
     if(weather04=="多云"){
      this.setData({image04:"http://damnnnnn.natapp1.cc/images/duoyun.png"})
    }
    if(weather04=="多云转小雨"){
      this.setData({image04:"http://damnnnnn.natapp1.cc/images/yu.png"})
    }
    if(weather04=="小雨"){
      this.setData({image04:"http://damnnnnn.natapp1.cc/images/yu.png"})
    }
     if(weather04=="中雨"){
      this.setData({image04:"http://damnnnnn.natapp1.cc/images/yu.png"})
    }
    if(weather04=="大雨"){
      this.setData({image04:"http://damnnnnn.natapp1.cc/images/yu.png"})
    }
     if(weather04=="暴雨"){
      this.setData({image04:"http://damnnnnn.natapp1.cc/images/yu.png"})
    }


    if(weather05=="晴"){
      this.setData({image05:"http://damnnnnn.natapp1.cc/images/qing.png"})
    }
    if(weather05=="多云转晴"){
      this.setData({image05:"http://damnnnnn.natapp1.cc/images/duoyunzhuanqing.png"})
    }
    if(weather05=="晴转多云"){
      this.setData({image05:"http://damnnnnn.natapp1.cc/images/duoyunzhuanqing.png"})
    }
    if(weather05=="阴"){
      this.setData({image05:"http://damnnnnn.natapp1.cc/images/yin.png"})
    }
    if(weather05=="阴转多云"){
      this.setData({image05:"http://damnnnnn.natapp1.cc/images/duoyunzhuanyin.png"})
    }
    if(weather05=="多云转阴"){
      this.setData({image05:"http://damnnnnn.natapp1.cc/images/duoyunzhuanyin.png"})
    }
     if(weather05=="多云"){
      this.setData({image05:"http://damnnnnn.natapp1.cc/images/duoyun.png"})
    }
    if(weather05=="多云转小雨"){
      this.setData({image05:"http://damnnnnn.natapp1.cc/images/yu.png"})
    }
    if(weather05=="小雨"){
      this.setData({image05:"http://damnnnnn.natapp1.cc/images/yu.png"})
    }
     if(weather05=="中雨"){
      this.setData({image05:"http://damnnnnn.natapp1.cc/images/yu.png"})
    }
    if(weather05=="大雨"){
      this.setData({image05:"http://damnnnnn.natapp1.cc/images/yu.png"})
    }
     if(weather05=="暴雨"){
      this.setData({image05:"http://damnnnnn.natapp1.cc/images/yu.png"})
    }



    if(weather06=="晴"){
      this.setData({image06:"http://damnnnnn.natapp1.cc/images/qing.png"})
    }
    if(weather06=="多云转晴"){
      this.setData({image06:"http://damnnnnn.natapp1.cc/images/duoyunzhuanqing.png"})
    }
    if(weather06=="晴转多云"){
      this.setData({image06:"http://damnnnnn.natapp1.cc/images/duoyunzhuanqing.png"})
    }
    if(weather06=="阴"){
      this.setData({image06:"http://damnnnnn.natapp1.cc/images/yin.png"})
    }
    if(weather06=="阴转多云"){
      this.setData({image06:"http://damnnnnn.natapp1.cc/images/duoyunzhuanyin.png"})
    }
    if(weather06=="多云转阴"){
      this.setData({image06:"http://damnnnnn.natapp1.cc/images/duoyunzhuanyin.png"})
    }
     if(weather06=="多云"){
      this.setData({image06:"http://damnnnnn.natapp1.cc/images/duoyun.png"})
    }
    if(weather06=="多云转小雨"){
      this.setData({image06:"http://damnnnnn.natapp1.cc/images/yu.png"})
    }
    if(weather06=="小雨"){
      this.setData({image06:"http://damnnnnn.natapp1.cc/images/yu.png"})
    }
     if(weather06=="中雨"){
      this.setData({image06:"http://damnnnnn.natapp1.cc/images/yu.png"})
    }
    if(weather06=="大雨"){
      this.setData({image06:"http://damnnnnn.natapp1.cc/images/yu.png"})
    }
     if(weather06=="暴雨"){
      this.setData({image06:"http://damnnnnn.natapp1.cc/images/yu.png"})
    }



    if(weather07=="晴"){
      this.setData({image07:"http://damnnnnn.natapp1.cc/images/qing.png"})
    }
    if(weather07=="多云转晴"){
      this.setData({image07:"http://damnnnnn.natapp1.cc/images/duoyunzhuanqing.png"})
    }
    if(weather07=="晴转多云"){
      this.setData({image07:"http://damnnnnn.natapp1.cc/images/duoyunzhuanqing.png"})
    }
    if(weather07=="阴"){
      this.setData({image07:"http://damnnnnn.natapp1.cc/images/yin.png"})
    }
    if(weather07=="阴转多云"){
      this.setData({image07:"http://damnnnnn.natapp1.cc/images/duoyunzhuanyin.png"})
    }
    if(weather07=="多云转阴"){
      this.setData({image07:"http://damnnnnn.natapp1.cc/images/duoyunzhuanyin.png"})
    }
     if(weather07=="多云"){
      this.setData({image07:"http://damnnnnn.natapp1.cc/images/duoyun.png"})
    }
    if(weather07=="多云转小雨"){
      this.setData({image07:"http://damnnnnn.natapp1.cc/images/yu.png"})
    }
    if(weather07=="小雨"){
      this.setData({image07:"http://damnnnnn.natapp1.cc/images/yu.png"})
    }
     if(weather07=="中雨"){
      this.setData({image07:"http://damnnnnn.natapp1.cc/images/yu.png"})
    }
    if(weather07=="大雨"){
      this.setData({image07:"http://damnnnnn.natapp1.cc/images/yu.png"})
    }
     if(weather07=="暴雨"){
      this.setData({image07:"http://damnnnnn.natapp1.cc/images/yu.png"})
    }
  },
  onLoad() {
    
  },
});
