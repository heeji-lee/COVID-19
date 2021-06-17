package com.example.covid_19application.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.covid_19application.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        BarChart chart = root.findViewById(R.id.chart);
        chart.setPinchZoom(false);

        //차트의 가로축
        XAxis xAxis=chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setTextSize(13f);

        //차트의 왼쪽 세로축
        YAxis leftAxis=chart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinValue(100);
        leftAxis.setAxisMaxValue(170);
        leftAxis.setGranularity(0.1f);

        //차트의 오른쪽 세로축
        YAxis rightAxis=chart.getAxisRight();
        rightAxis.setEnabled(false);

        //차트에 넣을 데이터 엔트리 생성 후 데이터 입력
        ArrayList person = new ArrayList<Integer>();
        person.add(new BarEntry(138, 0));
        person.add(new BarEntry(145, 1));
        person.add(new BarEntry(156, 2));
        person.add(new BarEntry(163, 3));
        person.add(new BarEntry(150, 4));
        person.add(new BarEntry(141, 5));
        person.add(new BarEntry(132, 6));

        //차트 가로축 데이터값 설정
        ArrayList day = new ArrayList();
        day.add("1일");
        day.add("2일");
        day.add("3일");
        day.add("4일");
        day.add("5일");
        day.add("6일");
        day.add("7일");

        //Bar 차트 dataset 생성
        BarDataSet bardataset = new BarDataSet(person, "일별 신규 확진자 수");

        //data 객체에 dataset 객체를 넣어준다.
        BarData data = new BarData(day, bardataset); // MPAndroidChart v3.X 오류 발생
        bardataset.setColors(ColorTemplate.createColors(new int[]{Color.rgb(255,127,0)}));
        bardataset.setValueTextColor(Color.rgb(75,45,14));
        bardataset.setValueTextSize(10f);
        bardataset.setBarSpacePercent(50f);
        chart.setTouchEnabled(false); //차트 고정
        chart.setData(data);

        //데이터 정수화화
       chart.getBarData().setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return ((int) value)+"";
            }
        });

        return root;
    }
}