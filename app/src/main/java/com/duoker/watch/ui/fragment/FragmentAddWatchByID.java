package com.duoker.watch.ui.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.duoker.watch.R;

/**
 * Created by chengang on 4/28/2017.
 */

public class FragmentAddWatchByID extends Fragment {

    // private WatchAddCheckPresenter.View iView = new WatchDeviceAddWatchIdFragment .3(this);
    private Button mAddView;
    private ImageView mHelpView;
    // private WatchAddCheckPresenterImpl mWatchAddCheckPresenter;
    private EditText mWatchIdEt;

    private Activity activity = null;

    private void initListener() {
        this.mHelpView.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // new AlertDialog(this.this$0.getActivity()).setTitle(this.this$0.getString(2131165776)).setMsg(this.this$0.getString(2131165777)).setCancelable(true).setPositiveButton(this.this$0.getString(2131165370), null).show();
            }
        });
        this.mAddView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = FragmentAddWatchByID.this.mWatchIdEt.getText().toString();
                // FragmentAddWatchByID.this.mWatchAddCheckPresenter.check(str);
            }
        });
    }

    public static FragmentAddWatchByID newInstance() {
        return new FragmentAddWatchByID();
    }

    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);

        this.activity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate( R.layout.fragment_add_watch_by_id, container, false);
    }

    public void onViewCreated(View paramView, @Nullable Bundle paramBundle) {
        super.onViewCreated(paramView, paramBundle);
        // this.mWatchAddCheckPresenter = new WatchAddCheckPresenterImpl(this.iView, new WatchRepositoryImpl(getActivity()));
        this.mWatchIdEt = ((EditText) activity.findViewById(R.id.watch_id_et));
        this.mHelpView = ((ImageView) activity.findViewById( R.id.help_view));
        this.mAddView = ((Button) activity.findViewById( R.id.add_view ));
        initListener();
    }

    public EditText getmWatchIdEt() {
        return mWatchIdEt;
    }
}
