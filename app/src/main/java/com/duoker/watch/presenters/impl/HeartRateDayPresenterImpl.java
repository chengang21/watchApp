package com.duoker.watch.presenters.impl;

/**
 * Created by cheng on 2017/10/9.
 */

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

import com.duoker.watch.DuokerWatchApp;
import com.duoker.watch.R;
import com.duoker.watch.interactors.base.SimpleCallback;
import com.duoker.watch.interactors.base.SimpleErrorBundle;
import com.duoker.watch.interactors.impl.GetHeartRateInteractorImpl;
import com.duoker.watch.model.HeartRateBean;
import com.duoker.watch.model.HeartRateViewModel;
import com.duoker.watch.presenters.HeartRateDayPresenter;
import com.duoker.watch.repository.HeartRateRepository;
import com.duoker.watch.ui.view.pagerdatepicker.DateHelper;
import org.apache.commons.lang3.time.DateUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class HeartRateDayPresenterImpl extends HeartRateDayPresenter
{
    private float allRecord;
    private float averageRecord;
    private float highRecord;
    private final HeartRateDayPresenter.View iView;
    private float lowRecord;
    private Comparator<HeartRateBean> mComparator = new Comparator<HeartRateBean>() {
        @Override
        public int compare(HeartRateBean paramAnonymousHeartRateBean1, HeartRateBean paramAnonymousHeartRateBean2)
        {
            long l1 = paramAnonymousHeartRateBean1.getCalcTime();
            long l2 = paramAnonymousHeartRateBean2.getCalcTime();
            if (l1 > l2)
                return 1;
            if (l1 == l2)
                return 0;
            return -1;
        }
    };
    private final Context mContext;
    private List<HeartRateViewModel> mDataList = new ArrayList();
    private Handler mHandler = new Handler();
    private final HeartRateRepository mHeartRateRepository;
    private Date mNowDate = new Date();

    public HeartRateDayPresenterImpl(HeartRateDayPresenter.View paramView, Context paramContext, HeartRateRepository paramHeartRateRepository)
    {
        this.iView = paramView;
        this.mContext = paramContext;
        this.mHeartRateRepository = paramHeartRateRepository;
    }

    private List<HeartRateBean> calculate(List<HeartRateBean> inputList)
    {
        List<HeartRateBean> outputList;
        if (inputList == null)
            return null;

        outputList = new ArrayList();
        Iterator iterator = inputList.iterator();

        Date thisDay = null;
        int totalHeartRate = 0;
        int cnt = 0;
        int avg = 0;

        while (iterator.hasNext()) {
            HeartRateBean hb = (HeartRateBean) iterator.next();
            int rate = hb.getHeartRate();

            Date date = new Date(1000L * hb.getCalcTime());
            if (thisDay == null) {
                thisDay = date;
                totalHeartRate = rate;
                cnt = 1;
            } else if (!DateUtils.isSameDay(thisDay, date)) {
                if (cnt != 0) {
                    avg = totalHeartRate / cnt;
                    HeartRateBean avgHB = new HeartRateBean();
                    avgHB.setCalcTime(thisDay.getTime() / 1000L);
                    avgHB.setHeartRate(avg);
                    outputList.add(avgHB);
                }
                thisDay = date;
                totalHeartRate = rate;
                cnt = 1;
            } else {
                totalHeartRate += rate;
                cnt++;
            }
        }

        avg = totalHeartRate / cnt;
        HeartRateBean avgHRB = new HeartRateBean();
        avgHRB.setCalcTime(thisDay.getTime() / 1000L);
        avgHRB.setHeartRate(avg);
        outputList.add(avgHRB);
        return outputList;
    }

    private List<HeartRateViewModel> clearNAddAll(List<HeartRateBean> listToAdd)
    {
        if (this.mDataList == null)
            this.mDataList = new ArrayList();
        else
            this.mDataList.clear();

        HeartRateViewModel heartRateViewModel = new HeartRateViewModel(null, 0);
        this.mDataList.add(heartRateViewModel);

        if ((listToAdd != null) && (!listToAdd.isEmpty())) {
            Iterator it = listToAdd.iterator();
            while (it.hasNext()) {
                HeartRateViewModel tmp = new HeartRateViewModel((HeartRateBean) it.next(), 1);
                this.mDataList.add(tmp);
            }
        }
        return this.mDataList;
    }

    public void getHeartRate()
    {
        DateHelper.BeginNEndItem localBeginNEndItem = DateHelper.getDayBeginNEnd(this.mNowDate);
        long starttime = localBeginNEndItem.getBeginTime().getTime() / 1000L;
        long endtime = localBeginNEndItem.getEndTime().getTime() / 1000L;
        String userid = DuokerWatchApp.getInstance().getLoginUser().getUserid();
        String watchId = DuokerWatchApp.getInstance().getDefaultWatch().getWatchId();
        this.iView.showProgress();
        new GetHeartRateInteractorImpl(userid, watchId, starttime, endtime, this.mHeartRateRepository).execute(new SimpleCallback<List<HeartRateBean>>()
        {
            public void onError(SimpleErrorBundle paramAnonymousDefaultErrorBundle)
            {
                iView.hideProgress();
                iView.showToast(R.string.heart_rate_get_fail);
                iView.resetText();
                iView.resetRecordChart();
            }

            public void onSuccess(List<HeartRateBean> heartRateBeanList)
            {
                iView.hideProgress();
                allRecord = 0F;
                averageRecord = 0F;
                highRecord = 0F;
                lowRecord = 0F;
                if ((heartRateBeanList != null) && (!heartRateBeanList.isEmpty())) {
                    Collections.sort(heartRateBeanList, mComparator);
                    int cnt = heartRateBeanList.size();

                    for (int j = 0; j < cnt; j++) {
                        int heartRate = ((HeartRateBean) heartRateBeanList.get(j)).getHeartRate();

                        if (j == 0)
                            lowRecord = heartRate;

                        allRecord += heartRate;

                        if (lowRecord > heartRate)
                            lowRecord = heartRate;

                        if (highRecord < heartRate)
                            heartRate = heartRate;
                    }
                    averageRecord = allRecord / cnt;
                    averageRecord = Math.round(10.0F * averageRecord) / 10.0F;
                } else {
                    iView.showToast(R.string.heart_rate_get_no_data);
                    iView.resetText();
                }
                iView.setAverageText(Integer.toString((int) averageRecord));
                iView.setHighText(Integer.toString((int) highRecord));
                iView.setLowText(Integer.toString((int) lowRecord));
                iView.inflateListView(clearNAddAll(heartRateBeanList));
                iView.resetRecordChart();

                List list = calculate(heartRateBeanList);
                iView.drawRecordChart(list);
            }
        });
    }

    public void onCreate(Bundle paramBundle)
    {
        this.mHandler.post(new Runnable()
        {
            public void run()
            {
                getHeartRate();
            }
        });
    }

    public void setDate(Date paramDate)
    {
        this.mNowDate = paramDate;
    }
}
