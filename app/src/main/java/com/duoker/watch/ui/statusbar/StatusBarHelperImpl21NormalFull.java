package com.duoker.watch.ui.statusbar;

/**
 * Created by chengang on 4/19/2017.
 */

import android.annotation.TargetApi;
import android.app.Activity;
import android.view.View;
import android.view.Window;

@TargetApi(21)
class StatusBarHelperImpl21NormalFull extends StatusBarHelperImpl21Normal
{
    public StatusBarHelperImpl21NormalFull(Activity paramActivity)
    {
        super(paramActivity);
    }

    protected void setColor(int paramInt)
    {
        this.mActivity.getWindow().getDecorView().setSystemUiVisibility(1280);
        setActivityRootLayoutFitSystemWindowsInternal();
        super.setColor(paramInt);
    }
}
