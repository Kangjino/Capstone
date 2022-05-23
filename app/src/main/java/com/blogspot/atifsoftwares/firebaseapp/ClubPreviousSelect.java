package com.blogspot.atifsoftwares.firebaseapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ClubPreviousSelect extends AppCompatActivity {
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_previous_select);


        actionBar = getSupportActionBar();
        actionBar.setTitle("동아리 분과 선택");
        Button A, B , C, D , E, F;
        A = findViewById(R.id.select_A);
        B= findViewById(R.id.select_B);
        C = findViewById(R.id.select_C);
        D = findViewById(R.id.select_D);
        E = findViewById(R.id.select_E);
        F = findViewById(R.id.select_F);

        A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ClubSelect5.class);
                startActivity(intent);
            }
        });

        B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ClubSelect4.class);
                startActivity(intent);
            }
        });
        C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ClubSelect3.class);
                startActivity(intent);
            }
        });
        D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ClubSelect2.class);
                startActivity(intent);
            }
        });
        E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ClubSelect.class);
                startActivity(intent);
            }
        });




    }
}