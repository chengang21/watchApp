package com.duoker.watch.storage;

/**
 * Created by chengang on 4/25/2017.
 */

import java.io.Serializable;

public class WatchsStorage implements Serializable {
    private String addr;
    private String altitude;
    private String calcTime;
    private String cellID;
    private String devdistance;
    private String devname;
    private String devsex;
    private String directionOffSet;
    private String directionSpeed;
    private String electricity;
    private String gpsQuality;
    private long groupId;
    private String headImage;
    private String lac;
    private String latitude;
    private String longitude;
    private String mcc;
    private String mnc;
    private int online;
    private String owner;
    private String phoneIMS;
    private String projectCate;
    private String rss;
    private String watchId;

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public String getCalcTime() {
        return calcTime;
    }

    public void setCalcTime(String calcTime) {
        this.calcTime = calcTime;
    }

    public String getCellID() {
        return cellID;
    }

    public void setCellID(String cellID) {
        this.cellID = cellID;
    }

    public String getDevdistance() {
        return devdistance;
    }

    public void setDevdistance(String devdistance) {
        this.devdistance = devdistance;
    }

    public String getDevname() {
        return devname;
    }

    public void setDevname(String devname) {
        this.devname = devname;
    }

    public String getDevsex() {
        return devsex;
    }

    public void setDevsex(String devsex) {
        this.devsex = devsex;
    }

    public String getDirectionOffSet() {
        return directionOffSet;
    }

    public void setDirectionOffSet(String directionOffSet) {
        this.directionOffSet = directionOffSet;
    }

    public String getDirectionSpeed() {
        return directionSpeed;
    }

    public void setDirectionSpeed(String directionSpeed) {
        this.directionSpeed = directionSpeed;
    }

    public String getElectricity() {
        return electricity;
    }

    public void setElectricity(String electricity) {
        this.electricity = electricity;
    }

    public String getGpsQuality() {
        return gpsQuality;
    }

    public void setGpsQuality(String gpsQuality) {
        this.gpsQuality = gpsQuality;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getLac() {
        return lac;
    }

    public void setLac(String lac) {
        this.lac = lac;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public String getMnc() {
        return mnc;
    }

    public void setMnc(String mnc) {
        this.mnc = mnc;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getPhoneIMS() {
        return phoneIMS;
    }

    public void setPhoneIMS(String phoneIMS) {
        this.phoneIMS = phoneIMS;
    }

    public String getProjectCate() {
        return projectCate;
    }

    public void setProjectCate(String projectCate) {
        this.projectCate = projectCate;
    }

    public String getRss() {
        return rss;
    }

    public void setRss(String rss) {
        this.rss = rss;
    }

    public String getWatchId() {
        return watchId;
    }

    public void setWatchId(String watchId) {
        this.watchId = watchId;
    }
}