package com.duoker.watch.ui.base;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.widget.Toast;

import com.duoker.watch.ui.statusbar.StatusBarHelper;
import com.github.anzewei.parallaxbacklayout.ParallaxActivityBase;
import com.duoker.watch.ui.view.MyProgressDialog;
import com.duoker.watch.ui.view.MyProgressPopupWindow;

/**
 * Created by chengang on 4/19/2017.
 */


public abstract class BaseActivity extends ParallaxActivityBase
{
    private boolean isPause = false;
    private MyProgressPopupWindow mOneProgressDialog;
    protected StatusBarHelper mStatusBarHelper;
    private MyProgressDialog mTwoProgressDialog;

    private void hideOneProgress()
    {
        if (this.mOneProgressDialog == null)
            return;
        try
        {
            this.mOneProgressDialog.dismiss();
            return;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void hideTwoProgress()
    {
        if (this.mTwoProgressDialog == null)
            return;
        try
        {
            this.mTwoProgressDialog.dismiss();
            return;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void showOneProgress()
    {
        try
        {
            if (mOneProgressDialog == null)
                mOneProgressDialog = new MyProgressPopupWindow(this);
            mOneProgressDialog.showAtLocation(getWindow().getDecorView(), 17, 0, 0);
            return;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void showTwoProgress()
    {
        try
        {
            if (this.mTwoProgressDialog == null)
                this.mTwoProgressDialog = new MyProgressDialog(this);
            if (!this.mTwoProgressDialog.isShowing())
                this.mTwoProgressDialog.show();
            return;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void hideProgress()
    {
        hideOneProgress();
    }

    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        this.mStatusBarHelper = new StatusBarHelper(this, 1, 3);
        setBackEnable(false);
    }

    protected void onDestroy()
    {
        super.onDestroy();
        hideProgress();
    }

    protected void onPause()
    {
        super.onPause();
        this.isPause = true;
        hideProgress();
    }

    protected void onResume()
    {
        super.onResume();
        this.isPause = false;
    }

    protected void onStart()
    {
        super.onStart();
    }

    protected void onStop()
    {
        super.onStop();
    }

    public void showLongToast(@StringRes int paramInt)
    {
        if ((isFinishing()) || (this.isPause))
            return;
        Toast.makeText(getApplicationContext(), paramInt, Toast.LENGTH_LONG).show();
    }

    public void showLongToast(CharSequence paramCharSequence)
    {
        if ((isFinishing()) || (this.isPause))
            return;
        Toast.makeText(getApplicationContext(), paramCharSequence, Toast.LENGTH_LONG ).show();
    }

    public void showProgress()
    {
        if (this.isPause)
            return;
        showOneProgress();
    }

    public void showShortToast(@StringRes int paramInt)
    {
        if ((isFinishing()) || (this.isPause))
            return;
        Toast.makeText(getApplicationContext(), paramInt, Toast.LENGTH_SHORT).show();
    }

    public void showShortToast(CharSequence paramCharSequence)
    {
        if ((isFinishing()) || (this.isPause))
            return;
        Toast.makeText(getApplicationContext(), paramCharSequence, Toast.LENGTH_SHORT).show();
    }
}
