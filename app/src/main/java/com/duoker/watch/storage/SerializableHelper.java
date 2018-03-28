package com.duoker.watch.storage;

import android.content.Context;

/**
 * Created by chengang on 4/25/2017.
 */

public class SerializableHelper
{
    private static final String DEFAULT_WATCH_KEY = "DEFAULT_WATCH_KEY";
    private static final String LOGIN_USER_KEY = "LOGIN_USER_KEY";
    private static final String WATCHES_KEY = "WATCHES_KEY";
    private final Context mContext;

    public SerializableHelper(Context paramContext)
    {
        this.mContext = paramContext.getApplicationContext();
    }

    public WatchsStorage getDefaultWatch()
    {
        Object obj = SerializableUtils.getObject(this.mContext, "DEFAULT_WATCH_KEY");
        if ((obj instanceof WatchsStorage))
            return (WatchsStorage)obj;
        return null;
    }

    public LoginUserStorage getLoginUser()
    {
        Object obj = SerializableUtils.getObject(this.mContext, "LOGIN_USER_KEY");
        if ((obj instanceof LoginUserStorage))
            return (LoginUserStorage)obj;
        return null;
    }

    public WatchsStorage[] getWatches()
    {
        Object obj = SerializableUtils.getObject(this.mContext, "WATCHES_KEY");
        if ((obj instanceof WatchsStorage[]))
            return (WatchsStorage[])obj;
        return null;
    }

    public void saveDefaultWatch(WatchsStorage watch)
    {
        SerializableUtils.saveObject(this.mContext, "DEFAULT_WATCH_KEY", watch);
    }

    public void saveLoginUser(LoginUserStorage loginUser)
    {
        SerializableUtils.saveObject(this.mContext, "LOGIN_USER_KEY", loginUser);
    }

    public void saveWatches( WatchsStorage[] watchesArray )
    {
        SerializableUtils.saveObject(this.mContext, "WATCHES_KEY", watchesArray);
    }
}
