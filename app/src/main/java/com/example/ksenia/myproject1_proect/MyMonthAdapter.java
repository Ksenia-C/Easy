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

     MyMonthAdapter(Context context, My[] arr) {
        super(context, R.layout.adapter_item, arr);

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

        return convertView;
    }
}