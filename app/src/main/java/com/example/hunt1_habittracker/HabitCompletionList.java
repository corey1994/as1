package com.example.hunt1_habittracker;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by Corey on 2016-10-03.
 */

public class HabitCompletionList {
    private Hashtable<String, HabitList> table;

    public HabitCompletionList() {
        table = new Hashtable<String, HabitList>();
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
        return table.get(name).getList();
    }

    public void remove(String name, int i) {
        table.get(name).removeByIndex(i);
    }
}
