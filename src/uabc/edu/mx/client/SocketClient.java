package uabc.edu.mx.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {

    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{

        //get the localhost IP address, if server is running on some other IP, you need to use that
        Socket socket = null;
        ObjectOutputStream objectOutputStream = null;
        ObjectInputStream objectInputStream = null;

        //establish socket connection to server
        socket = new Socket("ec2-3-81-158-158.compute-1.amazonaws.com", 9876);

        //write to socket using ObjectOutputStream
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        System.out.println("Sending request to Socket Server");
        objectOutputStream.writeObject("Hola mundo");

        //Close communications
        objectOutputStream.close();
        Thread.sleep(100);
    }
}
