package com.example.pc.profilesapptest.activities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.pc.profilesapptest.R;
import com.example.pc.profilesapptest.Requests.AddPhotoRequest;

import java.io.ByteArrayOutputStream;

public class CameraCapture extends AppCompatActivity {
    public static final int REQUEST_CAPTURE = 1;
    private Bitmap bitmap;
    private ImageView image;
    private Button btnAdd, btnCapture;
    private String encodedString;

    public String getEncodedString() {
        return encodedString;
    }

    public void setEncodedString(String encodedString) {
        this.encodedString = encodedString;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_capture);

        image = (ImageView)findViewById(R.id.imageView);
        btnAdd =(Button)findViewById(R.id.btnAdd);
        btnCapture=(Button)findViewById(R.id.btnCapture);
        final Context context = this;

        if(!hasCamera()) {
            btnAdd.setEnabled(false);
            btnCapture.setEnabled(false);
        }

        btnAdd=(Button)findViewById(R.id.btnAddImage);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddPhotoRequest addPhotoRequest = new AddPhotoRequest(getEncodedString(),context);
                Thread addPhotoThread = new Thread(addPhotoRequest);
                addPhotoThread.start();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUEST_CAPTURE && resultCode==RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap photo= (Bitmap)extras.get("data");
            image.setImageBitmap(photo);

            convertPhoto(photo);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public boolean hasCamera(){
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    public void capturePhoto(View view){
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i,REQUEST_CAPTURE);
    }

    public void convertPhoto(Bitmap photo) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        photo.compress(Bitmap.CompressFormat.JPEG,100,stream);
        byte[] array = stream.toByteArray();
        encodedString = Base64.encodeToString(array, Base64.DEFAULT);
    }

}
