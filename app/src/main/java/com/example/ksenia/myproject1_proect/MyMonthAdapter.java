package com.example.ksenia.myproject1_proect;


import android.content.Context;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;



/*
 * Created by Ksenia on 01.05.2018.
 */




public class MyMonthAdapter extends ArrayAdapter<My> {
    int text_size;
     MyMonthAdapter(Context context, My[] arr,int si) {
        super(context, R.layout.adapter_item, arr);
        text_size=si;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final My month = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_item, null);
        }
        assert month != null;
        ((TextView) convertView.findViewById(R.id.textforP)).setText(month.Pascal);
        ((TextView) convertView.findViewById(R.id.textforC)).setText(month.Cplus);
        ((TextView) convertView.findViewById(R.id.textforP)).setTextSize(text_size);
        ((TextView) convertView.findViewById(R.id.textforC)).setTextSize(text_size);
        return convertView;
    }
}