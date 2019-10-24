package com.example.chatrealtime.RoomChat.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatrealtime.Model.UserProfile;
import com.example.chatrealtime.R;

import java.util.List;

public class AShowMFriend extends RecyclerView.Adapter<AShowMFriend.ViewHolder> {
    List<UserProfile> userList;
    Context mContext;

    public AShowMFriend(List<UserProfile> userList, Context mContext) {
        this.userList = userList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_mfiend, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvNameMFriend.setText(userList.get(position).getFullName());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameMFriend;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameMFriend= itemView.findViewById(R.id.tvNameMyFriend);
        }
    }
}
