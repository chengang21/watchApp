package com.duoker.watch.presenters.impl;

import android.content.Context;
import android.text.TextUtils;

import com.duoker.watch.R;
import com.duoker.watch.interactors.base.SimpleCallback;
import com.duoker.watch.interactors.base.SimpleErrorBundle;
import com.duoker.watch.interactors.impl.LoginInteractorImpl;
import com.duoker.watch.model.LoginInfoBean;
import com.duoker.watch.model.LoginModel;
import com.duoker.watch.presenters.LoginPresenter;
import com.duoker.watch.repository.SetupInfoRepository;

import java.io.IOException;

/**
 * Created by cheng on 2017/9/3.
 */
public class LoginPresenterImpl extends LoginPresenter
{
    private final LoginPresenter.View iView;
    private final Context context;
    private final SetupInfoRepository mSetupInfoRepository;

    public LoginPresenterImpl(LoginPresenter.View view, Context ctx, SetupInfoRepository setupInfoRepository)
    {
        iView = view;
        context = ctx;
        mSetupInfoRepository = setupInfoRepository;
    }

    public void doLogin(String countryCode, String phone, String pwd)
    {
        if (TextUtils.isEmpty(phone))
        {
            this.iView.showToast(R.string.yi_str_input_phone_number);
            return;
        }
        if (TextUtils.isEmpty(pwd))
        {
            this.iView.showToast(R.string.yi_str_input_login_psw);
            return;
        }

        this.iView.showProgress();

        LoginModel loginModel = new LoginModel();
        if (TextUtils.equals("+86", countryCode))
            countryCode = "";
        loginModel.setLoginUserName(countryCode + phone);
        loginModel.setPassword(pwd);


        new LoginInteractorImpl(loginModel, context ).execute( new SimpleCallback<LoginInfoBean>() {
            @Override
            public void onSuccess(LoginInfoBean bean) {
                iView.hideProgress();
                if (bean == null)
                {
                    iView.showToast(R.string.yi_str_login_fail);
                    return;
                }
                iView.gotoMain();
                iView.finishActivity();
            }

            @Override
            public void onError(SimpleErrorBundle errorBundle) {
                iView.hideProgress();
                String str;
                if (errorBundle != null)
                {
                    Exception exception = errorBundle.getException();
                    if ((exception instanceof IOException))
                    {
                        str = exception.getMessage();
                        if (str == null)
                        {
                            iView.showToast(R.string.yi_str_login_fail);
                            return ;
                        }
                        else if (!str.contains("refused")) {
                            if (str.contains("Internal Server Error"))
                            {
                                iView.showToast(R.string.yi_str_user_no_exists);
                                return;
                            }
                            else if (str.contains("Unauthorized"))
                            {
                                iView.showToast(R.string.yi_str_password_no_correct);
                                return;
                            }
                        }

                        iView.showToast(R.string.yi_str_login_fail_check_network);
                        return;
                    }
                }
                iView.showToast(R.string.yi_str_login_fail);
            }
        });
    }
}
