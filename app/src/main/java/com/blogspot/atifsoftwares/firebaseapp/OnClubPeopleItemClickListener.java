package com.blogspot.atifsoftwares.firebaseapp;

import android.view.View;

import com.blogspot.atifsoftwares.firebaseapp.adapters.ClubPeopleAdapter;

public interface OnClubPeopleItemClickListener {
    //onItemClick()메서드가 호출될 때 파라미터로 뷰홀더 객체와 뷰 객체 그리고 뷰의 포지션 정보가 전달되도록 하기위한 인터페이스 생성
    // position정보는  몇번째 아이템인지 구분할 수 있는 인덱스 값
    public void onItemClick(ClubPeopleAdapter.ViewHolder holder, View view, int position);
}
