package com.duoker.watch.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.duoker.watch.model.SetupInfo;
import com.duoker.watch.network.L25Constants;

/**
 * Created by cheng on 2017/8/31.
 */

public class SetupInfoHelper {
    private static final String AUTO_LOGIN = "autoLogin";
    private static final String FIRST_RUN = "firstRun";
    private static final String LOGIN_USER_NAME = "userName";
    private static final String PASSWORD = "password";
    private static final String REMBER_PASSWORD = "remberPassword";
    private static final String SERVER_ADDR = "server_addr";
    private final String SP_NAME = "perference_setup";
    private final Context mContext;
    private final SharedPreferences mSharedPreferences;

    public SetupInfoHelper(Context ctx)
    {
        this.mContext = ctx.getApplicationContext();
        this.mSharedPreferences = ctx.getSharedPreferences("perference_setup", 0);
    }

    public void clearSetupParam()
    {
        SharedPreferences.Editor localEditor = this.mSharedPreferences.edit();
        localEditor.clear();
        localEditor.apply();
    }

    public String getServerAddr()
    {
        return this.mSharedPreferences.getString("server_addr", L25Constants.SERVER_ADDRESS);
    }

    public SetupInfo getSetupInfo()
    {
        SetupInfo setupInfo = new SetupInfo();
        setupInfo.setFirstRun(this.mSharedPreferences.getBoolean("firstRun", true));
        setupInfo.setLoginUserName(this.mSharedPreferences.getString("userName", ""));
        setupInfo.setPassword(this.mSharedPreferences.getString("password", ""));
        setupInfo.setRemberPassword(this.mSharedPreferences.getBoolean("remberPassword", false));
        setupInfo.setAutoLogin(this.mSharedPreferences.getBoolean("autoLogin", false));
        setupInfo.setServerAddr(this.mSharedPreferences.getString("server_addr", L25Constants.SERVER_ADDRESS));
        return setupInfo;
    }

    public void saveFirstRun(boolean paramBoolean)
    {
        SharedPreferences.Editor localEditor = this.mSharedPreferences.edit();
        localEditor.putBoolean("firstRun", paramBoolean);
        localEditor.apply();
    }

    public void saveLoginUserName(String paramString)
    {
        SharedPreferences.Editor localEditor = this.mSharedPreferences.edit();
        localEditor.putString("userName", paramString);
        localEditor.apply();
    }

    public void savePassword(String paramString)
    {
        SharedPreferences.Editor localEditor = this.mSharedPreferences.edit();
        localEditor.putString("password", paramString);
        localEditor.apply();
    }

    public void saveServerAddr(String paramString)
    {
        L25Constants.SERVER_ADDRESS = paramString;
        SharedPreferences.Editor localEditor = this.mSharedPreferences.edit();
        localEditor.putString("server_addr", paramString);
        localEditor.apply();
    }

    public void setSetupParam(SetupInfo paramSetupInfo)
    {
        if (paramSetupInfo == null)
            return;
        SharedPreferences.Editor editor = this.mSharedPreferences.edit();
        editor.clear();
        editor.putBoolean("firstRun", paramSetupInfo.isFirstRun());
        editor.putString("userName", paramSetupInfo.getLoginUserName());
        editor.putString("password", paramSetupInfo.getPassword());
        editor.putBoolean("remberPassword", paramSetupInfo.isRemberPassword());
        editor.putBoolean("autoLogin", paramSetupInfo.isAutoLogin());
        editor.putString("server_addr", paramSetupInfo.getServerAddr());
        editor.apply();
    }
}
