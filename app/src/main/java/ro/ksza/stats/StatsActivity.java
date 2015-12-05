package ro.ksza.stats;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import ro.ksza.stats.list.MoviesAdapter;
import ro.ksza.stats.model.StatsDao;

public class StatsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MoviesAdapter adapter;
    private LinearLayoutManager layoutManager;

    private StatsDao statsDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        setUpRecycler();

        statsDao = new StatsDao(this);
        adapter.setItems(statsDao.readAll());
    }

    private void setUpRecycler() {
        recyclerView = (RecyclerView) findViewById(R.id.people_list);

        adapter = new MoviesAdapter(this);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    
}
