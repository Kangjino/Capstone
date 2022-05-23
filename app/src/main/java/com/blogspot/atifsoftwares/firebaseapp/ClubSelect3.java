package com.blogspot.atifsoftwares.firebaseapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.ListView;

public class ClubSelect3 extends AppCompatActivity {
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_select);
        actionBar = getSupportActionBar();
        actionBar.setTitle("종교 분과 검색");
        //종교

        ListView lv3=findViewById(R.id.wow);
        rellist flAdapter=new rellist();

        lv3.setAdapter(flAdapter);

        flAdapter.addrel(ContextCompat.getDrawable(this,R.drawable.one),"원불교 학생회","우리 모여라 동아리에서는");
        flAdapter.addrel(ContextCompat.getDrawable(this,R.drawable.ccc),"CCC","지금 표시되는 부분에");
        flAdapter.addrel(ContextCompat.getDrawable(this,R.drawable.dfc),"DFC","이력서에 작성한 동아리 한줄소개가");
        flAdapter.addrel(ContextCompat.getDrawable(this,R.drawable.jdm),"JDM","노출될 예정입니다");
        flAdapter.addrel(ContextCompat.getDrawable(this,R.drawable.sce),"SCE","모여라동아리");
        flAdapter.addrel(ContextCompat.getDrawable(this,R.drawable.ivf),"IVF","화이팅!");
        flAdapter.addrel(ContextCompat.getDrawable(this,R.drawable.dsm),"DSM","맛있어");
        flAdapter.addrel(ContextCompat.getDrawable(this,R.drawable.sfc),"SFC","맛있어");
        flAdapter.addrel(ContextCompat.getDrawable(this,R.drawable.esf),"ESF","맛있어");
        flAdapter.addrel(ContextCompat.getDrawable(this,R.drawable.ubf),"UBF","맛있어");
        flAdapter.addrel(ContextCompat.getDrawable(this,R.drawable.navi),"네비게이토 선교회","맛있어");
        flAdapter.addrel(ContextCompat.getDrawable(this,R.drawable.gatol),"가톨릭 학생회","맛있어");
        flAdapter.addrel(ContextCompat.getDrawable(this,R.drawable.inter),"인터콥 학생회","맛있어");
        flAdapter.addrel(ContextCompat.getDrawable(this,R.drawable.christ),"예수전도단","맛있어");





    }
}