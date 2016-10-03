package com.example.hunt1_habittracker;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Corey on 2016-10-01.
 */


// This is the main controller class.
public class TrackerController {
    private static String HABITFILENAME = "tracker_habits.json";
    private static String COMPLETIONFILENAME = "tracker_completions.json";
    //Lazy singleton, as from the StudentPicker video.
    private static HabitList habitList;
    private static HabitCompletionList habitCompletionList;
    private static Context applicationContext;

    public TrackerController(Context context) {
        if (habitList == null) {
            habitList = new HabitList();
        }
        if (habitCompletionList == null) {
            habitCompletionList = new HabitCompletionList();
        }
        if (applicationContext == null) {
            applicationContext = context;
        }
    }

    public TrackerController() {
        if (habitList == null) {
            habitList = new HabitList();
        }
        if (habitCompletionList == null) {
            habitCompletionList = new HabitCompletionList();
        }
    }

    public HabitList getHabitList() {
            return habitList;
    }

    public HabitCompletionList getHabitCompletionList() {
        return habitCompletionList;
    }

    public void newHabit(String date, String name, List<String> daysToComplete) {
        Habit newHabit = new Habit(date, name, daysToComplete);
        habitList.add(newHabit);
        saveInFile();
    }

    public void removeHabitByIndex(int index) {
        habitList.removeByIndex(index);
        saveInFile();
    }

    public void newHabitCompletion(Date date, String name) {
        HabitCompletion completion = new HabitCompletion(date, name);
        habitCompletionList.add(completion);
    }

    public void removeCompletion(String name, int index) {
        habitCompletionList.remove(name, index);
    }

    public void loadFromFile() {
        try {
            FileInputStream fis = applicationContext.openFileInput(HABITFILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            // Code from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            Type listType = new TypeToken<ArrayList<Habit>>() {
            }.getType();

            List<Habit> habits = gson.fromJson(in, listType);
            this.habitList = new HabitList(habits);


            //Load habit completions
            fis = applicationContext.openFileInput(COMPLETIONFILENAME);
            in = new BufferedReader(new InputStreamReader(fis));
            habitCompletionList = gson.fromJson(in, HabitCompletionList.class);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            List<Habit> habits = new ArrayList<Habit>();
            this.habitList = new HabitList(habits);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }

    }

    public void saveInFile() {
        try {
            FileOutputStream fos = applicationContext.openFileOutput(HABITFILENAME,0);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(habitList.getList(), out);
            out.flush();

            fos.close();

            fos = applicationContext.openFileOutput(COMPLETIONFILENAME,0);

            out = new BufferedWriter(new OutputStreamWriter(fos));

            gson.toJson(habitCompletionList, out);
            out.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }
}
