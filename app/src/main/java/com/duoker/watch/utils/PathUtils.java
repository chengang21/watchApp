package com.duoker.watch.utils;

/**
 * Created by chengang on 5/10/2017.
 */


import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

@SuppressLint({"NewApi"})
public class PathUtils
{
    public static String getDataColumn(Context paramContext, Uri paramUri, String paramString, String[] paramArrayOfString)
    {
        Cursor localCursor = null;
        String[] arrayOfString = { "_data" };
        try
        {
            localCursor = paramContext.getContentResolver().query(paramUri, arrayOfString, paramString, paramArrayOfString, null);
            if ((localCursor != null) && (localCursor.moveToFirst()))
            {
                String str = localCursor.getString(localCursor.getColumnIndexOrThrow("_data"));
                return str;
            }
            return null;
        }
        finally
        {
            if (localCursor != null)
                localCursor.close();
        }
    }

    @SuppressLint({"NewApi"})
    public static String getPath(Context paramContext, Uri paramUri)
    {
        int i;
        String str1;

        //TODO ...
        return paramUri.getPath();
    }

    public static boolean isDownloadsDocument(Uri paramUri)
    {
        return "com.android.providers.downloads.documents".equals(paramUri.getAuthority());
    }

    public static boolean isExternalStorageDocument(Uri paramUri)
    {
        return "com.android.externalstorage.documents".equals(paramUri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri paramUri)
    {
        return "com.google.android.apps.photos.content".equals(paramUri.getAuthority());
    }

    public static boolean isMediaDocument(Uri paramUri)
    {
        return "com.android.providers.media.documents".equals(paramUri.getAuthority());
    }
}