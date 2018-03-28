package com.duoker.watch.presenters.impl;

import android.os.Bundle;
import android.os.Handler;

import com.duoker.watch.DuokerWatchApp;
import com.duoker.watch.R;
import com.duoker.watch.interactors.base.SimpleCallback;
import com.duoker.watch.interactors.base.SimpleErrorBundle;
import com.duoker.watch.interactors.impl.DelBatchScheduleInteractorImpl;
import com.duoker.watch.interactors.impl.OperateScheduleInteractorImpl;
import com.duoker.watch.model.ScheduleModel;
import com.duoker.watch.presenters.ScheduleListPresenter;
import com.duoker.watch.repository.ScheduleRepository;
import com.duoker.watch.storage.WatchsStorage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cheng on 2017/10/6.
 */

public class ScheduleListPresenterImpl extends ScheduleListPresenter
{
    private final ScheduleListPresenter.View iView;
    private List<ScheduleModel> mDataList = new ArrayList();
    Handler mHandler = new Handler();
    private final ScheduleRepository mScheduleRepository;

    public ScheduleListPresenterImpl(ScheduleListPresenter.View paramView, ScheduleRepository paramScheduleRepository)
    {
        this.iView = paramView;
        this.mScheduleRepository = paramScheduleRepository;
    }

    public void delBatch(List<ScheduleModel> paramList)
    {
        if ((paramList == null) || (paramList.isEmpty()))
            return;
        this.iView.showProgress();
        new DelBatchScheduleInteractorImpl(paramList, this.mScheduleRepository).execute(new SimpleCallback<Object>()
        {
            @Override
            public void onSuccess(Object paramT) {
                iView.hideProgress();
                iView.showToast(R.string.common_del_tip_2);
                getList();
            }

            @Override
            public void onError(SimpleErrorBundle errorBundle) {
                iView.hideProgress();
                iView.showToast(R.string.common_del_tip_3);
            }
        });
    }

    public void enable(final ScheduleModel scheduleModel, final boolean paramBoolean)
    {
        if (paramBoolean)
            scheduleModel.setSchedulestatus(1);
        else
        {
            scheduleModel.setSchedulestatus(0);
        }
        this.iView.showProgress();
        new OperateScheduleInteractorImpl(scheduleModel, this.mScheduleRepository).execute(new SimpleCallback<Object>()
        {
            @Override
            public void onSuccess(Object paramT) {
                iView.hideProgress();
                iView.showToast(R.string.schedule_edit_s);
            }

            @Override
            public void onError(SimpleErrorBundle errorBundle) {
                iView.hideProgress();
                iView.showToast(R.string.schedule_edit_f);
                if (paramBoolean)
                    scheduleModel.setSchedulestatus(0);
                else
                {
                    scheduleModel.setSchedulestatus(1);
                }
                iView.notifyListView();
            }
        });
    }

    public List<ScheduleModel> getDataList()
    {
        return this.mDataList;
    }

    public void getList()
    {
        WatchsStorage localWatchsStorage = DuokerWatchApp.getInstance().getDefaultWatch();
        if (localWatchsStorage == null)
            return;
        String userid = DuokerWatchApp.getInstance().getLoginUser().getUserid();
        String watchid = localWatchsStorage.getWatchId();
        this.iView.showProgress();
        new GetSchedulesInteractorImpl(userid, watchid, this.mScheduleRepository).execute(new SimpleCallback<List<ScheduleModel>>()
        {
            @Override
            public void onSuccess(List<ScheduleModel> list) {
                mDataList.clear();
                if ((list != null) && (!list.isEmpty()))
                    mDataList.addAll(list);

                iView.inflateListView(mDataList);
                iView.hideProgress();
            }

            @Override
            public void onError(SimpleErrorBundle errorBundle) {
            }
        });
    }

    public void onCreate(Bundle paramBundle)
    {
        if (DuokerWatchApp.getInstance().getDefaultWatch() == null)
        {
            this.iView.showNoWatch(true);
            return;
        }
        this.iView.showNoWatch(false);
        this.mHandler.post(new Runnable()
        {
            public void run()
            {
                getList();
            }
        });
    }
}
