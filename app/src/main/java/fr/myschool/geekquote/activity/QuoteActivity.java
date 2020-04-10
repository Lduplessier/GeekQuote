package fr.myschool.geekquote.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import fr.myschool.geekquote.R;
import fr.myschool.geekquote.model.Quote;

public class QuoteActivity extends Activity implements View.OnClickListener {

    private TextView tv_quote, tv_quote_creation_date;
    private RatingBar rb_quote_rating;
    private Button bt_ok, bt_cancel;
    private Quote quote;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);

        tv_quote = findViewById(R.id.tv_quote);
        tv_quote_creation_date = findViewById(R.id.tv_quote_creation_date);
        rb_quote_rating = findViewById(R.id.rb_quote_rating);
        bt_ok = findViewById(R.id.bt_ok);
        bt_cancel = findViewById(R.id.bt_cancel);

        bt_cancel.setOnClickListener(this);
        bt_ok.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            quote = (Quote) bundle.getSerializable("data");
            tv_quote.setText(quote.getStrQuote());
            tv_quote_creation_date.setText(java.text.DateFormat.getDateTimeInstance().format(quote.getCreationDate()));
            rb_quote_rating.setRating(quote.getRating());
        }
    }

    @Override
    public void onClick(View v) {
        if (v == bt_ok) {
            Float rating = rb_quote_rating.getRating();
            quote.setRating(rb_quote_rating.getRating());
            //Log.d(MainActivity.TAG, String.valueOf(quote));
            getIntent()
                    .putExtra("strQuote", quote.getStrQuote())
                    .putExtra("rating", quote.getRating());
            setResult(10, getIntent());
            finish();
        } else {
            getIntent();
            finish();
        }
    }
}