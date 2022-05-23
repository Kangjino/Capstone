package com.blogspot.atifsoftwares.firebaseapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.blogspot.atifsoftwares.firebaseapp.models.mon;

import java.util.ArrayList;

public class monlist extends BaseAdapter {
    ArrayList<mon> mons=new ArrayList<>();

    //총 몇개냐 세는거거
    @Override
    public int getCount() {
        return mons.size();
    }
    //데이터 보내기
    @Override
    public Object getItem(int i) {
        return mons.get(i);
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
            view=li.inflate(R.layout.monlayout, viewGroup, false);

        }

        TextView tv3=view.findViewById(R.id.tv3);
        TextView tv4=view.findViewById(R.id.tv4);
        ImageView iv1=view.findViewById(R.id.iv1);

        mon f=mons.get(i);

        tv4.setText(f.getName());
        tv3.setText(f.getText());
        iv1.setImageDrawable(f.getD());







        return view;
    }
    public  void addmon(Drawable d, String name, String text){
        mon f=new mon();

        f.setD(d);
        f.setName(name);
        f.setText(text);

        mons.add(f);
    }
}
