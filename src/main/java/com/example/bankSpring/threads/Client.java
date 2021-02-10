package com.example.bankSpring.threads;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client extends Thread {

    public String processComplaintAndGetResponse(String complaintMessage) throws IOException, ClassNotFoundException, InterruptedException {
        //get the localhost IP address, if server is running on some other IP, you need to use that
        InetAddress host = InetAddress.getLocalHost();
        Socket socket;
        ObjectInputStream ois;
        ObjectOutputStream oos;
        //establish socket connection to server
        socket = new Socket(host.getHostName(), 9876);
        //write to socket using ObjectOutputStream
        oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(complaintMessage);
        System.out.println("Sending request to Socket Server");
        Thread.sleep(2000);
        //read the server response message
        ois = new ObjectInputStream(socket.getInputStream());
        String message = (String) ois.readObject();
        System.out.println("Message: " + message);
        //close resources
        ois.close();
        oos.close();
        return message;
    }
}