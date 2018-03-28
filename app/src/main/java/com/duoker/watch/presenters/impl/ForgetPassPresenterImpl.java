package com.duoker.watch.presenters.impl;

import android.os.Handler;
import android.text.TextUtils;

import com.duoker.watch.R;
import com.duoker.watch.interactors.base.SimpleCallback;
import com.duoker.watch.interactors.base.SimpleErrorBundle;
import com.duoker.watch.interactors.impl.RequestVerifyCodeInteractorImpl;
import com.duoker.watch.interactors.impl.ResetPasswordInteractorImpl;
import com.duoker.watch.model.ResetPasswordModel;
import com.duoker.watch.presenters.ForgetPassPresenter;
import com.duoker.watch.repository.L25Repository;

/**
 * Created by cheng on 2017/9/3.
 */

public class ForgetPassPresenterImpl extends ForgetPassPresenter
{
    private final ForgetPassPresenter.View iView;
    private Handler mHandler = new Handler();
    private final L25Repository mL25Repository;
    private int mTime = 60;
    private Runnable mTimeRunnable = new Runnable() {
        @Override
        public void run() {
            if (mTime == 0)
            {
                iView.enableCodeView(true);
                iView.setCodeTime(mTime);
                mTime = 60;
                return;
            }
            iView.enableCodeView(false);
            iView.setCodeTime(mTime);
            onDestroy();
            mHandler.postDelayed(this, 1000L);
        }
    };

    public ForgetPassPresenterImpl(ForgetPassPresenter.View paramView, L25Repository paramL25Repository)
    {
        this.iView = paramView;
        this.mL25Repository = paramL25Repository;
    }

    public void forgetPass(String paramString1, String paramString2, String paramString3, String paramString4)
    {
        if (TextUtils.isEmpty(paramString2))
        {
            this.iView.showToast(R.string.forget_pass_phone_not_empty);
            return;
        }
        if (TextUtils.isEmpty(paramString4))
        {
            this.iView.showToast(R.string.forget_pass_code_not_empty);
            return;
        }
        if (TextUtils.isEmpty(paramString3))
        {
            this.iView.showToast(R.string.forget_pass_pass_not_empty);
            return;
        }
        ResetPasswordModel localResetPasswordModel = new ResetPasswordModel();
        localResetPasswordModel.setResetUserName(paramString1 + paramString2);
        localResetPasswordModel.setPassword(paramString3);
        localResetPasswordModel.setCode(paramString4);
        new ResetPasswordInteractorImpl(localResetPasswordModel, this.mL25Repository).execute(new SimpleCallback<Object>(){

            @Override
            public void onSuccess(Object paramT) {
                iView.showToast(R.string.forget_pass_phone_reset_ok);
                iView.gotoMain();
                iView.finishActivity();
            }

            @Override
            public void onError(SimpleErrorBundle errorBundle) {
                iView.showToast(R.string.forget_pass_phone_reset_fail);
            }
        });
    }

    public void getCode(String paramString1, String paramString2)
    {
        if (TextUtils.isEmpty(paramString2))
        {
            this.iView.showToast(R.string.forget_pass_phone_not_empty);
            return;
        }
        this.iView.enableCodeView(false);
        if (TextUtils.equals("+86", paramString1))
            paramString1 = "";
        new RequestVerifyCodeInteractorImpl(paramString1 + paramString2, mL25Repository).execute(new SimpleCallback<Object>(){

            @Override
            public void onSuccess(Object paramT) {
                mTime = 60;
                mHandler.post( mTimeRunnable );
                iView.showToast(R.string.hb_str_get_code_ok);
            }

            @Override
            public void onError(SimpleErrorBundle errorBundle) {
                iView.enableCodeView(true);
                iView.showToast(R.string.hb_str_get_code_fail);
            }
        });
    }

    public void onDestroy()
    {
        this.mHandler.removeCallbacks(this.mTimeRunnable);
    }
}
