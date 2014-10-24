package com.ski.ski_4;

/**
 * Created by Alyosha on 04.12.13.
 */


/*import static com.example.ski_4.Constants.THIRD_COLUMN;
import static com.example.ski_4.Constants.FOURTH_COLUMN;*/


import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class listviewAdapter extends BaseAdapter {

    public ArrayList<String> list_first;
    public ArrayList<String> list_second;
    Activity activity;

    public listviewAdapter(Activity activity, ArrayList<String> list1, ArrayList<String> list2) {
        super();
        this.activity = activity;
        this.list_first = list1;
        this.list_second = list2;

    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list_first.size();

    }


    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list_first.get(position);

    }


    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;

    }


    private View newView(ViewGroup parent) {
        View v;
        LayoutInflater inflater = (LayoutInflater) this.activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.listview_row, parent, false);
        return v;
    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        if (convertView == null) {
            v = newView(parent);
        } else {
            v = convertView;
        }
        bindView(v, position);
        return v;
    }




//    @Override
    public View bindView( View convertView, int position) {

        final TextView txt_first = (TextView) convertView.findViewById(R.id.FirstText);
        final TextView txt_second = (TextView) convertView.findViewById(R.id.SecondText);

     //   HashMap map = list.get(0);
        String list_f = list_first.get(position);
        String list_s = list_second.get(position);
        txt_first.setText(list_f);
        txt_second.setText(list_s);
        Log.w("Ski_c lvA coloumn",list_f );
        Log.w("Ski_c lvA constants date2 array is ", String.valueOf(Constants.Date2));
        return convertView;

    }


}