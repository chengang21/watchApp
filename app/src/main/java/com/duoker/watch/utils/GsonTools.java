package com.duoker.watch.utils;

/**
 * Created by chengang on 4/25/2017.
 */

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GsonTools
{
    public static <T> T changeGsonToBean(String str, Class<T> clazz)
    {
        try
        {
            return new Gson().fromJson(str, clazz);
        }
        catch (Exception e)
        {
        }
        return null;
    }

    public static <T> List<T> changeGsonToList(String json, Class<T> clazz)
    {
        return fromJsonArray(json, clazz);
    }

    public static <T> List<T> changeGsonToList2(String json, Class<T> clazz)
    {
        try
        {
            return (List)new Gson().fromJson(json, new TypeToken(){}.getType());
        }
        catch (Exception e)
        {
        }
        return null;
    }

    public static <T> List<Map<String, T>> changeGsonToListMaps(String paramString)
    {
        try
        {
            return (List)new Gson().fromJson(paramString, new TypeToken(){}.getType());
        }
        catch (Exception e)
        {
        }
        return null;
    }

    public static <T> Map<String, T> changeGsonToMaps(String paramString)
    {
        try
        {
            return (Map)new Gson().fromJson(paramString, new TypeToken(){}.getType());
        }
        catch (Exception e)
        {
        }
        return null;
    }

    public static String createGsonString(Object obj)
    {
        return new Gson().toJson(obj);
    }

    public static <T> List<T> fromJsonArray(String json, Class<T> clazz)
    {
        ArrayList list = new ArrayList();;
        try
        {
            Iterator iter = new JsonParser().parse(json).getAsJsonArray().iterator();
            while (iter.hasNext())
            {
                JsonElement element = (JsonElement)iter.next();
                list.add(new Gson().fromJson(element, clazz));
            }
        }
        catch (Exception localException)
        {
            list = null;
        }
        return list;
    }
}