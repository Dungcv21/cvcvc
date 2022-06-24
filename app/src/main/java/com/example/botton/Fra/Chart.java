package com.example.botton.Fra;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.botton.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


public class Chart extends Fragment {
    View v;
    PieChart pieChart;
    private SearchView searchView;
    private MenuItem menuItem;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_chart, container, false);
        pieChart = v.findViewById(R.id.chart);
        ArrayList<PieEntry> visi = new ArrayList<>();
        visi.add(new PieEntry(5, "Đã hoàn thành"));
        visi.add(new PieEntry(1, "Chưa hoàn thành"));
        visi.add(new PieEntry(2, "Đang tiến thành"));


        PieDataSet pieDataSet = new PieDataSet(visi, "");
        //pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setColors(Color.GREEN, Color.RED, Color.YELLOW);
//        pieDataSet.setColors(Color.GREEN, 1);
//        pieDataSet.setColor(Color.YELLOW, 2);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(20);
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("Title");
        pieChart.setCenterTextSize(30);
        pieChart.animate();
        return v;
    }

}