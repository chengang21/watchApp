package com.duoker.watch.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.duoker.watch.DuokerWatchApp;
import com.duoker.watch.R;
import com.duoker.watch.ui.activity.ReminderActivity;
import com.duoker.watch.ui.activity.SetWearerInfoActivity;
import com.duoker.watch.ui.activity.WatchAddActivity;
import com.duoker.watch.ui.activity.WatchBindingActivity;
import com.duoker.watch.ui.activity.WatchConfigActivity;

public class FragmentConfig extends Fragment {

    private Bundle savedInstanceState;
    private Activity activity = null;
    private View view;

    private View mNoWatchLayout;
    private RelativeLayout mUnbindLayout;
    private RelativeLayout mWatchSettingLayout;
    private RelativeLayout mWearerSettingLayout;
    private RelativeLayout mReminderLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragement_config, container, false);

        activity = this.getActivity();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mWatchSettingLayout = (RelativeLayout) activity.findViewById(R.id.config_watch_sets_layout);
        mUnbindLayout = (RelativeLayout) activity.findViewById(R.id.config_unbind_layout);
        mWearerSettingLayout = (RelativeLayout) activity.findViewById(R.id.watch_wearer_layout);
        mReminderLayout = (RelativeLayout) activity.findViewById(R.id.watch_reminder_layout);

        mNoWatchLayout = activity.findViewById(R.id.no_watch_layout_more);
        initListener();
    }

    private void initListener() {
        this.mNoWatchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getActivity(), WatchAddActivity.class);
                startActivity(intent);
            }
        });

        this.mWatchSettingLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WatchConfigActivity.class);
                startActivity(intent);
            }
        });

        this.mUnbindLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WatchBindingActivity.class);
                startActivity(intent);
            }
        });

        this.mWearerSettingLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SetWearerInfoActivity.class);
                startActivity(intent);
            }
        });

        this.mReminderLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ReminderActivity.class);
                startActivity(intent);
            }
        });
    }

    //   @Subscribe
    public void onEvent() // FlushHomeOtherFragmentDataEvent event)
    {
        // if (this.mHomeMorePresenter != null)
        {
            if (DuokerWatchApp.getInstance().getDefaultWatch() == null)
                showNoWatch(true);
            else
                showNoWatch(false);
        }
    }


    public void call(String paramString) {
        Intent localIntent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + paramString));
        FragmentConfig.this.activity.startActivity(localIntent);
    }


    public void hideLoading() {
        // hideProgress();
    }
     public void showLoading() {
        // showProgress();
    }

    public void showNoWatch(boolean visible) {
        if ((getActivity() == null) || getActivity().isFinishing() || !isAdded() || mNoWatchLayout == null)
            return;

        mNoWatchLayout.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    public void showToast(@StringRes int paramInt) {
        // showShortToast(paramInt);
    }

    public void showToast(CharSequence paramCharSequence) {
        //showShortToast(paramCharSequence);
    }

}

