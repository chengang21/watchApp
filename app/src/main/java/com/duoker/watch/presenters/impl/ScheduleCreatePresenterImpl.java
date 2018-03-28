package com.duoker.watch.presenters.impl;

import android.content.Context;
import android.text.TextUtils;

import com.duoker.watch.DuokerWatchApp;
import com.duoker.watch.R;
import com.duoker.watch.interactors.base.SimpleCallback;
import com.duoker.watch.interactors.base.SimpleErrorBundle;
import com.duoker.watch.interactors.impl.AddScheduleInteractorImpl;
import com.duoker.watch.model.ScheduleModel;
import com.duoker.watch.presenters.ScheduleCreatePresenter;
import com.duoker.watch.repository.ScheduleRepository;
import com.duoker.watch.ui.component.WeekChoose2CompHelper;
import com.duoker.watch.ui.event.FlushSchedulesEvent;
import com.duoker.watch.utils.MyDateUtils;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by cheng on 2017/10/6.
 */

public class ScheduleCreatePresenterImpl extends ScheduleCreatePresenter {
    private final String DATE_FORMAT = "yyyy-MM-dd";
    private final String DATE_FORMAT_2 = "MM/dd";
    private final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private final String DATE_TIME_FORMAT_2 = "yyyy-MM-dd HH:mm";
    private final String TIME_FORMAT = "HH:mm:ss";
    private final String TIME_FORMAT_2 = "HH:mm";

    private final ScheduleCreatePresenter.View iView;
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

    public ScheduleCreatePresenterImpl(ScheduleCreatePresenter.View view, Context context, ScheduleRepository repository) {
        iView = view;
        mContext = context.getApplicationContext();
        mScheduleRepository = repository;
    }

    private void inflateNoRepeat(Calendar paramCalendar) {
        Date localDate = paramCalendar.getTime();
        String str1 = DateFormatUtils.format(localDate, "MM/dd");
        String str2 = MyDateUtils.getWeekDesc(this.mContext, localDate);
        String str3 = str1 + " " + str2;
        iView.setNoRepeatDateText(str3);
        String str4 = DateFormatUtils.format(localDate, "HH:mm");
        iView.setNoRepeatTimeText(str4);
        iView.setRepeatTimeText(str4);
        iView.setRepeatText(this.mContext.getString(R.string.schedule_day_tip1));
    }

    private void inflateRepeat(Calendar paramCalendar) {
        String str = DateFormatUtils.format(paramCalendar.getTime(), "HH:mm");
        iView.setRepeatTimeText(str);
        iView.setNoRepeatTimeText(str);
    }

    private void setupDateTime(Calendar paramCalendar) {
        this.mYear = paramCalendar.get(Calendar.YEAR);
        this.mMonth = (1 + paramCalendar.get(Calendar.MONTH));
        this.mDay = paramCalendar.get(Calendar.DATE);
        this.mHour = paramCalendar.get(Calendar.HOUR_OF_DAY);
        this.mMinute = paramCalendar.get(Calendar.MINUTE);
        this.mSecond = paramCalendar.get(Calendar.SECOND);
    }

    public void addSchedule(String schedule) {
        if (TextUtils.isEmpty(schedule)) {
            iView.showToast(R.string.schedule_add_tip_1);
            return;
        }
        Date date = this.mCalendar.getTime();
        // if (this.mRepeat == 1);
        String str1 = MyDateUtils.getDate2Str(date, "HH:mm:ss");
        str1 = MyDateUtils.getDate2Str(date, "yyyy-MM-dd HH:mm:ss");
        if (TextUtils.isEmpty(str1)) {
            iView.showToast(R.string.schedule_add_tip_2);
            return;
        }
        String str2 = DuokerWatchApp.getInstance().getLoginUser().getUserid();
        String str3 = DuokerWatchApp.getInstance().getDefaultWatch().getWatchId();

        ScheduleModel scheduleModel = new ScheduleModel();
        scheduleModel.setUserId(str2);
        scheduleModel.setWatchId(str3);
        scheduleModel.setScheduleid("0");
        scheduleModel.setSchedulerepeat(this.mRepeat);
        scheduleModel.setSchedulecontent(schedule);
        scheduleModel.setScheduletime(str1);
        scheduleModel.setScheduleweek(this.mWeekData);
        scheduleModel.setSchedulestatus(this.mStatus);
        iView.showProgress();

        new AddScheduleInteractorImpl(scheduleModel, this.mScheduleRepository).execute(new SimpleCallback<Object>() {
            @Override
            public void onSuccess(Object paramT) {
                iView.showToast(R.string.schedule_add_tip_3);
                iView.hideProgress();
                EventBus.getDefault().post(new FlushSchedulesEvent());
                iView.finishActivity();
            }

            @Override
            public void onError(SimpleErrorBundle errorBundle) {
                iView.showToast(R.string.schedule_add_tip_4);
                iView.hideProgress();
            }
        });
    }

    public void clickChooseDateTimeLayout() {
        if (this.mRepeat == 1) {
            iView.showTimeDialog(this.mHour, this.mMinute, this.mSecond);
            return;
        }
        iView.showDateTimeDialog(this.mYear, this.mMonth, this.mDay, this.mHour, this.mMinute, this.mSecond);
    }

    public int getWeekData() {
        return this.mWeekData;
    }

    @Override
    public void initData(ScheduleModel scheduleModel) {
        if (scheduleModel != null) {
            this.mScheduleModel = scheduleModel;
            iView.setContentText(scheduleModel.getSchedulecontent());
            this.mRepeat = this.mScheduleModel.getSchedulerepeat();
            String scheduletime = this.mScheduleModel.getScheduletime();
            if (this.mRepeat == 1) {
                iView.setNoRepeatDateText("");
                int i = scheduleModel.getScheduleweek();
                if (WeekChoose2CompHelper.isSelectEveryday(i))
                    iView.setRepeatText(this.mContext.getString(R.string.schedule_day_tip2));
                else if (WeekChoose2CompHelper.isSelectWorkday(i)) {
                    iView.setRepeatText(this.mContext.getString(R.string.schedule_day_tip3));
                } else {
                    String str2 = WeekChoose2CompHelper.getWeekDesc(this.mContext, i);
                    iView.setRepeatText(str2);
                }
                try {
                    Date localDate2 = DateUtils.parseDate(scheduletime, new String[]{"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd", "HH:mm:ss", "yyyy-MM-dd HH:mm", "HH:mm"});
                    this.mCalendar = Calendar.getInstance();
                    this.mCalendar.setTime(localDate2);
                    setupDateTime(this.mCalendar);
                    inflateRepeat(this.mCalendar);
                } catch (Exception e) {
                    e.printStackTrace();
                    setupDateTime(this.mCalendar);
                    inflateNoRepeat(this.mCalendar);
                }
            }
            try {
                Date localDate1 = DateUtils.parseDate(scheduletime, new String[]{"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd", "HH:mm:ss", "yyyy-MM-dd HH:mm", "HH:mm"});
                this.mCalendar = Calendar.getInstance();
                this.mCalendar.setTime(localDate1);
                setupDateTime(this.mCalendar);
                inflateNoRepeat(this.mCalendar);
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.mCalendar = Calendar.getInstance();
        setupDateTime(this.mCalendar);
        inflateNoRepeat(this.mCalendar);
    }

    public void setHMS(int paramInt1, int paramInt2, int paramInt3) {
        this.mHour = paramInt1;
        this.mMinute = paramInt2;
        this.mSecond = paramInt3;
        this.mCalendar.set(Calendar.HOUR_OF_DAY, this.mHour);
        this.mCalendar.set(Calendar.MINUTE, this.mMinute);
        this.mCalendar.set(Calendar.SECOND, this.mSecond);
        inflateRepeat(this.mCalendar);
    }

    public void setRepeat(int paramInt) {
        this.mRepeat = paramInt;
    }

    public void setWeekData(int weekData) {
        this.mWeekData = weekData;
        if (weekData == 0) {
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

    public void setYMDHMS(int year, int month, int day, int hour, int minute, int sec) {
        this.mYear = year;
        this.mMonth = month;
        this.mDay = day;
        this.mHour = hour;
        this.mMinute = minute;
        this.mSecond = sec;
        this.mCalendar.set(Calendar.YEAR, this.mYear);
        this.mCalendar.set(Calendar.MONTH, -1 + this.mMonth);
        this.mCalendar.set(Calendar.DATE, this.mDay);
        this.mCalendar.set(Calendar.HOUR_OF_DAY, this.mHour);
        this.mCalendar.set(Calendar.MINUTE, this.mMinute);
        this.mCalendar.set(Calendar.SECOND, this.mSecond);
        inflateNoRepeat(this.mCalendar);
    }
}