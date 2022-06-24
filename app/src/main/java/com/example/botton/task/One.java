package com.example.botton.task;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.SearchManager;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.botton.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


public class One extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    Button add;
    TextView textView;
    View v;
    ListView listView;
    ListTaskAdapter listTaskAdapter;
    DatePickerDialog datePickerDialog;
    int year, month, day, hour = 0, minute = 0;
    String date = "", ssdate ="";

    SearchView searchView;
    private MenuItem menuItem;
    SwipeRefreshLayout swipeRefreshLayout;
    ArrayList<TaskClass> arrayList;


    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_one, container, false);
//        imageView = v.findViewById(R.id.imagestatus);
//        textView = v.findViewById(R.id.textStatus);
//        int stt = 1;
        swipeRefreshLayout = v.findViewById(R.id.swip);
        listView = v.findViewById(R.id.listview);
        add = v.findViewById(R.id.add);
        searchView = v.findViewById(R.id.simpleSearchView);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog();
            }
        });
        String[] title = {"Pr1", "Pr2", "Pr3"};
        String[] date = {"00:00", "06:00", "12:00"};
        String[] des = {"du1", "da2", "da3"};
        String[] mem = {"cv", "giang", "cuong"};
        String[] status = {"1", "0", "-1"};
        ArrayList<String> save = new ArrayList<>();
        arrayList = new ArrayList<>();
        ArrayList<String> test = new ArrayList<>();
        for (int i = 0; i < title.length; i++){
            TaskClass taskClass = new TaskClass(title[i], des[i], date[i], mem[i], status[i]);
            save.add(title[i]);
            arrayList.add(taskClass);
            test.add(title[i]);
        }
        listTaskAdapter = new ListTaskAdapter(getActivity(), arrayList);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_dropdown_item_1line ,test);
        listView.setAdapter(listTaskAdapter);
        //searchView.setActivated(true);
        searchView.setQueryHint("Type your keyword here");
        searchView.onActionViewExpanded();
        searchView.setIconified(true);
        searchView.clearFocus();
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), save.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                listTaskAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
               //adapter.getFilter().filter(newText);
                listTaskAdapter.getFilter().filter(newText);
                return false;
            }
        });
        swipeRefreshLayout.setOnRefreshListener(this);

        return v;
    }


    private void opendialog() {
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialod_add);
        Button btndate = dialog.findViewById(R.id.date);
        Button btntime = dialog.findViewById(R.id.time);
         Calendar calendar = Calendar.getInstance();
          int year = calendar.get(Calendar.YEAR);
          int month = calendar.get(Calendar.MONTH);
         int day = calendar.get(Calendar.DAY_OF_MONTH);
         int m = month + 1;
          ssdate = day + "/" + m + "/" + year;
         int hourr = calendar.get(Calendar.HOUR_OF_DAY);
         int minitee = calendar.get(Calendar.MINUTE);
         int s = hourr*60 + minitee;


        btndate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(), "hell", Toast.LENGTH_SHORT).show();
                datePickerDialog = new DatePickerDialog(dialog.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int yearr, int monthh, int dayOfMonth) {
                        monthh = monthh +1;
                        date = dayOfMonth + "/" + monthh + "/" +yearr;
                        Toast.makeText(getActivity(), date, Toast.LENGTH_SHORT).show();
                        //Toast.makeText(getActivity(), ssdate, Toast.LENGTH_SHORT).show();
                    }
                }, year, month, day);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
        btntime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog.OnTimeSetListener timePickerDialog = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minu) {
                        hour = hourOfDay;
                        minute = minu;
                        int ss = hour*60 + minute;
                        boolean a = true;
                        if (ssdate.equalsIgnoreCase(date)){
                            if (ss < s) {
                                Toast.makeText(getActivity(), "Chon lai time", Toast.LENGTH_SHORT).show();
                                a = false;
                            }
                        }
                        if(a){
                               String timee = String.format(Locale.getDefault(), "%02d:%02d", hour, minute);
                               Toast.makeText(getActivity(), timee, Toast.LENGTH_SHORT).show();
                           }
                    }
                };
                int style = AlertDialog.THEME_HOLO_DARK;
                TimePickerDialog timePickerDialog1 = new TimePickerDialog(getActivity(), style, timePickerDialog, hour, minute, true);
                timePickerDialog1.show();
            }
        });
        dialog.show();
    }

    @Override
    public void onRefresh() {
        TaskClass taskClasss = new TaskClass("Pr4", "da4", "24/06", "dungcvcvc", "0");
        arrayList.add(taskClasss);
        listTaskAdapter.notifyDataSetChanged();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        },2000);


    }
}