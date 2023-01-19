package com.aarafrao.salesmedia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout item_cu, item_updates, item_login, item_sm;

    private DatabaseReference mDatabase;
    private String updateLink = "";
    private String loginLink = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initViews();
        getFirebaseData();
    }

    private void getFirebaseData() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Dashboard").child("LoginLink").get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.e("firebase", "Error getting data", task.getException());
            } else {
                loginLink = String.valueOf(task.getResult().getValue());

            }
        });
        mDatabase.child("Dashboard").child("UpdateLink").get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.e("firebase", "Error getting data", task.getException());
            } else {
                updateLink = String.valueOf(task.getResult().getValue());

            }
        });

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
                startActivity(new Intent(this, SocialMediaActivity.class));
                break;

            case R.id.item_cu:
                startActivity(new Intent(this, ContactUsActivity.class));
                break;

            case R.id.item_updates:
                startActivity(new Intent(this, WebActivity.class).putExtra("url", String.valueOf(updateLink)));
                break;

            case R.id.item_login:

                startActivity(new Intent(this, WebActivity.class).putExtra("url", String.valueOf(loginLink)));
                break;
        }
    }
}