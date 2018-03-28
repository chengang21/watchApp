package com.duoker.watch.repository;

import android.content.Context;

import com.duoker.watch.db.MyDbException;
import com.duoker.watch.db.XUtilsDbHelper;
import com.duoker.watch.db.model.CalcWalkCountBean;
import com.duoker.watch.db.model.QueryCalcWalkCountModel;
import com.duoker.watch.storage.StepCountTargetStorage;

import org.xutils.ex.DbException;

import java.io.IOException;

/**
 * Created by chengang on 5/10/2017.
 */
public class CalcWalkCountRepositoryImpl implements CalcWalkCountRepository {
    private final Context mContext;

    public CalcWalkCountRepositoryImpl(Context applicationContext) {
        mContext = applicationContext.getApplicationContext();
    }


    public CalcWalkCountBean getCalcWalkCount(QueryCalcWalkCountModel queryCalcWalkCountModel )
            throws IOException
    {
        CalcWalkCountBean bean = new CalcWalkCountBean();

        bean.setWalkNum( 2 );
        return bean;
    }

    public StepCountTargetStorage getStepCountTarget(String paramString1, String paramString2)
            throws MyDbException
    {
        try
        {
            StepCountTargetStorage localStepCountTargetStorage = (StepCountTargetStorage)XUtilsDbHelper.getDbManager().selector(StepCountTargetStorage.class).where("loginUserName", "=", paramString1).and("watchid", "=", paramString2).orderBy("id", true).findFirst();
            return localStepCountTargetStorage;
        }
        catch (DbException localDbException)
        {
            localDbException.printStackTrace();
            throw new MyDbException(localDbException.getCause());
        }
    }

    public void saveStepCountTarget(StepCountTargetStorage paramStepCountTargetStorage)
            throws MyDbException {
        if (paramStepCountTargetStorage == null)
            return;
        String userName = paramStepCountTargetStorage.getLoginUserName();
        String watchId = paramStepCountTargetStorage.getWatchId();
        long l = paramStepCountTargetStorage.getTargetStep();
        try
        {
            StepCountTargetStorage storage = (StepCountTargetStorage)XUtilsDbHelper.getDbManager().
                    selector(StepCountTargetStorage.class).
                    where("loginUserName", "=", userName).and("watchid", "=", watchId).
                    orderBy("id", true).
                    findFirst();
            if (storage != null)
            {
                storage.setTargetStep(l);
                XUtilsDbHelper.getDbManager().save(storage);
                return;
            }
        }
        catch (DbException e)
        {
            e.printStackTrace();
            throw new MyDbException(e.getCause());
        }
    }
}
