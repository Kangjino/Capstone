package com.blogspot.atifsoftwares.firebaseapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.firebaseapp.OnClubPeopleItemClickListener;
import com.blogspot.atifsoftwares.firebaseapp.R;
import com.blogspot.atifsoftwares.firebaseapp.models.ModelClubPeople;

import java.util.ArrayList;

public class ClubPeopleAdapter extends RecyclerView.Adapter<ClubPeopleAdapter.ViewHolder>
    implements  OnClubPeopleItemClickListener{
    ArrayList<ModelClubPeople> items = new ArrayList<ModelClubPeople>();
    OnClubPeopleItemClickListener listener;

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if(listener != null) listener.onItemClick(holder, view, position);
    }


    public void setOnItemClickListener(OnClubPeopleItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ClubPeopleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.club_people_item, viewGroup, false); // 인플레이션을 통해 뷰 객체 만들기
        return new ViewHolder(itemView, this); // 뷰홀더 객체를 생성하면서 뷰 객체를 전달하고 그 뷰홀더 객체를 반환하기
    }



    @Override
    public void onBindViewHolder(@NonNull ClubPeopleAdapter.ViewHolder viewHolder, int position) {
        ModelClubPeople item = items.get(position);
        viewHolder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textView2;
        TextView textView3;



        public ViewHolder(@NonNull View itemView, final OnClubPeopleItemClickListener listener) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3 = itemView.findViewById(R.id.textView3);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(listener != null) {
                        listener.onItemClick(ViewHolder.this , view, position);
                    }
                }
            });

        }



        public void setItem(ModelClubPeople item) {
            textView.setText(item.getName());
            textView2.setText(item.getDepartment_number());
            textView3.setText(item.getGrade());
        }

    }


    public void addItem(ModelClubPeople item) {
        items.add(item);
    }

    public void setItems(ArrayList<ModelClubPeople> items) {
        this.items = items;
    }

    public ModelClubPeople getItem(int position) {
        return items.get(position);
    }

    public ModelClubPeople setItem(int position, ModelClubPeople item) {
        return items.set(position, item);
    }

    public interface OnItemClickListener{
        void onItemClick(View v, int position); //뷰와 포지션값
    }

    //리스너 객체 참조 변수
    private OnItemClickListener mListener = null;
    //리스너 객체 참조를 어댑터에 전달 메서드
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }


}
