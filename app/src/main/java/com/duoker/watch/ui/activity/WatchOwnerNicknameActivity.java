package com.duoker.watch.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.duoker.watch.R;
import com.duoker.watch.ui.base.BaseActivity;
import com.duoker.watch.ui.event.SetWatchInfoNicknameEvent;
import com.duoker.watch.ui.iosdialog.AlertDialog;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by chengang on 5/9/2017.
 */


public class WatchOwnerNicknameActivity extends BaseActivity {
    public static final String TAG = WatchOwnerNicknameActivity.class.getSimpleName();
    private EditText mNicknameEt;
    private String mOldNickname;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(R.layout.activity_watch_owner_nickname);
        initToolbar();
        mNicknameEt = ((EditText) findViewById(R.id.nickname_et));

        mOldNickname = getIntent().getStringExtra(TAG);
        mNicknameEt.setText(this.mOldNickname);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
                        saveNickname();
                    default:
                }
                return false;
            }
        });
    }


    private void saveNickname() {
        String str = this.mNicknameEt.getText().toString();
        if (TextUtils.isEmpty(str)) {
            this.mNicknameEt.setError(getString(R.string.nickname_not_empty));
            return;
        }
        SetWatchInfoNicknameEvent event = new SetWatchInfoNicknameEvent();
        event.nickname = str;
        EventBus.getDefault().post(event);
        finish();
    }

    private void showIsNeedSaveDialog() {
        String str = this.mNicknameEt.getText().toString();
        if (!TextUtils.equals(this.mOldNickname, str)) {
            new AlertDialog(this).setTitle(
                    getString(R.string.common_all_tip_1)).
                    setMsg(getString(R.string.common_save_tip_1)).
                    setCancelable(true).setNegativeButton(getString(R.string.common_all_tip_3),
                    new View.OnClickListener() {
                        public void onClick(View v) {
                            finish();
                        }
                    }).setPositiveButton(getString(R.string.common_all_tip_2), new View.OnClickListener() {
                public void onClick(View v) {
                    saveNickname();
                }
            }).show();
            return;
        }
        finish();
    }

    public void onBackPressed() {
        showIsNeedSaveDialog();
    }

}