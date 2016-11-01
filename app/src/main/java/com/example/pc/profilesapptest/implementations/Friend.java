package com.example.pc.profilesapptest.implementations;

import android.graphics.Bitmap;

import com.example.pc.profilesapptest.enums.FriendType;

import java.util.List;

/**
 * Created by PC on 27.9.2016 г..
 */
public class Friend {

    private String username;
    private String mail;
    private String lastChatMessage;
    private Bitmap profilePicture;
    private FriendType friendType;
    private List<Message> messages;

    public Friend(String username, String mail) {
        this.setUsername(username);
        this.setMail(mail);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
