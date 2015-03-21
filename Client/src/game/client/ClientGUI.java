package game.client;

import game.client.ClientCommon;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.net.*;

public class ClientGUI extends JFrame {
    private int score = 0;
    private String IPAddress = "localhost";
    private int PortNumber = 2209;

    private String key = "NONE";

    private Font numberFont;
    private Color cellColor[] = new Color[12];
    
    private JLabel lblNumber[] = new JLabel[16];

    private Socket client;

    public ClientGUI() {
        initialize();

    }
    
    // Set method for variable 'IPAddress'
    public void setIPAddress(String IPAddress) {
        this.IPAddress = IPAddress;
    }
    // Get method for variable 'IPAddress'
    public String getIPAddress() {
        return this.IPAddress;
    }

    // Set method for variable 'PortNumber'
    public void setPortNumber(int PortNumber) {
        this.PortNumber = PortNumber;
    }
    // Get method for variable 'PortNumber'
    public int getPortNumber() {
        return this.PortNumber;
    }


    private void initialize() {
        setTitle("Game 2048");
        setBounds(500, 100, 405, 425);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        client = null;

        JPanel pnlGame = new JPanel();
        this.setContentPane(pnlGame);
        pnlGame.setLayout(null);

        cellColor[0] = new Color(0, 199, 254);
        cellColor[1] = new Color(0, 150, 255);
        cellColor[2] = new Color(0, 101, 255);
        cellColor[3] = new Color(50, 0, 255);
        cellColor[4] = new Color(101, 0, 200);
        cellColor[5] = new Color(29, 198, 12);
        cellColor[6] = new Color(200, 0, 99);
        cellColor[7] = new Color(254, 0, 0);
        cellColor[8] = new Color(200, 50, 0);
        cellColor[9] = new Color(0, 100, 0);
        cellColor[10] = new Color(232, 43, 87);
        cellColor[11] = new Color(138, 51, 217);   
        
        numberFont = new Font("SanSerif", Font.CENTER_BASELINE, 25);

        for (int i = 0; i < 16; i++) {
            lblNumber[i] = new JLabel("0");
            lblNumber[i].setBounds((i % 4) * 100, (i / 4) * 100, 100, 100);
            lblNumber[i].setHorizontalAlignment(JLabel.CENTER);
            lblNumber[i].setForeground(Color.WHITE);
            lblNumber[i].setBackground(cellColor[0]);
            lblNumber[i].setOpaque(true);
            lblNumber[i].setBorder(new LineBorder(Color.BLACK));
            lblNumber[i].setFont(numberFont);
            pnlGame.add(lblNumber[i]);
        }
    }

    private void setNumber(String stringNumber) {
        int count = 0, point = 0;
		for (int index = 0; index < stringNumber.length(); index++) {
			if (stringNumber.charAt(index) == ';') {
				String tmp = stringNumber.substring(point, index);
				int iTmp = Integer.parseInt(tmp);
				if (iTmp != 0) {
					lblNumber[count].setText(tmp);
					lblNumber[count].setBackground(cellColor[(int)(Math.log10(iTmp)/Math.log10(2))]);
				}
				else {
					lblNumber[count].setText("");
					lblNumber[count].setBackground(cellColor[0]);
				}
				point = index + 1;
				count ++;
			}
		}
    }

 
    public void startGame() {
        client = ClientCommon.openSocket(IPAddress, PortNumber);
        setVisible(true);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP: 
                        System.out.println("Up");
                        break;
                    case KeyEvent.VK_DOWN:
                        System.out.println("Down");
                        break;
                    case KeyEvent.VK_LEFT:
                        System.out.println("Left");
                        break;
                    case KeyEvent.VK_RIGHT:
                        System.out.println("Right");
                        break;
                    default:
                        System.out.println("None");
                        break;
                }
            }
        });
    }
}
