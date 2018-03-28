package com.duoker.watch.presenters.impl;

import com.duoker.watch.DuokerWatchApp;
import com.duoker.watch.model.BindUserListBean;
import com.duoker.watch.presenters.BindWatchManagerPresenter;

/**
 * Created by cheng on 2017/10/8.
 */
public class BindWatchManagerPresenterImpl extends BindWatchManagerPresenter
{
    @Override
    public void clickTransfer() {

    }

    @Override
    public void clickUnbindOther() {

    }

    @Override
    public void clickUnbindSelf() {

    }

    @Override
    public void clickUnbindSelfNFactorySetting() {

    }

    @Override
    public void getBindUserList() {

    }

    @Override
    public boolean isAdmin() {
        return false;
    }

    @Override
    public void transfer(BindUserListBean.UserListBean paramUserListBean) {

    }

    @Override
    public void unbindOther(BindUserListBean.UserListBean paramUserListBean) {

    }

    @Override
    public void unbindSelf() {
        String str1 = DuokerWatchApp.getInstance().getLoginUser().getUserid();
        String str2 = DuokerWatchApp.getInstance().getDefaultWatch().getWatchId();
        // this.iView.showLoading();
        // new DelWatchInteractorImpl(str1, str2, this.mWatchRepository).execute(new BindWatchManagerPresenterImpl.3(this));
    }

    @Override
    public void unbindSelfNFactorySetting() {

    }
}
