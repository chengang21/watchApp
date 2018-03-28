package com.duoker.watch.storage;

/**
 * Created by cheng on 2017/10/6.
 */

import android.content.Context;
import android.content.SharedPreferences;

import com.duoker.watch.model.UserSetupInfo;

public class UserSetupInfoHelper {
    private static final String AUDIO = "audio";
    public static final String DEFAULT_WATCHID = "default_watchId";
    public static final String IS_DEL_OUT_TIME_SCHEDULE_KEY = "deleteouttimesch";
    private static final String MAP_TYPE = "map_type";
    private static final String NOTIFICATION_MSG = "notificationmsg";
    private static final String PRE_LATITUDE = "preLatitude";
    private static final String PRE_LONGITUDE = "preLongitude";
    private static final String VIBRATION = "vibration";
    private final String SP_NAME = "user_perference_setup";
    private final Context mContext;
    private final SharedPreferences mSharedPreferences;

    public UserSetupInfoHelper(Context paramContext) {
        this.mContext = paramContext.getApplicationContext();
        this.mSharedPreferences = paramContext.getSharedPreferences("user_perference_setup", 0);
    }

    public void clearSetupParam() {
        SharedPreferences.Editor localEditor = this.mSharedPreferences.edit();
        localEditor.clear();
        localEditor.apply();
    }

    public String getDefaultWatchId(String paramString) {
        return this.mSharedPreferences.getString(paramString + "default_watchId", "");
    }

    public UserSetupInfo.MapTypeEnum getMapType(String paramString) {
        int i = this.mSharedPreferences.getInt(paramString + "map_type", 3);
        if (i == 1)
            return UserSetupInfo.MapTypeEnum.BAIDU;
        if (i == 2)
            return UserSetupInfo.MapTypeEnum.GOOGLE;
        if (i == 3)
            return UserSetupInfo.MapTypeEnum.AMAP;
        return UserSetupInfo.MapTypeEnum.AMAP;
    }

    public String[] getPreLocation(String paramString) {
        return new String[]{this.mSharedPreferences.getString(paramString + "preLatitude", ""), this.mSharedPreferences.getString(paramString + "preLongitude", "")};
    }

    public UserSetupInfo getSetupInfo(String paramString) {
        UserSetupInfo setupInfo = new UserSetupInfo();
        setupInfo.setNotificationmsg(this.mSharedPreferences.getBoolean(paramString + "notificationmsg", false));
        setupInfo.setAudio(this.mSharedPreferences.getBoolean(paramString + "audio", false));
        setupInfo.setVibration(this.mSharedPreferences.getBoolean(paramString + "vibration", false));
        int mapType = this.mSharedPreferences.getInt(paramString + "map_type", 3);

        if (mapType == 1)
            setupInfo.setMapType(UserSetupInfo.MapTypeEnum.BAIDU);
        else if (mapType == 2)
            setupInfo.setMapType(UserSetupInfo.MapTypeEnum.GOOGLE);
        else if (mapType == 3)
            setupInfo.setMapType(UserSetupInfo.MapTypeEnum.AMAP);

        setupInfo.setDefaultWatchId(this.mSharedPreferences.getString(paramString + "default_watchId", ""));
        return setupInfo;
    }

    public boolean isDelOutTimeSchedule(String paramString) {
        return this.mSharedPreferences.getBoolean(paramString + "deleteouttimesch", false);
    }

    public void saveDefaultWatchId(String paramString1, String paramString2) {
        SharedPreferences.Editor localEditor = this.mSharedPreferences.edit();
        localEditor.putString(paramString1 + "default_watchId", paramString2);
        localEditor.apply();
    }

    public void saveIsDelOutTimeSchedule(String paramString, boolean paramBoolean) {
        SharedPreferences.Editor localEditor = this.mSharedPreferences.edit();
        localEditor.putBoolean(paramString + "deleteouttimesch", paramBoolean);
        localEditor.apply();
    }

    public void saveMapType(String paramString, UserSetupInfo.MapTypeEnum paramMapTypeEnum) {
        SharedPreferences.Editor localEditor = this.mSharedPreferences.edit();
        switch (paramMapTypeEnum)
        {
            case BAIDU:
                localEditor.putInt(paramString + "map_type", 1);
                break;
            case AMAP:
                localEditor.putInt(paramString + "map_type", 2);
                break;
            case GOOGLE:
                localEditor.putInt(paramString + "map_type", 3);
                break;
            default:
        }
        localEditor.apply();
    }

    public void savePreLocation(String paramString1, String paramString2, String paramString3) {
        SharedPreferences.Editor localEditor = this.mSharedPreferences.edit();
        localEditor.putString(paramString1 + "preLatitude", paramString2);
        localEditor.putString(paramString1 + "preLongitude", paramString3);
        localEditor.apply();
    }

    public void setSetupParam(String paramString, UserSetupInfo setupInfo) {
        if (setupInfo == null)
            return;

        SharedPreferences.Editor editor = this.mSharedPreferences.edit();
        editor.clear();
        editor.putBoolean(paramString + "notificationmsg", setupInfo.isNotificationmsg());
        editor.putBoolean(paramString + "audio", setupInfo.isAudio());
        editor.putBoolean(paramString + "vibration", setupInfo.isVibration());
        UserSetupInfo.MapTypeEnum localMapTypeEnum = setupInfo.getMapType();
        switch (localMapTypeEnum)
        {
            case BAIDU:
                editor.putInt(paramString + "map_type", 1);
                break;
            case AMAP:
                editor.putInt(paramString + "map_type", 2);
                break;
            case GOOGLE:
                editor.putInt(paramString + "map_type", 3);
                break;
            default:
        }

        editor.putString(paramString + "preLatitude", setupInfo.getPreLatitude());
        editor.putString(paramString + "preLongitude", setupInfo.getPreLongitude());
        editor.putBoolean(paramString + "deleteouttimesch", setupInfo.isDelOutTimeSchedule());
        editor.putString(paramString + "default_watchId", setupInfo.getDefaultWatchId());
        editor.apply();
    }
}
