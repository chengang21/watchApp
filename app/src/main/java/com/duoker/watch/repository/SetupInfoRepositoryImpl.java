package com.duoker.watch.repository;

import android.content.Context;

import com.duoker.watch.model.SetupInfo;
import com.duoker.watch.storage.SetupInfoHelper;

/**
 * Created by cheng on 2017/9/3.
 */
public class SetupInfoRepositoryImpl implements SetupInfoRepository
{
    private final Context mContext;
    private final SetupInfoHelper mSetupInfoHelper;

    public SetupInfoRepositoryImpl(Context paramContext)
    {
        this.mContext = paramContext.getApplicationContext();
        this.mSetupInfoHelper = new SetupInfoHelper(this.mContext);
    }

    public SetupInfo get()
    {
        return this.mSetupInfoHelper.getSetupInfo();
    }

    public void saveFirstRun(boolean isFirstRun)
    {
        this.mSetupInfoHelper.saveFirstRun(isFirstRun);
    }

    public void saveLoginUserName(String userName)
    {
        this.mSetupInfoHelper.saveLoginUserName(userName);
    }

    public void savePassword(String password)
    {
        this.mSetupInfoHelper.savePassword(password);
    }

    public void update(SetupInfo setupInfo)
    {
        this.mSetupInfoHelper.setSetupParam(setupInfo);
    }
}
