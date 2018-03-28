package com.duoker.watch.model;

/**
 * Created by cheng on 2017/10/8.
 */
import java.io.Serializable;

public class ProjectCateStorage
        implements Serializable
{
    private boolean bloodOxygen;
    private boolean chat;
    private boolean checkPhoneBill;
    private boolean classStop;
    private boolean fence;
    private boolean heartRate;
    private boolean oneDirectionCall;
    private boolean powerSave;
    private String projectCate;
    private boolean sleep;
    private boolean stepCounter;
    private boolean wifiSetting;

    public ProjectCateStorage()
    {
    }

    public ProjectCateStorage(String projectCate)
    {
        this.projectCate = projectCate;
        this.chat = true;
        this.stepCounter = true;
        this.fence = true;
        this.checkPhoneBill = true;
        this.classStop = true;
        this.oneDirectionCall = true;
        this.heartRate = true;
        this.sleep = true;
        this.bloodOxygen = true;
        this.wifiSetting = true;
        this.powerSave = true;
    }

    public boolean isBloodOxygen() {
        return bloodOxygen;
    }

    public void setBloodOxygen(boolean bloodOxygen) {
        this.bloodOxygen = bloodOxygen;
    }

    public boolean isChat() {
        return chat;
    }

    public void setChat(boolean chat) {
        this.chat = chat;
    }

    public boolean isCheckPhoneBill() {
        return checkPhoneBill;
    }

    public void setCheckPhoneBill(boolean checkPhoneBill) {
        this.checkPhoneBill = checkPhoneBill;
    }

    public boolean isClassStop() {
        return classStop;
    }

    public void setClassStop(boolean classStop) {
        this.classStop = classStop;
    }

    public boolean isFence() {
        return fence;
    }

    public void setFence(boolean fence) {
        this.fence = fence;
    }

    public boolean isHeartRate() {
        return heartRate;
    }

    public void setHeartRate(boolean heartRate) {
        this.heartRate = heartRate;
    }

    public boolean isOneDirectionCall() {
        return oneDirectionCall;
    }

    public void setOneDirectionCall(boolean oneDirectionCall) {
        this.oneDirectionCall = oneDirectionCall;
    }

    public boolean isPowerSave() {
        return powerSave;
    }

    public void setPowerSave(boolean powerSave) {
        this.powerSave = powerSave;
    }

    public String getProjectCate() {
        return projectCate;
    }

    public void setProjectCate(String projectCate) {
        this.projectCate = projectCate;
    }

    public boolean isSleep() {
        return sleep;
    }

    public void setSleep(boolean sleep) {
        this.sleep = sleep;
    }

    public boolean isStepCounter() {
        return stepCounter;
    }

    public void setStepCounter(boolean stepCounter) {
        this.stepCounter = stepCounter;
    }

    public boolean isWifiSetting() {
        return wifiSetting;
    }

    public void setWifiSetting(boolean wifiSetting) {
        this.wifiSetting = wifiSetting;
    }
}
