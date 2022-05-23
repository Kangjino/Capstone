package com.blogspot.atifsoftwares.firebaseapp;

import android.content.Intent;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
//commit test
    //views
    Button mRegisterBtn, mLoginBtn; // 회원가입 버튼, 로그인버튼 선언
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // 프로그램 실행시 액티비티 메인 시작
        actionBar = getSupportActionBar();
        actionBar.setTitle("모여라 동아리에 오신걸 환영합니다");
        //init views
        mRegisterBtn = findViewById(R.id.register_btn); // ID찾아와주고
        mLoginBtn = findViewById(R.id.login_btn);// ID 찾아와주고

        //handle register button click
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start RegisterActivity
                startActivity(new Intent(MainActivity.this, RegisterActivity.class)); // MainActivity에서 회원가입으로 이동
            }
        });
        //handle login button click
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start LoginActivity
                startActivity(new Intent(MainActivity.this, LoginActivity.class)); // MainActivity에서 로그인액티비티로 이동
            }
        });
    }
}
/*In this Part(30):
 * ->Post Notifications:
 *   -We will use Firebase Topic Messaging
 *   -We will create settings screen where user can enable/disable post notification
 *   -Add menu item "Settings" in menu_main.xml to access Settings Activity
 *   -I'm only creating notifications for posts, but using this tutorial you can implement notifications for
 *    likes, comments etc too.
 * ->Require Libraries
 *   -Volley                    [Already added for chat notification]
 *   -Firebase cloud messaging  [Already added for chat notification]*/