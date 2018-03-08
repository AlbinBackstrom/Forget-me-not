package com.example.albin.forgetmenot.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.example.albin.forgetmenot.database.DBAdapterHelper.DATABASE_NAME;
import static com.example.albin.forgetmenot.database.DBAdapterHelper.DATABASE_VERSION;

/**
 * Created by albin on 11/11/16.
 */

public class ReminderDbControlller {
    private static final String TAG = ReminderDbControlller.class.getSimpleName();


    /**
     * Om det inte fungerar:
     * Dubbelchecka alla namn på tabellerna
     * Logcat
     * Ta bort extends till DBapapter
     */

    //Table Reminder
    public static final String TABLE_NAME_TR = "Reminder";

    public static final String COLUMN_NAME_ID_TR = "_id";
    public static final String COLUMN_NAME_TITLE_TR = "Title";
    public static final String COLUMN_NAME_CONTENT_TR = "Content";
    public static final String COLUMN_NAME_DATE_TR = "Date";
    public static final String COLUMN_NAME_TIME_TR = "Time";
    public static final String COLUMN_NAME_DONE_TR = "Done";

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDB;
    private final Context mCtx;


    private static class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }

    /**
     * Constructor - takes the context to allow the database to be
     * opened/created
     *
     * @param ctx the Context within which to work
     */
    public ReminderDbControlller(Context ctx) {
        this.mCtx = ctx;
    }

    /**
     * Open the reminder database. If it cannot be opened, try to create a new
     * instance of the database. If it cannot be created, throw an exception to
     * signal the failure
     *
     * @return this (self reference, allowing this to be chained in an
     * initialization call)
     * @throws SQLException if the database could be neither opened or created
     */
    public ReminderDbControlller open() { //throws SQLException
        try {
            this.mDbHelper = new DatabaseHelper(this.mCtx);
            mDB = mDbHelper.getWritableDatabase(); //this.mDbHelper.getWritableDatabase()

        } catch (SQLException e) {
            Log.d(TAG, "fel: " + e.getMessage());
            Log.d(TAG, "fel: " + e);
        }
        return this;
    }

    /**
     * close return type: void
     */
    public void close() {
        this.mDbHelper.close();
    }


    //Nedan hamnar alla statementes för CRUD


    //Insert
    public boolean InsertNewReminder(ReminderModel model) {
        Log.d(TAG, " InsertNewTimer metoden ");
        boolean InsertNewReminderSuccess = false;
        int done = 0;
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_TITLE_TR, model.title);
        values.put(COLUMN_NAME_CONTENT_TR, model.content);
        values.put(COLUMN_NAME_DATE_TR, model.date);
        values.put(COLUMN_NAME_TIME_TR, model.time);
        values.put(COLUMN_NAME_DONE_TR, model.done);
        InsertNewReminderSuccess = mDB.insert(TABLE_NAME_TR, null, values) > 0;
        Log.d(TAG, " InseNewREMSuccBool " + String.valueOf(InsertNewReminderSuccess) + values);
        close();
        return InsertNewReminderSuccess;

    }

    //read
    public Cursor GetAllReminders() {
        open();
        String SQLiteGetAllReminder = "SELECT * FROM " + TABLE_NAME_TR;
        return mDB.rawQuery(SQLiteGetAllReminder, null);
    }



}
