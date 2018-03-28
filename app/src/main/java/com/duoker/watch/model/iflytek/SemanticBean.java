package com.duoker.watch.model.iflytek;

import java.io.Serializable;

/**
 * Created by cheng on 2017/10/6.
 */
public class SemanticBean implements Serializable {
    private SlotsBean slots;

    public SlotsBean getSlots() {
        return slots;
    }

    public void setSlots(SlotsBean slots) {
        this.slots = slots;
    }
}
