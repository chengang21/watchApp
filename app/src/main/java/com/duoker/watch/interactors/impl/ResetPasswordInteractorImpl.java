package com.duoker.watch.interactors.impl;

import com.duoker.watch.interactors.base.SimpleInteractor;
import com.duoker.watch.model.ResetPasswordModel;
import com.duoker.watch.repository.L25Repository;

import java.io.IOException;

/**
 * Created by cheng on 2017/9/3.
 */
public class ResetPasswordInteractorImpl  extends SimpleInteractor<Object>
{
    private final L25Repository mL25Repository;
    private final ResetPasswordModel mResetPasswordModel;

    public ResetPasswordInteractorImpl(ResetPasswordModel paramResetPasswordModel, L25Repository paramL25Repository)
    {
        this.mResetPasswordModel = paramResetPasswordModel;
        this.mL25Repository = paramL25Repository;
    }

    public void run()
    {
        try
        {
            this.mL25Repository.resetPassword(this.mResetPasswordModel);
            postObject2UiThread(null);
        }
        catch (IOException localIOException)
        {
            localIOException.printStackTrace();
            postException2UiThread(localIOException);
        }
    }
}
