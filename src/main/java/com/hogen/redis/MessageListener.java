package com.hogen.redis;

import java.io.Serializable;
import java.util.Map;

public class MessageListener{
    public void handleMessage(String message){
        System.out.println(0);
    };
    public void handleMessage(Map message){
        System.out.println(1);
    };
    public void handleMessage(byte[] message){
        System.out.println(2);
    };
    public void handleMessage(Serializable message){
        System.out.println(3);
    };
    public void handleMessage(Serializable message, String channel){
        System.out.println(4);
    };
}
