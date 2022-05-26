package com.blogspot.atifsoftwares.firebaseapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.firebaseapp.R;
import com.blogspot.atifsoftwares.firebaseapp.models.ModelClubPeople;

import java.util.ArrayList;

public class ClubPeopleAdapter extends RecyclerView.Adapter<ClubPeopleAdapter.ViewHolder> {
    ArrayList<ModelClubPeople> items = new ArrayList<ModelClubPeople>();

    @NonNull
    @Override
    public ClubPeopleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.club_people_item, viewGroup, false); // 인플레이션을 통해 뷰 객체 만들기
        return new ViewHolder(itemView); // 뷰홀더 객체를 생성하면서 뷰 객체를 전달하고 그 뷰홀더 객체를 반환하기
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

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textView2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);
        }

        public void setItem(ModelClubPeople item) {
            textView.setText(item.getName());
            textView2.setText(item.getDepartment_number());
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

}
