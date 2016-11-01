package com.example.pc.profilesapptest.Requests;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.example.pc.profilesapptest.R;
import com.example.pc.profilesapptest.StaticFields;
import com.example.pc.profilesapptest.implementations.Friend;
import com.example.pc.profilesapptest.interfaces.Requestable;

import java.io.IOException;

/**
 * Created by PC on 13.10.2016 г..
 */
public class AddFriendRequest extends Activity implements Requestable {
    private static final String COMMAND = "add";
    private String friendUsername;
    private String friendMail;
    private Context context;


    public AddFriendRequest(String friendUsername, String friendMail, Context context) {
        this.setFriendUsername(friendUsername);
        this.setFriendMail(friendMail);
        this.setContext(context);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    String getFriendMail() {
        return friendMail;
    }

    public void setFriendMail(String friendMail) {
        this.friendMail = friendMail;
    }

    public String getFriendUsername() {
        return friendUsername;
    }

    public void setFriendUsername(String friendUsername) {
        this.friendUsername = friendUsername;
    }

    @Override
    public void run() {
        try {
            sendClientRequest();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendClientRequest() throws IOException {
        final String response;
        String dbString = this.getFriendUsername()+","+this.getFriendMail()+";";

        StaticFields.printout.println(COMMAND);
        StaticFields.printout.println(dbString);
        StaticFields.printout.flush();


        response =StaticFields.reader.readLine();
        runOnUiThread(  new Runnable() {
            @Override
            public void run() {
                updateResponseView(response);
            }
        });
        Log.e("response",response);

        /**/
        FriendsRequest friendsRequest = new FriendsRequest();
        friendsRequest.run();
    }

    void updateResponseView(String response) {
        TextView responseTextView = (TextView)((Activity)getContext()).findViewById(R.id.responseTextView);
        responseTextView.setText(response);
    }
}
