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
import java.util.List;

/**
 * Created by Corey on 2016-10-01.
 */


// This is the main controller class.
public class TrackerController {
    private static String FILENAME = "tracker.json";
    //Lazy singleton, as from the StudentPicker video.
    private static HabitList habitList;
    private static Context applicationContext;

    public TrackerController(Context context) {
        if (habitList == null) {
            habitList = new HabitList();
        }
        if (applicationContext == null) {
            applicationContext = context;
        }
    }

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
        habitList.add(newHabit);
        saveInFile();
    }

    public void removeHabitByIndex(int index) {
        habitList.removeByIndex(index);
        saveInFile();
    }

    public void loadFromFile() {
        //TODO: make this work!
        try {
            FileInputStream fis = applicationContext.openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            // Code from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            Type listType = new TypeToken<ArrayList<Habit>>() {
            }.getType();

            List<Habit> habits = gson.fromJson(in, listType);
            this.habitList = new HabitList(habits);

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
            FileOutputStream fos = applicationContext.openFileOutput(FILENAME,0);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(habitList.getList(), out);
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
