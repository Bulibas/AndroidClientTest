package com.example.pc.profilesapptest.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pc.profilesapptest.R;
import com.example.pc.profilesapptest.Requests.AddFriendRequest;
import com.example.pc.profilesapptest.Requests.SearchRequest;

import org.w3c.dom.Text;

public class Search extends AppCompatActivity {
    private static TextView usernameFoundView;
    private static TextView mailFoundView;

    private Button searchButton;
    private Button addButton;
    private EditText searchEditText;
    private String input;

    public String getInput() {
        return this.input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        usernameFoundView = (TextView)findViewById(R.id.foundUsernameTextView);
        mailFoundView = (TextView)findViewById(R.id.foundMailTextView);
        final Context context = this;

        searchEditText = (EditText)findViewById(R.id.searchEditText);
        searchButton = (Button)findViewById(R.id.btnDatabaseSearch);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setInput(searchEditText.getText().toString());
                SearchRequest searchRequest = new SearchRequest(getInput(), context);
                Thread searchThread = new Thread(searchRequest);
                searchThread.start();
            }
        });

        addButton = (Button)findViewById(R.id.btnAdd);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddFriendRequest addFriendRequest =  new AddFriendRequest(getInput(), mailFoundView.getText().toString(), context);
                Thread addFriendThread = new Thread(addFriendRequest);
                addFriendThread.start();
            }
        });

    }
}
