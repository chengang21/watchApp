package com.duoker.watch.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duoker.watch.R;
import com.duoker.watch.storage.WatchsStorage;
import com.duoker.watch.ui.view.ArcProgress;

/**
 * Created by chengang on 4/29/2017.
 */

public class FragmentDeviceItem extends Fragment
{
    private static final String ENTITY = "watch_obj";
    public static final String TAG = FragmentDeviceItem.class.getSimpleName();
//    private MenuDeviceItemPresenter.View iView = new FragmentDeviceItem.1(this);
//    private MenuDeviceItemPresenterImpl mMenuDeviceItemPresenter;
    private ArcProgress mStepCountBar;
    private WatchsStorage mWatchsStorage;

    private void initData()
    {
//        if (this.mWatchsStorage != null)
//        {
//            // this.mMenuDeviceItemPresenter.initData(this.mWatchsStorage);
//            if (this.mWatchsStorage.getOnline() == 1)
//                this.mStepCountBar.setNameText(getString(2131165420));
//        }
//        else
//        {
//            return;
//        }
//        this.mStepCountBar.setNameText(getString(2131165413));
    }

    public static FragmentDeviceItem newInstance(WatchsStorage paramWatchsStorage)
    {
        FragmentDeviceItem localFragmentDeviceItem = new FragmentDeviceItem();
        Bundle localBundle = new Bundle();
        localBundle.putSerializable("watch_obj", paramWatchsStorage);
        localFragmentDeviceItem.setArguments(localBundle);
        return localFragmentDeviceItem;
    }

    public void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
//        if (getArguments() != null)
//            this.mWatchsStorage = ((WatchsStorage)getArguments().getSerializable("watch_obj"));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_device_item, container, false);
    }

    public void onDestroyView()
    {
        super.onDestroyView();
//        EventBus.getDefault().unregister(this);
    }
//
//    @Subscribe
//    public void onEvent(GetDataMenuDeviceItemEvent paramGetDataMenuDeviceItemEvent)
//    {
//        if ((this.mMenuDeviceItemPresenter != null) && (this.mWatchsStorage != null) && (TextUtils.equals(paramGetDataMenuDeviceItemEvent.watchsStorage.getWatchId(), this.mWatchsStorage.getWatchId())))
//        {
//            this.mMenuDeviceItemPresenter.getStepCountTarget();
//            this.mMenuDeviceItemPresenter.getStepCount();
//        }
//    }
//
//    @Subscribe
//    public void onEvent(GetTargetStepCountEvent paramGetTargetStepCountEvent)
//    {
//        if ((this.mMenuDeviceItemPresenter != null) && (this.mWatchsStorage != null) && (TextUtils.equals(paramGetTargetStepCountEvent.watchId, this.mWatchsStorage.getWatchId())))
//            this.mMenuDeviceItemPresenter.getStepCountTarget();
//    }

    public void onViewCreated(View paramView, @Nullable Bundle paramBundle)
    {
        super.onViewCreated(paramView, paramBundle);
//        EventBus.getDefault().register(this);
//        this.mMenuDeviceItemPresenter = new MenuDeviceItemPresenterImpl(this.iView, new CalcWalkCountRepositoryImpl(getActivity()));
        this.mStepCountBar = ((ArcProgress)paramView.findViewById( R.id.step_count_progress));
        initData();
    }
}
