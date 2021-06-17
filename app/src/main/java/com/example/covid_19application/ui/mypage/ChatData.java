package com.example.covid_19application.ui.mypage;

public class ChatData {
    private String nickname;
    private String msg;

    public ChatData(){}

    public ChatData(String nickname, String msg) {
        this.nickname = nickname;
        this.msg = msg;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}