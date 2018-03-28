package com.duoker.watch.interactors.impl;

import com.duoker.watch.interactors.base.SimpleInteractor;
import com.duoker.watch.repository.L25Repository;

import java.io.IOException;

/**
 * Created by cheng on 2017/9/3.
 */
public class RequestVerifyCodeInteractorImpl extends SimpleInteractor<Object>
{
    private final L25Repository mL25Repository;
    private final String mPhone;

    public RequestVerifyCodeInteractorImpl(String paramString, L25Repository paramL25Repository)
    {
        this.mPhone = paramString;
        this.mL25Repository = paramL25Repository;
    }

    public void run()
    {
        try
        {
            this.mL25Repository.requestVerifyCode(this.mPhone);
            postObject2UiThread(null);
            return;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            postException2UiThread(e);
        }
    }
}