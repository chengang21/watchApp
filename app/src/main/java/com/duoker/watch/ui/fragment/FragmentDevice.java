package com.duoker.watch.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.duoker.watch.R;
import com.duoker.watch.ui.activity.NotificationActivity;
import com.duoker.watch.ui.activity.SettingActivity;
import com.duoker.watch.ui.activity.WatchAddActivity;
import com.duoker.watch.ui.activity.WatchInfoActivity;
import com.duoker.watch.ui.activity.WatchListActivity;
import com.duoker.watch.ui.adapter.DeviceItemFragmentAdapter;
import com.duoker.watch.ui.view.CircleImageView;
import com.viewpagerindicator.CirclePageIndicator;

public class FragmentDevice extends Fragment {
    private Bundle savedInstanceState;
    private Activity activity = null;
    private View view;

    private boolean isFirstSelectPage = true;
    private Handler mHandler = new Handler();
    private ImageView mHomeChatDot;
    private RelativeLayout mHomeChatLayout;
    private RelativeLayout mHomeLeftBtnLayout;
    private CircleImageView mHomeLeftBtnView;
    private ImageView mHomeMessageDot;
    private RelativeLayout mHomeMessageLayout;
    private ImageView mHomePhoneCallDot;
    private RelativeLayout mHomePhoneCallLayout;
    private RelativeLayout mHomeRightBtnLayout;
    private ImageView mHomeRightBtnView;
    private CirclePageIndicator mIndicator;
    public DeviceItemFragmentAdapter mMenuDeviceItemFragmentAdapter;
    // private MenuDevicePresenterImpl mMenuDevicePresenter;
    private View mNoWatchLayout;
    private View mStepCountBarJustShow;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RelativeLayout mToolbarLayout;
    private ImageView mToolbarMenuAdd;
    private ImageView mToolbarMenuSetting;
    private TextView mToolbarTitleTv;
    private ViewPager mTopVp;

    public static FragmentDevice newInstance() {
        return new FragmentDevice();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragement_device, container, false);

        activity = this.getActivity();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // EventBus.getDefault().register(this);
        // this.mMenuDevicePresenter = new MenuDevicePresenterImpl(this.iView, new WatchRepositoryImpl(getActivity()), new AudioGroupAndMemberRepositoryImpl(), new ChatMessageRepositoryImpl(getActivity()), new NotificationMessageRepositoryImpl(getActivity()), new ProjectCateRepositoryImpl(getActivity()));
        this.mToolbarTitleTv = ((TextView) view.findViewById(R.id.toolbar_title_tv));
        this.mToolbarMenuSetting = ((ImageView) view.findViewById(R.id.toolbar_menu_setting));
        this.mToolbarMenuAdd = ((ImageView) view.findViewById(R.id.toolbar_menu_add));
        this.mTopVp = ((ViewPager) view.findViewById(R.id.top_vp));
        this.mIndicator = ((CirclePageIndicator) view.findViewById(R.id.indicator));
        this.mStepCountBarJustShow = view.findViewById(R.id.step_count_bar_just_show);
        this.mHomePhoneCallLayout = ((RelativeLayout) view.findViewById(R.id.home_phone_call_layout));
        this.mHomePhoneCallDot = ((ImageView) view.findViewById(R.id.home_phone_call_dot));
        this.mHomeMessageLayout = ((RelativeLayout) view.findViewById(R.id.home_message_layout));
        this.mHomeMessageDot = ((ImageView) view.findViewById(R.id.home_message_dot));
        this.mHomeChatLayout = ((RelativeLayout) view.findViewById(R.id.home_chat_layout));
        this.mHomeChatDot = ((ImageView) view.findViewById(R.id.home_chat_dot));
        this.mHomeLeftBtnLayout = ((RelativeLayout) view.findViewById(R.id.home_left_btn_layout));
        this.mHomeLeftBtnView = ((CircleImageView) view.findViewById(R.id.home_left_btn_view));
        this.mHomeRightBtnLayout = ((RelativeLayout) view.findViewById(R.id.home_right_btn_layout));
        this.mHomeRightBtnView = ((ImageView) view.findViewById(R.id.home_right_btn_view));
        this.mNoWatchLayout = view.findViewById(R.id.no_watch_layout);
        this.mSwipeRefreshLayout = ((SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout));
        //CG this.mSwipeRefreshLayout.setColorSchemeResources(new int[]{R.color.});
        initListener();

        // this.mMenuDevicePresenter.onCreate(paramBundle);
    }

    private void initListener() {
        this.mToolbarMenuSetting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FragmentDevice.this.getActivity(), SettingActivity.class);
                FragmentDevice.this.startActivity(intent);
            }
        });
        this.mToolbarMenuAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FragmentDevice.this.getActivity(), WatchAddActivity.class);
                FragmentDevice.this.startActivity(intent);
            }
        });
        this.mHomeLeftBtnLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // WatchsStorage localWatchsStorage = DuokerWatchApp.getInstance().getDefaultWatch();
                Intent intent = new Intent(FragmentDevice.this.getContext(), WatchInfoActivity.class);
                // intent.putExtra(WatchInfoActivity.TAG, localWatchsStorage);
                FragmentDevice.this.startActivity(intent);
            }
        });
        this.mHomeRightBtnLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FragmentDevice.this.getActivity(), WatchListActivity.class);
                FragmentDevice.this.startActivity(intent);
            }
        });
        this.mTopVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int paramInt) {
//                final WatchsStorage localWatchsStorage = FragmentDevice.this.mMenuDeviceItemFragmentAdapter.getWatches()[paramInt];
//                if (FragmentDevice.access$000(FragmentDevice.this)) {
//                    FragmentDevice.access$002(FragmentDevice.this, false);
//                    if ((paramInt != 0) && (paramInt != 1)) {
//                        FragmentDevice.access$200(FragmentDevice.this).postDelayed(new Runnable() {
//                                                                                           public void run() {
//                                                                                               FragmentDevice.access$100(FragmentDevice .5.
//                                                                                                       FragmentDevice.this, localWatchsStorage);
//                                                                                           }
//                                                                                       }
//                                , 500L);
//                        return;
//                    }
//                }
//                FragmentDevice.access$100(FragmentDevice.this, localWatchsStorage);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        this.mHomePhoneCallLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                if (!FragmentDevice.access$300(FragmentDevice.this))
//                    return;
//                final String str = App.getInstance().getDefaultWatch().getPhoneIMS();
//                if (TextUtils.isEmpty(str)) {
//                    FragmentDevice.this.showShortToast(2131165575);
//                    return;
//                }
//                new AlertDialog(FragmentDevice.this.getActivity()).setTitle(FragmentDevice.this.getString(2131165576)).setMsg(str).setCancelable(true).setNegativeButton(FragmentDevice.this.getString(2131165371), null).setPositiveButton(FragmentDevice.this.getString(2131165370), new View.OnClickListener() {
//                    public void onClick(View paramAnonymousView) {
//                        Intent intent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + str));
//                        FragmentDevice .6. FragmentDevice.this.startActivity(intent);
//                    }
//                }).show();
            }
        });
        this.mHomeMessageLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FragmentDevice.this.getActivity(), NotificationActivity.class);
                FragmentDevice.this.startActivity(intent);
            }
        });
        this.mHomeChatLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                FragmentDevice.aaa(FragmentDevice.this).gotoChatMessages();
            }
        });
        this.mNoWatchLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FragmentDevice.this.getActivity(), WatchAddActivity.class);
                FragmentDevice.this.startActivity(intent);
            }
        });

        this.mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
//                FragmentDevice.access$400(FragmentDevice.this).regularRequestWatches();
//                FragmentDevice.access$200(FragmentDevice.this).postDelayed(new Runnable() {
//                                                                           public void run() {
//                                                                               FragmentDevice.access$500(FragmentDevice .10. FragmentDevice.this).
//                                                                               setRefreshing(false);
//                                                                           }
//                                                                       }
//                        , 1500L);
            }
        });
    }

}

