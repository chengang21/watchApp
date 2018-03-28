package com.duoker.watch.repository;

import android.text.TextUtils;

import com.duoker.watch.model.HeartRateBean;
import com.duoker.watch.model.StartHeartRateMeasureBean;
import com.duoker.watch.model.StartHeartRateMeasureModel;
import com.duoker.watch.network.L25Constants;
import com.duoker.watch.network.utils.OkHttpUtils;
import com.duoker.watch.utils.GsonTools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.ResponseBody;

/**
 * Created by cheng on 2017/10/8.
 */

public class HeartRateRepositoryImpl implements HeartRateRepository {


    @Override
    public List<HeartRateBean> downloadHeartRate(String userid, String watchid, long calcTime, String range) throws IOException {
        List<HeartRateBean> list ;
        if ((TextUtils.isEmpty(userid)) || (TextUtils.isEmpty(watchid)) || (TextUtils.isEmpty(range))) {
            list = null;
        }
        else
        {
            FormBody formBody = new FormBody.Builder().
                    add("action", "downloadHeartRate").
                    add("userid", userid).add("watchid", watchid).
                    add("calcTime", Long.toString(calcTime)).
                    add("range", range).add("token", "token").
                    build();
            Request request = new Request.Builder().url(L25Constants.SERVER_ADDRESS + "/app").post(formBody).build();
            ResponseBody responseBody = OkHttpUtils.getInstance().enqueue(request);
            String resp = responseBody.string();  // [{"heartRate": "61", "calcTime": "1294898321"}, {"heartRate": "61", "calcTime": "1294898321"}]
            responseBody.close();

            List<HeartRateBean> localList = (List<HeartRateBean>)GsonTools.fromJsonArray(resp, HeartRateBean.class);
            list = new ArrayList();
            if ((localList != null) && (!localList.isEmpty()))
                for (int i = -1 + localList.size(); i >= 0; i--)
                    list.add( localList.get(i));
        }
        return list;
    }

    @Override
    public List<HeartRateBean> downloadHeartRateAfter(String userid, String watchid, long calcTime) throws IOException {
        return downloadHeartRate(userid, watchid, calcTime, "after");
    }

    @Override
    public List<HeartRateBean> downloadHeartRateBefore(String userid, String watchid, long calcTime) throws IOException {
        return downloadHeartRate(userid, watchid, calcTime, "before");
    }

    @Override
    public List<HeartRateBean> getHeartRate(String userid, String watchid, long beginTime, long endTime) throws IOException {
        List<HeartRateBean> list;
        if ((TextUtils.isEmpty(userid)) || (TextUtils.isEmpty(watchid)))
            list = null;
        else {
            FormBody formBody = new FormBody.Builder().
                    add("action", "getHeartRate").
                    add("userid", userid).add("watchid", watchid).
                    add("beginTime", Long.toString(beginTime)).
                    add("endTime", Long.toString(endTime)).
                    add("token", "token").build();

            Request request = new Request.Builder().url(L25Constants.SERVER_ADDRESS + "/app").post(formBody).build();
            ResponseBody responseBody = OkHttpUtils.getInstance().enqueue(request);
            String resp = responseBody.string();
            responseBody.close();

            List<HeartRateBean> localList = (List<HeartRateBean>)GsonTools.fromJsonArray(resp, HeartRateBean.class);
            list = new ArrayList();
            if ((localList != null) && (!localList.isEmpty()))
                for (int i = -1 + localList.size(); i >= 0; i--)
                    list.add(localList.get(i));
        }
        return list;
    }

    @Override
    public StartHeartRateMeasureBean startHeartRate(StartHeartRateMeasureModel model) throws IOException {
        FormBody formBody = new FormBody.Builder().
                add("action", "startHeartRate").
                add("userid", model.getUserid()).
                add("watchid", model.getWatchId()).
                add("token", model.getToken()).
                build();

        Request request = new Request.Builder().url(L25Constants.SERVER_ADDRESS + "/app").post(formBody).build();
        ResponseBody responseBody = OkHttpUtils.getInstance().enqueue(request);
        String resp = responseBody.string();
        responseBody.close();
        return (StartHeartRateMeasureBean) GsonTools.changeGsonToBean(resp, StartHeartRateMeasureBean.class);
    }
}
