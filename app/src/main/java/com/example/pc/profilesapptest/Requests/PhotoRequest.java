package com.example.pc.profilesapptest.Requests;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pc.profilesapptest.R;
import com.example.pc.profilesapptest.StaticFields;
import com.example.pc.profilesapptest.interfaces.Requestable;

import java.io.IOException;

/**
 * Created by PC on 15.10.2016 г..
 */
public class PhotoRequest extends Activity implements Requestable {
    private static final String COMMAND = "photo";
    public Bitmap bitmap;
    private Context context;
    private String username;
    private ImageView imageView;

    public PhotoRequest(Context context, String username, ImageView imageView) {
        this.setUsername(username);
        this.setContext(context);
        this.setImageView(imageView);
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void sendClientRequest() throws IOException {
        StaticFields.printout.println(COMMAND);
        StaticFields.printout.println(username);
        StaticFields.printout.flush();

        String encodedString;
        encodedString= StaticFields.reader.readLine();
        if(!encodedString.equals("no picture")) {
            setBitmap(StaticFields.convertStringToBitmap(encodedString));
            if(getBitmap()!=null) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getImageView().setImageBitmap(getBitmap());
                    }
                });
            }
        }
    }

    @Override
    public void run() {
        try {
            sendClientRequest();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
