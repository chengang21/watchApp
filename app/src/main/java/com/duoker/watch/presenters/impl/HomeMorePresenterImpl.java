package com.duoker.watch.presenters.impl;

/**
 * Created by cheng on 2017/10/8.
 */

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.duoker.watch.DuokerWatchApp;
import com.duoker.watch.R;
import com.duoker.watch.interactors.base.SimpleCallback;
import com.duoker.watch.interactors.base.SimpleErrorBundle;
import com.duoker.watch.interactors.impl.EditAllowShutdownInteractorImpl;
import com.duoker.watch.interactors.impl.GetMapTypeInteractorImpl;
import com.duoker.watch.interactors.impl.GetProjectCateInteractorImpl;
import com.duoker.watch.interactors.impl.GetWatchSettingInteractorImpl;
import com.duoker.watch.interactors.impl.QueryPhoneBalanceInteractorImpl;
import com.duoker.watch.interactors.impl.SetMonitorModeInteractorImpl;
import com.duoker.watch.interactors.impl.ShutdownDeviceInteractorImpl;
import com.duoker.watch.model.MonitorModeBean;
import com.duoker.watch.model.PhoneBalanceBean;
import com.duoker.watch.model.ProjectCateStorage;
import com.duoker.watch.model.UserSetupInfo;
import com.duoker.watch.db.model.WatchSettingBean;
import com.duoker.watch.presenters.HomeMorePresenter;
import com.duoker.watch.repository.L25Repository;
import com.duoker.watch.repository.MonitorModeRepository;
import com.duoker.watch.repository.ProjectCateRepository;
import com.duoker.watch.repository.UserSetupInfoRepository;
import com.duoker.watch.repository.WatchSettingRepository;
import com.duoker.watch.storage.WatchsStorage;
import com.duoker.watch.utils.AppUtils;

public class HomeMorePresenterImpl extends HomeMorePresenter {
    private static final String TAG = HomeMorePresenterImpl.class.getCanonicalName();
    private final HomeMorePresenter.View iView;
    private final Context mContext;
    private Handler mHandler = new Handler();
    private final L25Repository mL25Repository;
    private final MonitorModeRepository mMonitorModeRepository;
    private final ProjectCateRepository mProjectCateRepository;
    private final UserSetupInfoRepository mUserSetupInfoRepository;
    public WatchSettingBean mWatchSettingBean;
    private final WatchSettingRepository mWatchSettingRepository;

    public HomeMorePresenterImpl(HomeMorePresenter.View paramView, Context paramContext, WatchSettingRepository paramWatchSettingRepository, UserSetupInfoRepository paramUserSetupInfoRepository, MonitorModeRepository paramMonitorModeRepository, L25Repository paramL25Repository, ProjectCateRepository paramProjectCateRepository) {
        this.iView = paramView;
        this.mContext = paramContext.getApplicationContext();
        this.mWatchSettingRepository = paramWatchSettingRepository;
        this.mUserSetupInfoRepository = paramUserSetupInfoRepository;
        this.mMonitorModeRepository = paramMonitorModeRepository;
        this.mL25Repository = paramL25Repository;
        this.mProjectCateRepository = paramProjectCateRepository;
    }

    private void sendForceShutdownSms() {
        WatchsStorage localWatchsStorage = DuokerWatchApp.getInstance().getDefaultWatch();
        if (!AppUtils.checkIsCDMA(localWatchsStorage.getWatchId())) ;
        while (localWatchsStorage.getOnline() == 1)
            return;
        AppUtils.sendSMS(DuokerWatchApp.getInstance().getDefaultWatch().getPhoneIMS(), "POWERDOWN_FOR_LC");
    }

    private void setForbidShutdownText() {
        if (this.mWatchSettingBean != null) {
            if (this.mWatchSettingBean.getAllowshutdown() == 1)
                this.iView.setForbidShutdownText(this.mContext.getString(R.string.home_more_item_forbid_shutdown_open));
        } else
            return;
        this.iView.setForbidShutdownText(this.mContext.getString(R.string.home_more_item_forbid_shutdown_close));
    }

    public void forbidShutdown() {
        if (this.mWatchSettingBean == null)
            return;

         if (this.mWatchSettingBean.getAllowshutdown() == 1) {
             final int iAllowShutdown = 0;
             String userid = DuokerWatchApp.getInstance().getLoginUser().getUserid();
             String watchId = DuokerWatchApp.getInstance().getDefaultWatch().getWatchId();
             this.iView.showProgress();
             new EditAllowShutdownInteractorImpl(userid, watchId, iAllowShutdown, this.mWatchSettingRepository).execute(new SimpleCallback<Object>() {
                 public void onError(SimpleErrorBundle paramAnonymousSimpleErrorBundle) {
                     iView.showToast(R.string.common_all_edit_fail);
                     iView.hideProgress();
                 }

                 public void onSuccess(Object o) {
                     iView.showToast(R.string.common_all_edit_ok);
                     mWatchSettingBean.setAllowshutdown(iAllowShutdown);
                     setForbidShutdownText();
                     iView.hideProgress();
                 }
             });
         }
    }

    public void forceShutdown() {
        sendForceShutdownSms();
        new ShutdownDeviceInteractorImpl(DuokerWatchApp.getInstance().getLoginUser().getUserid(), DuokerWatchApp.getInstance().getDefaultWatch().getWatchId(), this.mWatchSettingRepository).execute(new SimpleCallback<Object>() {
            public void onError(SimpleErrorBundle paramAnonymousSimpleErrorBundle) {
                iView.showToast(R.string.watch_sets_force_shutdown_fail);
            }

            public void onSuccess(Object paramAnonymousObject) {
                iView.showToast(R.string.watch_sets_force_shutdown_ok);
            }
        });
    }

    public void getSets() {
        String str1 = DuokerWatchApp.getInstance().getLoginUser().getUserid();
        String str2 = DuokerWatchApp.getInstance().getDefaultWatch().getWatchId();
        this.iView.showProgress();
        new GetWatchSettingInteractorImpl(str1, str2, this.mWatchSettingRepository).execute(new SimpleCallback<WatchSettingBean>() {
            public void onError(SimpleErrorBundle paramAnonymousSimpleErrorBundle) {
                iView.hideProgress();
            }

            public void onSuccess(WatchSettingBean paramAnonymousWatchSettingBean) {
                mWatchSettingBean = paramAnonymousWatchSettingBean;
                setForbidShutdownText();
                iView.hideProgress();
            }
        });
    }

    public void intentCheckPhoneBill() {
        WatchsStorage localWatchsStorage = DuokerWatchApp.getInstance().getDefaultWatch();
        if (localWatchsStorage == null)
            return;
        String str = localWatchsStorage.getProjectCate();
        if (TextUtils.isEmpty(str)) {
            queryPhoneBalance();
            return;
        }
        new GetProjectCateInteractorImpl(str, this.mProjectCateRepository).execute(new SimpleCallback<ProjectCateStorage>() {
            public void onError(SimpleErrorBundle paramAnonymousSimpleErrorBundle) {
            }

            public void onSuccess(ProjectCateStorage paramAnonymousProjectCateStorage) {
                if (paramAnonymousProjectCateStorage != null) {
                    if (!paramAnonymousProjectCateStorage.isCheckPhoneBill()) {
                        Log.d(HomeMorePresenterImpl.TAG, "该手表不支持手表话费");
                        iView.showNotSupportFunc();
                    }
                } else
                    return;
                queryPhoneBalance();
            }
        });
    }

    public void intentForbidShutdown() {
        if (this.mWatchSettingBean == null)
            return;
        if (this.mWatchSettingBean.getAllowshutdown() == 1) {
            this.iView.showIntentForbidShutdown(true);
            return;
        }
        this.iView.showIntentForbidShutdown(false);
    }

    public void intentGotoBloodOxygen() {
        WatchsStorage localWatchsStorage = DuokerWatchApp.getInstance().getDefaultWatch();
        if (localWatchsStorage == null)
            return;
        String str = localWatchsStorage.getProjectCate();
        if (TextUtils.isEmpty(str)) {
            this.iView.gotoBloodOxygen();
            return;
        }
        new GetProjectCateInteractorImpl(str, this.mProjectCateRepository).execute(new SimpleCallback<ProjectCateStorage>() {
            public void onError(SimpleErrorBundle paramAnonymousSimpleErrorBundle) {
            }

            public void onSuccess(ProjectCateStorage paramAnonymousProjectCateStorage) {
                if (paramAnonymousProjectCateStorage != null) {
                    if (!paramAnonymousProjectCateStorage.isBloodOxygen()) {
                        Log.d(HomeMorePresenterImpl.TAG, "该手表不支持血氧");
                        iView.showNotSupportFunc();
                    }
                } else
                    return;
                iView.gotoBloodOxygen();
            }
        });
    }

    public void intentGotoClassStop() {
        WatchsStorage localWatchsStorage = DuokerWatchApp.getInstance().getDefaultWatch();
        if (localWatchsStorage == null)
            return;
        String str = localWatchsStorage.getProjectCate();
        if (TextUtils.isEmpty(str)) {
            this.iView.gotoClassStop();
            return;
        }
        new GetProjectCateInteractorImpl(str, this.mProjectCateRepository).execute(new SimpleCallback<ProjectCateStorage>() {
            public void onError(SimpleErrorBundle paramAnonymousSimpleErrorBundle) {
            }

            public void onSuccess(ProjectCateStorage paramAnonymousProjectCateStorage) {
                if (paramAnonymousProjectCateStorage != null) {
                    if (!paramAnonymousProjectCateStorage.isClassStop()) {
                        Log.d(HomeMorePresenterImpl.TAG, "该手表不支持上课禁用");
                        iView.showNotSupportFunc();
                    }
                } else
                    return;
                iView.gotoClassStop();
            }
        });
    }

    public void intentGotoFence() {
        WatchsStorage localWatchsStorage = DuokerWatchApp.getInstance().getDefaultWatch();
        if (localWatchsStorage == null)
            return;
        String str = localWatchsStorage.getProjectCate();
        if (TextUtils.isEmpty(str)) {
            this.iView.gotoFence();
            return;
        }
        new GetProjectCateInteractorImpl(str, this.mProjectCateRepository).execute(new SimpleCallback<ProjectCateStorage>() {
            public void onError(SimpleErrorBundle paramAnonymousSimpleErrorBundle) {
            }

            public void onSuccess(ProjectCateStorage paramAnonymousProjectCateStorage) {
                if (paramAnonymousProjectCateStorage != null) {
                    if (!paramAnonymousProjectCateStorage.isFence()) {
                        Log.d(HomeMorePresenterImpl.TAG, "该手表不支持电子围栏");
                        iView.showNotSupportFunc();
                    }
                } else
                    return;
                iView.gotoFence();
            }
        });
    }

    public void intentGotoHeartRate() {
        WatchsStorage localWatchsStorage = DuokerWatchApp.getInstance().getDefaultWatch();
        if (localWatchsStorage == null)
            return;
        String str = localWatchsStorage.getProjectCate();
        if (TextUtils.isEmpty(str)) {
            this.iView.gotoHeartRate();
            return;
        }
        new GetProjectCateInteractorImpl(str, this.mProjectCateRepository).execute(new SimpleCallback<ProjectCateStorage>() {
            public void onError(SimpleErrorBundle paramAnonymousSimpleErrorBundle) {
            }

            public void onSuccess(ProjectCateStorage paramAnonymousProjectCateStorage) {
                if (paramAnonymousProjectCateStorage != null) {
                    if (!paramAnonymousProjectCateStorage.isHeartRate()) {
                        Log.d(HomeMorePresenterImpl.TAG, "该手表不支持心率");
                        iView.showNotSupportFunc();
                    }
                } else
                    return;
                iView.gotoHeartRate();
            }
        });
    }

    public void intentGotoOneDirectionCall() {
        final String str1 = DuokerWatchApp.getInstance().getDefaultWatch().getPhoneIMS();
        if (TextUtils.isEmpty(str1))
            this.iView.showToast(R.string.more_item_phone_call_1);

        WatchsStorage localWatchsStorage1 = DuokerWatchApp.getInstance().getDefaultWatch();
        if (localWatchsStorage1 == null)
            return;

        String str2 = localWatchsStorage1.getProjectCate();
        if (TextUtils.isEmpty(str2)) {
            WatchsStorage localWatchsStorage2 = DuokerWatchApp.getInstance().getDefaultWatch();
            if ((AppUtils.checkIsCDMA(localWatchsStorage2.getWatchId())) && (localWatchsStorage2.getOnline() != 1)) {
                Log.d(TAG, "该手表不支持单向聆听");
                this.iView.showNotSupportFunc();
                return;
            }
            this.iView.showSureOneDirectionCallDialog(str1);
            return;
        }
        new GetProjectCateInteractorImpl(str2, this.mProjectCateRepository).execute(new SimpleCallback<ProjectCateStorage>() {
            public void onError(SimpleErrorBundle paramAnonymousSimpleErrorBundle) {
            }

            public void onSuccess(ProjectCateStorage paramAnonymousProjectCateStorage) {
                if (paramAnonymousProjectCateStorage != null) {
                    if (!paramAnonymousProjectCateStorage.isOneDirectionCall()) {
                        Log.d(HomeMorePresenterImpl.TAG, "该手表不支持单向聆听");
                        iView.showNotSupportFunc();
                    }
                } else
                    return;
                iView.showSureOneDirectionCallDialog(str1);
            }
        });
    }

    public void intentGotoSleep() {
        WatchsStorage localWatchsStorage = DuokerWatchApp.getInstance().getDefaultWatch();
        if (localWatchsStorage == null)
            return;
        String str = localWatchsStorage.getProjectCate();
        if (TextUtils.isEmpty(str)) {
            this.iView.gotoSleep();
            return;
        }
        new GetProjectCateInteractorImpl(str, this.mProjectCateRepository).execute(new SimpleCallback<ProjectCateStorage>() {
            public void onError(SimpleErrorBundle paramAnonymousSimpleErrorBundle) {
            }

            public void onSuccess(ProjectCateStorage paramAnonymousProjectCateStorage) {
                if (paramAnonymousProjectCateStorage != null) {
                    if (!paramAnonymousProjectCateStorage.isSleep()) {
                        Log.d(HomeMorePresenterImpl.TAG, "该手表不支持睡眠");
                        iView.showNotSupportFunc();
                    }
                } else
                    return;
                iView.gotoSleep();
            }
        });
    }

    public void intentGotoStepCounter() {
        WatchsStorage localWatchsStorage = DuokerWatchApp.getInstance().getDefaultWatch();
        if (localWatchsStorage == null)
            return;
        String str = localWatchsStorage.getProjectCate();
        if (TextUtils.isEmpty(str)) {
            this.iView.gotoStepCounter();
            return;
        }
        new GetProjectCateInteractorImpl(str, this.mProjectCateRepository).execute(new SimpleCallback<ProjectCateStorage>() {
            public void onError(SimpleErrorBundle paramAnonymousSimpleErrorBundle) {
            }

            public void onSuccess(ProjectCateStorage paramAnonymousProjectCateStorage) {
                if (paramAnonymousProjectCateStorage != null) {
                    if (!paramAnonymousProjectCateStorage.isStepCounter()) {
                        Log.d(HomeMorePresenterImpl.TAG, "该手表不支持运动计步");
                        iView.showNotSupportFunc();
                    }
                } else
                    return;
                iView.gotoStepCounter();
            }
        });
    }

    public void intentGotoTrackMap() {
        new GetMapTypeInteractorImpl(DuokerWatchApp.getInstance().getLoginUser().getLoginUserName(), this.mUserSetupInfoRepository).execute(new SimpleCallback<UserSetupInfo.MapTypeEnum>() {
            public void onError(SimpleErrorBundle paramAnonymousSimpleErrorBundle) {
            }

            public void onSuccess(UserSetupInfo.MapTypeEnum mapType) {
                switch (mapType) {
                    case GOOGLE:
                        iView.gotoTrackMapGoogle();
                        return;
                    case AMAP:
                    case BAIDU:
                        iView.gotoTrackMapAMap();
                        return;
                }
            }
        });
    }

    public void intentGotoWifiSetting() {
        WatchsStorage localWatchsStorage1 = DuokerWatchApp.getInstance().getDefaultWatch();
        if (localWatchsStorage1 == null)
            return;
        String str = localWatchsStorage1.getProjectCate();
        if (TextUtils.isEmpty(str)) {
            WatchsStorage localWatchsStorage2 = DuokerWatchApp.getInstance().getDefaultWatch();
            if ((AppUtils.checkIsCDMA(localWatchsStorage2.getWatchId())) && (localWatchsStorage2.getOnline() != 1)) {
                Log.d(TAG, "该手表不支持WIFI设置");
                this.iView.showNotSupportFunc();
                return;
            }
            this.iView.gotoWifiSetting();
            return;
        }
        new GetProjectCateInteractorImpl(str, this.mProjectCateRepository).execute(new SimpleCallback<ProjectCateStorage>() {
            public void onError(SimpleErrorBundle paramAnonymousSimpleErrorBundle) {
            }

            public void onSuccess(ProjectCateStorage paramAnonymousProjectCateStorage) {
                if (paramAnonymousProjectCateStorage != null) {
                    if (!paramAnonymousProjectCateStorage.isWifiSetting()) {
                        Log.d(HomeMorePresenterImpl.TAG, "该手表不支持WIFI设置");
                        iView.showNotSupportFunc();
                    }
                } else
                    return;
                iView.gotoWifiSetting();
            }
        });
    }

    public boolean isAdmin() {
        return TextUtils.equals(DuokerWatchApp.getInstance().getDefaultWatch().getOwner(), "1");
    }

    public void onCreate(Bundle paramBundle) {
        if (DuokerWatchApp.getInstance().getDefaultWatch() == null) {
            this.iView.showNoWatch(true);
            return;
        }
        this.iView.showNoWatch(false);
        this.mHandler.post(new Runnable() {
            public void run() {
                getSets();
            }
        });
    }

    public void queryPhoneBalance() {
        String str1 = DuokerWatchApp.getInstance().getLoginUser().getUserid();
        String str2 = DuokerWatchApp.getInstance().getDefaultWatch().getWatchId();
        this.iView.showProgress();
        new QueryPhoneBalanceInteractorImpl(str1, str2, this.mL25Repository).execute(new SimpleCallback<PhoneBalanceBean>() {
            public void onError(SimpleErrorBundle paramAnonymousSimpleErrorBundle) {
                iView.hideProgress();
                iView.showToast(R.string.tel_fare_query_tip_2);
            }

            public void onSuccess(PhoneBalanceBean paramAnonymousPhoneBalanceBean) {
                iView.hideProgress();
                iView.showTelFareDialog(mContext.getString(R.string.tel_fare_query_tip));
            }
        });
    }

    public void sureOneDirectionCall(final String txt) {
        String str1 = DuokerWatchApp.getInstance().getLoginUser().getUserid();
        String str2 = DuokerWatchApp.getInstance().getDefaultWatch().getWatchId();
        this.iView.showProgress();
        new SetMonitorModeInteractorImpl(str1, str2, this.mMonitorModeRepository).execute(new SimpleCallback<MonitorModeBean>() {
            public void onError(SimpleErrorBundle paramAnonymousSimpleErrorBundle) {
                iView.hideProgress();
                iView.showToast(R.string.home_more_item_monitor_request_fail);
            }

            public void onSuccess(MonitorModeBean paramAnonymousMonitorModeBean) {
                iView.hideProgress();
                String str = paramAnonymousMonitorModeBean.getResult();
                if (TextUtils.isEmpty(str))
                    iView.showToast(R.string.home_more_item_offline_to_call);
                if (!str.contains("offline"))
                    return;

                if (str.contains("ready")) {
                    iView.call(txt);
                    return;
                }


                iView.showToast(R.string.home_more_item_offline_to_call);
            }
        });
    }
}