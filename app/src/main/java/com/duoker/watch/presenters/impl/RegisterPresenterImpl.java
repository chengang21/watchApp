package com.duoker.watch.presenters.impl;

import android.os.Handler;
import android.text.TextUtils;

import com.duoker.watch.R;
import com.duoker.watch.interactors.base.SimpleCallback;
import com.duoker.watch.interactors.base.SimpleErrorBundle;
import com.duoker.watch.interactors.impl.RegisterInteractorImpl;
import com.duoker.watch.interactors.impl.RequestRegVerifyCodeInteractorImpl;
import com.duoker.watch.presenters.RegisterPresenter;
import com.duoker.watch.repository.L25Repository;
import com.duoker.watch.storage.RegisterModel;

/**
 * Created by cheng on 2017/9/3.
 */

public class RegisterPresenterImpl extends RegisterPresenter
{
    private final RegisterPresenter.View iView;
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
                mTime = 60 ;
                return;
            }
            iView.enableCodeView(false);
            iView.setCodeTime(mTime);
            mHandler.postDelayed(this, 1000L);
        }
    };

    public RegisterPresenterImpl( RegisterPresenter.View paramView, L25Repository repository )
    {
        iView = paramView;
        mL25Repository = repository;
    }

    public void getCode(String paramString1, String paramString2)
    {
        if (TextUtils.isEmpty(paramString2))
        {
            this.iView.showToast(R.string.forget_pass_phone_not_empty);
        }
        this.iView.enableCodeView(false);
        if (TextUtils.equals("+86", paramString1))
            paramString1 = "";
        new RequestRegVerifyCodeInteractorImpl(paramString1 + paramString2, mL25Repository).execute(new SimpleCallback<Object>(){

            @Override
            public void onSuccess(Object paramT) {
                mTime =  60 ;
                mHandler.post(mTimeRunnable);
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


    public void register(String countryCode, String phone, String pwd, String nickname, String validateCode, String avatarPath)
    {
        if (TextUtils.isEmpty(phone))
        {
            this.iView.showToast(R.string.yi_str_input_phonenumber);
            return;
        }
        if (TextUtils.isEmpty(pwd))
        {
            this.iView.showToast(R.string.yi_str_input_user_password );
            return;
        }
        if (TextUtils.isEmpty(nickname))
        {
            this.iView.showToast(R.string.yi_str_input_username);
            return;
        }
//        if (TextUtils.isEmpty(validateCode))
//        {
//            this.iView.showToast(R.string.yi_str_input_validate_code );
//            return;
//        }
//        if (TextUtils.isEmpty(avatarPath))
//        {
//            this.iView.showToast(R.string.yi_str_input_choose_avatar);
//            return;
//        }
        iView.showProgress();

        RegisterModel registerModel = new RegisterModel();
        if (TextUtils.equals("+86", countryCode))
            countryCode = "";
        registerModel.setRegisterUserName(countryCode + phone);
        registerModel.setPassword(pwd);
        registerModel.setUserName(nickname);
        registerModel.setCode(validateCode);

        new RegisterInteractorImpl(registerModel, avatarPath ).execute(new SimpleCallback<Object>(){

                @Override
                public void onSuccess(Object obj) {
                    iView.hideProgress();
                    iView.showToast(R.string.yi_str_register_ok  );
                    iView.goToLogin();
                    iView.finishActivity();
                }

                @Override
                public void onError(SimpleErrorBundle errorBundle) {
                    iView.hideProgress();
                    if (errorBundle != null)
                    {
                        String str = errorBundle.getException().getMessage();
                        if ((str != null) && (str.contains("UserBean EXIST")))
                        {
                            iView.showToast(R.string.yi_str_number_exists );
                            return;
                        }
                    }
                    iView.showToast(R.string.yi_str_register_fail );
                }
        } );
    }
}
