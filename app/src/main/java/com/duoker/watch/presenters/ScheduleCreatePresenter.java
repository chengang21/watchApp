package com.duoker.watch.presenters;

import com.duoker.watch.model.ScheduleModel;
import com.duoker.watch.ui.BaseView;

/**
 * Created by cheng on 2017/10/6.
 */
public abstract class ScheduleCreatePresenter {
    public abstract void addSchedule(String paramString);

    public abstract void clickChooseDateTimeLayout();

    public abstract int getWeekData();

    public abstract void initData(ScheduleModel scheduleModel);

    public abstract void setHMS(int paramInt1, int paramInt2, int paramInt3);

    public abstract void setWeekData(int paramInt);

    public abstract void setYMDHMS(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6);

    public interface View extends BaseView
    {
        void finishActivity();

        void setContentText(String paramString);

        void setNoRepeatDateText(String paramString);

        void setNoRepeatDateVisible(boolean paramBoolean);

        void setNoRepeatTimeText(String paramString);

        void setNoRepeatTimeVisible(boolean paramBoolean);

        void setRepeatText(String paramString);

        void setRepeatTimeText(String paramString);

        void setRepeatTimeVisible(boolean paramBoolean);

        void showDateTimeDialog(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6);

        void showTimeDialog(int paramInt1, int paramInt2, int paramInt3);
    }
}
