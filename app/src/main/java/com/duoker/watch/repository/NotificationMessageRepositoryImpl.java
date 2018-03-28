package com.duoker.watch.repository;

/**
 * Created by cheng on 2017/10/9.
 */


import android.content.Context;

import com.duoker.watch.db.XUtilsDbHelper;
import com.duoker.watch.model.GetNotificationMessageModel;
import com.duoker.watch.storage.NotificationMessageStorage;

import org.xutils.DbManager;
import org.xutils.common.util.KeyValue;
import org.xutils.db.sqlite.WhereBuilder;

import java.util.ArrayList;
import java.util.List;

public class NotificationMessageRepositoryImpl implements NotificationMessageRepository
{
    private final Context mContext;

    public NotificationMessageRepositoryImpl(Context paramContext)
    {
        this.mContext = paramContext.getApplicationContext();
    }

    public long countMsg(String paramString, List<String> paramList, List<Integer> paramList1)
            throws Exception
    {
        if ((paramList1 != null) && (!paramList1.isEmpty()))
            return XUtilsDbHelper.getDbManager().selector(NotificationMessageStorage.class).where("loginUserName", "=", paramString).and("watchid", "in", paramList).and("mtype", "in", paramList1).orderBy("ctime", true).count();
        return XUtilsDbHelper.getDbManager().selector(NotificationMessageStorage.class).where("loginUserName", "=", paramString).and("watchid", "in", paramList).orderBy("ctime", true).count();
    }

    public long countUnreadMsg(String paramString, List<String> paramList, List<Integer> paramList1, int paramInt)
            throws Exception
    {
        if ((paramList1 != null) && (!paramList1.isEmpty()))
            return XUtilsDbHelper.getDbManager().selector(NotificationMessageStorage.class).where("loginUserName", "=", paramString).and("watchid", "in", paramList).and("mtype", "in", paramList1).and("readFlag", "=", Integer.valueOf(paramInt)).orderBy("ctime", true).count();
        return XUtilsDbHelper.getDbManager().selector(NotificationMessageStorage.class).where("loginUserName", "=", paramString).and("watchid", "in", paramList).and("readFlag", "=", Integer.valueOf(paramInt)).orderBy("ctime", true).count();
    }

    public void del(List<NotificationMessageStorage> paramList)
            throws Exception
    {
        XUtilsDbHelper.getDbManager().delete(paramList);
    }

    public void doReadUnreadMsg(String paramString, List<String> paramList, List<Integer> paramList1)
            throws Exception
    {
        WhereBuilder localWhereBuilder = XUtilsDbHelper.getDbManager().selector(NotificationMessageStorage.class).where("loginUserName", "=", paramString).and("watchid", "in", paramList).and("mtype", "in", paramList1).and("readFlag", "=", Integer.valueOf(1)).orderBy("ctime", true).getWhereBuilder();
        DbManager localDbManager = XUtilsDbHelper.getDbManager();
        KeyValue[] arrayOfKeyValue = new KeyValue[1];
        arrayOfKeyValue[0] = new KeyValue("readFlag", Integer.valueOf(2));
        localDbManager.update(NotificationMessageStorage.class, localWhereBuilder, arrayOfKeyValue);
    }

    public List<NotificationMessageStorage> findPage(GetNotificationMessageModel paramGetNotificationMessageModel)
            throws Exception
    {
        ArrayList localArrayList = new ArrayList();
        List localList = XUtilsDbHelper.getDbManager().selector(NotificationMessageStorage.class).where("loginUserName", "=", paramGetNotificationMessageModel.getLoginUserName()).and("watchid", "in", paramGetNotificationMessageModel.getWatchIds()).and("mtype", "in", paramGetNotificationMessageModel.getTypeList()).orderBy("ctime", true).limit(paramGetNotificationMessageModel.getSize()).offset(paramGetNotificationMessageModel.getStart()).findAll();
        if ((localList != null) && (!localList.isEmpty()))
            for (int i = -1 + localList.size(); i >= 0; i--)
                localArrayList.add((NotificationMessageStorage)localList.get(i));
        return localArrayList;
    }

    public void save(List<NotificationMessageStorage> paramList)
            throws Exception
    {
        XUtilsDbHelper.getDbManager().replace(paramList);
    }

    public List<NotificationMessageStorage> unreadMsg(String paramString, List<String> paramList, int paramInt)
            throws Exception
    {
        ArrayList localArrayList = new ArrayList();
        List localList = XUtilsDbHelper.getDbManager().selector(NotificationMessageStorage.class).where("loginUserName", "=", paramString).and("watchid", "in", paramList).and("readFlag", "=", Integer.valueOf(paramInt)).orderBy("ctime", true).findAll();
        if ((localList != null) && (!localList.isEmpty()))
            for (int i = -1 + localList.size(); i >= 0; i--)
                localArrayList.add((NotificationMessageStorage)localList.get(i));
        return localArrayList;
    }

    public List<NotificationMessageStorage> unreadMsg(String paramString, List<String> paramList, List<Integer> paramList1, int paramInt)
            throws Exception
    {
        ArrayList localArrayList = new ArrayList();
        List localList = XUtilsDbHelper.getDbManager().selector(NotificationMessageStorage.class).where("loginUserName", "=", paramString).and("watchid", "in", paramList).and("mtype", "in", paramList1).and("readFlag", "=", Integer.valueOf(paramInt)).orderBy("ctime", true).findAll();
        if ((localList != null) && (!localList.isEmpty()))
            for (int i = -1 + localList.size(); i >= 0; i--)
                localArrayList.add((NotificationMessageStorage)localList.get(i));
        return localArrayList;
    }

    public void update(NotificationMessageStorage paramNotificationMessageStorage)
            throws Exception
    {
        XUtilsDbHelper.getDbManager().update(paramNotificationMessageStorage, new String[0]);
    }
}