package com.example.pc.profilesapptest.activities;

//java nio - predimstva i nedostatuci
//websocket

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pc.profilesapptest.R;
import com.example.pc.profilesapptest.Requests.FriendsRequest;
import com.example.pc.profilesapptest.Requests.LoginRequest;
import com.example.pc.profilesapptest.StaticFields;

public class Login extends AppCompatActivity{

    private Button btnLogin;
    private Button toRegistration;

    private EditText textViewUsername;
    private EditText textViewPassword;
    private TextView errorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textViewPassword = (EditText)findViewById(R.id.textViewPassword);
        textViewUsername = (EditText)findViewById(R.id.textViewUsername);
        errorTextView = (TextView) findViewById(R.id.errorTextView);

        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StaticFields.username = textViewUsername.getText().toString();
                StaticFields.password = textViewPassword.getText().toString();
                Context context = getApplicationContext();

                LoginRequest loginRequest = new LoginRequest(StaticFields.username, StaticFields.password, context);
                Thread loginThread = new Thread(loginRequest);
                loginThread.start();
            }
        });

        toRegistration = (Button)findViewById(R.id.toRegistration);
        toRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toRegistration();
            }
        });
    }

    private void toMenu() {
        Intent menu = new Intent(Login.this, Menu.class);
        Login.this.startActivity(menu);
    }

    private void toRegistration() {
        Intent toReg = new Intent(Login.this, Registration.class);
        Login.this.startActivity(toReg);
    }

}
