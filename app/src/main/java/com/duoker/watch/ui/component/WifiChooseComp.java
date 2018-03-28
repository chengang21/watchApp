package com.duoker.watch.ui.component;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.duoker.watch.R;
import com.duoker.watch.db.model.WifiHotSpotModel;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;

import java.util.ArrayList;
import java.util.List;

public class WifiChooseComp {
    private final Context mContext;
    private QuickAdapter<WifiHotSpotModel> mDataAdapter;
    private List<WifiHotSpotModel> mDataList = new ArrayList();
    private ListView mLv;
    private OnItemClickListener mOnItemClickListener;
    private View mView;

    public WifiChooseComp(Context paramContext) {
        this.mContext = paramContext;
        initView();
    }

    private void initListView() {
        List list = this.mDataList;
        if (list == null)
            return;

        if (this.mDataAdapter == null) {
            this.mDataAdapter = new QuickAdapter<WifiHotSpotModel>(mContext, android.R.layout.simple_list_item_1, mDataList) {
                @Override
                protected void convert(BaseAdapterHelper helper, WifiHotSpotModel item) {
                    helper.setText(android.R.id.text1, item.getWifiName());
                }
            };
            this.mLv.setAdapter(this.mDataAdapter);
            this.mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (mOnItemClickListener != null)
                        mOnItemClickListener.onItemClick(position, (WifiHotSpotModel) mDataAdapter.getItem(position));
                }
            });
        } else {

            this.mDataAdapter.clear();
            this.mDataAdapter.addAll(this.mDataList);
        }
    }

    private void initView() {
        this.mView = View.inflate(this.mContext, R.layout.wifi_choose_comp, null);
        this.mLv = ((ListView) this.mView.findViewById(R.id.lv1));
        initListView();
    }

    public void addAll(List<WifiHotSpotModel> paramList) {
        if (paramList == null)
            return;
        this.mDataList.addAll(paramList);
        initListView();
    }

    public View getView() {
        return this.mView;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public static interface OnItemClickListener {
        public void onItemClick(int pos, WifiHotSpotModel paramWifiHotSpotModel);
    }
}
