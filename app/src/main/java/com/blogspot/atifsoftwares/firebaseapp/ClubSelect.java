package com.blogspot.atifsoftwares.firebaseapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.ListView;

public class ClubSelect extends AppCompatActivity {

    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_select);

        actionBar = getSupportActionBar();
        actionBar.setTitle("학술 분과 검색");
        //학술분과
        ListView lv4=findViewById(R.id.wow);
        studylist flAdapter=new studylist();

        lv4.setAdapter(flAdapter);

        flAdapter.addstudy(ContextCompat.getDrawable(this,R.drawable.isa),"I.S.A.","맛있어");
        flAdapter.addstudy(ContextCompat.getDrawable(this,R.drawable.hong),"흥사단 아카데미","맛있어");
        flAdapter.addstudy(ContextCompat.getDrawable(this,R.drawable.option),"Option","맛있어");
        flAdapter.addstudy(ContextCompat.getDrawable(this,R.drawable.sky),"하늘사랑","맛있어");
        flAdapter.addstudy(ContextCompat.getDrawable(this,R.drawable.abc),"ABC마케팅","맛있어");
        flAdapter.addstudy(ContextCompat.getDrawable(this,R.drawable.ptc),"P.T.C","맛있어");
        flAdapter.addstudy(ContextCompat.getDrawable(this,R.drawable.hp),"H.P","맛있어");
        flAdapter.addstudy(ContextCompat.getDrawable(this,R.drawable.gmp),"백악GMP","맛있어");
        flAdapter.addstudy(ContextCompat.getDrawable(this,R.drawable.chinese),"청람","맛있어");
        flAdapter.addstudy(ContextCompat.getDrawable(this,R.drawable.car),"대자연","맛있어");


    }
}