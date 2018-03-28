package com.duoker.watch.ui.view.pagerdatepicker;

/**
 * Created by cheng on 2017/10/15.
 */


import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.duoker.watch.R;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateRecyclerView extends RecyclerView
{
    private static final String TAG = DateRecyclerView.class.getSimpleName();
    private DefaultDateAdapter mAdapter;
    private List<DateHelper.DateItem> mDateItems;
    private DescEntity mDescEntity = new DescEntity();
    private OnItemClickListener mOnItemClickListener;
    private OnItemSelectedListener mOnItemSelectedListener;
    private GradientDrawable mSelectedDrawable;
    private PickerTypeEnum mType = PickerTypeEnum.DAY;

    public DateRecyclerView(Context paramContext)
    {
        this(paramContext, null);
    }

    public DateRecyclerView(Context paramContext, @Nullable AttributeSet paramAttributeSet)
    {
        this(paramContext, paramAttributeSet, 0);
    }

    public DateRecyclerView(Context paramContext, @Nullable AttributeSet paramAttributeSet, int paramInt)
    {
        super(paramContext, paramAttributeSet, paramInt);
        init();
    }

    private List<DateHelper.DateItem> getDayData()
    {
        Calendar calendar = Calendar.getInstance();
        Date localDate = calendar.getTime();
        calendar.add(Calendar.MONTH, -2);
        return DateHelper.getDaysBetweenStartAndEnd(calendar.getTime(), localDate);
    }

    private List<DateHelper.DateItem> getMonthData()
    {
        Calendar calendar = Calendar.getInstance();
        Date localDate = calendar.getTime();
        calendar.add(Calendar.YEAR, -1);
        return DateHelper.getMonth(calendar.getTime(), localDate);
    }

    private List<DateHelper.DateItem> getWeekData()
    {
        Calendar calendar = Calendar.getInstance();
        Date localDate = calendar.getTime();
        calendar.add(Calendar.MONTH, -4);
        return DateHelper.getWeek(calendar.getTime(), localDate);
    }

    private void init()
    {
        LinearLayoutManager localLinearLayoutManager = new LinearLayoutManager(getContext());
        localLinearLayoutManager.setOrientation(0);
        setLayoutManager(localLinearLayoutManager);
        this.mSelectedDrawable = ((GradientDrawable)getContext().getResources().getDrawable(R.drawable.ec_pager_date_picker_circle_bg));
    }

    public DefaultDateAdapter getAdapter()
    {
        return this.mAdapter;
    }

    public void setAdapter()
    {
        DefaultDateAdapter localDefaultDateAdapter = new DefaultDateAdapter(getContext());
        localDefaultDateAdapter.setOnItemSelectedListener(new DefaultDateAdapter.OnOperateListener()
        {
            public void onItemClick(DateHelper.DateItem paramAnonymousDateItem, int pos)
            {
                if (DateRecyclerView.this.mOnItemClickListener != null)
                    DateRecyclerView.this.mOnItemClickListener.onItemClick(paramAnonymousDateItem, pos);
            }

            public void onItemSelected(DateHelper.DateItem paramAnonymousDateItem, int pos)
            {
                if (DateRecyclerView.this.mOnItemSelectedListener != null)
                    DateRecyclerView.this.mOnItemSelectedListener.onItemSelected(paramAnonymousDateItem, pos);
            }
        });
        localDefaultDateAdapter.setDescEntity(this.mDescEntity);
        localDefaultDateAdapter.setType(this.mType);
        localDefaultDateAdapter.setSelectedDrawable(this.mSelectedDrawable);
        if (this.mType == PickerTypeEnum.DAY)
            this.mDateItems = getDayData();
        else
        {
            if (this.mType == PickerTypeEnum.WEEK)
                this.mDateItems = getWeekData();
            else if (this.mType == PickerTypeEnum.MONTH)
                this.mDateItems = getMonthData();
            else
                this.mDateItems = getDayData();
        }

        setAdapter(localDefaultDateAdapter);
        this.mAdapter.setData(this.mDateItems);
        setSelectionPosition(-1 + this.mDateItems.size());

    }

    public void setAdapter(DefaultDateAdapter paramDefaultDateAdapter)
    {
        this.mAdapter = paramDefaultDateAdapter;
        super.setAdapter(paramDefaultDateAdapter);
    }

    public void setDescEntity(DescEntity paramDescEntity)
    {
        this.mDescEntity = paramDescEntity;
    }

    public void setOnItemClickListener(OnItemClickListener paramOnItemClickListener)
    {
        this.mOnItemClickListener = paramOnItemClickListener;
    }

    public void setOnItemSelectedListener(OnItemSelectedListener paramOnItemSelectedListener)
    {
        this.mOnItemSelectedListener = paramOnItemSelectedListener;
    }

    public void setSelectedColor(@ColorInt int paramInt)
    {
        if (this.mSelectedDrawable != null)
            this.mSelectedDrawable.setColor(paramInt);
    }

    public void setSelectionPosition(int paramInt)
    {
        this.mAdapter.setSelectionPosition(paramInt);
        scrollToPosition(paramInt);
    }

    public void setType(PickerTypeEnum paramPickerTypeEnum)
    {
        this.mType = paramPickerTypeEnum;
    }

    public interface OnItemClickListener
    {
        void onItemClick(DateHelper.DateItem paramDateItem, int paramInt);
    }

    public interface OnItemSelectedListener
    {
       void onItemSelected(DateHelper.DateItem paramDateItem, int paramInt);
    }
}
