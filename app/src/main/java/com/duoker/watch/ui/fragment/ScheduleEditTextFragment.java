package com.duoker.watch.ui.fragment;

/**
 * Created by cheng on 2017/10/7.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.duoker.watch.R;
import com.duoker.watch.model.ScheduleModel;
import com.duoker.watch.presenters.ScheduleEditPresenter;
import com.duoker.watch.presenters.impl.ScheduleEditPresenterImpl;
import com.duoker.watch.repository.ScheduleRepositoryImpl;
import com.duoker.watch.ui.base.BaseFragment;
import com.duoker.watch.ui.component.WeekChooseComp;
import com.duoker.watch.ui.view.wheelview.WheelDatePicker;
import com.duoker.watch.ui.view.wheelview.WheelTimePicker;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnClickListener;
import com.orhanobut.dialogplus.ViewHolder;

public class ScheduleEditTextFragment extends BaseFragment  implements ScheduleEditPresenter.View
{
    private static final String ENTITY = "entity";
    public static final String TAG = ScheduleEditTextFragment.class.getSimpleName(); 
    private LinearLayout mChooseDateTimeLayout;
    private EditText mContentEt;
    private RelativeLayout mDelLayout;
    private TextView mNoRepeatDateTv;
    private TextView mNoRepeatTimeTv;
    private LinearLayout mRepeatLayout;
    private TextView mRepeatTimeTv;
    private TextView mRepeatTv;
    private ScheduleEditPresenterImpl mScheduleEditPresenter;
    private ScheduleModel mScheduleModel;

    private void initData()
    {
        this.mScheduleEditPresenter.initData(this.mScheduleModel);
    }

    private void initListener()
    {
        this.mChooseDateTimeLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mScheduleEditPresenter.clickChooseDateTimeLayout();
            }
        });
        this.mRepeatLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                WeekChooseComp localWeekChooseComp = new WeekChooseComp(getActivity());
                localWeekChooseComp.init(mScheduleEditPresenter.getWeekData());
                ViewHolder localViewHolder = new ViewHolder(localWeekChooseComp.getView());
                DialogPlus localDialogPlus = DialogPlus.newDialog(getActivity()).setContentHolder(localViewHolder).setCancelable(true).setGravity(80).setOnDismissListener(null).setExpanded(false).setOnCancelListener(null).setContentBackgroundResource(17170443).setOnClickListener(null).create();
                localWeekChooseComp.setOnItemClickListener(new WeekChooseComp.OnItemClickListener()
                {
                    public void onItemClick(String paramAnonymousString, int paramAnonymousInt)
                    {
                        mScheduleEditPresenter.setWeekData(paramAnonymousInt);
                        mContentEt.setText(paramAnonymousString);
                    }
                });
                localDialogPlus.show();
            }
        });
        this.mDelLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mScheduleEditPresenter.delSchedule();
            }
        });
    }

    public static ScheduleEditTextFragment newInstance(ScheduleModel paramScheduleModel)
    {
        ScheduleEditTextFragment localScheduleEditTextFragment = new ScheduleEditTextFragment();
        if (paramScheduleModel != null)
        {
            Bundle localBundle = new Bundle();
            localBundle.putSerializable("entity", paramScheduleModel);
            localScheduleEditTextFragment.setArguments(localBundle);
        }
        return localScheduleEditTextFragment;
    }

    public void edit()
    {
        String str = this.mContentEt.getText().toString();
        this.mScheduleEditPresenter.editSchedule(str);
    }

    public void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        if (getArguments() != null)
            this.mScheduleModel = ((ScheduleModel)getArguments().getSerializable("entity"));
    }

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
    {
        return paramLayoutInflater.inflate(R.layout.fragment_schedule_edit_text, paramViewGroup, false);
    }

    public void onViewCreated(View paramView, @Nullable Bundle paramBundle)
    {
        super.onViewCreated(paramView, paramBundle);
        this.mScheduleEditPresenter = new ScheduleEditPresenterImpl(this, getActivity(), new ScheduleRepositoryImpl(getActivity()));
        this.mContentEt = ((EditText)paramView.findViewById(R.id.content_et));
        this.mChooseDateTimeLayout = ((LinearLayout)paramView.findViewById(R.id.choose_date_time_layout));
        this.mNoRepeatDateTv = ((TextView)paramView.findViewById(R.id.no_repeat_date_tv));
        this.mNoRepeatTimeTv = ((TextView)paramView.findViewById(R.id.no_repeat_time_tv));
        this.mRepeatTimeTv = ((TextView)paramView.findViewById(R.id.repeat_time_tv));
        this.mRepeatLayout = ((LinearLayout)paramView.findViewById(R.id.repeat_layout));
        this.mRepeatTv = ((TextView)paramView.findViewById(R.id.repeat_tv));
        this.mDelLayout = ((RelativeLayout)paramView.findViewById(R.id.del_layout));
        initListener();
        initData();
    }
    
    
    //  

    @Override
    public void finishActivity()
    {
        if ((getActivity() == null) || (getActivity().isFinishing()) || (!isAdded()))
            return;
        getActivity().finish();
    }

    @Override
    public void hideProgress()
    {
        hideProgress();
    }

    @Override
    public void setContentText(String paramString)
    {
        if ((getActivity() == null) || (getActivity().isFinishing()) || (!isAdded()))
            return; 
        mContentEt.setText(paramString);
    }

    @Override
    public void setNoRepeatDateText(String paramString)
    {
        if ((getActivity() == null) || (getActivity().isFinishing()) || (!isAdded()));
        while (mNoRepeatDateTv == null)
            return;
        mNoRepeatDateTv.setText(paramString);
    }
    @Override
    public void setNoRepeatDateVisible(boolean paramBoolean)
    {
        if ((getActivity() == null) || (getActivity().isFinishing()) || (!isAdded()));
        while (mNoRepeatDateTv == null)
            return;
        if (paramBoolean)
        {
            mNoRepeatDateTv.setVisibility(View.VISIBLE);
            return;
        }
        mNoRepeatDateTv.setVisibility(View.GONE);
    }
    @Override
    public void setNoRepeatTimeText(String paramString)
    {
        if ((getActivity() == null) || (getActivity().isFinishing()) || (!isAdded()));
        while (mNoRepeatTimeTv == null)
            return;
        mNoRepeatTimeTv.setText(paramString);
    }
    @Override
    public void setNoRepeatTimeVisible(boolean paramBoolean)
    {
        if ((getActivity() == null) || (getActivity().isFinishing()) || (!isAdded()));
        while (mNoRepeatTimeTv == null)
            return;
        if (paramBoolean)
        {
            mNoRepeatTimeTv.setVisibility(View.VISIBLE);
            return;
        }
        mNoRepeatTimeTv.setVisibility(View.GONE);
    }
    @Override
    public void setRepeatText(String paramString)
    {
        if ((getActivity() == null) || (getActivity().isFinishing()) || (!isAdded()));
        while (mRepeatTv == null)
            return;
        mRepeatTv.setText(paramString);
    }
    @Override
    public void setRepeatTimeText(String paramString)
    {
        if ((getActivity() == null) || (getActivity().isFinishing()) || (!isAdded()));
        while (mRepeatTimeTv == null)
            return;
        mRepeatTimeTv.setText(paramString);
    }
    @Override
    public void setRepeatTimeVisible(boolean paramBoolean)
    {
        if ((getActivity() == null) || (getActivity().isFinishing()) || (!isAdded()));
        while (mRepeatTimeTv == null)
            return;
        if (paramBoolean)
        {
            mRepeatTimeTv.setVisibility(View.VISIBLE);
            return;
        }
        mRepeatTimeTv.setVisibility(View.GONE);
    }
    @Override
    public void showDateTimeDialog(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
    {
        if (mScheduleEditPresenter == null)
            return;
        View localView = View.inflate(getActivity(), R.layout.choose_date_time_dialog, null);
        final WheelDatePicker localWheelDatePicker = (WheelDatePicker)localView.findViewById(R.id.date_picker);
        localWheelDatePicker.init(paramInt1, paramInt2, paramInt3);
        localWheelDatePicker.setWheelMargin(100);
        final WheelTimePicker localWheelTimePicker = (WheelTimePicker)localView.findViewById(R.id.time_picker);
        localWheelTimePicker.setShowSecond(false);
        localWheelTimePicker.setWheelMargin(100);
        localWheelTimePicker.init(paramInt4, paramInt5, 0);
        ViewHolder localViewHolder = new ViewHolder(localView);
        DialogPlus.newDialog(getActivity()).setContentHolder(localViewHolder).
                setHeader(R.layout.dialog_header).setCancelable(true).setGravity(80).
                setOnDismissListener(null).setExpanded(false).
                setContentHeight(-2).setOnCancelListener(null).
                setContentBackgroundResource(17170443).
                setOnClickListener(new OnClickListener()    {
                    public void onClick(DialogPlus dialogPlus, View view)
                    {
                        switch (view.getId())
                        {
                            case  R.id.sure_layout:
                                int i = localWheelDatePicker.getYear();
                                int j = localWheelDatePicker.getMonth();
                                int k = localWheelDatePicker.getDay();
                                int m = localWheelTimePicker.getHour();
                                int n = localWheelTimePicker.getMinute();
                                int i1 = localWheelTimePicker.getSecond();
                                mScheduleEditPresenter.setYMDHMS(i, j, k, m, n, i1);
                                dialogPlus.dismiss();
                                return;
                            case R.id.cancel_layout:
                                dialogPlus.dismiss();
                        }
                    }
                }).create().show();
    }
    @Override
    public void showProgress()
    {
        showProgress();
    }

    public void showTimeDialog(int paramInt1, int paramInt2, int paramInt3)
    {
        if (mScheduleEditPresenter == null)
            return;
        View localView = View.inflate(getActivity(), R.layout.choose_time_dialog, null);
        final WheelTimePicker localWheelTimePicker = (WheelTimePicker)localView.findViewById(R.id.time_picker);
        localWheelTimePicker.setShowSecond(false);
        localWheelTimePicker.setWheelMargin(100);
        localWheelTimePicker.init(paramInt1, paramInt2, 0);
        ViewHolder localViewHolder = new ViewHolder(localView);
        DialogPlus.newDialog(getActivity()).
                setContentHolder(localViewHolder).
                setHeader(R.layout.dialog_header).
                setCancelable(true).setGravity(80).
                setOnDismissListener(null).
                setExpanded(false).setContentHeight(-2).
                setOnCancelListener(null).
                setContentBackgroundResource(17170443).
                setOnClickListener(new OnClickListener()
        {
            public void onClick(DialogPlus dialogPlus, View view)
            {
                switch (view.getId())
                {
                    case R.id.sure_layout:
                        int i = localWheelTimePicker.getHour();
                        int j = localWheelTimePicker.getMinute();
                        int k = localWheelTimePicker.getSecond();
                        mScheduleEditPresenter.setHMS(i, j, k);
                        dialogPlus.dismiss();
                        return;
                    case R.id.cancel_layout:
                        dialogPlus.dismiss();
                }
            }
        }).create().show();
    }

    @Override
    public void showError(String message) {

    }

    public void showToast(@StringRes int resourceID)
    {
        showShortToast(resourceID);
    }

    public void showToast(CharSequence charSequence)
    {
        showShortToast(charSequence);
    }
}
