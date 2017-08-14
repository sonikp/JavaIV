package week5; // TTTClient.java
// Fig. 24.13: TicTacToeServer.java. This class maintains a game of Tic-Tac-Toe for two clients.
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;



public class TTTClient extends JFrame implements Runnable  {

	private JTextField idField; // textfield to display player's mark
	private JTextArea displayArea; // JTextArea to display output
	private JPanel boardPanel; // panel for tic-tac-toe board
	private JPanel panel2; // panel to hold board
	private Square board[][]; // tic-tac-toe board
	private Square currentSquare; // current square
	private Socket connection; // connection to server
	private Scanner input; // input from server
	private Formatter output; // output to server
	private String ticTacToeHost; // host name for server
	private String myMark; // this client's mark
	private boolean myTurn; // determines which client's turn it is
	private final String X_MARK = "X"; // mark for first client
	private final String O_MARK = "O"; // mark for second client

	// constructor
	public TTTClient(String host)
	{
		System.out.println("TTTClient");
		
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
		







	}

	// start the client thread
	public void startClient()
	{

	} // end method startClient

	// control thread that allows continuous update of displayArea
	public void run()
	{

	} // end method run







	// private inner class for the squares on the board
	private class Square extends JPanel 
	{
		private String mark; // mark to be drawn in this square
		private int location; // location of square

		public Square( String squareMark, int squareLocation )
		{
			mark = squareMark;				// set mark for this square
		} // end Square constructor

		// return preferred size of Square
		public Dimension getPreferredSize() 
		{ 
			return new Dimension( 30, 30 ); // return preferred size
		} // end method getPreferredSize



		// draw Square
		public void paintComponent( Graphics g )
		{
			super.paintComponent(g);
			g.drawRect(0,  0, 29, 29); 	// draw square
			g.drawString(mark, 11, 20); // draw mark

		} // end method paintComponent
	} // end inner-class Square
} // end class TicTacToeClient

