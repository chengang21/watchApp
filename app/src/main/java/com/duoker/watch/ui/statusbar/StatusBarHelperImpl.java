package com.duoker.watch.ui.statusbar;

/**
 * Created by chengang on 4/19/2017.
 */

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;

abstract class StatusBarHelperImpl
{
    final Activity mActivity;
    boolean mActivityRootLayoutFitSystemWindows = true;

    public StatusBarHelperImpl(Activity paramActivity)
    {
        this.mActivity = paramActivity;
    }

    protected abstract void destroy();

    protected int getStatusBarHeight()
    {
        int i = this.mActivity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return this.mActivity.getResources().getDimensionPixelSize(i);
    }

    public boolean isActivityRootLayoutFitSystemWindows()
    {
        return this.mActivityRootLayoutFitSystemWindows;
    }

    public void setActivityRootLayoutFitSystemWindows(boolean paramBoolean)
    {
        this.mActivityRootLayoutFitSystemWindows = paramBoolean;
    }

    protected void setActivityRootLayoutFitSystemWindowsInternal()
    {
        ((ViewGroup)this.mActivity.findViewById(android.R.id.content)).getChildAt(0).setFitsSystemWindows(isActivityRootLayoutFitSystemWindows());
    }

    protected abstract void setColor(int paramInt);

    void setConsumeInsets(final StatusBarHelper.OnConsumeInsetsCallback paramOnConsumeInsetsCallback)
    {
        ViewGroup localViewGroup = (ViewGroup)this.mActivity.findViewById(android.R.id.content);
        View localView = localViewGroup.getChildAt(0);
        ConsumeInsetsFrameLayout localConsumeInsetsFrameLayout = new ConsumeInsetsFrameLayout(this.mActivity);
        localViewGroup.removeView(localView);
        localConsumeInsetsFrameLayout.addView(localView);
        localViewGroup.addView(localConsumeInsetsFrameLayout);
        localConsumeInsetsFrameLayout.setOnInsetsCallback(new ConsumeInsetsFrameLayout.OnInsetsCallback()
        {
            public void onInsetsChanged(Rect paramAnonymousRect)
            {
                if (paramOnConsumeInsetsCallback != null)
                    paramOnConsumeInsetsCallback.onInsetsChanged(paramAnonymousRect);
            }
        });
    }

    protected abstract void setDrawable(Drawable paramDrawable);
}