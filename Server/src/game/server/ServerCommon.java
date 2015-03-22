package game.server;

import java.net.*;
import java.io.*;

public final class ServerCommon {
    private static int[][] cell = new int[4][4];

    private ServerCommon() {}

    public static String genCellNumber() {
        int x_1 = ServerCommon.random() % 4;
		int y_1 = ServerCommon.random() % 4;
		int x_2 = ServerCommon.random() % 4;
		int y_2 = ServerCommon.random() % 4;
		if (x_2 == x_1) {
			while (y_2 == y_1) {
				y_2 = ServerCommon.random() % 4;
			}
		}
		String cellNumber = "";
		for (int i = 0; i < 16; i++) {
			if (x_1 * 4 + y_1 == i) {
				int value = ServerCommon.random() % 10;
				if (value % 5 == 0) cellNumber += "4;";
				else cellNumber += "2;";
			}
			else {
				if (x_2 * 4 + y_2 == i) {
					int value = ServerCommon.random() % 10;
					if (value % 5 == 0) cellNumber += "4;";
					else cellNumber += "2;";
				}
				else cellNumber += "0;";
			}
		}
		return cellNumber;

    }
        
    public static String translate(String key, String numberString) {
        ServerCommon.stringToCell(numberString);
        if (key.equals("UP")) ServerCommon.cellToUp();
        if (key.equals("DOWN")) ServerCommon.cellToDown();
        if (key.equals("LEFT")) ServerCommon.cellToLeft();
        if (key.equals("RIGHT")) ServerCommon.cellToRight();
        if (key.equals("NONE")) {}
        return ServerCommon.cellToString();
    }

    private static void stringToCell(String string) {
        int column = 0, row = 0, point = 0;
        for (int index = 0; index < string.length(); index++) {
            if (string.charAt(index) == ';') {
                cell[row][column] = Integer.parseInt(string.substring(point, index));
                point = index + 1;
                column++;
                if (column == 4) {
                    column = 0;
                    row++;
                }
                if (row == 4) break;
            }
        }
    }

    private static String cellToString() {
        String string = "";
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                string += (String.valueOf(cell[row][column]) + ";");
            }   
        }
        return string;
    }

	private static void cellToUp() {
		boolean isChanged = false;
		for (int i = 0; i < 4; i++) {
			for (int j = 2; j >= 0; j--) {
				if (cell[j][i] == 0) {
					for (int k = j + 1; k < 4; k++) {
						if (cell[k][i] != 0) isChanged = true;
						cell[k - 1][i] = cell[k][i];
					}
					cell[3][i] = 0;
				}
			}
			if (cell[0][i] == cell[1][i] && cell[0][i] != 0) {
				cell[0][i] *= 2;
				isChanged = true;
				if (cell[2][i] == cell[3][i] && cell[2][i] != 0) {
					cell[1][i] = cell[2][i] * 2;
					cell[2][i] = cell[3][i] = 0;
				}
				else {
					cell[1][i] = cell[2][i];
					cell[2][i] = cell[3][i];
					cell[3][i] = 0;
				}
			}
			if (cell[1][i] == cell[2][i] && cell[1][i] != 0) {
				cell[1][i] *= 2;
				isChanged = true;
				cell[2][i] = cell[3][i];
				cell[3][i] = 0;
			}
			if (cell[2][i] == cell[3][i] && cell[2][i] != 0) {
				cell[2][i] *= 2;
				isChanged = true;
				cell[3][i] = 0;
			}
		}
		if (isChanged == true) ServerCommon.generateCell();
	}
	
	private static void cellToDown() {
		boolean isChanged = false;
		for (int i = 0; i < 4; i++) {
			for (int j = 1; j <=3; j++) {
				if (cell[j][i] == 0) {
					for (int k = j - 1; k >= 0; k--) {
						if (cell[k][i] != 0) isChanged = true;
						cell[k + 1][i] = cell[k][i];
					}
					cell[0][i] = 0;
				}
			}
			if (cell[3][i] == cell[2][i] && cell[3][i] != 0) {
				isChanged = true;
				cell[3][i] *= 2;
				if (cell[1][i] == cell[0][i] && cell[1][i] != 0) {
					cell[2][i] = cell[1][i] * 2;
					cell[1][i] = cell[0][i] = 0;
				}
				else {
					cell[2][i] = cell[1][i];
					cell[1][i] = cell[0][i];
					cell[0][i] = 0;
				}
			}
			if (cell[2][i] == cell[1][i] && cell[2][i] != 0) {
				cell[2][i] *= 2;
				isChanged = true;
				cell[1][i] = cell[0][i];
				cell[0][i] = 0;
			}
			if (cell[1][i] == cell[0][i] && cell[1][i] != 0) {
				cell[1][i] *= 2;
				isChanged = true;
				cell[0][i] = 0;
			}
		}
		if (isChanged == true) ServerCommon.generateCell();
	}

	private static void cellToLeft() {
		boolean isChanged = false;
		for (int i = 0; i < 4; i++) {
			for (int j = 2; j >= 0; j--) {
				if (cell[i][j] == 0) {
					for (int k = j + 1; k < 4; k++) {
						if (cell[k][i] != 0) isChanged = true;
						cell[i][k - 1] = cell[i][k];
					}
					cell[i][3] = 0;
				}
			}
			if (cell[i][0] == cell[i][1] && cell[i][0] != 0) {
				cell[i][0] *= 2;
				isChanged = true;
				if (cell[i][2] == cell[i][3] && cell[i][2] != 0) {
					cell[i][1] = cell[i][2] * 2;
					cell[i][2] = cell[i][3] = 0;
				}
				else {
					cell[i][1] = cell[i][2];
					cell[i][2] = cell[i][3];
					cell[i][3] = 0;
				}
			}
			if (cell[i][1] == cell[i][2] && cell[i][1] != 0) {
				cell[i][1] *= 2;
				isChanged = true;
				cell[i][2] = cell[i][3];
				cell[i][3] = 0;
			}
			if (cell[i][2] == cell[i][3] && cell[i][2] != 0) {
				cell[i][2] *= 2;
				isChanged = true;
				cell[i][3] = 0;
			}
		}
		if (isChanged == true) ServerCommon.generateCell();
	}

	private static void cellToRight() {
		boolean isChanged = false;
		for (int i = 0; i < 4; i++) {
			for (int j = 1; j <= 3; j++) {
				if (cell[i][j] == 0) {
					for (int k = j - 1; k >= 0; k--) {
						if (cell[k][i] != 0) isChanged = true;
						cell[i][k + 1] = cell[i][k];
					}
					cell[i][0] = 0;
				}
			}
			if (cell[i][3] == cell[i][2] && cell[i][3] != 0) {
				cell[i][3] *= 2;
				isChanged = true;
				if (cell[i][1] == cell[i][0] && cell[i][1] != 0) {
					cell[i][2] = cell[i][1] * 2;
					cell[i][1] = cell[i][0] = 0;
				}
				else {
					cell[i][2] = cell[i][1];
					cell[i][1] = cell[i][0];
					cell[i][0] = 0;
				}
			}
			if (cell[i][2] == cell[i][1] && cell[i][2] != 0) {
				cell[i][2] *= 2;
				isChanged = true;
				cell[i][1] = cell[i][0];
				cell[i][0] = 0;
			}
			if (cell[i][1] == cell[i][0] && cell[i][1] != 0) {
				cell[i][1] *= 2;
				isChanged = true;
				cell[i][0] = 0;
			}
		}
		if (isChanged == true) ServerCommon.generateCell();
	}

	private static void generateCell() {
		int row = ServerCommon.random() % 4;
		int column = ServerCommon.random() % 4;
		while(cell[row][column] != 0) {
			row = ServerCommon.random() % 4;
			column = ServerCommon.random() % 4;
		}	
		int tmp = ServerCommon.random() % 10;
		if (tmp % 5 == 0) cell[row][column] = 4;
		else cell[row][column] = 2;
	}
    
    private static Socket openSocket(ServerSocket serverSocket) {
        Socket socket = null;
        try {
            socket = serverSocket.accept();
        } catch(IOException io) {
            io.printStackTrace();
        }
        return socket;
    }

    public static void sendMessage(ServerSocket serverSocket, String message) {
        Socket socket = ServerCommon.openSocket(serverSocket);
        try {
            PrintStream sending = new PrintStream(socket.getOutputStream(), true);
            sending.print(message);
            sending.close();
            socket.close();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public static String receiveMessage(ServerSocket serverSocket) {
        String message = null;
        Socket socket = ServerCommon.openSocket(serverSocket);
        try {
            BufferedReader receiving = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            message = receiving.readLine();
            receiving.close();
            socket.close();
        } catch (IOException io) {
            io.printStackTrace();
        }
        return message;
    }

    private static int random() {
        return (int)(Math.random() * 1000);
    }
}
