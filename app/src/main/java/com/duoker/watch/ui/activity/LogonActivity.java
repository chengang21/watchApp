package com.duoker.watch.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.duoker.watch.R;
import com.duoker.watch.presenters.LoginPresenter;
import com.duoker.watch.presenters.impl.LoginPresenterImpl;
import com.duoker.watch.repository.SetupInfoRepositoryImpl;
import com.duoker.watch.ui.base.BaseActivity;
import com.duoker.watch.ui.event.SetCountryCodeEvent;
import com.duoker.watch.utils.MultipleClickUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * A login screen that offers login via email/password.
 */
public class LogonActivity extends BaseActivity implements LoginPresenter.View {
    private static final String TAG = LogonActivity.class.getSimpleName();
    private TextView mCountryCodeView;
    private Button mForgetPassView;
    private LoginPresenter mLoginPresenter;
    private Button mLoginView;
    private ImageView mLogoView;
    private EditText mPassEt;
    private EditText mPhoneEt;
    private Button mRegisterView;
    private MultipleClickUtils mToolbarTitleMultiClick;

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_logon);
        EventBus.getDefault().register(this);
        Context ctx = getApplicationContext();
        this.mLoginPresenter = new LoginPresenterImpl(this, getApplicationContext(), new SetupInfoRepositoryImpl(ctx));

        mLogoView = ((ImageView) findViewById(R.id.logo_view));
        mCountryCodeView = ((TextView) findViewById(R.id.country_code_view));
        mPhoneEt = ((EditText) findViewById(R.id.phone_et));
        mPassEt = ((EditText) findViewById(R.id.pass_et));
        mLoginView = ((Button) findViewById(R.id.login_view));
        mRegisterView = ((Button) findViewById(R.id.register_view));
        mForgetPassView = ((Button) findViewById(R.id.forget_pass_view));
        if (false) // SDKUtils.isNeedHide(this)
        {
            mRegisterView.setVisibility(View.GONE);
            mForgetPassView.setVisibility(View.GONE);
        }

        initListener();
        initData();
    }

    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(SetCountryCodeEvent event) {
        if (this.mCountryCodeView != null)
            this.mCountryCodeView.setText(event.phoneCode);
    }

    public void finishActivity() {
        finish();
    }

    public void gotoMain() {
        Intent intent = new Intent(LogonActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void hideLoading() {
        hideProgress();
    }

    public void setPassword(String pwd) {
        mPassEt.setText(pwd);
    }

    public void setPhoneText(String phoneText) {
        mPhoneEt.setText(phoneText);
    }

    public void showLoading() {
        showProgress();
    }

    @Override
    public void showError(String message) {

    }

    public void showToast(@StringRes int resourceID) {
        showShortToast(resourceID);
    }

    public void showToast(CharSequence charSequence) {
        showShortToast(charSequence);
    }

    private void initData() {
    }

    private void initListener() {
        this.mToolbarTitleMultiClick = new MultipleClickUtils( 4,
                new MultipleClickUtils.OnMultipleClickListener() {
                    public void onClickFinish() {
                        Intent intent = new Intent(LogonActivity.this, ServerSettingActivity.class);
                        startActivity(intent);
                    }

                    public void onClickPosition(int cnt1, int cnt2) {
                        int i = cnt2 - cnt1;
                        if (i <= 2)
                            showShortToast("还需要点击：" + i);
                    }
                });

        this.mLogoView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mToolbarTitleMultiClick.multipleClick();
            }
        });
        this.mLoginView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String countrycode = mCountryCodeView.getText().toString();
                String phone = mPhoneEt.getText().toString();
                String password = mPassEt.getText().toString();
                mLoginPresenter.doLogin(countrycode, phone, password);
            }
        });

        this.mRegisterView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(LogonActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        this.mForgetPassView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(LogonActivity.this, ForgetPassActivity.class);
                startActivity(intent);
            }
        });
    }

}

