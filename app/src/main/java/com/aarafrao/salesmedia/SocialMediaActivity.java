package com.aarafrao.salesmedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SocialMediaActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private TextView txtInstaUname, txtYtUname;
    private MaterialButton btnFollow, btnSubs;
    private String ytLink = "";
    private String instaLink = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_media);


        initViews();


    }

    private void initViews() {
        txtInstaUname = findViewById(R.id.txtWaNo);
        txtYtUname = findViewById(R.id.txtMail);
        btnFollow = findViewById(R.id.btnMsg);
        btnSubs = findViewById(R.id.btnMail);

        getFirebaseData();
        btnSubs.setOnClickListener(v -> {
            startActivity(new Intent(SocialMediaActivity.this, WebActivity.class).putExtra("url", ytLink));
        });

        btnFollow.setOnClickListener(v -> {
            startActivity(new Intent(SocialMediaActivity.this, WebActivity.class).putExtra("url", instaLink));
        });
    }

    private void getFirebaseData() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Social").child("YtChannel").get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.e("firebase", "Error getting data", task.getException());
            } else {
                txtYtUname.setText(String.valueOf(task.getResult().getValue()));

            }
        });
        mDatabase.child("Social").child("iUName").get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.e("firebase", "Error getting data", task.getException());
            } else {
                txtInstaUname.setText(String.valueOf(task.getResult().getValue()));
            }
        });

        mDatabase.child("Social").child("ILink").get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.e("firebase", "Error getting data", task.getException());
            } else {
                instaLink = String.valueOf(task.getResult().getValue());
            }
        });
        mDatabase.child("Social").child("ChannelLink").get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.e("firebase", "Error getting data", task.getException());
            } else {
                ytLink = String.valueOf(task.getResult().getValue());
            }
        });

    }
}