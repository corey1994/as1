package com.example.hunt1_habittracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class HabitTrackerMainActivity extends AppCompatActivity {
    private ArrayAdapter<Habit> habitAdapter;
    private ListView habitListView;
    private List<Habit> listOfHabits;
    TrackerController tc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_tracker_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tc = new TrackerController();
        listOfHabits = new ArrayList<Habit>();
        habitListView = (ListView) findViewById(R.id.habit_ListView);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //Prevent the fab from hiding behind the ListView
        fab.bringToFront();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddHabitActivity.class);
                startActivity(intent);
            }
        });

        habitAdapter = new ArrayAdapter<Habit>(this, R.layout.habit_list_item, listOfHabits);
        habitListView.setAdapter(habitAdapter);

        //Create an object of an abstract class, using the listener interface
        //From studentPicker videos
        Listener habitListViewListener = new Listener() {
            @Override
            public void update() {
                listOfHabits.clear();
                listOfHabits.addAll(tc.getHabitList().getHabits());
                habitAdapter.notifyDataSetChanged();
            }
        };

        tc.getHabitList().addListener(habitListViewListener);

    }

    protected void onResume() {
        super.onResume();

        //I recognize there is probably a better way to do this,
        //but for now we just have one or two listeners, so the performance
        //won't be that bad.
        tc.getHabitList().notifyListeners();
    }

}
