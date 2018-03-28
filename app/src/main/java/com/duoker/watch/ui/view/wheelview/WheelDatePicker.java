package com.duoker.watch.ui.view.wheelview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by cheng on 2017/10/7.
 */

public class WheelDatePicker extends RelativeLayout
{
    private int mDay = -1;
    private List<String> mDayList = new ArrayList();
    private LoopView mDayLv;
    private int mMonth = -1;
    private List<String> mMonthList = new ArrayList();
    private LoopView mMonthLv;
    private int mYear = -1;
    private List<String> mYearList = new ArrayList();
    private LoopView mYearLv;

    public WheelDatePicker(Context paramContext)
    {
        this(paramContext, null);
    }

    public WheelDatePicker(Context paramContext, AttributeSet paramAttributeSet)
    {
        this(paramContext, paramAttributeSet, 0);
    }

    public WheelDatePicker(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
    {
        super(paramContext, paramAttributeSet, paramInt);
        initView();
    }

    private void initMonthLv()
    {
        int i = 0;
        this.mMonthList.clear();
        for (int j = 1; j <= 12; j++)
        {
            this.mMonthList.add(Integer.toString(j));
            if (j == this.mMonth)
                i = j - 1;
        }
        this.mMonthLv.setItems(this.mMonthList);
        this.mMonthLv.setInitPosition(i);
        this.mMonthLv.setListener(new OnItemSelectedListener()
        {
            public void onItemSelected(int paramAnonymousInt)
            {
                int i = Integer.parseInt((String)WheelDatePicker.this.mMonthList.get(paramAnonymousInt));
                WheelDatePicker.this.updateDayLv(i);
            }
        });
        updateDayLv(this.mMonth);
    }

    private void initView()
    {
        LinearLayout localLinearLayout = new LinearLayout(getContext());
        localLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        addView(localLinearLayout, new RelativeLayout.LayoutParams(-2, -2));
        this.mYearLv = new LoopView(getContext());
        this.mMonthLv = new LoopView(getContext());
        this.mDayLv = new LoopView(getContext());
        this.mYearLv.setTextSize(24.0F);
        this.mYearLv.setViewPadding(6, 0, 6, 0);
        this.mMonthLv.setTextSize(24.0F);
        this.mMonthLv.setViewPadding(6, 0, 6, 0);
        this.mDayLv.setTextSize(24.0F);
        this.mDayLv.setViewPadding(6, 0, 6, 0);
        LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-2, -2);
        localLayoutParams.setMargins(30, 0, 30, 0);
        localLinearLayout.addView(this.mYearLv);
        localLinearLayout.addView(this.mMonthLv, localLayoutParams);
        localLinearLayout.addView(this.mDayLv);
        if ((this.mYear == -1) || (this.mMonth == -1) || (this.mDay == -1))
        {
            Calendar localCalendar = Calendar.getInstance();
            this.mYear = localCalendar.get(Calendar.YEAR);
            this.mMonth = (1 + localCalendar.get(Calendar.MONTH));
            this.mDay = localCalendar.get(Calendar.DAY_OF_MONTH);
        }
        initYearLv();
        initMonthLv();
    }

    private void initYearLv()
    {
        int i = 0;
        this.mYearList.clear();
        for (int j = 2000; j <= 2100; j++)
        {
            this.mYearList.add(Integer.toString(j));
            if (j == this.mYear)
                i = j - 2000;
        }
        this.mYearLv.setItems(this.mYearList);
        this.mYearLv.setInitPosition(i);
    }

    private void updateDayLv(int paramInt)
    {
        Calendar localCalendar = Calendar.getInstance();
        localCalendar.set(this.mYear, paramInt - 1, this.mDay);
        int i = localCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int j = 0;
        this.mDayList.clear();
        for (int k = 1; k <= i; k++)
        {
            this.mDayList.add(Integer.toString(k));
            if (k == this.mDay)
                j = k - 1;
        }
        this.mDayLv.setItems(this.mDayList);
        this.mDayLv.setInitPosition(j);
    }

    public int getDay()
    {
        int i = this.mDayLv.getSelectedItem();
        return Integer.parseInt((String)this.mDayList.get(i));
    }

    public int getMonth()
    {
        int i = this.mMonthLv.getSelectedItem();
        return Integer.parseInt((String)this.mMonthList.get(i));
    }

    public int getYear()
    {
        int i = this.mYearLv.getSelectedItem();
        return Integer.parseInt((String)this.mYearList.get(i));
    }

    public void init(int paramInt1, int paramInt2, int paramInt3)
    {
        this.mYear = paramInt1;
        this.mMonth = paramInt2;
        this.mDay = paramInt3;
        initYearLv();
        initMonthLv();
    }

    public void init(Calendar paramCalendar)
    {
        if (paramCalendar == null)
            return;
        this.mYear = paramCalendar.get(Calendar.YEAR);
        this.mMonth = (1 + paramCalendar.get(Calendar.MONTH));
        this.mDay = paramCalendar.get(Calendar.DAY_OF_MONTH);
        initYearLv();
        initMonthLv();
    }

    public void setVisibleItems(int paramInt)
    {
        this.mYearLv.setVisibleItems(paramInt);
        this.mMonthLv.setVisibleItems(paramInt);
        this.mDayLv.setVisibleItems(paramInt);
    }

    public void setWheelMargin(int paramInt)
    {
        if (this.mMonthLv != null)
        {
            LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)this.mMonthLv.getLayoutParams();
            localLayoutParams.setMargins(paramInt, 0, paramInt, 0);
            this.mMonthLv.setLayoutParams(localLayoutParams);
        }
    }
}