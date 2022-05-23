package com.blogspot.atifsoftwares.firebaseapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.ListView;

public class ClubSelect2 extends AppCompatActivity {
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_select);
        actionBar = getSupportActionBar();
        actionBar.setTitle("수련 분과 검색");
        //수련분과

        ListView lv2=findViewById(R.id.wow);
        sulist flAdapter2=new sulist();

        lv2.setAdapter(flAdapter2);

        flAdapter2.addsu(ContextCompat.getDrawable(this,R.drawable.ultimite),"얼쑤얼티밋","맛있어");
        flAdapter2.addsu(ContextCompat.getDrawable(this,R.drawable.custa),"CUSTA","맛있어");
        flAdapter2.addsu(ContextCompat.getDrawable(this,R.drawable.extreme),"익스트림","맛있어");
        flAdapter2.addsu(ContextCompat.getDrawable(this,R.drawable.tae),"태백회","맛있어");
        flAdapter2.addsu(ContextCompat.getDrawable(this,R.drawable.jujitsu),"테이크다운","맛있어");
        flAdapter2.addsu(ContextCompat.getDrawable(this,R.drawable.cycle),"바이커스","맛있어");
        flAdapter2.addsu(ContextCompat.getDrawable(this,R.drawable.bolling),"굴리세","맛있어");
        flAdapter2.addsu(ContextCompat.getDrawable(this,R.drawable.baseball),"풀카운트","맛있어");
        flAdapter2.addsu(ContextCompat.getDrawable(this,R.drawable.pingpong),"꽃보다 핑퐁","맛있어");
        flAdapter2.addsu(ContextCompat.getDrawable(this,R.drawable.basketball),"클러치","맛있어");
        flAdapter2.addsu(ContextCompat.getDrawable(this,R.drawable.glof),"나이스샷","맛있어");









    }
}