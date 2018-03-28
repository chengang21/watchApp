package com.duoker.watch.ui.view;


import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.github.ybq.android.spinkit.style.FadingCircle;

import com.duoker.watch.R;

/**
 * Created by chengang on 4/19/2017.
 */


public class MyProgressPopupWindow extends PopupWindow
{
    private Context mContext;

    public MyProgressPopupWindow(Context paramContext)
    {
        super(paramContext);
        this.mContext = paramContext;
        init();
    }

    private void init()
    {
        FrameLayout localFrameLayout = new FrameLayout(this.mContext);
        View view = View.inflate(this.mContext, R.layout.base_progress_dialog, localFrameLayout);
        setContentView(view);
        setWidth(-2);
        setHeight(-2);
        setBackgroundDrawable(new ColorDrawable(0));
        ImageView imageView = (ImageView)view.findViewById(R.id.loadingImageView);
        FadingCircle fadingCircle = new FadingCircle();
        fadingCircle.setColor(0x00FF00);
        imageView.setImageDrawable(fadingCircle);
        fadingCircle.start();
    }
}
