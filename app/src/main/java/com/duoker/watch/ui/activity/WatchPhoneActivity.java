package com.duoker.watch.ui.activity;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.duoker.watch.R;
import com.duoker.watch.presenters.WatchInfoPhonePresenter;
import com.duoker.watch.presenters.impl.WatchInfoPhonePresenterImpl;
import com.duoker.watch.repository.WatchRepositoryImpl;
import com.duoker.watch.storage.WatchsStorage;
import com.duoker.watch.ui.base.BaseActivity;
import com.duoker.watch.ui.iosdialog.AlertDialog;

/**
 * Created by chengang on 5/9/2017.
 */

public class WatchPhoneActivity extends BaseActivity implements WatchInfoPhonePresenter.View {
    public static final String TAG = WatchPhoneActivity.class.getSimpleName();

    private EditText mPhoneEt;
    private WatchInfoPhonePresenterImpl mWatchInfoPhonePresenter;


    @Override
    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_watch_info_phone);
        this.mWatchInfoPhonePresenter = new WatchInfoPhonePresenterImpl(this, new WatchRepositoryImpl(getApplicationContext()));
        initToolbar();
        mPhoneEt = ((EditText) findViewById(R.id.phone_et));

        if (getIntent() != null) {
            WatchsStorage storage = (WatchsStorage) getIntent().getSerializableExtra(TAG);
            this.mWatchInfoPhonePresenter.initData(storage);
        }
    }

    private void bindSimCard() {
        String str = this.mPhoneEt.getText().toString();
        this.mWatchInfoPhonePresenter.bindSimCard(str);
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.drawable.icon_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showIsNeedSaveDialog();
            }
        });
        toolbar.inflateMenu(R.menu.watch_info);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.sure:
                        WatchPhoneActivity.this.bindSimCard();
                    default:
                }
                return false;

            }
        });
    }

    private void showIsNeedSaveDialog() {
        String str = this.mPhoneEt.getText().toString();
        if (!TextUtils.equals(this.mWatchInfoPhonePresenter.getOldPhone(), str)) {
            new AlertDialog(this).setTitle(getString(R.string.common_all_tip_1)).
                    setMsg(getString(R.string.common_save_tip_1)).
                    setCancelable(true).setNegativeButton(getString(R.string.common_all_tip_3),
                    new View.OnClickListener() {
                        public void onClick(View v) {
                            finish();
                        }
                    }).setPositiveButton(getString(R.string.common_all_tip_2),
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            WatchPhoneActivity.this.bindSimCard();
                        }
                    }).show();
            return;
        }
        finish();
    }

    @Override
    public void onBackPressed() {
        showIsNeedSaveDialog();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void finishActivity() {
        WatchPhoneActivity.this.finish();
    }

    @Override
    public void setPhoneError(int paramAnonymousInt) {
        WatchPhoneActivity.this.mPhoneEt.setError(WatchPhoneActivity.this.getString(paramAnonymousInt));
    }

    @Override
    public void setPhoneText(String paramAnonymousString) {
        WatchPhoneActivity.this.mPhoneEt.setText(paramAnonymousString);
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showToast(@StringRes int resourceID) {
        WatchPhoneActivity.this.showShortToast(resourceID);
    }

    @Override
    public void showToast(CharSequence charSequence) {
        WatchPhoneActivity.this.showShortToast(charSequence);
    }
}
