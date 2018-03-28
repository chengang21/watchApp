package com.duoker.watch.ui.view;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by chengang on 4/24/2017.
 *  * @fun: 屏幕工具类
 */

class DisplayUtil {

    protected static final String TAG = "DisplayUtil";

    /**
     * get screen height of this cellphone
     *
     */
    public static int getMobileHeight(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    /**
     * get screen width of this cellphone
     *
     */
    public static int getMobileWidth(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;

    }

    /**
     * 根据手机的分辨率dp 转成px(像素)
     */
    static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率px(像素) 转成dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    static float sp2px(Context context, float sp){
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return sp * scale;
    }

}
