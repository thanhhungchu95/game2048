package game.client;

import java.net.*;
import java.io.*;

public final class ClientCommon {
    private ClientCommon() {}

    public static void openSocket(Socket socket, String IPAddress, int PortNumber) {
        try {
            ClientCommon.closeSocket(socket);
            socket = new Socket(IPAddress, PortNumber);
        } catch(IOException io) {
            io.printStackTrace();
        }
    }

    public static void closeSocket(Socket socket) {
        try {
            if (socket.isConnected()) {
                socket.close();
            }
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
