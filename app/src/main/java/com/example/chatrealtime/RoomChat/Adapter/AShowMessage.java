package com.example.chatrealtime.RoomChat.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatrealtime.Model.ChatMessage;
import com.example.chatrealtime.R;

import java.util.List;

public class AShowMessage extends RecyclerView.Adapter<AShowMessage.ViewHolder> {
    List<ChatMessage> messageList;
    Context context;

    public AShowMessage(List<ChatMessage> messageList, Context context) {
        this.messageList = messageList;
        this.context = context;
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
        holder.tvMessage.setText(message.getMessageText());
        holder.tvUserName.setText(message.getMessageUser());
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMessage;
        TextView tvUserName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMessage= itemView.findViewById(R.id.tvMessage);
            tvUserName= itemView.findViewById(R.id.tvUserName);
        }
    }
}
