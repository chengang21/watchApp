package com.duoker.watch.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.duoker.watch.R;
import com.duoker.watch.model.ScheduleModel;
import com.duoker.watch.ui.base.BaseActivity;
import com.duoker.watch.ui.event.ScheduleSpeech2TextEvent;
import com.duoker.watch.ui.fragment.ScheduleCreateSpeechFragment;
import com.duoker.watch.ui.fragment.ScheduleCreateTextFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by cheng on 2017/10/6.
 */
public class ScheduleCreateActivity extends BaseActivity
{
    private static final String TAG = ScheduleCreateActivity.class.getSimpleName();
    private Fragment mNowShowFragment;
    private ScheduleCreateSpeechFragment mScheduleCreateSpeechFragment;
    private ScheduleCreateTextFragment mScheduleCreateTextFragment;
    private View mToolbarMenuSure;

    private void initData()
    {
        showFragment(null, false);
    }

    private void initListener()
    {
        this.mToolbarMenuSure.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                if (ScheduleCreateActivity.this.mScheduleCreateTextFragment != null)
                    ScheduleCreateActivity.this.mScheduleCreateTextFragment.add();
            }
        });
    }

    private void initToolbar()
    {
        findViewById(R.id.toolbar_menu_back).setOnClickListener( new View.OnClickListener()  {
            public void onClick(View v)
            {
                finish();
            }
        });
    }

    private void initView()
    {
        this.mToolbarMenuSure = findViewById(R.id.toolbar_menu_sure);
    }

    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_schedule_create);
        EventBus.getDefault().register(this);
        initToolbar();
        initView();
        initListener();
        initData();
    }

    protected void onDestroy()
    {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(ScheduleSpeech2TextEvent paramScheduleSpeech2TextEvent)
    {
        if (isFinishing())
            return;

        showFragment(paramScheduleSpeech2TextEvent.scheduleModel, paramScheduleSpeech2TextEvent.speech2TextFragment);
    }

    public void showFragment(ScheduleModel scheduleModel, boolean speech2TextFragment)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (this.mNowShowFragment != null)
            fragmentTransaction.hide(this.mNowShowFragment);

        if (speech2TextFragment) {
            if (scheduleModel != null) {
                if (this.mScheduleCreateTextFragment != null)
                    fragmentTransaction.remove(this.mScheduleCreateTextFragment);
                ScheduleCreateTextFragment localScheduleCreateTextFragment2 = ScheduleCreateTextFragment.newInstance(scheduleModel);
                this.mScheduleCreateTextFragment = localScheduleCreateTextFragment2;
                this.mNowShowFragment = localScheduleCreateTextFragment2;
                fragmentTransaction.add(R.id.container_layout, this.mScheduleCreateTextFragment, ScheduleCreateTextFragment.TAG);
            }
        }
        else
        {
            if (this.mScheduleCreateTextFragment == null)
            {
                ScheduleCreateTextFragment localScheduleCreateTextFragment1 = ScheduleCreateTextFragment.newInstance(scheduleModel);
                this.mScheduleCreateTextFragment = localScheduleCreateTextFragment1;
                this.mNowShowFragment = localScheduleCreateTextFragment1;
                fragmentTransaction.add(R.id.container_layout, this.mScheduleCreateTextFragment, ScheduleCreateTextFragment.TAG);
            }
            else
            {
                this.mNowShowFragment = this.mScheduleCreateTextFragment;
                fragmentTransaction.show(this.mScheduleCreateTextFragment);
            }
        }
        fragmentTransaction.commit();
    }
}
