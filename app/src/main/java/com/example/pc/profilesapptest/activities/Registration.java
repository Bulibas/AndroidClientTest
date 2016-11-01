package com.example.pc.profilesapptest.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pc.profilesapptest.R;
import com.example.pc.profilesapptest.Requests.RegistrationRequest;
import com.example.pc.profilesapptest.StaticFields;

//E/AndroidRuntime: FATAL EXCEPTION: main android.os.NetworkOnMainThreadException


public class Registration extends AppCompatActivity {
    private EditText mailEditText;
    private EditText passwordEditText;
    private EditText usernameEditText;

    private Button btnRegistration;
    private Button toLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mailEditText = (EditText)findViewById(R.id.newMailAddressText);
        passwordEditText = (EditText)findViewById(R.id.newPasswordText);
        usernameEditText = (EditText)findViewById(R.id.newUsernameText);

        btnRegistration = (Button)findViewById(R.id.btnRegister);
        toLogin = (Button)findViewById(R.id.toLogin);

        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StaticFields.username = usernameEditText.getText().toString();
                StaticFields.password = passwordEditText.getText().toString();
                StaticFields.mailAddress = mailEditText.getText().toString();
                Context context = getApplicationContext();

                RegistrationRequest registrationRequest = new RegistrationRequest(StaticFields.username,
                        StaticFields.password, StaticFields.mailAddress, context);
                Thread registrationThread = new Thread(registrationRequest);
                registrationThread.start();
            }
        });

        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Registration.this, Login.class);
                Registration.this.startActivity(i);
            }
        });

    }

    private void toMenu() {
        Intent i = new Intent(Registration.this, Menu.class);
        Registration.this.startActivity(i);
    }
}
