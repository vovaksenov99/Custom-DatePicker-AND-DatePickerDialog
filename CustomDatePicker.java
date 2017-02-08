package com.example.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static java.lang.Math.max;

/**
 * Created by Владимир on 07.02.2017.
 */

public class CustomDatePicker extends LinearLayout {

    Context context;
    ArrayList<ArrayList<MyPair<Integer, Integer>>> selected_interval_list = new ArrayList<>();

    CustomDatePicker(Context c, AttributeSet a) {
        super(c, a);
        context = c;
       // setOrientation(VERTICAL);
        setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        if (changed) {
            System.out.print(getChildCount());
            addView(get_DatePicker(2017));

        }
    }

    void set_selected_interval(ArrayList<ArrayList<MyPair<Integer, Integer>>> busy_list) {
        selected_interval_list.clear();
        selected_interval_list.addAll(busy_list);
        invalidate();
    }

    View get_DatePicker(int year) {
        LayoutInflater inflater = LayoutInflater.from(context);
        List<View> pages = new ArrayList<View>();
        final int[] height = {0};
        for (int i = 0; i < 12; i++) {
            View page = inflater.inflate(R.layout.main_form, null);
            TextView mm = (TextView) page.findViewById(R.id.month);
            mm.setText(new DateFormatSymbols().getMonths()[i].toString() + " " + Integer.toString(year));
            final GridView gridview = (GridView) page.findViewById(R.id.grid);
            int pass = get_month_first_day_of_week(i, year);

            if (selected_interval_list.size() > i)
                gridview.setAdapter(new GridAdapter(context, year, i, selected_interval_list.get(i), pass));
            else
                gridview.setAdapter(new GridAdapter(context, year, i, new ArrayList<MyPair<Integer, Integer>>(), pass));

            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id) {
                    Toast.makeText(context, "" + position,
                            Toast.LENGTH_SHORT).show();
                }
            });

            page = (View) page;
            pages.add(page);
        }
        GridPagerAdapter pagerAdapter = new GridPagerAdapter(pages, context);
        final ViewPager viewPager = new ViewPager(context);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        int p = viewPager.getMeasuredHeight();


        return viewPager;
    }


    void show_DatePickerDialog(int year) {

        LinearLayout linearLayout = new LinearLayout(context);
        //setOrientation(VERTICAL);
        linearLayout.setPadding(15, 5, 15, 5);
        linearLayout.addView(get_DatePicker(year));
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(linearLayout);
        builder.setPositiveButton("Ок", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    private int get_month_first_day_of_week(int i, int year) {
        Calendar cc;
        cc = Calendar.getInstance();
        cc.set(year, i, 1);
        switch (cc.get(Calendar.DAY_OF_WEEK)) {
            case 1:
                return 6;

            case 2:
                return 0;

            case 3:
                return 1;

            case 4:
                return 2;

            case 5:
                return 3;

            case 6:
                return 4;

            case 7:
                return 5;

            default:
                return 0;

        }
    }
}
