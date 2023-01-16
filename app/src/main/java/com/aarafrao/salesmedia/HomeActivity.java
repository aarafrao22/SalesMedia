package com.aarafrao.salesmedia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout item_cu, item_updates, item_login, item_sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initViews();
    }

    private void initViews() {
        item_cu = findViewById(R.id.item_cu);
        item_sm = findViewById(R.id.item_sm);
        item_updates = findViewById(R.id.item_updates);
        item_login = findViewById(R.id.item_login);

        item_updates.setOnClickListener(this);
        item_sm.setOnClickListener(this);
        item_login.setOnClickListener(this);
        item_cu.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.item_sm:
                Toast.makeText(this, "SM", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_cu:
                Toast.makeText(this, "SM", Toast.LENGTH_SHORT).show();

                break;
            case R.id.item_updates:
                Toast.makeText(this, "SM", Toast.LENGTH_SHORT).show();

                break;
            case R.id.item_login:
                Toast.makeText(this, "SM", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}