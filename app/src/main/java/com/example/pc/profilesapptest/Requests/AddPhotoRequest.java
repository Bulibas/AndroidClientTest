package com.example.pc.profilesapptest.Requests;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.TextView;

import com.example.pc.profilesapptest.R;
import com.example.pc.profilesapptest.StaticFields;
import com.example.pc.profilesapptest.activities.CameraCapture;
import com.example.pc.profilesapptest.interfaces.Requestable;

import org.w3c.dom.Text;

import java.io.IOException;

/**
 * Created by PC on 15.10.2016 г..
 */
public class AddPhotoRequest extends Activity implements Requestable {
    private static final String COMMAND = "addPhoto";
    private String encodedString;
    private Context context;

    public AddPhotoRequest(String encodedString, Context context) {
        this.setContext(context);
        this.setEncodedString(encodedString);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getEncodedString() {
        return encodedString;
    }

    public void setEncodedString(String encodedString) {
        this.encodedString = encodedString;
    }

    @Override
    public void sendClientRequest() throws IOException {
        final String response;

        StaticFields.printout.println(COMMAND);
        StaticFields.printout.println(getEncodedString());
        StaticFields.printout.flush();
        StaticFields.printout.close();

        Log.e("string", getEncodedString());

        response =StaticFields.reader.readLine();
        /*runOnUiThread(new Runnable() {
            @Override
            public void run() {
                updateResponseView(response);
            }
        });*/
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

    void updateResponseView(String response) {
        TextView responseTextView = (TextView)((Activity)getContext()).findViewById(R.id.photoResponseTextView);
        responseTextView.setText(response);
    }
}
