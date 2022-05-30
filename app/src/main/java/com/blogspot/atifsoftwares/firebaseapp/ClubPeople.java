package com.blogspot.atifsoftwares.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.firebaseapp.adapters.ClubPeopleAdapter;
import com.blogspot.atifsoftwares.firebaseapp.models.ModelClub;
import com.blogspot.atifsoftwares.firebaseapp.models.ModelClubPeople;
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

    ImageView img;
    String grade2;
    public static String club_name="d", name="d", grade="d", department_number="dd";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_people);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("동아리 회원 목록");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        img = findViewById(R.id.imageView);





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
                    department_number = group.getDepartment_Number();
                    club_name = group.getDongari();



                    databaseReference.child("동아리회원목록").child(club_name).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            ModelClubPeople group = dataSnapshot.getValue(ModelClubPeople.class);
                            if(dataSnapshot.exists()) {
                                for (DataSnapshot ds: dataSnapshot.getChildren()) {

                                    name = ""+ ds.child("name").getValue();
                                    grade = ""+ds.child("grade").getValue();
                                    department_number = (String) ds.child("department_number").getValue();
                                    adapter.addItem(new ModelClubPeople(name, grade, department_number));
                                }
                            }
                            recyclerView.setAdapter(adapter);


                        }



                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            //Log.e("MainActivity", String.valueOf(databaseError.toException())); // 에러문 출력
                        }
                    });






                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Log.e("MainActivity", String.valueOf(databaseError.toException())); // 에러문 출력
            }
        });


        adapter.setOnItemClickListener(new OnClubPeopleItemClickListener() {
            @Override
            public void onItemClick(ClubPeopleAdapter.ViewHolder holder, View view, int position) {
                ModelClubPeople item = adapter.getItem(position);
                if(true) {
                    Intent intent = new Intent(getApplicationContext(), ResumeSeeActivity.class);
                    intent.putExtra("text", item.getName());
                    startActivity(intent);
                }
                Toast.makeText(getApplicationContext(), "동아리 회장만 이력서를 열람할 수 있습니다.", Toast.LENGTH_SHORT);

            }
        });




    }
}

