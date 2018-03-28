package com.duoker.watch.ui.fragment;

/**
 * Created by cheng on 2017/10/8.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.duoker.watch.R;
import com.duoker.watch.model.HeartRateBean;
import com.duoker.watch.model.HeartRateViewModel;
import com.duoker.watch.presenters.HeartRateMonthPresenter;
import com.duoker.watch.presenters.impl.HeartRateMonthPresenterImpl;
import com.duoker.watch.repository.HeartRateRepositoryImpl;
import com.duoker.watch.ui.adapter.DividerDecoration;
import com.duoker.watch.ui.adapter.HeartRateViewAdapter;
import com.duoker.watch.ui.base.BaseFragment;
import com.duoker.watch.ui.component.HeartRateMonthViewComp;
import com.duoker.watch.ui.event.FlushHeartRateEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class HeartRateMonthFragment extends BaseFragment implements HeartRateMonthPresenter.View
{
    public static final String TAG = HeartRateMonthFragment.class.getSimpleName(); 
    private HeartRateViewAdapter mDataAdapter;
    private HeartRateMonthPresenterImpl mHeartRatePresenter;
    private HeartRateMonthViewComp mHeartRateViewComp;
    private RecyclerView mRv1;

    @Override
    public void onViewCreated(View paramView, @Nullable Bundle paramBundle)
    {
        super.onViewCreated(paramView, paramBundle);
        EventBus.getDefault().register(this);
        this.mHeartRatePresenter = new HeartRateMonthPresenterImpl(this, getActivity(), new HeartRateRepositoryImpl());

        this.mRv1 = ((RecyclerView)findViewById(R.id.record_rv1));
        LinearLayoutManager localLinearLayoutManager = new LinearLayoutManager(getActivity());
        localLinearLayoutManager.setOrientation(LinearLayout.VERTICAL);
        this.mRv1.setLayoutManager(localLinearLayoutManager);
        this.mRv1.addItemDecoration(new DividerDecoration(getActivity()));
        this.mHeartRateViewComp = new HeartRateMonthViewComp(getActivity(), this.mHeartRatePresenter);

        this.mHeartRatePresenter.onCreate(paramBundle);
    }

    @Override
    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
    {
        return paramLayoutInflater.inflate(R.layout.fragment_heart_rate_month, paramViewGroup, false);
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }


    public static HeartRateMonthFragment newInstance()
    {
        return new HeartRateMonthFragment();
    }

    @Override
    public void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
    }

    @Subscribe
    public void onEvent(FlushHeartRateEvent paramFlushHeartRateEvent)
    {
        if ((getActivity() == null) || (getActivity().isFinishing()) || (!isAdded()))
            return;
        this.mHeartRatePresenter.getHeartRate();
    }
    
    
    //=================================== view ============================


    public void drawRecordChart(List<HeartRateBean> paramList)
    {
        if ((getActivity() == null) || (getActivity().isFinishing()) || (!isAdded())) 
            return;
        mHeartRateViewComp.drawRecordChart(paramList);
    }

    public void finishActivity()
    {
        if ((getActivity() == null) || (getActivity().isFinishing()) || (!isAdded()))
            return;
        getActivity().finish();
    }

    public void hideLoading()
    {
        hideProgress();
    }

    public void inflateListView(List<HeartRateViewModel> list)
    {
        if ((getActivity() == null) || (getActivity().isFinishing()) || (!isAdded())) 
            return;
        if (mDataAdapter == null)
        {
            mDataAdapter = new HeartRateViewAdapter(getActivity(), mRv1.getRootView(), list);
            mDataAdapter.setOnItemClickListener(new HeartRateViewAdapter.OnItemClickListener()
            {
                public void onItemClick(View v, int pos, HeartRateViewModel m)
                {
                }
            });
            mRv1.setAdapter(mDataAdapter);
        }
        mDataAdapter.setData(list);
    }

    public void resetRecordChart()
    {
        if ((getActivity() == null) || (getActivity().isFinishing()) || (!isAdded()))
            return;
        mHeartRateViewComp.resetRecordChart();
    }

    public void resetText()
    {
        if ((getActivity() == null) || (getActivity().isFinishing()) || (!isAdded()))
            return;
        setAverageText("");
        setHighText("");
        setLowText("");
    }

    public void setAverageText(String paramString)
    {
        if ((getActivity() == null) || (getActivity().isFinishing()) || (!isAdded()))
            return;
        mHeartRateViewComp.setAverageText(paramString);
    }

    public void setHighText(String paramString)
    {
        if ((getActivity() == null) || (getActivity().isFinishing()) || (!isAdded()))
            return;
        mHeartRateViewComp.setHighText(paramString);
    }

    public void setLowText(String paramString)
    {
        if ((getActivity() == null) || (getActivity().isFinishing()) || (!isAdded()))
            return;
        mHeartRateViewComp.setLowText(paramString);
    }

    public void showLoading()
    {
        showProgress();
    }

    @Override
    public void showError(String message) {
        
    }

    public void showToast(@StringRes int paramInt)
    {
        showShortToast(paramInt);
    }

    public void showToast(CharSequence paramCharSequence)
    {
        showShortToast(paramCharSequence);
    }

}
