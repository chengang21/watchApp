package com.duoker.watch.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;

import com.duoker.watch.R;
import com.duoker.watch.ui.fragment.FragmentHeartRateDay;
import com.duoker.watch.ui.fragment.FragmentHeartRateMonth;
import com.duoker.watch.ui.fragment.FragmentHeartRateWeek;

/**
 * Created by chengang on 4/28/2017.
 */

public class HeartrateActivity extends Activity {
    // private HeartRateChooseViewPopupWindow mHeartRateChooseViewPopupWindow;
    private FragmentHeartRateDay mHeartRateDayFragment;
    private FragmentHeartRateMonth mHeartRateMonthFragment;
    private FragmentHeartRateWeek mHeartRateWeekFragment;
    private Fragment mNowShowFragment;
    private FloatingActionButton mStartMeasureView;
    private ImageView mToolbarMenuChooseView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_heart_rate );
        // EventBus.getDefault().register(this);
        findViewById( R.id.toolbar_menu_back).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                HeartrateActivity.this.finish();
            }
        });

        // this.mToolbarMenuChooseView = ((ImageView) findViewById(2131558597));
        this.mStartMeasureView = (FloatingActionButton) findViewById( R.id.start_measure_view );
        // this.mHeartRateChooseViewPopupWindow = new HeartRateChooseViewPopupWindow(this);

        initListener();
        initFragment();
    }

    private void initFragment() {
        // showFragment(0);
    }

    private void initListener() {

        this.mStartMeasureView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                Intent intent = new Intent( HeartrateActivity.this, HeartrateMeasureActivity.class );
                HeartrateActivity.this.startActivity(intent);
            }
        });

//        this.mToolbarMenuChooseView.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View paramAnonymousView) {
//                if (HeartrateActivity.this.mHeartRateChooseViewPopupWindow != null)
//                    HeartrateActivity.this.mHeartRateChooseViewPopupWindow.showPopup(paramAnonymousView);
//            }
//        });
//
//        this.mHeartRateChooseViewPopupWindow.setOnClickListener(new HeartRateChooseViewPopupWindow.OnClickListener() {
//            public void onClickDay() {
//                Log.d(HeartrateActivity.TAG, "点击天");
//                HeartrateActivity.this.showFragment(0);
//                HeartrateActivity.this.mHeartRateChooseViewPopupWindow.dismiss();
//                HeartrateActivity.this.mToolbarMenuChooseView.setImageResource(2130837805);
//            }
//
//            public void onClickMonth() {
//                Log.d(HeartrateActivity.TAG, "点击月");
//                HeartrateActivity.this.showFragment(2);
//                HeartrateActivity.this.mHeartRateChooseViewPopupWindow.dismiss();
//                HeartrateActivity.this.mToolbarMenuChooseView.setImageResource(2130837818);
//            }
//
//            public void onClickWeek() {
//                Log.d(HeartrateActivity.TAG, "点击周");
//                HeartrateActivity.this.showFragment(1);
//                HeartrateActivity.this.mHeartRateChooseViewPopupWindow.dismiss();
//                HeartrateActivity.this.mToolbarMenuChooseView.setImageResource(2130837830);
//            }
//        });
    }


    protected void onDestroy() {
        super.onDestroy();
        // EventBus.getDefault().unregister(this);
    }

//    @Subscribe
//    public void onEvent(MeasureHeartRateOKEvent event) {
//    }
}

