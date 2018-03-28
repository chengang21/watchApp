package com.duoker.watch.ui.fragment;

/**
 * Created by cheng on 2017/10/6.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.duoker.watch.R;
import com.duoker.watch.model.ScheduleModel;
import com.duoker.watch.presenters.ScheduleCreatePresenter;
import com.duoker.watch.presenters.impl.ScheduleCreatePresenterImpl;
import com.duoker.watch.repository.ScheduleRepositoryImpl;
import com.duoker.watch.ui.base.BaseFragment;
import com.duoker.watch.ui.component.WeekChooseComp;
import com.duoker.watch.ui.event.ScheduleSpeech2TextEvent;
import com.duoker.watch.ui.view.wheelview.WheelDatePicker;
import com.duoker.watch.ui.view.wheelview.WheelTimePicker;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnClickListener;
import com.orhanobut.dialogplus.ViewHolder;

import org.greenrobot.eventbus.EventBus;

public class ScheduleCreateTextFragment extends BaseFragment implements ScheduleCreatePresenter.View {
    private static final String ENTITY = "entity";
    public static final String TAG = ScheduleCreateTextFragment.class.getSimpleName();
    private ImageView mChangeSpeechView;
    private LinearLayout mChooseDateTimeLayout;
    private EditText mContentEt;
    private RelativeLayout mGiveUpLayout;
    private TextView mNoRepeatDateTv;
    private TextView mNoRepeatTimeTv;
    private LinearLayout mRepeatLayout;
    private TextView mRepeatTimeTv;
    private TextView mRepeatTv;
    private ScheduleCreatePresenterImpl mScheduleCreatePresenter;
    private ScheduleModel mScheduleModel;

    @Override
    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle) {
        return paramLayoutInflater.inflate(R.layout.fragment_schedule_create_text, paramViewGroup, false);
    }

    @Override
    public void onViewCreated(View paramView, @Nullable Bundle paramBundle) {
        super.onViewCreated(paramView, paramBundle);
        this.mScheduleCreatePresenter = new ScheduleCreatePresenterImpl(this, getActivity(), new ScheduleRepositoryImpl(getActivity()));
        this.mContentEt = ((EditText) paramView.findViewById(R.id.content_et));
        this.mChangeSpeechView = ((ImageView) paramView.findViewById(R.id.change_speech_view));
        this.mChooseDateTimeLayout = ((LinearLayout) paramView.findViewById(R.id.choose_date_time_layout));
        this.mNoRepeatDateTv = ((TextView) paramView.findViewById(R.id.no_repeat_date_tv));
        this.mNoRepeatTimeTv = ((TextView) paramView.findViewById(R.id.no_repeat_time_tv));
        this.mRepeatTimeTv = ((TextView) paramView.findViewById(R.id.repeat_time_tv));
        this.mRepeatLayout = ((LinearLayout) paramView.findViewById(R.id.repeat_layout));
        this.mRepeatTv = ((TextView) paramView.findViewById(R.id.repeat_tv));
        this.mGiveUpLayout = ((RelativeLayout) paramView.findViewById(R.id.give_up_layout));

        initListener();
        initData();
    }

    private void initData() {
        this.mScheduleCreatePresenter.initData(this.mScheduleModel);
    }

    private void initListener() {
        this.mChangeSpeechView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScheduleSpeech2TextEvent localScheduleSpeech2TextEvent = new ScheduleSpeech2TextEvent();
                localScheduleSpeech2TextEvent.speech2TextFragment = false;
                EventBus.getDefault().post(localScheduleSpeech2TextEvent);
            }
        });
        this.mChooseDateTimeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mScheduleCreatePresenter.clickChooseDateTimeLayout();
            }
        });
        this.mRepeatLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeekChooseComp weekChooseComp = new WeekChooseComp(getActivity());
                weekChooseComp.init(mScheduleCreatePresenter.getWeekData());
                ViewHolder viewHolder = new ViewHolder(weekChooseComp.getView());
                DialogPlus dialogPlus = DialogPlus.newDialog(getActivity()).
                        setContentHolder(viewHolder).
                        setCancelable(true).
                        setGravity(80).
                        setOnDismissListener(null).
                        setExpanded(false).
                        setOnCancelListener(null).
                        setContentBackgroundResource(17170443).
                        setOnClickListener(null).create();

                weekChooseComp.setOnItemClickListener(new WeekChooseComp.OnItemClickListener() {
                    public void onItemClick(String txt, int weekData) {
                        mScheduleCreatePresenter.setWeekData(weekData);
                        mRepeatTimeTv.setText(txt);
                    }
                });
                dialogPlus.show();
            }
        });
        this.mGiveUpLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

    public static ScheduleCreateTextFragment newInstance(ScheduleModel paramScheduleModel) {
        ScheduleCreateTextFragment localScheduleCreateTextFragment = new ScheduleCreateTextFragment();
        if (paramScheduleModel != null) {
            Bundle localBundle = new Bundle();
            localBundle.putSerializable("entity", paramScheduleModel);
            localScheduleCreateTextFragment.setArguments(localBundle);
        }
        return localScheduleCreateTextFragment;
    }

    public void add() {
        String str = this.mContentEt.getText().toString();
        this.mScheduleCreatePresenter.addSchedule(str);
    }

    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        if (getArguments() != null)
            this.mScheduleModel = ((ScheduleModel) getArguments().getSerializable("entity"));
    }


    @Override
    public void finishActivity() {
        if ((getActivity() == null) || (getActivity().isFinishing()) || (!isAdded()))
            return;
        getActivity().finish();
    }

    @Override
    public void hideProgress() {
        // hideProgress();
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void setContentText(String paramString) {
        if ((getActivity() == null) || (getActivity().isFinishing()) || (!isAdded())) ;
        while (mContentEt == null)
            return;
        mContentEt.setText(paramString);
    }

    @Override
    public void setNoRepeatDateText(String paramString) {
        if ((getActivity() == null) || (getActivity().isFinishing()) || (!isAdded())) ;
        while (mNoRepeatDateTv == null)
            return;
        mNoRepeatDateTv.setText(paramString);
    }

    @Override
    public void setNoRepeatDateVisible(boolean paramBoolean) {
        if ((getActivity() == null) || (getActivity().isFinishing()) || (!isAdded())) ;
        while (mNoRepeatDateTv == null)
            return;
        if (paramBoolean) {
            mNoRepeatDateTv.setVisibility(View.VISIBLE);
            return;
        }
        mNoRepeatDateTv.setVisibility(View.GONE);
    }

    @Override
    public void setNoRepeatTimeText(String paramString) {
        if ((getActivity() == null) || (getActivity().isFinishing()) || (!isAdded())) ;
        while (mNoRepeatTimeTv == null)
            return;
        mNoRepeatTimeTv.setText(paramString);
    }

    @Override
    public void setNoRepeatTimeVisible(boolean paramBoolean) {
        if ((getActivity() == null) || (getActivity().isFinishing()) || (!isAdded())) ;
        while (mNoRepeatTimeTv == null)
            return;
        if (paramBoolean) {
            mNoRepeatTimeTv.setVisibility(View.VISIBLE);
            return;
        }
        mNoRepeatTimeTv.setVisibility(View.GONE);
    }

    @Override
    public void setRepeatText(String paramString) {
        if ((getActivity() == null) || (getActivity().isFinishing()) || (!isAdded()))
            return;
        mRepeatTv.setText(paramString);
    }

    @Override
    public void setRepeatTimeText(String paramString) {
        if ((getActivity() == null) || (getActivity().isFinishing()) || (!isAdded())) ;
        while (mRepeatTimeTv == null)
            return;
        mRepeatTimeTv.setText(paramString);
    }

    @Override
    public void setRepeatTimeVisible(boolean paramBoolean) {
        if ((getActivity() == null) || (getActivity().isFinishing()) || (!isAdded())) ;
        while (mRepeatTimeTv == null)
            return;
        if (paramBoolean) {
            mRepeatTimeTv.setVisibility(View.VISIBLE);
            return;
        }
        mRepeatTimeTv.setVisibility(View.GONE);
    }

    @Override
    public void showDateTimeDialog(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
        if (mScheduleCreatePresenter == null)
            return;
        View localView = View.inflate(getActivity(), R.layout.choose_date_time_dialog, null);
        final WheelDatePicker localWheelDatePicker = (WheelDatePicker) localView.findViewById(R.id.date_picker);
        localWheelDatePicker.init(paramInt1, paramInt2, paramInt3);
        localWheelDatePicker.setWheelMargin(100);
        final WheelTimePicker localWheelTimePicker = (WheelTimePicker) localView.findViewById(R.id.time_picker);
        localWheelTimePicker.setShowSecond(false);
        localWheelTimePicker.setWheelMargin(100);
        localWheelTimePicker.init(paramInt4, paramInt5, 0);
        ViewHolder localViewHolder = new ViewHolder(localView);

        DialogPlus.newDialog(getActivity()).
                setContentHolder(localViewHolder).
                setHeader(R.layout.dialog_header).setCancelable(true).
                setGravity(80).
                setOnDismissListener(null).setExpanded(false).
                setContentHeight(-2).
                setOnCancelListener(null).setContentBackgroundResource(17170443).
                setOnClickListener(new OnClickListener() {
            public void onClick(DialogPlus dialogPlus, View view) {
                switch (view.getId()) {
                    case R.id.sure_layout:
                        int i = localWheelDatePicker.getYear();
                        int j = localWheelDatePicker.getMonth();
                        int k = localWheelDatePicker.getDay();
                        int m = localWheelTimePicker.getHour();
                        int n = localWheelTimePicker.getMinute();
                        int i1 = localWheelTimePicker.getSecond();
                        mScheduleCreatePresenter.setYMDHMS(i, j, k, m, n, i1);
                        dialogPlus.dismiss();
                        return;
                    case R.id.cancel_layout:
                        dialogPlus.dismiss();
                }
            }
        }).create().show();
    }

    @Override
    public void showProgress() {
        // showProgress();
    }

    @Override
    public void showTimeDialog(int paramInt1, int paramInt2, int paramInt3) {
        if (mScheduleCreatePresenter == null)
            return;
        View localView = View.inflate(getActivity(), R.layout.choose_time_dialog, null);
        final WheelTimePicker timePicker = (WheelTimePicker) localView.findViewById(R.id.time_picker);
        timePicker.setShowSecond(false);
        timePicker.setWheelMargin(100);
        timePicker.init(paramInt1, paramInt2, 0);
        ViewHolder viewHolder = new ViewHolder(localView);
        DialogPlus.newDialog(getActivity()).setContentHolder(viewHolder).setHeader(R.layout.dialog_header).
                setCancelable(true).setGravity(80).setOnDismissListener(null).
                setExpanded(false).setContentHeight(-2).setOnCancelListener(null).
                setContentBackgroundResource(17170443).
                setOnClickListener(new OnClickListener() {
                    public void onClick(DialogPlus dialogPlus, View view) {
                        switch (view.getId()) {
                            case R.id.sure_layout:
                                int i = timePicker.getHour();
                                int j = timePicker.getMinute();
                                int k = timePicker.getSecond();
                                mScheduleCreatePresenter.setHMS(i, j, k);
                                dialogPlus.dismiss();
                                return;
                            case R.id.cancel_layout:
                                dialogPlus.dismiss();
                        }
                    }
                }).create().show();
    }

    @Override
    public void showToast(@StringRes int resourceID) {
        showShortToast(resourceID);
    }

    @Override
    public void showToast(CharSequence charSequence) {
        showShortToast(charSequence);
    }
}
