package com.duoker.watch.ui.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.duoker.watch.R;
import com.duoker.watch.ui.fragment.FragmentSetting;

public class SettingActivity extends FragmentActivity  implements FragmentSetting.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initToolbar();
        initFragment();
    }

    private void initFragment()
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add( R.id.container_layout , FragmentSetting.newInstance("", ""), FragmentSetting.TAG);
        transaction.commit();
    }

    private void initToolbar()
    {
        findViewById( R.id.toolbar_menu_back ).setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                finish();
            }
        });
//
//        final View localView = findViewById(2131558681);
//        localView.setOnClickListener(new View.OnClickListener()
//        {
//            public void onClick(View paramAnonymousView)
//            {
//                new AlertDialog(SetupActivity.this).setTitle(SetupActivity.this.getString(2131165369)).setMsg(SetupActivity.this.getString(2131165665)).setCancelable(true).setNegativeButton(SetupActivity.this.getString(2131165371), null).setPositiveButton(SetupActivity.this.getString(2131165370), new SetupActivity.2.1(this)).show();
//            }
//        });
//
//        if (SDKUtils.isNeedHide(this))
//            localView.setVisibility(8);
//        final MultipleClickUtils localMultipleClickUtils = new MultipleClickUtils(6, new MultipleClickUtils.OnMultipleClickListener()
//        {
//            public void onClickFinish()
//            {
//                localView.performClick();
//            }
//
//            public void onClickPosition(int paramAnonymousInt1, int paramAnonymousInt2)
//            {
//                int i = paramAnonymousInt2 - paramAnonymousInt1;
//                if (i <= 2)
//                    SetupActivity.this.showShortToast("还需要点击--" + i);
//            }
//        });
//        findViewById(2131558556).setOnClickListener(new View.OnClickListener()
//        {
//            public void onClick(View paramAnonymousView)
//            {
//                localMultipleClickUtils.multipleClick();
//            }
//        });
    }

    private void logout()
    {
//        String str = DuokerWatchApp.getInstance().getLoginUser().getUserid();
//        showProgress();
//        new LogoutInteractorImpl(str, new LoginRepositoryImpl(getApplicationContext())).execute(new DefaultCallback()
//        {
//            public void onError(DefaultErrorBundle paramAnonymousDefaultErrorBundle)
//            {
//                SetupActivity.this.hideProgress();
//            }
//
//            public void onSuccess(Object paramAnonymousObject)
//            {
//                SetupActivity.this.hideProgress();
//                Intent localIntent = new Intent(SetupActivity.this, LoginActivity.class);
//                localIntent.setFlags(32768);
//                SetupActivity.this.startActivity(localIntent);
//                LongCosPushInterface.stopPush();
//            }
//        });
    }

    protected void onDestroy()
    {
        super.onDestroy();
    }


    @Override
    public void onFragmentInteraction(Uri uri) {
        return;
    }
}
