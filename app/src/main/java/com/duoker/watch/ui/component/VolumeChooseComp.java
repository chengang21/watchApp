package com.duoker.watch.ui.component;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.duoker.watch.R;
import com.duoker.watch.utils.VolumeHelper;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;

import java.util.ArrayList;
import java.util.List;

public class VolumeChooseComp {
    private final Context mContext;
    private QuickAdapter<VolumeHelper.VolumeData> mDataAdapter;
    private List<VolumeHelper.VolumeData> mDataList = new ArrayList();
    private ListView mLv;
    private int mSelect = 1;
    private View mView;

    public VolumeChooseComp(Context paramContext) {
        this.mContext = paramContext.getApplicationContext();
        initView();
    }

    private void initListView() {

        this.mDataList = new VolumeHelper(this.mContext).findData();
        if (this.mDataAdapter == null) {
            this.mDataAdapter = new QuickAdapter<VolumeHelper.VolumeData>( this.mContext, R.layout.volume_choose_comp_item, this.mDataList ){

                @Override
                protected void convert(BaseAdapterHelper helper, VolumeHelper.VolumeData item) {
                    helper.setText(R.id.name_tv, item.getDesc());
                    if (helper.getPosition() == mSelect)
                    {
                        helper.setImageResource(R.id.is_select_iv, R.drawable.hb_icon_check_mark);
                        return;
                    }
                    helper.setImageResource(R.id.is_select_iv, 0);
                }
            } ;
            this.mLv.setAdapter(this.mDataAdapter);
            this.mLv.setOnItemClickListener(new  AdapterView.OnItemClickListener(){

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    mSelect = position;
                    mDataAdapter.notifyDataSetChanged();
                }
            });
        }
        else {
            this.mDataAdapter.clear();
            this.mDataAdapter.addAll(this.mDataList);
        }
    }

    private void initView() {
        this.mView = View.inflate(this.mContext, R.layout.volume_choose_comp, null);
        this.mLv = ((ListView) this.mView.findViewById(R.id.lv1));
        initListView();
    }

    public VolumeHelper.VolumeData getSelectedItem() {
        return (VolumeHelper.VolumeData) this.mDataList.get(this.mSelect);
    }

    public View getView() {
        return this.mView;
    }

    public void selectValue(int paramInt) {
        int i = this.mDataList.size();
        for (int j = 0; ; j++)
            if (j < i) {
                if (((VolumeHelper.VolumeData) this.mDataList.get(j)).getVolume() == paramInt)
                    this.mSelect = j;
            } else {
                initListView();
                return;
            }
    }
}
