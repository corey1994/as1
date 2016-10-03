package com.example.hunt1_habittracker;

/**
 * Created by Corey on 2016-10-03.
 *
 * The intention is that this class will hold all completions of a particular habit.
 * Thus, it does not accept completions with a different name.
 */

public class HabitCompletionList extends HabitList {
    private String name;

    public HabitCompletionList(String name) {
        super();
        this.name = name;
    }

    @Override
    public void add(Habit completion) {
        if (completion.getName() == this.name) {
            this.habits.add(completion);
            notifyListeners();
        }
        else {
            //TODO throw exception
        }
    }
}
