package com.duoker.watch.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duoker.watch.R;
import com.duoker.watch.ui.component.NotifyChooseComp;
import com.duoker.watch.ui.component.VolumeChooseComp;
import com.duoker.watch.ui.event.GetWatchSetsEvent;
import com.duoker.watch.ui.view.SwitchButton;
import com.duoker.watch.utils.NotifyHelper;
import com.duoker.watch.utils.VolumeHelper;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnClickListener;
import com.orhanobut.dialogplus.ViewHolder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by chengang on 4/27/2017.
 */

public class WatchSettingActivity extends Activity
{
    private LinearLayout mNotifyLayout;
    private TextView mNotifyTv;
    private SwitchButton mSavePowerTb;
    private LinearLayout mVolumeLayout;
    private TextView mVolumeTv;
    // private WatchSets2PresenterImpl mWatchSetsPresenter;


    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_watch_setting);

        ((ImageView)findViewById(R.id.toolbar_menu_back)).setOnClickListener( new View.OnClickListener()
        {
            public void onClick(View v)
            {
                WatchSettingActivity.this.finish();
            }
        });

        this.mNotifyLayout = ((LinearLayout)findViewById(R.id.notify_layout));
        this.mNotifyTv = ((TextView)findViewById(R.id.notify_tv));
        this.mVolumeLayout = ((LinearLayout)findViewById(R.id.volume_layout));
        this.mVolumeTv = ((TextView)findViewById(R.id.volume_tv));
        this.mSavePowerTb = ((SwitchButton)findViewById(R.id.save_power_tb));

        initListener();

        EventBus.getDefault().register(this);
        // this.mWatchSetsPresenter = new WatchSets2PresenterImpl(this.iView, new NotifyHelper(getApplicationContext()), new VolumeHelper(getApplicationContext()), new WatchSettingRepositoryImpl(), new ProjectCateRepositoryImpl(getApplicationContext()));

        // this.mWatchSetsPresenter.onCreate(paramBundle);
    }


    private void initListener()
    {
        this.mNotifyLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = mNotifyTv.getText().toString();
                NotifyHelper.NotifyType notifyType = new NotifyHelper(WatchSettingActivity.this.getApplicationContext()).getNotifyType(txt);
                final NotifyChooseComp notifyChooseComp = new NotifyChooseComp(WatchSettingActivity.this.getApplicationContext());
                notifyChooseComp.init(notifyType);
                ViewHolder localViewHolder = new ViewHolder(notifyChooseComp.getView());
                DialogPlus.newDialog(WatchSettingActivity.this).
                        setContentHolder(localViewHolder).setHeader(R.layout.dialog_header).
                        setCancelable(true).setGravity(80).setOnDismissListener(null).
                        setExpanded(false).setContentHeight(-2).setOnCancelListener(null).
                        setContentBackgroundResource(17170443).setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(DialogPlus dialog, View view) {
                        switch (view.getId()) {
                            case R.id.mute_layout:
                                notifyChooseComp.clickMuteLayout();
                                return;
                            case R.id.shake_layout:
                                notifyChooseComp.clickShakeLayout();
                                return;
                            case R.id.shake_ring_layout:
                                notifyChooseComp.clickShakeRingLayout();
                                return;
                            case R.id.sure_layout:
                                NotifyHelper.NotifyType localNotifyType = notifyChooseComp.getNotifyType();
                                // mWatchSetsPresenter.setNotify(localNotifyType);
                                dialog.dismiss();
                                return;
                            case R.id.cancel_layout:
                                dialog.dismiss();
                        }
                    }
                }).create().show();
            }
        });

        this.mVolumeLayout.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
//                if (!WatchSettingActivity.this.mWatchSetsPresenter.isAdmin())
//                {
//                    WatchSettingActivity.this.iView.showToast(2131165368);
//                    return;
//                }
                showChooseVolumeDialog();
            }
        });

        this.mSavePowerTb.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
//                if (!WatchSettingActivity.this.mWatchSetsPresenter.isAdmin())
//                {
//                    WatchSettingActivity.this.iView.showToast(2131165368);
//                    SwitchButton localSwitchButton = WatchSettingActivity.this.mSavePowerTb;
//                    if (!WatchSettingActivity.this.mSavePowerTb.isChecked());
//                    for (boolean bool2 = true; ; bool2 = false)
//                    {
//                        localSwitchButton.setChecked(bool2);
//                        return;
//                    }
//                }
//                boolean bool1 = WatchSettingActivity.this.mSavePowerTb.isChecked();
//                WatchSettingActivity.this.mWatchSetsPresenter.intentSavePower(bool1);
            }
        });
    }


    private void showChooseVolumeDialog()
    {
        final VolumeChooseComp volumeChooseComp = new VolumeChooseComp(this);
        volumeChooseComp.selectValue(9); //TODO this.mWatchSetsPresenter.getVolume()
        ViewHolder viewHolder = new ViewHolder(volumeChooseComp.getView());
        DialogPlus.newDialog(this).setContentHolder(viewHolder).setHeader(R.layout.dialog_header).
                setCancelable(true).setGravity(80).setOnDismissListener(null).
                setExpanded(false).setContentHeight(-2).setOnCancelListener(null).
                setContentBackgroundResource(17170443).
                setOnClickListener(new OnClickListener() {
                    public void onClick(DialogPlus dialog, View view) {
                        switch (view.getId()) {
                            case R.id.sure_layout:
                                VolumeHelper.VolumeData localVolumeData = volumeChooseComp.getSelectedItem();
                                //TODO mWatchSetsPresenter.setVolume(localVolumeData.getVolume());
                                dialog.dismiss();
                                return;
                            case R.id.cancel_layout:
                                dialog.dismiss();
                        }
                    }
                }).create().show();

    }

    protected void onDestroy()
    {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(GetWatchSetsEvent paramGetWatchSetsEvent)
    {
//        if (this.mWatchSetsPresenter != null)
//            this.mWatchSetsPresenter.getSets();
    }

}
