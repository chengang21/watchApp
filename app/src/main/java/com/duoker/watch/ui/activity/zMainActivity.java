package com.duoker.watch.ui.activity;

/**
 * Created by chengang on 4/25/2017.
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.widget.TextView;
import android.widget.Toast;

import com.duoker.watch.executor.impl.ThreadExecutor;
import com.duoker.watch.presenters.MainPresenter;
import com.duoker.watch.presenters.MainPresenter.View;
import com.duoker.watch.presenters.impl.MainPresenterImpl;
import com.duoker.watch.storage.WelcomeMessageRepository;
import com.duoker.watch.threading.MainThreadImpl;


public class zMainActivity extends Activity implements View {

    // @Bind(R.id.welcome_textview)
    TextView mWelcomeTextView;

    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //CG setContentView(R.layout.activity_main);
        //CG ButterKnife.bind(this);

        // create a presenter for this view
        mPresenter = new MainPresenterImpl(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                this,
                new WelcomeMessageRepository()
        );
    }

    @Override
    protected void onResume() {
        super.onResume();

        // let's start welcome message retrieval when the app resumes
        mPresenter.onResume();
    }

    @Override
    public void showProgress() {
        mWelcomeTextView.setText("Retrieving...");
    }

    @Override
    public void hideProgress() {
        Toast.makeText(this, "Retrieved!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError(String message) {
        mWelcomeTextView.setText(message);
    }

    @Override
    public void showToast(@StringRes int resourceID) {

    }

    @Override
    public void showToast(CharSequence charSequence) {

    }

    @Override
    public void displayWelcomeMessage(String msg) {
        mWelcomeTextView.setText(msg);
    }
}
