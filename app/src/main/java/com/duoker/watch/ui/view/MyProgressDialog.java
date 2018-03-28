package com.duoker.watch.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.duoker.watch.R;

/**
 * Created by chengang on 4/19/2017.
 */


public class MyProgressDialog extends Dialog
{
    private ImageView loadingImageView;
    private Context mContext;

    public MyProgressDialog(Context paramContext)
    {
        super(paramContext);
        this.mContext = paramContext;
    }

    public MyProgressDialog(Context paramContext, int paramInt)
    {
        super(paramContext, paramInt);
        this.mContext = paramContext;
    }

    public MyProgressDialog(Context paramContext, boolean paramBoolean, DialogInterface.OnCancelListener paramOnCancelListener)
    {
        super(paramContext, paramBoolean, paramOnCancelListener);
        this.mContext = paramContext;
    }

    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        FrameLayout localFrameLayout = new FrameLayout(this.mContext);
        View localView = View.inflate(this.mContext, R.layout.base_progress_dialog, localFrameLayout);
        requestWindowFeature(1);
        Window localWindow = getWindow();
        //CG localWindow.setBackgroundDrawableResource(17170445);
        localWindow.setGravity(17);
        setContentView(localView);
        this.loadingImageView = ((ImageView)localView.findViewById(R.id.loadingImageView));
    }

    protected void onStart()
    {
        super.onStart();
        setCanceledOnTouchOutside(false);
        Animation localAnimation = AnimationUtils.loadAnimation(this.mContext, R.anim.base_progress_dialog_loading_animation);
        this.loadingImageView.startAnimation(localAnimation);
    }

    protected void onStop()
    {
        super.onStop();
    }
}
