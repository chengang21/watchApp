package com.duoker.watch.ui.fragment;

import android.support.v4.app.Fragment;

/**
 * Created by chengang on 4/28/2017.
 */

public class HeartRateMeasureFragment extends Fragment
{
//    public static final String TAG = HeartRateMeasureFragment.class.getSimpleName();
//    private HeartRateMeasurePresenter.View iView = new HeartRateMeasureFragment.2(this);
//    private TextView mBottomTipView;
//    private HeartRateMeasurePresenterImpl mHeartRateMeasurePresenter;
//    private LineChart mMeasureChart;
//    private ImageView mMeasureHearView;
//    private NumberProgressBar mProgressBar;
//    private View mProgressLayout;
//    private View mProgressTextLayout;
//    private TextView mProgressTextView;
//    private ColorfulRingProgressView mSuccessProgressBar;
//
//    private void addData(int paramInt)
//    {
//        LineData localLineData = (LineData)this.mMeasureChart.getData();
//        if (localLineData != null)
//        {
//            Object localObject = (ILineDataSet)localLineData.getDataSetByIndex(0);
//            if (localObject == null)
//            {
//                localObject = createSet();
//                localLineData.addDataSet((IDataSet)localObject);
//            }
//            localLineData.addEntry(new Entry(((ILineDataSet)localObject).getEntryCount(), paramInt), 0);
//            localLineData.notifyDataChanged();
//            this.mMeasureChart.notifyDataSetChanged();
//            this.mMeasureChart.setVisibleXRangeMaximum(12.0F);
//            this.mMeasureChart.moveViewToX(localLineData.getEntryCount());
//        }
//    }
//
//    private LineDataSet createSet()
//    {
//        LineDataSet localLineDataSet = new LineDataSet(null, "");
//        localLineDataSet.disableDashedLine();
//        localLineDataSet.setColor(Color.parseColor("#ff4800"));
//        localLineDataSet.setCircleColor(-1);
//        localLineDataSet.setLineWidth(2.0F);
//        localLineDataSet.setCircleRadius(3.0F);
//        localLineDataSet.setDrawCircleHole(false);
//        localLineDataSet.setDrawCircles(false);
//        localLineDataSet.setValueTextSize(9.0F);
//        localLineDataSet.setDrawFilled(false);
//        localLineDataSet.setDrawValues(false);
//        localLineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
//        return localLineDataSet;
//    }
//
//    private void initChart()
//    {
//        this.mMeasureChart.setDrawGridBackground(false);
//        this.mMeasureChart.setDescription("");
//        this.mMeasureChart.setNoDataText(getString(2131165380));
//        this.mMeasureChart.setNoDataTextDescription(getString(2131165381));
//        this.mMeasureChart.setTouchEnabled(true);
//        this.mMeasureChart.setDragEnabled(true);
//        this.mMeasureChart.setScaleEnabled(false);
//        this.mMeasureChart.setPinchZoom(true);
//        this.mMeasureChart.setBackgroundColor(0);
//        this.mMeasureChart.getLegend().setEnabled(false);
//        XAxis localXAxis = this.mMeasureChart.getXAxis();
//        localXAxis.enableGridDashedLine(10.0F, 10.0F, 0.0F);
//        localXAxis.setDrawGridLines(false);
//        localXAxis.setDrawAxisLine(false);
//        localXAxis.setDrawLimitLinesBehindData(false);
//        localXAxis.setDrawLabels(false);
//        localXAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        localXAxis.setAvoidFirstLastClipping(true);
//        localXAxis.setXOffset(1.0F);
//        localXAxis.setEnabled(false);
//        YAxis localYAxis = this.mMeasureChart.getAxisLeft();
//        localYAxis.removeAllLimitLines();
//        localYAxis.setAxisMaxValue(130.0F);
//        localYAxis.setAxisMinValue(60.0F);
//        localYAxis.setYOffset(20.0F);
//        localYAxis.enableGridDashedLine(10.0F, 10.0F, 0.0F);
//        localYAxis.setDrawZeroLine(false);
//        localYAxis.setDrawGridLines(false);
//        localYAxis.setGranularityEnabled(false);
//        localYAxis.setDrawLimitLinesBehindData(true);
//        localYAxis.setDrawLabels(false);
//        localYAxis.setEnabled(false);
//        this.mMeasureChart.getAxisRight().setEnabled(false);
//        LineData localLineData = new LineData();
//        this.mMeasureChart.setData(localLineData);
//    }
//
//    private void initData()
//    {
//    }
//
//    private void initListener()
//    {
//        this.mProgressBar.setOnProgressBarListener(new HeartRateMeasureFragment.1(this));
//    }
//
//    public static HeartRateMeasureFragment newInstance()
//    {
//        return new HeartRateMeasureFragment();
//    }
//
//    public void onCreate(Bundle paramBundle)
//    {
//        super.onCreate(paramBundle);
//    }
//
//    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
//    {
//        return paramLayoutInflater.inflate(2130968700, paramViewGroup, false);
//    }
//
//    public void onDestroyView()
//    {
//        super.onDestroyView();
//        this.mHeartRateMeasurePresenter.onDestroy();
//        EventBus.getDefault().unregister(this);
//    }
//
//    @Subscribe
//    public void onEvent(PushHeartRateMessagesEvent paramPushHeartRateMessagesEvent)
//    {
//    }
//
//    public void onViewCreated(View paramView, @Nullable Bundle paramBundle)
//    {
//        super.onViewCreated(paramView, paramBundle);
//        EventBus.getDefault().register(this);
//        this.mHeartRateMeasurePresenter = new HeartRateMeasurePresenterImpl(this.iView, new HeartRateRepositoryImpl());
//        this.mMeasureHearView = ((ImageView)findViewById(2131558830));
//        this.mMeasureChart = ((LineChart)findViewById(2131558831));
//        this.mBottomTipView = ((TextView)findViewById(2131558832));
//        this.mProgressLayout = findViewById(2131558833);
//        this.mProgressBar = ((NumberProgressBar)findViewById(2131558834));
//        this.mSuccessProgressBar = ((ColorfulRingProgressView)findViewById(2131558827));
//        this.mProgressTextLayout = findViewById(2131558828);
//        this.mProgressTextView = ((TextView)findViewById(2131558829));
//        initListener();
//        initChart();
//        initData();
//        this.mHeartRateMeasurePresenter.onCreate(paramBundle);
//    }
}
