package com.blogspot.atifsoftwares.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.firebaseapp.models.ModelClubApply;
import com.blogspot.atifsoftwares.firebaseapp.models.ModelClubPeople;
import com.blogspot.atifsoftwares.firebaseapp.models.ModelResume;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ResumeSeeActivity extends AppCompatActivity {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = database.getReference();
    String uid;
    String Title2, Name2, Grade2, Department2, Old2, Department_Number2, Hobby2, Speciality2, Sex2;
    TextView Title, Name, Grade, Department, Old, Department_Number, Hobby, Speciality, Sex;
    Button btn_apply, btn_refuse, btn_back;
    String dongari;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_see);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("회원 이력서 열람");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        Title = findViewById(R.id.Title);
        Name = findViewById(R.id.Name);
        Grade = findViewById(R.id.Grade);
        Department = findViewById(R.id.Department);
        Old = findViewById(R.id.Old);
        Department_Number = findViewById(R.id.Department_Number);
        Hobby = findViewById(R.id.Hobby);
        Speciality = findViewById(R.id.Speciality);
        Sex = findViewById(R.id.Sex);

        final FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        uid = fUser.getUid();
        Intent intent = getIntent(); //전달할 데이터를 받을 Intent
        //text 키값으로 데이터를 받는다. String을 받아야 하므로 getStringExtra()를 사용함
        String text = intent.getStringExtra("text");
       btn_apply = findViewById(R.id.btn_save);
       btn_refuse = findViewById(R.id.btn_load);
       btn_back = findViewById(R.id.btn_back);

       btn_back.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(getApplicationContext(), ClubPeople.class);
               startActivity(intent);
           }
       });


        databaseReference.child("Resume_management").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ModelResume group = dataSnapshot.getValue(ModelResume.class);
                if(dataSnapshot.exists()) {
                    dongari = group.getDongari();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Log.e("MainActivity", String.valueOf(databaseError.toException())); // 에러문 출력
            }
        });


       btn_apply.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               databaseReference.child("동아리회원목록").child(dongari).child(Department_Number2).addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       ModelClubPeople group = dataSnapshot.getValue(ModelClubPeople.class);
                       if(dataSnapshot.exists()) {
                           String b = group.getName();
                           add_club_apply(dongari, b, Department_Number2);
                           Toast.makeText(getApplicationContext(), b + "가 " + dongari + "의 새로운 회원이 되었습니다.", Toast.LENGTH_LONG).show();
                       }
                   }
                   @Override
                   public void onCancelled(@NonNull DatabaseError databaseError) {
                       //Log.e("MainActivity", String.valueOf(databaseError.toException())); // 에러문 출력
                   }
               });
           }
       });



        databaseReference.child("Resume_management").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ModelResume group = dataSnapshot.getValue(ModelResume.class);

                if(!dataSnapshot.exists()) {

                }else {
                    for (DataSnapshot ds: dataSnapshot.getChildren()) {
                        String tmp;
                        tmp = "" + ds.child("name").getValue();
                        if(text.equals(tmp)) {
                            Title2 = "" + ds.child("title").getValue();
                            Name2 = "" + ds.child("name").getValue();
                            Grade2 = "" + ds.child("grade").getValue();
                            Department2 = "" + ds.child("department").getValue();
                            Old2 = "" + ds.child("old").getValue();
                            Department_Number2 = "" + ds.child("department_Number").getValue();;
                            Hobby2 = "" + ds.child("hobby").getValue();
                            Speciality2 = "" + ds.child("speciality").getValue();
                            Sex2 = "" + ds.child("sex").getValue();

                            Title.setText(Title2);
                            Name.setText(Name2);
                            Grade.setText(Grade2);
                            Department.setText(Department2);
                            Old.setText(Old2);
                            Department_Number.setText(Department_Number2);
                            Hobby.setText(Hobby2);
                            Speciality.setText(Speciality2);
                            Sex.setText(Sex2);
                            Toast.makeText(getApplicationContext(), Name2 +"의 이력서 정보를 불러왔습니다.", Toast.LENGTH_SHORT).show();
                        }


                    }

                    //각각의 값 받아오기 get어쩌구 함수들은 ModelResume.class에서 지정한것
                    /*
                    Title2 = group.getTitle();
                    Name2 = group.getName();
                    Grade2 = group.getGrade();
                    Department2 = group.getDepartment();
                    Old2 = group.getOld();
                    Department_Number2 = group.getDepartment_Number();
                    Hobby2 = group.getHobby();
                    Speciality2 = group.getSpeciality();
                    Sex2 = group.getSex();

                    Title.setText(Title2);
                    Name.setText(Name2);
                    Grade.setText(Grade2);
                    Department.setText(Department2);
                    Old.setText(Old2);
                    Department_Number.setText(Department_Number2);
                    Hobby.setText(Hobby2);
                    Speciality.setText(Speciality2);
                    Sex.setText(Sex2);
*/
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Log.e("MainActivity", String.valueOf(databaseError.toException())); // 에러문 출력
            }
        });
    }

    public void add_club_apply(String club_name, String name, String department_number) {


        ModelClubApply animal = new ModelClubApply(club_name, name, department_number, "동아리 회원");
        databaseReference.child("동아리회원목록").child(club_name).child(department_number).setValue(animal);

    }
}