package com.aarafrao.salesmedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ContactUsActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private TextView txtWaNo, txtMail;
    private String waLink;
    private MaterialButton btnMsg, btnMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        initViews();

    }

    private void initViews() {
        txtMail = findViewById(R.id.txtMail);
        txtWaNo = findViewById(R.id.txtWaNo);
        btnMsg = findViewById(R.id.btnMsg);
        btnMail = findViewById(R.id.btnMail);

        getFirebaseData();
        btnMail.setOnClickListener(v -> {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:" + txtMail));
            startActivity(Intent.createChooser(emailIntent, "Send feedback"));
        });

        btnMsg.setOnClickListener(v -> {
            startActivity(new Intent(this, WebActivity.class).putExtra("url", waLink));
        });
    }

    private void getFirebaseData() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Contacts").child("Email").get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.e("firebase", "Error getting data", task.getException());
            } else {
                txtMail.setText(String.valueOf(task.getResult().getValue()));
            }
        });
        mDatabase.child("Contacts").child("WaLink").get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.e("firebase", "Error getting data", task.getException());
            } else {
                waLink = (String.valueOf(task.getResult().getValue()));
            }
        });

        mDatabase.child("Contacts").child("WaNo").get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.e("firebase", "Error getting data", task.getException());
            } else {
                txtWaNo.setText(String.valueOf(task.getResult().getValue()));
            }
        });


    }

}