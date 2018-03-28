package com.duoker.watch.presenters;

import com.duoker.watch.presenters.base.AbstractPresenter;
import com.duoker.watch.ui.BaseView;

/**
 * Created by cheng on 2017/10/8.
 */

public abstract class HomeMorePresenter extends AbstractPresenter
{
    public abstract void forbidShutdown();

    public abstract void forceShutdown();

    public abstract void getSets();

    public abstract void intentCheckPhoneBill();

    public abstract void intentForbidShutdown();

    public abstract void intentGotoBloodOxygen();

    public abstract void intentGotoClassStop();

    public abstract void intentGotoFence();

    public abstract void intentGotoHeartRate();

    public abstract void intentGotoOneDirectionCall();

    public abstract void intentGotoSleep();

    public abstract void intentGotoStepCounter();

    public abstract void intentGotoTrackMap();

    public abstract void intentGotoWifiSetting();

    public abstract boolean isAdmin();

    public abstract void sureOneDirectionCall(String txt);

    public interface View extends BaseView
    {
        void call(String phoneNumber);

        void gotoBloodOxygen();

        void gotoClassStop();

        void gotoFence();

        void gotoHeartRate();

        void gotoSleep();

        void gotoStepCounter();

        void gotoTrackMapAMap();

        void gotoTrackMapGoogle();

        void gotoWifiSetting();

        void setForbidShutdownText(String text);

        void showIntentForbidShutdown(boolean paramBoolean);

        void showNoWatch(boolean paramBoolean);

        void showNotSupportFunc();

        void showSureOneDirectionCallDialog(String txt);

        void showTelFareDialog(String txt);
    }
}