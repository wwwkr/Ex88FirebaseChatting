package com.rtw181204.ex88firebasechatting;

public class MessageItem {

    String name;
    String message;
    String time;
    String profileUrl;
    String uid;



    public MessageItem(String name, String message, String time, String profileUrl, String uid) {
        this.name = name;
        this.message = message;
        this.time = time;
        this.profileUrl = profileUrl;
        this.uid = uid;


    }

    //firebase DB에 저장하려면 빈 생성자가 있어야 함
    public MessageItem() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
