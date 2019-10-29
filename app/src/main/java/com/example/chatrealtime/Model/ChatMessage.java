package com.example.chatrealtime.Model;

import java.util.Date;

public class ChatMessage {
    private String messageText;
    private String userID;
    private long messageTime;
    public ChatMessage() {
        //mặc định cần có
    }

    public ChatMessage(String messageText, String userID) {
        this.messageText = messageText;
        this.userID = userID;
        messageTime= new Date().getTime();
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }
}
