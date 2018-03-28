package com.duoker.watch.utils;

import android.telephony.SmsManager;
import android.text.TextUtils;

import java.util.Iterator;

/**
 * Created by cheng on 2017/10/8.
 */

public class AppUtils {
    public static boolean checkIsCDMA(String paramString)
    {
        boolean bool;
        if (TextUtils.isEmpty(paramString))
            bool = false;
        else
        {
            char[] arrayOfChar = paramString.toCharArray();
            int i = paramString.length();
            bool = false;
            if (i <= 14)
                bool = true;
            for (int j = 0; j < i; j++)
            {
                if ((arrayOfChar[j] <= 'Z') && (arrayOfChar[j] >= 'A'))
                    bool = true;
                if ((arrayOfChar[j] <= 'z') && (arrayOfChar[j] >= 'a'))
                    bool = true;
            }
        }
        return bool;
    }

    public static void sendSMS(String paramString1, String paramString2)
    {
        if (TextUtils.isEmpty(paramString1));
        {
            SmsManager smsManager = SmsManager.getDefault();
            Iterator iterator = smsManager.divideMessage(paramString2).iterator();
            while (iterator.hasNext())
                smsManager.sendTextMessage(paramString1, null, (String)iterator.next(), null, null);
        }
    }
}
