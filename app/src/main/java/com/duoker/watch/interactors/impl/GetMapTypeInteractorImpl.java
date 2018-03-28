package com.duoker.watch.interactors.impl;

import com.duoker.watch.interactors.base.SimpleInteractor;
import com.duoker.watch.model.UserSetupInfo;
import com.duoker.watch.repository.UserSetupInfoRepository;

/**
 * Created by cheng on 2017/10/9.
 */
public class GetMapTypeInteractorImpl extends SimpleInteractor<UserSetupInfo.MapTypeEnum>
{
    private final String mLoginUserName;
    private final UserSetupInfoRepository mUserSetupInfoRepository;

    public GetMapTypeInteractorImpl(String paramString, UserSetupInfoRepository paramUserSetupInfoRepository)
    {
        this.mLoginUserName = paramString;
        this.mUserSetupInfoRepository = paramUserSetupInfoRepository;
    }

    public void run()
    {
        postObject2UiThread(this.mUserSetupInfoRepository.getMapType(this.mLoginUserName));
    }
}
