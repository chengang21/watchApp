package com.duoker.watch.presenters;

/**
 * Created by cheng on 2017/9/8.
 */

import com.duoker.watch.db.model.WifiHotSpotModel;
import com.duoker.watch.presenters.base.AbstractPresenter;
import com.duoker.watch.ui.BaseView;

import java.util.List;

public abstract class WatchSetsWifiPresenter extends AbstractPresenter
{
    public abstract void enable(boolean paramBoolean, String paramString1, String paramString2);

    public abstract boolean isEnable();

    public abstract void mergeWifi(List<WifiHotSpotModel> paramList);

    public abstract void scanWifi();

    public abstract void setWifi(String paramString1, String paramString2);

    public static interface View extends BaseView
    {
        public void checkEnable(boolean paramBoolean);

        public void finishActivity();

        public void inflateListView(List<WifiHotSpotModel> paramList);

        public void scanLocalWifi();

        public void setPassText(String paramString);

        public void setSsidText(String paramString);
    }
}