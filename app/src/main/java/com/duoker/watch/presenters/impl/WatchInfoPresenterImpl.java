package com.duoker.watch.presenters.impl;

import android.text.TextUtils;

import com.duoker.watch.DuokerWatchApp;
import com.duoker.watch.R;
import com.duoker.watch.interactors.base.SimpleCallback;
import com.duoker.watch.interactors.base.SimpleErrorBundle;
import com.duoker.watch.interactors.impl.DelWatchInteractorImpl;
import com.duoker.watch.interactors.impl.EditWatchInfoInteractorImpl;
import com.duoker.watch.interactors.impl.GetStepCountTargetInteractorImpl;
import com.duoker.watch.interactors.impl.SaveStepCountTargetInteractorImpl;
import com.duoker.watch.presenters.WatchInfoPresenter;
import com.duoker.watch.presenters.base.AbstractPresenter;
import com.duoker.watch.repository.CalcWalkCountRepository;
import com.duoker.watch.repository.WatchRepository;
import com.duoker.watch.storage.StepCountTargetStorage;
import com.duoker.watch.storage.WatchsStorage;
import com.duoker.watch.ui.event.GetTargetStepCountEvent;
import com.duoker.watch.ui.event.GetWatchListEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by chengang on 5/9/2017.
 */
public class WatchInfoPresenterImpl extends AbstractPresenter implements WatchInfoPresenter {

    private final WatchInfoPresenter.View iView;
    private final CalcWalkCountRepository mCalcWalkCountRepository;
    private boolean mIsAdmin;
    private final WatchRepository mWatchRepository;
    private WatchsStorage mWatchsStorage;


    public WatchInfoPresenterImpl(WatchInfoPresenter.View view, WatchRepository watchRepository, CalcWalkCountRepository calcWalkCountRepository) {
        iView = view;
        mWatchRepository = watchRepository;
        mCalcWalkCountRepository = calcWalkCountRepository;
    }

    @Override
    public void delWatch() {
        new DelWatchInteractorImpl(DuokerWatchApp.getInstance().getLoginUser().getUserid(), mWatchsStorage.getWatchId(), mWatchRepository).
                execute(new SimpleCallback<Object>() {
                    @Override
                    public void onSuccess(Object paramT) {
                        iView.showToast(R.string.manger_watches_info_is_unbind_ok);
                        EventBus.getDefault().post(new GetWatchListEvent());
                        iView.finishActivity();
                    }

                    @Override
                    public void onError(SimpleErrorBundle errorBundle) {
                        iView.showToast(R.string.manger_watches_info_is_unbind_fail);
                    }
                });
    }

    @Override
    public WatchsStorage getWatchInfo() {
        return mWatchsStorage;
    }

    private void getStepCountTarget() {
        new GetStepCountTargetInteractorImpl(DuokerWatchApp.getInstance().getLoginUser().getLoginUserName(),
                mWatchsStorage.getWatchId(),
                mCalcWalkCountRepository).execute(new SimpleCallback<StepCountTargetStorage>() {
            @Override
            public void onSuccess(StepCountTargetStorage paramT) {
                if (paramT != null) {
                    long l = paramT.getTargetStep();
                    iView.setTargetStepText(Long.toString(l));
                    return;
                }
                iView.setTargetStepText("3000");
            }

            @Override
            public void onError(SimpleErrorBundle errorBundle) {

            }
        });
    }

    @Override
    public void initData(WatchsStorage paramWatchsStorage) {
        mWatchsStorage = paramWatchsStorage;
        String headImage = paramWatchsStorage.getHeadImage();
        String devname = paramWatchsStorage.getDevname();
        String devsex = paramWatchsStorage.getDevsex();
        String watchId = paramWatchsStorage.getWatchId();
        String phoneIMS = paramWatchsStorage.getPhoneIMS();
        mIsAdmin = TextUtils.equals(paramWatchsStorage.getOwner(), "1");
        String imgUrl = "http://duoker.cc/" + headImage;
        iView.setAvatarImg(imgUrl);
        iView.setNicknameText(devname);
        if (TextUtils.equals(devsex, "1")) {
            iView.setSexText(R.string.manger_watches_info_male);
            iView.setWatchIdText(watchId);
            iView.setPhoneText(phoneIMS);
            getStepCountTarget();
        } else {
            iView.setSexText(R.string.manger_watches_info_female);
        }

    }

    @Override
    public boolean isAdmin() {
        return mIsAdmin;
    }

    @Override
    public void saveWatchInfo(String paramString1, final String nickname, final String sex) {
        if (TextUtils.isEmpty(nickname)) {
            iView.showToast(R.string.manger_watches_info_tip2);
            return;
        }
        if (TextUtils.isEmpty(sex)) {
            iView.showToast(R.string.manger_watches_info_tip3);
            return;
        }
        String userid = DuokerWatchApp.getInstance().getLoginUser().getUserid();
        WatchsStorage storage = new WatchsStorage();
        storage.setWatchId(mWatchsStorage.getWatchId());
        storage.setDevname(nickname);
        storage.setDevsex(sex);
        storage.setHeadImage(mWatchsStorage.getHeadImage());
        new EditWatchInfoInteractorImpl(userid, storage, paramString1, mWatchRepository, null /*mAliOSSRepository*/).execute( new SimpleCallback<String>(){

            @Override
            public void onSuccess(String paramT) {
                iView.showToast(R.string.manger_watches_info_tip4);
                EventBus.getDefault().post(new GetWatchListEvent());
                mWatchsStorage.setDevname( nickname);
                mWatchsStorage.setDevsex(sex);
                mWatchsStorage.setHeadImage(paramT);
                iView.finishActivity();
            }

            @Override
            public void onError(SimpleErrorBundle errorBundle) {
                iView.showToast(R.string.manger_watches_info_tip5);
            }
        });

    }

    @Override
    public void setNickname(String paramString) {
        mWatchsStorage.setDevname(paramString);
    }

    @Override
    public void setPhone(String paramString) {
        mWatchsStorage.setPhoneIMS(paramString);
    }

    @Override
    public void setStepCountTarget(final Long stepCount) {
        String userName = DuokerWatchApp.getInstance().getLoginUser().getLoginUserName();
        final String watchId = mWatchsStorage.getWatchId();
        StepCountTargetStorage storage = new StepCountTargetStorage();
        storage.setLoginUserName(userName);
        storage.setWatchId(watchId);
        storage.setTargetStep(stepCount.longValue());
        new SaveStepCountTargetInteractorImpl(storage, mCalcWalkCountRepository).execute(
                new SimpleCallback<Object>() {

                    @Override
                    public void onSuccess(Object paramT) {
                        iView.setTargetStepText(String.valueOf(stepCount));
                        EventBus.getDefault().post(new GetTargetStepCountEvent(watchId));
                    }

                    @Override
                    public void onError(SimpleErrorBundle errorBundle) {

                    }
                });
    }


}
