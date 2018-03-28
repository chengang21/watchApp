package com.duoker.watch.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by chengang on 5/10/2017.
 */


public class ImageUtils
{
    private ImageUtils()
    {
        throw new AssertionError();
    }

    public static byte[] bitmapToByte(Bitmap paramBitmap)
    {
        if (paramBitmap == null)
            return null;
        ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
        paramBitmap.compress(Bitmap.CompressFormat.PNG, 100, localByteArrayOutputStream);
        return localByteArrayOutputStream.toByteArray();
    }

    public static Drawable bitmapToDrawable(Bitmap paramBitmap)
    {
        if (paramBitmap == null)
            return null;
        return new BitmapDrawable(paramBitmap);
    }

    public static Bitmap byteToBitmap(byte[] paramArrayOfByte)
    {
        if ((paramArrayOfByte == null) || (paramArrayOfByte.length == 0))
            return null;
        return BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramArrayOfByte.length);
    }

    public static Drawable byteToDrawable(byte[] paramArrayOfByte)
    {
        return bitmapToDrawable(byteToBitmap(paramArrayOfByte));
    }

    public static int calculateInSampleSize(BitmapFactory.Options paramOptions, int paramInt1, int paramInt2)
    {
        int i = paramOptions.outHeight;
        int j = paramOptions.outWidth;
        int k = 1;
        int n;
        if ((i > paramInt2) || (j > paramInt1))
        {
            int m = Math.round(i / paramInt2);
            n = Math.round(j / paramInt1);
            if (m < n)
                k = m;
        }
        else
        {
            return k;
        }
        return n;
    }

    public static Bitmap convertToMutable(Bitmap paramBitmap)
    {
        try
        {
            File localFile = new File(Environment.getExternalStorageDirectory() + File.separator + "temp.tmp");
            RandomAccessFile localRandomAccessFile = new RandomAccessFile(localFile, "rw");
            int i = paramBitmap.getWidth();
            int j = paramBitmap.getHeight();
            Bitmap.Config localConfig = paramBitmap.getConfig();
            FileChannel localFileChannel = localRandomAccessFile.getChannel();
            MappedByteBuffer localMappedByteBuffer = localFileChannel.map(FileChannel.MapMode.READ_WRITE, 0L, j * paramBitmap.getRowBytes());
            paramBitmap.copyPixelsToBuffer(localMappedByteBuffer);
            paramBitmap.recycle();
            System.gc();
            paramBitmap = Bitmap.createBitmap(i, j, localConfig);
            localMappedByteBuffer.position(0);
            paramBitmap.copyPixelsFromBuffer(localMappedByteBuffer);
            localFileChannel.close();
            localRandomAccessFile.close();
            localFile.delete();
            return paramBitmap;
        }
        catch (FileNotFoundException localFileNotFoundException)
        {
            localFileNotFoundException.printStackTrace();
            return paramBitmap;
        }
        catch (IOException localIOException)
        {
            localIOException.printStackTrace();
        }
        return paramBitmap;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources paramResources, int paramInt1, int paramInt2, int paramInt3)
    {
        BitmapFactory.Options localOptions = new BitmapFactory.Options();
        localOptions.inJustDecodeBounds = true;
        localOptions.inSampleSize = calculateInSampleSize(localOptions, paramInt2, paramInt3);
        localOptions.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(paramResources, paramInt1, localOptions);
    }

    public static Bitmap decodeSampledBitmapFromUri(Uri paramUri, int paramInt1, int paramInt2)
    {
        BitmapFactory.Options localOptions = new BitmapFactory.Options();
        localOptions.inJustDecodeBounds = true;
        localOptions.inSampleSize = calculateInSampleSize(localOptions, paramInt1, paramInt2);
        localOptions.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(paramUri.getPath(), localOptions);
    }

    public static Bitmap drawableToBitmap(Drawable paramDrawable)
    {
        if (paramDrawable == null)
            return null;
        return ((BitmapDrawable)paramDrawable).getBitmap();
    }

    public static byte[] drawableToByte(Drawable paramDrawable)
    {
        return bitmapToByte(drawableToBitmap(paramDrawable));
    }

    public static Bitmap scaleImage(Bitmap paramBitmap, float paramFloat1, float paramFloat2)
    {
        if (paramBitmap == null)
            return null;
        Matrix localMatrix = new Matrix();
        localMatrix.postScale(paramFloat1, paramFloat2);
        return Bitmap.createBitmap(paramBitmap, 0, 0, paramBitmap.getWidth(), paramBitmap.getHeight(), localMatrix, true);
    }

    public static Bitmap scaleImageTo(Bitmap paramBitmap, int paramInt1, int paramInt2)
    {
        return scaleImage(paramBitmap, paramInt1 / paramBitmap.getWidth(), paramInt2 / paramBitmap.getHeight());
    }
}
