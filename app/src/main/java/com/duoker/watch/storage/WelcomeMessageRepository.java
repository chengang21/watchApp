package com.duoker.watch.storage;

import com.duoker.watch.repository.MessageRepository;

/**
 * Created by chengang on 4/25/2017.
 */

public class WelcomeMessageRepository implements MessageRepository {
    @Override
    public String getWelcomeMessage() {
        String msg = "Welcome, friend!"; // let's be friendly


        // let's simulate some network/database lag
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return msg;
    }
}
