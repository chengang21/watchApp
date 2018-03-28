package com.duoker.watch.ui.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.duoker.watch.R;
import com.duoker.watch.storage.WatchsStorage;
import com.duoker.watch.ui.base.BaseActivity;
import com.duoker.watch.utils.QRCodeUtils;
import com.duoker.watch.utils.ScreenUtils;


public class WatchInfoWatchIdActivity extends BaseActivity {
    public static final String TAG = WatchInfoWatchIdActivity.class.getSimpleName();
    ImageView mQrcodeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_info_watchid);

        initToolbar();
        initView();
        initData();
    }

    private void initData() {
        if (getIntent() != null) {
            Bitmap localBitmap = QRCodeUtils.createQRImage(((WatchsStorage) getIntent().getSerializableExtra(TAG)).getWatchId(),
                    ScreenUtils.dpToPxInt(getApplicationContext(), 200.0F),
                    ScreenUtils.dpToPxInt(getApplicationContext(), 100.0F));
            this.mQrcodeView.setImageBitmap(localBitmap);
        }
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.drawable.icon_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View paramAnonymousView) {
                WatchInfoWatchIdActivity.this.finish();
            }
        });
    }

    private void initView() {
        this.mQrcodeView = ((ImageView) findViewById(R.id.qrcode_view));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

