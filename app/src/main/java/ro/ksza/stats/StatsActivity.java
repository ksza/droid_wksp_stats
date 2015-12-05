package ro.ksza.stats;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import ro.ksza.stats.list.MoviesAdapter;
import ro.ksza.stats.list.StatsFragment;
import ro.ksza.stats.model.Person;
import ro.ksza.stats.model.StatsDao;

public class StatsActivity extends AppCompatActivity {

    private StatsFragment fragment;
    private StatsDao statsDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        statsDao = new StatsDao(this);

        fragment = (StatsFragment) getFragmentManager().findFragmentById(R.id.stats_fragment);

        List<Person> personList = statsDao.readAll();
        fragment.dataReady(personList);
    }
}
