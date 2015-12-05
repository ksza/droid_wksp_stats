package ro.ksza.stats.list;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import jp.wasabeef.recyclerview.animators.FlipInLeftYAnimator;
import ro.ksza.stats.R;
import ro.ksza.stats.model.Person;

/**
 * Created by karoly.szanto on 05/12/15.
 */
public class StatsFragment extends Fragment {

    private RecyclerView recyclerView;
    private MoviesAdapter adapter;
    private LinearLayoutManager layoutManager;

    public void dataReady(List<Person> personList) {
        adapter.setItems(personList);
    }

    public void itemReady(Person person) {
        adapter.insertItemAtEnd(person);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stats, container, false);

        setUpRecycler(view, inflater.getContext());

        return view;
    }

    private void setUpRecycler(View view, Context context) {
        recyclerView = (RecyclerView) view.findViewById(R.id.people_list);

        recyclerView.setItemAnimator(new FlipInLeftYAnimator());

        adapter = new MoviesAdapter(context);
        layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
