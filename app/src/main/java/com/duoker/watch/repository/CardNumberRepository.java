package com.duoker.watch.repository;

/**
 * Created by chengang on 4/25/2017.
 */

import com.duoker.watch.db.model.CardNumberBean;

import java.io.IOException;
import java.util.List;

public interface CardNumberRepository
{
    List<CardNumberBean> getCardNumber(String userid, String watchid)
            throws IOException;

    void saveAll(String userid, String watchid, List<CardNumberBean> list)
            throws IOException;
}