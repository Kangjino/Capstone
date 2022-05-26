package com.blogspot.atifsoftwares.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.blogspot.atifsoftwares.firebaseapp.adapters.ClubPeopleAdapter;
import com.blogspot.atifsoftwares.firebaseapp.models.ModelClub;
import com.blogspot.atifsoftwares.firebaseapp.models.ModelClubPeople;
import com.blogspot.atifsoftwares.firebaseapp.models.ModelMindpost;
import com.blogspot.atifsoftwares.firebaseapp.models.ModelResume;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;


public class ClubPeople extends AppCompatActivity {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = database.getReference();

    List<ModelClub> ClubPList;

    public static String name="d", grade="d";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_people);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("동아리 회원 목록");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);






        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false); // 여기 압축
        recyclerView.setLayoutManager(layoutManager);
        ClubPeopleAdapter adapter = new ClubPeopleAdapter();






        String uid;
        final FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        uid = fUser.getUid();

        databaseReference.child("Resume_management").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ModelResume group = dataSnapshot.getValue(ModelResume.class);

                if(dataSnapshot.exists()) {

                    name = group.getName();
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Log.e("MainActivity", String.valueOf(databaseError.toException())); // 에러문 출력
            }
        });

        databaseReference.child("Club_Management").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ModelClub group = dataSnapshot.getValue(ModelClub.class);

                if(dataSnapshot.exists()) {
                    for (DataSnapshot ds: dataSnapshot.getChildren()) {
                        grade = group.getGrade();
                        adapter.addItem(new ModelClubPeople(name, grade));
                    }
                }
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Log.e("MainActivity", String.valueOf(databaseError.toException())); // 에러문 출력
            }
        });




        recyclerView.setAdapter(adapter);

    }
}

