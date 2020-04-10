package fr.myschool.geekquote.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import fr.myschool.geekquote.R;

import fr.myschool.geekquote.adapter.QuoteListAdapter;
import fr.myschool.geekquote.model.Quote;

public class QuoteListActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {

    public static final String TAG = "GeekQuote";

    private ArrayList<Quote> quotes = new ArrayList<>();

    private EditText et_main_quote;
    private Button bt_main_add;
    private ListView lv_main_quotes;
    private QuoteListAdapter quoteArrayAdapter;
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (extras != null) {
            for (Quote q : this.quotes) {
                if (q.getStrQuote() == extras.get("quoteStr")) {
                    q.setRating((int) extras.get("quoteRating"));

                }
            }
            extras.clear();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_main_quote = findViewById(R.id.et_main_quote);
        bt_main_add = findViewById(R.id.bt_main_add);
        lv_main_quotes = findViewById(R.id.lv_main_quotes);

        bt_main_add.setOnClickListener(this);
        lv_main_quotes.setOnItemClickListener(this);

        quoteArrayAdapter = new QuoteListAdapter(this, quotes);

        lv_main_quotes.setAdapter(quoteArrayAdapter);

        String[] initialQuotes = getResources().getStringArray(R.array.initial_quotes);
        for(String s : initialQuotes) {
            addQuote(s);
        }
    }


    void addQuote(String strQuote) {
        Quote quote = new Quote(strQuote);
        quotes.add(0, quote);

        //Toast.makeText(this, strQuote, Toast.LENGTH_SHORT).show();

    }

    public void onClick(View v) {
        if(v == bt_main_add) {
            String inputStr = et_main_quote.getText().toString();
            addQuote(inputStr);
            et_main_quote.getText().clear();
        }
    }


    public void onItemClick(AdapterView<?> Parent, View view, int position, long id) {
        String strQuote = quotes.get(position).getStrQuote();
        int ratingQuote = quotes.get(position).getRating();

        Intent intent = new Intent(this, QuoteActivity.class);
        intent.putExtra("strquote", strQuote);
        intent.putExtra("ratingquote", ratingQuote);
        startActivity(intent);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
