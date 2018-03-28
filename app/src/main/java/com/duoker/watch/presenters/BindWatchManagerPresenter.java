package com.duoker.watch.presenters;

import com.duoker.watch.model.BindUserListBean;
import com.duoker.watch.ui.BaseView;

import java.util.List;

/**
 * Created by cheng on 2017/10/8.
 */
public abstract class BindWatchManagerPresenter {
    public abstract void clickTransfer();

    public abstract void clickUnbindOther();

    public abstract void clickUnbindSelf();

    public abstract void clickUnbindSelfNFactorySetting();

    public abstract void getBindUserList();

    public abstract boolean isAdmin();

    public abstract void transfer(BindUserListBean.UserListBean paramUserListBean);

    public abstract void unbindOther(BindUserListBean.UserListBean paramUserListBean);

    public abstract void unbindSelf();

    public abstract void unbindSelfNFactorySetting();

    public static abstract interface View extends BaseView
    {
        void finishActivity();

        void gotoHomeDevice();

        void showIsUnbindSelf();

        void showIsUnbindSelfNFactorySetting();

        void showTipDialog(int paramInt);

        void showTransferDialog(List<BindUserListBean.UserListBean> paramList);

        void showUnbindOtherDialog(List<BindUserListBean.UserListBean> paramList);
    }
}
