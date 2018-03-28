package com.duoker.watch.interactors.impl;

import android.text.TextUtils;

import com.duoker.watch.storage.WatchsStorage;
import com.duoker.watch.interactors.base.SimpleInteractor;
import com.duoker.watch.repository.AliOSSRepository;
import com.duoker.watch.repository.WatchRepository;

/**
 * Created by chengang on 5/9/2017.
 */

public class EditWatchInfoInteractorImpl extends SimpleInteractor<String> {
    private final AliOSSRepository mAliOSSRepository;
    private final String mAvatarFilePath;
    private final String mUserId;
    private final WatchRepository mWatchRepository;
    private final WatchsStorage mWatchsStorage;

    public EditWatchInfoInteractorImpl(String userid, WatchsStorage paramWatchsStorage, String paramString2, WatchRepository paramWatchRepository, AliOSSRepository paramAliOSSRepository) {
        this.mUserId = userid;
        this.mWatchsStorage = paramWatchsStorage;
        this.mAvatarFilePath = paramString2;
        this.mWatchRepository = paramWatchRepository;
        this.mAliOSSRepository = paramAliOSSRepository;
    }

    public void run() {
        try {
            if (!TextUtils.isEmpty(this.mAvatarFilePath)) {
                String url = this.mAliOSSRepository.uploadAvatar(this.mAvatarFilePath);
                this.mWatchsStorage.setHeadImage( url);
                this.mWatchRepository.edit(this.mUserId, mWatchsStorage);
                postObject2UiThread(url);
            } else {
                this.mWatchsStorage.getHeadImage();
            }
        } catch (Exception e) {
            postException2UiThread(e);
        }
    }
}
