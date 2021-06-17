package com.example.covid_19application.ui.login;

public class User {
    public String id;
    public String pwd;
    public String name;
    public String address;
    public String tel;
    public String regDate;

    public User(){}

    public User(String id, String pwd, String name, String address, String tel, String regDate) {
        this.id = id;
        this.pwd = pwd;
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.regDate = regDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }
}