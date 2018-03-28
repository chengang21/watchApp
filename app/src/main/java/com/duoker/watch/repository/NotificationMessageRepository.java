package com.duoker.watch.repository;

import com.duoker.watch.model.GetNotificationMessageModel;
import com.duoker.watch.storage.NotificationMessageStorage;

import java.util.List;

/**
 * Created by cheng on 2017/10/9.
 */
public interface NotificationMessageRepository {
    long countMsg(String paramString, List<String> paramList, List<Integer> paramList1) throws Exception;

    long countUnreadMsg(String paramString, List<String> paramList, List<Integer> paramList1, int paramInt) throws Exception;

    void del(List<NotificationMessageStorage> paramList) throws Exception;

    void doReadUnreadMsg(String paramString, List<String> paramList, List<Integer> paramList1) throws Exception;

    List<NotificationMessageStorage> findPage(GetNotificationMessageModel paramGetNotificationMessageModel) throws Exception;

    void save(List<NotificationMessageStorage> paramList) throws Exception;

    List<NotificationMessageStorage> unreadMsg(String paramString, List<String> paramList, int paramInt) throws Exception;

    List<NotificationMessageStorage> unreadMsg(String paramString, List<String> paramList, List<Integer> paramList1, int paramInt) throws Exception;

    void update(NotificationMessageStorage paramNotificationMessageStorage) throws Exception;

}
