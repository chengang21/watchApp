package com.duoker.watch.presenters.impl;

/**
 * Created by cheng on 2017/9/8.
 */

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;

import com.duoker.watch.DuokerWatchApp;
import com.duoker.watch.R;
import com.duoker.watch.db.model.WifiHotSpotModel;
import com.duoker.watch.interactors.base.SimpleCallback;
import com.duoker.watch.interactors.base.SimpleErrorBundle;
import com.duoker.watch.interactors.impl.GetWatchSettingInteractorImpl;
import com.duoker.watch.interactors.impl.GetWifiSpot2ModelInteractorImpl;
import com.duoker.watch.interactors.impl.SetDeviceWifiHotSpotInteractorImpl;
import com.duoker.watch.model.SetWifiHotSpotModel;
import com.duoker.watch.db.model.WatchSettingBean;
import com.duoker.watch.db.model.WifisetBean;
import com.duoker.watch.presenters.WatchSetsWifiPresenter;
import com.duoker.watch.repository.WatchSettingRepository;
import com.duoker.watch.ui.event.GetWatchSetsEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class WatchSetsWifiPresenterImpl extends WatchSetsWifiPresenter
{
    private final WatchSetsWifiPresenter.View iView;
    private WatchSettingBean mWatchSettingBean;
    private final WatchSettingRepository mWatchSettingRepository;
    private List<WifiHotSpotModel> mWifiHotSpotModels = new ArrayList();

    public WatchSetsWifiPresenterImpl(WatchSetsWifiPresenter.View paramView, WatchSettingRepository paramWatchSettingRepository)
    {
        this.iView = paramView;
        this.mWatchSettingRepository = paramWatchSettingRepository;
    }

    private void getSets()
    {
        String str1 = DuokerWatchApp.getInstance().getLoginUser().getUserid();
        String str2 = DuokerWatchApp.getInstance().getDefaultWatch().getWatchId();
        this.iView.showProgress();
        new GetWatchSettingInteractorImpl(str1, str2, this.mWatchSettingRepository).execute( new SimpleCallback<WatchSettingBean>(){

            @Override
            public void onSuccess(WatchSettingBean paramT) {
                mWatchSettingBean = paramT;
                iView.hideProgress();
            }

            @Override
            public void onError(SimpleErrorBundle errorBundle) {
                iView.hideProgress();
            }
        });
    }

    private void initData(WatchSettingBean paramWatchSettingBean)
    {
        this.mWatchSettingBean = paramWatchSettingBean;
        if (this.mWatchSettingBean != null)
        {
            boolean bool = isEnable();
            this.iView.checkEnable(bool);
            WifisetBean wifisetBean = this.mWatchSettingBean.getWifiset();
            if (wifisetBean != null)
            {
                String wifissid = wifisetBean.getWifissid();
                String wifipwd = wifisetBean.getWifipwd();
                this.iView.setSsidText(wifissid);
                this.iView.setPassText(wifipwd);
            }
        }
    }

    public void enable(boolean wifiChecked, String ssid, String pwd)
    {
        if (TextUtils.isEmpty(ssid))
        {
            this.iView.showToast(R.string.watch_sets_wifi_name_not_empty);

            if (wifiChecked){
                iView.checkEnable(wifiChecked);
            }
            return;
        }
        String userid = DuokerWatchApp.getInstance().getLoginUser().getUserid();
        String watchId = DuokerWatchApp.getInstance().getDefaultWatch().getWatchId();
        SetWifiHotSpotModel setWifiHotSpotModel = new SetWifiHotSpotModel();
        setWifiHotSpotModel.setWifienable(wifiChecked?1:0);
        setWifiHotSpotModel.setWifimac(ssid);
        setWifiHotSpotModel.setWifissid(ssid);
        setWifiHotSpotModel.setWifipwd(pwd);
        new SetDeviceWifiHotSpotInteractorImpl(userid, watchId, setWifiHotSpotModel, this.mWatchSettingRepository).execute( new SetDeviceWifiHotSpotCallback(wifiChecked));
    }

    public boolean isEnable()
    {
        try
        {
            int i = this.mWatchSettingBean.getWifiset().getWifienable();
            return i == 1;
        }
        catch (Exception localException)
        {
            localException.printStackTrace();
        }
        return false;
    }

    public void mergeWifi(List<WifiHotSpotModel> paramList)
    {
        if (paramList != null)
            this.mWifiHotSpotModels.addAll(paramList);
        this.iView.inflateListView(this.mWifiHotSpotModels);
    }

    public void onCreate(Bundle paramBundle)
    {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                scanWifi();
            }
        });
    }

    public void scanWifi()
    {
        String userid = DuokerWatchApp.getInstance().getLoginUser().getUserid();
        String watchId = DuokerWatchApp.getInstance().getDefaultWatch().getWatchId();
        iView.showProgress();
        new GetWifiSpot2ModelInteractorImpl(userid, watchId, this.mWatchSettingRepository).execute(new SimpleCallback<List<WifiHotSpotModel>>(){

            @Override
            public void onSuccess(List<WifiHotSpotModel> list) {
                iView.hideProgress();
                if (list != null)
                    mWifiHotSpotModels.addAll(list);
                iView.scanLocalWifi();
            }

            @Override
            public void onError(SimpleErrorBundle errorBundle) {
                iView.hideProgress();
                iView.scanLocalWifi();
            }
        });
    }

    public void setWifi(String paramString1, String paramString2)
    {
        if (TextUtils.isEmpty(paramString1))
        {
            this.iView.showToast(R.string.watch_sets_wifi_name_not_empty);
            return;
        }
        String userid = DuokerWatchApp.getInstance().getLoginUser().getUserid();
        String watchId = DuokerWatchApp.getInstance().getDefaultWatch().getWatchId();
        SetWifiHotSpotModel setWifiHotSpotModel = new SetWifiHotSpotModel();
        setWifiHotSpotModel.setWifienable(1);
        setWifiHotSpotModel.setWifimac(paramString1);
        setWifiHotSpotModel.setWifissid(paramString1);
        setWifiHotSpotModel.setWifipwd(paramString2);
        new SetDeviceWifiHotSpotInteractorImpl(userid, watchId, setWifiHotSpotModel, this.mWatchSettingRepository).execute(new SimpleCallback<Object>() {
            @Override
            public void onSuccess(Object paramT) {
                iView.showToast(R.string.common_all_edit_ok);
                EventBus.getDefault().post(new GetWatchSetsEvent());
                iView.finishActivity();
            }

            @Override
            public void onError(SimpleErrorBundle errorBundle) {
                iView.showToast(R.string.common_all_edit_fail);
            }
        });
    }

    private class SetDeviceWifiHotSpotCallback implements SimpleCallback<Object>
    {
        boolean mChecked = false;
        public SetDeviceWifiHotSpotCallback(boolean checked )
        {
            mChecked = checked;
        }
        @Override
        public void onSuccess(Object paramT) {
            iView.showToast(R.string.common_all_edit_ok);
            iView.checkEnable(mChecked);
            EventBus.getDefault().post(new GetWatchSetsEvent());
            // WatchSetsWifiPresenterImpl.this.scanWifi();
        }

        @Override
        public void onError(SimpleErrorBundle errorBundle) {
            iView.showToast(R.string.common_all_edit_fail);
            if (mChecked) {
                iView.checkEnable(mChecked);
            }
        }
    }
}
