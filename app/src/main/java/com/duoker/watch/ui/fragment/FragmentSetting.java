package com.duoker.watch.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.duoker.watch.DuokerWatchApp;
import com.duoker.watch.R;
import com.duoker.watch.ui.activity.AboutActivity;
import com.duoker.watch.ui.activity.HelpActivity;
import com.duoker.watch.ui.activity.UserInfoActivity;
import com.duoker.watch.ui.activity.WatchListActivity;
import com.duoker.watch.ui.view.CircleImageView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentSetting.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentSetting#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSetting extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public static final String TAG = FragmentSetting.class.getSimpleName();
    // private FragmentSettingPresenter.View iView = new FragmentSetting.7(this);
    // private FragmentSettingPresenterImpl mFragmentSettingPresenter;
    private RelativeLayout mAboutLayout;
    private RadioButton mAmapRb;
    private CircleImageView mAvatarIv;
    private RadioButton mBaiduRb;
    private RadioButton mGoogleRb;
    private RelativeLayout mHelpLayout;
    private LinearLayout mManagerWatchLayout;
    private LinearLayout mMapInnerLayout;
    private RadioGroup mMapTypeRg;
    private TextView mNameTv;
    private LinearLayout mShareLayout;
    private TextView mWatchDescTv;

    private OnFragmentInteractionListener mListener;

    public FragmentSetting() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentSetting.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentSetting newInstance(String param1, String param2) {
        FragmentSetting fragment = new FragmentSetting();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Activity activity = getActivity();
        // this.mFragmentSettingPresenter = new FragmentSettingPresenterImpl(this.iView, getActivity(), new UserSetupInfoRepositoryImpl(getActivity()));
        this.mAvatarIv = ((CircleImageView)activity.findViewById( R.id.avatar_iv ));
        this.mNameTv = ((TextView)activity.findViewById( R.id.name_tv ));
        this.mWatchDescTv = ((TextView)activity.findViewById( R.id.watch_desc_tv ));
        this.mShareLayout = ((LinearLayout)activity.findViewById( R.id.share_layout ));
        this.mManagerWatchLayout = ((LinearLayout)activity.findViewById( R.id.manager_watch_layout ));
        this.mMapInnerLayout = ((LinearLayout)activity.findViewById( R.id.map_inner_layout ));
        this.mMapTypeRg = ((RadioGroup)activity.findViewById( R.id.map_type_rg ));
        this.mGoogleRb = ((RadioButton)activity.findViewById(R.id.google_rb));
        this.mBaiduRb = ((RadioButton)activity.findViewById(R.id.baidu_rb));
        this.mAmapRb = ((RadioButton)activity.findViewById(R.id.amap_rb));
        this.mAboutLayout = ((RelativeLayout)activity.findViewById(R.id.about_layout));
        this.mHelpLayout = ((RelativeLayout)activity.findViewById(R.id.help_layout));
 
        setupListener();
        // ShareSDK.initSDK(getActivity());
        // this.mFragmentSettingPresenter.onCreate(paramBundle);
    }

    private void setupListener() {
        this.mAvatarIv.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserInfoActivity.class);
                startActivity(intent);
            }
        });
        this.mShareLayout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
//                ViewHolder localViewHolder = new ViewHolder(2130968764);
//                DialogPlus.newDialog(getActivity()).setContentHolder(localViewHolder).setCancelable(true).setGravity(80).setOnDismissListener(null).setExpanded(false).setContentHeight(-2).setOnCancelListener(null).setContentBackgroundResource(17170443).setOnClickListener(new OnClickListener()
//                {
//                    public void onClick(DialogPlus paramAnonymousDialogPlus, View paramAnonymousView)
//                    {
//                        switch (paramAnonymousView.getId())
//                        {
//                            default:
//                                return;
//                            case 2131558953:
//                                SetupFragment.access$000(SetupFragment.2.this.this$0);
//                                paramAnonymousDialogPlus.dismiss();
//                                return;
//                            case 2131558954:
//                                SetupFragment.access$100(SetupFragment.2.this.this$0);
//                                paramAnonymousDialogPlus.dismiss();
//                                return;
//                            case 2131558955:
//                                SetupFragment.access$200(SetupFragment.2.this.this$0);
//                                paramAnonymousDialogPlus.dismiss();
//                                return;
//                            case 2131558956:
//                        }
//                        SetupFragment.access$300(SetupFragment.2.this.this$0);
//                        paramAnonymousDialogPlus.dismiss();
//                    }
//                }).create().show();
            }
        });
        this.mManagerWatchLayout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                List localList = DuokerWatchApp.getInstance().getWatchesList();
                if ((localList == null) || (localList.isEmpty()))
                {
                    // new AlertDialog(getActivity()).setTitle(getString(2131165369)).setMsg(getString(2131165672)).setCancelable(true).setPositiveButton(getString(2131165370), null).show();
                    return;
                }
                Intent intent = new Intent(getActivity(), WatchListActivity.class);
                startActivity(intent);
            }

        });
        this.mMapTypeRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

            }
        });
        this.mAboutLayout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AboutActivity.class);
                startActivity(intent);
            }
        });
        this.mHelpLayout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HelpActivity.class);
                startActivity(intent);
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void hideLoading()
    {
//        hideProgress();
    }

    public void setAvatar(String paramString)
    {
//        if ((getActivity() == null) || (getActivity().isFinishing()) || (!isAdded()));
//        while (SetupFragment.access$600(this.this$0) == null)
//            return;
//        Glide.with(getActivity().getApplicationContext()).load(paramString).error(2130837831).into(SetupFragment.access$600(this.this$0));
    }

    public void setNickname(String paramString)
    {
//        if ((getActivity() == null) || (getActivity().isFinishing()) || (!isAdded()));
//        while (SetupFragment.access$700(this.this$0) == null)
//            return;
//        SetupFragment.access$700(this.this$0).setText(paramString);
    }

    public void setWatchDescText(String paramString)
    {
//        if ((getActivity() == null) || (getActivity().isFinishing()) || (!isAdded()));
//        while (SetupFragment.access$800(this.this$0) == null)
//            return;
//        SetupFragment.access$800(this.this$0).setText(paramString);
    }

    public void showLoading()
    {
//        showProgress();
    }

    public void showToast(@StringRes int paramInt)
    {
//        showShortToast(paramInt);
    }

    public void showToast(CharSequence paramCharSequence)
    {
//        showShortToast(paramCharSequence);
    }
    
}
