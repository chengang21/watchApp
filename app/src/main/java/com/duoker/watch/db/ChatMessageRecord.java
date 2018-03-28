package com.duoker.watch.db;

/**
 * Created by chengang on 4/24/2017.
 */

import android.text.TextUtils;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.io.Serializable;

@Table(name = "ChatMessage")
public class ChatMessageRecord
        implements Serializable {

    @Column(autoGen = true, isId = true, name = "_id")
    private long _id;

    @Column(name = "content")
    private String content;

    @Column(name = "ctime")
    private long ctime;

    @Column(name = "devtype")
    private int devtype;

    @Column(name = "fappver")
    private int fappver;

    @Column(name = "fromuid")
    private String fromuid;

    @Column(name = "groupId")
    private long groupId;

    @Column(name = "headurl")
    private String headurl;

    @Column(name = "loginUserName")
    private String loginUserName;
    private long msgId;

    @Column(name = "mtype")
    private int mtype;

    @Column(name = "naudiotime")
    private int naudiotime;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "readFlag")
    private int readFlag;

    @Column(name = "srcurl")
    private String srcurl;

    @Column(name = "type")
    private int type;

    @Column(name = "watchId")
    private String watchId;

    public boolean equals(Object paramObject) {
        if ((paramObject instanceof ChatMessageRecord)) {
            ChatMessageRecord localChatMessageStorage = (ChatMessageRecord) paramObject;
            if ((TextUtils.equals(getLoginUserName(), localChatMessageStorage.getLoginUserName())) && (get_id() == localChatMessageStorage.get_id()))
                return true;
        }
        return false;
    }

    public String getContent() {
        return this.content;
    }

    public long getCtime() {
        return this.ctime;
    }

    public int getDevtype() {
        return this.devtype;
    }

    public int getFappver() {
        return this.fappver;
    }

    public String getFromuid() {
        return this.fromuid;
    }

    public long getGroupId() {
        return this.groupId;
    }

    public String getHeadurl() {
        return this.headurl;
    }

    public String getLoginUserName() {
        return this.loginUserName;
    }

    public long getMsgId() {
        return this.msgId;
    }

    public int getMtype() {
        return this.mtype;
    }

    public int getNaudiotime() {
        return this.naudiotime;
    }

    public String getNickname() {
        return this.nickname;
    }

    public int getReadFlag() {
        return this.readFlag;
    }

    public String getSrcurl() {
        return this.srcurl;
    }

    public int getType() {
        return this.type;
    }

    public String getWatchId() {
        return this.watchId;
    }

    public long get_id() {
        return this._id;
    }

    public int hashCode() {
        return new StringBuilder(getLoginUserName()).append(get_id()).hashCode();
    }

    public void setContent(String paramString) {
        this.content = paramString;
    }

    public void setCtime(long paramLong) {
        this.ctime = paramLong;
    }

    public void setDevtype(int paramInt) {
        this.devtype = paramInt;
    }

    public void setFappver(int paramInt) {
        this.fappver = paramInt;
    }

    public void setFromuid(String paramString) {
        this.fromuid = paramString;
    }

    public void setGroupId(long paramLong) {
        this.groupId = paramLong;
    }

    public void setHeadurl(String paramString) {
        this.headurl = paramString;
    }

    public void setLoginUserName(String paramString) {
        this.loginUserName = paramString;
    }

    public void setMsgId(long paramLong) {
        this.msgId = paramLong;
    }

    public void setMtype(int paramInt) {
        this.mtype = paramInt;
    }

    public void setNaudiotime(int paramInt) {
        this.naudiotime = paramInt;
    }

    public void setNickname(String paramString) {
        this.nickname = paramString;
    }

    public void setReadFlag(int paramInt) {
        this.readFlag = paramInt;
    }

    public void setSrcurl(String paramString) {
        this.srcurl = paramString;
    }

    public void setType(int paramInt) {
        this.type = paramInt;
    }

    public void setWatchId(String paramString) {
        this.watchId = paramString;
    }

    public void set_id(long paramLong) {
        this._id = paramLong;
    }
}