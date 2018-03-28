package com.duoker.watch.ui.activity;

/**
 * Created by cheng on 2017/10/7.
 */

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.duoker.watch.R;
import com.duoker.watch.model.ScheduleModel;
import com.duoker.watch.ui.base.BaseActivity;
import com.duoker.watch.ui.fragment.ScheduleEditTextFragment;

public class ScheduleEditActivity extends BaseActivity
{
    public static final String ENTITY = "ENTITY";
    private static final String TAG = ScheduleEditActivity.class.getSimpleName();
    private ScheduleEditTextFragment mScheduleEditTextFragment;
    private View mToolbarMenuSure;

    private void initData()
    {
        if (getIntent() != null)
        {
            ScheduleModel localScheduleModel = (ScheduleModel)getIntent().getSerializableExtra("ENTITY");
            FragmentTransaction localFragmentTransaction = getSupportFragmentManager().beginTransaction();
            this.mScheduleEditTextFragment = ScheduleEditTextFragment.newInstance(localScheduleModel);
            localFragmentTransaction.add(2131558566, this.mScheduleEditTextFragment, ScheduleEditTextFragment.TAG);
            localFragmentTransaction.commit();
        }
    }

    private void initListener()
    {
        this.mToolbarMenuSure.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                if (mScheduleEditTextFragment != null)
                    mScheduleEditTextFragment.edit();
            }
        });
    }

    private void initToolbar()
    {
        findViewById(R.id.toolbar_layout).setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
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
        setContentView(R.layout.activity_schedule_edit);
        initToolbar();
        initView();
        initListener();
        initData();
    }

    protected void onDestroy()
    {
        super.onDestroy();
    }
}
