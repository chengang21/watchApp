package com.duoker.watch.ui.view.wheelview;

import android.os.Handler;

/**
 * Created by cheng on 2017/10/7.
 */
import android.os.Handler;
import android.os.Message;

final class MessageHandler extends Handler
{
    public static final int WHAT_INVALIDATE_LOOP_VIEW = 1000;
    public static final int WHAT_ITEM_SELECTED = 3000;
    public static final int WHAT_SMOOTH_SCROLL = 2000;
    final LoopView loopview;

    MessageHandler(LoopView paramLoopView)
    {
        this.loopview = paramLoopView;
    }

    public final void handleMessage(Message paramMessage)
    {
        switch (paramMessage.what)
        {
            default:
                return;
            case 1000:
                this.loopview.invalidate();
                return;
            case 2000:
                this.loopview.smoothScroll(LoopView.ACTION.FLING);
                return;
            case 3000:
        }
        this.loopview.onItemSelected();
    }
}
