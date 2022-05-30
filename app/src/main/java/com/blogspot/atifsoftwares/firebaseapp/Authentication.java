package com.blogspot.atifsoftwares.firebaseapp;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.core.view.MenuItemCompat;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.firebaseapp.adapters.AdapterPosts;
import com.blogspot.atifsoftwares.firebaseapp.adapters.AdapterUsers;
import com.blogspot.atifsoftwares.firebaseapp.models.ModelClub;
import com.blogspot.atifsoftwares.firebaseapp.models.ModelClubApply;
import com.blogspot.atifsoftwares.firebaseapp.models.ModelPost;
import com.blogspot.atifsoftwares.firebaseapp.models.ModelResume;
import com.blogspot.atifsoftwares.firebaseapp.models.ModelUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Authentication extends AppCompatActivity {

    FirebaseAuth firebaseAuth; // 파이어베이스 객체생성
    String uid; // uid에 사용자 식별
    EditText show;
    String club_name;
    Button btn_apply;
    String tmp;
    String name, department_name;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference();

    final FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();
    //get path of database named "Users" containing users info
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        btn_apply = findViewById(R.id.btn_apply);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("동아리 가입신청");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        firebaseAuth = FirebaseAuth.getInstance(); // 파이어베이스 객체 선언

        show = findViewById(R.id.showmethemoney);
        club_name = show.getText().toString();

        //get current user
        uid = fUser.getUid();

        databaseReference.child("Resume_management").child(uid).child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                if (dataSnapshot.exists()) {
                    name = value;
                    if (value.length() < 2) {
                        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                        startActivity(intent);
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "이력서 작성을 완료해야 신청이 가능합니다.", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Log.e("MainActivity", String.valueOf(databaseError.toException())); // 에러문 출력
            }
        });

        databaseReference.child("Resume_management").child(uid).child("department_Number").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                department_name= value;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Log.e("MainActivity", String.valueOf(databaseError.toException())); // 에러문 출력
            }
        });



        btn_apply.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                tmp = show.getText().toString();

                databaseReference.child(tmp).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String value = dataSnapshot.getValue(String.class);

                        if(tmp.length()<2) {
                            Toast.makeText(getApplicationContext(), "동아리 이름을 다시 확인해주세요.", Toast.LENGTH_SHORT).show();
                        }else{


                            add_club_apply(tmp, name, department_name);
                            databaseReference.child("Resume_management").child(uid).child("dongari").setValue(tmp);

                            Toast.makeText(getApplicationContext(), tmp +"동아리에 가입 신청이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                        }


                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        //Log.e("MainActivity", String.valueOf(databaseError.toException())); // 에러문 출력
                    }
                });
            }
        });




    }

    public void add_club_apply(String club_name, String name, String department_number) {


        ModelClubApply animal = new ModelClubApply(club_name, name, department_number, "동아리 신청중입니다");
        databaseReference.child("동아리회원목록").child(club_name).child(department_number).setValue(animal);

    }
}