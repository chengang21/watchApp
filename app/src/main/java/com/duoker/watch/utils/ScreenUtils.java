package com.duoker.watch.utils;

/**
 * Created by chengang on 5/10/2017.
 */


import android.content.Context;

public class ScreenUtils
{
    private ScreenUtils()
    {
        throw new AssertionError();
    }

    public static float dpToPx(Context paramContext, float paramFloat)
    {
        if (paramContext == null)
            return -1.0F;
        return paramFloat * paramContext.getResources().getDisplayMetrics().density;
    }

    public static int dpToPxInt(Context paramContext, float paramFloat)
    {
        return (int)(0.5F + dpToPx(paramContext, paramFloat));
    }

    public static int getStatusBarHeight(Context paramContext)
    {
        int i = paramContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
        int j = 0;
        if (i > 0)
            j = paramContext.getResources().getDimensionPixelSize(i);
        return j;
    }

    public static float pxToDp(Context paramContext, float paramFloat)
    {
        if (paramContext == null)
            return -1.0F;
        return paramFloat / paramContext.getResources().getDisplayMetrics().density;
    }

    public static int pxToDpCeilInt(Context paramContext, float paramFloat)
    {
        return (int)(0.5F + pxToDp(paramContext, paramFloat));
    }
}