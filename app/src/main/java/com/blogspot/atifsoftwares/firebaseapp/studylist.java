package com.blogspot.atifsoftwares.firebaseapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.blogspot.atifsoftwares.firebaseapp.models.study;

import java.util.ArrayList;

public class studylist extends BaseAdapter {
    ArrayList<study> studys=new ArrayList<>();

    //총 몇개냐 세는거거
    @Override
    public int getCount() {
        return studys.size();
    }
    //데이터 보내기
    @Override
    public Object getItem(int i) {
        return studys.get(i);
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
            view=li.inflate(R.layout.studylayout, viewGroup, false);

        }

        TextView tv9=view.findViewById(R.id.tv9);
        TextView tv10=view.findViewById(R.id.tv10);
        ImageView iv4=view.findViewById(R.id.iv4);

        study f=studys.get(i);

        tv10.setText(f.getName());
        tv9.setText(f.getText());
        iv4.setImageDrawable(f.getD());







        return view;
    }
    public  void addstudy(Drawable d, String name, String text){
        study f=new study();

        f.setD(d);
        f.setName(name);
        f.setText(text);

        studys.add(f);
    }
}
