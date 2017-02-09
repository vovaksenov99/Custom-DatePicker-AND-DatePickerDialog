package com.akscorp.blueboard.CustomDatePicker;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.akscorp.blueboard.MyUtilite.MyPair;
import com.akscorp.blueboard.R;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Владимир on 07.02.2017.
 */

public class GridAdapter extends BaseAdapter {

    private Context context;

    int year, month;
    String[] day_of_week_name = new String[7];
    ArrayList<MyPair<Calendar,Calendar>> busy_list = new ArrayList<>();
    int pass = 0;

    public GridAdapter(Context c, int year, int month, ArrayList<MyPair<Calendar,Calendar>> busy, int pass) {
        context = c;
        this.year = year;
        this.month = month;
        this.busy_list.clear();
        this.busy_list.addAll(busy);
        this.pass = pass;

        DateFormatSymbols dfs = new DateFormatSymbols();
        day_of_week_name = dfs.getShortWeekdays();

    }

    public int getCount() {
        Calendar mycal = new GregorianCalendar(year, month, 1);
        int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
        return daysInMonth + 7 + pass;//day in month + day of week count + null element count
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;


            //fill day of week name
            if (position < 7) {
                TextView textView = new TextView(context);
                int index = position + 2;
                if (index > 7)
                    index = 1;
                textView.setText(day_of_week_name[index]);
                textView.setGravity(Gravity.CENTER);
                return textView;
            }
            //add null element for start from correct day of week
            if (position < 7 + pass) {
                return new View(context);
            }

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.grid_element, null);
            LinearLayout linearLayout = (LinearLayout) v;
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            linearLayout.setGravity(Gravity.CENTER);

            CircleTextView tv = (CircleTextView) linearLayout.findViewById(R.id.date);
            tv.setText(Integer.toString(position - 6 - pass));
            tv.setTextColor(Color.BLACK);
            tv.setGravity(Gravity.CENTER);
            tv.set_back_color(R.color.colorAccent);
            tv.set_padding_around_text(33);




            try {
                for(int i=0;i<busy_list.size();i++) {
                    int d = position - 6 - pass;
                    Calendar cc = Calendar.getInstance();
                    cc.set(year,month,d);
                    if((cc.after(busy_list.get(i).first) && cc.before(busy_list.get(i).second)) || comp(cc,busy_list.get(i).first)
                            || comp(cc,busy_list.get(i).second)) {
                        tv.set_check(true);
                        break;
                    }
                }

            }
            catch (Exception e)
            {

            }



        return v;
    }
    boolean comp(Calendar a,Calendar b)
    {
        if(a.get(Calendar.DAY_OF_MONTH) == b.get(Calendar.DAY_OF_MONTH) &&
                a.get(Calendar.MONTH) == b.get(Calendar.MONTH) &&
                a.get(Calendar.YEAR) == b.get(Calendar.YEAR))
            return true;
        return false;
    }

}