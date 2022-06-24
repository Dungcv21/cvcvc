package com.example.botton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.botton.Fra.Mess;
import com.example.botton.Fra.Task;
import com.example.botton.Fra.Chart;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity {
    private MeowBottomNavigation meowBottomNavigation;
    MenuItem menuItem;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        meowBottomNavigation = findViewById(R.id.navigation_bar);
        meowBottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_home));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_message));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_fm_24));
        meowBottomNavigation.show(1, true);
        replace(new Task());
        meowBottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                switch (model.getId()){
                    case 1:
                        replace(new Task());
                        break;
                    case 2:
                        replace(new Mess());
                        break;
                    case 3:
                        replace(new Chart());
                        break;
                    default:
                        replace(new Task());
                }
                return null;
            }
        });
        meowBottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                //Toast.makeText(MainActivity.this, "SS", Toast.LENGTH_SHORT).show();
                return null;
            }
        });
    }
    private  void  replace(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fram, fragment);
        fragmentTransaction.commit();
    }



}
