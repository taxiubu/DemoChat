package com.example.chatrealtime.Model;

public class UserProfile {
    private String userID, fullName, avatar, coverImage, Status;

    public UserProfile() {
    }

    public UserProfile(String userID, String fullName, String avatar, String coverImage, String status) {
        this.userID = userID;
        this.fullName = fullName;
        this.avatar = avatar;
        this.coverImage = coverImage;
        Status = status;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
