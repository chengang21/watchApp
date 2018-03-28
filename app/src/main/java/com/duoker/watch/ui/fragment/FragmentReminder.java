package com.duoker.watch.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.duoker.watch.DuokerWatchApp;
import com.duoker.watch.R;
import com.duoker.watch.model.ScheduleModel;
import com.duoker.watch.presenters.ScheduleListPresenter;
import com.duoker.watch.presenters.impl.ScheduleListPresenterImpl;
import com.duoker.watch.repository.ScheduleRepositoryImpl;
import com.duoker.watch.storage.WatchsStorage;
import com.duoker.watch.ui.activity.ScheduleCreateActivity;
import com.duoker.watch.ui.activity.ScheduleEditActivity;
import com.duoker.watch.ui.activity.WatchAddActivity;
import com.duoker.watch.ui.base.BaseFragment;
import com.duoker.watch.ui.component.WeekChoose2CompHelper;
import com.duoker.watch.ui.event.FlushHomeOtherFragmentDataEvent;
import com.duoker.watch.ui.event.FlushSchedulesEvent;
import com.duoker.watch.ui.view.MultipleChoiceListView;
import com.duoker.watch.ui.view.SwitchButton;
import com.duoker.watch.utils.MyDateUtils;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class FragmentReminder extends BaseFragment implements ScheduleListPresenter.View {
    private Bundle savedInstanceState;
    private Activity activity = null;
    private View view;

    public static final String TAG = FragmentReminder.class.getSimpleName();
    private QuickAdapter<ScheduleModel> mDataAdapter;
    private MultipleChoiceListView mLv1;
    private RelativeLayout mNoDataLayout;
    private View mNoWatchLayout;
    private ScheduleListPresenterImpl mSchedulePresenter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RelativeLayout mToolbarLayout;
    private ImageView mToolbarMenuAdd;
    private ImageView mToolbarMenuDel;
    private TextView mToolbarMenuSureDel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragement_reminder, container, false);

        activity = this.getActivity();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        EventBus.getDefault().register(this);
        this.mSchedulePresenter = new ScheduleListPresenterImpl(this, new ScheduleRepositoryImpl(getActivity()));
        this.mToolbarLayout = ((RelativeLayout) view.findViewById(R.id.toolbar_layout));
        this.mToolbarMenuSureDel = ((TextView) view.findViewById(R.id.toolbar_menu_sure_del));
        this.mToolbarMenuAdd = ((ImageView) view.findViewById(R.id.toolbar_menu_add));
        this.mToolbarMenuDel = ((ImageView) view.findViewById(R.id.toolbar_menu_del));
        this.mNoDataLayout = ((RelativeLayout) view.findViewById(R.id.no_data_layout));
        this.mSwipeRefreshLayout = ((SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout));
        this.mLv1 = ((MultipleChoiceListView) view.findViewById(R.id.lv1));
        this.mNoWatchLayout = view.findViewById(R.id.no_watch_layout);
        this.mSwipeRefreshLayout.setColorSchemeResources(R.color.colorToolbar_bg);
        initListener();
        this.mSchedulePresenter.onCreate(bundle);
    }

    private void delBatch() {
        List localList = this.mLv1.getPositionsSelectedMultiple();
        if ((localList != null) && (!localList.isEmpty())) {
            ArrayList localArrayList = new ArrayList();
            Iterator localIterator = localList.iterator();
            while (localIterator.hasNext()) {
                int i = ((Integer) localIterator.next()).intValue();
                localArrayList.add((ScheduleModel) this.mDataAdapter.getItem(i));
            }
            this.mSchedulePresenter.delBatch(localArrayList);
        }
        setMultiple(false);
    }

    private void initListener() {
        this.mToolbarMenuSureDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delBatch();
            }
        });
        this.mToolbarMenuAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isHaveWatch())
                    return;
                if (mSchedulePresenter.getDataList().size() < 20) {
                    Intent localIntent = new Intent(getActivity(), ScheduleCreateActivity.class);
                    startActivity(localIntent);
                    return;
                }
                showShortToast(R.string.schedule_so_much);
            }
        });
        this.mToolbarMenuDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isHaveWatch())
                    return;

                if ((mDataAdapter == null) || (mDataAdapter.getCount() == 0))
                    return;

                //if (mLv1.getMultipleMode())
                //   mToolbarMenuDel.setEnabled(false);
                //else
                //    FragmentReminder.this., true);
            }
        });
        this.mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSchedulePresenter.getList();
            }

        });
        this.mLv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ScheduleModel localScheduleModel = (ScheduleModel) mDataAdapter.getItem(position);
                Intent localIntent = new Intent(getActivity(), ScheduleEditActivity.class);
                localIntent.putExtra("ENTITY", localScheduleModel);
                startActivity(localIntent);
            }
        });

        this.mLv1.setOnMultipleChangeListener(new MultipleChoiceListView.OnMultipleChangeListener() {
            @Override
            public void onMultipleItemChanged(boolean paramBoolean, int paramInt1, int paramInt2) {
                StringBuilder sb = new StringBuilder();
                sb.append("(").append(paramInt2).append(")").append(getString(R.string.common_all_tip_2));
                mToolbarMenuSureDel.setText(sb.toString());
            }

            @Override
            public void onMultipleItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong) {

            }

            @Override
            public void onMultipleModeChange(boolean paramBoolean) {

            }
        });
        this.mNoWatchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent localIntent = new Intent(getActivity(), WatchAddActivity.class);
                startActivity(localIntent);
            }
        });
    }

    private boolean isHaveWatch() {
        WatchsStorage localWatchsStorage = DuokerWatchApp.getInstance().getDefaultWatch();
        return (localWatchsStorage != null) && (!TextUtils.isEmpty(localWatchsStorage.getWatchId()));
    }

    public static FragmentReminder newInstance() {
        return new FragmentReminder();
    }

    private void setMultiple(boolean paramBoolean) {
        if (paramBoolean) {
            this.mLv1.setMultipleMode(true);
            this.mToolbarMenuSureDel.setVisibility(View.VISIBLE);
            this.mToolbarMenuDel.setImageResource(R.drawable.hb_btn_cancel_del);
        } else {
            this.mLv1.setMultipleMode(false);
            this.mToolbarMenuSureDel.setVisibility(View.GONE);
            this.mToolbarMenuDel.setImageResource(R.drawable.hb_btn_del);
        }
        String str = "(" + 0 + ")" + getString(R.string.common_all_tip_2);
        this.mToolbarMenuSureDel.setText(str);
    }

    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
    }


    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(FlushHomeOtherFragmentDataEvent paramFlushHomeOtherFragmentDataEvent) {
        if (this.mSchedulePresenter != null) {
            this.mSchedulePresenter.getList();
            if (DuokerWatchApp.getInstance().getDefaultWatch() == null)
                showNoWatch(true);
        } else {
            return;
        }
        showNoWatch(false);
    }

    @Subscribe
    public void onEvent(FlushSchedulesEvent paramFlushSchedulesEvent) {
        if (this.mSchedulePresenter != null)
            this.mSchedulePresenter.getList();
    }


    // iView

    @Override
    public void hideProgress() {
        if ((getActivity() == null) || (getActivity().isFinishing()) || (!isAdded()))
            return;

        mSwipeRefreshLayout.setRefreshing(false);
    }

    public void inflateListView(List<ScheduleModel> scheduleModelList) {
        if ((mLv1 == null) || (mNoDataLayout == null))
            return;
        if ((getActivity() == null) || (getActivity().isFinishing()) || (!isAdded()))
            return;

        if ((scheduleModelList == null) || (scheduleModelList.isEmpty())) {
            mNoDataLayout.setVisibility(View.VISIBLE);
            mLv1.setVisibility(View.GONE);
            return;
        }

        mDataAdapter = new QuickAdapter<ScheduleModel>(getActivity(), R.layout.schedule_list_item, scheduleModelList) {
            @Override
            protected void convert(BaseAdapterHelper helper, final ScheduleModel scheduleModel) {
                final CheckBox localCheckBox = (CheckBox) helper.getView(R.id.muti_check_view);
                final SwitchButton switchButton = (SwitchButton) helper.getView(R.id.enable_tb);
                final int i = helper.getPosition();
                String schedule = scheduleModel.getScheduletime();

                if (!mLv1.getMultipleMode()) {
                    localCheckBox.setVisibility(View.GONE);
                    switchButton.setVisibility(View.VISIBLE);
                    localCheckBox.setChecked(mLv1.isPositionSelectedMultiple(i));
                    View.OnClickListener local1 = new View.OnClickListener() {
                        public void onClick(View paramAnonymous2View) {
                            boolean bool = localCheckBox.isChecked();
                            mLv1.selectPositionMultiple(i, bool);
                        }
                    };
                    localCheckBox.setOnClickListener(local1);

                    if (scheduleModel.getSchedulerepeat() != 0) {
                        helper.setVisible(R.id.date_tv, false);
                        try {
                            String time = DateFormatUtils.format(DateUtils.parseDate(schedule, new String[]{"HH:mm:ss", "HH:mm"}), "HH:mm");

                            helper.setText(R.id.time_tv, time);
                            helper.setText(R.id.circle_week_desc_view, getString(R.string.schedule_muti_tip));
                            if (WeekChoose2CompHelper.isSelectEveryday(scheduleModel.getScheduleweek()))
                                helper.setText(R.id.week_tv, getString(R.string.schedule_day_tip2));
                        } catch (ParseException e) {

                            e.printStackTrace();

                            if (WeekChoose2CompHelper.isSelectWorkday(scheduleModel.getScheduleweek())) {
                                helper.setText(R.id.week_tv, getString(R.string.schedule_day_tip3));
                            }
                            helper.setText(R.id.week_tv, WeekChoose2CompHelper.getWeekDesc(getActivity(), scheduleModel.getScheduleweek()));
                        }
                    } else
                        helper.setVisible(R.id.date_tv, true);
                } else {
                    localCheckBox.setVisibility(View.VISIBLE);
                    switchButton.setVisibility(View.GONE);
                }
                try {
                    Date date = DateUtils.parseDate(schedule, new String[]{"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm"});
                    helper.setText(R.id.date_tv, DateFormatUtils.format(date, "yyyy/MM/dd"));
                    helper.setText(R.id.time_tv, DateFormatUtils.format(date, "HH:mm"));
                    helper.setText(R.id.circle_week_desc_view, MyDateUtils.getWeekDesc(getActivity(), date));
                    helper.setText(R.id.week_tv, MyDateUtils.getWeekDesc(getActivity(), date));
                    if (scheduleModel.getSchedulestatus() != 1) {
                        switchButton.setCheckedImmediately(false);
                    } else {
                        switchButton.setCheckedImmediately(true);
                        helper.setText(R.id.content_tv, scheduleModel.getSchedulecontent());

                        switchButton.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View paramAnonymous2View) {
                                boolean checked = switchButton.isChecked();
                                mSchedulePresenter.enable(scheduleModel, checked);
                            }
                        });
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        };

        mLv1.setAdapter(mDataAdapter);
        mDataAdapter.addAll(scheduleModelList);
        mLv1.setVisibility(View.VISIBLE);

        mNoDataLayout.setVisibility(View.GONE);
        //mDataAdapter.clear();
    }

    public void notifyListView() {
        if ((getActivity() == null) || (getActivity().isFinishing()) || (!isAdded())) ;
        while (mDataAdapter == null)
            return;
        mDataAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgress() {
        if ((getActivity() == null) || (getActivity().isFinishing()) || (!isAdded())) ;
        while (mSwipeRefreshLayout == null)
            return;
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void showNoWatch(boolean paramBoolean) {
        if ((getActivity() == null) || (getActivity().isFinishing()) || (!isAdded())) ;
        while (mNoWatchLayout == null)
            return;
        if (paramBoolean) {
            mNoWatchLayout.setVisibility(View.VISIBLE);
            return;
        }
        mNoWatchLayout.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {

    }

    public void showToast(@StringRes int resourceID) {
        showShortToast(resourceID);
    }

    public void showToast(CharSequence charSequence) {
        showShortToast(charSequence);
    }

}

