package com.duoker.watch.repository;

/**
 * Created by cheng on 2017/10/9.
 */


import android.content.Context;

import com.duoker.watch.model.UserSetupInfo;
import com.duoker.watch.storage.UserSetupInfoHelper;

public class UserSetupInfoRepositoryImpl implements UserSetupInfoRepository
{
    private final Context mContext;
    private final UserSetupInfoHelper mUserSetupInfoHelper;

    public UserSetupInfoRepositoryImpl(Context paramContext)
    {
        this.mContext = paramContext;
        this.mUserSetupInfoHelper = new UserSetupInfoHelper(paramContext);
    }

    public UserSetupInfo get(String paramString)
    {
        return this.mUserSetupInfoHelper.getSetupInfo(paramString);
    }

    public UserSetupInfo.MapTypeEnum getMapType(String paramString)
    {
        return this.mUserSetupInfoHelper.getMapType(paramString);
    }

    public void setMapType(String paramString, UserSetupInfo.MapTypeEnum paramMapTypeEnum)
    {
        this.mUserSetupInfoHelper.saveMapType(paramString, paramMapTypeEnum);
    }

    public void update(String paramString, UserSetupInfo paramUserSetupInfo)
    {
        this.mUserSetupInfoHelper.setSetupParam(paramString, paramUserSetupInfo);
    }
}