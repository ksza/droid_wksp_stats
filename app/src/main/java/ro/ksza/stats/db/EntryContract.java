package ro.ksza.stats.db;

import android.provider.BaseColumns;

public final class EntryContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public EntryContract() {}

    /* Inner class that defines the table contents */
    public static abstract class Entry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME_PERSON_NAME = "title";
        public static final String COLUMN_NAME_PERSON_AGE = "age";
    }
}