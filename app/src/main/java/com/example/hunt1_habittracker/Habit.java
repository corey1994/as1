package com.example.hunt1_habittracker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Corey on 2016-09-29.
 */



public class Habit {
    //TODO throw exception on missing name or empty daysToComplete

    protected Date date;
    protected String name;
    private List<String> daysToComplete;
    protected static SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");

    public Habit(Date date, String name) {
        this.date = date;
        this.name = name;
    }

    public Habit(Date date, String name, List<String> daysToComplete) {
        this.date = date;
        this.name = name;
        this.daysToComplete = daysToComplete;
    }

    public Habit(String dateString, String name, List<String> daysToComplete) {
        //TODO: throw exceptions on improper/empty input in any field.
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

    @Override
    public String toString() {
        //substring idea from http://www.w3schools.com/jsref/jsref_substring.asp
        String daysString = "(";
        for (int i = 0; i < this.daysToComplete.size(); i++) {
            if (i < this.daysToComplete.size() - 1) {
                daysString += this.daysToComplete.get(i).substring(0,3) + ", ";
            }
            else {
                daysString += this.daysToComplete.get(i).substring(0,3) + ")";
            }
        }
        return this.name+"\n"+daysString+"\n"+getDateString();
    }
}
