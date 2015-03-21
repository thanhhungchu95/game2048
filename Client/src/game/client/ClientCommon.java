package game.client;

import java.net.*;
import java.io.*;
import javax.swing.JOptionPane;

public final class ClientCommon {
    private ClientCommon() {}

    public static Socket openSocket(String IPAddress, int PortNumber) {
        Socket socket = null;
        try {
            socket = new Socket(IPAddress, PortNumber);
        } catch(IOException io) {
            JOptionPane.showConfirmDialog(null, "Server not running :((", "Error", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        return socket;
    }

    public static void closeSocket(Socket socket) {
        try {
            socket.close();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public static void sendMessage(Socket socket, String message) {
        try {
            PrintStream sending = new PrintStream(socket.getOutputStream(), true);
            sending.print(message);
            sending.close();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public static String receiveMessage(Socket socket) {
        String message = null;
        try {
            BufferedReader receiving = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            message = receiving.readLine();
            receiving.close();
        } catch (IOException io) {
            io.printStackTrace();
        }
        return message;
    } 
}
