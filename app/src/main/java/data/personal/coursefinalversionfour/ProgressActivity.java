package data.personal.coursefinalversionfour;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import static java.util.Comparator.comparing;

public class ProgressActivity extends DemoBase {

    protected BarChart mChart;
    private Typeface mTf;
    DBHandler db = new DBHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                //WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_progress);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTf = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Regular.ttf");
        mChart = (BarChart) findViewById(R.id.chart1);
        mChart.setBackgroundColor(Color.WHITE);
        mChart.setExtraTopOffset(-30f);
        mChart.setExtraBottomOffset(10f);
        mChart.setExtraLeftOffset(70f);
        mChart.setExtraRightOffset(70f);

        mChart.setDrawBarShadow(false);
        mChart.setDrawValueAboveBar(true);

        mChart.getDescription().setEnabled(false);

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        mChart.setDrawGridBackground(false);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(mTf);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setTextColor(Color.LTGRAY);
        xAxis.setTextSize(13f);
        xAxis.setLabelCount(5);
        xAxis.setCenterAxisLabels(true);
        xAxis.setGranularity(1f);

        YAxis left = mChart.getAxisLeft();
        left.setDrawLabels(false);
        left.setSpaceTop(25f);
        left.setSpaceBottom(25f);
        left.setDrawAxisLine(false);
        left.setDrawGridLines(false);
        left.setDrawZeroLine(true); // draw a zero line
        left.setZeroLineColor(Color.GRAY);
        left.setZeroLineWidth(0.7f);
        mChart.getAxisRight().setEnabled(false);
        mChart.getLegend().setEnabled(false);

        // THIS IS THE ORIGINAL DATA YOU WANT TO PLOT
        final List<Data> data = new ArrayList<>();
        data.add(new Data(0, -7, "12-29"));
        data.add(new Data(1, -5, "12-30"));
        data.add(new Data(2, 5, "12-31"));
        data.add(new Data(3, -3, "01-01"));
        data.add(new Data(4, 10, "01-02"));

        data.add(new Data(5, -8, "12-29"));
        data.add(new Data(6, 0, "12-30"));
        data.add(new Data(7, -1, "12-31"));
        data.add(new Data(8,2, "01-01"));
        data.add(new Data(9, 1, "01-02"));

        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return data.get(Math.min(Math.max((int) value, 0), data.size()-1)).xAxisValue;
            }
        });

        try {
            setWeeklyData(db.getWeeklyProgress());
            }
        catch (Exception ex){
            Log.i("ERROR: ", ex.getMessage().toString());
        }
        //setData(data);
        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                //Toast.makeText(getApplicationContext(), TabMessage.get(tabId, true), Toast.LENGTH_LONG).show();
            }
        });

        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                //Toast.makeText(getApplicationContext(), TabMessage.get(tabId, true), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setData(List<Data> dataList) {

        ArrayList<BarEntry> values = new ArrayList<BarEntry>();
        List<Integer> colors = new ArrayList<Integer>();

        int green = Color.rgb(110, 190, 102);
        int red = Color.rgb(211, 74, 88);

        for (int i = 0; i < dataList.size(); i++) {

            Data d = dataList.get(i);
            BarEntry entry = new BarEntry(d.xValue, d.yValue);

            values.add(entry);

            // specific colors
            if (d.yValue >= 0)
                colors.add(red);
            else
                colors.add(green);
        }

        BarDataSet set;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set = (BarDataSet)mChart.getData().getDataSetByIndex(0);
            set.setValues(values);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            set = new BarDataSet(values, "Values");
            set.setColors(colors);
            set.setValueTextColors(colors);

            BarData data = new BarData(set);
            data.setValueTextSize(13f);
            data.setValueTypeface(mTf);
            data.setValueFormatter(new ValueFormatter());
            data.setBarWidth(0.8f);

            mChart.setData(data);
            mChart.invalidate();
        }
    }
    private void setWeeklyData(List<BarChartEntry> dataList) throws Exception {



        ArrayList<BarEntry> values = new ArrayList<BarEntry>();
        List<Integer> colors = new ArrayList<Integer>();

        int green = Color.rgb(110, 190, 102);
        int red = Color.rgb(211, 74, 88);
        List<BarChartEntry> weeklyData = new ArrayList<BarChartEntry>();
        List<BarChartEntry> tempWeeklyData = weeklyData;

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = sdf.format(c.getTime());
        Date date =new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
        int id = 0;
        for(int i=6;i>=0;i--) {
            BarChartEntry barEntry = new BarChartEntry();
            SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd" );
            Calendar cal = Calendar.getInstance();
            cal.setTime( dateFormat.parse( strDate ) );
            cal.add( Calendar.DATE, i );
            barEntry.setDate(cal.getTime());
            barEntry.setValue(0);
            barEntry.setId(i+1);
            weeklyData.add(barEntry);
            id++;
        }
        //Collections.sort(weeklyData, comparing(BarChartEntry::getId));
        for(BarChartEntry e1:dataList)
            Log.i("FROM DATABASE: ", String.valueOf(e1.getDate()));
          for(BarChartEntry eOuter: dataList)
            {
                for(BarChartEntry bEntry: tempWeeklyData) {
                    Log.i("MATCHED1: ", String.valueOf(eOuter.getDate()));
                    Log.i("MATCHED2: ", String.valueOf(bEntry.getDate()));
                    if (eOuter.getDate().equals(bEntry.getDate())) {
                        Log.i("MATCHED: ", String.valueOf(bEntry.getDate()));
                        weeklyData.remove(bEntry);
                        bEntry.setValue(eOuter.getValue());
                        weeklyData.add(bEntry);
                        break;
                    }
                }
            }
        Log.i("Number of Entries: ", String.valueOf(weeklyData.size()));
        for(BarChartEntry e:weeklyData)
            Log.i(String.valueOf(e.getId()), String.valueOf(e.getValue()));
        BarChartEntry testEntry = new BarChartEntry();
        testEntry.setId(8);
        testEntry.setValue(20);
        testEntry.setDate(date);
        weeklyData.add(testEntry);
       // weeklyData.sort(Comparator.comparing(BarChartEntry::getId));
        Collections.sort(weeklyData, new WeeklyDataIdComparator());
        for(BarChartEntry e:weeklyData)
            Log.i(String.valueOf(e.getId()), String.valueOf(e.getValue()));

        for (BarChartEntry e2: weeklyData) {
            int value =  e2.getValue() - db.getGoal();

            BarEntry entry = new BarEntry(e2.getId(), value);

            values.add(entry);

            // specific colors
            if (value >= 0)
                colors.add(red);
            else
                colors.add(green);
        }

        BarDataSet set;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set = (BarDataSet)mChart.getData().getDataSetByIndex(0);
            set.setValues(values);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            set = new BarDataSet(values, "Values");
            set.setColors(colors);
            set.setValueTextColors(colors);

            BarData data = new BarData(set);
            data.setValueTextSize(13f);
            data.setValueTypeface(mTf);
            data.setValueFormatter(new ValueFormatter());
            data.setBarWidth(0.8f);

            mChart.setData(data);
            mChart.invalidate();
        }
    }
    /**
     * Demo class representing data.
     */
    private class Data {

        public String xAxisValue;
        public int yValue;
        public int xValue;

        public Data(int xValue, int yValue, String xAxisValue) {
            this.xAxisValue = xAxisValue;
            this.yValue = yValue;
            this.xValue = xValue;
        }
    }

    private class ValueFormatter implements IValueFormatter
    {

        private DecimalFormat mFormat;

        public ValueFormatter() {
            mFormat = new DecimalFormat("######");
        }

        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            return mFormat.format(value);
        }
        public void setZeroValuedIcon(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
           // entry.setIcon();

        }
    }
}
