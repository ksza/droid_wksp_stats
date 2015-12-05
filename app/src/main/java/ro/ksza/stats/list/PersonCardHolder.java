package ro.ksza.stats.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import ro.ksza.stats.R;
import ro.ksza.stats.model.Person;

public class PersonCardHolder extends RecyclerView.ViewHolder {

    private TextView nameLabel;
    private TextView ageLabel;

    public PersonCardHolder(View itemView) {
        super(itemView);

        nameLabel = (TextView) itemView.findViewById(R.id.name_label);
        ageLabel = (TextView) itemView.findViewById(R.id.age_label);
    }

    public void map(final Person item, final int position) {
        nameLabel.setText(item.getName());
        ageLabel.setText("" + item.getAge());
    }
}
