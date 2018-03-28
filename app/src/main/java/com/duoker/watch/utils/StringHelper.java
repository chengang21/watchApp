package com.duoker.watch.utils;

import java.io.UnsupportedEncodingException;

/**
 * Created by cheng on 2017/9/11.
 */

public class StringHelper {

    public static void test1() throws UnsupportedEncodingException
    {
        //String永远是unicode编码
        System.out.println("字节按编码转成字符:");

        String strUtf8Hex = "E4B8ADE69687"; //“中文”的utf8的16进制编码
        byte byteUtf8[] = hex2byte(strUtf8Hex); //转成字节流
        String strString1 = new String(byteUtf8,"UTF-8");//!!!后面必须跟UTF-8，否则编码错误
        System.out.println(strString1);//输出“中文”

        String strUtf16BeHex = "4E2D6587"; //“中文”的unicode Big Endian（最低地址存放高位字节）的16进制编码
        byte byteUtf16Be[] = hex2byte(strUtf16BeHex); //转成字节流
        String strString2 = new String(byteUtf16Be,"UTF-16BE"); //!!!后面必须跟UTF-16BE，否则编码错误
        System.out.println(strString2);//输出“中文”

        String strUtf16LeHex = "2D4E8765"; //“中文”的unicode Little Endian的16进制编码
        byte byteUtf16Le[] = hex2byte(strUtf16LeHex); //转成字节流
        String strString3 = new String(byteUtf16Le,"UTF-16LE"); //!!!后面必须跟UTF-16LE，否则编码错误
        System.out.println(strString3);//输出“中文”
        System.out.println(strString1.equals(strString2));//true
        System.out.println(strString1.equals(strString3));//true
        System.out.println();

        System.out.println("原UTF-16LE的" + strUtf16LeHex + ",错误转码，再转回：");
        String strErr_From_Utf16Le_To_Utf8 = new String(byteUtf16Le,"UTF-8"); //按utf8生成了错误string，有可能不可逆

        byte[] badReturn1 = getBytesArray(strErr_From_Utf16Le_To_Utf8,"UTF-8");//即使再按utf8也无法读出正确内容
        System.out.println(byte2hex(badReturn1));
        byte[] badReturn2 = getBytesArray(strErr_From_Utf16Le_To_Utf8,"UTF-16LE");//无法读出原内容
        System.out.println(byte2hex(badReturn2));
        byte[] badReturn3 = getBytesArray(strErr_From_Utf16Le_To_Utf8,"iso8859-1");//无法读出原内容
        System.out.println(byte2hex(badReturn3));
        byte[] badReturn4 = getBytesArray(strErr_From_Utf16Le_To_Utf8,"UTF-16BE");//无法读出原内容
        System.out.println(byte2hex(badReturn4));
        System.out.println("------------------------");
    }
    public static void test2() throws UnsupportedEncodingException
    {
        String source= "中国";

        String charSet1 = "utf-8";
        byte b1[] = getBytesArray(source, charSet1);
        System.out.println("'" + source + "'" + "的" + charSet1 + "编码:" + byte2hex(b1));
        String charSet2 = "UTF-16BE";
        byte b2[] = getBytesArray(source, charSet2);
        System.out.println("'" + source + "'" + "的" + charSet2 + "编码:" + byte2hex(b2));

        String charSet3= "UTF-16LE";
        byte b3[] = getBytesArray(source, charSet3);
        System.out.println("'" + source + "'" + "的" + charSet3 + "编码:" + byte2hex(b3));

        System.out.println("------------------------");
    }

    public static void main(String[] args)  throws UnsupportedEncodingException{
        test1();
        test2();
    }

    public static byte[] getBytesArray(String str,String changeToCharset)
            throws UnsupportedEncodingException {
        return str.getBytes(changeToCharset);
    }

    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
        }
        return hs.toUpperCase();
    }


    public static byte[] hex2byte(String content) {
        byte[] mtemp=new byte[content.length()/2];
        int j=0;
        for (int n=0; n<content.length(); n=n+2) {
            String sTemp=content.substring(n,n+2);
            mtemp[j]=(byte)Integer.parseInt(sTemp, 16);
            j++;
        }
        return mtemp;
    }
}
