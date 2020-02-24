package uabc.edu.mx.server;

import java.io.*;
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
        OutputStream outputstream = null;
        try {
            inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            //convert ObjectInputStream object to String
            String message = (String) objectInputStream.readObject();
            System.out.println("Message Received: " + message);

            outputstream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputstream);
            String response = "Me estas preguntando: " + message;
            objectOutputStream.writeObject(response);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
