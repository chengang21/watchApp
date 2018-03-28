package com.duoker.watch.ui.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by chengang on 4/23/2017.
 */

public class VerticalSwipeRefreshLayout extends SwipeRefreshLayout
{
    private float mPrevX;
    private int mTouchSlop = 80;

    public VerticalSwipeRefreshLayout(Context paramContext, AttributeSet paramAttributeSet)
    {
        super(paramContext, paramAttributeSet);
    }

    public int getTouchSlop()
    {
        return this.mTouchSlop;
    }

    public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
    {
        /*
        switch (paramMotionEvent.getAction())
        {
            case 1:
            default:
            case 0:
            case 2:
        }
        do
            while (true)
            {
                return super.onInterceptTouchEvent(paramMotionEvent);
                // this.mPrevX = paramMotionEvent.getX();
            }
        //CG while (Math.abs(paramMotionEvent.getX() - this.mPrevX) <= this.mTouchSlop);        */
             return false;
    }

    public void setTouchSlop(int paramInt)
    {
        this.mTouchSlop = paramInt;
    }
}
