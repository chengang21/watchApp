package com.duoker.watch.repository;

import com.duoker.watch.model.UserSetupInfo;

/**
 * Created by cheng on 2017/10/8.
 */
public interface UserSetupInfoRepository {
    UserSetupInfo get(String paramString);

    UserSetupInfo.MapTypeEnum getMapType(String paramString);

    void setMapType(String paramString, UserSetupInfo.MapTypeEnum paramMapTypeEnum);

    void update(String paramString, UserSetupInfo paramUserSetupInfo);
}
