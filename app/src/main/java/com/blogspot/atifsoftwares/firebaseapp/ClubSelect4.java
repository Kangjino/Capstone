package com.blogspot.atifsoftwares.firebaseapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.ListView;

public class ClubSelect4 extends AppCompatActivity {
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_select);
        //문예
        actionBar = getSupportActionBar();
        actionBar.setTitle("문예 분과 검색");

       ListView lv1=findViewById(R.id.wow);
        monlist flAdapter=new monlist();

        lv1.setAdapter(flAdapter);

        flAdapter.addmon(ContextCompat.getDrawable(this,R.drawable.movie),"영화영상동아리 전영","맛있어");
        flAdapter.addmon(ContextCompat.getDrawable(this,R.drawable.free),"Free$t.","맛있어");
        flAdapter.addmon(ContextCompat.getDrawable(this,R.drawable.ucdc),"UCDC","맛있어");
        flAdapter.addmon(ContextCompat.getDrawable(this,R.drawable.photo),"PHOTO-Y","맛있어");
        flAdapter.addmon(ContextCompat.getDrawable(this,R.drawable.pan),"PAN’s PM","맛있어");
        flAdapter.addmon(ContextCompat.getDrawable(this,R.drawable.hamonic),"하모닉스","맛있어");
        flAdapter.addmon(ContextCompat.getDrawable(this,R.drawable.pong),"조선대학교 풍물패","맛있어");
        flAdapter.addmon(ContextCompat.getDrawable(this,R.drawable.mok),"묵연회","맛있어");
        flAdapter.addmon(ContextCompat.getDrawable(this,R.drawable.gwan),"조대관현악반","맛있어");
        flAdapter.addmon(ContextCompat.getDrawable(this,R.drawable.sosbak),"소박한기쁨","맛있어");
        flAdapter.addmon(ContextCompat.getDrawable(this,R.drawable.muze),"뮤즈","맛있어");
        flAdapter.addmon(ContextCompat.getDrawable(this,R.drawable.band),"CARAVAN(캐라반)","맛있어");
        flAdapter.addmon(ContextCompat.getDrawable(this,R.drawable.nala),"나라얀","맛있어");
        flAdapter.addmon(ContextCompat.getDrawable(this,R.drawable.drama),"조대극회","맛있어");
        flAdapter.addmon(ContextCompat.getDrawable(this,R.drawable.sori),"소리터 관현악단","맛있어");
        flAdapter.addmon(ContextCompat.getDrawable(this,R.drawable.paint),"그림촌","맛있어");
        flAdapter.addmon(ContextCompat.getDrawable(this,R.drawable.emm),"음률","맛있어");
        flAdapter.addmon(ContextCompat.getDrawable(this,R.drawable.magic),"일루젼","맛있어");
        flAdapter.addmon(ContextCompat.getDrawable(this,R.drawable.shock),"Shock driveR","맛있어");
        flAdapter.addmon(ContextCompat.getDrawable(this,R.drawable.silver),"은가비","맛있어");





    }
}