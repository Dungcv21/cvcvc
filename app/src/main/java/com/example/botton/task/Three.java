package com.example.botton.task;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.botton.R;

import java.util.ArrayList;

public class Three extends Fragment {

    View v;
    ListView listView;
    ListTaskAdapter listTaskAdapter;


    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_three, container, false);
//        imageView = v.findViewById(R.id.imagestatus);
//        textView = v.findViewById(R.id.textStatus);
//        int stt = 1;
        listView = v.findViewById(R.id.listview3);
        String[] title = {"Pr1", "Pr2", "Pr3"};
        String[] date = {"00:00", "06:00", "12:00"};
        String[] des = {"du1", "da2", "da3"};
        String[] mem = {"cv", "giang", "cuong"};
        String[] status = {"1", "0", "-1"};
        ArrayList<TaskClass> arrayList = new ArrayList<>();
        ArrayList<String> test = new ArrayList<>();
        for (int i = 0; i < title.length; i++){
            int stt = Integer.parseInt(status[i]);
            if(stt == -1) {
                TaskClass taskClass = new TaskClass(title[i], des[i], date[i], mem[i], status[i]);
                arrayList.add(taskClass);
            }
            test.add(title[i]);
        }
        listTaskAdapter = new ListTaskAdapter(getActivity(), arrayList);
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_dropdown_item_1line ,test);
        listView.setAdapter(listTaskAdapter);

        return v;
    }
}