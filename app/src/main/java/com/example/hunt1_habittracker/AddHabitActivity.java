package com.example.hunt1_habittracker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AddHabitActivity extends Activity {
    TrackerController tc;
    TextView newHabitName;
    TextView newHabitDate;
    List<CheckBox> checkBoxList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);

        tc = new TrackerController();

        newHabitName = (TextView) findViewById(R.id.newHabitNameEditText);
        newHabitDate = (TextView) findViewById(R.id.newHabitDateEditText);

        //This is going to be a list of our checkboxes, so we can loop through them later.
        checkBoxList = new ArrayList<CheckBox>();
        checkBoxList.add((CheckBox) findViewById(R.id.sundayCheckBox));
        checkBoxList.add((CheckBox) findViewById(R.id.mondayCheckBox));
        checkBoxList.add((CheckBox) findViewById(R.id.tuesdayCheckBox));
        checkBoxList.add((CheckBox) findViewById(R.id.wednesdayCheckBox));
        checkBoxList.add((CheckBox) findViewById(R.id.thursdayCheckBox));
        checkBoxList.add((CheckBox) findViewById(R.id.fridayCheckBox));
        checkBoxList.add((CheckBox) findViewById(R.id.saturdayCheckBox));

        Button saveButton = (Button) findViewById(R.id.saveHabitButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buildNewHabit();
                //I thought this closed the whole app.
                //Consulted http://stackoverflow.com/questions/4038479/android-go-back-to-previous-activity
                finish();
            }
        });
    }

    private void buildNewHabit() {
        //TODO: deal with exceptions
        String name = newHabitName.getText().toString();
        String date = newHabitDate.getText().toString();

        List<String> daysToComplete = new ArrayList<String>();
        for (CheckBox checkBox : checkBoxList) {
            if (checkBox.isChecked()) {
                daysToComplete.add(checkBox.getText().toString());
            }
        }

        tc.newHabit(date, name, daysToComplete);
    }
}
