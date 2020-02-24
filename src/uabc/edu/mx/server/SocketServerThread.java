package uabc.edu.mx.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

public class SocketServerThread extends Thread{
    private Socket socket;

    public SocketServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("Thread initialized");
        //read from socket to ObjectInputStream object
        InputStream inputStream = null;
        try {
            inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            //convert ObjectInputStream object to String
            String message = (String) objectInputStream.readObject();
            System.out.println("Message Received: " + message);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
