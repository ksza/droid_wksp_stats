package ro.ksza.stats;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import ro.ksza.stats.list.MoviesAdapter;

public class StatsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private MoviesAdapter adapter;

    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        setUpRecycler();
    }

    private void setUpRecycler() {
        recyclerView = (RecyclerView) findViewById(R.id.people_list);

        adapter = new MoviesAdapter(this);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
