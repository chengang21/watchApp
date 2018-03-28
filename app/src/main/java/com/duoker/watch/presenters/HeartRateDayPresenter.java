package com.duoker.watch.presenters;

import com.duoker.watch.model.HeartRateBean;
import com.duoker.watch.model.HeartRateViewModel;
import com.duoker.watch.presenters.base.AbstractPresenter;
import com.duoker.watch.ui.BaseView;

import java.util.Date;
import java.util.List;

/**
 * Created by cheng on 2017/10/9.
 */

public abstract class HeartRateDayPresenter extends AbstractPresenter
{
    public abstract void getHeartRate();

    public abstract void setDate(Date date);

    public  interface View extends BaseView
    {
        void drawRecordChart(List<HeartRateBean> heartRateBeanList);

        void finishActivity();

        void inflateListView(List<HeartRateViewModel> heartRateViewModelList);

        void resetRecordChart();

        void resetText();

        void setAverageText(String avg);

        void setHighText(String high);

        void setLowText(String low);
    }
}
