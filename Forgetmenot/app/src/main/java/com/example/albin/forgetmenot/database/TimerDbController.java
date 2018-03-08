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

public class TimerDbController {
    private static final String TAG = TimerDbController.class.getSimpleName();
    //Table Timer
    public static final String TABLE_NAME_TIMER = "Timer";

    public static final String COLUMN_NAME_ID_TT = "_id";
    public static final String COLUMN_NAME_TITLE_TT = "Title";
    public static final String COLUMN_NAME_TIME_TT = "Time";
    public static final String COLUMN_NAME_DONE_TT = "Done";

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase db;
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
    public TimerDbController(Context ctx) {
        this.mCtx = ctx;
    }

    /**
     * Open the database. If it cannot be opened, try to create a new
     * instance of the database. If it cannot be created, throw an exception to
     * signal the failure
     *
     * @return this (self reference, allowing this to be chained in an
     * initialization call)
     * @throws SQLException if the database could be neither opened or created
     */
    public TimerDbController open() throws SQLException {
        this.mDbHelper = new DatabaseHelper(this.mCtx);
        this.db = this.mDbHelper.getWritableDatabase();
        return this;
    }

    /**
     * close return type: void
     */
    public void close() {
        this.mDbHelper.close();
    }

    /**
     * Nedan är alla metoder för att CRUDa till
     * databasen och tabellen Timer.
     */


    /**
     * The Insert statement for a new timer
     *
     * @param model From the class TimerModel. A model/bean used for CRUD
     *              functions to the DB.
     *              model.title is the name/title of the Timer
     *              model.minutes is the number of minutes the user have set to
     *              use before the alarm/notification will go off.
     * @return InsertNewTimerSuccess
     * * InsertNewTimerSuccess is a boolean returned to the activity
     * and is used for checking for successfull insert. Normally the db.insert method
     * is returning -1 if fail and the newly inserted rowid for the entry.
     * <p>
     * Dubbelkolla ovanstånde, sätt logg/debugga och kolla returen
     * genom att skicka in felaktigt data eller något sånt
     * <p>
     * int done = 0 is  used as a boolean value for the timer as the SQLite Db dont
     * use boolean values. 0 is active/not finished. 1 is finished/done.
     * <p>
     * Can be used as this in the calling activity:
     * InsertNewTimerSuccess = TimerDbController.InsertNewTimer(params...)
     * if(InsertNewTimerSuccess){
     * <p><p>//do something. For example display snackbar/toast to notify user of successfull insert
     * }else{
     * <p><p>//do something else. Notify of failed insert.
     * }
     */


    public boolean InsertNewTimer(TimerModel model) {
        Log.d(TAG, " InsertNewTimer metoden ");
        boolean InsertNewTimerSuccess = false;
        int done = 0;
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_TITLE_TT, model.title);
        values.put(COLUMN_NAME_TIME_TT, model.minutes);
        values.put(COLUMN_NAME_DONE_TT, done);
        InsertNewTimerSuccess = db.insert(TABLE_NAME_TIMER, null, values) > 0;
        Log.d(TAG, " InseNewTimSuccBool " + String.valueOf(InsertNewTimerSuccess) + values);
        close();
        return InsertNewTimerSuccess;
    }


    public Cursor GetAllTimers() {
        open();
        String SQLGetAllTimers = " SELECT * FROM " + TABLE_NAME_TIMER + " WHERE " + COLUMN_NAME_DONE_TT + " = " + 0;
        Log.d(TAG, SQLGetAllTimers);
//        close();
        return db.rawQuery(SQLGetAllTimers, null);
    }


    public void FinishTimer(int id) {
        try {

            Log.d(TAG, "Finish timer  TimerDBController");
            String sId = String.valueOf(id);
            open();
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME_DONE_TT, 1);

            String whereClause = COLUMN_NAME_ID_TT + " = ? ";
            String[] whereArgs = new String[]{sId};

            //denna stämmer ej. Är ett försök till text represantion av db.update.
            String sql = "update " + TABLE_NAME_TIMER + " where " + whereClause + whereArgs + ":";
            Log.d(TAG, sql);
            db.update(
                    TABLE_NAME_TIMER,
                    values,
                    whereClause,
                    whereArgs
            );

        } catch (SQLException e) {
            Log.d(TAG, e.getMessage());
        }
    }

    //delete all timers
    public void DeleteAllTimers() {
//        String SQLDeleteAllTimer = "DELETE * FROM " + TABLE_NAME_TIMER;
        open();
        db.delete(TABLE_NAME_TIMER, null, null);
        close();

    }

    /**
     * The Read statement for all the timers
     *
     * @return A list of timer object.
     */
//    public List<TimerModel> GetAllTimers() {
//        List<TimerModel> timerModelList = new ArrayList<>();
//        open();
//
//        //om det inte funkar så select id as _id
//
//        String SQLGetAllTimers = " SELECT * FROM " + TABLE_NAME_TIMER;
//        Cursor c = db.rawQuery(SQLGetAllTimers, null);
//        try {
//            if (c.moveToFirst()) {
//                do {
//                    TimerModel model = new TimerModel();
//                    model.id = c.getInt(c.getColumnIndex(COLUMN_NAME_ID_TT));
//                    model.title = c.getString(c.getColumnIndex(COLUMN_NAME_TITLE_TT));
//                    model.minutes = c.getString(c.getColumnIndex(COLUMN_NAME_TIME_TT));
//                    model.done = c.getInt(c.getColumnIndex(COLUMN_NAME_DONE_TT));
//                    timerModelList.add(model);
//                } while (c.moveToNext());
//            }
//        } catch (Exception e) {
//            Log.d(TAG, e.getMessage());
//        }finally {
//            if (c != null && !c.isClosed()){
//                c.close();
//            }
//        }
//        return timerModelList;
//    }
}
