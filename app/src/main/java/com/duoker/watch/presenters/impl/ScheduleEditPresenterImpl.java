package com.duoker.watch.presenters.impl;

/**
 * Created by cheng on 2017/10/7.
 */

import android.content.Context;
import android.text.TextUtils;

import com.duoker.watch.R;
import com.duoker.watch.interactors.base.SimpleCallback;
import com.duoker.watch.interactors.base.SimpleErrorBundle;
import com.duoker.watch.interactors.impl.DelScheduleInteractorImpl;
import com.duoker.watch.interactors.impl.EditScheduleInteractorImpl;
import com.duoker.watch.model.ScheduleModel;
import com.duoker.watch.presenters.ScheduleEditPresenter;
import com.duoker.watch.repository.ScheduleRepository;
import com.duoker.watch.ui.component.WeekChoose2CompHelper;
import com.duoker.watch.ui.event.FlushSchedulesEvent;
import com.duoker.watch.utils.MyDateUtils;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;
import java.util.Date;

public class ScheduleEditPresenterImpl extends ScheduleEditPresenter {
    private final String DATE_FORMAT = "yyyy-MM-dd";
    private final String DATE_FORMAT_2 = "MM/dd";
    private final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private final String DATE_TIME_FORMAT_2 = "yyyy-MM-dd HH:mm";
    private final String TIME_FORMAT = "HH:mm:ss";
    private final String TIME_FORMAT_2 = "HH:mm";

    private final ScheduleEditPresenter.View iView;
    private Calendar mCalendar;
    private final Context mContext;
    private int mDay;
    private int mHour;
    private int mMinute;
    private int mMonth;
    private int mRepeat = 0;
    private ScheduleModel mScheduleModel;
    private final ScheduleRepository mScheduleRepository;
    private int mSecond;
    private int mStatus = 1;
    private int mWeekData = 0;
    private int mYear;

    public ScheduleEditPresenterImpl(ScheduleEditPresenter.View paramView, Context paramContext, ScheduleRepository paramScheduleRepository) {
        this.iView = paramView;
        this.mContext = paramContext.getApplicationContext();
        this.mScheduleRepository = paramScheduleRepository;
    }

    private void inflateNoRepeat(Calendar paramCalendar) {
        Date localDate = paramCalendar.getTime();
        String str1 = DateFormatUtils.format(localDate, "MM/dd");
        String str2 = MyDateUtils.getWeekDesc(this.mContext, localDate);
        String str3 = str1 + " " + str2;
        this.iView.setNoRepeatDateText(str3);
        String str4 = DateFormatUtils.format(localDate, "HH:mm");
        this.iView.setNoRepeatTimeText(str4);
        this.iView.setRepeatTimeText(str4);
        this.iView.setRepeatText(this.mContext.getString(R.string.schedule_day_tip1));
    }

    private void inflateRepeat(Calendar paramCalendar) {
        String str = DateFormatUtils.format(paramCalendar.getTime(), "HH:mm");
        this.iView.setRepeatTimeText(str);
        this.iView.setNoRepeatTimeText(str);
    }

    private void setupDateTime(Calendar paramCalendar) {
        this.mYear = paramCalendar.get(Calendar.YEAR);
        this.mMonth = (1 + paramCalendar.get(Calendar.MONTH));
        this.mDay = paramCalendar.get(Calendar.DATE);
        this.mHour = paramCalendar.get(Calendar.HOUR_OF_DAY);
        this.mMinute = paramCalendar.get(Calendar.MINUTE);
        this.mSecond = paramCalendar.get(Calendar.SECOND);
    }

    public void clickChooseDateTimeLayout() {
        if (this.mRepeat == 1) {
            this.iView.showTimeDialog(this.mHour, this.mMinute, this.mSecond);
            return;
        }
        this.iView.showDateTimeDialog(this.mYear, this.mMonth, this.mDay, this.mHour, this.mMinute, this.mSecond);
    }

    public void delSchedule() {
        this.iView.showProgress();
        new DelScheduleInteractorImpl(this.mScheduleModel, this.mScheduleRepository).execute(new SimpleCallback<Object>() {
            public void onError(SimpleErrorBundle paramAnonymousDefaultErrorBundle) {
                iView.showToast(R.string.schedule_add_tip_4);
                iView.hideProgress();
            }

            public void onSuccess(Object o) {
                iView.showToast(R.string.schedule_add_tip_3);
                iView.hideProgress();
                EventBus.getDefault().post(new FlushSchedulesEvent());
                iView.finishActivity();
            }
        });
    }

    public void editSchedule(String paramString) {
        Date localDate = this.mCalendar.getTime();

        if (TextUtils.isEmpty(paramString))
        {
            this.iView.showToast(R.string.schedule_add_tip_1);
            return;
        }
        String time = MyDateUtils.getDate2Str(localDate, "HH:mm:ss");

        if (TextUtils.isEmpty(time)) {
            this.iView.showToast(R.string.schedule_add_tip_2);
            return;
        }
        time = MyDateUtils.getDate2Str(localDate, "yyyy-MM-dd HH:mm:ss");
        if (this.mRepeat == 1) ;

        mScheduleModel.setSchedulerepeat(this.mRepeat);
        mScheduleModel.setSchedulecontent(paramString);
        mScheduleModel.setScheduletime(time);
        mScheduleModel.setScheduleweek(this.mWeekData);
        mScheduleModel.setSchedulestatus(this.mStatus);
        iView.showProgress();

        new EditScheduleInteractorImpl(this.mScheduleModel, this.mScheduleRepository).execute(new SimpleCallback<Object>() {
            @Override
            public void onError(SimpleErrorBundle paramAnonymousDefaultErrorBundle) {
                iView.showToast(R.string.schedule_add_tip_4);
                iView.hideProgress();
            }

            @Override
            public void onSuccess(Object paramAnonymousObject) {
                iView.showToast(R.string.schedule_add_tip_3);
                iView.hideProgress();
                EventBus.getDefault().post(new FlushSchedulesEvent());
                iView.finishActivity();
            }
        });
    }

    public int getWeekData() {
        return this.mWeekData;
    }

    public void initData(ScheduleModel paramScheduleModel) {
        String str1;
        int i;
        if (paramScheduleModel != null) {
            mScheduleModel = paramScheduleModel;
            iView.setContentText(paramScheduleModel.getSchedulecontent());
            mStatus = paramScheduleModel.getSchedulestatus();
            setWeekData(paramScheduleModel.getScheduleweek());
            mRepeat = mScheduleModel.getSchedulerepeat();
            str1 = mScheduleModel.getScheduletime();
            if (mRepeat != 1) {
                try {
                    Date localDate1 = org.apache.commons.lang3.time.DateUtils.parseDate(str1, new String[]{"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd", "HH:mm:ss", "yyyy-MM-dd HH:mm", "HH:mm"});
                    mCalendar = Calendar.getInstance();
                    mCalendar.setTime(localDate1);
                    setupDateTime(mCalendar);
                    inflateNoRepeat(mCalendar);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    mCalendar = Calendar.getInstance();
                    setupDateTime(mCalendar);
                }
            }
            iView.setNoRepeatDateText("");
            i = paramScheduleModel.getScheduleweek();
            if (!WeekChoose2CompHelper.isSelectEveryday(i)) {
                if (WeekChoose2CompHelper.isSelectWorkday(i)) {
                    iView.setRepeatText(mContext.getString(R.string.schedule_day_tip3));
                } else {
                    String str2 = WeekChoose2CompHelper.getWeekDesc(mContext, i);
                    iView.setRepeatText(str2);
                }
            } else {
                iView.setRepeatText(mContext.getString(R.string.schedule_day_tip2));
            }
            try {
                Date localDate2 = org.apache.commons.lang3.time.DateUtils.parseDate(str1, new String[]{"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd", "HH:mm:ss", "yyyy-MM-dd HH:mm", "HH:mm"});
                mCalendar = Calendar.getInstance();
                mCalendar.setTime(localDate2);
                setupDateTime(mCalendar);
                inflateRepeat(mCalendar);
            } catch (Exception e) {
                e.printStackTrace();
                mCalendar = Calendar.getInstance();
                setupDateTime(mCalendar);
            }
        }
    }

    public void setHMS(int paramInt1, int paramInt2, int paramInt3) {
        mHour = paramInt1;
        mMinute = paramInt2;
        mSecond = paramInt3;
        mCalendar.set(Calendar.HOUR_OF_DAY, mHour);
        mCalendar.set(Calendar.MINUTE, mMinute);
        mCalendar.set(Calendar.SECOND, mSecond);
        inflateRepeat(mCalendar);
    }

    public void setRepeat(int paramInt) {
        mRepeat = paramInt;
    }

    public void setWeekData(int paramInt) {
        mWeekData = paramInt;
        if (paramInt == 0) {
            setRepeat(0);
            iView.setNoRepeatDateVisible(true);
            iView.setNoRepeatTimeVisible(true);
            iView.setRepeatTimeVisible(false);
            return;
        }
        setRepeat(1);
        iView.setNoRepeatDateVisible(false);
        iView.setNoRepeatTimeVisible(false);
        iView.setRepeatTimeVisible(true);
    }

    public void setYMDHMS(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
        mYear = paramInt1;
        mMonth = paramInt2;
        mDay = paramInt3;
        mHour = paramInt4;
        mMinute = paramInt5;
        mSecond = paramInt6;
        mCalendar.set(Calendar.YEAR, mYear);
        mCalendar.set(Calendar.MONTH, -1 + mMonth);
        mCalendar.set(Calendar.DATE, mDay);
        mCalendar.set(Calendar.HOUR_OF_DAY, mHour);
        mCalendar.set(Calendar.MINUTE, mMinute);
        mCalendar.set(Calendar.SECOND, mSecond);
        inflateNoRepeat(mCalendar);
    }
}