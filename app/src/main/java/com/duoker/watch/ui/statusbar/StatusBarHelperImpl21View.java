package com.duoker.watch.ui.statusbar;

/**
 * Created by chengang on 4/19/2017.
 */
import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.Window;

@TargetApi(21)
class StatusBarHelperImpl21View extends StatusBarHelperImpl19
{
    public StatusBarHelperImpl21View(Activity paramActivity)
    {
        super(paramActivity);
    }

    protected void setDrawable(Drawable paramDrawable)
    {
        Window localWindow = this.mActivity.getWindow();
        localWindow.getDecorView().setSystemUiVisibility(1280);
        localWindow.setStatusBarColor(0);
        setupStatusBarView();
        setActivityRootLayoutFitSystemWindowsInternal();
        this.mStatusBarDrawable = paramDrawable;
        this.mStatusBarView.setBackground(paramDrawable);
    }
}
