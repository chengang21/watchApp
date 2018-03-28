package com.duoker.watch.ui.pop;

/**
 * Created by cheng on 2017/10/8.
 */

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.PopupWindow;

import com.duoker.watch.R;

public class HeartRateChooseViewPopupWindow extends PopupWindow {
    private OnClickListener listener;
    private final Context mContext;
    private View mDayView;
    private View mMonthView;
    private View mWeekView;

    public HeartRateChooseViewPopupWindow(Context paramContext) {
        super(paramContext);
        this.mContext = paramContext;
        initView();
        initListener();
    }


    private void initListener() {
        this.mDayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listener != null)
                    listener.onClickDay();
            }
        });
        this.mWeekView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listener != null)
                    listener.onClickWeek();
            }
        });
        this.mMonthView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onClickMonth();
            }
        });
    }

    private void initView() {
        View view = View.inflate(this.mContext, R.layout.popup_heart_rate_choose_view, null);
        this.mDayView = view.findViewById(R.id.day_view);
        this.mWeekView = view.findViewById(R.id.week_view);
        this.mMonthView = view.findViewById(R.id.month_view);
        setContentView(view);
        setWidth(-2);
        setHeight(-2);
        setFocusable(true);
        setBackgroundDrawable(new ColorDrawable(0));
    }

    public void setOnClickListener(OnClickListener paramOnClickListener) {
        this.listener = paramOnClickListener;
    }

    public void showPopup(View paramView) {
        showAsDropDown(paramView, 0, 0);
        update();
    }

    public interface OnClickListener {
        void onClickDay();

        void onClickMonth();

        void onClickWeek();
    }
}
