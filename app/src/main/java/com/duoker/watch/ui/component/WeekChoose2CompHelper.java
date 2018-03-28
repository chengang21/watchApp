package com.duoker.watch.ui.component;

/**
 * Created by cheng on 2017/10/6.
 */

import android.content.Context;

import com.duoker.watch.R;

import java.util.ArrayList;
import java.util.List;

public class WeekChoose2CompHelper {
    public static String[] getDescShowNameArray(Context paramContext) {
        String[] week = new String[7];
        week[0] = paramContext.getString(R.string.yi_str_sunday);
        week[1] = paramContext.getString(R.string.yi_str_monday);
        week[2] = paramContext.getString(R.string.yi_str_tuesday);
        week[3] = paramContext.getString(R.string.yi_str_wednesday);
        week[4] = paramContext.getString(R.string.yi_str_thursday);
        week[5] = paramContext.getString(R.string.yi_str_friday);
        week[6] = paramContext.getString(R.string.yi_str_saturday);
        return week;
    }

    public static String[] getItemShowNameArray(Context paramContext) {
        String[] week = new String[7];
        week[0] = paramContext.getString(R.string.yi_str_sunday);
        week[1] = paramContext.getString(R.string.yi_str_monday);
        week[2] = paramContext.getString(R.string.yi_str_tuesday);
        week[3] = paramContext.getString(R.string.yi_str_wednesday);
        week[4] = paramContext.getString(R.string.yi_str_thursday);
        week[5] = paramContext.getString(R.string.yi_str_friday);
        week[6] = paramContext.getString(R.string.yi_str_saturday);
        return week;
    }

    public static final boolean[] getSelectItemBooleanArray(int day) {
        boolean[] week = new boolean[7];

        week[0] = 0 < (day & 0x1);
        week[1] = 0 < (day & 0x2);
        week[2] = 0 < (day & 0x4);
        week[3] = 0 < (day & 0x8);
        week[4] = 0 < (day & 0x10);
        week[5] = 0 < (day & 0x20);
        week[6] = 0 < (day & 0x40);

        return week;
    }

    public static final boolean[] getSelectItemBooleanArray(List<WeekChoose> list) {
        boolean[] arrayOfBoolean = new boolean[7];
        if (list == null)
            return arrayOfBoolean;

        int size = list.size();
        for (int j = 0; j < size; j++) {
            if (((WeekChoose) list.get(j)).isSelect())
                arrayOfBoolean[j] = true;
        }

        return arrayOfBoolean;
    }

    public static final int getWeekData(List<WeekChoose> paramList) {
        return getWeekData(getSelectItemBooleanArray(paramList));
    }

    public static final int getWeekData(boolean[] selectedDays) {
        int ret = 0;
        if (selectedDays == null)
            return 0;

        for (int j = 0; j < 7; j++)
            if (selectedDays[j])
                ret |= 1 << j + 1;
        return ret;
    }

    public static final String getWeekDesc(Context context, int paramInt) {
        return getWeekDesc(context, getSelectItemBooleanArray(paramInt));
    }

    public static final String getWeekDesc(Context paramContext, List<WeekChoose> paramList) {
        return getWeekDesc(paramContext, getSelectItemBooleanArray(paramList));
    }

    public static final String getWeekDesc(Context context, boolean[] selectedDays) {
        if (selectedDays == null)
            return "";

        ArrayList list = new ArrayList();
        int size = selectedDays.length;
        for (int j = 0; j < size; j++)
            if (selectedDays[j])
                list.add(Integer.valueOf(j));

        StringBuilder sb = new StringBuilder();
        String[] daysDesc = getDescShowNameArray(context);

        size = list.size();
        for (int m = 0; m < size; m++) {
            sb.append(daysDesc[((Integer) list.get(m)).intValue()]);
            if (m != size - 1)
                sb.append(" ");
        }
        return sb.toString();
    }

    public static final boolean isSelectEveryday(int paramInt) {
        boolean[] selected = getSelectItemBooleanArray(paramInt);
        int size = selected.length;
        for (int j = 0; j < size; j++)
            if (selected[j])
                return false;

        return true;
    }

    public static final boolean isSelectWorkday(int day) {
        boolean[] selectedDays = getSelectItemBooleanArray(day);
        boolean ret = true;

        for (int i = 0; i < 7; i++) {
            boolean selected = selectedDays[i];
            if (selected && ((i == 0) || (i == 6))) {
                return false;
            } else if (!selected)
                ret = false;

        }
        return ret;
    }


}
