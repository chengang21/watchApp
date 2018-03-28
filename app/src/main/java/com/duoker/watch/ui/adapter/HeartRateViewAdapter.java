package com.duoker.watch.ui.adapter;

/**
 * Created by cheng on 2017/10/8.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.duoker.watch.R;
import com.duoker.watch.model.HeartRateBean;
import com.duoker.watch.model.HeartRateViewModel;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;
import java.util.List;

public class HeartRateViewAdapter extends RecyclerView.Adapter {
    private OnItemClickListener listener;
    private final Context mContext;
    private List<HeartRateViewModel> mData;
    private final View mHeaderView;

    public HeartRateViewAdapter(Context paramContext, View paramView, List<HeartRateViewModel> paramList) {
        this.mContext = paramContext;
        this.mHeaderView = paramView;
        this.mData = paramList;
    }

    public int getItemCount() {
        if (this.mData == null)
            return 0;
        return this.mData.size();
    }

    public int getItemViewType(int paramInt) {
        return (mData.get(paramInt)).getType();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final HeartRateViewModel localHeartRateViewModel;
        HeartRateBean localHeartRateBean;
        if ((holder instanceof DataViewHolder)) {
            localHeartRateViewModel = (HeartRateViewModel) this.mData.get(position);
            localHeartRateBean = localHeartRateViewModel.getHeartRateBean();
            if (localHeartRateBean == null)
                return;


            int i = localHeartRateBean.getHeartRate();
            if (i >= 90)
                ((DataViewHolder) holder).iv1.setImageResource(R.drawable.heart_rate_measure_heart);
            else if ((i >= 60) && (i < 90))
                ((DataViewHolder) holder).iv1.setImageResource(R.drawable.heart_rate_measure_heart);
            else
                ((DataViewHolder) holder).iv1.setImageResource(R.drawable.heart_rate_measure_heart);

            ((DataViewHolder) holder).rateView.setText(String.valueOf(localHeartRateBean.getHeartRate()));
            Date localDate = new Date(1000L * localHeartRateBean.getCalcTime());
            ((DataViewHolder) holder).timeView.setText(DateFormatUtils.format(localDate, "yyyy-MM-dd HH:mm:ss"));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (listener != null)
                        listener.onItemClick(v, position, localHeartRateViewModel);
                }
            });

        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt) {
        if (paramInt == 0)
            return new HeaderViewHolder(this.mHeaderView);

        return new DataViewHolder(View.inflate(this.mContext, R.layout.heart_rate_data_item, null));
    }

    public void setData(List<HeartRateViewModel> paramList) {
        this.mData = paramList;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener paramOnItemClickListener) {
        this.listener = paramOnItemClickListener;
    }

    class DataViewHolder extends RecyclerView.ViewHolder {
        ImageView iv1;
        TextView rateView;
        TextView timeView;

        public DataViewHolder(View view) {
            super(view);
            this.iv1 = ((ImageView) view.findViewById(R.id.iv1));
            this.rateView = ((TextView) view.findViewById(R.id.rate_view));
            this.timeView = ((TextView) view.findViewById(R.id.time_view));
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(View view) {
            super(view);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View paramView, int paramInt, HeartRateViewModel paramHeartRateViewModel);
    }
}