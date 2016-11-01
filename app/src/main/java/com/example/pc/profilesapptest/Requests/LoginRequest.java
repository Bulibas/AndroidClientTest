package com.example.pc.profilesapptest.Requests;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.pc.profilesapptest.StaticFields;
import com.example.pc.profilesapptest.activities.Menu;
import com.example.pc.profilesapptest.interfaces.Requestable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by PC on 4.10.2016 г..
 */
public class LoginRequest extends Activity implements Requestable {
    private static final String COMMAND = "log";
   // public static PrintStream printout=null;
    private String username, password;
    private boolean logged=false;
    private Context context;

    public LoginRequest(String username, String password, Context context) {
        this.setUsername(username);
        this.setPassword(password);
        this.setContext(context);
    }

    public void setContext(Context context) {
        this.context=context;
    }

    public Context getContext() {
        return this.context;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getLogged () {
        return this.logged;
    }

    private void setLogged (boolean logged) {
        this.logged=logged;
    }

    @Override
    public void sendClientRequest() throws IOException {
        StaticFields.socket = new Socket(URL, PORT);
        String response;

        StaticFields.printout = new PrintStream(StaticFields.socket.getOutputStream());
        StaticFields.reader = new BufferedReader(new InputStreamReader(StaticFields.socket.getInputStream()));

        StaticFields.printout.println(COMMAND);
        StaticFields.printout.println(this.username);
        StaticFields.printout.println(this.password);
        StaticFields.printout.flush();

        response = StaticFields.reader.readLine();
        if(response.equals("logged")) {
            setLogged(true);
            toMenu();
        }
        else if(response.equals("not logged")) {
            this.setLogged(false);
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
    public void toMenu() {
        Intent intent = new Intent(getContext(), Menu.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("username", this.getUsername());
        getContext().startActivity(intent);
    }
}
