package fr.myschool.geekquote.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.util.List;

import fr.myschool.geekquote.R;
import fr.myschool.geekquote.model.Quote;

public class QuoteListAdapter extends ArrayAdapter<Quote> {

    private Context context;
    public QuoteListAdapter(@NonNull Context context, @NonNull List<Quote> objects) {
        super(context, 0, objects);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Quote quote = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_quotes, parent, false);
        }

        TextView tv_item_quote_strquote = convertView.findViewById(R.id.tv_item_quote_strquote);
        TextView tv_item_quote_date = convertView.findViewById(R.id.tv_item_quote_date);

        tv_item_quote_strquote.setText(quote.getStrQuote());
        tv_item_quote_date.setText(DateFormat.getDateTimeInstance().format(quote.getCreationDate()));

        return convertView;
    }
}
