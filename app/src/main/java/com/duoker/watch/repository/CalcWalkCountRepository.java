package com.duoker.watch.repository;

import com.duoker.watch.db.MyDbException;
import com.duoker.watch.db.model.CalcWalkCountBean;
import com.duoker.watch.db.model.QueryCalcWalkCountModel;
import com.duoker.watch.storage.StepCountTargetStorage;

import java.io.IOException;

/**
 * Created by chengang on 5/9/2017.
 */
public interface CalcWalkCountRepository
{
    CalcWalkCountBean getCalcWalkCount(QueryCalcWalkCountModel queryCalcWalkCountModel)
            throws IOException;

    StepCountTargetStorage getStepCountTarget(String paramString1, String paramString2)
            throws MyDbException;

    void saveStepCountTarget(StepCountTargetStorage stepCountTargetStorage)
            throws MyDbException;
}