package com.duoker.watch.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.duoker.watch.R;
import com.duoker.watch.ui.fragment.HeartRateMeasureFragment;

/**
 * Created by chengang on 4/28/2017.
 */

public class HeartrateMeasureActivity extends Activity
{
    private HeartRateMeasureFragment mHeartRateMeasureFragment;
    private Fragment mNowShowFragment;
    private ImageView mToolbarMenuBack;
    private TextView mToolbarTitle;

    private void initFragment()
    {
        showFragment(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_heart_rate_measure );
        // EventBus.getDefault().register(this);

        findViewById( R.id.toolbar_menu_back ).setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                HeartrateMeasureActivity.this.finish();
            }
        });

        initFragment();
    }

    protected void onDestroy()
    {
        super.onDestroy();
        // EventBus.getDefault().unregister(this);
    }

//    @Subscribe
//    public void onEvent(MeasureHeartRateOKEvent paramMeasureHeartRateOKEvent)
//    {
//        if (this.mToolbarMenuBack != null)
//        {
//            FragmentTransaction localFragmentTransaction = getSupportFragmentManager().beginTransaction();
//            localFragmentTransaction.replace(2131558566, HeartRateMeasureResultFragment.newInstance(paramMeasureHeartRateOKEvent.heartRateBeanList), HeartRateMeasureResultFragment.TAG);
//            localFragmentTransaction.commit();
//        }
//    }

    public void showFragment(int paramInt)
    {
//        FragmentTransaction localFragmentTransaction = getSupportFragmentManager().beginTransaction();
//        if (this.mNowShowFragment != null)
//            localFragmentTransaction.hide(this.mNowShowFragment);
//        if (paramInt == 0)
//        {
//            if (this.mHeartRateMeasureFragment != null)
//                break label72;
//            HeartRateMeasureFragment localHeartRateMeasureFragment = HeartRateMeasureFragment.newInstance();
//            this.mHeartRateMeasureFragment = localHeartRateMeasureFragment;
//            this.mNowShowFragment = localHeartRateMeasureFragment;
//            localFragmentTransaction.add(2131558566, this.mHeartRateMeasureFragment, HeartRateMeasureFragment.TAG);
//        }
//        while (true)
//        {
//            localFragmentTransaction.commit();
//            return;
//            label72: this.mNowShowFragment = this.mHeartRateMeasureFragment;
//            localFragmentTransaction.show(this.mHeartRateMeasureFragment);
//        }
    }
}
