package com.duoker.watch.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.duoker.watch.R;
import com.duoker.watch.qrcode.CaptureActivity;
import com.duoker.watch.ui.fragment.FragmentAddWatchByID;
import com.duoker.watch.ui.fragment.FragmentAddWatchByQRCode;

/**
 * Created by chengang on 4/28/2017.
 */

public class WatchAddActivity extends FragmentActivity {
    private RadioGroup mMenuLayout;
    private Fragment mNowShowFragment;
    private FragmentAddWatchByQRCode fragmentAddWatchByQRCode;
    private FragmentAddWatchByID fragmentAddWatchByID;

    private void initView() {
        this.mMenuLayout = ((RadioGroup) findViewById(R.id.menu_layout));

        findViewById(R.id.toolbar_menu_back).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                WatchAddActivity.this.finish();
            }
        });

        this.mMenuLayout.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.menu_qr_code)
                    WatchAddActivity.this.showFragment(0);
                else if (checkedId == R.id.menu_watch_id)
                    WatchAddActivity.this.showFragment(1);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_add);

        initView();
        mMenuLayout.check(R.id.menu_qr_code);
    }

    public void showFragment(int checkedId) {
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        if (this.mNowShowFragment != null) {
            trans.hide(this.mNowShowFragment);
        }
        if (checkedId == 0) {
            if (this.fragmentAddWatchByQRCode == null) {
                fragmentAddWatchByQRCode = FragmentAddWatchByQRCode.newInstance();
                this.mNowShowFragment = fragmentAddWatchByQRCode;
                trans.add(R.id.container_layout, this.fragmentAddWatchByQRCode, FragmentAddWatchByQRCode.class.getSimpleName());
            } else {
                this.mNowShowFragment = fragmentAddWatchByQRCode;
                trans.show(this.fragmentAddWatchByQRCode);
            }
            trans.commit();
        } else if (checkedId == 1) {
            if (this.fragmentAddWatchByID == null) {
                fragmentAddWatchByID = FragmentAddWatchByID.newInstance();
                this.mNowShowFragment = fragmentAddWatchByID;
                trans.add(R.id.container_layout, this.fragmentAddWatchByID, FragmentAddWatchByID.class.getSimpleName());
            } else {
                this.mNowShowFragment = fragmentAddWatchByID;
                trans.show(this.fragmentAddWatchByID);
            }
            trans.commit();
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case CaptureActivity.SCANNIN_GREQUEST_CODE:
                if(resultCode == Activity.RESULT_OK){
                    Bundle bundle = data.getExtras();
                    // 显示扫描到的内容
                    // mTextView.setText(bundle.getString("result"));
                    // 显示
                    Toast.makeText( getApplicationContext(), bundle.getString("result"),
                            Toast.LENGTH_LONG).show();
                    // mImageView.setImageBitmap((Bitmap) data.getParcelableExtra("bitmap"));
                }
                break;
        }
    }
}
