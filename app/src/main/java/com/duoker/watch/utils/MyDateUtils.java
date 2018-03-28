package com.duoker.watch.utils;

/**
 * Created by cheng on 2017/10/6.
 */


import android.content.Context;

import com.duoker.watch.R;
import com.duoker.watch.ui.view.pagerdatepicker.DateHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class MyDateUtils
{
    public static Date formatDate(Date date, String format)
    {
        try
        {
            String strDate = getDate2Str(date, format);
            return new SimpleDateFormat(format).parse(strDate);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return date;
    }

    public static String formatOne2One(String datestr, String format, String format2)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try
        {
            Date localDate = simpleDateFormat.parse(datestr);
            String str = new SimpleDateFormat(format2).format(localDate);
            return str;
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static String getDate2Str(Date paramDate, String format)
    {
        try
        {
            return new SimpleDateFormat(format).format(paramDate);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "";
    }

    public static Date getDateTimeFirst(Date paramDate)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(paramDate);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getDateTimeLast(Date paramDate)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(paramDate);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public static String getDesc(String date)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            return getDesc(simpleDateFormat.parse(date));
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static String getDesc(Date date)
    {
        String strDate = getDate2Str(date, "yyyy-MM-dd");
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

        try
        {
            Date date1 = sf.parse(strDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date1);

            Date today = sf.parse(getDate2Str(new Date(), "yyyy-MM-dd"));
            Calendar calendarToday = Calendar.getInstance();
            calendarToday.setTime(today);

            long timeMs = calendar.getTimeInMillis();
            long msDiff = calendarToday.getTimeInMillis() - timeMs;
            if (msDiff < 86400000L)  // 1 day
                return "今天";
            else if ((msDiff >= 86400000L) && (msDiff < 2L * 86400000L))
                return "昨天";
            else if ((msDiff >= 2L * 86400000L) && (msDiff < 3L * 86400000L))
                return "前天";

            return getDate2Str(date, "yyyy-MM-dd");
        }
        catch (ParseException e)
        {
            e.printStackTrace();
            return "未知";
        }
    }

    public static String getNowDate2Str()
    {
        return getNowDate2Str("yyyy-MM-dd HH:mm:ss");
    }

    public static String getNowDate2Str(String format)
    {
        return getDate2Str(new Date(), format);
    }

    public static boolean isRangeDate(Date date, Date start, Date end)
    {
        Date tmpDate = formatDate(date, "yyyy-MM-dd");

        if ((tmpDate.getTime() == start.getTime()) || (tmpDate.getTime() == end.getTime()))
            return false;
        return (date.after(start)) && (date.before(end));
    }

    public static boolean isRangeDateByNow(Date startDate, Date endDate)
    {
        return isRangeDate(new Date(), startDate, endDate);
    }

    public static boolean isRangeTime(Date date, Date start, Date end)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        Calendar startCalender = Calendar.getInstance();
        startCalender.setTime(start);
        startCalender.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));

        Calendar endCalender = Calendar.getInstance();
        endCalender.setTime(end);
        endCalender.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));

        return (date.after(startCalender.getTime())) && (date.before(endCalender.getTime()));
    }

    public static Date str2Date(String strDate, String format)
    {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        try
        {
            return sf.parse(strDate);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return null;
    }


    public static String getWeekDesc(Context context, Date date)
    {
        if (date == null)
            return null;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int i = calendar.get(Calendar.DAY_OF_WEEK);

        if (i == 1)
            return context.getString(R.string.yi_str_sunday);
        if (i == 2)
            return context.getString(R.string.yi_str_monday);
        if (i == 3)
            return context.getString(R.string.yi_str_tuesday);
        if (i == 4)
            return context.getString(R.string.yi_str_wednesday);
        if (i == 5)
            return context.getString(R.string.yi_str_thursday);
        if (i == 6)
            return context.getString(R.string.yi_str_friday);
        if (i == 7)
            return context.getString(R.string.yi_str_saturday);

        return context.getString(R.string.yi_str_monday);
    }

    public static List<DateHelper.DateItem> getDaysBetweenStartAndEnd(Date startDate, Date endDate) {
        List<DateHelper.DateItem> dates = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);

        while (calendar.getTime().before(endDate)) {
            Date result = calendar.getTime();
            dates.add(new DateHelper.DateItem(result));
            calendar.add(Calendar.DATE, 1);
        }

        return dates;
    }

}
