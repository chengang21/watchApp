package com.duoker.watch.utils;

/**
 * Created by cheng on 2017/10/2.
 */


import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;

public class IOUtils {
    private static final int BUFFER_SIZE = 4096;

    public static byte[] readStreamAsBytesArray(InputStream paramInputStream)
            throws IOException {
        if (paramInputStream == null)
            return new byte[0];
        ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
        byte[] arrayOfByte = new byte[2048];
        while (true) {
            int i = paramInputStream.read(arrayOfByte);
            if (i <= -1)
                break;
            localByteArrayOutputStream.write(arrayOfByte, 0, i);
        }
        localByteArrayOutputStream.flush();
        return localByteArrayOutputStream.toByteArray();
    }

    public static byte[] readStreamAsBytesArray(InputStream paramInputStream, int paramInt)
            throws IOException {
        if (paramInputStream == null)
            return new byte[0];
        ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
        byte[] arrayOfByte = new byte[2048];
        int i;
        for (long l = 0L; l < paramInt; l += i) {
            i = paramInputStream.read(arrayOfByte, 0, Math.min(2048, (int) (paramInt - l)));
            if (i <= -1)
                break;
            localByteArrayOutputStream.write(arrayOfByte, 0, i);
        }
        localByteArrayOutputStream.flush();
        return localByteArrayOutputStream.toByteArray();
    }

    public static String readStreamAsString(InputStream is, String charset) throws IOException {
        String ret = "";
        if (is == null) {
            return "";
        }

        StringWriter stringWriter = new StringWriter();
        char[] arrayOfChar = new char[1024];

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is, charset));
            try {
                while (true) {
                    int i = br.read(arrayOfChar);
                    if (i <= 0)
                        break;
                    stringWriter.write(arrayOfChar, 0, i);
                }
            } finally {
                is.close();

                if (br != null)
                    br.close();
                if (stringWriter != null)
                    stringWriter.close();
                ret = stringWriter.toString();
                return ret;
            }
        } finally {

        }
    }

    public static void readStreamToFile(InputStream paramInputStream, File paramFile)
            throws IOException {
        FileOutputStream localFileOutputStream = new FileOutputStream(paramFile);
        byte[] arrayOfByte = new byte[4096];
        while (true) {
            int i = paramInputStream.read(arrayOfByte);
            if (i == -1)
                break;
            localFileOutputStream.write(arrayOfByte, 0, i);
        }
        localFileOutputStream.flush();
        localFileOutputStream.close();
    }

    public static void safeClose(InputStream paramInputStream) {
        if (paramInputStream != null) ;
        try {
            paramInputStream.close();
            return;
        } catch (IOException localIOException) {
        }
    }

    public static void safeClose(OutputStream paramOutputStream) {
        if (paramOutputStream != null) ;
        try {
            paramOutputStream.close();
            return;
        } catch (IOException localIOException) {
        }
    }
}
