package com.duoker.watch.repository;

/**
 * Created by cheng on 2017/10/9.
 */

import android.text.TextUtils;

import com.duoker.watch.model.GpsDataBean;
import com.duoker.watch.model.QueryGpsDataModel;
import com.duoker.watch.network.L25Constants;
import com.duoker.watch.network.utils.OkHttpUtils;
import com.duoker.watch.utils.GsonTools;

import java.io.IOException;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.ResponseBody;

public class GpsDataRepositoryImpl implements GpsDataRepository
{
    public List<GpsDataBean> getGpsDataList(QueryGpsDataModel queryGpsDataModel) throws IOException
    {
        if (queryGpsDataModel == null)
            return null;
        String userId = queryGpsDataModel.getUserId();
        String watchId = queryGpsDataModel.getWatchId();
        if ((TextUtils.isEmpty(userId)) || (TextUtils.isEmpty(watchId)))
            return null;

        boolean bydate = queryGpsDataModel.isBydate();
        long beginTime = queryGpsDataModel.getBeginTime();
        long endTime = queryGpsDataModel.getEndTime();
        FormBody formBody = new FormBody.Builder().
                add("action", "getGpsData").
                add("userid", userId).
                add("watchid", watchId).
                add("bydate", Boolean.toString(bydate)).
                add("beginTime", Long.toString(beginTime)).
                add("endTime", Long.toString(endTime)).
                add("token", "token").build();
        Request request = new Request.Builder().url(L25Constants.SERVER_ADDRESS + "/app").post(formBody).build();
        ResponseBody responseBody = OkHttpUtils.getInstance().enqueue(request);
        String resp = responseBody.string();
        responseBody.close();
        return GsonTools.changeGsonToList(resp, GpsDataBean.class);
    }

    public String reverseGoogleGeoCode(String lat, String lng)  throws IOException
    {
        if ((TextUtils.isEmpty(lat)) || (TextUtils.isEmpty(lng)))
            return "";
        String str1 = lat + "," + lng;
        String str2 = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + str1;
        Request localRequest = new Request.Builder().url(str2).get().build();
        ResponseBody localResponseBody = OkHttpUtils.getInstance().enqueue(localRequest);
        String str3 = localResponseBody.string();
        localResponseBody.close();
        String str4 = "";
        try
        {
            int i = str3.indexOf("formatted_address\"");
            int j = str3.indexOf("geometry");
            if ((i <= str3.length()) && (i >= 0))
            {
                if ((j > str3.length()) || (j < 0))
                    return "";
                str4 = str3.substring(i + "formatted_address\"".length(), j);
                if (TextUtils.isEmpty(str4))
                    return "";
                str4 = str4.replaceFirst(":", "");
                str4 = str4.replaceFirst("\"", "");
                str4 = str4.substring(0, str4.lastIndexOf("\""));
                str4 = str4.substring(0, str4.lastIndexOf(","));
                String str5 = str4.substring(0, str4.lastIndexOf("\""));
                return str5;
            }
        }
        catch (Exception localException)
        {
            localException.printStackTrace();
            return str4;
        }
        return "";
    }

    public void setModeToEmergency(String userid, String watchid)
            throws IOException
    {
        if ((TextUtils.isEmpty(userid)) || (TextUtils.isEmpty(watchid)))
            return;
        FormBody formBody = new FormBody.Builder().
                add("action", "setModeToEmergency").
                add("userid", userid).
                add("watchid", watchid).
                add("token", "").build();
        Request request = new Request.Builder().url(L25Constants.SERVER_ADDRESS + "/app").post(formBody).build();
        OkHttpUtils.getInstance().enqueue(request).close();
    }
}