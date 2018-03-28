package com.duoker.watch.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duoker.watch.R;
import com.duoker.watch.db.model.CardNumberBean;
import com.duoker.watch.presenters.WatchContactAddPresenter;
import com.duoker.watch.presenters.impl.WatchContactsAddPresenterImpl;
import com.duoker.watch.ui.base.BaseActivity;
import com.duoker.watch.ui.event.QueryContactEvent;
import com.duoker.watch.ui.view.MyToolbar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by chengang on 4/25/2017.
 */

public class WatchContactsAddActivity extends BaseActivity implements WatchContactAddPresenter.View {
    public static final String ENTITY = "ENTITY";
    public static final String POSITION = "POSITION";
    private static final String TAG = WatchContactsAddActivity.class.getSimpleName();

    private boolean isListenCheck = false;
    private boolean isPhoneCallCheck = false;
    private boolean isSosCheck = false;
    private ImageView mAddrListView;
    private LinearLayout mListenLayout;
    private ImageView mListenView;
    private EditText mNameEt;
    private LinearLayout mPhoneCallLayout;
    private ImageView mPhoneCallView;
    private EditText mPhoneEt;
    private EditText mShortNumEt;
    private LinearLayout mSosLayout;
    private ImageView mSosView;
    private MyToolbar mToolbarLayout;
    private ImageView mToolbarMenuBack;
    private ImageView mToolbarMenuSure;
    private TextView mToolbarTitleTv;

    private WatchContactsAddPresenterImpl mContactsAddPresenter;

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_watch_add_contact);
        EventBus.getDefault().register(this);
        mContactsAddPresenter = new WatchContactsAddPresenterImpl(this);
        initViews();
        initListener();
        initData();
    }

    private void initViews() {
        this.mToolbarLayout = ((MyToolbar) findViewById(R.id.toolbar_layout));
        this.mToolbarMenuBack = ((ImageView) findViewById(R.id.toolbar_menu_back));

        this.mToolbarTitleTv = ((TextView) findViewById(R.id.toolbar_title_tv));
        this.mToolbarMenuSure = ((ImageView) findViewById(R.id.toolbar_menu_sure));
        this.mSosLayout = ((LinearLayout) findViewById(R.id.sos_layout));
        this.mSosView = ((ImageView) findViewById(R.id.sos_view));
        this.mPhoneCallLayout = ((LinearLayout) findViewById(R.id.phone_call_layout));
        this.mPhoneCallView = ((ImageView) findViewById(R.id.phone_call_view));
        this.mListenLayout = ((LinearLayout) findViewById(R.id.listen_layout));
        this.mListenView = ((ImageView) findViewById(R.id.listen_view));
        this.mNameEt = ((EditText) findViewById(R.id.name_et));
        this.mAddrListView = ((ImageView) findViewById(R.id.addr_list_view));
        this.mPhoneEt = ((EditText) findViewById(R.id.phone_et));
        this.mShortNumEt = ((EditText) findViewById(R.id.short_num_et));
    }

    private void initData() {
        if (getIntent() != null) {
            CardNumberBean bean = (CardNumberBean) getIntent().getSerializableExtra("ENTITY");
            int pos = getIntent().getIntExtra("POSITION", -1);
            mContactsAddPresenter.initData(bean, pos);
        }
    }


    private void initListener() {
        this.mToolbarMenuBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                finish();
            }
        });

        this.mToolbarMenuSure.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                addOrEditContacts();
            }
        });
        mSosLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                checkSosLayout(true);
                checkPhoneCallLayout(false);
                checkListenLayout(false);
            }
        });
        this.mPhoneCallLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                checkSosLayout(false);
                checkPhoneCallLayout(true);
                checkListenLayout(false);
            }
        });
        this.mListenLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                checkSosLayout(false);
                checkPhoneCallLayout(false);
                checkListenLayout(true);
            }
        });

        this.mAddrListView.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                Intent intent = new Intent(WatchContactsAddActivity.this, SelectContactActivity.class);
                startActivity(intent);
            }
        });
    }

    private void checkListenLayout(boolean chked) {
        this.isListenCheck = chked;
        this.mListenView.setImageResource(chked ? R.drawable.hb_add_contacts_listen_checked : R.drawable.hb_add_contacts_listen_normal);
    }

    private void checkPhoneCallLayout(boolean chked) {
        this.isPhoneCallCheck = chked;
        this.mPhoneCallView.setImageResource(chked ? R.drawable.hb_add_contacts_phone_checked : R.drawable.hb_add_contacts_phone_normal);
    }

    private void checkSosLayout(boolean chk) {
        this.isSosCheck = chk;
        this.mSosView.setImageResource(chk ? R.drawable.hb_add_contacts_sos_checked : R.drawable.hb_add_contacts_sos_normal);
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void setCheckListenLayout(boolean paramBoolean) {
        if (mListenView != null)
            checkListenLayout(paramBoolean);
    }

    @Override
    public void setCheckPhoneCallLayout(boolean paramBoolean) {
        if (mPhoneCallView != null)
            checkPhoneCallLayout(paramBoolean);
    }

    @Override
    public void setCheckSosLayout(boolean paramBoolean) {
        if (mSosView != null)
            checkSosLayout(paramBoolean);
    }

    @Override
    public void setNameText(String paramString) {
        if (mNameEt != null)
            mNameEt.setText(paramString);
    }

    @Override
    public void setPhoneText(String paramString) {
        if (mPhoneEt != null)
            mPhoneEt.setText(paramString);
    }

    @Override
    public void setShortNumText(String paramString) {
        if (mShortNumEt != null)
            mShortNumEt.setText(paramString);
    }

    @Override
    public void setToolbarTitleText(@StringRes int paramInt) {
        if (mToolbarLayout != null)
            mToolbarTitleTv.setText(paramInt);
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showToast(@StringRes int resourceID) {

        showShortToast(resourceID);
    }

    @Override
    public void showToast(CharSequence charSequence) {
        showShortToast(charSequence);
    }


    private void addOrEditContacts()
    {
        String name = this.mNameEt.getText().toString();
        String phone = this.mPhoneEt.getText().toString();
        String shortnum = this.mShortNumEt.getText().toString();
        int type = -1;
        if (this.isSosCheck)
            type = 1;
        if (this.isPhoneCallCheck)
            type = 2;
        if (this.isListenCheck)
            type = 3;
        this.mContactsAddPresenter.addOrEditContacts(name, phone, shortnum, type);
    }

    @Subscribe
    public void onEvent( QueryContactEvent event )
    {
        if (this.mNameEt != null)
        {
            this.mNameEt.setText(event.name);
            this.mPhoneEt.setText(event.phone);
        }
    }
}