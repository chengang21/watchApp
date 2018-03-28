package com.duoker.watch.ui.statusbar;

/**
 * Created by chengang on 4/19/2017.
 */

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.Window;

@TargetApi(21)
class StatusBarHelperImpl21Normal extends StatusBarHelperImplBase
{
    public StatusBarHelperImpl21Normal(Activity paramActivity)
    {
        super(paramActivity);
    }

    protected void destroy()
    {
    }

    protected void setColor(int paramInt)
    {
        this.mActivity.getWindow().setStatusBarColor(paramInt);
        setActivityRootLayoutFitSystemWindowsInternal();
    }

    protected void setDrawable(Drawable paramDrawable)
    {
    }
}