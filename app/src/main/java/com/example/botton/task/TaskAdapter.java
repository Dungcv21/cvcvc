package com.example.botton.task;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class TaskAdapter extends FragmentStatePagerAdapter {
    public TaskAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public TaskAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return  new One();
            case 1:
                return  new Two();
            case 2:
                return  new Three();
            case 3:
                return  new Four();
            default:
                return new One();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return  "Tất cả công việc";
            case 1:
                return  "Công việc của tôi";
            case 2:
                return  "Công việc chưa xong";
            case 3:
                return  "Công việc đã xong";
            default:
                return  "Tất cả công việc";
        }
    }
}
