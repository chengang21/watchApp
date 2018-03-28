package com.duoker.watch.ui.view.wheelview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
/**
 * Created by cheng on 2017/10/7.
 */

public class WheelTimePicker extends RelativeLayout
{
    private boolean isShowSecond = true;
    private int mHour = -1;
    private List<String> mHourList = new ArrayList();
    private LoopView mHourLv;
    private int mMinute = -1;
    private List<String> mMinuteList = new ArrayList();
    private LoopView mMinuteLv;
    private int mSecond = -1;
    private List<String> mSecondList = new ArrayList();
    private LoopView mSecondLv;

    public WheelTimePicker(Context paramContext)
    {
        this(paramContext, null);
    }

    public WheelTimePicker(Context paramContext, AttributeSet paramAttributeSet)
    {
        this(paramContext, paramAttributeSet, 0);
    }

    public WheelTimePicker(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
    {
        super(paramContext, paramAttributeSet, paramInt);
        initView();
    }

    private void initHourLv()
    {
        int i = 0;
        this.mHourList.clear();
        for (int j = 0; j <= 23; j++)
        {
            this.mHourList.add(Integer.toString(j));
            if (j == this.mHour)
                i = j;
        }
        this.mHourLv.setItems(this.mHourList);
        this.mHourLv.setInitPosition(i);
    }

    private void initMinuteLv()
    {
        int i = 0;
        this.mMinuteList.clear();
        for (int j = 0; j <= 59; j++)
        {
            this.mMinuteList.add(Integer.toString(j));
            if (j == this.mMinute)
                i = j;
        }
        this.mMinuteLv.setItems(this.mMinuteList);
        this.mMinuteLv.setInitPosition(i);
    }

    private void initSecondLv()
    {
        int i = 0;
        this.mSecondList.clear();
        for (int j = 0; j <= 59; j++)
        {
            this.mSecondList.add(Integer.toString(j));
            if (j == this.mSecond)
                i = j;
        }
        this.mSecondLv.setItems(this.mSecondList);
        this.mSecondLv.setInitPosition(i);
    }

    private void initView()
    {
        LinearLayout localLinearLayout = new LinearLayout(getContext());
        localLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        addView(localLinearLayout, new RelativeLayout.LayoutParams(-2, -2));
        this.mHourLv = new LoopView(getContext());
        this.mMinuteLv = new LoopView(getContext());
        this.mSecondLv = new LoopView(getContext());
        this.mHourLv.setTextSize(24.0F);
        this.mHourLv.setViewPadding(6, 0, 6, 0);
        this.mMinuteLv.setTextSize(24.0F);
        this.mMinuteLv.setViewPadding(6, 0, 6, 0);
        this.mSecondLv.setTextSize(24.0F);
        this.mSecondLv.setViewPadding(6, 0, 6, 0);
        LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-2, -2);
        localLayoutParams.setMargins(30, 0, 30, 0);
        localLinearLayout.addView(this.mHourLv);
        localLinearLayout.addView(this.mMinuteLv, localLayoutParams);
        localLinearLayout.addView(this.mSecondLv);
        if ((this.mHour == -1) || (this.mMinute == -1) || (this.mSecond == -1))
        {
            Calendar localCalendar = Calendar.getInstance();
            this.mHour = localCalendar.get(Calendar.HOUR_OF_DAY);
            this.mMinute = localCalendar.get(Calendar.MINUTE);
            this.mSecond = localCalendar.get(Calendar.SECOND);
        }
        initHourLv();
        initMinuteLv();
        initSecondLv();
    }

    public int getHour()
    {
        int i = this.mHourLv.getSelectedItem();
        return Integer.parseInt((String)this.mHourList.get(i));
    }

    public int getMinute()
    {
        int i = this.mMinuteLv.getSelectedItem();
        return Integer.parseInt((String)this.mMinuteList.get(i));
    }

    public int getSecond()
    {
        int i = this.mSecondLv.getSelectedItem();
        return Integer.parseInt((String)this.mSecondList.get(i));
    }

    public void init(int paramInt1, int paramInt2)
    {
        init(paramInt1, paramInt2, 0);
    }

    public void init(int paramInt1, int paramInt2, int paramInt3)
    {
        this.mHour = paramInt1;
        this.mMinute = paramInt2;
        this.mSecond = paramInt3;
        initHourLv();
        initMinuteLv();
        initSecondLv();
    }

    public void setShowSecond(boolean paramBoolean)
    {
        if (this.mSecondLv != null)
        {
            if (paramBoolean)
                this.mSecondLv.setVisibility(View.VISIBLE);
        }
        else
            return;
        this.mSecondLv.setVisibility(View.GONE);
    }

    public void setVisibleItems(int paramInt)
    {
        this.mHourLv.setVisibleItems(paramInt);
        this.mMinuteLv.setVisibleItems(paramInt);
        this.mSecondLv.setVisibleItems(paramInt);
    }

    public void setWheelMargin(int paramInt)
    {
        if (this.mMinuteLv != null) {
            LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams) this.mMinuteLv.getLayoutParams();
            if (this.mSecondLv.getVisibility() != View.GONE)
                localLayoutParams.setMargins(paramInt, 0, paramInt, 0);
            else
                localLayoutParams.setMargins(paramInt, 0, 0, 0);

            this.mMinuteLv.setLayoutParams(localLayoutParams);
        }
    }
}
