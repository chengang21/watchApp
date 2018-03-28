package com.duoker.watch.repository;

import android.text.TextUtils;

import com.duoker.watch.model.MonitorModeBean;
import com.duoker.watch.network.L25Constants;
import com.duoker.watch.network.utils.OkHttpUtils;
import com.duoker.watch.utils.GsonTools;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.ResponseBody;

/**
 * Created by cheng on 2017/10/9.
 */

public class MonitorModeRepositoryImpl implements MonitorModeRepository
{
    public MonitorModeBean setCallToMonitorMode(String userid, String watchid) throws IOException
    {
        if ((TextUtils.isEmpty(userid)) || (TextUtils.isEmpty(watchid)))
            return null;
        FormBody formBody = new FormBody.Builder().
                add("action", "setCallToMonitorMode").
                add("userid", userid).
                add("watchid", watchid).
                add("token", "token").build();
        Request request = new Request.Builder().url(L25Constants.SERVER_ADDRESS + "/app.php").post(formBody).build();
        ResponseBody responseBody = OkHttpUtils.getInstance().enqueue(request);
        String str = responseBody.string();
        responseBody.close();
        return (MonitorModeBean) GsonTools.changeGsonToBean(str, MonitorModeBean.class);
    }
}
