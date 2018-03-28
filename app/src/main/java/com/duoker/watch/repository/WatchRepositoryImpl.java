package com.duoker.watch.repository;

import android.content.Context;
import android.text.TextUtils;

import com.duoker.watch.DuokerWatchApp;
import com.duoker.watch.db.model.AgreeBindBean;
import com.duoker.watch.db.model.ApplyBindBean;
import com.duoker.watch.db.model.CheckWatchBean;
import com.duoker.watch.db.model.DoAgreeBindModel;
import com.duoker.watch.db.model.WatchsBean;
import com.duoker.watch.model.DoApplyBindModel;
import com.duoker.watch.storage.WatchsStorage;
import com.duoker.watch.network.L25Constants;
import com.duoker.watch.network.utils.OkHttpUtils;
import com.duoker.watch.utils.GsonTools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.ResponseBody;

/**
 * Created by chengang on 4/30/2017.
 */
public class WatchRepositoryImpl implements WatchRepository {
    private final Context mContext;

    public WatchRepositoryImpl(Context paramContext) {
        this.mContext = paramContext.getApplicationContext();
    }

    public void add(String userid, String watchid, String pwd) throws IOException {
        if ((TextUtils.isEmpty(userid)) || (TextUtils.isEmpty(watchid)) || (TextUtils.isEmpty(pwd)))
            return;
        FormBody formBody = new FormBody.Builder().
                add("action", "addwatch").
                add("userid", userid).
                add("watchid", watchid).
                add("pwd", pwd).
                add("token", "token").build();

        Request request = new Request.Builder().url(L25Constants.SERVER_ADDRESS + "/app").post(formBody).build();
        OkHttpUtils.getInstance().enqueue(request).close();
    }

    public AgreeBindBean agreeBind(DoAgreeBindModel doAgreeBindModel) throws IOException {
        FormBody formBody = new FormBody.Builder().
                add("action", "agreebind").
                add("userid", doAgreeBindModel.getUserid()).
                add("watchid", doAgreeBindModel.getWatchId()).
                add("bindUserid", doAgreeBindModel.getBindUserid()).
                add("status", Integer.toString(doAgreeBindModel.getStatus())).
                add("token", "token").
                build();

        Request request = new Request.Builder().url(L25Constants.SERVER_ADDRESS + "/app").post(formBody).build();
        ResponseBody responseBody = OkHttpUtils.getInstance().enqueue(request);
        String str = responseBody.string();
        responseBody.close();

        return (AgreeBindBean) GsonTools.changeGsonToBean(str, AgreeBindBean.class);
    }

    public ApplyBindBean applyBind(DoApplyBindModel applyBindModel) throws IOException {
        FormBody formBody = new FormBody.Builder().
                add("action", "applybind").
                add("userid", applyBindModel.getUserid()).
                add("watchid", applyBindModel.getWatchId()).
                add("token", "token").build();
        Request request = new Request.Builder().url(L25Constants.SERVER_ADDRESS + "/app").post(formBody).build();
        ResponseBody responseBody = OkHttpUtils.getInstance().enqueue(request);
        String str = responseBody.string();
        responseBody.close();
        return (ApplyBindBean) GsonTools.changeGsonToBean(str, ApplyBindBean.class);
    }

    public void bindSimCard(String userid, String watchid, String simcard) throws IOException {
        if ((TextUtils.isEmpty(userid)) || (TextUtils.isEmpty(watchid)) || (TextUtils.isEmpty(simcard)))
            return;
        FormBody formBody = new FormBody.Builder().
                add("action", "bindsimcard").
                add("userid", userid).
                add("watchid", watchid).
                add("simcard", simcard).
                add("token", "token").
                build();
        Request request = new Request.Builder().url(L25Constants.SERVER_ADDRESS + "/app.php").post(formBody).build();
        OkHttpUtils.getInstance().enqueue(request).close();
    }

    public CheckWatchBean checkWatch(String userid, String watchid) throws IOException {
        if ((TextUtils.isEmpty(userid)) || (TextUtils.isEmpty(watchid)))
            return null;
        FormBody formBody = new FormBody.Builder().
                add("action", "checkwatch").
                add("userid", userid).
                add("watchid", watchid).
                add("token", "token").
                build();
        Request request = new Request.Builder().url(L25Constants.SERVER_ADDRESS + "/app.php").post(formBody).build();
        ResponseBody responseBody = OkHttpUtils.getInstance().enqueue(request);
        String str = responseBody.string();
        responseBody.close();
        return (CheckWatchBean) GsonTools.changeGsonToBean(str, CheckWatchBean.class);
    }

    public void delWatch(String userid, String watchid) throws IOException {
        if ((TextUtils.isEmpty(userid)) || (TextUtils.isEmpty(watchid)))
            return;
        FormBody formBody = new FormBody.Builder().
                add("action", "delwatch").
                add("userid", userid).
                add("watchid", watchid).
                add("token", "token").build();
        Request request = new Request.Builder().url(L25Constants.SERVER_ADDRESS + "/app.php").post(formBody).build();
        OkHttpUtils.getInstance().enqueue(request).close();
    }

    public void edit(String userid, WatchsStorage watchsStorage) throws IOException {
        if ((TextUtils.isEmpty(userid)) || (watchsStorage == null))
            return;
        String watchId = watchsStorage.getWatchId();
        String devname = watchsStorage.getDevname();
        String devsex = watchsStorage.getDevsex();
        String headImage = watchsStorage.getHeadImage();
        FormBody formBody = new FormBody.Builder().
                add("action", "editwatchdetail").
                add("userid", userid).
                add("watchid", watchId).
                add("nikname", devname).
                add("sex", devsex).
                add("headImage", headImage).
                add("token", "token").build();
        Request request = new Request.Builder().url(L25Constants.SERVER_ADDRESS + "/app.php").post(formBody).build();
        OkHttpUtils.getInstance().enqueue(request).close();
    }

    public List<WatchsBean> getWatchList(String userid) throws IOException {
        List<WatchsBean> resultList = null;
        List<WatchsStorage> list;
        String username;
        int i;
        if (TextUtils.isEmpty(userid)) {
            DuokerWatchApp.getInstance().setWatches(null);
            DuokerWatchApp.getInstance().setDefaultWatch(null);
            DuokerWatchApp.getInstance().setDefaultWatchId("", "");
            return resultList;
        }

        FormBody formBody = new FormBody.Builder().
                add("action", "getwatchlistinfo").
                add("userid", userid).
                add("token", "token").
                build();
        Request request = new Request.Builder().url(L25Constants.SERVER_ADDRESS + "/app.php").post(formBody).build();
        ResponseBody responseBody = OkHttpUtils.getInstance().enqueue(request);
        String resp = responseBody.string();
        responseBody.close();

        resultList = GsonTools.changeGsonToList(resp, WatchsBean.class);
        list = watchsBean2WatchesStorage(resultList);

        if ((list == null) || (list.isEmpty())) {
            DuokerWatchApp.getInstance().setWatches(null);
            DuokerWatchApp.getInstance().setDefaultWatch(null);
            DuokerWatchApp.getInstance().setDefaultWatchId(userid, "");
            return resultList;
        }

        username = DuokerWatchApp.getInstance().getLoginUser().getLoginUserName();
        WatchsStorage[] arrayOfWatchsStorage = (WatchsStorage[]) list.toArray(new WatchsStorage[list.size()]);
        DuokerWatchApp.getInstance().setWatches(arrayOfWatchsStorage);

        String defaultWatchId = DuokerWatchApp.getInstance().getDefaultWatchId(username);
        Iterator iterator = list.iterator();

        WatchsStorage watchsStorage = null;
        while (iterator.hasNext()) {
            watchsStorage = (WatchsStorage) iterator.next();
            if (TextUtils.equals(watchsStorage.getWatchId(), defaultWatchId))
                break;
        }
        if (watchsStorage != null)
            DuokerWatchApp.getInstance().setDefaultWatch(watchsStorage);
        else {
            WatchsStorage tmp = (WatchsStorage) list.get(0);
            DuokerWatchApp.getInstance().setDefaultWatch(tmp);
            DuokerWatchApp.getInstance().setDefaultWatchId(username, tmp.getWatchId());
        }
        return resultList;
    }


    public static WatchsStorage watchsBean2WatchesStorage(WatchsBean watchsBean) {
        if (watchsBean == null)
            return null;
        WatchsStorage watchsStorage = new WatchsStorage();
        watchsStorage.setWatchId(watchsBean.getWatchId());
        watchsStorage.setOwner(watchsBean.getOwner());
        watchsStorage.setDevname(watchsBean.getDevname());
        watchsStorage.setDevdistance(watchsBean.getDevdistance());
        watchsStorage.setElectricity(watchsBean.getElectricity());
        watchsStorage.setHeadImage(watchsBean.getHeadImage());
        watchsStorage.setOnline(watchsBean.getOnline());
        watchsStorage.setPhoneIMS(watchsBean.getPhoneIMS());
        watchsStorage.setDevsex(watchsBean.getDevsex());
        watchsStorage.setRss(watchsBean.getRss());
        watchsStorage.setMcc(watchsBean.getMcc());
        watchsStorage.setMnc(watchsBean.getMnc());
        watchsStorage.setLac(watchsBean.getLac());
        watchsStorage.setCellID(watchsBean.getCellID());
        watchsStorage.setGpsQuality(watchsBean.getGpsQuality());
        watchsStorage.setCalcTime(watchsBean.getCalcTime());
        watchsStorage.setLongitude(watchsBean.getLongitude());
        watchsStorage.setLatitude(watchsBean.getLatitude());
        watchsStorage.setAddr(watchsBean.getAddr());
        watchsStorage.setAltitude(watchsBean.getAltitude());
        watchsStorage.setDirectionOffSet(watchsBean.getDirectionOffSet());
        watchsStorage.setDirectionSpeed(watchsBean.getDirectionSpeed());
        watchsStorage.setGroupId(watchsBean.getGroupId());
        watchsStorage.setProjectCate(watchsBean.getProjectCate());
        return watchsStorage;
    }

    public static List<WatchsStorage> watchsBean2WatchesStorage(List<WatchsBean> paramList) {
        List<WatchsStorage> retlist = null;
        if (paramList != null) {
            retlist = new ArrayList();
            Iterator iterator = paramList.iterator();
            while (iterator.hasNext())
                retlist.add(watchsBean2WatchesStorage((WatchsBean) iterator.next()));
        }
        return retlist;
    }
}
