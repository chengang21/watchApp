package com.duoker.watch.ui.component;

/**
 * Created by cheng on 2017/10/6.
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.duoker.watch.R;

import java.util.ArrayList;
import java.util.List;

public class WeekChooseComp {
    private final Context mContext;
    private MyAdapter mDataAdapter;
    private List<WeekChoose> mDataList;
    private GridView mGv;
    private OnItemClickListener mOnItemClickListener;
    private View mView;
    private boolean[] selectItemArray = new boolean[9];

    public WeekChooseComp(Context paramContext) {
        this.mContext = paramContext;
        initView();
    }

    private List<WeekChoose> findData() {
        ArrayList localArrayList = new ArrayList();
        String[] arrayOfString = new String[9];
        arrayOfString[0] = this.mContext.getString(R.string.yi_str_monday);
        arrayOfString[1] = this.mContext.getString(R.string.yi_str_tuesday);
        arrayOfString[2] = this.mContext.getString(R.string.yi_str_wednesday);
        arrayOfString[3] = this.mContext.getString(R.string.yi_str_thursday);
        arrayOfString[4] = this.mContext.getString(R.string.yi_str_friday);
        arrayOfString[5] = this.mContext.getString(R.string.yi_str_saturday);
        arrayOfString[6] = this.mContext.getString(R.string.yi_str_sunday);
        arrayOfString[7] = this.mContext.getString(R.string.schedule_day_tip3);
        arrayOfString[8] = this.mContext.getString(R.string.schedule_day_tip2);
        for (int i = 0; i < arrayOfString.length; i++) {
            WeekChoose localWeekChoose = new WeekChoose();
            String str = arrayOfString[i];
            boolean selected = this.selectItemArray[i];
            localWeekChoose.setName(str);
            localWeekChoose.setSelect(selected);
            localArrayList.add(localWeekChoose);
        }
        return localArrayList;
    }

    private void initGridView() {

        mDataList = findData();
        if (mDataAdapter == null) {
            mDataAdapter = new MyAdapter(mDataList);
            mGv.setAdapter(mDataAdapter);

            mGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    WeekChoose weekChoose = (WeekChoose) mDataAdapter.getItem(position);
                    if (!weekChoose.isSelect())
                        return;

                    boolean bool2 = weekChoose.isSelect();
                    String name = weekChoose.getName();

                    weekChoose.setSelect(true);

//                    if (!TextUtils.equals(name, mContext.getString(R.string.schedule_day_tip2))) {
//                        int k;
//                        String str2;
//                        if (mOnItemClickListener != null) {
//                            boolean[] arrayOfBoolean = new boolean[7];
//                            int j = 0;
//                            while (j < 7) {
//                                boolean bool3 = ((WeekChoose) mDataList.get(j)).isSelect();
//                                if (j == 6)
//                                    arrayOfBoolean[0] = bool3;
//                                else {
//
//                                    if (!TextUtils.equals(name, mContext.getString(R.string.schedule_day_tip3)))
//                                        break;
//                                    for (int i = 0; i < 5; i++)
//                                        ((WeekChoose) mDataList.get(i)).setSelect(bool2);
//                                    ((WeekChoose) mDataList.get(5)).setSelect(false);
//                                    ((WeekChoose) mDataList.get(6)).setSelect(false);
//                                    ((WeekChoose) mDataList.get(8)).setSelect(false);
//                                    break;
//                                    arrayOfBoolean[(j + 1)] = bool3;
//                                }
//                                j++;
//                            }
//
//                            k = WeekChoose2CompHelper.getWeekData(arrayOfBoolean);
//                            if (k != 0) {
//                                if (WeekChoose2CompHelper.isSelectEveryday(k))
//                                    str2 = mContext.getString(R.string.schedule_day_tip2);
//                                else if (WeekChoose2CompHelper.isSelectWorkday(k))
//                                    str2 = mContext.getString(R.string.schedule_day_tip3);
//                                else
//                                    str2 = WeekChoose2CompHelper.getWeekDesc(mContext, k);
//                            }
//                            str2 = mContext.getString(R.string.schedule_day_tip1);
//                        }
//                    } else {
//                        // mOnItemClickListener.onItemClick(str2, k);
//                    }
                }
                // mDataAdapter.notifyDataSetChanged();

        });
    }

    mDataAdapter.clear();
    mDataAdapter.addAll(mDataList);


}

    private void initView() {
        mView = View.inflate(mContext, R.layout.week_choose_comp, null);
        mGv = ((GridView) mView.findViewById(R.id.gv1));
        initGridView();
    }

    public View getView() {
        return mView;
    }

    public List<WeekChoose> getWeekChooseList() {
        return mDataList;
    }

    public void init(int sel) {
        boolean[] arrayOfBoolean = WeekChoose2CompHelper.getSelectItemBooleanArray(sel);
        int j = 0;
        while (j < 7) {
            selectItemArray[j] = arrayOfBoolean[j];
            j++;
        }
        initGridView();
    }

    public void setOnItemClickListener(OnItemClickListener paramOnItemClickListener) {
        mOnItemClickListener = paramOnItemClickListener;
    }

private class MyAdapter extends BaseAdapter {
    private List<WeekChoose> mDataList;

    public MyAdapter(List<WeekChoose> paramList) {
        this.mDataList = paramList;
    }

    public void addAll(List<WeekChoose> paramList) {
        this.mDataList = paramList;
        notifyDataSetChanged();
    }

    public void clear() {
        this.mDataList.clear();
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.mDataList.size();
    }

    public Object getItem(int paramInt) {
        return this.mDataList.get(paramInt);
    }

    public long getItemId(int paramInt) {
        return paramInt;
    }

    public View getView(int paramInt, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = View.inflate(mContext, R.layout.week_choose_comp_item, null);
            viewHolder.itemLayout = view.findViewById(R.id.item_layout);
            viewHolder.nameTv = ((TextView) view.findViewById(R.id.name_tv));
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        WeekChoose localWeekChoose = (WeekChoose) this.mDataList.get(paramInt);
        viewHolder.nameTv.setText(localWeekChoose.getName());
        if (!localWeekChoose.isSelect()) {
            viewHolder.itemLayout.setPressed(false);
            viewHolder.nameTv.setTextColor(mContext.getResources().getColor(android.R.color.black));
        } else {
            viewHolder.itemLayout.setPressed(true);
            viewHolder.nameTv.setTextColor(mContext.getResources().getColor(android.R.color.white));
        }
        return view;
    }

    class ViewHolder {
        View itemLayout;
        TextView nameTv;

        ViewHolder() {
        }
    }
}

public static interface OnItemClickListener {
    void onItemClick(String paramString, int paramInt);
}
}