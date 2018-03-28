package com.duoker.watch.utils;

/**
 * Created by cheng on 2017/9/9.
 */

import android.content.Context;
import com.duoker.watch.R;
import java.util.ArrayList;
import java.util.List;

public class VolumeHelper
{
    private final Context mContext;

    public VolumeHelper(Context paramContext)
    {
        this.mContext = paramContext.getApplicationContext();
    }

    public List<VolumeHelper.VolumeData> findData()
    {
        ArrayList list = new ArrayList();
        VolumeHelper.VolumeData volumeData = new VolumeHelper.VolumeData();
        volumeData.setVolume(1);
        volumeData.setDesc(this.mContext.getString(R.string.watch_sets_volume_1));
        list.add(volumeData);
        VolumeHelper.VolumeData volumeData2 = new VolumeHelper.VolumeData();
        volumeData2.setVolume(2);
        volumeData2.setDesc(this.mContext.getString(R.string.watch_sets_volume_2));
        list.add(volumeData2);
        VolumeHelper.VolumeData volumeData3 = new VolumeHelper.VolumeData();
        volumeData3.setVolume(3);
        volumeData3.setDesc(this.mContext.getString(R.string.watch_sets_volume_3));
        list.add(volumeData3);
        VolumeHelper.VolumeData volumeData4 = new VolumeHelper.VolumeData();
        volumeData4.setVolume(4);
        volumeData4.setDesc(this.mContext.getString(R.string.watch_sets_volume_4));
        list.add(volumeData4);
        VolumeHelper.VolumeData volumeData5 = new VolumeHelper.VolumeData();
        volumeData5.setVolume(5);
        volumeData5.setDesc(this.mContext.getString(R.string.watch_sets_volume_5));
        list.add(volumeData5);
        VolumeHelper.VolumeData volumeData6 = new VolumeHelper.VolumeData();
        volumeData6.setVolume(6);
        volumeData6.setDesc(this.mContext.getString(R.string.watch_sets_volume_6));
        list.add(volumeData6);
        return list;
    }

    public String getDesc(int paramInt)
    {
        switch (paramInt)
        {
            case 1:
                return this.mContext.getString(R.string.watch_sets_volume_1);
            case 2:
                return this.mContext.getString(R.string.watch_sets_volume_2);
            case 3:
                return this.mContext.getString(R.string.watch_sets_volume_3);
            case 4:
                return this.mContext.getString(R.string.watch_sets_volume_4);
            case 5:
                return this.mContext.getString(R.string.watch_sets_volume_5);
            case 6:
                return this.mContext.getString(R.string.watch_sets_volume_6);
            default:
                return this.mContext.getString(R.string.watch_sets_volume_1);
        }
    }

    public class  VolumeData
    {
        private String desc;
        private int volume;

        public String getDesc()
        {
            return this.desc;
        }

        public int getVolume()
        {
            return this.volume;
        }

        public void setDesc(String paramString)
        {
            this.desc = paramString;
        }

        public void setVolume(int paramInt)
        {
            this.volume = paramInt;
        }
    }
}