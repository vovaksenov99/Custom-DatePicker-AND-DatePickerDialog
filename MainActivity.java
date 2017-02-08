package com.example.myapplication;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        ArrayList<ArrayList<MyPair<Integer, Integer>>> dd = new ArrayList<>();
        dd.add(new ArrayList<MyPair<Integer, Integer>>());
        dd.get(dd.size() - 1).add(new MyPair<Integer, Integer>().mp(1, 3));
        CustomDatePicker ds = (CustomDatePicker) findViewById(R.id.sss);
        ds.set_selected_interval(dd);
        ds.show_DatePickerDialog(2017);
    }
}
