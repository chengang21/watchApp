package com.duoker.watch.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by chengang on 4/23/2017.
 */

public class MyToolbar extends RelativeLayout {
    public MyToolbar(Context paramContext) {

        super(paramContext);
    }

    public MyToolbar(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
    }

    public MyToolbar(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        ImageView localImageView = new ImageView(getContext());
        RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, 1);
        localLayoutParams.addRule(12);
        localImageView.setLayoutParams(localLayoutParams);
        localImageView.setImageDrawable(new ColorDrawable(Color.parseColor("#dfe1e0")));
        addView(localImageView);
    }
}