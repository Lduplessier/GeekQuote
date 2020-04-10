package fr.myschool.geekquote.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import fr.myschool.geekquote.R;
import fr.myschool.geekquote.model.Quote;

public class LoginActivity extends Activity implements View.OnClickListener {

    private EditText et_login_mail;
    private EditText et_login_pass;
    private Button bt_login_confirm;

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        prefs = getSharedPreferences("fr.myschool.geekquote", this.MODE_PRIVATE);
        editor = prefs.edit();

        et_login_mail = findViewById(R.id.et_login_mail);
        et_login_pass = findViewById(R.id.et_login_pass);
        bt_login_confirm = findViewById(R.id.bt_login_confirm);

        bt_login_confirm.setOnClickListener(this);

        if (!prefs.contains("isLogged")) {
            editor.putBoolean("isLogged", false);
            editor.apply();
        } else {
            if (prefs.getBoolean("isLogged", false) == true) {
                Intent intent = new Intent(this, QuoteListActivity.class);
                startActivity(intent);
                finish();
            }
        }


    }

    @Override
    public void onClick(View v) {
        String email = et_login_mail.getText().toString();
        String password = et_login_pass.getText().toString();
        if (email.equals("Admin")  && password.equals("Admin")) {
            editor.putBoolean("isLogged", true);
            editor.apply();
            Intent intent = new Intent(this, QuoteListActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
