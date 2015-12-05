package ro.ksza.stats;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import ro.ksza.stats.list.MoviesAdapter;
import ro.ksza.stats.model.Person;
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

        setUpSwipeToDismiss();
    }

    private void setUpSwipeToDismiss() {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {

                final int position = viewHolder.getAdapterPosition();
                final Person item = adapter.getItemAt(position);

                if(statsDao.deleteById(item.getId()) != 0) {
                    adapter.removeItemAt(position);
                }
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
}
