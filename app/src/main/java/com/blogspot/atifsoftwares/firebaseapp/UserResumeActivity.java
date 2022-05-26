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

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;


public class UserResumeActivity extends AppCompatActivity {
    String uid;
    //파이어 베이스 데이터 연동을 위한 코드
    private FirebaseDatabase database = FirebaseDatabase.getInstance();


    //DatabaseReference는 데이터베이스의 특정 위치로 연결하는 거라고 생각하면 된다.
    //현재 연결은 데이터베이스에만 딱 연결해놓고
    //키값(테이블 또는 속성)의 위치 까지는 들어가지는 않은 모습이다.
    private DatabaseReference databaseReference = database.getReference();

    Button btn_back, btn_save, btn_load;
    EditText Title, Name, Grade, Department, Old, Department_Number, Hobby, Speciality, Sex;
    String Title2, Name2, Grade2, Department2, Old2, Department_Number2, Hobby2, Speciality2;
    String Sex2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_resume);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("이력서 관리");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);


        btn_save = findViewById(R.id.btn_save);
        btn_load = findViewById(R.id.btn_load);
        btn_back = findViewById(R.id.btn_back);
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
        //get path of database named "Users" containing users info
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        uid = fUser.getUid();










        //버튼을 누르면 값을 저장하기
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addResume(Title.getText().toString(), Name.getText().toString(),Grade.getText().toString(),Department.getText().toString(),
                        Old.getText().toString(),Department_Number.getText().toString(), Hobby.getText().toString(), Speciality.getText().toString()
                , Sex.getText().toString(), uid);

                Toast.makeText(getApplicationContext(), "회원 이력서 저장이 성공적으로 완료되었습니다.", Toast.LENGTH_SHORT).show();


                Intent intent2 = new Intent(getApplicationContext(), DashboardActivity.class);
                startActivity(intent2);

            }
        });




           databaseReference.child("Resume_management").child(uid).addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                   ModelResume group = dataSnapshot.getValue(ModelResume.class);

                   if(!dataSnapshot.exists()) {

                   }else {


                       //각각의 값 받아오기 get어쩌구 함수들은 ModelResume.class에서 지정한것
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


                       Toast.makeText(getApplicationContext(), "회원 이력서 불러오기가 성공적으로 완료되었습니다.", Toast.LENGTH_SHORT).show();
                   }

               }

               @Override
               public void onCancelled(@NonNull DatabaseError databaseError) {
                   //Log.e("MainActivity", String.valueOf(databaseError.toException())); // 에러문 출력
               }
           });




        // 나가기 버튼을 눌렀을때 홈 화면으로 이동
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                startActivity(intent);
            }
        });




    }

    public void addResume(String Title, String Name, String Grade, String Department, String Old, String Department_Number, String Hobby, String Speciality
                          , String Sex, String uid) {
        ModelResume resume = new ModelResume(Title, Name, Grade, Department, Old, Department_Number, Hobby, Speciality, Sex, uid);

        databaseReference.child("Resume_management").child(uid).setValue(resume);
    }

}