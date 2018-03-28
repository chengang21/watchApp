package com.duoker.watch.repository;

/**
 * Created by cheng on 2017/9/3.
 */

import com.duoker.watch.model.CheckAppVersionBean;
import com.duoker.watch.model.CheckAppVersionModel;
import com.duoker.watch.model.PhoneBalanceBean;
import com.duoker.watch.model.ResetPasswordModel;
import com.duoker.watch.storage.RegisterModel;

import java.io.IOException;

public interface L25Repository
{
    public CheckAppVersionBean checkAppVersion(CheckAppVersionModel paramCheckAppVersionModel) throws IOException;

    public PhoneBalanceBean queryPhoneBalance(String paramString1, String paramString2) throws IOException;

    public void register(RegisterModel paramRegisterModel) throws IOException;

    public void registerPush(String paramString1, String paramString2) throws IOException;

    public void requestPollPositionToServer(String paramString1, String paramString2) throws IOException;

    public void requestRegVerifyCode(String paramString) throws IOException;

    public void requestVerifyCode(String paramString) throws IOException;

    public void resetPassword(ResetPasswordModel paramResetPasswordModel) throws IOException;
}