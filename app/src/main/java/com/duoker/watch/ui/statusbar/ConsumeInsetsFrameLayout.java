package com.duoker.watch.ui.statusbar;

/**
 * Created by chengang on 4/19/2017.
 */

import android.content.Context;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.widget.FrameLayout;

final class ConsumeInsetsFrameLayout extends FrameLayout
{
    private Rect mInsets = new Rect();
    private OnInsetsCallback mOnInsetsCallback;

    public ConsumeInsetsFrameLayout(Context paramContext)
    {
        super(paramContext);
    }

    public ConsumeInsetsFrameLayout(Context paramContext, AttributeSet paramAttributeSet)
    {
        super(paramContext, paramAttributeSet);
    }

    public ConsumeInsetsFrameLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
    {
        super(paramContext, paramAttributeSet, paramInt);
    }

    protected final boolean fitSystemWindows(Rect paramRect)
    {
        this.mInsets.left = paramRect.left;
        this.mInsets.top = paramRect.top;
        this.mInsets.right = paramRect.right;
        this.mInsets.bottom = paramRect.bottom;
        paramRect.left = 0;
        paramRect.top = 0;
        paramRect.right = 0;
        if (this.mOnInsetsCallback != null)
            this.mOnInsetsCallback.onInsetsChanged(this.mInsets);

        return super.fitSystemWindows(paramRect);
    }

    public void setOnInsetsCallback(OnInsetsCallback paramOnInsetsCallback)
    {
        this.mOnInsetsCallback = paramOnInsetsCallback;
    }

    public static abstract interface OnInsetsCallback
    {
        public abstract void onInsetsChanged(Rect paramRect);
    }
}
