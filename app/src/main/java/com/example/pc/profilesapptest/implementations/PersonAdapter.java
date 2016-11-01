package com.example.pc.profilesapptest.implementations;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pc.profilesapptest.R;
import com.example.pc.profilesapptest.Requests.PhotoRequest;
import com.example.pc.profilesapptest.StaticFields;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by PC on 12.10.2016 г..
 */
public class PersonAdapter extends BaseAdapter {
    private Context context;

    public PersonAdapter(Context context) {
        this.context=context;

    }

    @Override
    public int getCount() {
        return StaticFields.friendsList.size();
    }

    @Override
    public Object getItem(int i) {
        return StaticFields.friendsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view.inflate(context, R.layout.single_row,null);
        TextView nameTextView = (TextView)v.findViewById(R.id.foundUsernameTextView);
        TextView mailTextView =(TextView)v.findViewById(R.id.foundMailTextView);
        ImageView profilPicture = (ImageView)v.findViewById(R.id.profilePicture);

        nameTextView.setText(StaticFields.friendsList.get(i).getUsername());
        mailTextView.setText(StaticFields.friendsList.get(i).getMail());

        PhotoRequest photoRequest = new PhotoRequest(getContext(),
                StaticFields.friendsList.get(i).getUsername(),profilPicture);
        Thread photoThread = new Thread(photoRequest);
        photoThread.start();

        return v;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
