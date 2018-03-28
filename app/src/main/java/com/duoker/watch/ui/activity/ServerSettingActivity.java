package com.duoker.watch.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.duoker.watch.R;
import com.duoker.watch.storage.SetupInfoHelper;
import com.duoker.watch.ui.base.BaseActivity;

public class ServerSettingActivity extends BaseActivity {
    private String[] mAddrArray = {"139.196.226.71:8280", "139.196.226.71:80"};
    private Spinner mAddrSp;
    private Button mLongConnBtn;
    private EditText mServerEt;
    private Button mSureBtn;
    private boolean isDebug = true;

    private void initData() {
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, this.mAddrArray);
        this.mAddrSp.setAdapter(arrayAdapter);

        new Handler().postDelayed( new Runnable() {
                  public void run() {
                      String str = new SetupInfoHelper(getApplication()).getServerAddr();
                      mServerEt.setText(str);
                      mServerEt.setSelection(mServerEt.length());
                      if (isDebug) {
                          mLongConnBtn.setText("长连接：DEBUG");
                          return;
                      }
                      mLongConnBtn.setText("长连接：正式");
                  }
              }, 1000L);
    }

    private void initListener() {
        this.mAddrSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong) {
                String str = mAddrArray[paramAnonymousInt];
                mServerEt.setText(str);
            }

            public void onNothingSelected(AdapterView<?> v) {
            }
        });

        this.mLongConnBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if ( isDebug )
                {
                    mLongConnBtn.setText("长连接：DEBUG");
                    return;
                }
                mLongConnBtn.setText("长连接：正式");
                return;
            }
        });
        this.mSureBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String str = mServerEt.getText().toString();
                new SetupInfoHelper(getApplication()).saveServerAddr(str);
                finish();
            }
        });
    }

    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_server_setting);

        this.mAddrSp = ((Spinner) findViewById(R.id.addr_sp));
        this.mServerEt = ((EditText) findViewById(R.id.server_et));
        this.mLongConnBtn = ((Button) findViewById(R.id.longcos_conn_btn));
        this.mSureBtn = ((Button) findViewById(R.id.sure_btn));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.drawable.icon_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        initListener();
        initData();
    }

    protected void onDestroy() {
        super.onDestroy();
    }
}
