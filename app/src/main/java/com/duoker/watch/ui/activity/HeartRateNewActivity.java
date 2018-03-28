package com.duoker.watch.ui.activity;

/**
 * Created by cheng on 2017/10/8.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.duoker.watch.R;
import com.duoker.watch.ui.base.BaseActivity;
import com.duoker.watch.ui.event.MeasureHeartRateOKEvent;
import com.duoker.watch.ui.fragment.HeartRateDayFragment;
import com.duoker.watch.ui.fragment.HeartRateMonthFragment;
import com.duoker.watch.ui.fragment.HeartRateWeekFragment;
import com.duoker.watch.ui.pop.HeartRateChooseViewPopupWindow;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class HeartRateNewActivity extends BaseActivity {
    private static final String TAG = HeartRateNewActivity.class.getSimpleName();
    private HeartRateChooseViewPopupWindow mHeartRateChooseViewPopupWindow;
    private HeartRateDayFragment mHeartRateDayFragment;
    private HeartRateMonthFragment mHeartRateMonthFragment;
    private HeartRateWeekFragment mHeartRateWeekFragment;
    private Fragment mNowShowFragment;
    private FloatingActionButton mStartMeasureView;
    private ImageView mToolbarMenuChooseView;
    private TextView mToolbarTitle;

    @Override
    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_heart_rate_new);
        EventBus.getDefault().register(this);
        initToolbar();
        initView();
        initListener();
        initFragment();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(MeasureHeartRateOKEvent paramMeasureHeartRateOKEvent) {
    }

    private void initFragment() {
        showFragment(0);
    }

    private void initListener() {
        this.mToolbarMenuChooseView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                if (mHeartRateChooseViewPopupWindow != null)
                    mHeartRateChooseViewPopupWindow.showPopup(paramAnonymousView);
            }
        });
        this.mHeartRateChooseViewPopupWindow.setOnClickListener(new HeartRateChooseViewPopupWindow.OnClickListener() {
            public void onClickDay() {
                Log.d(HeartRateNewActivity.TAG, "点击天");
                showFragment(0);
                mHeartRateChooseViewPopupWindow.dismiss();
                mToolbarMenuChooseView.setImageResource(R.drawable.icon_day);
            }

            public void onClickMonth() {
                Log.d(HeartRateNewActivity.TAG, "点击月");
                showFragment(2);
                mHeartRateChooseViewPopupWindow.dismiss();
                mToolbarMenuChooseView.setImageResource(R.drawable.icon_month);
            }

            public void onClickWeek() {
                Log.d(HeartRateNewActivity.TAG, "点击周");
                showFragment(1);
                mHeartRateChooseViewPopupWindow.dismiss();
                mToolbarMenuChooseView.setImageResource(R.drawable.icon_week);
            }
        });
        this.mStartMeasureView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent localIntent = new Intent(HeartRateNewActivity.this, HeartrateMeasureActivity.class);
                startActivity(localIntent);
            }
        });
    }

    private void initToolbar() {
        findViewById(R.id.toolbar_menu_back).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        this.mToolbarTitle = ((TextView) findViewById(R.id.toolbar_title_tv));
        this.mToolbarMenuChooseView = ((ImageView) findViewById(R.id.toolbar_menu_choose_view));
        this.mStartMeasureView = ((FloatingActionButton) findViewById(R.id.start_measure_view));
        this.mHeartRateChooseViewPopupWindow = new HeartRateChooseViewPopupWindow(this);
    }


    public void showFragment(int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (this.mNowShowFragment != null)
            transaction.hide(this.mNowShowFragment);

        if (index == 0) {
            if (this.mHeartRateDayFragment == null) {
                HeartRateDayFragment localHeartRateDayFragment = HeartRateDayFragment.newInstance();
                this.mHeartRateDayFragment = localHeartRateDayFragment;
                this.mNowShowFragment = localHeartRateDayFragment;
                transaction.add(R.id.container_layout, this.mHeartRateDayFragment, HeartRateDayFragment.TAG);
            } else {

                this.mNowShowFragment = this.mHeartRateDayFragment;
                transaction.show(this.mHeartRateDayFragment);
            }
        }
        if (index == 1) {
            if (this.mHeartRateWeekFragment == null) {
                HeartRateWeekFragment localHeartRateWeekFragment = HeartRateWeekFragment.newInstance();
                this.mHeartRateWeekFragment = localHeartRateWeekFragment;
                this.mNowShowFragment = localHeartRateWeekFragment;
                transaction.add(R.id.container_layout, this.mHeartRateWeekFragment, HeartRateWeekFragment.TAG);
            } else {
                this.mNowShowFragment = this.mHeartRateWeekFragment;
                transaction.show(this.mHeartRateWeekFragment);
            }
        } else if (index == 2) {
            if (this.mHeartRateMonthFragment == null) {
                HeartRateMonthFragment localHeartRateMonthFragment = HeartRateMonthFragment.newInstance();
                this.mHeartRateMonthFragment = localHeartRateMonthFragment;
                this.mNowShowFragment = localHeartRateMonthFragment;
                transaction.add(R.id.container_layout, this.mHeartRateMonthFragment, HeartRateMonthFragment.TAG);
            } else {
                this.mNowShowFragment = this.mHeartRateMonthFragment;
                transaction.show(this.mHeartRateMonthFragment);
            }
        }

        transaction.commit();
    }
}