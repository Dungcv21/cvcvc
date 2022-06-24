package com.example.botton.task;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.botton.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListTaskAdapter extends BaseAdapter implements Filterable {
    Context context;
    List<TaskClass> taskClasses;
    List<TaskClass> mtaskClasses;
    public ListTaskAdapter(@NonNull Context context, List<TaskClass> taskClassArrayList) {
        this.context = context;
        this.taskClasses = taskClassArrayList;
        this.mtaskClasses = taskClassArrayList;
    }

    @Override
    public int getCount() {
        return taskClasses.size();
    }

    @Override
    public Object getItem(int position) {
        return taskClasses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TaskClass taskClass = taskClasses.get(position);
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        }
        TextView title = convertView.findViewById(R.id.titleTask);
        TextView date = convertView.findViewById(R.id.dateTask);
        TextView des = convertView.findViewById(R.id.desTask);
        TextView mem = convertView.findViewById(R.id.textNameMember);
        TextView status = convertView.findViewById(R.id.textStatus);
        ImageView imstt = convertView.findViewById(R.id.imagestatus);

        int stt = Integer.parseInt(taskClass.getStatus());
        if(stt == -1){
            imstt.setImageResource(R.drawable.ic_err);
            imstt.setColorFilter(ContextCompat.getColor(context, R.color.err), android.graphics.PorterDuff.Mode.MULTIPLY);
            status.setText("Chưa hoàn thành");
            //status.setTextColor(Color.parseColor("#F10000"));
            status.setBackgroundColor(Color.RED);
        }else if(stt == 0){
            imstt.setImageResource(R.drawable.ic_wait);
            imstt.setColorFilter(ContextCompat.getColor(context, R.color.wait ), android.graphics.PorterDuff.Mode.MULTIPLY);
            status.setText("Đang tiến hành");
            //status.setTextColor(Color.parseColor("#F4DD14"));
            status.setBackgroundColor(Color.YELLOW);
        }else {
            imstt.setImageResource(R.drawable.ic_done);
            imstt.setColorFilter(ContextCompat.getColor(context, R.color.done), android.graphics.PorterDuff.Mode.MULTIPLY);
            status.setText("Đã hoàn thành");
//            status.setTextColor(Color.parseColor("#3BF407"));
            status.setBackgroundColor(Color.GREEN);
        }
        title.setText(taskClass.getTitle());
        date.setText(taskClass.getDate());
        des.setText(taskClass.getDes());
        mem.setText(taskClass.getMember());

        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if(strSearch.isEmpty()){
                    taskClasses = mtaskClasses;
                }else{
                    List<TaskClass> list = new ArrayList<>();
                    for(TaskClass t : mtaskClasses){
                        if(t.getTitle().toLowerCase().contains(strSearch.toLowerCase())
                        || t.getDate().toLowerCase().contains(strSearch.toLowerCase())
                        || t.getDes().toLowerCase().contains(strSearch.toLowerCase())
                        || t.getMember().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(t);
                        }
                    }
                    taskClasses = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = taskClasses;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                            taskClasses =(List<TaskClass>) results.values;
                            notifyDataSetChanged();
            }
        };
    }
}
