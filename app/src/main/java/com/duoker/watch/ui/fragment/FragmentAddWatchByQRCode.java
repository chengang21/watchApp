package com.duoker.watch.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.duoker.watch.R;
import com.duoker.watch.qrcode.CaptureActivity;

/**
 * Created by chengang on 4/28/2017.
 */

public class FragmentAddWatchByQRCode extends Fragment {
    private ImageView mScanLayout;
    private Button mScanView;

    private void initListener() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FragmentAddWatchByQRCode.this.getActivity(), CaptureActivity.class);
                startActivity(intent);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, CaptureActivity.SCANNIN_GREQUEST_CODE);

                // FragmentAddWatchByQRCode.this.getActivity().finish();
            }
        };
        this.mScanLayout.setOnClickListener(listener);
        this.mScanView.setOnClickListener(listener);
    }

    public static FragmentAddWatchByQRCode newInstance() {
        return new FragmentAddWatchByQRCode();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_watch_by_qrcode, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mScanLayout = ((ImageView) getActivity().findViewById(R.id.scan_layout));
        this.mScanView = ((Button) getActivity().findViewById(R.id.scan_view));
        initListener();
    }

    //CG not called, WatchAddActivity.onActivityResult() get called
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case CaptureActivity.SCANNIN_GREQUEST_CODE:
                if(resultCode == Activity.RESULT_OK){
                    Bundle bundle = data.getExtras();
                    // 显示扫描到的内容
                    // mTextView.setText(bundle.getString("result"));
                    Toast toast = Toast.makeText( getActivity(), bundle.getString("result"),
                            Toast.LENGTH_LONG) ;
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    // 显示
                    // mImageView.setImageBitmap((Bitmap) data.getParcelableExtra("bitmap"));
                }
                break;
        }
    }
}