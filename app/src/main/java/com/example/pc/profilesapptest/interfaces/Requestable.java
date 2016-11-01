package com.example.pc.profilesapptest.interfaces;

import java.io.IOException;

//TODO SINGLETON

/**
 * Created by PC on 4.10.2016 г..
 */
public interface Requestable extends Runnable {
    static final Integer PORT = 8080;
    static final String URL = "192.168.0.106";
//      static final String URL = "192.168.0.25";

    //static final String URL = "192.168.43.97";

    void sendClientRequest() throws IOException;
}