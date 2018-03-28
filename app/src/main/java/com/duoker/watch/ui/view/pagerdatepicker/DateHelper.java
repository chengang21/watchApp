package com.duoker.watch.ui.view.pagerdatepicker;

/**
 * Created by cheng on 2017/10/Calendar.MILLISECOND.
 */


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateHelper
{
    public static String getDate2Str(Date date, String format)
    {
        try
        {
            return new SimpleDateFormat(format).format(date);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "";
    }

    public static BeginNEndItem getDayBeginNEnd(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date start = calendar.getTime();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return new BeginNEndItem(start, calendar.getTime());
    }

    public static List<DateItem> getDaysBetweenStartAndEnd(Date start, Date end)
    {
        return getDaysBetweenStartAndEnd(start, end, true);
    }

    public static List<DateItem> getDaysBetweenStartAndEnd(Date start, Date end, boolean includeStart)
    {
        ArrayList retList = new ArrayList();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        if (includeStart)
        {
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
        }
        while (calendar.getTime().before(end))
        {
            retList.add(new DateItem(calendar.getTime()));
            calendar.add(Calendar.DATE, 1);
        }
        return retList;
    }

    public static List<DateItem> getMonth(Date start, Date end)
    {
        return getMonth(start, end, true);
    }

    public static List<DateItem> getMonth(Date start, Date end, boolean includeStart)
    {
        ArrayList retList = new ArrayList();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        if (includeStart)
        {
            calendar.set(Calendar.DATE, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
        }

        while (calendar.getTime().before(end))
        {
            retList.add(new DateItem(calendar.getTime()));
            calendar.add(Calendar.MONTH, 1);
        }
        return retList;
    }

    public static BeginNEndItem getMonthBeginNEnd(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date start = calendar.getTime();
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);

        return new BeginNEndItem(start, calendar.getTime());
    }

    public static List<DateItem> getWeek(Date start, Date end)
    {
        return getWeek(start, end, true);
    }

    public static List<DateItem> getWeek(Date start, Date end, boolean includeStart)
    {
        ArrayList<DateItem> retlist = new ArrayList<DateItem>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);

        int week = calendar.get(Calendar.DAY_OF_WEEK);
        if (week != 1)
            calendar.add(Calendar.DATE, -(week - 1));

        if (includeStart)
        {
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
        }

        while (calendar.getTime().before(end))
        {
            retlist.add(new DateItem(calendar.getTime()));
            calendar.add(Calendar.DATE, 7);
        }

        return retlist;
    }

    public static BeginNEndItem getWeekBeginNEnd(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int week = calendar.get(Calendar.DAY_OF_WEEK);
        if (week != 1)
            calendar.add(Calendar.DATE, -(week - 1));

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date start = calendar.getTime();

        calendar.add(Calendar.DATE, 6);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return new BeginNEndItem(start, calendar.getTime());
    }

    public static class BeginNEndItem
    {
        private Date beginTime;
        private Date endTime;

        public BeginNEndItem(Date start, Date end)
        {
            this.beginTime = start;
            this.endTime = end;
        }

        public Date getBeginTime()
        {
            return this.beginTime;
        }

        public Date getEndTime()
        {
            return this.endTime;
        }

        public void setBeginTime(Date paramDate)
        {
            this.beginTime = paramDate;
        }

        public void setEndTime(Date paramDate)
        {
            this.endTime = paramDate;
        }
    }

    public static class DateItem
    {
        private Date date;

        public DateItem(Date date)
        {
            this.date = date;
        }

        public boolean equals(Object obj)
        {
            DateItem localDateItem = (DateItem)obj;
            return this.date.getTime() == localDateItem.getDate().getTime();
        }

        public Date getDate()
        {
            return this.date;
        }

        public int hashCode()
        {
            return Long.valueOf(this.date.getTime()).hashCode();
        }
    }
}
