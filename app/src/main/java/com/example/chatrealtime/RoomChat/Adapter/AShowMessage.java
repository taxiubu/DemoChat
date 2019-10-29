package com.example.chatrealtime.RoomChat.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatrealtime.Model.ChatMessage;
import com.example.chatrealtime.R;

import java.util.List;

public class AShowMessage extends RecyclerView.Adapter<AShowMessage.ViewHolder> {
    List<ChatMessage> messageList;
    Context context;
    String userID;

    public AShowMessage(List<ChatMessage> messageList, Context context, String userID) {
        this.messageList = messageList;
        this.context = context;
        this.userID= userID;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_chat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChatMessage message= messageList.get(position);
        if(userID!=message.getUserID()){
            holder.layoutMessCurrentUser.setVisibility(View.GONE);
            holder.tvMessage.setText(message.getMessageText());
        }
        else {
            holder.layoutMessFriend.setVisibility(View.GONE);
            holder.tvMessageCurrentUser.setText(message.getMessageText());
        }

    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout layoutMessFriend, layoutMessCurrentUser;
        TextView tvMessage;
        TextView tvMessageCurrentUser;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMessage= itemView.findViewById(R.id.tvMessage);
            tvMessageCurrentUser= itemView.findViewById(R.id.tvMessageCurrentUser);
            layoutMessFriend = itemView.findViewById(R.id.layoutMessFriend);
            layoutMessCurrentUser= itemView.findViewById(R.id.layoutMessCurrentUser);
        }
    }
}
