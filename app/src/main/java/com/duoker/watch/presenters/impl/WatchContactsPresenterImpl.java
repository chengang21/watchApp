package com.duoker.watch.presenters.impl;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;

import com.duoker.watch.DuokerWatchApp;
import com.duoker.watch.R;
import com.duoker.watch.db.model.CardNumberBean;
import com.duoker.watch.db.model.WatchSettingBean;
import com.duoker.watch.interactors.base.SimpleCallback;
import com.duoker.watch.interactors.base.SimpleErrorBundle;
import com.duoker.watch.interactors.impl.GetCardNumberInteractorImpl;
import com.duoker.watch.interactors.impl.GetWatchSettingInteractorImpl;
import com.duoker.watch.interactors.impl.SaveAllCardNumberInteractorImpl;
import com.duoker.watch.presenters.WatchContactsPresenter;
import com.duoker.watch.presenters.base.AbstractPresenter;
import com.duoker.watch.repository.CardNumberRepository;
import com.duoker.watch.repository.WatchRepository;
import com.duoker.watch.repository.WatchSettingRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by chengang on 4/25/2017.
 */

public class WatchContactsPresenterImpl extends AbstractPresenter implements WatchContactsPresenter {
    private final View iView;
    private boolean isAdmin = false;
    private boolean isNeedSave = false;
    private List<CardNumberBean> mCardNumberBeanList = new ArrayList<CardNumberBean>();
    private final Handler mHandler = new Handler();
    private final CardNumberRepository mCardNumberRepository;
    private final WatchRepository mWatchRepository;
    private final WatchSettingRepository mWatchSettingRepository;

    public WatchContactsPresenterImpl(View view, CardNumberRepository cardNumRepository, WatchSettingRepository watchSettingRepository, WatchRepository watchRepository) {
        this.iView = view;
        this.mCardNumberRepository = cardNumRepository;
        this.mWatchSettingRepository = watchSettingRepository;
        this.mWatchRepository = watchRepository;
    }

    private Comparator<CardNumberBean> mComparator = new Comparator<CardNumberBean>() {
        public int compare(CardNumberBean bean1, CardNumberBean bean2) {
            int i = bean1.getCardtype();
            int j = bean2.getCardtype();
            if (i > j)
                return 1;
            if (i == j)
                return 0;
            return -1;
        }
    };

    public void addItem(CardNumberBean bean, int pos) {
        if (bean == null)
            return;

        this.isNeedSave = true;
        if (pos == -1) {
            mCardNumberBeanList.add(bean);
        } else {
            String cardid = bean.getCardid();
            CardNumberBean record = (CardNumberBean) this.mCardNumberBeanList.get(pos);
            if (TextUtils.equals(record.getCardid(), cardid)) {
                record.setCardnum(bean.getCardnum());
                record.setCardname(bean.getCardname());
                record.setCardshortnum(bean.getCardshortnum());
                record.setCardIsChecked(bean.getCardIsChecked());
                record.setCardtype(bean.getCardtype());
            }
        }
        Collections.sort(this.mCardNumberBeanList, this.mComparator);
        iView.inflateListView(this.mCardNumberBeanList);
    }

    public void delItem(CardNumberBean bean) {
        mCardNumberBeanList.remove(bean);
        isNeedSave = true;
        iView.inflateListView(mCardNumberBeanList);
    }

    // get all the contacts of the specified watch
    public void getCallNumbers() {
        iView.showProgress();
        String userid = DuokerWatchApp.getInstance().getLoginUser().getUserid();
        String watchid = DuokerWatchApp.getInstance().getDefaultWatch().getWatchId();

        new GetCardNumberInteractorImpl(userid, watchid, mCardNumberRepository).execute(new SimpleCallback<List<CardNumberBean>>() {
            @Override
            public void onError(SimpleErrorBundle paramDefaultErrorBundle) {
                iView.hideProgress();
            }

            @Override
            public void onSuccess(List<CardNumberBean> list) {
                iView.hideProgress();
                if ((list != null) && (!list.isEmpty())) {
                    mCardNumberBeanList.clear();
                    mCardNumberBeanList.addAll(list);
                    Collections.sort(mCardNumberBeanList, mComparator);
                    iView.inflateListView(list);
                }
            }
        });

    }


    private void checkWatch() {
        //new CheckWatchInteractorImpl(DuokerWatchApp.getInstance().getLoginUser().getUserid(), DuokerWatchApp.getInstance().getDefaultWatch().getWatchId(), this.mWatchRepository).execute(new WatchContactsPresenterImpl5 (this));
    }

    private void getWatchSetting() {
        iView.showProgress();
        String userid = "chengang"; // uokerWatchApp.getInstance().getLoginUser().getUserid()
        String watchid = "watchid";  // DuokerWatchApp.getInstance().getDefaultWatch().getWatchId()

        new GetWatchSettingInteractorImpl( userid,
                watchid,
                mWatchSettingRepository
        ).execute( new SimpleCallback<WatchSettingBean>() {

            @Override
            public void onSuccess(WatchSettingBean bean) {
                iView.hideProgress();
                if (bean.getOnlyCallPHB() == 1)
                {
                    iView.setWhiteNameListChecked(true);
                    return;
                }
                iView.setWhiteNameListChecked(false);
            }

            @Override
            public void onError(SimpleErrorBundle errorBundle) {
                iView.hideProgress();
            }
        });
    }


    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean isNeedSave() {
        return isNeedSave;
    }

    public void onCreate(Bundle paramBundle) {
//        if (TextUtils.equals(DuokerWatchApp.getInstance().getDefaultWatch().getOwner(), "1"))
        isAdmin = true;

        // 父 View 没有创建好，咋办呢？这里有一个最简单的办法就是过一段时间再调这个函数，此函数中的弹出框调用部分放到一个 Handler 中，并且通过延时来触发, delay 200ms
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                getCallNumbers();

                String phone = "13916101696"; //DuokerWatchApp.getInstance().getDefaultWatch().getPhoneIMS();
                iView.setPhoneText(phone);
                String str2 = "chengang";  //DuokerWatchApp.getInstance().getDefaultWatch().getHeadImage();

                if (!TextUtils.isEmpty(str2)) {
                    String str3 = "https://www.baidu.com/img/bd_logo1.png"; // + str2;
                    iView.setAvatar(str3);
                }
            }
        }, 200);
    }

    public void saveContacts(final boolean finishActivity) {
        if (mCardNumberBeanList == null)
            mCardNumberBeanList = new ArrayList();
        iView.showProgress();

        String userid =  DuokerWatchApp.getInstance().getLoginUser().getUserid();
        String watchid =  DuokerWatchApp.getInstance().getDefaultWatch().getWatchId();

        new SaveAllCardNumberInteractorImpl(userid, watchid, mCardNumberBeanList, mCardNumberRepository).execute(new SimpleCallback<Object>() {

            @Override
            public void onSuccess(Object paramT) {
                iView.hideProgress();
                if (finishActivity) {
                    iView.finishActivity();
                    return;
                }
                getCallNumbers();
                iView.showToast(R.string.contacts_tip_1);
            }

            @Override
            public void onError(SimpleErrorBundle errorBundle) {
                iView.hideProgress();
                iView.showToast(R.string.contacts_tip_2);

            }
        });
    }

    public void setWhiteNameList(boolean paramBoolean) {
        if (paramBoolean) ;
        for (int i = 1; ; i = 0) {
            this.iView.showProgress();
            //new SetOnlyCallPHBInteractorImpl(DuokerWatchApp.getInstance().getLoginUser().getUserid(), DuokerWatchApp.getInstance().getDefaultWatch().getWatchId(), i, this.mWatchSettingRepository).execute(new WatchContactsPresenterImplparamBoolean));
            return;
        }
    }
}
