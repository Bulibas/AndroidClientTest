package com.example.pc.profilesapptest.Requests;

import android.util.Log;

import com.example.pc.profilesapptest.StaticFields;
import com.example.pc.profilesapptest.implementations.Friend;
import com.example.pc.profilesapptest.interfaces.Requestable;

import java.io.IOException;


public class FriendsRequest implements Requestable{
    private static final String COMMAND = "friends";

    @Override
    public void sendClientRequest() throws IOException {
        StaticFields.printout.println(COMMAND);
        StaticFields.printout.flush();

        String friendsString = StaticFields.reader.readLine();
        if(!friendsString.equals("not found")) {
           friendsString = friendsString.substring(0,friendsString.length()-1);
            manageFriendsString(friendsString);
        }
        Log.e("response", friendsString);
    }

    @Override
    public void run() {
        try {
            sendClientRequest();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void manageFriendsString(String friendsString) {
        String friendUsername, friendMail;
        String[] friendsList = friendsString.split(";");

        StaticFields.friendsList.clear();
        for (int i = 0; i < friendsList.length; i++) {
            String[] friendString = friendsList[i].split(",");
            friendUsername=friendString[0];
            friendMail=friendString[1];
            Friend friend = new Friend(friendUsername,friendMail);
            StaticFields.friendsList.add(friend);
        }
    }
}
