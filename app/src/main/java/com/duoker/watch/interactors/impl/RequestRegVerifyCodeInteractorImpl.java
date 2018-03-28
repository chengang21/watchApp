package com.duoker.watch.interactors.impl;

import android.text.TextUtils;

import com.duoker.watch.interactors.base.SimpleInteractor;
import com.duoker.watch.network.L25Constants;
import com.duoker.watch.network.utils.OkHttpUtils;
import com.duoker.watch.repository.L25Repository;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Request;

/**
 * Created by cheng on 2017/9/3.
 */
public class RequestRegVerifyCodeInteractorImpl extends SimpleInteractor<Object>
{
    private final L25Repository mL25Repository;
    private final String mPhone;

    public RequestRegVerifyCodeInteractorImpl(String paramString, L25Repository paramL25Repository)
    {
        this.mPhone = paramString;
        this.mL25Repository = paramL25Repository;
    }

    public void run()
    {
        try
        {
            requestRegVerifyCode(mPhone);
            postObject2UiThread(null);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            postException2UiThread(e);
        }
    }

    public void requestRegVerifyCode(String phone) throws IOException
    {
        if (TextUtils.isEmpty(phone))
            return;
        FormBody formBody = new FormBody.Builder().add("action", "getregverifycode").add("phone", phone).add("token", "token").build();
        Request request = new Request.Builder().url(L25Constants.SERVER_ADDRESS + "/app").post(formBody).build();
        OkHttpUtils.getInstance().enqueue(request).close();
    }
}
