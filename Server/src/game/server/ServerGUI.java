package game.server;

import game.server.ServerCommon;

import javax.swing.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class ServerGUI extends JFrame {
    private ServerSocket serverSocket = null;

    public ServerGUI() {
        try {
            serverSocket = new ServerSocket(2209);
        } catch (IOException io) {
            io.printStackTrace();
        }
        initialize();
    }
    
    private void initialize() {
        setTitle("Game 2048 Server");
        setBounds(550, 175, 300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel pnlServer = new JPanel();
        setContentPane(pnlServer);
        pnlServer.setLayout(null);

        JButton btnExit = new JButton("Exit");
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnExit.setBounds(115, 80, 70, 30);
        pnlServer.add(btnExit);
    }

    public void runServer() {
        setVisible(true);
        while (true) {
            System.out.println("Waiting for connection ...");
			String numberString = ServerCommon.genCellNumber();
            ServerCommon.sendMessage(serverSocket, numberString);
            System.out.println("Connect successful!");
            System.out.println("Playing ...");
            while(true) {
                String request = ServerCommon.receiveMessage(serverSocket);
                if (request.equals("EXIT")) break;
				numberString = ServerCommon.translate(request, numberString);
                ServerCommon.sendMessage(serverSocket, numberString);
            }
            System.out.println("Close connection complete!");
        }
    }
}
