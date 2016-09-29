package com.example.hunt1_habittracker;

import android.test.ActivityInstrumentationTestCase2;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Corey on 2016-09-29.
 */

public class HabitTest extends ActivityInstrumentationTestCase2 {
    ArrayList daysToComplete = new ArrayList<String>();
    String stdName = "Clean your room";

    public HabitTest() {
        super(HabitTrackerMainActivity.class);
        daysToComplete.add("Monday");
        daysToComplete.add("Tuesday");
    }

    public void testGetName() {
        Habit a = new Habit(new Date(), stdName, daysToComplete);
        String name = a.getName();
        assertEquals(stdName, name);
    }

    public void testGetDateString() {
        Habit a = new Habit("January 2, 2016", stdName, daysToComplete);
        String dateString = a.getDateString();
        String expectedDateString = "Jan 02, 2016";
        assertEquals(expectedDateString, dateString);
    }
}
