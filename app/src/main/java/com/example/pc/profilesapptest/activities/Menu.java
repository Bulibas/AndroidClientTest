package com.example.pc.profilesapptest.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pc.profilesapptest.R;
import com.example.pc.profilesapptest.Requests.FriendsRequest;
import com.example.pc.profilesapptest.Requests.PhotoRequest;
import com.example.pc.profilesapptest.StaticFields;

public class Menu extends AppCompatActivity {

    private Button friendsButton;
    private Button searchButton;
    private Button cameraButton;
    private TextView welcomeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = this;

        String username = getIntent().getExtras().getString("username");
        welcomeTextView = (TextView) findViewById(R.id.welcomeTextView);
        welcomeTextView.setText("Welcome, " + username + "!");

        friendsButton = (Button) findViewById(R.id.btnFriends);
        friendsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Menu.this, FriendsManager.class);
                Menu.this.startActivity(i);
            }
        });

        searchButton = (Button) findViewById(R.id.btnSearch);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Menu.this, Search.class);
                Menu.this.startActivity(i);
            }
        });

        cameraButton = (Button)findViewById(R.id.btnCameraOpen);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Menu.this, CameraCapture.class);
                Menu.this.startActivity(i);
            }
        });

        ImageView profilePhoto = (ImageView)findViewById(R.id.profileImageView);
        loadProfilePhoto(profilePhoto,username, context);

        loadFriendsData();

    }

    private void loadProfilePhoto(ImageView imageView, String username, Context context){
        PhotoRequest photoRequest = new PhotoRequest(context, username,imageView);
        Thread photoThread = new Thread(photoRequest);
        photoThread.start();
    }

    private void loadFriendsData() {
        if(StaticFields.friendsList.isEmpty()){
            FriendsRequest friendsRequest = new FriendsRequest();
            Thread friendsThread = new Thread(friendsRequest);
            friendsThread.start();
        }
    }
}
