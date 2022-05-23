package com.blogspot.atifsoftwares.firebaseapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.blogspot.atifsoftwares.firebaseapp.models.su;

import java.util.ArrayList;

public class sulist extends BaseAdapter {
    ArrayList<su> sus=new ArrayList<>();

    //총 몇개냐 세는거거
    @Override
    public int getCount() {
        return sus.size();
    }
    //데이터 보내기
    @Override
    public Object getItem(int i) {
        return sus.get(i);
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
            view=li.inflate(R.layout.sulayout, viewGroup, false);

        }

        TextView tv5=view.findViewById(R.id.tv5);
        TextView tv6=view.findViewById(R.id.tv6);
        ImageView iv2=view.findViewById(R.id.iv2);

        su f=sus.get(i);

        tv6.setText(f.getName());
        tv5.setText(f.getText());
        iv2.setImageDrawable(f.getD());







        return view;
    }
    public  void addsu(Drawable d, String name, String text){
        su f=new su();

        f.setD(d);
        f.setName(name);
        f.setText(text);

        sus.add(f);
    }
}
