package com.duoker.watch.ui.component;

/**
 * Created by cheng on 2017/10/8.
 */

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.duoker.watch.R;
import com.duoker.watch.utils.NotifyHelper;

public class NotifyChooseComp {
    private final Context mContext;
    private View mMuteLayout;
    private NotifyHelper.NotifyType mNotifyType = NotifyHelper.NotifyType.MUTE;
    private ImageView mSelectMuteIv;
    private ImageView mSelectShakeIv;
    private ImageView mSelectShakeRingIv;
    private View mShakeLayout;
    private View mShakeRingLayout;
    private View mView;

    public NotifyChooseComp(Context paramContext) {
        this.mContext = paramContext;
        initView();
    }

    public void init(NotifyHelper.NotifyType paramNotifyType) {
        this.mNotifyType = paramNotifyType;
        initData();
    }

    private void initData() {
        switch (this.mNotifyType) {
            case MUTE:
                clickMuteLayout();
                break;
            case SHAKE:
                clickShakeLayout();
                break;
            case SHAKE_RING:
                clickShakeRingLayout();
                break;
        }
    }

    private void initView() {
        this.mView = View.inflate(this.mContext, R.layout.choose_notify_dialog, null);
        this.mMuteLayout = this.mView.findViewById(R.id.mute_layout);
        this.mShakeLayout = this.mView.findViewById(R.id.shake_layout);
        this.mShakeRingLayout = this.mView.findViewById(R.id.shake_ring_layout);
        this.mSelectMuteIv = ((ImageView) this.mView.findViewById(R.id.select_mute_iv));
        this.mSelectShakeIv = ((ImageView) this.mView.findViewById(R.id.select_shake_iv));
        this.mSelectShakeRingIv = ((ImageView) this.mView.findViewById(R.id.select_shake_ring_iv));
        this.mMuteLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                NotifyChooseComp.this.clickMuteLayout();
            }
        });
        this.mShakeLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                NotifyChooseComp.this.clickShakeLayout();
            }
        });
        this.mShakeRingLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                NotifyChooseComp.this.clickShakeRingLayout();
            }
        });
        initData();
    }

    public void clickMuteLayout() {
        this.mNotifyType = NotifyHelper.NotifyType.MUTE;
        this.mSelectMuteIv.setImageResource(R.drawable.hb_icon_check_mark);
        this.mSelectShakeIv.setImageResource(0);
        this.mSelectShakeRingIv.setImageResource(0);
    }

    public void clickShakeLayout() {
        this.mNotifyType = NotifyHelper.NotifyType.SHAKE;
        this.mSelectMuteIv.setImageResource(0);
        this.mSelectShakeIv.setImageResource(R.drawable.hb_icon_check_mark);
        this.mSelectShakeRingIv.setImageResource(0);
    }

    public void clickShakeRingLayout() {
        this.mNotifyType = NotifyHelper.NotifyType.SHAKE_RING;
        this.mSelectMuteIv.setImageResource(0);
        this.mSelectShakeIv.setImageResource(0);
        this.mSelectShakeRingIv.setImageResource(R.drawable.hb_icon_check_mark);
    }

    public NotifyHelper.NotifyType getNotifyType() {
        return this.mNotifyType;
    }

    public View getView() {
        return this.mView;
    }

}
