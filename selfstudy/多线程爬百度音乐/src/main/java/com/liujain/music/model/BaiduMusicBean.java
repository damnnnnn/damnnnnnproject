package com.liujain.music.model;

public class BaiduMusicBean {

    private String top;
    private String songName;
    private String IrcLink;
    private String songLink;
    private String songImg;
    private String author;
    private String list;
    private String songID;

    public String getSongID() {
        return songID;
    }

    public void setSongID(String songID) {
        this.songID = songID;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getIrcLink() {
        return IrcLink;
    }

    public void setIrcLink(String ircLink) {
        IrcLink = ircLink;
    }

    public String getSongLink() {
        return songLink;
    }

    public void setSongLink(String songLink) {
        this.songLink = songLink;
    }

    public String getSongImg() {
        return songImg;
    }

    public void setSongImg(String songImg) {
        this.songImg = songImg;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }
}
