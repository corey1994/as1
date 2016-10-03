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

    public void testGetCountByName() {
        String name = "Clean dishes";
        HabitCompletionList list = new HabitCompletionList();
        assertEquals(0, list.getCountByName(name));

        HabitCompletion a = new HabitCompletion(new Date(), name);
        list.add(a);
        assertEquals(1, list.getCountByName(name));

        HabitCompletion b = new HabitCompletion("January 1, 2016", name);
        list.add(a);
        assertEquals(2, list.getCountByName(name));
    }

    public void testGetListByName() {
        String name = "Clean dishes";
        HabitCompletionList completionList = new HabitCompletionList();
        HabitCompletion a = new HabitCompletion(new Date(), name);
        completionList.add(a);
        HabitCompletion b = new HabitCompletion("January 1, 2016", name);
        completionList.add(b);

        List<Habit> list = completionList.getListByName(name);
        assertTrue(list.contains(a));
        assertTrue(list.contains(b));
    }

    public void testRemoveCompletion() {
        String name = "Clean dishes";
        HabitCompletionList completionList = new HabitCompletionList();
        HabitCompletion a = new HabitCompletion(new Date(), name);
        completionList.add(a);
        HabitCompletion b = new HabitCompletion("January 1, 2016", name);
        completionList.add(b);

        List<Habit> list = completionList.getListByName(name);
        assertTrue(list.contains(a));
        assertTrue(list.contains(b));

        completionList.remove(name, 0);
        list = completionList.getListByName(name);
        assertEquals(b, list.get(0));
        assertEquals(1, list.size());
    }
}
