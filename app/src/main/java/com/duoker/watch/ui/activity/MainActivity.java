package com.duoker.watch.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.duoker.watch.R;
import com.duoker.watch.ui.fragment.FragmentAdapter;
import com.duoker.watch.ui.fragment.FragmentCall;
import com.duoker.watch.ui.fragment.FragmentConfig;
import com.duoker.watch.ui.fragment.FragmentLocation;
import com.duoker.watch.ui.fragment.FragmentMore;
import com.duoker.watch.ui.fragment.FragmentWebview;

import java.util.ArrayList;
import java.util.List;

// import android.support.v7.app.AppCompatActivity;

public class MainActivity extends FragmentActivity {

    public static String myObjectId = null;
    private RadioGroup rgs;//用来切换各个页面
    private RadioButton btn1;//如果是用户第一次进入这个app没有进行注册，则会跳转到注册页面
    public List<Fragment> fragments = new ArrayList<Fragment>();//将5个fragment添加到这个list里

    public static Fragment currentFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        // btn1 = (RadioButton) findViewById(R.id.menu_device);
        // pd = PedometerDB.getInstance(this);
        // 判断用户是否进行注册过，如果没有进行注册则选中注册页面的radiobutton
        myObjectId = "Chen Gang";

        rgs = (RadioGroup) findViewById(R.id.menu_layout);//实例化RadioGroup

        fragments.add(new FragmentWebview());

        FragmentLocation tmp = new FragmentLocation();
        tmp.setSavedInstanceState( savedInstanceState);
        fragments.add(tmp); // FragmentPK

        fragments.add( new FragmentConfig() );

        fragments.add(new FragmentCall());
        // fragments.add(new FragmentReminder());
        fragments.add(new FragmentMore());

        //自己写的一个fragment的适配器，进行几个页面的逻辑跳转
        new FragmentAdapter(MainActivity.this, fragments, R.id.container_frame, rgs, this);

//        RadioButton btn = (RadioButton) findViewById(R.id.btn1);
//        btn.setChecked( true );

    }



}
