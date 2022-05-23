package com.blogspot.atifsoftwares.firebaseapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.ListView;

public class ClubSelect5 extends AppCompatActivity {
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_select);
        //추가 메인 엑티비티 xml 아이디
        //봉사

        actionBar = getSupportActionBar();
        actionBar.setTitle("봉사 분과 검색");
       ListView lv=findViewById(R.id.wow);
        stonelist flAdapter=new stonelist();

        lv.setAdapter(flAdapter);

        flAdapter.addstone(ContextCompat.getDrawable(this,R.drawable.green),"그린액션","맛있어");
        flAdapter.addstone(ContextCompat.getDrawable(this,R.drawable.dang),"당나귀","맛있어");
        flAdapter.addstone(ContextCompat.getDrawable(this,R.drawable.big),"큰언니회","맛있어");
        flAdapter.addstone(ContextCompat.getDrawable(this,R.drawable.blue),"푸른야학","맛있어");
        flAdapter.addstone(ContextCompat.getDrawable(this,R.drawable.animal),"애니멀 애니몰","맛있어");
        flAdapter.addstone(ContextCompat.getDrawable(this,R.drawable.dodo),"두드림","맛있어");




    }
}