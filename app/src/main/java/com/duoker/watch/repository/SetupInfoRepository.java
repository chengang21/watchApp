package com.duoker.watch.repository;

import com.duoker.watch.model.SetupInfo;

/**
 * Created by cheng on 2017/9/3.
 */
public interface SetupInfoRepository
{
    SetupInfo get();

    void saveFirstRun(boolean isFirstRun);

    void saveLoginUserName(String userName);

    void savePassword(String password);

    void update(SetupInfo setupInfo);
}