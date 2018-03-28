package com.duoker.watch.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.duoker.watch.R;
import com.duoker.watch.presenters.RegisterPresenter;
import com.duoker.watch.presenters.impl.RegisterPresenterImpl;
import com.duoker.watch.repository.L25RepositoryImpl;
import com.duoker.watch.ui.base.BaseActivity;
import com.duoker.watch.ui.event.SetCountryCodeEvent;
import com.duoker.watch.ui.view.CircleImageView;
import com.duoker.watch.ui.view.MyToolbar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


public class RegisterActivity extends BaseActivity implements RegisterPresenter.View {
    private static final String TAG = RegisterActivity.class.getSimpleName();

    private CircleImageView mAvatarView;
    private TextView mCountryCodeView;
    private String mFilePath;
    private TextView mGetCodeView;
    private EditText mNicknameEt;
    private EditText mPassEt;
    private EditText mPhoneEt;
    private RegisterPresenterImpl mRegisterPresenter;
    private Button mRegisterView;
    private MyToolbar mToolbarLayout;
    private ImageView mToolbarMenuBack;
    private EditText mValidateEt;

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_register);
        EventBus.getDefault().register(this);

        this.mRegisterPresenter = new RegisterPresenterImpl(this, new L25RepositoryImpl(getApplicationContext()));

        this.mAvatarView = ((CircleImageView) findViewById(R.id.avatar_view));
        this.mCountryCodeView = ((TextView) findViewById(R.id.country_code_view));
        this.mPhoneEt = ((EditText) findViewById(R.id.phone_et));
        this.mValidateEt = ((EditText) findViewById(R.id.validate_et));
        this.mGetCodeView = ((TextView) findViewById(R.id.get_code_view));
        this.mPassEt = ((EditText) findViewById(R.id.pass_et));
        this.mNicknameEt = ((EditText) findViewById(R.id.nickname_et));
        this.mRegisterView = ((Button) findViewById(R.id.register_view));

        this.mToolbarLayout = ((MyToolbar) findViewById(R.id.toolbar_layout));
        this.mToolbarMenuBack = ((ImageView) findViewById(R.id.toolbar_menu_back));
        this.mToolbarMenuBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        initListener();
    }

    private void initListener() {
        this.mRegisterView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                doRegister();
            }
        });
        this.mAvatarView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent localIntent = new Intent(RegisterActivity.this, ChooseAvatarImageActivity.class);
                startActivityForResult(localIntent, 1);
            }
        });

        this.mCountryCodeView.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent localIntent = new Intent(RegisterActivity.this, CountryCodeActivity.class);
                startActivity(localIntent);
            }
        });

        this.mGetCodeView.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                String str1 = mCountryCodeView.getText().toString();
                String str2 = mPhoneEt.getText().toString();
                mRegisterPresenter.getCode(str1, str2);
            }
        });
    }

    protected void onDestroy() {
        super.onDestroy();
        this.mRegisterPresenter.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(SetCountryCodeEvent event) {
        if (this.mCountryCodeView != null)
            this.mCountryCodeView.setText(event.phoneCode);
    }

    private void doRegister()
    {
        String countryCode = mCountryCodeView.getText().toString();
        String phone = mPhoneEt.getText().toString();
        String pwd = mPassEt.getText().toString();
        String nickname = mNicknameEt.getText().toString();
        String validateCode = mValidateEt.getText().toString();
        mRegisterPresenter.register(countryCode, phone, pwd, nickname, validateCode, mFilePath);
    }

    public void enableCodeView(boolean enable) {
        if (mGetCodeView != null)
            mGetCodeView.setEnabled(enable);
    }

    public void finishActivity() {
        finish();
    }

    public void goToLogin() {
        Intent intent = new Intent(RegisterActivity.this, LogonActivity.class);
        startActivity(intent);
    }

    public void hideLoading() {
        hideProgress();
    }

    public void setCodeTime(int time) {
        if (mGetCodeView != null) {
            if (time != 0) {
                StringBuilder sb = new StringBuilder(getString(R.string.hb_str_get_validate)).append("(").append(time).append(")");
                mGetCodeView.setText(sb.toString());
            }
            mGetCodeView.setText(getString(R.string.hb_str_get_validate));
        }
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1) {
            this.mFilePath = data.getStringExtra("FILE_PATH");
            Glide.with(getApplicationContext()).load(this.mFilePath).error(R.drawable.iflytek_face_01).into(this.mAvatarView);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}
