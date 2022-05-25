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

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;


public class ClubMake extends AppCompatActivity {

    //파이어 베이스 데이터 연동을 위한 코드
    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    //DatabaseReference는 데이터베이스의 특정 위치로 연결하는 거라고 생각하면 된다.
    //현재 연결은 데이터베이스에만 딱 연결해놓고
    //키값(테이블 또는 속성)의 위치 까지는 들어가지는 않은 모습이다.
    private DatabaseReference databaseReference = database.getReference();

    Button btn_back, btn_save, btn_load, btn_plz;
    EditText Club_Title, Club_Name, Club_Introduce, Club_Rule, Club_Harmoney;
    String Club_Title2, Club_Name2, Club_Introduce2, Club_Rule2, Club_Harmoney2, uid;
    String grade = "비회원";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_make);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("동아리 개설");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);





        btn_save = findViewById(R.id.btn_save);
        btn_back = findViewById(R.id.btn_back);
        btn_plz = findViewById(R.id.btn_plz);
        Club_Title = findViewById(R.id.Club_Title);
        Club_Name = findViewById(R.id.Club_Name);
        Club_Introduce = findViewById(R.id.Club_Introduce);
        Club_Rule = findViewById(R.id.Club_Rule);
        Club_Harmoney = findViewById(R.id.Club_Harmoney);
        final FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        uid = fUser.getUid();

        databaseReference.child("Club_Management").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ModelClub group = dataSnapshot.getValue(ModelClub.class);



                Club_Title2 = group.getClub_Title();
                Club_Title.setText(Club_Title2);
                Club_Name2 = group.getClub_Name();
                Club_Name.setText(Club_Name2);
                Club_Introduce2 = group.getClub_Introduce();
                Club_Introduce.setText(Club_Introduce2);
                Club_Rule2 = group.getClub_Rule();
                Club_Rule.setText(Club_Rule2);
                Club_Harmoney2 = group.getClub_Harmoney();
                Club_Harmoney.setText(Club_Harmoney2);
                if(grade.length() < group.getGrade().length()) {

                    if(group.getGrade()=="동아리 회장") {
                        grade = "동아리 회장";
                    }else if(group.getGrade() == "동아리 회장 인증중") {
                        grade = "동아리 회장 인증중";
                    }else {
                        grade= "동아리원";
                    }
                }

                Toast.makeText(getApplicationContext(), "동아리 개설 정보를 성공적으로 불러왔습니다.", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Log.e("MainActivity", String.valueOf(databaseError.toException())); // 에러문 출력
            }
        });


        btn_plz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grade = "동아리 회장 인증중";
                addClub(Club_Title.getText().toString(), Club_Name.getText().toString(),Club_Introduce.getText().toString(),Club_Rule.getText().toString(),
                        Club_Harmoney.getText().toString(), grade);

                Toast.makeText(getApplicationContext(), "동아리 개설 요청이 완료되었습니다.", Toast.LENGTH_LONG).show();


                Intent intent2 = new Intent(getApplicationContext(), DashboardActivity.class);
                startActivity(intent2);

            }
        });

        //버튼을 누르면 값을 저장하기
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addClub(Club_Title.getText().toString(), Club_Name.getText().toString(),Club_Introduce.getText().toString(),Club_Rule.getText().toString(),
                        Club_Harmoney.getText().toString(), grade);

                Toast.makeText(getApplicationContext(), "동아리 정보가 저장되었습니다.", Toast.LENGTH_LONG).show();


                Intent intent2 = new Intent(getApplicationContext(), DashboardActivity.class);
                startActivity(intent2);

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

    public void addClub(String Club_Title, String Club_Name,String Club_Introduce,String Club_Rule,
                        String Club_Harmoney, String grade) {
        ModelClub club = new ModelClub(Club_Title, Club_Name, Club_Introduce, Club_Rule, Club_Harmoney, grade);

        databaseReference.child("Club_Management").child(uid).setValue(club);
    }

}