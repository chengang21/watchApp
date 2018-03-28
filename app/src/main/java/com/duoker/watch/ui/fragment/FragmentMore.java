package com.duoker.watch.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.duoker.watch.DuokerWatchApp;
import com.duoker.watch.R;
import com.duoker.watch.presenters.HomeMorePresenter;
import com.duoker.watch.presenters.impl.HomeMorePresenterImpl;
import com.duoker.watch.qrcode.CaptureActivity;
import com.duoker.watch.repository.L25RepositoryImpl;
import com.duoker.watch.repository.MonitorModeRepositoryImpl;
import com.duoker.watch.repository.ProjectCateRepositoryImpl;
import com.duoker.watch.repository.UserSetupInfoRepositoryImpl;
import com.duoker.watch.repository.WatchSettingRepositoryImpl;
import com.duoker.watch.ui.activity.BloodOxygenActivity;
import com.duoker.watch.ui.activity.ClassStopActivity;
import com.duoker.watch.ui.activity.ContactActivity;
import com.duoker.watch.ui.activity.HeartRateNewActivity;
import com.duoker.watch.ui.activity.MapTrackAMapActivity;
import com.duoker.watch.ui.activity.SafeAreaNetActivity;
import com.duoker.watch.ui.activity.SleepActivity;
import com.duoker.watch.ui.activity.SportStepActivity;
import com.duoker.watch.ui.activity.WatchAddActivity;
import com.duoker.watch.ui.activity.WatchBindingActivity;
import com.duoker.watch.ui.activity.WatchSetsWifiActivity;
import com.duoker.watch.ui.activity.WatchSettingActivity;
import com.duoker.watch.ui.base.BaseFragment;
import com.duoker.watch.ui.event.FlushHomeOtherFragmentDataEvent;
import com.duoker.watch.ui.iosdialog.AlertDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class FragmentMore extends BaseFragment implements HomeMorePresenter.View {

    private Bundle savedInstanceState;
    private Activity activity = null;
    private View view;

    private RelativeLayout mBloodOxygenLayout;
    private LinearLayout mClassStopLayout;
    private RelativeLayout mContactsLayout;
    private LinearLayout mFenceLayout;
    private RelativeLayout mForbidShutdownLayout;
    private TextView mForbidShutdownTv;
    private RelativeLayout mForceShutdownLayout;
    private RelativeLayout mHeartRateLayout;
    private HomeMorePresenterImpl mHomeMorePresenter;
    private LinearLayout mListenLayout;
    private LinearLayout mMapTrackLayout;
    private View mNoWatchLayout;
    private RelativeLayout mSleepLayout;
    private LinearLayout mStepLayout;
    private LinearLayout mTelFareLayout;
    private RelativeLayout mUnbindLayout;
    private RelativeLayout mWatchSettingLayout;
    private RelativeLayout mWatchSetsLayout;
    private RelativeLayout mWifiLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragement_more, container, false);

        activity = this.getActivity();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EventBus.getDefault().register(this);

        mStepLayout = (LinearLayout) activity.findViewById(R.id.step_layout);
        mContactsLayout = (RelativeLayout) activity.findViewById(R.id.contacts_layout);
        mWatchSettingLayout = (RelativeLayout) activity.findViewById(R.id.watch_sets_layout);
        mUnbindLayout = (RelativeLayout) activity.findViewById(R.id.unbind_layout);
        mHeartRateLayout = (RelativeLayout) activity.findViewById(R.id.heart_rate_layout);
        mSleepLayout = (RelativeLayout) activity.findViewById(R.id.sleep_layout);
        mBloodOxygenLayout = (RelativeLayout) activity.findViewById(R.id.blood_oxygen_layout);
        mWifiLayout = (RelativeLayout) activity.findViewById(R.id.wifi_layout);
        mForbidShutdownLayout = (RelativeLayout) activity.findViewById(R.id.forbid_shutdown_layout);
        mForceShutdownLayout = (RelativeLayout) activity.findViewById(R.id.force_shutdown_layout);
        mNoWatchLayout = activity.findViewById(R.id.no_watch_layout_more);
        mFenceLayout = ((LinearLayout) activity.findViewById(R.id.fence_layout));
        mTelFareLayout = ((LinearLayout) activity.findViewById(R.id.tel_fare_layout));
        mMapTrackLayout = ((LinearLayout) activity.findViewById(R.id.map_track_layout));
        mClassStopLayout = ((LinearLayout) activity.findViewById(R.id.class_stop_layout));
        mListenLayout = ((LinearLayout) activity.findViewById(R.id.listen_layout));
        mWatchSetsLayout = ((RelativeLayout) activity.findViewById(R.id.watch_sets_layout));
        mForbidShutdownTv = ((TextView) activity.findViewById(R.id.forbid_shutdown_tv));

        this.mHomeMorePresenter = new HomeMorePresenterImpl(this, getActivity(),
                new WatchSettingRepositoryImpl(),
                new UserSetupInfoRepositoryImpl(getActivity()),
                new MonitorModeRepositoryImpl(),
                new L25RepositoryImpl(getActivity()),
                new ProjectCateRepositoryImpl(getActivity()));

        initListener();

        this.mHomeMorePresenter.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    private void initListener() {
        this.mNoWatchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WatchAddActivity.class);
                startActivity(intent);
            }
        });

        this.mStepLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mHomeMorePresenter.intentGotoStepCounter();
            }
        });
        this.mFenceLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mHomeMorePresenter.intentGotoFence();
            }
        });
        this.mTelFareLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mHomeMorePresenter.intentCheckPhoneBill();
            }
        });
        this.mMapTrackLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mHomeMorePresenter.intentGotoTrackMap();
            }
        });
        this.mClassStopLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mHomeMorePresenter.intentGotoClassStop();
            }
        });
        this.mListenLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mHomeMorePresenter.intentGotoOneDirectionCall();
            }
        });
        this.mContactsLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ContactActivity.class);
                startActivity(intent);
            }
        });

        this.mWatchSettingLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WatchSettingActivity.class);
                startActivity(intent);
            }
        });

        this.mUnbindLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WatchBindingActivity.class);
                startActivity(intent);
            }
        });

        this.mHeartRateLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mHomeMorePresenter.intentGotoHeartRate();
                //CG Intent intent = new Intent(getActivity(), HeartrateActivity.class);
                //startActivity(intent);
            }
        });
        this.mSleepLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mHomeMorePresenter.intentGotoSleep();
            }
        });
        this.mBloodOxygenLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mHomeMorePresenter.intentGotoBloodOxygen();
            }
        });
        this.mWifiLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mHomeMorePresenter.intentGotoWifiSetting();
            }
        });
        this.mForbidShutdownLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mHomeMorePresenter.intentForbidShutdown();
            }
        });
        this.mForceShutdownLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /* if (!isAdmin())
                {
                    new AlertDialog( getActivity()).setTitle( getString(R.string.common_all_tip_1)).
                            setMsg( getString(R.string.home_more_item_no_authority)).
                            setCancelable(true).setPositiveButton( getString(R.string.common_all_tip_2), null).show();
                    return;
                } */
                new AlertDialog(getActivity()).setTitle(getString(R.string.common_all_tip_1)).
                        setMsg(getString(R.string.home_more_item_force_shutdown_close_msg)).
                        setCancelable(true).
                        setNegativeButton(getString(R.string.common_all_tip_3), null).
                        setPositiveButton(getString(R.string.common_all_tip_2),
                                new View.OnClickListener() {
                                    public void onClick(View v) {
                                        mHomeMorePresenter.forceShutdown();
                                    }
                                }).show();
            }
        });
    }


    @Subscribe
    public void onEvent(FlushHomeOtherFragmentDataEvent event) {
        if (this.mHomeMorePresenter == null)
            return;

        if (DuokerWatchApp.getInstance().getDefaultWatch() == null)
            showNoWatch(true);

        showNoWatch(false);
    }


    private void startScanQRcode() {
        //点击按钮跳转到二维码扫描界面，这里用的是startActivityForResult跳转
        //扫描完了之后调到该界面
//        Button mButton = (Button) findViewById(R.id.button1);
//        mButton.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(MainActivity.this, CaptureActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
//            }
//        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case CaptureActivity.SCANNIN_GREQUEST_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    // 显示扫描到的内容
                    // mTextView.setText(bundle.getString("result"));
                    // 显示
                    Toast.makeText(getActivity().getApplicationContext(), bundle.getString("result"),
                            Toast.LENGTH_LONG).show();
                    // mImageView.setImageBitmap((Bitmap) data.getParcelableExtra("bitmap"));
                }
                break;
        }
    }


    //============================================================================================== 
    // view
    @Override
    public void call(String phoneNumber) {
        Intent intent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }

    @Override
    public void gotoBloodOxygen() {
        Intent intent = new Intent(getActivity(), BloodOxygenActivity.class);
        startActivity(intent);
    }

    @Override
    public void gotoClassStop() {
        Intent intent = new Intent(getActivity(), ClassStopActivity.class);
        startActivity(intent);
    }

    @Override
    public void gotoFence() {
        Intent intent = new Intent(getActivity(), SafeAreaNetActivity.class);
        startActivity(intent);
    }

    @Override
    public void gotoHeartRate() {
        Intent intent = new Intent(getActivity(), HeartRateNewActivity.class);
        startActivity(intent);
    }

    @Override
    public void gotoSleep() {
        Intent intent = new Intent(getActivity(), SleepActivity.class);
        startActivity(intent);
    }

    @Override
    public void gotoStepCounter() {
        Intent intent = new Intent(getActivity(), SportStepActivity.class);
        startActivity(intent);
    }

    @Override
    public void gotoTrackMapAMap() {
        Intent intent = new Intent(getActivity(), MapTrackAMapActivity.class);
        startActivity(intent);
    }

    @Override
    public void gotoTrackMapGoogle() {
        // Intent intent = new Intent(getActivity(), MapTrackGoogleActivity.class);
        // startActivity(intent);
    }

    @Override
    public void gotoWifiSetting() {
        Intent intent = new Intent(getActivity(), WatchSetsWifiActivity.class);
        startActivity(intent);
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void setForbidShutdownText(String text) {
        if ((getActivity() == null) || (getActivity().isFinishing()) || (!isAdded()))
            return;

        mForbidShutdownTv.setText(text);
    }

    @Override
    public void showIntentForbidShutdown(boolean paramBoolean) {
        if ((getActivity() == null) || (getActivity().isFinishing()) || (!isAdded()))
            return;
        if (paramBoolean) ;
        for (String str = getString(R.string.home_more_item_forbid_shutdown_close_msg); ; str = getString(R.string.home_more_item_forbid_shutdown_open_msg)) {
            new AlertDialog(getActivity()).
                    setTitle(getString(R.string.common_all_tip_1)).
                    setMsg(str).
                    setCancelable(true).
                    setNegativeButton(getString(R.string.common_all_tip_3), null).
                    setPositiveButton(getString(R.string.common_all_tip_2),
                            new View.OnClickListener() {
                public void onClick(View v) {
                    mHomeMorePresenter.forbidShutdown();
                }
            }).show();
        }
    }

    @Override
    public void showNoWatch(boolean visible) {
        if ((getActivity() == null) || getActivity().isFinishing() || !isAdded() || mNoWatchLayout == null)
            return;

        mNoWatchLayout.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showNotSupportFunc() {
        if ((getActivity() == null) || (getActivity().isFinishing()) || (!isAdded()))
            return;
        new AlertDialog(getActivity()).
                setMsg(getString(R.string.hb_not_support_func)).
                setCancelable(true).setNegativeButton(getString(R.string.hb_not_support_cancel), null).
                setPositiveButton(getString(R.string.hb_not_support_sure),
                        new View.OnClickListener() {
                            public void onClick(View v) {
                                Intent intent = new Intent("android.intent.action.VIEW");
                                intent.setData(Uri.parse(getString(R.string.hb_not_support_goto_website)));
                                startActivity(intent);
                            }
                        }).show();
    }

    @Override
    public void showSureOneDirectionCallDialog(final String txt) {
        if ((getActivity() == null) || (getActivity().isFinishing()) || (!isAdded()))
            return;
        new AlertDialog(getActivity()).
                setTitle(getString(R.string.home_more_item_monitor)).
                setMsg(txt).
                setCancelable(true).
                setNegativeButton(getString(R.string.common_all_tip_3), null).
                setPositiveButton(getString(R.string.common_all_tip_2),
                        new View.OnClickListener() {
                            public void onClick(View v) {
                                mHomeMorePresenter.sureOneDirectionCall(txt);
                            }
                        }).show();
    }

    @Override
    public void showTelFareDialog(String txt) {
        if ((getActivity() == null) || (getActivity().isFinishing()) || (!isAdded()))
            return;
        new AlertDialog(getActivity()).
                setTitle(getString(R.string.common_all_tip_1)).
                setMsg(txt).
                setCancelable(true).
                setPositiveButton(getString(R.string.common_all_tip_2), null).
                show();
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

