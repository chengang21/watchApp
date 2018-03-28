package com.duoker.watch.presenters.impl;

import android.text.TextUtils;

import com.duoker.watch.R;
import com.duoker.watch.db.model.CardNumberBean;
import com.duoker.watch.presenters.WatchContactAddPresenter;
import com.duoker.watch.presenters.base.AbstractPresenter;
import com.duoker.watch.ui.event.AddOrEditWatchContactsEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by chengang on 4/30/2017.
 */
public class WatchContactsAddPresenterImpl extends AbstractPresenter implements WatchContactAddPresenter {
    private final View iView;
    private CardNumberBean mCardNumberBean;
    private int mPosition = -1;

    public WatchContactsAddPresenterImpl(WatchContactAddPresenter.View view)
    {
        this.iView = view;
    }

    public void addOrEditContacts(String name, String phone, String paramString3, int type)
    {
        if (TextUtils.isEmpty(name))
        {
            this.iView.showToast(R.string.contacts_add_tip_1);
            return;
        }
        if (TextUtils.isEmpty(phone))
        {
            this.iView.showToast(R.string.contacts_add_tip_2);
            return;
        }
        if (type == -1)
        {
            this.iView.showToast(R.string.contacts_add_tip_3);
            return;
        }

        CardNumberBean bean = new CardNumberBean();
        bean.setCardid(this.mCardNumberBean.getCardid());
        bean.setCardname(name);
        bean.setCardnum(phone);
        bean.setCardshortnum(paramString3);
        bean.setCardIsChecked(this.mCardNumberBean.getCardIsChecked());
        bean.setCardtype(type);

        AddOrEditWatchContactsEvent event = new AddOrEditWatchContactsEvent();
        event.cardNumberBean = bean;
        event.position = this.mPosition;
        EventBus.getDefault().post(event);
        this.iView.finishActivity();
    }

    @Override
    public void initData(CardNumberBean bean, int paramInt)
    {
        this.mPosition = paramInt;
        if (bean != null)
        {
            this.mCardNumberBean = bean;
            this.iView.setToolbarTitleText(R.string.contacts_add_title_edit);
            this.iView.setNameText(bean.getCardname());
            this.iView.setPhoneText(bean.getCardnum());
            this.iView.setShortNumText(bean.getCardshortnum());
            int i = bean.getCardtype();
            if (i == 1)
                this.iView.setCheckSosLayout(true);
            else if (i == 2) {
                iView.setCheckPhoneCallLayout(true);
            }

            this.iView.setCheckListenLayout(true);
            return;
        }
        this.mCardNumberBean = new CardNumberBean();
        this.mCardNumberBean.setCardid("-1");
        this.mCardNumberBean.setCardIsChecked(0);
        this.iView.setToolbarTitleText(R.string.contacts_add_title_add);
    }
}
