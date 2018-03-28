package com.duoker.watch.presenters;

import com.duoker.watch.model.ScheduleModel;
import com.duoker.watch.presenters.base.AbstractPresenter;
import com.duoker.watch.ui.BaseView;

import java.util.List;

/**
 * Created by cheng on 2017/10/6.
 */

public abstract class ScheduleListPresenter extends AbstractPresenter
{
    public abstract void delBatch(List<ScheduleModel> paramList);
    public abstract void enable(ScheduleModel paramScheduleModel, boolean paramBoolean);
    public abstract List<ScheduleModel> getDataList();
    public abstract void getList();

    public interface View extends BaseView
    {
        void inflateListView(List<ScheduleModel> paramList);
        void notifyListView();
        void showNoWatch(boolean paramBoolean);
    }
}
