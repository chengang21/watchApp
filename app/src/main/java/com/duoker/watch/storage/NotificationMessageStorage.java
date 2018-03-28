package com.duoker.watch.storage;

/**
 * Created by cheng on 2017/10/9.
 */


import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;

@Table(name="NotificationMessage")
public class NotificationMessageStorage implements Serializable
{

    @Column(isId=true, name="_id")
    private long _id;

    @Column(name="content")
    private String content;

    @Column(name="ctime")
    private long ctime;

    @Column(name="deluid")
    private String deluid;

    @Column(name="devtype")
    private int devtype;

    @Column(name="fNickname")
    private String fNickname;

    @Column(name="fappver")
    private int fappver;

    @Column(name="fenceId")
    private long fenceId;

    @Column(name="fname")
    private String fname;

    @Column(name="fromuid")
    private String fromuid;

    @Column(name="groupId")
    private long groupId;

    @Column(name="headImage")
    private String headImage;

    @Column(name="headurl")
    private String headurl;

    @Column(name="loginUserName")
    private String loginUserName;

    @Column(name="msg")
    private String msg;

    @Column(name="msgId", property="UNIQUE")
    private long msgId;

    @Column(name="mtype")
    private int mtype;

    @Column(name="nNickname")
    private String nNickname;

    @Column(name="name")
    private String name;

    @Column(name="naudiotime")
    private int naudiotime;

    @Column(name="newuid")
    private String newuid;

    @Column(name="nickname")
    private String nickname;

    @Column(name="phone")
    private String phone;

    @Column(name="pwd")
    private String pwd;

    @Column(name="readFlag")
    private int readFlag;

    @Column(name="srcurl")
    private String srcurl;

    @Column(name="status")
    private int status;

    @Column(name="touid")
    private String touid;

    @Column(name="type")
    private int type;

    @Column(name="watchid")
    private String watchId;

    public String getContent()
    {
        return this.content;
    }

    public long getCtime()
    {
        return this.ctime;
    }

    public String getDeluid()
    {
        return this.deluid;
    }

    public int getDevtype()
    {
        return this.devtype;
    }

    public int getFappver()
    {
        return this.fappver;
    }

    public long getFenceId()
    {
        return this.fenceId;
    }

    public String getFname()
    {
        return this.fname;
    }

    public String getFromuid()
    {
        return this.fromuid;
    }

    public long getGroupId()
    {
        return this.groupId;
    }

    public String getHeadImage()
    {
        return this.headImage;
    }

    public String getHeadurl()
    {
        return this.headurl;
    }

    public String getLoginUserName()
    {
        return this.loginUserName;
    }

    public String getMsg()
    {
        return this.msg;
    }

    public long getMsgId()
    {
        return this.msgId;
    }

    public int getMtype()
    {
        return this.mtype;
    }

    public String getName()
    {
        return this.name;
    }

    public int getNaudiotime()
    {
        return this.naudiotime;
    }

    public String getNewuid()
    {
        return this.newuid;
    }

    public String getNickname()
    {
        return this.nickname;
    }

    public String getPhone()
    {
        return this.phone;
    }

    public String getPwd()
    {
        return this.pwd;
    }

    public int getReadFlag()
    {
        return this.readFlag;
    }

    public String getSrcurl()
    {
        return this.srcurl;
    }

    public int getStatus()
    {
        return this.status;
    }

    public String getTouid()
    {
        return this.touid;
    }

    public int getType()
    {
        return this.type;
    }

    public String getWatchId()
    {
        return this.watchId;
    }

    public long get_id()
    {
        return this._id;
    }

    public String getfNickname()
    {
        return this.fNickname;
    }

    public String getnNickname()
    {
        return this.nNickname;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    public void setDeluid(String deluid) {
        this.deluid = deluid;
    }

    public void setDevtype(int devtype) {
        this.devtype = devtype;
    }

    public void setfNickname(String fNickname) {
        this.fNickname = fNickname;
    }

    public void setFappver(int fappver) {
        this.fappver = fappver;
    }

    public void setFenceId(long fenceId) {
        this.fenceId = fenceId;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setFromuid(String fromuid) {
        this.fromuid = fromuid;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }

    public void setLoginUserName(String loginUserName) {
        this.loginUserName = loginUserName;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setMsgId(long msgId) {
        this.msgId = msgId;
    }

    public void setMtype(int mtype) {
        this.mtype = mtype;
    }

    public void setnNickname(String nNickname) {
        this.nNickname = nNickname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNaudiotime(int naudiotime) {
        this.naudiotime = naudiotime;
    }

    public void setNewuid(String newuid) {
        this.newuid = newuid;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setReadFlag(int readFlag) {
        this.readFlag = readFlag;
    }

    public void setSrcurl(String srcurl) {
        this.srcurl = srcurl;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTouid(String touid) {
        this.touid = touid;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setWatchId(String watchId) {
        this.watchId = watchId;
    }
}

