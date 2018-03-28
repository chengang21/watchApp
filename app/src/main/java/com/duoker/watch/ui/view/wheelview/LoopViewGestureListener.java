package com.duoker.watch.ui.view.wheelview;

import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by cheng on 2017/10/7.
 */

final class LoopViewGestureListener extends GestureDetector.SimpleOnGestureListener
{
    final LoopView loopView;

    LoopViewGestureListener(LoopView paramLoopView)
    {
        this.loopView = paramLoopView;
    }

    public final boolean onFling(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
    {
        this.loopView.scrollBy(paramFloat2);
        return true;
    }
}
