package com.example.hunt1_habittracker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Corey on 2016-09-29.
 */

public class HabitList {
    private List<Habit> habits;
    private ArrayList<Listener> listeners;

    public HabitList() {
        habits = new ArrayList<Habit>();
        listeners = new ArrayList<Listener>();
    }

    public void addHabit(Habit habit) {
        habits.add(habit);
    }

    public int getCount() {
        return habits.size();
    }

    //TODO: sort habits by date
    public List<Habit> getHabits() {
        return habits;
    }

    //TODO: filter habits by day
    public List<Habit> getHabitsByDay() {
        return habits;
    }

    public void notifyListeners() {
        for (Listener listener : listeners) {
            listener.update();
        }
    }

    public void addListener(Listener l) {
        this.listeners.add(l);
    }

    public void removeListener(Listener l) {
        //TODO: implement removeListener
    }
}
