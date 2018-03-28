package com.duoker.watch.utils;

/**
 * Created by cheng on 2017/10/6.
 */
import android.content.Context;
import android.os.Vibrator;

public class SystemServiceUtils
{
    private static SystemServiceUtils instance;
    private final Context mContext;

    public SystemServiceUtils(Context paramContext)
    {
        this.mContext = paramContext.getApplicationContext();
    }

    public static SystemServiceUtils getInstance(Context paramContext)
    {
        if (instance == null);
        try
        {
            if (instance == null)
                instance = new SystemServiceUtils(paramContext);
            return instance;
        }
        finally
        {
        }
    }

    public void vibratorOnce()
    {
        vibratorOnce(500);
    }

    public void vibratorOnce(int paramInt)
    {
        Vibrator localVibrator = (Vibrator)this.mContext.getSystemService( Context.VIBRATOR_SERVICE); // "vibrator"
        long[] arrayOfLong = new long[2];
        arrayOfLong[0] = 0L;
        arrayOfLong[1] = paramInt;
        localVibrator.vibrate(arrayOfLong, -1);
    }
}
