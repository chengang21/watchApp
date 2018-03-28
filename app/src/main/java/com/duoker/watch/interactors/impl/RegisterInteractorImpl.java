package com.duoker.watch.interactors.impl;

import android.text.TextUtils;

import com.duoker.watch.interactors.base.SimpleInteractor;
import com.duoker.watch.network.L25Constants;
import com.duoker.watch.network.utils.OkHttpUtils;
import com.duoker.watch.storage.RegisterModel;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.ResponseBody;

/**
 * Created by cheng on 2017/9/3.
 */
public class RegisterInteractorImpl extends SimpleInteractor<Object>
{
    private final String mAvatarFilePath;
    private final RegisterModel mRegisterUser;

    public RegisterInteractorImpl(RegisterModel registerModel, String avatarPath )
    {
        mRegisterUser = registerModel;
        mAvatarFilePath = avatarPath;
    }

    @Override
    public void run()
    {
        try
        {
            mRegisterUser.setHeadImage("");
            String ret = register(mRegisterUser);
            postObject2UiThread(ret);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            postException2UiThread(e);
        }
    }

    public String register(RegisterModel registerModel)  throws IOException
    {
        if (registerModel == null)
            return null;

        String phone = registerModel.getRegisterUserName();
        String password = registerModel.getPassword();
        String name = registerModel.getUserName();
        String headImage = registerModel.getHeadImage();
        if (TextUtils.isEmpty(headImage))
            headImage = "";

        FormBody body = new FormBody.Builder().
                add("action", "register").
                add("phone", phone).
                add("pwd", password).
                add("nickname", name).
                // add("headImage", headImage).
                // add("token", "").
                // add("code", registerModel.getCode()).
                build();
        Request request = new Request.Builder().url(L25Constants.SERVER_ADDRESS + "/app").post(body).build();
        // OkHttpUtils.getInstance().enqueue(request).close();
        ResponseBody resp = OkHttpUtils.getInstance().enqueue(request);
        return resp.string();
    }
}
