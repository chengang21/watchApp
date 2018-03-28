package com.duoker.watch.ui.activity;

/**
 * Created by chengang on 4/28/2017.
 */

import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duoker.watch.R;
import com.duoker.watch.db.model.WifiHotSpotModel;
import com.duoker.watch.presenters.WatchSetsWifiPresenter;
import com.duoker.watch.presenters.impl.WatchSetsWifiPresenterImpl;
import com.duoker.watch.repository.WatchSettingRepositoryImpl;
import com.duoker.watch.ui.base.BaseActivity;
import com.duoker.watch.ui.component.WifiChooseComp;
import com.duoker.watch.ui.view.SwitchButton;
import com.duoker.watch.utils.WifiAdmin;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// import com.kyleduo.switchbutton.SwitchButton;

public class WatchSetsWifiActivity extends BaseActivity implements WatchSetsWifiPresenter.View {
    private static final String TAG = WatchSetsWifiActivity.class.getSimpleName();

    private SwitchButton mEnableTb;
    private EditText mPwdView;
    private LinearLayout mSsidLayout;
    private TextView mSsidView;
    private Button mSureBtn;
    private WatchSetsWifiPresenterImpl mWatchSetsWifiPresenter;


    @Override
    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_watch_sets_wifi);
        this.mWatchSetsWifiPresenter = new WatchSetsWifiPresenterImpl(this, new WatchSettingRepositoryImpl());
        initToolbar();
        initView();
        initListener();
        this.mWatchSetsWifiPresenter.onCreate(paramBundle);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void checkEnable(boolean paramAnonymousBoolean) {
        if (WatchSetsWifiActivity.this.mEnableTb != null)
            WatchSetsWifiActivity.this.mEnableTb.setChecked(paramAnonymousBoolean);
    }

    public void finishActivity() {
        WatchSetsWifiActivity.this.finish();
    }

    public void hideLoading() {
        WatchSetsWifiActivity.this.hideProgress();
    }

    public void inflateListView(List<WifiHotSpotModel> list) {
        WifiChooseComp localWifiChooseComp = new WifiChooseComp(WatchSetsWifiActivity.this);
        localWifiChooseComp.addAll(list);
        ViewHolder localViewHolder = new ViewHolder(localWifiChooseComp.getView());
        final DialogPlus localDialogPlus = DialogPlus.newDialog(WatchSetsWifiActivity.this).setContentHolder(localViewHolder).setHeader(null).setCancelable(true).setGravity(80).setOnDismissListener(null).setExpanded(false).setContentHeight(-2).setOnCancelListener(null).setContentBackgroundResource(17170443).setOnClickListener(null).create();
        localDialogPlus.show();
        localWifiChooseComp.setOnItemClickListener(new WifiChooseComp.OnItemClickListener() {
            public void onItemClick(int paramAnonymous2Int, WifiHotSpotModel paramAnonymous2WifiHotSpotModel) {
                String str = paramAnonymous2WifiHotSpotModel.getWifiName();
                if (WatchSetsWifiActivity.this.mSsidView != null)
                    WatchSetsWifiActivity.this.mSsidView.setText(str);
                localDialogPlus.dismiss();
            }
        });
    }

    public void scanLocalWifi() {
        WifiAdmin localWifiAdmin = new WifiAdmin(WatchSetsWifiActivity.this.getApplicationContext());
        localWifiAdmin.startScan();
        List localList = localWifiAdmin.getWifiList();
        ArrayList localArrayList = new ArrayList();
        if (localList != null) {
            Iterator localIterator = localList.iterator();
            while (localIterator.hasNext()) {
                ScanResult localScanResult = (ScanResult) localIterator.next();
                if (!TextUtils.isEmpty(localScanResult.SSID)) {
                    WifiHotSpotModel localWifiHotSpotModel = new WifiHotSpotModel();
                    localWifiHotSpotModel.setWifiName(localScanResult.SSID);
                    localWifiHotSpotModel.setWifiMac(localScanResult.BSSID);
                    localWifiHotSpotModel.setWifiRssi(Integer.toString(localScanResult.level));
                    localArrayList.add(localWifiHotSpotModel);
                }
            }
        }
        WatchSetsWifiActivity.this.mWatchSetsWifiPresenter.mergeWifi(localArrayList);
    }

    public void setPassText(String paramAnonymousString) {
        if (WatchSetsWifiActivity.this.mPwdView != null)
            WatchSetsWifiActivity.this.mPwdView.setText(paramAnonymousString);
    }

    public void setSsidText(String paramAnonymousString) {
        if (WatchSetsWifiActivity.this.mSsidView != null)
            WatchSetsWifiActivity.this.mSsidView.setText(paramAnonymousString);
    }

    public void showLoading() {
        WatchSetsWifiActivity.this.showProgress();
    }

    @Override
    public void showError(String message) {

    }

    public void showToast(@StringRes int resourceID) {
        WatchSetsWifiActivity.this.showShortToast(resourceID);
    }

    public void showToast(CharSequence charSequence) {
        WatchSetsWifiActivity.this.showShortToast(charSequence);
    }

    private void initListener() {
        this.mEnableTb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                boolean bool = WatchSetsWifiActivity.this.mEnableTb.isChecked();
                String str1 = WatchSetsWifiActivity.this.mSsidView.getText().toString();
                String str2 = WatchSetsWifiActivity.this.mPwdView.getText().toString();
                WatchSetsWifiActivity.this.mWatchSetsWifiPresenter.enable(bool, str1, str2);
            }
        });
        this.mSsidLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                WatchSetsWifiActivity.this.mWatchSetsWifiPresenter.scanWifi();
            }
        });
        this.mSureBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                if (!WatchSetsWifiActivity.this.mWatchSetsWifiPresenter.isEnable())
                    return;
                String str1 = WatchSetsWifiActivity.this.mSsidView.getText().toString();
                String str2 = WatchSetsWifiActivity.this.mPwdView.getText().toString();
                WatchSetsWifiActivity.this.mWatchSetsWifiPresenter.setWifi(str1, str2);
            }
        });
    }

    private void initToolbar() {
        ((ImageView) findViewById(R.id.toolbar_menu_back)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                WatchSetsWifiActivity.this.finish();
            }
        });
    }

    private void initView() {
        this.mSsidLayout = ((LinearLayout) findViewById(R.id.ssid_layout));
        this.mSsidView = ((TextView) findViewById(R.id.ssid_view));
        this.mPwdView = ((EditText) findViewById(R.id.pwd_view));
        this.mEnableTb = ((SwitchButton) findViewById( R.id.enable_tb));
        this.mSureBtn = ((Button) findViewById(R.id.sure_btn));
    }

}
