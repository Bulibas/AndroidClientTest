package com.example.pc.profilesapptest.Requests;

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
import java.util.InputMismatchException;

/**
 * Created by PC on 4.10.2016 г..
 */
public class RegistrationRequest implements Requestable{
    private static final String COMMAND = "reg";
    private Context context;

    private String username, mail, password;
    private boolean isRegistered;

    public RegistrationRequest(String username, String password, String mail, Context context) {
        this.setUsername(username);
        this.setPassword(password);
        this.setMail(mail);
        this.setContext(context);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public boolean getIsRegistered () {
        return isRegistered;
    }

    public void setIsRegistered (boolean isRegistered) {
        this.isRegistered =isRegistered;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public  void run() {
        try {
            sendClientRequest();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendClientRequest() throws IOException {
        StaticFields.socket = new Socket(URL, PORT);
        StaticFields.printout = new PrintStream(StaticFields.socket.getOutputStream());
        StaticFields.reader = new BufferedReader(new InputStreamReader(StaticFields.socket.getInputStream()));
        String response;

        try {
            StaticFields.printout.println(COMMAND);
            StaticFields.printout.println(this.username);
            StaticFields.printout.println(this.password);
            StaticFields.printout.println(this.mail);
            StaticFields.printout.flush();

            response = StaticFields.reader.readLine();
            if(response.equals("registered")) {
                this.setIsRegistered(true);
                toMenu();
            }
            else if (response.equals("not registered")){
                this.setIsRegistered(false);
            }
            Log.e("response",response);

        } catch (InputMismatchException e){
            System.out.println();
        } finally {
            //if(s!=null) {
                //s.close();
            //}
            //some closing
            /*if(reader!=null) {
                reader.close();
            }
            if(printout!=null) {
                printout.close();
            }*/
        }
    }

    public void toMenu() {
        Intent intent = new Intent(getContext(), Menu.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("username", this.getUsername());
        getContext().startActivity(intent);
    }

}
