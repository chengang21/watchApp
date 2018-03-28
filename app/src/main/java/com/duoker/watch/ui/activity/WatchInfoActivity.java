package com.duoker.watch.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.duoker.watch.R;
import com.duoker.watch.presenters.WatchInfoPresenter;
import com.duoker.watch.presenters.impl.WatchInfoPresenterImpl;
import com.duoker.watch.repository.CalcWalkCountRepositoryImpl;
import com.duoker.watch.repository.WatchRepositoryImpl;
import com.duoker.watch.storage.WatchsStorage;
import com.duoker.watch.ui.base.BaseActivity;
import com.duoker.watch.ui.view.MyToolbar;

public class WatchInfoActivity extends BaseActivity implements WatchInfoPresenter.View {

    private ImageView mAvatarIv;
    private RelativeLayout mAvatarLayout;
    private String mFilePath;
    private RelativeLayout mNicknameLayout;
    private TextView mNicknameTv;
    private RelativeLayout mPhoneLayout;
    private TextView mPhoneTv;
    private RelativeLayout mSexLayout;
    private TextView mSexTv;
    private RelativeLayout mTargetStepLayout;
    private TextView mTargetStepTv;
    private MyToolbar mToolbarLayout;
    private ImageView mToolbarMenuBack;
    private ImageView mToolbarMenuSure;
    private RelativeLayout mUnbindLayout;
    private WatchInfoPresenterImpl mWatchInfoPresenter;
    private RelativeLayout mWatchidLayout;
    private TextView mWatchidTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_info);

        // EventBus.getDefault().register(this);
        mWatchInfoPresenter = new WatchInfoPresenterImpl(this, new WatchRepositoryImpl(getApplicationContext()),  new CalcWalkCountRepositoryImpl(getApplicationContext()));

        this.mToolbarLayout = ((MyToolbar) findViewById(R.id.toolbar_layout));
        this.mToolbarMenuBack = ((ImageView) findViewById(R.id.toolbar_menu_back));
        this.mToolbarMenuBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        mToolbarMenuSure = ((ImageView) findViewById(R.id.toolbar_menu_sure));
        mAvatarLayout = ((RelativeLayout) findViewById(R.id.avatar_layout));
        mAvatarIv = ((ImageView) findViewById(R.id.avatar_iv));
        mNicknameLayout = ((RelativeLayout) findViewById(R.id.nickname_layout));
        mNicknameTv = ((TextView) findViewById(R.id.nickname_tv));
        mSexLayout = ((RelativeLayout) findViewById(R.id.sex_layout));
        mSexTv = ((TextView) findViewById(R.id.sex_tv));
        mWatchidLayout = ((RelativeLayout) findViewById(R.id.watchid_layout));
        mWatchidTv = ((TextView) findViewById(R.id.watchid_tv));
        mPhoneLayout = ((RelativeLayout) findViewById(R.id.phone_layout));
        mPhoneTv = ((TextView) findViewById(R.id.phone_tv));
        mTargetStepLayout = ((RelativeLayout) findViewById(R.id.target_step_layout));
        mTargetStepTv = ((TextView) findViewById(R.id.target_step_tv));
        mUnbindLayout = ((RelativeLayout) findViewById(R.id.unbind_layout));

        if (getIntent() != null) {
//            WatchsStorage storage = (WatchsStorage) getIntent().getSerializableExtra(WatchInfoActivity.class.getSimpleName());
//            mWatchInfoPresenter.initData(storage);
        }

        initListener();
    }


    private void initListener() {
        this.mToolbarMenuSure.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                saveWatchInfo();
            }
        });
        this.mAvatarLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                Intent localIntent = new Intent(WatchInfoActivity.this, ChooseAvatarImageActivity.class);
                startActivityForResult(localIntent, 1);
            }
        });
        this.mNicknameLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String nickname = mNicknameTv.getText().toString();
                Intent intent = new Intent(WatchInfoActivity.this, WatchOwnerNicknameActivity.class);
                intent.putExtra(WatchOwnerNicknameActivity.TAG, nickname);
                startActivity(intent);
            }
        });

        this.mSexLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
//                if (!mWatchInfoPresenter.isAdmin()) {
//                    showToast(R.string.common_all_no_auth);
//                    return;
//                }
                showChooseSexDialog();
            }
        });
        this.mPhoneLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
//                if (!mWatchInfoPresenter.isAdmin()) {
//                    showToast(R.string.common_all_no_auth);
//                    return;
//                }
                WatchsStorage storage = mWatchInfoPresenter.getWatchInfo();
                Intent intent = new Intent(WatchInfoActivity.this, WatchPhoneActivity.class);
                intent.putExtra(WatchPhoneActivity.TAG, storage);
                startActivity(intent);
            }
        });
        this.mWatchidLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View paramAnonymousView) {
                WatchsStorage storage = mWatchInfoPresenter.getWatchInfo();
                Intent intent = new Intent(WatchInfoActivity.this, WatchInfoWatchIdActivity.class);
                intent.putExtra(WatchInfoWatchIdActivity.TAG, storage);
                startActivity(intent);
            }
        });
//        this.mTargetStepLayout.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View paramAnonymousView) {
//                        TargetStepChooseCompWheel localTargetStepChooseCompWheel = new TargetStepChooseCompWheel(WatchInfoActivity.this);
//                        localTargetStepChooseCompWheel.select(mTargetStepTv.getText().toString());
//                        DialogPlus.newDialog(WatchInfoActivity.this).setContentHolder(new ViewHolder(localTargetStepChooseCompWheel.getView())).setHeader(2130968688).
//                                setCancelable(true).setGravity(80).setOnDismissListener(null).
//                                setExpanded(false).setContentHeight(-2).
//                                setOnCancelListener(null).setContentBackgroundResource(17170443).
//                                setOnClickListener(new View.OnClickListener() {
//
//                                    @Override
//                                    public void onClick(View v) {
//                                        switch (v.getId()) {
//                                            case R.id.title_tv:
//                                            default:
//                                                return;
//                                            case R.id.sure_layout:
//                                                Long localLong = this.val$targetStepChooseCompWheel.getSelectedItem();
//                                                WatchInfoActivity.access$200(this.this$1.this$0).setStepCountTarget(localLong);
//                                                paramDialogPlus.dismiss();
//                                                return;
//                                            case 2131558812:
//                                        }
//                                        paramDialogPlus.dismiss();
//                                    }
//                                }).create().show();
//                    }
//                });

        this.mUnbindLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View paramAnonymousView) {
                new AlertDialog.Builder(WatchInfoActivity.this).setTitle(R.string.common_all_tip_1).
                        setMessage(R.string.manger_watches_info_is_unbind).
                        setPositiveButton(R.string.common_all_tip_2, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mWatchInfoPresenter.delWatch();
                            }
                        }).setNegativeButton(
                        R.string.common_all_tip_3,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
            }
        });
    }

    private void saveWatchInfo() {
        String str1 = this.mNicknameTv.getText().toString();
        if (TextUtils.equals(this.mSexTv.getText().toString(), getString(R.string.manger_watches_info_male)))
            ;
        for (String str2 = "1"; ; str2 = "2") {
            this.mWatchInfoPresenter.saveWatchInfo(this.mFilePath, str1, str2);
            return;
        }
    }

    private void showChooseSexDialog() {
        if (TextUtils.equals(this.mSexTv.getText().toString(), getString(R.string.manger_watches_info_male)))
            ;
//        for (SexChooseComp.SexType localSexType = SexChooseComp.SexType.MALE; ; localSexType = SexChooseComp.SexType.FEMALE) {
//            final SexChooseComp localSexChooseComp = new SexChooseComp(getApplicationContext());
//            localSexChooseComp.init(localSexType);
//            ViewHolder localViewHolder = new ViewHolder(localSexChooseComp.getView());
//            DialogPlus.newDialog(this).setContentHolder(localViewHolder).
//                    setHeader(2130968688).setCancelable(true).setGravity(80).
//                    setOnDismissListener(null).setExpanded(false).setContentHeight(-2).
//                    setOnCancelListener(null).setContentBackgroundResource(17170443).
//                    setOnClickListener(new OnClickListener() {
//                public void onClick(DialogPlus paramAnonymousDialogPlus, View paramAnonymousView) {
//                    switch (paramAnonymousView.getId()) {
//                        default:
//                            return;
//                        case R.id.male_layout:
//                            localSexChooseComp.clickMaleLayout();
//                            return;
//                        case R.id.female_layout:
//                            localSexChooseComp.clickFemaleLayout();
//                            return;
//                        case R.id.sure_layout:
//                            SexChooseComp.SexType localSexType = localSexChooseComp.getSex();
//
//                            switch (localSexType) {
//
//                                case 1:
//                                    WatchInfoActivity.this.mSexTv.setText(R.string.manger_watches_info_male);
//                                    break;
//                                case 2:
//                                    WatchInfoActivity.this.mSexTv.setText(R.string.manger_watches_info_female);
//                            }
//                            paramAnonymousDialogPlus.dismiss();
//
//                        case R.id.cancel_layout:
//                    }
//                    paramAnonymousDialogPlus.dismiss();
//                }
//            }).create().show();
//            return;
//        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1) {
            this.mFilePath = data.getStringExtra("FILE_PATH");
            Glide.with(getApplicationContext()).load(this.mFilePath).error(R.drawable.iflytek_face_01).into(this.mAvatarIv);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    protected void onDestroy() {
        super.onDestroy();
        // EventBus.getDefault().unregister(this);
    }

//    @Subscribe
//    public void onEvent(SetWatchInfoNicknameEvent paramSetWatchInfoNicknameEvent)
//    {
//        String str = paramSetWatchInfoNicknameEvent.nickname;
//        if (this.mNicknameTv != null)
//        {
//            this.mNicknameTv.setText(str);
//            this.mWatchInfoPresenter.setNickname(str);
//        }
//    }

//    @Subscribe
//    public void onEvent(SetWatchInfoPhoneEvent paramSetWatchInfoPhoneEvent)
//    {
//        String str = paramSetWatchInfoPhoneEvent.phone;
//        if (this.mPhoneTv != null)
//        {
//            this.mPhoneTv.setText(str);
//            this.mWatchInfoPresenter.setPhone(str);
//        }
//    }


    @Override
    public void finishActivity() {
        finish();
    }

    public void hideLoading() {
        hideProgress();
    }

    @Override
    public void setAvatarImg(String url) {
        if (mAvatarIv != null)
            Glide.with(getApplicationContext()).load(url).error(R.drawable.iflytek_face_01).into(mAvatarIv);
    }

    @Override
    public void setNicknameText(String nickname) {
        mNicknameTv.setText(nickname);
    }

    @Override
    public void setPhoneText(String phoneText) {
        mPhoneTv.setText(phoneText);
    }

    @Override
    public void setSexText(int sex) {
        mSexTv.setText(sex);
    }

    @Override
    public void setTargetStepText(String step) {
        if (mTargetStepTv != null)
            mTargetStepTv.setText(step);
    }

    @Override
    public void setWatchIdText(String watchid) {
        mWatchidTv.setText(watchid);
    }


    public void showLoading() {
        showProgress();
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

}
