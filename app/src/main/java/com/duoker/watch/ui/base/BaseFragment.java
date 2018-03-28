package com.duoker.watch.ui.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by chengang on 4/19/2017.
 */

public abstract class BaseFragment extends Fragment
{
    private static final String Is_Hidden = "Is_Hidden";
    private boolean isHidden = false;
    protected View rootView;

    public View findViewById(int paramInt)
    {
        if (this.rootView != null)
            return this.rootView.findViewById(paramInt);
        throw new RuntimeException("rootView is not attach");
    }

    public void hideProgress()
    {
        if ((getActivity() == null) || (getActivity().isFinishing()) || (!isAdded()));
        while (!(getActivity() instanceof BaseActivity))
            return;
        ((BaseActivity)getActivity()).hideProgress();
    }

    public void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
    }

    public void onDestroy()
    {
        super.onDestroy();
        hideProgress();
    }

    public void onDestroyView()
    {
        super.onDestroyView();
        hideProgress();
    }

    public void onDetach()
    {
        super.onDetach();
        hideProgress();
    }

    public void onHiddenChanged(boolean paramBoolean)
    {
        super.onHiddenChanged(paramBoolean);
        this.isHidden = paramBoolean;
        if (paramBoolean)
            hideProgress();
    }

    public void onPause()
    {
        super.onPause();
        this.isHidden = true;
        hideProgress();
    }

    public void onResume()
    {
        super.onResume();
        this.isHidden = false;
    }

    public void onSaveInstanceState(Bundle paramBundle)
    {
        super.onSaveInstanceState(paramBundle);
        paramBundle.putBoolean("Is_Hidden", true);
    }

    public void onStop()
    {
        super.onStop();
        hideProgress();
    }

    public void onViewCreated(View paramView, @Nullable Bundle paramBundle)
    {
        super.onViewCreated(paramView, paramBundle);
        this.rootView = paramView;
    }

    public void showProgress()
    {
        if ((getActivity() == null) || (getActivity().isFinishing()) || (!isAdded()));
        do
        {
            return;
            /*CG  if (this.isHidden)
            {
                hideProgress();
                return;
            } */
        }
        while (!(getActivity() instanceof BaseActivity));
        //CG ((BaseActivity)getActivity()).showProgress();
    }

    public void showShortToast(@StringRes int paramInt)
    {
        if ((getActivity() == null) || (getActivity().isFinishing()))
            return;
        Toast.makeText(getActivity(), paramInt, Toast.LENGTH_SHORT).show();
    }

    public void showShortToast(CharSequence paramCharSequence)
    {
        if ((getActivity() == null) || (getActivity().isFinishing()))
            return;
        Toast.makeText(getActivity(), paramCharSequence, Toast.LENGTH_SHORT).show();
    }
}