package com.example.pc.profilesapptest.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.pc.profilesapptest.R;
import com.example.pc.profilesapptest.Requests.FriendsRequest;
import com.example.pc.profilesapptest.StaticFields;
import com.example.pc.profilesapptest.implementations.Friend;
import com.example.pc.profilesapptest.implementations.PersonAdapter;

import java.util.ArrayList;
import java.util.List;

public class FriendsManager extends AppCompatActivity {

    ListView listView;
    private PersonAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_manager);

        listView = (ListView)findViewById(R.id.friendsList);
        adapter = new PersonAdapter(getApplicationContext());
        listView.setAdapter(adapter);
    }

    /*public void createListView() {
        for (Friend friend: StaticFields.friendsList) {
            namesList.add(friend.getUsername());
        }

    }*/
}
