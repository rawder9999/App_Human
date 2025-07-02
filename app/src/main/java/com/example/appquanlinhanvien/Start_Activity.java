package com.example.appquanlinhanvien;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import Database.Database_For_HumanManage;

public class Start_Activity extends AppCompatActivity {
    Database_For_HumanManage databaseForHumanManage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        databaseForHumanManage = new Database_For_HumanManage(this);

        Button btnStart = findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Start_Activity.this, SignUp_Activity.class);
                startActivity(intent);
            }
        });
    }
}