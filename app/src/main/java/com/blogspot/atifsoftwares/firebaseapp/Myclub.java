package com.blogspot.atifsoftwares.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;

import com.blogspot.atifsoftwares.firebaseapp.adapters.ClubPeopleAdapter;
import com.blogspot.atifsoftwares.firebaseapp.adapters.ClubchatAdapter;
import com.blogspot.atifsoftwares.firebaseapp.models.ModelClub;
import com.blogspot.atifsoftwares.firebaseapp.models.ModelClubPeople;
import com.blogspot.atifsoftwares.firebaseapp.models.ModelClubchat;
import com.blogspot.atifsoftwares.firebaseapp.models.ModelResume;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;


public class Myclub extends AppCompatActivity {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();


    String uid;
    Button btn_manageent;
    Button btn_people;
    Button btn_show;
    TextView click;
    EditText text;
    String dongari, name;
    int count2;
    //convert timestamp to dd/mm/yyyy hh:mm am/pm

    //DatabaseReference는 데이터베이스의 특정 위치로 연결하는 거라고 생각하면 된다.
    //현재 연결은 데이터베이스에만 딱 연결해놓고
    //키값(테이블 또는 속성)의 위치 까지는 들어가지는 않은 모습이다.
    private DatabaseReference databaseReference = database.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myclub);
        final int[] a = {0};

        btn_manageent = findViewById(R.id.btn_management);
        btn_people = findViewById(R.id.btn_people);
        btn_show = findViewById(R.id.btn_show);
        click = findViewById(R.id.click);
        text = findViewById(R.id.text);


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false); // 여기 압축
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        recyclerView.setLayoutManager(layoutManager);
        ClubchatAdapter adapter = new ClubchatAdapter();

        adapter.setinit();
        recyclerView.setAdapter(adapter);




        final FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        uid = fUser.getUid();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);


        databaseReference.child("Resume_management").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ModelResume group = dataSnapshot.getValue(ModelResume.class);
                actionBar.setTitle(group.getDongari());
                dongari = group.getDongari();
                name = group.getName();




                databaseReference.child("동아리회원목록").child(dongari).child("개수").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()) {
                            String count = dataSnapshot.getValue().toString();
                            count2 = Integer.parseInt(count);
                            if(a[0] ==0) {
                                count2++;
                                a[0] =1;
                            }
                            databaseReference.child("동아리회원목록").child(dongari).child("개수").setValue(count2);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        //Log.e("MainActivity", String.valueOf(databaseError.toException())); // 에러문 출력
                    }
                });


                databaseReference.child("동아리회원목록").child(dongari).child("채팅").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //ModelClubchat group = dataSnapshot.getValue(ModelClubchat.class);
                        if(dataSnapshot.exists()) {
                            for (DataSnapshot ds: dataSnapshot.getChildren()) {
                                String name1 = ""+ ds.child("name").getValue();
                                String text1 = ""+ds.child("chat").getValue();
                                adapter.addItem(new ModelClubchat(name1, text1));

                            }
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        //Log.e("MainActivity", String.valueOf(databaseError.toException())); // 에러문 출력
                    }
                });






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

        btn_show.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClubMindPostFragment fragment5 = new ClubMindPostFragment();
                FragmentTransaction ft5 = getSupportFragmentManager().beginTransaction();
                ft5.replace(R.id.content, fragment5, "");
                ft5.commit();
            }
        }));

        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a[0]=0;
                count2++;
                String tmp = text.getText().toString();
                addClubchat(name, tmp);
                text.setText("");
                adapter.setinit();
                recyclerView.setAdapter(adapter);

            }
        });








    }
    public void addClubchat(String name, String text) {
        ModelClubchat club = new ModelClubchat(name, text);

        databaseReference.child("동아리회원목록").child(dongari).child("채팅").child(String.valueOf(count2)).setValue(club);
    }
}