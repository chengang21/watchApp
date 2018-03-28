package com.duoker.watch.presenters;

/**
 * Created by cheng on 2017/10/8.
 */

import com.duoker.watch.model.HeartRateBean;
import com.duoker.watch.model.HeartRateViewModel;
import com.duoker.watch.presenters.base.AbstractPresenter;
import com.duoker.watch.ui.BaseView;

import java.util.Date;
import java.util.List;

public abstract class HeartRateMonthPresenter extends AbstractPresenter
{
    public abstract void getHeartRate();

    public abstract void setDate(Date paramDate);

    public static abstract interface View extends BaseView
    {
        public abstract void drawRecordChart(List<HeartRateBean> paramList);

        public abstract void finishActivity();

        public abstract void inflateListView(List<HeartRateViewModel> paramList);

        public abstract void resetRecordChart();

        public abstract void resetText();

        public abstract void setAverageText(String paramString);

        public abstract void setHighText(String paramString);

        public abstract void setLowText(String paramString);
    }
}
