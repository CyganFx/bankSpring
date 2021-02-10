package com.example.bankSpring.threads;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String args[]) throws IOException, ClassNotFoundException {

        int port = 9876;
        ServerSocket server = new ServerSocket(port);
        while (true) {
            System.out.println("Waiting for the client request");
            Socket socket = server.accept();
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            String message = (String) ois.readObject();
            System.out.println("Message Received: " + message);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject("Hi Client, server received your request, sorry for such inconvenience, we will try to do better!");
            ois.close();
            oos.close();
            socket.close();
            if (message.equalsIgnoreCase("exit")) break;
        }
        System.out.println("Shutting down Socket server!!");
        server.close();
    }

}