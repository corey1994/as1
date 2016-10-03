package com.example.hunt1_habittracker;

import android.test.ActivityInstrumentationTestCase2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Corey on 2016-10-03.
 */

public class HabitCompletionListTest extends ActivityInstrumentationTestCase2 {
    public HabitCompletionListTest() {
        super(HabitTrackerMainActivity.class);
    }

    public void testGetList() {
        HabitCompletion a = new HabitCompletion(new Date(), "clean room");
        HabitCompletionList al = new HabitCompletionList(a.getName());
        al.add(a);
        List<Habit> list = al.getList();

        String toString = list.get(0).toString();
        String expected = "clean room\n"+a.getDateString();
        assertEquals(expected, toString);
    }
}
