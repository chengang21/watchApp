package com.duoker.watch.utils;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chengang on 5/10/2017.
 */


public class AvatarUploadUtils
{
    public static final String PIC_TEMP_DEFAULT_DIR = "tupai_pic_temp_dir";
    public static final String PIC_TEMP_TEST_FILE = "portrait_file";

    public static File getPortraitFile(Context paramContext)
    {
        File localFile = new File(paramContext.getExternalCacheDir(), "tupai_pic_temp_dir");
        if (!localFile.exists())
            localFile.mkdirs();
        return new File(localFile.getAbsolutePath() + "/" + "portrait_file");
    }

    public static String saveMyBitmap(Context paramContext, Bitmap paramBitmap)
    {
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        String str = localSimpleDateFormat.format(new Date()) + ".png";
        File localFile1 = new File(paramContext.getExternalCacheDir(), "tupai_pic_temp_dir");
        if (!localFile1.exists())
            localFile1.mkdirs();
        File localFile2 = new File(localFile1.getAbsolutePath(), str);
        try
        {
            localFile2.createNewFile();
            FileOutputStream localFileOutputStream = new FileOutputStream(localFile2);
            paramBitmap.compress(Bitmap.CompressFormat.PNG, 100, localFileOutputStream);
            localFileOutputStream.flush();
            localFileOutputStream.close();
            return localFile2.getAbsolutePath();
        }
        catch (IOException localIOException)
        {
            while (true)
                localIOException.printStackTrace();
        }
    }

    public static String saveMyBitmap(Context paramContext, Uri paramUri)
    {
        return saveMyBitmap(paramContext, ImageUtils.decodeSampledBitmapFromUri(paramUri, 300, 300));
    }
}
