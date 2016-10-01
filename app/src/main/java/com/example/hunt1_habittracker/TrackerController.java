package com.example.hunt1_habittracker;

/**
 * Created by Corey on 2016-10-01.
 */


// This is the main controller class.
public class TrackerController {
    //Lazy singleton, as from the StudentPicker video.
    private HabitList habitList;

    public TrackerController() {
        habitList = null;
    }

    public HabitList getHabitList() {
        if(habitList != null) {
            return habitList;
        }
        else {
            habitList = new HabitList();
            return habitList;
        }
    }
}
