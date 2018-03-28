package com.duoker.watch.repository;

/**
 * Created by chengang on 4/25/2017.
 */

import android.text.TextUtils;

import com.duoker.watch.db.model.AllWifiHotSpotBean;
import com.duoker.watch.db.model.SetClassTimeModel;
import com.duoker.watch.db.model.WatchSettingBean;
import com.duoker.watch.db.model.WifiHotSpotBean;
import com.duoker.watch.db.model.WifiHotSpotModel;
import com.duoker.watch.model.SetWifiHotSpotModel;
import com.duoker.watch.network.L25Constants;
import com.duoker.watch.network.utils.OkHttpUtils;
import com.duoker.watch.utils.GsonTools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.ResponseBody;

public class WatchSettingRepositoryImpl implements WatchSettingRepository {
    private void addWifiHotSpotModel2List(List<WifiHotSpotModel> paramList, String userid, String watchid, String rssi) {
        if (!TextUtils.isEmpty(userid)) {
            WifiHotSpotModel wifiHotSpotModel = new WifiHotSpotModel();
            wifiHotSpotModel.setWifiName(userid);
            wifiHotSpotModel.setWifiMac(watchid);
            wifiHotSpotModel.setWifiRssi(rssi);
            paramList.add(wifiHotSpotModel);
        }
    }

    public void allowShutdownDevice(String userid, String watchid, int allow) throws IOException {
        if ((TextUtils.isEmpty(userid)) || (TextUtils.isEmpty(watchid)))
            return;
        FormBody body = new FormBody.Builder().
                add("action", "allowshutdown").
                add("userid", userid).
                add("watchid", watchid).
                add("allowshutdown", Integer.toString(allow)).
                add("token", "token").
                build();
        Request request = new Request.Builder().url(L25Constants.SERVER_ADDRESS + "/app").post(body).build();
        OkHttpUtils.getInstance().enqueue(request).close();
    }

    public void editAlertMode(String userid, String watchid, int mode) throws IOException {
        if ((TextUtils.isEmpty(userid)) || (TextUtils.isEmpty(watchid)))
            return;
        FormBody body = new FormBody.Builder().
                add("action", "editAlertmode").
                add("userid", userid).
                add("watchid", watchid).
                add("alertmode", Integer.toString(mode)).
                add("token", "token").
                build();
        Request request = new Request.Builder().url(L25Constants.SERVER_ADDRESS + "/app.php").post(body).build();
        OkHttpUtils.getInstance().enqueue(request).close();
    }

    public WatchSettingBean getSetting(String userid, String watchid) throws IOException {
        if ((TextUtils.isEmpty(userid)) || (TextUtils.isEmpty(watchid)))
            return null;
        FormBody body = new FormBody.Builder().
                add("action", "getWatchSetting").
                add("userid", userid).
                add("watchid", watchid).
                add("token", "token").
                build();
        Request request = new Request.Builder().url(L25Constants.SERVER_ADDRESS + "/app.php").post(body).build();
        ResponseBody responseBody = OkHttpUtils.getInstance().enqueue(request);
        String str = responseBody.string();
        responseBody.close();
        return (WatchSettingBean) GsonTools.changeGsonToBean(str, WatchSettingBean.class);
    }

    public AllWifiHotSpotBean getWifiHotSpots(String userid, String watchid) throws IOException {
        if ((TextUtils.isEmpty(userid)) || (TextUtils.isEmpty(watchid)))
            return null;

        FormBody body = new FormBody.Builder().
                add("action", "getWifiHotspot").
                add("userid", userid).
                add("watchid", watchid).
                add("token", "token").
                build();
        Request request = new Request.Builder().url(L25Constants.SERVER_ADDRESS + "/app.php").post(body).build();
        ResponseBody responseBody = OkHttpUtils.getInstance().enqueue(request);
        String str = responseBody.string();
        responseBody.close();
        return (AllWifiHotSpotBean) GsonTools.changeGsonToBean(str, AllWifiHotSpotBean.class);
    }

    public List<WifiHotSpotModel> getWifiHotSpots2Model(String userid, String watchid) throws IOException {
        if ((TextUtils.isEmpty(userid)) || (TextUtils.isEmpty(watchid)))
            return  null;

        List<WifiHotSpotModel> retList = new ArrayList();
        WifiHotSpotBean wifiHotSpotBean;

        FormBody body = new FormBody.Builder().
                add("action", "getWifiHotspot2").
                add("userid", userid).
                add("watchid", watchid).
                add("token", "token").
                build();

        Request request = new Request.Builder().url(L25Constants.SERVER_ADDRESS + "/app.php").post(body).build();
        ResponseBody responseBody = OkHttpUtils.getInstance().enqueue(request);
        String resp = responseBody.string();
        responseBody.close();

        return GsonTools.changeGsonToList(resp, WifiHotSpotModel.class);
    }

    public void setClassTime(SetClassTimeModel classTimeModel) throws IOException {
        if (classTimeModel == null)
            return;

        String userid = classTimeModel.getUserid();
        String watchId = classTimeModel.getWatchId();
        if ((TextUtils.isEmpty(userid)) || (TextUtils.isEmpty(watchId)))
            return ;


        FormBody body = new FormBody.Builder().
                add("action", "setclass").
                add("userid", userid).
                add("watchid", watchId).
                add("token", "token").
                add("classenable", Integer.toString(classTimeModel.getClassenable())).
                add("classweek", Integer.toString(classTimeModel.getClassweek())).
                add("classmorningbegin", classTimeModel.getClassmorningbegin()).
                add("classmorningend", classTimeModel.getClassmorningend()).
                add("classafternoonbegin", classTimeModel.getClassafternoonbegin()).
                add("classafternoonend", classTimeModel.getClassafternoonend()).
                build();
        Request request = new Request.Builder().url(L25Constants.SERVER_ADDRESS + "/app.php").post(body).build();
        OkHttpUtils.getInstance().enqueue(request).close();
    }

    public void setDeviceWifiHotSpot(String userid, String watchid, SetWifiHotSpotModel setWifiHotSpotModel) throws IOException {
        if ((TextUtils.isEmpty(userid)) || (TextUtils.isEmpty(watchid)) || (setWifiHotSpotModel == null))
            return;

        FormBody body = new FormBody.Builder().
                add("action", "setdevwifi").
                add("userid", userid).
                add("watchid", watchid).
                add("wifienable", Integer.toString(setWifiHotSpotModel.getWifienable())).
                add("wifissid", setWifiHotSpotModel.getWifissid()).
                add("wifipwd", setWifiHotSpotModel.getWifipwd()).
                add("wifimac", setWifiHotSpotModel.getWifimac()).
                add("token", "token").
                build();
        Request request = new Request.Builder().url(L25Constants.SERVER_ADDRESS + "/app.php").post(body).build();
        OkHttpUtils.getInstance().enqueue(request).close();
    }

    public void setOnlyCallPHB(String userid, String watchid, int onoff) throws IOException {
        if ((TextUtils.isEmpty(userid)) || (TextUtils.isEmpty(watchid)))
            return;

        FormBody body = new FormBody.Builder().
                add("action", "setOnlyCallPHB").
                add("userid", userid).
                add("watchid", watchid).
                add("onoff", Integer.toString(onoff)).
                add("token", "token").
                build();
        Request request = new Request.Builder().url(L25Constants.SERVER_ADDRESS + "/app.php").post(body).build();
        OkHttpUtils.getInstance().enqueue(request).close();
    }

    public void setPowerSaveMode(String userid, String watchid, int onoff) throws IOException {
        if ((TextUtils.isEmpty(userid)) || (TextUtils.isEmpty(watchid)))
            return;
        FormBody body = new FormBody.Builder().
                add("action", "setPowerSaveMode").
                add("userid", userid).
                add("watchid", watchid).
                add("onoff", Integer.toString(onoff)).
                add("token", "token").
                build();
        Request request = new Request.Builder().url(L25Constants.SERVER_ADDRESS + "/app.php").post(body).build();
        OkHttpUtils.getInstance().enqueue(request).close();
    }

    public void setVolume(String userid, String watchid, int volume)
            throws IOException {
        if ((TextUtils.isEmpty(userid)) || (TextUtils.isEmpty(watchid)))
            return;
        FormBody body = new FormBody.Builder().
                add("action", "setVolume").
                add("userid", userid).
                add("watchid", watchid).
                add("volume", Integer.toString(volume)).
                add("token", "token").
                build();

        Request request = new Request.Builder().url(L25Constants.SERVER_ADDRESS + "/app.php").post(body).build();
        ResponseBody responseBody = OkHttpUtils.getInstance().enqueue(request);
        responseBody.string();
        responseBody.close();
    }

    public void shutdownDevice(String userid, String watchid) throws IOException {
        if ((TextUtils.isEmpty(userid)) || (TextUtils.isEmpty(watchid)))
            return;

        FormBody body = new FormBody.Builder().
                add("action", "shutdown").
                add("userid", userid).
                add("watchid", watchid).
                add("token", "token").
                build();
        Request request = new Request.Builder().url(L25Constants.SERVER_ADDRESS + "/app.php").post(body).build();
        OkHttpUtils.getInstance().enqueue(request).close();
    }
}
