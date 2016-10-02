package com.example.hunt1_habittracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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
                listOfHabits.addAll(tc.getHabitList().getHabits());
                habitAdapter.notifyDataSetChanged();
            }
        };

        tc.getHabitList().addListener(habitListViewListener);

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
            //TODO: add action for this item
            case R.id.menu_item_show_completions:
                return false;
            case R.id.menu_item_delete_habit:
                tc.removeHabitByIndex(info.position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
