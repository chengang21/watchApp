package com.duoker.watch.model;

import java.io.Serializable;

public class GpsDataBean implements Serializable
{
  private String addr;
  private String altitude;
  private long  calcTime;
  private String cellID;
  private String directionOffSet;
  private String directionSpeed;
  private String electricity;
  private String gpsQuality;
  private String lac;
  private String latitude;
  private String longitude;
  private String mcc;
  private String mnc;
  private String rs;

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

  public long getCalcTime() {
    return calcTime;
  }

  public void setCalcTime(long calcTime) {
    this.calcTime = calcTime;
  }

  public String getCellID() {
    return cellID;
  }

  public void setCellID(String cellID) {
    this.cellID = cellID;
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

  public String getRs() {
    return rs;
  }

  public void setRs(String rs) {
    this.rs = rs;
  }
}
