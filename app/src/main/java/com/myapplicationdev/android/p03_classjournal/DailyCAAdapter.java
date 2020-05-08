package com.myapplicationdev.android.p03_classjournal;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DailyCAAdapter extends ArrayAdapter<DailyCA> {
    private ArrayList<DailyCA> dailyCA;
    private Context context;
    private TextView tvWeek;
    private TextView tvDG;
    private ImageView ivDG;

    public DailyCAAdapter(Context context,int resource, ArrayList<DailyCA> objects){
        super(context,resource,objects);
        dailyCA = objects;
        this.context = context;

    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row,parent,false);
        tvWeek =  (TextView) rowView.findViewById(R.id.tvWeek);
        tvDG =  (TextView) rowView.findViewById(R.id.tvDG);
        ivDG =  (ImageView) rowView.findViewById(R.id.iv);


        DailyCA currentDailyCA = dailyCA.get(position);
        tvDG.setText(currentDailyCA.getDgGrade());
        tvWeek.setText(""+currentDailyCA.getWeek());
        ivDG.setImageResource(R.drawable.dg);
        return rowView;
    }
}
