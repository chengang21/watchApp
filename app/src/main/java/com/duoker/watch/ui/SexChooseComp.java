package com.duoker.watch.ui;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.duoker.watch.R;

/**
 * Created by chengang on 5/9/2017.
 */

public class SexChooseComp {

    public enum SexType {
        MALE,
        FEMALE
    }

    private final Context mContext;
    private View mFemaleLayout;
    private View mMaleLayout;
    private ImageView mSelectFemaleIv;
    private ImageView mSelectMaleIv;
    private SexType mSexType = SexType.MALE;
    private View mView;

    public SexChooseComp(Context paramContext) {
        mContext = paramContext;
        initView();
    }

    private void initData() {
        switch (mSexType.ordinal()) {
            case 1:
                mSelectMaleIv.setImageResource(R.drawable.hb_icon_check_mark);
                mSelectFemaleIv.setImageResource(0);
                return;
            case 2:
                mSelectMaleIv.setImageResource(0);
                mSelectFemaleIv.setImageResource(R.drawable.hb_icon_check_mark);
        }
    }

    private void initView() {
        mView = View.inflate(mContext, R.layout.choose_sex_dialog, null);
        mMaleLayout = mView.findViewById(R.id.male_layout);
        mFemaleLayout = mView.findViewById(R.id.female_layout);
        mSelectMaleIv = ((ImageView) mView.findViewById(R.id.select_male_iv));
        mSelectFemaleIv = ((ImageView) mView.findViewById(R.id.select_female_iv));
        mMaleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickMaleLayout();
            }
        });
        mFemaleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickFemaleLayout();
            }
        });

        initData();
    }

    public void clickFemaleLayout() {
        mSexType = SexType.FEMALE;
        mSelectMaleIv.setImageResource(0);
        mSelectFemaleIv.setImageResource(R.drawable.hb_icon_check_mark);
    }

    public void clickMaleLayout() {
        mSexType = SexType.MALE;
        mSelectMaleIv.setImageResource(R.drawable.hb_icon_check_mark);
        mSelectFemaleIv.setImageResource(0);
    }

    public SexType getSex() {
        return mSexType;
    }

    public View getView() {
        return mView;
    }

    public void init(SexType paramSexType) {
        mSexType = paramSexType;
        initData();
    }

}