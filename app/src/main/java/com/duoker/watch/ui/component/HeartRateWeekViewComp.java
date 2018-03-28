package com.duoker.watch.ui.component;

/**
 * Created by cheng on 2017/10/8.
 */

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.widget.TextView;

import com.duoker.watch.R;
import com.duoker.watch.model.HeartRateBean;
import com.duoker.watch.presenters.HeartRateWeekPresenter;
import com.duoker.watch.ui.base.BaseComponent;
import com.duoker.watch.ui.view.pagerdatepicker.DateHelper;
import com.duoker.watch.ui.view.pagerdatepicker.DateRecyclerView;
import com.duoker.watch.ui.view.pagerdatepicker.DescEntity;
import com.duoker.watch.ui.view.pagerdatepicker.PickerTypeEnum;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HeartRateWeekViewComp extends BaseComponent
{
    public static final String TAG = HeartRateWeekViewComp.class.getSimpleName();
    private TextView mAverageView;
    private LineChart mChart;
    private List<HeartRateBean> mData;
    private TextView mGreatView;
    private HeartRateWeekPresenter mHeartRatePresenter;
    private TextView mLowView;
    private DateRecyclerView mRecyclerView;

    public HeartRateWeekViewComp(Activity paramActivity, HeartRateWeekPresenter paramHeartRateWeekPresenter)
    {
        super(paramActivity);
        setContentView(R.layout.comp_heart_rate_month_view);
        this.mHeartRatePresenter = paramHeartRateWeekPresenter;
        initView();
        initListener();
        initChart();
        initData();
    }

    private LineDataSet createSet(List<Entry> paramList)
    {
        LineDataSet localLineDataSet = new LineDataSet(paramList, "");
        localLineDataSet.disableDashedLine();
        localLineDataSet.setColor(Color.parseColor("#2E6F84"));
        localLineDataSet.setCircleColor(Color.parseColor("#34abc5"));
        localLineDataSet.setLineWidth(2.0F);
        localLineDataSet.setCircleRadius(3.0F);
        localLineDataSet.setDrawCircleHole(false);
        localLineDataSet.setDrawCircles(true);
        localLineDataSet.setValueTextSize(9.0F);
        localLineDataSet.setValueTextColor(-1);
        localLineDataSet.setDrawFilled(true);
        localLineDataSet.setDrawValues(true);
        localLineDataSet.setHighLightColor(Color.parseColor("#DD8E42"));
        localLineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        if (Utils.getSDKInt() >= 18)
        {
            localLineDataSet.setFillDrawable(new ColorDrawable(Color.parseColor("#2E6F84")));
            return localLineDataSet;
        }
        localLineDataSet.setFillColor(Color.parseColor("#2E6F84"));
        return localLineDataSet;
    }

    private LineData getLineData(List<HeartRateBean> paramList)
    {
        if (paramList == null)
            return null;
        int i = paramList.size();
        this.mChart.getXAxis().setLabelCount(i, true);
        ArrayList localArrayList1 = new ArrayList();
        for (int j = 0; j < i; j++)
        {
            int k = ((HeartRateBean)paramList.get(j)).getHeartRate();
            localArrayList1.add(new Entry(j, k));
        }
        LineDataSet localLineDataSet = createSet(localArrayList1);
        ArrayList localArrayList2 = new ArrayList();
        localArrayList2.add(localLineDataSet);
        return new LineData(localArrayList2);
    }

    private void initChart()
    {
        this.mChart.setDrawGridBackground(false);
        // this.mChart.setDescription("");
        this.mChart.setNoDataText(getString(R.string.common_no_data));
        // this.mChart.setNoDataTextDescription(getString(2131165381));
        this.mChart.setTouchEnabled(true);
        this.mChart.setDragEnabled(true);
        this.mChart.setScaleEnabled(false);
        this.mChart.setPinchZoom(true);
        this.mChart.setBackgroundColor(Color.parseColor("#3F495C"));
        this.mChart.getLegend().setEnabled(false);
        XAxis localXAxis = this.mChart.getXAxis();
        localXAxis.enableGridDashedLine(10.0F, 10.0F, 0.0F);
        localXAxis.setDrawGridLines(true);
        localXAxis.setDrawAxisLine(true);
        localXAxis.setDrawLimitLinesBehindData(false);
        localXAxis.setDrawLabels(true);
        localXAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        localXAxis.setAvoidFirstLastClipping(true);
        localXAxis.setValueFormatter(new XAxisValueFormatter());
        localXAxis.setTextColor(-1);
        localXAxis.setAvoidFirstLastClipping(true);
        localXAxis.setXOffset(24.0F);
        localXAxis.setEnabled(true);
        YAxis localYAxis = this.mChart.getAxisLeft();
        localYAxis.removeAllLimitLines();
        localYAxis.setAxisMaxValue(130.0F);
        localYAxis.setAxisMinValue(50.0F);
        localYAxis.setYOffset(24.0F);
        localYAxis.enableGridDashedLine(10.0F, 10.0F, 0.0F);
        localYAxis.setDrawZeroLine(true);
        localYAxis.setDrawGridLines(false);
        localYAxis.setGranularityEnabled(true);
        localYAxis.setDrawLimitLinesBehindData(true);
        localYAxis.setDrawLabels(true);
        localYAxis.setTextColor(-1);
        localYAxis.setEnabled(true);
        this.mChart.getAxisRight().setEnabled(false);
        LineData localLineData = new LineData();
        this.mChart.setData(localLineData);
    }

    private void initData()
    {
        this.mRecyclerView.setAdapter();
    }

    private void initListener()
    {
        this.mRecyclerView.setOnItemClickListener(new DateRecyclerView.OnItemClickListener()
        {
            public void onItemClick(DateHelper.DateItem paramAnonymousDateItem, int paramAnonymousInt)
            {
                Log.d(HeartRateWeekViewComp.TAG, "position--" + paramAnonymousInt);
                Date localDate = paramAnonymousDateItem.getDate();
                Log.d(HeartRateWeekViewComp.TAG, "date--" + localDate.toString());
                HeartRateWeekViewComp.this.mHeartRatePresenter.setDate(localDate);
                HeartRateWeekViewComp.this.mHeartRatePresenter.getHeartRate();
            }
        });
    }

    private void initView()
    {
        this.mRecyclerView = ((DateRecyclerView)findViewById(R.id.rv1));
        this.mRecyclerView.setType(PickerTypeEnum.WEEK);
        DescEntity desc = new DescEntity();

        desc.setJanuary(getString(R.string.heart_rate_str_month_1));
        desc.setFebruary(getString(R.string.heart_rate_str_month_2));
        desc.setMarch(getString(R.string.heart_rate_str_month_3));
        desc.setApril(getString(R.string.heart_rate_str_month_4));
        desc.setMay(getString(R.string.heart_rate_str_month_5));
        desc.setJune(getString(R.string.heart_rate_str_month_6));
        desc.setJuly(getString(R.string.heart_rate_str_month_7));
        desc.setAugust(getString(R.string.heart_rate_str_month_8));
        desc.setSeptember(getString(R.string.heart_rate_str_month_9));
        desc.setOctober(getString(R.string.heart_rate_str_month_10));
        desc.setNovember(getString(R.string.heart_rate_str_month_11));
        desc.setDecember(getString(R.string.heart_rate_str_month_12));
        desc.setMonday(getString(R.string.heart_rate_str_monday));
        desc.setTuesday(getString(R.string.heart_rate_str_tuesday));
        desc.setWednesday(getString(R.string.heart_rate_str_wednesday));
        desc.setThursday(getString(R.string.heart_rate_str_thursday));
        desc.setFriday(getString(R.string.heart_rate_str_friday));
        desc.setSaturday(getString(R.string.heart_rate_str_saturday));
        desc.setSunday(getString(R.string.heart_rate_str_sunday));

        this.mRecyclerView.setDescEntity(desc);
        this.mChart = ((LineChart)findViewById(R.id.chart));
        this.mLowView = ((TextView)findViewById(R.id.low_view));
        this.mAverageView = ((TextView)findViewById(R.id.average_view));
        this.mGreatView = ((TextView)findViewById(R.id.great_view));
    }

    public void drawRecordChart(List<HeartRateBean> paramList)
    {
        if ((this.mChart != null) && (paramList != null) && (!paramList.isEmpty()))
        {
            this.mData = paramList;
            LineData localLineData = getLineData(paramList);
            this.mChart.setData(localLineData);
            this.mChart.setVisibleXRangeMaximum(7.0F);
            this.mChart.animateY(1500);
        }
    }

    public void resetRecordChart()
    {
        this.mData = null;
        if (this.mChart != null)
            this.mChart.clear();
    }

    public void setAverageText(String paramString)
    {
        if (this.mAverageView != null)
            this.mAverageView.setText(paramString);
    }

    public void setHighText(String paramString)
    {
        if (this.mGreatView != null)
            this.mGreatView.setText(paramString);
    }

    public void setLowText(String paramString)
    {
        if (this.mLowView != null)
            this.mLowView.setText(paramString);
    }

    public class XAxisValueFormatter        implements IAxisValueFormatter
    {
        private Calendar mCalendar = Calendar.getInstance();
        final Date mDate = new Date();

        public XAxisValueFormatter()
        {
        }

        private int getDayOfWeek(long paramLong)
        {
            this.mDate.setTime(1000L * paramLong);
            this.mCalendar.setTime(this.mDate);
            return this.mCalendar.get(Calendar.DAY_OF_WEEK);
        }

        public int getDecimalDigits()
        {
            return 0;
        }

        public String getFormattedValue(float paramFloat, AxisBase paramAxisBase)
        {
            int i = (int)paramFloat;
            try
            {
                switch (getDayOfWeek(((HeartRateBean)HeartRateWeekViewComp.this.mData.get(i)).getCalcTime()))
                {
                    case 1:
                        return getString(R.string.sport_step_sunday);
                    case 2:
                        return getString(R.string.sport_step_monday);
                    case 3:
                        return getString(R.string.sport_step_tuesday);
                    case 4:
                        return getString(R.string.sport_step_wednesday);
                    case 5:
                        return getString(R.string.sport_step_thursday);
                    case 6:
                        return getString(R.string.sport_step_friday);
                    case 7:
                        return getString(R.string.sport_step_sunday);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return "";
        }
    }
}
