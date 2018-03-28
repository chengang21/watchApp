package com.duoker.watch.storage;

import java.io.Serializable;

/**
 * Created by chengang on 4/25/2017.
 */
public class LoginUserStorage implements Serializable {
    private String birthYear;
    private String headImage;
    private String loginUserName = "";
    private String password = "";
    private String session;
    private String userName;
    private String userSex;
    private String userid;

    public String getBirthYear() {
        return birthYear;
    }

    public String getHeadImage() {
        return headImage;
    }

    public String getLoginUserName() {
        return loginUserName;
    }

    public String getPassword() {
        return password;
    }

    public String getSession() {
        return session;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserSex() {
        return userSex;
    }

    public String getUserid() {
        return userid;
    }

    public void setBirthYear(String paramString) {
        birthYear = paramString;
    }

    public void setHeadImage(String paramString) {
        headImage = paramString;
    }

    public void setLoginUserName(String paramString) {
        loginUserName = paramString;
    }

    public void setPassword(String paramString) {
        password = paramString;
    }

    public void setSession(String paramString) {
        session = paramString;
    }

    public void setUserName(String paramString) {
        userName = paramString;
    }

    public void setUserSex(String paramString) {
        userSex = paramString;
    }

    public void setUserid(String paramString) {
        userid = paramString;
    }
}
