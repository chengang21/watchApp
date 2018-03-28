package com.duoker.watch.db;

import java.io.Serializable;

/**
 * Created by chengang on 4/24/2017.
 */

public class LoginUserRecord implements Serializable {

    private String birthYear;
    private String headImage;
    private String loginUserName = "";
    private String password = "";
    private String session;
    private String userName;
    private String userSex;
    private String userid;

    public String getBirthYear() {
        return this.birthYear;
    }

    public String getHeadImage() {
        return this.headImage;
    }

    public String getLoginUserName() {
        return this.loginUserName;
    }

    public String getPassword() {
        return this.password;
    }

    public String getSession() {
        return this.session;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getUserSex() {
        return this.userSex;
    }

    public String getUserid() {
        return this.userid;
    }

    public void setBirthYear(String paramString) {
        this.birthYear = paramString;
    }

    public void setHeadImage(String paramString) {
        this.headImage = paramString;
    }

    public void setLoginUserName(String paramString) {
        this.loginUserName = paramString;
    }

    public void setPassword(String paramString) {
        this.password = paramString;
    }

    public void setSession(String paramString) {
        this.session = paramString;
    }

    public void setUserName(String paramString) {
        this.userName = paramString;
    }

    public void setUserSex(String paramString) {
        this.userSex = paramString;
    }

    public void setUserid(String paramString) {
        this.userid = paramString;
    }
}
