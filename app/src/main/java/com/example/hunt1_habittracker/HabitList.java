package com.example.hunt1_habittracker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Corey on 2016-09-29.
 */

public class HabitList {
    protected List<Habit> habits;
    protected List<Listener> listeners;

    public HabitList() {
        habits = new ArrayList<Habit>();
        listeners = new ArrayList<>();
    }

    public HabitList(List<Habit> habits) {
        this.habits = habits;
        listeners = new ArrayList<>();
    }

    public void add(Habit habit) {
        habits.add(habit);
        notifyListeners();
    }

    public void removeByIndex(int index) {
        habits.remove(index);
        notifyListeners();
    }

    //Iterator code concept by Andreas_D
    //http://stackoverflow.com/questions/14231688/how-to-remove-element-from-arraylist-by-checking-its-value
    //NOTE: If two habits have the same name they will both be deleted.  Use other removeByIndex instead
    public void removeByName(String name) {
        List<Habit> deleteCandidates = new ArrayList<>();

        // Pass 1 - collect delete candidates
        for (Habit habit : habits) {
            if (habit.getName() == name) {
                deleteCandidates.add(habit);
            }
        }

        habits.removeAll(deleteCandidates);

        notifyListeners();
    }

    public int getCount() {
        return habits.size();
    }

    //TODO: sort habits by date
    public List<Habit> getList() {
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
        this.listeners.remove(l);
    }
}
