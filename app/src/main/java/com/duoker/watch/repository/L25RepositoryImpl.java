package com.duoker.watch.repository;

import android.content.Context;
import android.text.TextUtils;

import com.duoker.watch.model.CheckAppVersionBean;
import com.duoker.watch.model.CheckAppVersionModel;
import com.duoker.watch.model.PhoneBalanceBean;
import com.duoker.watch.model.ResetPasswordModel;
import com.duoker.watch.network.L25Constants;
import com.duoker.watch.network.utils.OkHttpUtils;
import com.duoker.watch.storage.RegisterModel;
import com.duoker.watch.utils.GsonTools;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.ResponseBody;

/**
 * Created by cheng on 2017/9/3.
 */

public class L25RepositoryImpl implements L25Repository
{
    private final Context mContext;

    public L25RepositoryImpl(Context ctx)
    {
        this.mContext = ctx.getApplicationContext();
    }

    public CheckAppVersionBean checkAppVersion(CheckAppVersionModel checkAppVersionModel)
            throws IOException
    {
        FormBody localFormBody = new FormBody.Builder().
                add("action", "checkappversion").
                add("userid", checkAppVersionModel.getUserid()).
                add("osType", Integer.toString(checkAppVersionModel.getOsType())).
                add("brand", checkAppVersionModel.getBrand()).
                add("appVerNo", Integer.toString(checkAppVersionModel.getAppVerNo())).
                add("token", checkAppVersionModel.getToken()).build();
        
        Request localRequest = new Request.Builder().url(L25Constants.SERVER_ADDRESS + "/app").post(localFormBody).build();
        ResponseBody responseBody = OkHttpUtils.getInstance().enqueue(localRequest);
        String str = responseBody.string();
        responseBody.close();

        return (CheckAppVersionBean)GsonTools.changeGsonToBean(str, CheckAppVersionBean.class);
    }

    public PhoneBalanceBean queryPhoneBalance(String userid, String watchid)  throws IOException
    {
        if ((TextUtils.isEmpty(userid)) || (TextUtils.isEmpty(watchid)))
            return null;
        FormBody localFormBody = new FormBody.Builder().
                add("action", "phoneBalance").
                add("userid", userid).
                add("watchid", watchid).
                add("token", "token").
                build();
        Request localRequest = new Request.Builder().url(L25Constants.SERVER_ADDRESS + "/app.php").post(localFormBody).build();
        ResponseBody localResponseBody = OkHttpUtils.getInstance().enqueue(localRequest);
        String str = localResponseBody.string();
        localResponseBody.close();

        return (PhoneBalanceBean) GsonTools.changeGsonToBean(str, PhoneBalanceBean.class);
    }

    public void register(RegisterModel registerModel)  throws IOException
    {
        if (registerModel == null)
            return;
        String phone = registerModel.getRegisterUserName();
        String password = registerModel.getPassword();
        String name = registerModel.getUserName();
        String headImage = registerModel.getHeadImage();
        if (TextUtils.isEmpty(headImage))
            headImage = "";

        FormBody body = new FormBody.Builder().
                add("phone", phone).
                add("pwd", password).
                add("userName", name).
                add("headImage", headImage).
                add("token", "").
                add("code", registerModel.getCode()).
                build();
        Request request = new Request.Builder().url(L25Constants.SERVER_ADDRESS + "/user/regist.gz").post(body).build();
        OkHttpUtils.getInstance().enqueue(request).close();
    }

    public void registerPush(String userid, String identify) throws IOException
    {
        if ((TextUtils.isEmpty(userid)) || (TextUtils.isEmpty(identify)))
            return;
        FormBody localFormBody = new FormBody.Builder().add("action", "").
                add("action", "setdevidentity").
                add("userid", userid).
                add("identity", identify).
                add("devtype", "12").add("lan", "2052").
                add("token", "").build();
        Request localRequest = new Request.Builder().url(L25Constants.SERVER_ADDRESS + "/app").post(localFormBody).build();
        OkHttpUtils.getInstance().enqueue(localRequest).close();
    }

    public void requestPollPositionToServer(String userid, String watchid) throws IOException
    {
        if ((TextUtils.isEmpty(userid)) || (TextUtils.isEmpty(watchid)))
            return;
        FormBody localFormBody = new FormBody.Builder().
                add("action", "pollPositionToServer").
                add("userid", userid).
                add("watchid", watchid).
                add("token", "token").build();
        Request localRequest = new Request.Builder().url(L25Constants.SERVER_ADDRESS + "/app").post(localFormBody).build();
        OkHttpUtils.getInstance().enqueue(localRequest).close();
    }

    public void requestRegVerifyCode(String phone) throws IOException
    {
        if (TextUtils.isEmpty(phone))
            return;
        FormBody formBody = new FormBody.Builder().add("action", "getregverifycode").add("phone", phone).add("token", "token").build();
        Request request = new Request.Builder().url(L25Constants.SERVER_ADDRESS + "/app").post(formBody).build();
        OkHttpUtils.getInstance().enqueue(request).close();
    }

    public void requestVerifyCode(String phone)  throws IOException
    {
        if (TextUtils.isEmpty(phone))
            return;
        FormBody formBody = new FormBody.Builder().
                add("action", "getverifycode").
                add("phone", phone).
                add("token", "token").
                build();
        Request request = new Request.Builder().url(L25Constants.SERVER_ADDRESS + "/app").post(formBody).build();
        OkHttpUtils.getInstance().enqueue(request).close();
    }

    public void resetPassword(ResetPasswordModel resetPasswordModel) throws IOException
    {
        if (resetPasswordModel == null)
            return;
        String phone = resetPasswordModel.getResetUserName();
        String code = resetPasswordModel.getCode();
        String password = resetPasswordModel.getPassword();
        FormBody formBody = new FormBody.Builder().
                add("action", "resetpwd").
                add("phone", phone).
                add("code", code).
                add("password", password).
                add("token", "token").
                build();
        Request localRequest = new Request.Builder().url(L25Constants.SERVER_ADDRESS + "/app.php").post(formBody).build();
        OkHttpUtils.getInstance().enqueue(localRequest).close();
    }
}