package ro.ksza.stats;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import ro.ksza.stats.list.StatsFragment;
import ro.ksza.stats.model.Person;
import ro.ksza.stats.model.StatsDao;

public class DataCollectionActivity extends AppCompatActivity {

    private static final String TAG = "DataCollectionActivity";
    private EditText nameField, ageField;
    private Button saveButton;
    private FloatingActionButton fab;

    private StatsFragment fragment;

    private CoordinatorLayout coordinatorLayout;

    private StatsDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_collection);

        dao = new StatsDao(this);

        nameField = (EditText) findViewById(R.id.name_view);
        ageField = (EditText) findViewById(R.id.age_view);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coord);
        fab = (FloatingActionButton) findViewById(R.id.stats_button);

        fragment = (StatsFragment) getFragmentManager().findFragmentById(R.id.stats_fragment);

        findViewById(R.id.add_to_db).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                doOnClick();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStatsActivity();
            }
        });

        fragment.dataReady(dao.readAll());
    }

    private void startStatsActivity() {
        final Intent statsIntent = new Intent(this, StatsActivity.class);
        startActivity(statsIntent);
    }

    private void doOnClick() {
        hideKeyboard();

        final String rawName = nameField.getText().toString().trim();
        final int rawAge = Integer.parseInt(ageField.getText().toString().trim());

        Person person = new Person(rawName, rawAge);
        final long generatedId = dao.writePerson(person);

        fragment.itemReady(person);

        confirmAndUndo(generatedId);
    }

    private void confirmAndUndo(final long generatedId) {
        Snackbar snackbar = Snackbar.make(coordinatorLayout, "Successfully saved: " + generatedId, Snackbar.LENGTH_LONG);
        snackbar.setAction("Undo", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao.deleteById(generatedId);
            }
        });
        snackbar.show();
    }

    private void hideKeyboard() {
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
