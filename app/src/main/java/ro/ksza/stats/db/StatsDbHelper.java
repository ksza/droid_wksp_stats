package ro.ksza.stats.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ro.ksza.stats.db.EntryContract;

public class StatsDbHelper extends SQLiteOpenHelper {

    private static final String TEXT_TYPE = " TEXT";
    private static final String NUMBER_TYPE = " NUMBER";
    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + EntryContract.Entry.TABLE_NAME + " (" +
                    EntryContract.Entry._ID + " INTEGER PRIMARY KEY," +
                    EntryContract.Entry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
                    EntryContract.Entry.COLUMN_NAME_PERSON_NAME + TEXT_TYPE + COMMA_SEP +
                    EntryContract.Entry.COLUMN_NAME_PERSON_AGE + NUMBER_TYPE +
            " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + EntryContract.Entry.TABLE_NAME;

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "PeopleStats.db";

    public StatsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}