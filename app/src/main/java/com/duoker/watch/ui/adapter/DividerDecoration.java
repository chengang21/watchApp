package com.duoker.watch.ui.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by cheng on 2017/10/15.
 */

public class DividerDecoration extends RecyclerView.ItemDecoration
{
    private static final int[] ATTRS = { 16843284 };
    public static final int HORIZONTAL_LIST = 0;
    public static final int VERTICAL_LIST = 1;
    private Drawable mDivider;

    public DividerDecoration(Context paramContext)
    {
        TypedArray localTypedArray = paramContext.obtainStyledAttributes(ATTRS);
        this.mDivider = localTypedArray.getDrawable(0);
        localTypedArray.recycle();
    }

    private int getOrientation(RecyclerView paramRecyclerView)
    {
        try
        {
            LinearLayoutManager localLinearLayoutManager = (LinearLayoutManager)paramRecyclerView.getLayoutManager();
            return localLinearLayoutManager.getOrientation();
        }
        catch (ClassCastException localClassCastException)
        {
            throw new IllegalStateException("DividerDecoration can only be used with a LinearLayoutManager.", localClassCastException);
        }
    }

    public void drawHorizontal(Canvas paramCanvas, RecyclerView paramRecyclerView)
    {
        int i = paramRecyclerView.getPaddingTop();
        int j = paramRecyclerView.getHeight() - paramRecyclerView.getPaddingBottom();
        int k = paramRecyclerView.getPaddingLeft();
        int m = paramRecyclerView.getWidth() - paramRecyclerView.getPaddingRight();
        int n = paramRecyclerView.getChildCount();
        for (int i1 = 0; i1 < n; i1++)
        {
            View localView = paramRecyclerView.getChildAt(i1);
            RecyclerView.LayoutParams localLayoutParams = (RecyclerView.LayoutParams)localView.getLayoutParams();
            int i2 = Math.max(k, localView.getRight() + localLayoutParams.rightMargin);
            int i3 = Math.min(m, i2 + this.mDivider.getIntrinsicHeight());
            this.mDivider.setBounds(i2, i, i3, j);
            this.mDivider.draw(paramCanvas);
        }
    }

    public void drawVertical(Canvas paramCanvas, RecyclerView paramRecyclerView)
    {
        int i = paramRecyclerView.getPaddingLeft();
        int j = paramRecyclerView.getWidth() - paramRecyclerView.getPaddingRight();
        int k = paramRecyclerView.getPaddingTop();
        int m = paramRecyclerView.getHeight() - paramRecyclerView.getPaddingBottom();
        int n = paramRecyclerView.getChildCount();
        for (int i1 = 0; i1 < n; i1++)
        {
            View localView = paramRecyclerView.getChildAt(i1);
            RecyclerView.LayoutParams localLayoutParams = (RecyclerView.LayoutParams)localView.getLayoutParams();
            int i2 = Math.max(k, localView.getBottom() + localLayoutParams.bottomMargin);
            int i3 = Math.min(m, i2 + this.mDivider.getIntrinsicHeight());
            this.mDivider.setBounds(i, i2, j, i3);
            this.mDivider.draw(paramCanvas);
        }
    }

    public void getItemOffsets(Rect paramRect, View paramView, RecyclerView paramRecyclerView, RecyclerView.State paramState)
    {
        super.getItemOffsets(paramRect, paramView, paramRecyclerView, paramState);
        if (getOrientation(paramRecyclerView) == 1)
        {
            paramRect.set(0, 0, 0, this.mDivider.getIntrinsicHeight());
            return;
        }
        paramRect.set(0, 0, this.mDivider.getIntrinsicWidth(), 0);
    }

    public void onDraw(Canvas paramCanvas, RecyclerView paramRecyclerView, RecyclerView.State paramState)
    {
        super.onDraw(paramCanvas, paramRecyclerView, paramState);
        if (getOrientation(paramRecyclerView) == 1)
        {
            drawVertical(paramCanvas, paramRecyclerView);
            return;
        }
        drawHorizontal(paramCanvas, paramRecyclerView);
    }
}
