package ro.ksza.stats.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ro.ksza.stats.db.EntryContract;
import ro.ksza.stats.db.StatsDbHelper;

/**
 * Created by karoly.szanto on 05/12/15.
 */
public class StatsDao {

    private static final String TAG = "StatsDao";

    private final StatsDbHelper dbHelper;

    public StatsDao(final Context context) {
        dbHelper = new StatsDbHelper(context);
    }

    public synchronized long writePerson(final Person person) {

        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(EntryContract.Entry.COLUMN_NAME_ENTRY_ID, 1);
        values.put(EntryContract.Entry.COLUMN_NAME_PERSON_NAME, person.getName());
        values.put(EntryContract.Entry.COLUMN_NAME_PERSON_AGE, person.getAge());

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                EntryContract.Entry.TABLE_NAME,
                null,
                values);

        Log.d(TAG, "Successfully added to DB: " + person.toString());

        return newRowId;
    }

    public synchronized List<Person> readAll() {

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                EntryContract.Entry._ID,
                EntryContract.Entry.COLUMN_NAME_PERSON_NAME,
                EntryContract.Entry.COLUMN_NAME_PERSON_AGE,
        };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                EntryContract.Entry.COLUMN_NAME_PERSON_NAME + " ASC";

        Cursor cursor = db.query(
                EntryContract.Entry.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                "",                                       // The columns for the WHERE clause
                new String[] {},                          // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        final List<Person> result = new ArrayList<>();

        while(cursor.moveToNext()) {
            final String name = cursor.getString(
                    cursor.getColumnIndexOrThrow(EntryContract.Entry.COLUMN_NAME_PERSON_NAME)
            );
            final int age = cursor.getInt(
                    cursor.getColumnIndexOrThrow(EntryContract.Entry.COLUMN_NAME_PERSON_AGE)
            );
            final long id = cursor.getLong(
                    cursor.getColumnIndexOrThrow(EntryContract.Entry._ID)
            );

            final Person person = new Person(name, age, id);
            result.add(person);

            Log.d(TAG, "Read person: " + person);
        }

        return result;
    }

    public long deleteById(Long id) {
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.delete(
                EntryContract.Entry.TABLE_NAME,
                EntryContract.Entry._ID + " = ?",
                new String[]{id.toString()}
        );
    }
}
