package com.duoker.watch.ui.statusbar;

/**
 * Created by chengang on 4/19/2017.
 */

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.graphics.Rect;

public class StatusBarHelper
{
    public static final int LEVEL_19_TRANSLUCENT = 1;
    public static final int LEVEL_21_NORMAL = 1;
    public static final int LEVEL_21_NORMAL_FULL = 2;
    public static final int LEVEL_21_VIEW = 3;
    public static final int LEVEL_NONE=0;
    private final StatusBarHelperImpl mImpl;

    public abstract interface OnConsumeInsetsCallback
    {
        public abstract void onInsetsChanged(Rect paramRect);
    }

    public StatusBarHelper(Activity paramActivity)
    {
        this(paramActivity, 1, 1);
    }

    public StatusBarHelper(Activity paramActivity, int paramInt1, int paramInt2)
    {
        if (paramInt2 == 1)
        {
            this.mImpl = new StatusBarHelperImpl21Normal(paramActivity);
            return;
        }
        if (paramInt2 == 2)
        {
            this.mImpl = new StatusBarHelperImpl21NormalFull(paramActivity);
            return;
        }
        if (paramInt2 == 3)
        {
            this.mImpl = new StatusBarHelperImpl21View(paramActivity);
            return;
        }
        this.mImpl = new StatusBarHelperImplBase(paramActivity);
        return;
    }

    public void setActivityRootLayoutFitSystemWindows(boolean paramBoolean)
    {
        this.mImpl.setActivityRootLayoutFitSystemWindows(paramBoolean);
    }

    public void setColor(int paramInt)
    {
        this.mImpl.setColor(paramInt);
    }

    public void setConsumeInsets(StatusBarHelper.OnConsumeInsetsCallback paramOnConsumeInsetsCallback)
    {
        this.mImpl.setConsumeInsets(paramOnConsumeInsetsCallback);
    }

    public void setDrawable(Drawable paramDrawable)
    {
        this.mImpl.setDrawable(paramDrawable);
    }
}
