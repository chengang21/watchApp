package com.duoker.watch.repository;

/**
 * Created by chengang on 4/25/2017.
 */

import com.duoker.watch.db.model.AgreeBindBean;
import com.duoker.watch.db.model.ApplyBindBean;
import com.duoker.watch.db.model.CheckWatchBean;
import com.duoker.watch.db.model.DoAgreeBindModel;
import com.duoker.watch.db.model.WatchsBean;
import com.duoker.watch.model.DoApplyBindModel;
import com.duoker.watch.storage.WatchsStorage;

import java.io.IOException;
import java.util.List;

public interface WatchRepository {
    void add(String userid, String watchid, String pwd) throws IOException;

    AgreeBindBean agreeBind(DoAgreeBindModel doAgreeBindModel) throws IOException;

    ApplyBindBean applyBind(DoApplyBindModel applyBindModel) throws IOException;

    void bindSimCard(String userid, String watchid, String simcard) throws IOException;

    CheckWatchBean checkWatch(String userid, String watchid) throws IOException;

    void delWatch(String userid, String watchid) throws IOException;

    void edit(String userid, WatchsStorage watchsStorage) throws IOException;

    List<WatchsBean> getWatchList(String userid) throws IOException;
}