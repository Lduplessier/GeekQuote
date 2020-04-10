package fr.myschool.geekquote.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import fr.myschool.geekquote.R;
import fr.myschool.geekquote.model.Quote;

public class QuoteListAdapter extends ArrayAdapter<Quote> {

    public QuoteListAdapter(@NonNull Context context, @NonNull List<Quote> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Quote q = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_quote_layout, parent, false);
        }

        TextView tv_quote = convertView.findViewById(R.id.tv_quote);
        TextView rating = convertView.findViewById(R.id.rating);
        TextView tv_creation_date = convertView.findViewById(R.id.tv_creation_date);
        ImageView rating_star_svg = convertView.findViewById(R.id.rating_star_svg);
        tv_quote.setText(q.getStrQuote());
        rating.setText(q.getRating().toString());
        tv_creation_date.setText(java.text.DateFormat.getDateTimeInstance().format(q.getCreationDate()));


        if (q.getRating() == 0) {
            rating.setVisibility(View.INVISIBLE);
            rating_star_svg.setVisibility(View.INVISIBLE);
        } else {
            rating.setVisibility(View.VISIBLE);
            rating_star_svg.setVisibility(View.VISIBLE);
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}