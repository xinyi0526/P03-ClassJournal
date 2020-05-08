package com.myapplicationdev.android.p03_classjournal;

import java.io.Serializable;

public class DailyCA implements Serializable {
    private String dgGrade;
    private int week;

    public String getDgGrade() {
        return dgGrade;
    }


    public int getWeek() {
        return week;
    }
    public DailyCA(String dgGrade,int week){
        this.dgGrade = dgGrade;
        this.week = week;
    }
}
