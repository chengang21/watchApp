package com.duoker.watch.db;

/**
 * Created by chengang on 5/10/2017.
 */


import org.xutils.DbManager;
import org.xutils.x;

public class XUtilsDbHelper
{
    private static final String DB_NAME = "duoker.db";
    private static DbManager.DaoConfig mDaoConfig = new DbManager.DaoConfig().setDbName( DB_NAME ).setDbVersion(1).setDbOpenListener(new DbManager.DbOpenListener()
    {
        public void onDbOpened(DbManager paramAnonymousDbManager)
        {
            paramAnonymousDbManager.getDatabase().enableWriteAheadLogging();
        }
    }).setDbUpgradeListener(new DbManager.DbUpgradeListener()
    {
        public void onUpgrade(DbManager paramAnonymousDbManager, int paramAnonymousInt1, int paramAnonymousInt2)
        {
        }
    });
    private static DbManager mDbManager = x.getDb(mDaoConfig);

    public static DbManager getDbManager()
    {
        return mDbManager;
    }
}