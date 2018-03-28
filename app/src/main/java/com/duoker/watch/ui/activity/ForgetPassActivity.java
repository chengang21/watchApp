package com.duoker.watch.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.duoker.watch.R;
import com.duoker.watch.presenters.ForgetPassPresenter;
import com.duoker.watch.presenters.impl.ForgetPassPresenterImpl;
import com.duoker.watch.repository.L25RepositoryImpl;
import com.duoker.watch.ui.base.BaseActivity;
import com.duoker.watch.ui.event.SetCountryCodeEvent;
import com.duoker.watch.ui.view.MyToolbar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by cheng on 2017/9/3.
 */
public class ForgetPassActivity extends BaseActivity implements ForgetPassPresenter.View {

    public void enableCodeView(boolean paramAnonymousBoolean) {
        if (mGetCodeView != null)
            mGetCodeView.setEnabled(paramAnonymousBoolean);
    }

    public void finishActivity() {
        finish();
    }

    public void gotoMain() {
        Intent localIntent = new Intent(ForgetPassActivity.this, LoginActivity.class);
        startActivity(localIntent);
    }

    public void hideLoading() {
        hideProgress();
    }

    public void setCodeTime(int paramAnonymousInt) {
        if (mGetCodeView != null) {
            if (paramAnonymousInt != 0) {
                StringBuilder localStringBuilder = new StringBuilder(getString(R.string.hb_str_get_validate)).append("(").append(paramAnonymousInt).append(")");
                mGetCodeView.setText(localStringBuilder.toString());
            }
        } else
            return;
        mGetCodeView.setText(getString(R.string.hb_str_get_validate));
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

    private TextView mCountryCodeView;
    private ForgetPassPresenterImpl mForgetPassPresenter;
    private TextView mGetCodeView;
    private EditText mPassEt;
    private EditText mPhoneEt;
    private Button mSureView;
    private MyToolbar mToolbarLayout;
    private ImageView mToolbarMenuBack;
    private EditText mValidateEt;

    private void initListener() {
        this.mCountryCodeView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Intent localIntent = new Intent(ForgetPassActivity.this, CountryCodeActivity.class);
                // startActivity(localIntent);
            }
        });
        this.mGetCodeView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String str1 = mCountryCodeView.getText().toString();
                String str2 = mPhoneEt.getText().toString();
                mForgetPassPresenter.getCode(str1, str2);
            }
        });
        this.mSureView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String str1 = mCountryCodeView.getText().toString();
                String str2 = mPhoneEt.getText().toString();
                String str3 = mPassEt.getText().toString();
                String str4 = mValidateEt.getText().toString();
                mForgetPassPresenter.forgetPass(str1, str2, str3, str4);
            }
        });
    }

    private void initToolbar() {
        this.mToolbarLayout = ((MyToolbar) findViewById(R.id.toolbar_layout));
        this.mToolbarMenuBack = ((ImageView) findViewById( R.id.toolbar_menu_back ));
        this.mToolbarMenuBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
 

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView( R.layout.activity_forget_password );
        EventBus.getDefault().register(this);
        this.mForgetPassPresenter = new ForgetPassPresenterImpl(this, new L25RepositoryImpl(getApplicationContext()));
        initToolbar();
        
        this.mCountryCodeView = ((TextView) findViewById(R.id.country_code_view));
        this.mPhoneEt = ((EditText) findViewById(R.id.phone_et));
        this.mValidateEt = ((EditText) findViewById(R.id.validate_et));
        this.mGetCodeView = ((TextView) findViewById(R.id.get_code_view));
        this.mPassEt = ((EditText) findViewById(R.id.pass_et));
        this.mSureView = ((Button) findViewById(R.id.sure_view));
        
        initListener();
    }

    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        this.mForgetPassPresenter.onDestroy();
    }

    @Subscribe
    public void onEvent(SetCountryCodeEvent event) {
        if (this.mCountryCodeView != null)
            this.mCountryCodeView.setText(event.phoneCode);
    }
}
