package com.duoker.watch.ui.statusbar;

/**
 * Created by chengang on 4/19/2017.
 */


import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

@TargetApi(19)
class StatusBarHelperImpl19 extends StatusBarHelperImplBase
{
    protected Drawable mStatusBarDrawable;
    protected View mStatusBarView;

    public StatusBarHelperImpl19(Activity paramActivity)
    {
        super(paramActivity);
    }

    protected void destroy()
    {
        destroyStatusBarView();
        setStatusBarTranslucent(false);
    }

    protected void destroyStatusBarView()
    {
        if (this.mStatusBarView != null)
        {
            ((ViewGroup)this.mActivity.findViewById(android.R.id.content)).removeView(this.mStatusBarView);
            this.mStatusBarView = null;
        }
    }

    protected void setColor(int paramInt)
    {
        setDrawable(new ColorDrawable(paramInt));
    }

    protected void setDrawable(Drawable paramDrawable)
    {
        setStatusBarTranslucent(true);
        setupStatusBarView();
        setActivityRootLayoutFitSystemWindowsInternal();
        this.mStatusBarDrawable = paramDrawable;
        this.mStatusBarView.setBackground(paramDrawable);
    }

    protected void setStatusBarTranslucent(boolean paramBoolean)
    {
        Window localWindow = this.mActivity.getWindow();
        WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
        if (paramBoolean)
            if ((0x4000000 & localLayoutParams.flags) == 0)
            {
                localLayoutParams.flags = (0x4000000 | localLayoutParams.flags);
                localWindow.setAttributes(localLayoutParams);
            }
        while ((0x4000000 & localLayoutParams.flags) == 0)
            return;
        localLayoutParams.flags = (0xFBFFFFFF & localLayoutParams.flags);
        localWindow.setAttributes(localLayoutParams);
    }

    protected void setupStatusBarView()
    {
        if (this.mStatusBarView == null)
        {
            this.mStatusBarView = new View(this.mActivity);
            ViewGroup.LayoutParams localLayoutParams = new ViewGroup.LayoutParams(-1, getStatusBarHeight());
            ((ViewGroup)this.mActivity.findViewById(android.R.id.content)).addView(this.mStatusBarView, localLayoutParams);
        }
    }
}
