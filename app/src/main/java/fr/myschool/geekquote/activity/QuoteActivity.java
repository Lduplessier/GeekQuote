package fr.myschool.geekquote.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import fr.myschool.geekquote.R;

public class QuoteActivity extends Activity implements View.OnClickListener {
   // private static final String TAG = QuoteActivity.class.getSimpleName();
    private Button bt_quote_cancel, bt_quote_ok;
    private RatingBar rb_quote;
    private Bundle extrasInit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);

        extrasInit = getIntent().getExtras();

        rb_quote = findViewById(R.id.rb_quote);
        TextView tv_quote_layout_date = findViewById(R.id.tv_quote_layout_date);
        TextView tv_quote_layout_str = findViewById(R.id.tv_quote_layout_str);

        bt_quote_cancel = findViewById(R.id.bt_quote_cancel);
        bt_quote_ok = findViewById(R.id.bt_quote_ok);

        bt_quote_cancel.setOnClickListener(this);
        bt_quote_ok.setOnClickListener(this);

        tv_quote_layout_date.setText(extrasInit.getString("quoteDate"));
        tv_quote_layout_str.setText(extrasInit.getString("quoteStr"));
        rb_quote.setRating(extrasInit.getInt("quoteRating"));
    }

    @Override
    public void onClick(View v) {

        if(v == bt_quote_cancel) {
            Intent intent = new Intent(this, QuoteActivity.class);
            intent.putExtra("quoteRating", rb_quote.getRating());
            intent.putExtra("quoteStr", extrasInit.getString("quoteStr"));
            startActivity(intent);
            return;
        }

        if (v == bt_quote_ok) {
            extrasInit.clear();
            Intent intent = new Intent(this, QuoteActivity.class);
            startActivity(intent);
        }
    }
}