package com.example.hunt1_habittracker;

import java.util.List;

/**
 * Created by Corey on 2016-10-01.
 */


// This is the main controller class.
public class TrackerController {
    //Lazy singleton, as from the StudentPicker video.
    private static HabitList habitList;

    public TrackerController() {
        if (habitList == null) {
            habitList = new HabitList();
        }
    }

    public HabitList getHabitList() {
            return habitList;
    }

    public void newHabit(String date, String name, List<String> daysToComplete) {
        Habit newHabit = new Habit(date, name, daysToComplete);
        habitList.addHabit(newHabit);
    }

    public void saveToFile() {
        //TODO: implement this to make app persistent.
    }
}
