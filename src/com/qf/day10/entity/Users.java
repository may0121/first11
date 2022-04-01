package com.qf.day10.entity;
//用户实体类
public class Users {
    private  String uname;
    private  String upassword;
    private  Integer uage;
    private  String usex;
    private  String uhobby;
    private  String ucity;
    private  String ubirthday;
    private Integer uid;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public Integer getUage() {
        return uage;
    }

    public void setUage(Integer uage) {
        this.uage = uage;
    }

    public String getUsex() {
        return usex;
    }

    public void setUsex(String usex) {
        this.usex = usex;
    }

    public String getUhobby() {
        return uhobby;
    }

    public void setUhobby(String uhobby) {
        this.uhobby = uhobby;
    }

    public String getUcity() {
        return ucity;
    }

    public void setUcity(String ucity) {
        this.ucity = ucity;
    }

    public String getUbirthday() {
        return ubirthday;
    }

    public void setUbirthday(String ubirthday) {
        this.ubirthday = ubirthday;
    }

    @Override
    public String toString() {
        return "Users{" +
                "uname='" + uname + '\'' +
                ", upassword='" + upassword + '\'' +
                ", uage=" + uage +
                ", usex='" + usex + '\'' +
                ", uhobby='" + uhobby + '\'' +
                ", ucity='" + ucity + '\'' +
                ", ubirthday='" + ubirthday + '\'' +
                ", uid=" + uid +
                '}';
    }
}

