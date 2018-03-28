package com.duoker.watch.repository;

import com.duoker.watch.model.GpsDataBean;
import com.duoker.watch.model.QueryGpsDataModel;

import java.io.IOException;
import java.util.List;

/**
 * Created by cheng on 2017/10/9.
 */
public interface GpsDataRepository {
    List<GpsDataBean> getGpsDataList(QueryGpsDataModel queryGpsDataModel) throws IOException;

    String reverseGoogleGeoCode(String lat, String lng) throws IOException;

    void setModeToEmergency(String userid, String watchid) throws IOException;
}
