package com.duoker.watch.presenters.impl;

import android.text.TextUtils;

import com.duoker.watch.DuokerWatchApp;
import com.duoker.watch.R;
import com.duoker.watch.interactors.base.SimpleCallback;
import com.duoker.watch.interactors.base.SimpleErrorBundle;
import com.duoker.watch.interactors.impl.BindSimCardInteractorImpl;
import com.duoker.watch.presenters.WatchInfoPhonePresenter;
import com.duoker.watch.presenters.base.AbstractPresenter;
import com.duoker.watch.repository.WatchRepository;
import com.duoker.watch.storage.WatchsStorage;
import com.duoker.watch.ui.event.GetWatchListEvent;
import com.duoker.watch.ui.event.SetWatchInfoPhoneEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by chengang on 5/9/2017.
 */

public class WatchInfoPhonePresenterImpl extends AbstractPresenter implements WatchInfoPhonePresenter {

    private final WatchInfoPhonePresenter.View iView;
    private String mOldPhone;
    private final WatchRepository mWatchRepository;
    private WatchsStorage mWatchsStorage;

    public WatchInfoPhonePresenterImpl( WatchInfoPhonePresenter.View view, WatchRepository repository) {
        iView = view;
        mWatchRepository = repository;
    }

    @Override
    public void bindSimCard(String simcard) {
        if (TextUtils.isEmpty(simcard))
        {
            this.iView.setPhoneError(R.string.manger_watches_info_phone_not_empty );
            return;
        }

        new BindSimCardInteractorImpl( DuokerWatchApp.getInstance().getLoginUser().getUserid(),
                mWatchsStorage.getWatchId(), simcard, mWatchRepository ).execute( new SimpleCallback<SetWatchInfoPhoneEvent>(){

            @Override
            public void onSuccess( SetWatchInfoPhoneEvent parm ) {
                iView.showToast( R.string.manger_watches_info_phone_ok );
                SetWatchInfoPhoneEvent event = new SetWatchInfoPhoneEvent();
                event.phone = parm.phone;
                EventBus.getDefault().post(event);
                EventBus.getDefault().post(new GetWatchListEvent());
                iView.finishActivity();
            }

            @Override
            public void onError(SimpleErrorBundle errorBundle) {
                iView.showToast(R.string.manger_watches_info_phone_fail);
            }
        });
    }

    @Override
    public String getOldPhone() {
        return mOldPhone;
    }

    @Override
    public void initData(WatchsStorage storage) {
        mWatchsStorage = storage;
        mOldPhone = storage.getPhoneIMS();
        iView.setPhoneText(mOldPhone);
    }
}
