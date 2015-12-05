package ro.ksza.stats.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ro.ksza.stats.R;
import ro.ksza.stats.model.Person;

/**
 * Handles a collection of MovieItems
 */
public class MoviesAdapter extends RecyclerView.Adapter<PersonCardHolder> {

    private List<Person> items = new ArrayList<>();

    private final LayoutInflater inflater;

    public MoviesAdapter(final Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public PersonCardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = inflater.inflate(R.layout.person_item_layout, parent, false);
        return new PersonCardHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PersonCardHolder holder, int position) {
        holder.map(items.get(position), position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public Person getItemAt(final int position) {
        return items.get(position);
    }

    public void setItems(final List<Person> movies) {
        items = movies;
        notifyDataSetChanged();
    }


    public void insertItemAt(final Person item, final int position) {
        if(items.size() == 0) {
            items.add(item);
        } else {
            items.add(position, item);
        }
        notifyItemInserted(position);
    }

    public void insertItemAtEnd(final Person item) {
        this.insertItemAt(item, getItemCount());
    }

    public void removeItemAt(final int position) {
        items.remove(position);
        notifyItemRemoved(position);
    }
}
