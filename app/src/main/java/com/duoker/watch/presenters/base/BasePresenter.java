package com.duoker.watch.presenters.base;

/**
 * Created by chengang on 4/24/2017.
 */

import android.os.Bundle;

public interface BasePresenter
{
    public void onCreate(Bundle paramBundle);

    public void onDestroy();

    public void onError(String paramString);

    public void onPause();

    public void onRestart();

    public void onRestoreInstanceState(Bundle paramBundle);

    public void onResume();

    public void onSaveInstanceState(Bundle paramBundle);

    public void onStart();

    public void onStop();
}
