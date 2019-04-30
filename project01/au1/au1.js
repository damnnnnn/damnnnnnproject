// pages/au1/au1.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    music:""
  },
  clickone(e){
    console.log(e.currentTarget.dataset);
    let name = e.currentTarget.dataset.name;
    let url = e.currentTarget.dataset.url;
    let img = e.currentTarget.dataset.img;
    let author = e.currentTarget.dataset.author;
     
     wx.navigateTo({
       url: '../au2/au2?url='+url+'&name='+name+'&img='+img+'&author='+author,
     })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let that = this;
    wx.request({
      url: 'http://damnnnnn.natapp1.cc/text/music.json',
      success : function (res){
        console.log(res.data);
        let music = res.data;
        that.setData({ music: music});
      }
    })
    
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})