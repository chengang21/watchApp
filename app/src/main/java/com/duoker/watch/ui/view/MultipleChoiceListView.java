package com.duoker.watch.ui.view;

/**
 * Created by chengang on 4/23/2017.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MultipleChoiceListView extends ListView
{
    private BaseAdapter mAdapter;
    private int mCount = 0;
    private boolean mMultiSelectMode = false;
    private Map<Integer, Boolean> mMultiSelectPositionMap = new HashMap();
    private OnItemClickListener mOnItemClickListener;
    private OnMultipleChangeListener mOnMultipleChangeListener;

    public MultipleChoiceListView(Context paramContext)
    {
        super(paramContext);
        init();
    }

    public MultipleChoiceListView(Context paramContext, AttributeSet paramAttributeSet)
    {
        super(paramContext, paramAttributeSet);
        init();
    }

    public MultipleChoiceListView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
    {
        super(paramContext, paramAttributeSet, paramInt);
        init();
    }

    private void init()
    {
        super.setOnItemClickListener( new OnItemClickListener() {
            public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong) {
                //CG
            }
        });
    }

    public boolean getMultipleMode()
    {
        return this.mMultiSelectMode;
    }

    public List<Integer> getPositionsSelectedMultiple()
    {
        ArrayList localArrayList = new ArrayList();
        Iterator localIterator = this.mMultiSelectPositionMap.keySet().iterator();
        while (localIterator.hasNext())
        {
            int i = ((Integer)localIterator.next()).intValue();
            if (isPositionSelectedMultiple(i))
                localArrayList.add(Integer.valueOf(i));
        }
        return localArrayList;
    }

    public int getPositionsSelectedMultipleCount()
    {
        return this.mCount;
    }

    public boolean isPositionSelectedMultiple(int paramInt)
    {
        if (this.mMultiSelectPositionMap == null)
            this.mMultiSelectPositionMap = new HashMap();
        Boolean localBoolean = (Boolean)this.mMultiSelectPositionMap.get(Integer.valueOf(paramInt));
        if (localBoolean == null)
            localBoolean = Boolean.valueOf(false);
        return localBoolean.booleanValue();
    }

    public void selectAllPositionMultiple(boolean paramBoolean)
    {
        int i = getCount();
        for (int j = 0; j < i; j++)
            selectPositionMultiple(j, paramBoolean);
        if (this.mAdapter != null)
            this.mAdapter.notifyDataSetChanged();
    }

    public void selectPositionMultiple(int paramInt, boolean paramBoolean)
    {
        if (isPositionSelectedMultiple(paramInt) == paramBoolean);
        while (true)
        {
            return;
            /*
            this.mMultiSelectPositionMap.put(Integer.valueOf(paramInt), Boolean.valueOf(paramBoolean));
            if (paramBoolean)
                this.mCount = (1 + this.mCount);
            while (this.mOnMultipleChangeListener != null)
            {
                this.mOnMultipleChangeListener.onMultipleItemChanged(paramBoolean, paramInt, getPositionsSelectedMultipleCount());
                return;
                if (this.mCount > 0)
                    this.mCount = (-1 + this.mCount);
            } */
        }
    }

    public void setAdapter(ListAdapter paramListAdapter)
    {
        super.setAdapter(paramListAdapter);
        if ((paramListAdapter instanceof BaseAdapter))
            this.mAdapter = ((BaseAdapter)paramListAdapter);
    }

    public void setMultipleMode(boolean paramBoolean)
    {
        if (this.mMultiSelectMode == paramBoolean);
        while (true)
        {
            return;
            /*
            this.mMultiSelectPositionMap.clear();
            this.mCount = 0;
            this.mMultiSelectMode = paramBoolean;
            if (this.mAdapter != null)
                this.mAdapter.notifyDataSetChanged();
            while (this.mOnMultipleChangeListener != null)
            {
                this.mOnMultipleChangeListener.onMultipleModeChange(this.mMultiSelectMode);
                return;
                requestLayout();
            }
            */
        }
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener paramOnItemClickListener)
    {
        this.mOnItemClickListener = paramOnItemClickListener;
    }

    public void setOnMultipleChangeListener(OnMultipleChangeListener paramOnMultipleChangeListener)
    {
        this.mOnMultipleChangeListener = paramOnMultipleChangeListener;
    }

    public static abstract interface OnMultipleChangeListener
    {
        public abstract void onMultipleItemChanged(boolean paramBoolean, int paramInt1, int paramInt2);

        public abstract void onMultipleItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong);

        public abstract void onMultipleModeChange(boolean paramBoolean);
    }
}