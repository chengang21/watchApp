package com.duoker.watch.ui.base;

/**
 * Created by cheng on 2017/10/8.
 */

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.view.View;

public class BaseComponent
{
    private Activity mActivity;
    private View mRootView;

    public BaseComponent(Activity paramActivity)
    {
        this.mActivity = paramActivity;
    }

    public View findViewById(int paramInt)
    {
        if (this.mRootView != null)
            return this.mRootView.findViewById(paramInt);
        throw new RuntimeException("rootView is not attach");
    }

    public Activity getActivity()
    {
        return this.mActivity;
    }

    public View getRootView()
    {
        return this.mRootView;
    }

    public final String getString(@StringRes int paramInt)
    {
        return this.mActivity.getResources().getString(paramInt);
    }

    public void setContentView(@LayoutRes int paramInt)
    {
        this.mRootView = View.inflate(this.mActivity, paramInt, null);
    }

    public void setContentView(View paramView)
    {
        this.mRootView = paramView;
    }
}
