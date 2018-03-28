package com.duoker.watch.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duoker.watch.R;

public class FragmentCall extends Fragment
{
    private Bundle savedInstanceState;
    private  Activity activity = null;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragement_call, container, false);

        activity = this.getActivity();

        return view;
    }
}

