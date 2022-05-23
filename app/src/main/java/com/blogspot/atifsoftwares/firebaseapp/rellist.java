package com.blogspot.atifsoftwares.firebaseapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.blogspot.atifsoftwares.firebaseapp.models.rel;

import java.util.ArrayList;

public class rellist extends BaseAdapter {
    ArrayList<rel> rels=new ArrayList<>();

    //총 몇개냐 세는거거
    @Override
    public int getCount() {
        return rels.size();
    }
    //데이터 보내기
    @Override
    public Object getItem(int i) {
        return rels.get(i);
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
            view=li.inflate(R.layout.rellayout, viewGroup, false);

        }

        TextView tv7=view.findViewById(R.id.tv7);
        TextView tv8=view.findViewById(R.id.tv8);
        ImageView iv3=view.findViewById(R.id.iv3);

        rel f=rels.get(i);

        tv8.setText(f.getName());
        tv7.setText(f.getText());
        iv3.setImageDrawable(f.getD());







        return view;
    }
    public  void addrel(Drawable d, String name, String text){
        rel f=new rel();

        f.setD(d);
        f.setName(name);
        f.setText(text);

        rels.add(f);
    }
}
