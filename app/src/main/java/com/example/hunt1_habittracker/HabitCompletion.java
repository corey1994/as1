package com.example.hunt1_habittracker;


import java.util.Date;

/**
 * Created by Corey on 2016-10-03.
 *
 * This is a subclass of habit.
 * A completion is a habit that has already been dealt with by the user.
 * So a HabitCompletion is-a Habit.
 * The only part of Habit that is not needed here is the daysToComplete list.
 */


public class HabitCompletion extends Habit {

    public HabitCompletion(Date date, String name) {
        super(date, name);
    }

    @Override
    public String toString() {
        return this.name+"\n"+getDateString();
    }
}
