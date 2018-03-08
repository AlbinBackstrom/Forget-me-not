package com.example.albin.forgetmenot.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.example.albin.forgetmenot.database.ReminderDbControlller.COLUMN_NAME_CONTENT_TR;
import static com.example.albin.forgetmenot.database.ReminderDbControlller.COLUMN_NAME_DATE_TR;
import static com.example.albin.forgetmenot.database.ReminderDbControlller.COLUMN_NAME_DONE_TR;
import static com.example.albin.forgetmenot.database.ReminderDbControlller.COLUMN_NAME_ID_TR;
import static com.example.albin.forgetmenot.database.ReminderDbControlller.COLUMN_NAME_TIME_TR;
import static com.example.albin.forgetmenot.database.ReminderDbControlller.COLUMN_NAME_TITLE_TR;
import static com.example.albin.forgetmenot.database.ReminderDbControlller.TABLE_NAME_TR;
import static com.example.albin.forgetmenot.database.TimerDbController.COLUMN_NAME_DONE_TT;
import static com.example.albin.forgetmenot.database.TimerDbController.COLUMN_NAME_ID_TT;
import static com.example.albin.forgetmenot.database.TimerDbController.COLUMN_NAME_TIME_TT;
import static com.example.albin.forgetmenot.database.TimerDbController.COLUMN_NAME_TITLE_TT;
import static com.example.albin.forgetmenot.database.TimerDbController.TABLE_NAME_TIMER;

/**
 * Created by albin on 11/11/16.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = DatabaseHelper.class.getSimpleName();

    public static final String DATABASE_NAME = "forgetmenot.db";
    public static final int DATABASE_VERSION = 1;

    /**
     * Contants
     */
    public static final String TEXT_TYPE = " TEXT";
    public static final String INT_TYPE = " INTEGER";
    public static final String COMMA_SEP = ",";
    public static final String PK_INT_AUTO_INC_NO_NULL = " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,";

    /**
     * SQL statement for create Reminder table
     */

    private static final String CREATE_TABLE_REMINDER_SQL_STATEMENT =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_TR + " (" +
                    COLUMN_NAME_ID_TR + PK_INT_AUTO_INC_NO_NULL +
                    COLUMN_NAME_TITLE_TR + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NAME_CONTENT_TR + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NAME_DATE_TR + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NAME_TIME_TR + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NAME_DONE_TR + INT_TYPE + " )";


    //SQL statement for create Timer table
    public static final String CREATE_TABLE_TIMER_SQL_STATEMENT =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_TIMER + " (" +
                    COLUMN_NAME_ID_TT + PK_INT_AUTO_INC_NO_NULL +
                    COLUMN_NAME_TITLE_TT + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NAME_TIME_TT + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NAME_DONE_TT + INT_TYPE + " )";

    public static DatabaseHelper sInstance;

    public static synchronized DatabaseHelper getInstance(Context context) {
        Log.d(TAG, "Nya dbHelper get instance");
        if (sInstance == null) {
            sInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG, "Nya dbHelper construktor");

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "Nya dbHelper onCreate table ");
        Log.d(TAG, CREATE_TABLE_TIMER_SQL_STATEMENT);
        Log.d(TAG, CREATE_TABLE_REMINDER_SQL_STATEMENT);

        db.execSQL(CREATE_TABLE_TIMER_SQL_STATEMENT);
        db.execSQL(CREATE_TABLE_REMINDER_SQL_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }




}
