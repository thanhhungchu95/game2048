package game.client;

import game.client.ClientGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ConnectForm extends JFrame {
    
    public ConnectForm() {
        initialize();
    }

    private void initialize() {
        this.setTitle("Connect Form");
        this.setBounds(550, 225, 300, 150);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel pnlForm = new JPanel();
        this.getContentPane().add(pnlForm, BorderLayout.CENTER);
        pnlForm.setLayout(null);

        JLabel lblIPAddress = new JLabel("IP addr");
        lblIPAddress.setHorizontalAlignment(SwingConstants.CENTER);
        lblIPAddress.setVerticalAlignment(SwingConstants.CENTER);
        lblIPAddress.setBounds(3, 3, 74, 34);
        pnlForm.add(lblIPAddress);

        JLabel lblPortNumber = new JLabel("Port");
        lblPortNumber.setHorizontalAlignment(SwingConstants.CENTER);
        lblPortNumber.setVerticalAlignment(SwingConstants.CENTER);
        lblPortNumber.setBounds(3, 43, 74, 34);
        pnlForm.add(lblPortNumber);

        final JTextField txtIPAddress = new JTextField();
        txtIPAddress.setBounds(83, 5, 210, 30);
        pnlForm.add(txtIPAddress);

        final JTextField txtPortNumber = new JTextField("2209");
        txtPortNumber.setBounds(83, 45, 210, 30);
        txtPortNumber.setEditable(false);
        pnlForm.add(txtPortNumber);

        JButton btnConnect = new JButton("Conn.");
        btnConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientGUI gui = new ClientGUI();
                gui.setIPAddress(txtIPAddress.getText());
                gui.setPortNumber(Integer.parseInt(txtPortNumber.getText()));
                gui.startGame();
                dispose();
            }
        });
        btnConnect.setBounds(24, 85, 79, 35);
        pnlForm.add(btnConnect);

        JButton btnReset = new JButton("Reset");
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtIPAddress.setText("");
            }
        });
        btnReset.setBounds(108, 85, 79, 35);
        pnlForm.add(btnReset);

        JButton btnClose = new JButton("Close");
        btnClose.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnClose.setBounds(192, 85, 79, 35);
        pnlForm.add(btnClose);
    }

    public void runForm() {
        this.setVisible(true);
    }
}
