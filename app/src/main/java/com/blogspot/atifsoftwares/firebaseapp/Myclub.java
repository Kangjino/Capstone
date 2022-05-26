package com.blogspot.atifsoftwares.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.blogspot.atifsoftwares.firebaseapp.models.ModelClub;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;


public class Myclub extends AppCompatActivity {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();


    String uid;
    Button btn_manageent;
    Button btn_people;
    Button btn_chat;
    //DatabaseReference는 데이터베이스의 특정 위치로 연결하는 거라고 생각하면 된다.
    //현재 연결은 데이터베이스에만 딱 연결해놓고
    //키값(테이블 또는 속성)의 위치 까지는 들어가지는 않은 모습이다.
    private DatabaseReference databaseReference = database.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myclub);

        btn_manageent = findViewById(R.id.btn_management);
        btn_people = findViewById(R.id.btn_people);
        btn_chat = findViewById(R.id.btn_chat);






        final FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        uid = fUser.getUid();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        databaseReference.child("Club_Management").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ModelClub group = dataSnapshot.getValue(ModelClub.class);
                actionBar.setTitle(group.getClub_Name());




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn_manageent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ClubMake.class);
                startActivity(intent);
            }
        });

        btn_people.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ClubPeople.class);
                startActivity(intent);
            }
        });

        btn_chat.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClubMindPostFragment fragment5 = new ClubMindPostFragment();
                FragmentTransaction ft5 = getSupportFragmentManager().beginTransaction();
                ft5.replace(R.id.content, fragment5, "");
                ft5.commit();
            }
        }));

    }
}