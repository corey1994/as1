package com.example.hunt1_habittracker;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by Corey on 2016-10-01.
 */

public class TrackerControllerTest extends ActivityInstrumentationTestCase2 {
    public TrackerControllerTest() {
        super(HabitTrackerMainActivity.class);
    }

    public void testGetHabitList() {
        TrackerController tc = new TrackerController();

        HabitList habitList = tc.getHabitList();
        assertTrue(habitList != null);

        assertTrue(habitList.getList().size() == 0);


    }
}
