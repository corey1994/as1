package com.example.hunt1_habittracker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Corey on 2016-09-29.
 */



public class Habit {

    private Date date;
    private String name;
    private ArrayList<String> daysToComplete;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");


    public Habit(Date date, String name, ArrayList<String> daysToComplete) {
        this.date = date;
        this.name = name;
        this.daysToComplete = daysToComplete;
    }

    public Habit(String dateString, String name, ArrayList<String> daysToComplete) {
        try {
            this.date = dateFormat.parse(dateString);
        }
        catch (ParseException e) {
            this.date = new Date();
        }
        this.name = name;
        this.daysToComplete = daysToComplete;
    }

    public String getName() {
        return this.name;
    }

    public Date getDate() {
        return this.date;
    }

    public String getDateString() {
        return dateFormat.format(date);
    }
}
