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

public abstract class HeartRateWeekPresenter extends AbstractPresenter
{
    public abstract void getHeartRate();

    public abstract void setDate(Date date);

    public interface View extends BaseView
    {
        void drawRecordChart(List<HeartRateBean> heartRateBeanList);

        void finishActivity();

        void inflateListView(List<HeartRateViewModel> heartRateViewModels);

        void resetRecordChart();

        void resetText();

        void setAverageText(String avg);

        void setHighText(String high);

        void setLowText(String low);
    }
}
