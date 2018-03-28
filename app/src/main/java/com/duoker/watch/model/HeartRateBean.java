package com.duoker.watch.model;

/**
 * Created by cheng on 2017/10/8.
 */

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class HeartRateBean implements Serializable, Parcelable {

    public static final Parcelable.Creator<HeartRateBean> CREATOR = new MyCreator() ;
    private int heartRate = 0;
    private long calcTime = 0L;

    public HeartRateBean() {
    }

    protected HeartRateBean(Parcel parcel) {
        this.calcTime = parcel.readLong();
        this.heartRate = parcel.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel paramParcel, int paramInt) {
        paramParcel.writeLong(this.calcTime);
        paramParcel.writeInt(this.heartRate);
    }

    public long getCalcTime() {
        return calcTime;
    }

    public void setCalcTime(long calcTime) {
        this.calcTime = calcTime;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }
}

class MyCreator implements Parcelable.Creator<HeartRateBean>
{
    public HeartRateBean createFromParcel(Parcel paramParcel)
    {
        return new HeartRateBean(paramParcel);
    }

    public HeartRateBean[] newArray(int paramInt)
    {
        return new HeartRateBean[paramInt];
    }
}