package com.example.albin.forgetmenot;

import android.app.DialogFragment;
import android.content.Context;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.albin.forgetmenot.database.ReminderDbControlller;
import com.example.albin.forgetmenot.database.ReminderModel;
import com.example.albin.forgetmenot.fragment.DatePickerFragment;
import com.example.albin.forgetmenot.fragment.TimePickerFragment;
//import android.support.v4.app.DialogFragment;


//Här lägger man till nya påminelser
public class AddNewReminder extends AppCompatActivity {


    private static final String TAG = AddNewReminder.class.getSimpleName();
    android.support.design.widget.FloatingActionButton btnSaveReminder;
    EditText etDate;
    Calendar myCalendar;
    TextView tvReminderDate, tvReminderTime;
    EditText etReminderTitle, etReminderContent;
    String title, content, date, time;


    //Dagens datum ska hämtas och fylla textview
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_reminder);

        initToolbar();
        fabSaveNewReminder();

    }

    /**
     * Method that is
     */
    private void fabSaveNewReminder() {
        btnSaveReminder = (FloatingActionButton) findViewById(R.id.fab_save_reminder);
        btnSaveReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etReminderTitle = (EditText) findViewById(R.id.etReminderTitle);
                etReminderContent = (EditText) findViewById(R.id.etReminderContent);
                tvReminderDate = (TextView) findViewById(R.id.tvReminderDate);
                tvReminderTime = (TextView) findViewById(R.id.tvReminderTime);

                ReminderModel model = new ReminderModel();

                model.title = etReminderTitle.getText().toString();
                model.content = etReminderContent.getText().toString();
                model.date = tvReminderDate.getText().toString();
                model.time = tvReminderTime.getText().toString();
                final Context context = view.getContext();

                Log.d(TAG, model.title + model.content + model.date + model.time);
                boolean insertSuccess = new ReminderDbControlller(context).InsertNewReminder(model);
                if (insertSuccess) {
                    Log.d(TAG, "Inserted success i main från alert dialog");

                } else {
                    Log.d(TAG, "Inserted FAILED i main från alert dialog");
                }
                Log.d(TAG, "btn save clicked");
                finish();
            }
        });
    }

    /**
     * A layout, when clicked it calls the class
     * showDatePickerFragment that is creating and
     * showing a Date Picker Fragement
     *
     * @param view
     */
    public void dateLayoutClick(View view) {
        Log.d(TAG, "dateLayoutClick");
        showDatePickerFragment(view);
    }

    private void showDatePickerFragment(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), TAG);
    }

    /**
     * A layout, when clicked it calls the class
     * showTimePickerFragment that is creating and
     * showing a Time Picker Fragement
     *
     * @param view
     */
    public void timeLayoutClick(View view) {
        Log.d(TAG, "timeLayoutClick");
        showTimePickerFragment(view);
    }

    private void showTimePickerFragment(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), TAG);
    }


    /**
     * The toolbar for the activity. Refactored from the onCreateh method,
     * It finds its corresponding toolbar in the layout/xml file.
     * The last line code R.drawable.ic_close_white_18dp is setting
     * the standard back arrow to a close/ X and closes/finishes the
     * current activity instead.
     */
    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_new_reminder);
        if (toolbar == null) return;
        toolbar.setTitle("Ny påminelse");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white_18dp);
    }

    /**
     * Used for the Toolbar and is called whes the user presses the back arrow?
     * and in this case the close/X. The method below will finish the current
     * activity and go back to the last used.
     *
     * @return
     */
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return false;
    }

}
