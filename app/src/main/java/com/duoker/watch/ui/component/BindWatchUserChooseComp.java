package com.duoker.watch.ui.component;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.duoker.watch.R;
import com.duoker.watch.model.BindUserListBean;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;

import java.util.List;

public class BindWatchUserChooseComp
{
  private final Context mContext;
  private QuickAdapter<BindUserListBean.UserListBean> mDataAdapter;
  private List<BindUserListBean.UserListBean> mDataList;
  private ListView mLv;
  private int mSelect = 0;
  private View mView;

  public BindWatchUserChooseComp(Context paramContext, List<BindUserListBean.UserListBean> paramList)
  {
    this.mContext = paramContext.getApplicationContext();
    this.mDataList = paramList;
    initView();
  }

  private void initListView()
  {
    try
    {
      if (this.mDataAdapter == null)
      {
        this.mDataAdapter = new QuickAdapter<BindUserListBean.UserListBean>(mContext, R.layout.bind_watch_user_choose_comp_item, this.mDataList) {
          @Override
          protected void convert(BaseAdapterHelper helper, BindUserListBean.UserListBean item) {
            helper.setText(R.id.name_tv, item.getUserName());
            if (helper.getPosition() == mSelect)
            {
              helper.setImageResource(R.id.is_select_iv, R.drawable.hb_icon_check_mark);
              return;
            }
            helper.setImageResource(R.id.is_select_iv, 0);
          }
        } ;
        this.mLv.setAdapter(this.mDataAdapter);
        this.mLv.setOnItemClickListener(new AdapterView.OnItemClickListener( ){

          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mSelect = position;
            mDataAdapter.notifyDataSetChanged();
          }
        });
      }
      else
      {
        this.mDataAdapter.clear();
        this.mDataAdapter.addAll(this.mDataList);
      }
    }
    finally
    {
    }
  }

  private void initView()
  {
    this.mView = View.inflate(this.mContext, R.layout.bind_watch_user_choose_comp, null);
    this.mLv = ((ListView)this.mView.findViewById(R.id.lv1));
    initListView();
  }

  public BindUserListBean.UserListBean getSelectedItem()
  {
    if ((this.mDataList != null) && (!this.mDataList.isEmpty()))
      return (BindUserListBean.UserListBean)this.mDataList.get(this.mSelect);
    return null;
  }

  public View getView()
  {
    return this.mView;
  }
}
