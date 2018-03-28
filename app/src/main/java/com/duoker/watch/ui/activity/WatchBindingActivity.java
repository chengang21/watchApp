package com.duoker.watch.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.duoker.watch.R;
import com.duoker.watch.presenters.impl.BindWatchManagerPresenterImpl;

/**
 * Created by chengang on 4/27/2017.
 */

public class WatchBindingActivity extends Activity {

    private RelativeLayout mUnbindOtherLayout;
    private RelativeLayout mUnbindSelfLayout;
    private RelativeLayout mUnbindSelfNFactorySettingLayout;
    private RelativeLayout mUnbindTransferLayout;
    private BindWatchManagerPresenterImpl mBindWatchManagerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ;
        setContentView(R.layout.activity_watch_bind);
        // this.mBindWatchManagerPresenter = new BindWatchManagerPresenterImpl(this.iView, new DeviceBindRepositoryImpl(getApplicationContext()), new WatchRepositoryImpl(getApplicationContext()));

        this.mUnbindSelfLayout = ((RelativeLayout) findViewById(R.id.unbind_self_layout));
        this.mUnbindOtherLayout = ((RelativeLayout) findViewById(R.id.unbind_other_layout));
        this.mUnbindTransferLayout = ((RelativeLayout) findViewById(R.id.unbind_transfer_layout));
        this.mUnbindSelfNFactorySettingLayout = ((RelativeLayout) findViewById(R.id.unbind_self_n_factory_setting_layout));

        findViewById(R.id.toolbar_menu_back).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        initListener();
        // this.mBindWatchManagerPresenter.onCreate(paramBundle);
    }

    private void initListener() {
        this.mUnbindSelfLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //mBindWatchManagerPresenter.clickUnbindSelf();
            }
        });
        this.mUnbindOtherLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //mBindWatchManagerPresenter.clickUnbindOther();
            }
        });
        this.mUnbindTransferLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ///mBindWatchManagerPresenter.clickTransfer();
            }
        });
        this.mUnbindSelfNFactorySettingLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //mBindWatchManagerPresenter.clickUnbindSelfNFactorySetting();
            }
        });
    }


}
