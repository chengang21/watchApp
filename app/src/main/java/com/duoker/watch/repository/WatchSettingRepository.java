package com.duoker.watch.repository;

/**
 * Created by chengang on 4/25/2017.
 */

import com.duoker.watch.db.model.AllWifiHotSpotBean;
import com.duoker.watch.db.model.SetClassTimeModel;
import com.duoker.watch.db.model.WatchSettingBean;
import com.duoker.watch.db.model.WifiHotSpotModel;
import com.duoker.watch.model.SetWifiHotSpotModel;

import java.io.IOException;
import java.util.List;

public interface WatchSettingRepository {
    void allowShutdownDevice(String userid, String watchid, int paramInt) throws IOException;

    void editAlertMode(String userid, String watchid, int paramInt) throws IOException;

    WatchSettingBean getSetting(String userid, String watchid) throws IOException;

    AllWifiHotSpotBean getWifiHotSpots(String userid, String watchid) throws IOException;

    List<WifiHotSpotModel> getWifiHotSpots2Model(String userid, String watchid) throws IOException;

    void setClassTime(SetClassTimeModel classTimeModel) throws IOException;

    void setDeviceWifiHotSpot(String userid, String watchid, SetWifiHotSpotModel setWifiHotSpotModel) throws IOException;

    void setOnlyCallPHB(String userid, String watchid, int paramInt) throws IOException;

    void setPowerSaveMode(String userid, String watchid, int paramInt) throws IOException;

    void setVolume(String userid, String watchid, int paramInt) throws IOException;

    void shutdownDevice(String userid, String watchid) throws IOException;
}