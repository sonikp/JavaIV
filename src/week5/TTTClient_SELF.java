package week5; // TTTClient_SELF.java

// client side of client/sever Tic-Tac-Toe program

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.Socket;
import java.net.InetAddress;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.util.Formatter;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class TTTClient_SELF extends JFrame implements Runnable {
	
	
	private JTextField idField;	// textfield to display player's mark
	private JTextArea displayArea;
	private JPanel boardPanel;
	private JPanel panel2;
	private Square[][] board;	// tic-tac-toe board
	private Square currentSquare;
	private Socket connection;
	private Scanner input;
	private Formatter output;
	private String ticTacToeHost;	// hostname for server
	private String myMark;	// this client's mark
	private boolean myTurn; // determines which client's turn it is
	private final String X_MARK = "X";
	private final String O_MARK = "O";
	
	// constructor:: set-up user-interface and board
	public TTTClient_SELF(String host) {
		
		System.out.println("TTTClient_SELF");
		
		ticTacToeHost = host;
		displayArea = new JTextArea(4, 30); // set-up JTextArea
		displayArea.setEditable(false);
		add(new JScrollPane(displayArea), BorderLayout.SOUTH);
		
		boardPanel = new JPanel();
		boardPanel.setLayout(new GridLayout(3, 3, 0, 0));
		
		board = new Square[3][3]; 	// create board
		
		// loop over the rows in the board
		for (int row = 0; row < board.length; row++) {
			
			// loop over columns in the board
			for (int column = 0; column < board[row].length; column++) {
				
				// create square
				board[row][column] = new Square(" ", row * 3 + column);
				boardPanel.add(board[row][column]); 	// add square
			}
		}
		
		idField = new JTextField(); // set-up textfield
		idField.setEditable(false);
		add(idField, BorderLayout.NORTH);
		// **********************
		panel2 = new JPanel();		// set-up panel to contain boardPanel
		panel2.add(boardPanel, BorderLayout.CENTER);
		add(panel2, BorderLayout.CENTER);
		
		setSize(300, 225);
		setVisible(true);
		// ************************
		startClient();
		
		
	}	// end constructor
	
	// start client thread
	public void startClient() {

	}	// end startClient
	
	// control thread that allows continuous update of displayArea
	public void run() {

	}	// end run()
	

	
	// private inner class for the squares on the board
	private class Square extends JPanel {
		
		private String mark;	// mark to be drawn
		private int location; 	// location of square
		
		public Square(String squareMark, int squareLocation) {
			
			mark = squareMark;				// set mark for this square

		}
		
		// return preferred size of Square
		public Dimension getPreferredSize() {
			return new Dimension(30, 30); 		// return preferred size
		}
		

		
		// draw Square
		public void paintComponents(Graphics g) {
			
			super.paintComponent(g);			
			g.drawRect(0,  0, 29, 29); 	// draw square
			g.drawString(mark, 11, 20); // draw mark
		}
	} // end inner class Square
	
} // end class TicTacToeClient























