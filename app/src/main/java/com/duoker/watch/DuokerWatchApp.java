package com.duoker.watch;

/**
 * Created by chengang on 4/25/2017.
 */

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;

import com.duoker.watch.storage.LoginUserStorage;
import com.duoker.watch.storage.SerializableHelper;
import com.duoker.watch.storage.WatchsStorage;

import java.util.Arrays;
import java.util.List;

// import cn.jpush.android.api.JPushInterface;

public class DuokerWatchApp extends Application
{
    private static DuokerWatchApp mInstance = null;
    private int mAppCount = 0;
    private WatchsStorage mDefaultWatchsStorage = null;
    private LoginUserStorage mLoginUserStorage = null;
    private WatchsStorage[] mWatchsStorageArray = null;

    public static DuokerWatchApp getInstance()
    {
        return mInstance;
    }

    public static String getProcessName(Context paramContext, int paramInt)
    {
        List activitylist = ((ActivityManager)paramContext.getSystemService( Context.ACTIVITY_SERVICE)).getRunningAppProcesses();
//        if (activitylist != null);
//        {
//            Iterator localIterator = activitylist.iterator();
//
//            while (!localIterator.hasNext())
//            {
//                localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)localIterator.next();
//                ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo;
//                return null;
//            } while ( localRunningAppProcessInfo.pid != paramInt )
//        }
//
        return ""; // localRunningAppProcessInfo.processName;
    }

    private void initApp()
    {
//        SpeechUtility.createUtility(this, "appid=55fc2890");
    }

    public WatchsStorage getDefaultWatch()
    {
        if (this.mDefaultWatchsStorage == null)
            this.mDefaultWatchsStorage = new SerializableHelper(getApplicationContext()).getDefaultWatch();
        return this.mDefaultWatchsStorage;
    }

    public String getDefaultWatchId(String paramString)
    {
        // return new UserSetupInfoHelper(getApplicationContext()).getDefaultWatchId(paramString);
        return "";
    }

    public LoginUserStorage getLoginUser()
    {
        if (this.mLoginUserStorage == null)
            this.mLoginUserStorage = new SerializableHelper(getApplicationContext()).getLoginUser();
        return this.mLoginUserStorage;
    }

    public WatchsStorage[] getWatches()
    {
        if (this.mWatchsStorageArray == null)
            this.mWatchsStorageArray = new SerializableHelper(getApplicationContext()).getWatches();
        return this.mWatchsStorageArray;
    }

    public List<WatchsStorage> getWatchesList()
    {
        WatchsStorage[] arrayOfWatchsStorage = getWatches();
        if (arrayOfWatchsStorage == null)
            return null;
        return Arrays.asList(arrayOfWatchsStorage);
    }

    public boolean isForeground()
    {
        return this.mAppCount > 0;
    }

    public void onCreate()
    {
        super.onCreate();
        mInstance = this;
        if (TextUtils.equals(getProcessName(this, Process.myPid()), getPackageName()))
            initApp();

        registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks()
        {
            public void onActivityCreated(Activity paramAnonymousActivity, Bundle paramAnonymousBundle)
            {
            }

            public void onActivityDestroyed(Activity paramAnonymousActivity)
            {
            }

            public void onActivityPaused(Activity paramAnonymousActivity)
            {
            }

            public void onActivityResumed(Activity paramAnonymousActivity)
            {
            }

            public void onActivitySaveInstanceState(Activity paramAnonymousActivity, Bundle paramAnonymousBundle)
            {
            }

            public void onActivityStarted(Activity paramAnonymousActivity)
            {
            }

            public void onActivityStopped(Activity paramAnonymousActivity)
            {
            }
        });
    }

    public void setDefaultWatch(WatchsStorage paramWatchsStorage)
    {
        this.mDefaultWatchsStorage = paramWatchsStorage;
        new SerializableHelper(getApplicationContext()).saveDefaultWatch(paramWatchsStorage);
    }

    public void setDefaultWatchId(String userid, String watchid)
    {
        // new UserSetupInfoHelper(getApplicationContext()).saveDefaultWatchId(paramString1, paramString2);
    }

    public void setLoginUser(LoginUserStorage loginUser)
    {
        this.mLoginUserStorage = loginUser;
        new SerializableHelper(getApplicationContext()).saveLoginUser(loginUser);
    }

    public void setWatches(WatchsStorage[] watches)
    {
        this.mWatchsStorageArray = watches;
        new SerializableHelper(getApplicationContext()).saveWatches(watches);
    }
}