package com.duoker.watch.ui.view.pagerdatepicker;

/**
 * Created by cheng on 2017/10/15.
 */

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.duoker.watch.R;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DefaultDateAdapter extends RecyclerView.Adapter<DefaultViewHolder> {
    private boolean isChinese = false;
    private final Context mContext;
    private List<DateHelper.DateItem> mDataList;
    private DescEntity mDescEntity;
    private OnOperateListener mOnOperateListener;
    private int mSelectPosition = 0;
    private Drawable mSelectedDrawable;
    private PickerTypeEnum mType = PickerTypeEnum.DAY;

    public DefaultDateAdapter(Context paramContext) {
        this.mContext = paramContext;
        init();
    }

    public DefaultDateAdapter(Context paramContext, List<DateHelper.DateItem> paramList) {
        this.mContext = paramContext;
        this.mDataList = paramList;
        init();
    }

    private void doDay(DefaultViewHolder defaultViewHolder, int paramInt) {
        Date localDate = ((DateHelper.DateItem) this.mDataList.get(paramInt)).getDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(localDate);
        if (this.mDescEntity == null) {
            int k = -1 + calendar.get(Calendar.DAY_OF_WEEK);
            if (k == 0)
                k = 7;
            defaultViewHolder.topView.setText(String.valueOf(k));
            defaultViewHolder.centerView.setText(String.valueOf(calendar.get(Calendar.DATE)));
        } else {
            int dayofweek = calendar.get(Calendar.DAY_OF_WEEK);
            String week = null;
            switch (dayofweek) {
                case 1:
                    week = this.mDescEntity.getSunday();
                    break;
                case 2:
                    week = this.mDescEntity.getMonday();
                    break;
                case 3:
                    week = this.mDescEntity.getTuesday();
                    break;
                case 4:
                    week = this.mDescEntity.getWednesday();
                    break;
                case 5:
                    week = this.mDescEntity.getThursday();
                    break;
                case 6:
                    week = this.mDescEntity.getFriday();
                    break;
                case 7:
                    week = this.mDescEntity.getSaturday();
                    break;
                default:
                    week = "Error";
            }
            defaultViewHolder.topView.setText((CharSequence) week);
        }
        defaultViewHolder.bottomView.setText(String.valueOf(calendar.get(Calendar.DATE)));
    }

    private void doMonth(DefaultViewHolder viewHolder, int pos) {
        Date localDate = ((DateHelper.DateItem) this.mDataList.get(pos)).getDate();
        Calendar localCalendar = Calendar.getInstance();
        localCalendar.setTime(localDate);
        int i = localCalendar.get(Calendar.MONTH);
        viewHolder.topView.setVisibility(View.GONE);
        if (this.mDescEntity == null) {
            viewHolder.centerView.setText(String.valueOf(i + 1));
            return;
        }
        String month = null;
        switch (i) {
            case 0:
                month = this.mDescEntity.getJanuary();
                break;
            case 1:
                month = this.mDescEntity.getFebruary();
                break;
            case 2:
                month = this.mDescEntity.getMarch();
                break;
            case 3:
                month = this.mDescEntity.getApril();
                break;
            case 4:
                month = this.mDescEntity.getMay();
                break;
            case 5:
                month = this.mDescEntity.getJune();
                break;
            case 6:
                month = this.mDescEntity.getJuly();
                break;
            case 7:
                month = this.mDescEntity.getAugust();
                break;
            case 8:
                month = this.mDescEntity.getSeptember();
                break;
            case 9:
                month = this.mDescEntity.getOctober();
                break;
            case 10:
                month = this.mDescEntity.getNovember();
                break;
            case 11:
                month = this.mDescEntity.getDecember();
                break;
            default:
        }

        if (TextUtils.isEmpty((CharSequence) month))
            month = String.valueOf(i + 1);

        viewHolder.centerView.setText((CharSequence) month);
        viewHolder.bottomView.setVisibility(View.GONE);

    }

    private void doWeek(DefaultViewHolder paramDefaultViewHolder, int paramInt) {
        DateHelper.BeginNEndItem localBeginNEndItem = DateHelper.getWeekBeginNEnd(((DateHelper.DateItem) this.mDataList.get(paramInt)).getDate());
        Date localDate1 = localBeginNEndItem.getBeginTime();
        Date localDate2 = localBeginNEndItem.getEndTime();
        String str1 = DateHelper.getDate2Str(localDate1, "M.d");
        String str2 = DateHelper.getDate2Str(localDate2, "M.d");
        paramDefaultViewHolder.topView.setText(str1);
        Calendar localCalendar = Calendar.getInstance();
        localCalendar.setTime(localDate1);
        paramDefaultViewHolder.centerView.setText(String.valueOf(localCalendar.get(Calendar.DATE)));
        paramDefaultViewHolder.bottomView.setText(str2);
    }

    private void init() {
        this.mSelectedDrawable = this.mContext.getResources().getDrawable(R.drawable.ec_pager_date_picker_circle_bg);
        this.isChinese = isZh();
    }

    private boolean isZh() {
        return this.mContext.getResources().getConfiguration().locale.getLanguage().endsWith("zh");
    }

    public int getItemCount() {
        return this.mDataList.size();
    }


    public DefaultViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int pos) {
        View view;
        if (this.mType == PickerTypeEnum.DAY)
            view = View.inflate(this.mContext, R.layout.item_view_default_date, null);
        else if (this.mType == PickerTypeEnum.WEEK)
            view = View.inflate(this.mContext, R.layout.item_view_default_week_date, null);
        else if (this.mType == PickerTypeEnum.MONTH)
            view = View.inflate(this.mContext, R.layout.item_view_default_month_date, null);
        else
            view = View.inflate(this.mContext, R.layout.item_view_default_date, null);

        return new DefaultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DefaultViewHolder holder, final int pos) {
        final DateHelper.DateItem dateItem = (DateHelper.DateItem) this.mDataList.get(pos);
        if (this.mSelectPosition == pos) {
            holder.centerLayout.setBackgroundDrawable(this.mSelectedDrawable);

            if (this.mType == PickerTypeEnum.DAY)
                doDay(holder, pos);
            else if (this.mType == PickerTypeEnum.WEEK)
                doWeek(holder, pos);
            else if (this.mType == PickerTypeEnum.MONTH)
                doMonth(holder, pos);

        } else {
            holder.centerLayout.setBackgroundDrawable(null);
        }

        holder.rootView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mOnOperateListener == null)
                    return;

                if (mSelectPosition != pos) {
                    mSelectPosition = pos;
                    notifyDataSetChanged();
                }
                mOnOperateListener.onItemClick(dateItem, pos);
            }
        });
    }


    public void setData(List<DateHelper.DateItem> paramList) {
        this.mDataList = paramList;
        notifyDataSetChanged();
    }

    public void setDescEntity(DescEntity paramDescEntity) {
        this.mDescEntity = paramDescEntity;
        notifyDataSetChanged();
    }

    public void setOnItemSelectedListener(OnOperateListener paramOnOperateListener) {
        this.mOnOperateListener = paramOnOperateListener;
    }

    public void setSelectedDrawable(Drawable paramDrawable) {
        this.mSelectedDrawable = paramDrawable;
        notifyDataSetChanged();
    }

    public void setSelectionPosition(int paramInt) {
        this.mSelectPosition = paramInt;
        notifyDataSetChanged();
        if ((this.mOnOperateListener == null) || (this.mDataList == null) || (paramInt >= this.mDataList.size()))
            return;
        this.mOnOperateListener.onItemSelected((DateHelper.DateItem) this.mDataList.get(paramInt), paramInt);
    }

    public void setType(PickerTypeEnum paramPickerTypeEnum) {
        this.mType = paramPickerTypeEnum;
        notifyDataSetChanged();
    }


    public interface OnOperateListener {
        void onItemClick(DateHelper.DateItem paramDateItem, int pos);
        void onItemSelected(DateHelper.DateItem paramDateItem, int pos);
    }
}


class DefaultViewHolder extends RecyclerView.ViewHolder {
    TextView bottomView;
    View centerLayout;
    TextView centerView;
    View rootView;
    TextView topView;

    public DefaultViewHolder(View view) {
        super(view);

        rootView = view;
        topView = ((TextView) view.findViewById(R.id.top_view));
        centerLayout = view.findViewById(R.id.center_layout);
        centerView = ((TextView) view.findViewById(R.id.center_view));
        bottomView = ((TextView) view.findViewById(R.id.bottom_view));
    }
}
