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

import com.duoker.watch.R;
import com.duoker.watch.model.HeartRateBean;
import com.duoker.watch.model.HeartRateViewModel;
import com.duoker.watch.presenters.HeartRateWeekPresenter;
import com.duoker.watch.presenters.impl.HeartRateWeekPresenterImpl;
import com.duoker.watch.repository.HeartRateRepositoryImpl;
import com.duoker.watch.ui.adapter.DividerDecoration;
import com.duoker.watch.ui.adapter.HeartRateViewAdapter;
import com.duoker.watch.ui.base.BaseFragment;
import com.duoker.watch.ui.component.HeartRateWeekViewComp;
import com.duoker.watch.ui.event.FlushHeartRateEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class HeartRateWeekFragment extends BaseFragment implements  HeartRateWeekPresenter.View
{
    public static final String TAG = HeartRateWeekFragment.class.getSimpleName(); 
    private HeartRateViewAdapter mDataAdapter;
    private HeartRateWeekPresenterImpl mHeartRatePresenter;
    private HeartRateWeekViewComp mHeartRateViewComp;
    private RecyclerView mRv1; 

    private void initView()
    {
        this.mRv1 = ((RecyclerView)findViewById(2131558826));
        LinearLayoutManager localLinearLayoutManager = new LinearLayoutManager(getActivity());
        localLinearLayoutManager.setOrientation(1);
        this.mRv1.setLayoutManager(localLinearLayoutManager);
        this.mRv1.addItemDecoration(new DividerDecoration(getActivity()));
        this.mHeartRateViewComp = new HeartRateWeekViewComp(getActivity(), this.mHeartRatePresenter);
    }

    public static HeartRateWeekFragment newInstance()
    {
        return new HeartRateWeekFragment();
    }

    public void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
    }

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
    {
        return paramLayoutInflater.inflate(R.layout.fragment_heart_rate_week, paramViewGroup, false);
    }

    public void onDestroyView()
    {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(FlushHeartRateEvent event)
    {
        if ((getActivity() == null) || (getActivity().isFinishing()) || (!isAdded()))
            return;
        this.mHeartRatePresenter.getHeartRate();
    }

    public void onViewCreated(View paramView, @Nullable Bundle paramBundle)
    {
        super.onViewCreated(paramView, paramBundle);
        EventBus.getDefault().register(this);
        this.mHeartRatePresenter = new HeartRateWeekPresenterImpl(this, getActivity(), new HeartRateRepositoryImpl());
        initView(); 
        this.mHeartRatePresenter.onCreate(paramBundle);
    }
    
    //=================== view =======================================

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

    public void inflateListView(List<HeartRateViewModel> paramList)
    {
        if ((getActivity() == null) || (getActivity().isFinishing()) || (!isAdded()))
            return;

        if (mDataAdapter == null)
        {
            mDataAdapter = new HeartRateViewAdapter(getActivity(), mHeartRateViewComp.getRootView(), paramList);
            mDataAdapter.setOnItemClickListener(new HeartRateViewAdapter.OnItemClickListener()
            {
                public void onItemClick(View v, int pos, HeartRateViewModel m)
                {
                }
            });
            mRv1.setAdapter(mDataAdapter) ;

        }
        mDataAdapter.setData(paramList);
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