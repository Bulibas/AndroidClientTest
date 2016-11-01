package com.example.pc.profilesapptest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.example.pc.profilesapptest.implementations.Friend;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 10.10.2016 г..
 */
public class StaticFields {
    public static Socket socket=null;
    public static PrintStream printout=null;
    public static BufferedReader reader=null;

    public static List<Friend> friendsList=new ArrayList<>();

    public static String username;
    public static String password;
    public static String mailAddress;

    public static Bitmap convertStringToBitmap(String string){
        byte[] byteArray = Base64.decode(string,Base64.NO_PADDING);
        final Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);
        return bitmap;
    }

    //IS IT GOOD PRACTICE?

}
