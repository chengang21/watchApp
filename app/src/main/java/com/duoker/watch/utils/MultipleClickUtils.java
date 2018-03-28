package com.duoker.watch.utils;

import android.os.Handler;
import android.os.SystemClock;

/**
 * Created by cheng on 2017/8/31.
 */

public class MultipleClickUtils {
    private boolean isStart = false;
    private Handler mHandler = new Handler();
    private long[] mHits;
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            MultipleClickUtils.this.nowClickPosition = 0;
            MultipleClickUtils.this.isStart = false;
        }
    };
    private int nowClickPosition = 0;
    private OnMultipleClickListener onMultipleClickListener;
    private int time = 500;

    public MultipleClickUtils(int hits, OnMultipleClickListener paramOnMultipleClickListener)
    {
        this.mHits = new long[hits];
        this.onMultipleClickListener = paramOnMultipleClickListener;
        this.time = (hits * 250);
    }

    public void multipleClick()
    {
        if (!this.isStart)
        {
            this.isStart = true;
            this.mHandler.postDelayed(this.mRunnable, this.time);
        }
        this.nowClickPosition = (1 + this.nowClickPosition);
        System.arraycopy(this.mHits, 1, this.mHits, 0, -1 + this.mHits.length);
        this.mHits[(-1 + this.mHits.length)] = SystemClock.uptimeMillis();
        if (this.mHits[0] >= SystemClock.uptimeMillis() - this.time)
        {
            this.nowClickPosition = 0;
            this.isStart = false;
            this.mHandler.removeCallbacks(this.mRunnable);
            if (this.onMultipleClickListener != null)
                this.onMultipleClickListener.onClickFinish();
        }
        if (this.onMultipleClickListener!= null)
            this.onMultipleClickListener.onClickPosition(this.nowClickPosition, this.mHits.length);
    }

    public void setTime(int paramInt)
    {
        this.time = paramInt;
    }

    public static interface OnMultipleClickListener
    {
        public void onClickFinish();

        public void onClickPosition(int paramInt1, int paramInt2);
    }
}
