package com.duoker.watch.utils;

/**
 * Created by cheng on 2017/9/3.
 */

import java.security.MessageDigest;

public class MD5Utils
{
    private static final String[] strDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    // ERROR //
    public static String GetMD5Code(String paramString)
    {
        // Byte code:
        //   0: aconst_null
        //   1: astore_1
        //   2: new 10	java/lang/String
        //   5: dup
        //   6: aload_0
        //   7: invokespecial 54	java/lang/String:<init>	(Ljava/lang/String;)V
        //   10: astore_2
        //   11: ldc 56
        //   13: invokestatic 62	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
        //   16: aload_0
        //   17: invokevirtual 66	java/lang/String:getBytes	()[B
        //   20: invokevirtual 70	java/security/MessageDigest:digest	([B)[B
        //   23: invokestatic 74	com/longcos/clean/utils/MD5Utils:byteToString	([B)Ljava/lang/String;
        //   26: astore 4
        //   28: aload 4
        //   30: areturn
        //   31: astore_3
        //   32: aload_3
        //   33: invokevirtual 77	java/security/NoSuchAlgorithmException:printStackTrace	()V
        //   36: aload_1
        //   37: areturn
        //   38: astore_3
        //   39: aload_2
        //   40: astore_1
        //   41: goto -9 -> 32
        //
        // Exception table:
        //   from	to	target	type
        //   2	11	31	java/security/NoSuchAlgorithmException
        //   11	28	38	java/security/NoSuchAlgorithmException
        return "";
    }

    private static String byteToArrayString(byte paramByte)
    {
        int i = paramByte;
        if (i < 0)
            i += 256;
        int j = i / 16;
        int k = i % 16;
        return strDigits[j] + strDigits[k];
    }

    private static String byteToNum(byte paramByte)
    {
        int i = paramByte;
        System.out.println("iRet1=" + i);
        if (i < 0)
            i += 256;
        return String.valueOf(i);
    }

    private static String byteToString(byte[] paramArrayOfByte)
    {
        StringBuffer localStringBuffer = new StringBuffer();
        for (int i = 0; i < paramArrayOfByte.length; i++)
            localStringBuffer.append(byteToArrayString(paramArrayOfByte[i]));
        return localStringBuffer.toString();
    }

    public static String getMD5(String paramString)
    {
        char[] arrayOfChar1 = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102 };
        try
        {
            MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
            localMessageDigest.update(paramString.getBytes());
            byte[] arrayOfByte = localMessageDigest.digest();
            char[] arrayOfChar2 = new char[32];
            int i = 0;
            int j = 0;
            while (i < 16)
            {
                int k = arrayOfByte[i];
                int m = j + 1;
                arrayOfChar2[j] = arrayOfChar1[(0xF & k >>> 4)];
                j = m + 1;
                arrayOfChar2[m] = arrayOfChar1[(k & 0xF)];
                i++;
            }
            String str = new String(arrayOfChar2).toUpperCase();
            return str;
        }
        catch (Exception localException)
        {
        }
        return null;
    }
}