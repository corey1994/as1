package com.example.hunt1_habittracker;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by Corey on 2016-10-03.
 */

public class HabitCompletionList {
    private Hashtable<String, HabitList> table;
    private List<Listener> listeners;

    public HabitCompletionList() {
        table = new Hashtable<String, HabitList>();
        listeners = new ArrayList<Listener>();
    }

    public void add(HabitCompletion a) {
        String name = a.getName();
        if (table.containsKey(name)) {
            table.get(name).add(a);
        }
        else {
            HabitList newList = new HabitList();
            newList.add(a);
            table.put(name, newList);
        }
        notifyListeners();
    }

    public int getCountByName(String name) {
        if (table.containsKey(name)) {
            return table.get(name).getCount();
        }
        else {
            return 0;
        }
    }


    public List<Habit> getListByName(String name) {
        if (table.containsKey(name)) {
            return table.get(name).getList();
        }
        else {
            return new ArrayList<Habit>();
        }
    }

    public Hashtable<String, HabitList> getHashtable() {
        return table;
    }

    public void remove(String name, int i) {
        table.get(name).removeByIndex(i);
        notifyListeners();
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
