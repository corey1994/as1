package com.example.hunt1_habittracker;

import android.test.ActivityInstrumentationTestCase2;

import java.util.Date;

import static java.util.Arrays.asList;

/**
 * Created by Corey on 2016-09-29.
 */

public class HabitListTest extends ActivityInstrumentationTestCase2 {
    Habit habit;

    //Christoffer Hammarström http://stackoverflow.com/questions/1005073/initialization-of-an-arraylist-in-one-line
    public HabitListTest() {
        super(HabitTrackerMainActivity.class);
        habit = new Habit(new Date(), "Clean room", asList("Mon", "Tue", "Wed"));
    }

    // Tests addHabit, but also getCount
    public void testAddHabit() {
        HabitList list = new HabitList();
        int count = list.getCount();
        assertEquals(0, count);
        list.addHabit(habit);
        count = list.getCount();
        assertEquals(1, count);
        assertEquals(list.getHabits().get(0), habit);
    }

    boolean updated = false;
    public void testNotifyListeners() {
        HabitList list = new HabitList();
        Listener l = new Listener() {
            @Override
            public void update() {
                HabitListTest.this.updated = true;
            }
        };
        list.addListener(l);
        list.notifyListeners();
        assertTrue(this.updated);
    }


}
