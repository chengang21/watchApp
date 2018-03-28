package com.duoker.watch.storage;

import android.app.Activity;
import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by chengang on 4/25/2017.
 */
public class SerializableUtils {

    private static String makeFileName( String key )
    {
        return "dk_"+ key + ".data";
    }
    public static void saveObject(Context context, String key, Object obj) {

        String fileName = makeFileName(key);
        ObjectOutputStream oos = null;
        try {
            /* 根据用户提供的文件名，以及文件的应用模式，打开一个输出流.文件不存系统会为你创建一个的，
             * 至于为什么这个地方还有FileNotFoundException抛出，我也比较纳闷。在Context中是这样定义的
             *   public abstract FileOutputStream openFileOutput(String name, int mode)
             *   throws FileNotFoundException;
             * openFileOutput(String name, int mode);
             * 第一个参数，代表文件名称，注意这里的文件名称不能包括任何的/或者/这种分隔符，只能是文件名
             *          该文件会被保存在/data/data/应用名称/files/chenzheng_java.txt
             * 第二个参数，代表文件的操作模式
             *          MODE_PRIVATE 私有（只能创建它的应用访问） 重复写入时会文件覆盖
             *          MODE_APPEND  私有   重复写入时会在文件的末尾进行追加，而不是覆盖掉原来的文件
             *          MODE_WORLD_READABLE 公用  可读
             *          MODE_WORLD_WRITEABLE 公用 可读写
             *  */
            FileOutputStream outputStream = context.openFileOutput(fileName, Activity.MODE_PRIVATE);
            oos = new ObjectOutputStream( outputStream );
            oos.writeObject(obj);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    public static Object getObject( Context context, String key ) {

        String fileName = makeFileName(key);

        try {
            FileInputStream inputStream = context.openFileInput(fileName);
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return null;
    }
}
