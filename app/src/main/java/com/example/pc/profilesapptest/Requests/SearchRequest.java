package com.example.pc.profilesapptest.Requests;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pc.profilesapptest.R;
import com.example.pc.profilesapptest.StaticFields;
import com.example.pc.profilesapptest.implementations.Friend;
import com.example.pc.profilesapptest.interfaces.Requestable;

import java.io.IOException;
import java.io.PrintStream;

/**
 * Created by PC on 11.10.2016 г..
 */
public class SearchRequest extends Activity implements Requestable {
    private static final String COMMAND = "search";
    private String input;
    private Context context;

    public SearchRequest(String input, Context context) {
        this.setContext(context);
        this.setInput(input);
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void sendClientRequest() throws IOException {
        String response;
        final String username, mail;

        StaticFields.printout.println(COMMAND);
        StaticFields.printout.println(this.getInput());
        StaticFields.printout.flush();

        response =StaticFields.reader.readLine();
        if(!response.equals("not found")) {
            username=StaticFields.reader.readLine();
            mail=StaticFields.reader.readLine();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ImageView responseImageView = (ImageView)((Activity)getContext()).findViewById(R.id.profilePicture);

                    PhotoRequest photoRequest = new PhotoRequest(getContext(),getInput(),responseImageView);
                    Thread photoThread = new Thread(photoRequest);
                    photoThread.start();
                    Bitmap bitmap = photoRequest.bitmap;

                    foundUpdateView(username, mail);
                }
            });
        }
        else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    notFoundUpdateView("User not found!");
                }
            });
        }
        Log.e("response",response);
    }

    @Override
    public void run() {
        try {
            sendClientRequest();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void foundUpdateView(String username, String mail) {
        TextView nameTextView = (TextView)((Activity)context).findViewById(R.id.foundUsernameTextView);
        TextView mailTextView = (TextView)((Activity)context).findViewById(R.id.foundMailTextView);
        TextView responseTextView = (TextView)((Activity)getContext()).findViewById(R.id.responseTextView);

        nameTextView.setText(username);
        mailTextView.setText(mail);
        responseTextView.setText("User found!");

    }
    void notFoundUpdateView(String response) {
        TextView responseTextView = (TextView)((Activity)getContext()).findViewById(R.id.responseTextView);
        responseTextView.setText(response);
    }


}














