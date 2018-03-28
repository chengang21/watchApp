package com.duoker.watch.utils;

/**
 * Created by cheng on 2017/10/8.
 */


import android.content.Context;
import android.text.TextUtils;

import com.duoker.watch.R;

public class NotifyHelper
{
    public static enum NotifyType
    {
        MUTE,
        SHAKE,
        SHAKE_RING
    }

    private final String MUTE_DESC;
    private final String SHAKE_DESC;
    private final String SHAKE_RING_DESC;
    private final Context mContext;

    public NotifyHelper(Context paramContext)
    {
        this.mContext = paramContext.getApplicationContext();
        this.MUTE_DESC = this.mContext.getString(R.string.watch_sets_notify_mute);
        this.SHAKE_DESC = this.mContext.getString(R.string.watch_sets_notify_shake);
        this.SHAKE_RING_DESC = this.mContext.getString(R.string.watch_sets_notify_shake_ring);
    }

    public String getDescName(int paramInt)
    {
        NotifyType localNotifyType = getNotifyType(paramInt);
        switch (localNotifyType)
        {
            case MUTE:
                return this.MUTE_DESC;
            case SHAKE:
                return this.SHAKE_DESC;
            case SHAKE_RING:
                return this.SHAKE_RING_DESC;
            default:
                return this.MUTE_DESC;
        }
    }

    public String getDescName(NotifyType notifyType)
    {
        switch (notifyType)
        {
            case MUTE:
                return this.MUTE_DESC;
            case SHAKE:
                return this.SHAKE_DESC;
            case SHAKE_RING:
                return this.SHAKE_RING_DESC;
            default:
                return this.MUTE_DESC;
        }
    }

    public int getMode(NotifyType notifyType)
    {
        switch (notifyType)
        {
            case MUTE:
                return 1;
            case SHAKE:
                return 2;
            case SHAKE_RING:
                return 3;
            default:
                return 1;
        }
    }

    public int getMode(String notify)
    {
        if (TextUtils.equals(notify, this.MUTE_DESC))
            return 1;

        if (TextUtils.equals(notify, this.SHAKE_DESC))
            return 2;

        if (TextUtils.equals(notify, this.SHAKE_RING_DESC))
            return 3;

        return 1;
    }

    public NotifyType getNotifyType(int paramInt)
    {
        if (paramInt == 1)
            return NotifyType.MUTE;
        if (paramInt == 2)
            return NotifyType.SHAKE;
        if (paramInt == 3)
            return NotifyType.SHAKE_RING;
        return NotifyType.MUTE;
    }

    public NotifyType getNotifyType(String paramString)
    {
        int i = getMode(paramString);
        if (i == 1)
            return NotifyType.MUTE;
        if (i == 2)
            return NotifyType.SHAKE;
        if (i == 3)
            return NotifyType.SHAKE_RING;
        return NotifyType.MUTE;
    }

}
