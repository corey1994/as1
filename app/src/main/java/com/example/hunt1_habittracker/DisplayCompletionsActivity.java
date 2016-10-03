package com.example.hunt1_habittracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class DisplayCompletionsActivity extends AppCompatActivity {

    private ArrayAdapter<Habit> completionAdapter;
    private List<Habit> completionList;
    private String completionName;
    private String completionCountString;
    private Listener completionListViewListener;
    private TrackerController tc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_completions);

        final TextView completionCountTextView = (TextView) findViewById(R.id.completionCountTextView);
        ListView completionListView = (ListView) findViewById(R.id.completionListView);
        registerForContextMenu(completionListView);

        //Get habit name we are showing completions for
        Intent intent = getIntent();
        completionName = intent.getStringExtra(HabitTrackerMainActivity.EXTRA_COMPLETION_NAME);

        tc = new TrackerController();
        completionList = tc.getHabitCompletionList().getListByName(completionName);


        completionCountString = completionList.size()+" completions for "+completionName;
        completionCountTextView.setText(completionCountString);

        completionAdapter = new ArrayAdapter<Habit>(this, R.layout.habit_list_item, completionList);
        completionListView.setAdapter(completionAdapter);

        //Create an object of an abstract class, using the listener interface
        //From studentPicker videos
        completionListViewListener = new Listener() {
            @Override
            public void update() {
                completionCountString = completionList.size()+" completions for "+completionName;
                completionCountTextView.setText(completionCountString);

                //Apparently completionList is a reference to my private array?  Not sure why/how
                //completionList.clear();
                //completionList.addAll(tc.getHabitCompletionList().getListByName(completionName));

                completionAdapter.notifyDataSetChanged();
            }
        };

        tc.getHabitCompletionList().addListener(completionListViewListener);
    }

    //Code is directly from https://developer.android.com/guide/topics/ui/menus.html#FloatingContextMenu
    //Besides of course using my own menu resource id
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.completion_context_menu, menu);
    }

    //Again, code structure taken directly from android docs
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.menu_item_delete_completion:
                tc.removeCompletion(completionName, info.position);
            default:
                return super.onContextItemSelected(item);
        }
    }

    //@Override
    //protected void onResume() {
    //    super.onResume();
    //
    //   completionListViewListener.update();
    //}

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tc.getHabitCompletionList().removeListener(completionListViewListener);
    }
}
