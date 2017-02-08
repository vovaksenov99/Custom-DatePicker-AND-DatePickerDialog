package com.example.myapplication;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Владимир on 07.02.2017.
 */

public class GridPagerAdapter extends PagerAdapter {

    List<View> pages = null;
    Context context;

    public GridPagerAdapter(List<View> pages, Context context){
        this.pages = pages;
        this.context = context;
    }

    @Override
    public Object instantiateItem(View collection, int position){
        View v = pages.get(position);
        try {
            ((ViewPager) collection).addView(v);
        }
        catch (Exception e)
        {

        }
        return v;
    }

    @Override
    public void destroyItem(View collection, int position, Object view){
        ((ViewPager) collection).removeView((View) view);
    }

    @Override
    public int getCount(){
        return pages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object){
        return view.equals(object);
    }

    @Override
    public void finishUpdate(View arg0){
    }

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1){
    }

    @Override
    public Parcelable saveState(){
        return null;
    }

    @Override
    public void startUpdate(View arg0){
    }
}