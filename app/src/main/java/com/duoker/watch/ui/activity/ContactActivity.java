package com.duoker.watch.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.duoker.watch.R;
import com.duoker.watch.db.model.CardNumberBean;
import com.duoker.watch.presenters.WatchContactsPresenter;
import com.duoker.watch.presenters.impl.WatchContactsPresenterImpl;
import com.duoker.watch.repository.CardNumberRepositoryImpl;
import com.duoker.watch.repository.WatchRepositoryImpl;
import com.duoker.watch.repository.WatchSettingRepositoryImpl;
import com.duoker.watch.ui.base.BaseActivity;
import com.duoker.watch.ui.event.AddOrEditWatchContactsEvent;
import com.duoker.watch.ui.view.CircleImageView;
import com.duoker.watch.ui.view.MyToolbar;
import com.duoker.watch.ui.view.SwitchButton;
import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class ContactActivity extends BaseActivity implements WatchContactsPresenter.View {
    //private Animation animation;
    private CircleImageView mAvatarIv;
    private ListView mLv1;
    private TextView mMasterTv;
    private TextView mPhoneTv;
    private MyToolbar mToolbarLayout;
    private ImageView mToolbarMenuAddContact;
    private ImageView mToolbarMenuBack;
    private ImageView mToolbarMenuSync;
    private SwitchButton mWhiteNameListTb;
    private WatchContactsPresenterImpl mContactsPresenter;
    private QuickAdapter<CardNumberBean> mDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_contact);
        initToolbar();
        initWidgets();
        setupListener();

        EventBus.getDefault().register(this);
        mContactsPresenter = new WatchContactsPresenterImpl(this, new CardNumberRepositoryImpl(), new WatchSettingRepositoryImpl(), new WatchRepositoryImpl(getApplicationContext()));
        mContactsPresenter.onCreate(savedInstanceState);
    }

    private void initToolbar() {
        this.mToolbarLayout = ((MyToolbar) findViewById(R.id.toolbar_layout));
        this.mToolbarMenuBack = ((ImageView) findViewById(R.id.toolbar_menu_back));
        this.mToolbarMenuBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initWidgets() {
        this.mToolbarMenuSync = ((ImageView) findViewById(R.id.toolbar_menu_sync));
        this.mToolbarMenuAddContact = ((ImageView) findViewById(R.id.toolbar_menu_add_contact));
        this.mAvatarIv = ((CircleImageView) findViewById(R.id.avatar_iv));
        this.mMasterTv = ((TextView) findViewById(R.id.master_tv));
        this.mPhoneTv = ((TextView) findViewById(R.id.phone_tv));
        this.mWhiteNameListTb = ((SwitchButton) findViewById(R.id.white_name_list_tb));
        this.mLv1 = ((ListView) findViewById(R.id.lv1));
    }

    private void setupListener() {

        mToolbarMenuAddContact.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent addActivity = new Intent(ContactActivity.this, WatchContactsAddActivity.class);
                startActivity(addActivity);
            }
        });

        mToolbarMenuSync.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v)
            {
                if (!mContactsPresenter.isAdmin())
                {
                    showShortToast("没有权限！");
                    return;
                }
                mContactsPresenter.saveContacts(false);
            }
        });

        mWhiteNameListTb.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                if (! mContactsPresenter.isAdmin())
                {
                    showShortToast(R.string.common_all_no_auth);
                    boolean ckd = mWhiteNameListTb.isChecked();
                    mContactsPresenter.setWhiteNameList(ckd);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (false /*need save*/) {
            showSaveDialog();
        } else {
            finish();
        }
    }

    private void showSaveDialog() {

    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void inflateListView(List<CardNumberBean> list) {

        if (mDataAdapter == null) {
            mDataAdapter = new QuickAdapter<CardNumberBean>(ContactActivity.this, R.layout.contact_item, list) {
                protected void convert(final BaseAdapterHelper adapterHelper, final CardNumberBean bean) {
                    String shortnum = bean.getCardshortnum() == null ? "" : bean.getCardshortnum();;
                    adapterHelper.setText(R.id.name_tv, bean.getCardname());
                    adapterHelper.setText(R.id.number_tv, bean.getCardnum());
                    adapterHelper.setText(R.id.short_num_tv, shortnum);
                    adapterHelper.setText(R.id.avatar_view, bean.getCardname());

                    adapterHelper.getView(R.id.content_layout).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!mContactsPresenter.isAdmin()) {
                                showShortToast(R.string.common_all_no_auth);
                                return;
                            }
                            Intent intent = new Intent(ContactActivity.this, WatchContactsAddActivity.class);
                            intent.putExtra("ENTITY", bean);
                            intent.putExtra("POSITION", adapterHelper.getPosition());
                            startActivity(intent);
                        }
                    });

                    adapterHelper.getView(R.id.content_layout).setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            remove(bean);
                            return true;
                        }
                    });

                    if (!isFirstPositionForSection(adapterHelper)) {
                        adapterHelper.setVisible(R.id.category_layout, false);
                    } else {
                        adapterHelper.setVisible(R.id.category_layout, true);
                        int type = bean.getCardtype();
                        if (type == 1) {
                            adapterHelper.setText(R.id.category_tv, getString(R.string.contacts_item_2));
                        } else if (type == 2) {
                            adapterHelper.setText(R.id.category_tv, getString(R.string.contacts_item_4));
                        } else if (type == 3) {
                            adapterHelper.setText(R.id.category_tv, getString(R.string.contacts_item_5));
                        }
                    }
                }

                public boolean isFirstPositionForSection(BaseAdapterHelper adapterHelper) {
                    int i = adapterHelper.getPosition();
                    int j = ((CardNumberBean) getItem(i)).getCardtype();
                    for (int k = 0; ; k++) {
                        int m = getCount();
                        boolean isFirst = false;
                        if (k < m) {
                            if (((CardNumberBean) getItem(k)).getCardtype() != j)
                                continue;
                            isFirst = false;
                            if (k == i)
                                isFirst = true;
                        }
                        return isFirst;
                    }
                }
            };
            if (mLv1 != null)
                mLv1.setAdapter(mDataAdapter);
            return;
        }
        mDataAdapter.clear();
        mDataAdapter.addAll(list);
    }

    @Override
    public void setAvatar(String paramString) {
        if (mAvatarIv != null)
            Glide.with( getApplicationContext()).load(paramString).error(R.drawable.iflytek_face_01).into(mAvatarIv);
    }

    @Override
    public void setMasterText(String owner) {
        if (mMasterTv != null)
            mMasterTv.setText(getString(R.string.watch_owner) + owner);
    }

    @Override
    public void setPhoneText(String phone) {
        if (mPhoneTv != null)
            mPhoneTv.setText(getString(R.string.watch_tele_number) + phone);
    }

    @Override
    public void setWhiteNameListChecked(boolean paramBoolean) {
        if (mWhiteNameListTb != null)
            mWhiteNameListTb.setChecked(paramBoolean);
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showToast(@StringRes int resourceID) {
        showShortToast(resourceID);
    }

    @Override
    public void showToast(CharSequence charSequence) {
        showShortToast(charSequence);
    }


    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(AddOrEditWatchContactsEvent e)
    {
        if (this.mContactsPresenter == null)
            return;
        CardNumberBean localCardNumberBean = e.cardNumberBean;
        int i = e.position;
        this.mContactsPresenter.addItem(localCardNumberBean, i);
    }

}
