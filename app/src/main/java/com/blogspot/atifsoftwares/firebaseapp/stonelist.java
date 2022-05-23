package com.blogspot.atifsoftwares.firebaseapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.blogspot.atifsoftwares.firebaseapp.models.stone;

import java.util.ArrayList;

public class stonelist extends BaseAdapter {
    ArrayList<stone> stones=new ArrayList<>();

    //총 몇개냐 세는거거
    @Override
    public int getCount() {
        return stones.size();
    }
    //데이터 보내기
    @Override
    public Object getItem(int i) {
        return stones.get(i);
    }

    //어디에 있어
    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Context c = viewGroup.getContext();
        if(view==null){
            LayoutInflater li=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=li.inflate(R.layout.cap, viewGroup, false);

        }

        TextView tv=view.findViewById(R.id.tv);
        TextView tv2=view.findViewById(R.id.tv2);
        ImageView iv=view.findViewById(R.id.iv);

        stone f=stones.get(i);

        tv2.setText(f.getName());
        tv.setText(f.getText());
        iv.setImageDrawable(f.getD());







        return view;
    }
    public  void addstone(Drawable d, String name, String text){
        stone f=new stone();

        f.setD(d);
        f.setName(name);
        f.setText(text);

        stones.add(f);
    }
}
