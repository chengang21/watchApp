package com.duoker.watch.presenters;

import com.duoker.watch.storage.WatchsStorage;
import com.duoker.watch.ui.BaseView;

/**
 * Created by chengang on 5/9/2017.
 */

public interface WatchInfoPresenter {
    void delWatch();

    WatchsStorage getWatchInfo();

    void initData(WatchsStorage paramWatchsStorage);

    boolean isAdmin();

    void saveWatchInfo(String paramString1, String paramString2, String paramString3);

    void setNickname(String paramString);

    void setPhone(String paramString);

    void setStepCountTarget(Long paramLong);

    interface View extends BaseView
    {
        void finishActivity();

        void setAvatarImg(String paramString);

        void setNicknameText(String paramString);

        void setPhoneText(String paramString);

        void setSexText(int paramInt);

        void setTargetStepText(String paramString);

        void setWatchIdText(String paramString);
    }
}
