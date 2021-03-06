package com.example.hunt1_habittracker;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HabitTrackerMainActivity extends AppCompatActivity {
    private ArrayAdapter<Habit> habitAdapter;
    private ListView habitListView;
    private List<Habit> listOfHabits;
    TrackerController tc;

    public static final String EXTRA_COMPLETION_NAME = "com.example.hunt1_habittracker.completion_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_tracker_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tc = new TrackerController(getApplicationContext());
        tc.loadFromFile();
        listOfHabits = new ArrayList<Habit>();
        habitListView = (ListView) findViewById(R.id.habit_ListView);
        registerForContextMenu(habitListView);

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
                listOfHabits.addAll(tc.getHabitList().getList());
                habitAdapter.notifyDataSetChanged();
            }
        };

        tc.getHabitList().addListener(habitListViewListener);
        tc.getHabitList().notifyListeners();  //Refresh the view to show habits loaded from file

    }

    //Code is directly from https://developer.android.com/guide/topics/ui/menus.html#FloatingContextMenu
    //Besides of course using my own menu resource id
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.habit_context_menu, menu);
    }

    //Again, code structure taken directly from android docs
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.menu_item_complete_habit:
                tc.newHabitCompletion(new Date(), tc.getHabitList().getList().get(info.position).getName());
                return true;
            case R.id.menu_item_show_completions:
                Intent intent = new Intent(this, DisplayCompletionsActivity.class);
                String completionName = tc.getHabitList().getList().get(info.position).getName();
                intent.putExtra(EXTRA_COMPLETION_NAME, completionName);
                startActivity(intent);
                return true;
            case R.id.menu_item_delete_habit:
                tc.removeHabitByIndex(info.position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
