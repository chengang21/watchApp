package com.duoker.watch.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.duoker.watch.storage.WatchsStorage;
import com.duoker.watch.ui.fragment.FragmentDeviceItem;

/**
 * Created by chengang on 4/29/2017.
 */

public class DeviceItemFragmentAdapter extends FragmentStatePagerAdapter
{
    private WatchsStorage[] mWatchsStorageArray;

    public DeviceItemFragmentAdapter(FragmentManager paramFragmentManager, WatchsStorage[] paramArrayOfWatchsStorage)
    {
        super(paramFragmentManager);
        this.mWatchsStorageArray = paramArrayOfWatchsStorage;
    }

    public int getCount()
    {
        if (this.mWatchsStorageArray == null)
            return 0;
        return this.mWatchsStorageArray.length;
    }

    public Fragment getItem(int paramInt)
    {
        if (this.mWatchsStorageArray == null)
            return null;
        return FragmentDeviceItem.newInstance(this.mWatchsStorageArray[paramInt]);
    }

    public int getItemPosition(Object paramObject)
    {
        return -2;
    }

    public CharSequence getPageTitle(int paramInt)
    {
        return Integer.toString(paramInt);
    }
//
//    public WatchsStorage[] getWatches()
//    {
//        return this.mWatchsStorageArray;
//    }

    public void setWatches(WatchsStorage[] parm)
    {
        this.mWatchsStorageArray = parm;
        notifyDataSetChanged();
    }
}