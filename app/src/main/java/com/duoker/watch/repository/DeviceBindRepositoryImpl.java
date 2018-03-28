package com.duoker.watch.repository;

/**
 * Created by cheng on 2017/10/9.
 */


import android.content.Context;

import com.duoker.watch.model.BindUserListBean;
import com.duoker.watch.model.DoFactorySettingModel;
import com.duoker.watch.model.DoPermissionToTransferModel;
import com.duoker.watch.model.DoUnbindWatchModel;
import com.duoker.watch.model.FactorySettingBean;
import com.duoker.watch.model.GetBindUserListModel;
import com.duoker.watch.model.PermissionToTransferBean;
import com.duoker.watch.model.UnbindWatchBean;
import com.duoker.watch.network.L25Constants;
import com.duoker.watch.network.utils.OkHttpUtils;
import com.duoker.watch.utils.GsonTools;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.ResponseBody;

public class DeviceBindRepositoryImpl implements DeviceBindRepository {
    private final Context mContext;

    public DeviceBindRepositoryImpl(Context paramContext) {
        this.mContext = paramContext.getApplicationContext();
    }

    public FactorySettingBean factorySetting(DoFactorySettingModel factorySettingModel) throws IOException {
        FormBody localFormBody = new FormBody.Builder().
                add("action", "factorySetting").
                add("userid", factorySettingModel.getUserid()).
                add("watchid", factorySettingModel.getWatchid()).
                add("token", factorySettingModel.getToken()).build();
        Request localRequest = new Request.Builder().url(L25Constants.SERVER_ADDRESS + "/app.php").post(localFormBody).build();
        ResponseBody localResponseBody = OkHttpUtils.getInstance().enqueue(localRequest);
        String str = localResponseBody.string();
        localResponseBody.close();
        return (FactorySettingBean) GsonTools.changeGsonToBean(str, FactorySettingBean.class);
    }

    public BindUserListBean getBindUserList(GetBindUserListModel bindUserListModel) throws IOException {
        FormBody localFormBody = new FormBody.Builder().
                add("action", "bindUserList").
                add("userid", bindUserListModel.getUserid()).
                add("watchid", bindUserListModel.getWatchid()).
                add("token", bindUserListModel.getToken()).build();

        Request request = new Request.Builder().url(L25Constants.SERVER_ADDRESS + "/app.php").post(localFormBody).build();
        ResponseBody responseBody = OkHttpUtils.getInstance().enqueue(request);
        String str = responseBody.string();
        responseBody.close();

        return (BindUserListBean) GsonTools.changeGsonToBean(str, BindUserListBean.class);
    }

    public PermissionToTransferBean permissionToTransfer(DoPermissionToTransferModel paramDoPermissionToTransferModel) throws IOException {
        FormBody localFormBody = new FormBody.Builder().
                add("action", "permissionToTransfer").
                add("userid", paramDoPermissionToTransferModel.getUserid()).
                add("watchid", paramDoPermissionToTransferModel.getWatchid()).
                add("newUid", paramDoPermissionToTransferModel.getNewUid()).
                add("pwd", paramDoPermissionToTransferModel.getPwd()).
                add("token", paramDoPermissionToTransferModel.getToken()).build();

        Request localRequest = new Request.Builder().url(L25Constants.SERVER_ADDRESS + "/app.php").post(localFormBody).build();
        ResponseBody localResponseBody = OkHttpUtils.getInstance().enqueue(localRequest);
        String str = localResponseBody.string();
        localResponseBody.close();
        return (PermissionToTransferBean) GsonTools.changeGsonToBean(str, PermissionToTransferBean.class);
    }

    public UnbindWatchBean unbindWatch(DoUnbindWatchModel unbindWatchModel) throws IOException {
        FormBody localFormBody = new FormBody.Builder().
                add("action", "unbindwatch").
                add("userid", unbindWatchModel.getUserid()).
                add("deluid", unbindWatchModel.getDeluid()).
                add("watchid", unbindWatchModel.getWatchId()).
                add("token", unbindWatchModel.getToken()).build();

        Request request = new Request.Builder().url(L25Constants.SERVER_ADDRESS + "/app.php").post(localFormBody).build();
        ResponseBody responseBody = OkHttpUtils.getInstance().enqueue(request);
        String str = responseBody.string();
        responseBody.close();
        return (UnbindWatchBean) GsonTools.changeGsonToBean(str, UnbindWatchBean.class);
    }
}
