package com.blogspot.atifsoftwares.firebaseapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.firebaseapp.R;
import com.blogspot.atifsoftwares.firebaseapp.models.ModelClubchat;

import java.util.ArrayList;

public class ClubchatAdapter extends RecyclerView.Adapter<ClubchatAdapter.ViewHolder> {

    static ArrayList<ModelClubchat> items = new ArrayList<ModelClubchat>();

    @NonNull
    @Override
    public ClubchatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.club_chat_item, viewGroup, false); // 인플레이션을 통해 뷰 객체 만들기
        return new ViewHolder(itemView); // 뷰홀더 객체를 생성하면서 뷰 객체를 전달하고 그 뷰홀더 객체를 반환하기
    }

    @Override
    public void onBindViewHolder(@NonNull ClubchatAdapter.ViewHolder viewHolder, int position) {
        ModelClubchat item = items.get(position);
        viewHolder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textView2;

        public ViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);
        }

        public void setItem(ModelClubchat item) {
            textView.setText(item.getName());
            textView2.setText(item.getChat());
        }

    }

    public void addItem(ModelClubchat item) {
        items.add(item);
    }

    public void setItems(ArrayList<ModelClubchat> items) {
        this.items = items;
    }

    public ModelClubchat getItem(int position) {
        return items.get(position);
    }

    public ModelClubchat setItem(int position, ModelClubchat item) {
        return items.set(position, item);
    }

    public void setinit() {
        items =  new ArrayList<ModelClubchat>();
    }

}
