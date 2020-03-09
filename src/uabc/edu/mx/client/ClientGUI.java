package uabc.edu.mx.client;

import javax.swing.*;
import javax.swing.event.AncestorListener;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientGUI extends JDialog {
    //get the localhost IP address, if server is running on some other IP, you need to use that
    Socket socket = null;
    ObjectOutputStream objectOutputStream = null;
    ObjectInputStream objectInputStream = null;

    private JPanel contentPane;
    private JButton buttonConnect;
    private JButton buttonDisconnect;
    private JButton buttonSend;
    private JTextField textFieldMessage;
    private JTextArea textAreaMessages;

    public ClientGUI() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonConnect);

        buttonConnect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onConnect();
            }
        });

        buttonDisconnect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onDisconnect();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onDisconnect();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onDisconnect();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        buttonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //write to socket using ObjectOutputStream
                try {
                    socket = new Socket("localhost", 9876);

                    objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                    objectOutputStream.writeObject(textFieldMessage.getText());
                    System.out.println("Sending request to Socket Server: " + textFieldMessage.getText() );

                    objectInputStream = new ObjectInputStream(socket.getInputStream());
                    String message = (String) objectInputStream.readObject();
                    textAreaMessages.append("El servidor responde: " + message + "\n");
                    System.out.println("El servidor responde: " + message);

                    //Close communications
                    objectOutputStream.close();
                    objectInputStream.close();

                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }


            }
        });
    }

    private void onConnect() {
        // add your code here

    }

    private void onDisconnect() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        ClientGUI dialog = new ClientGUI();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

}
