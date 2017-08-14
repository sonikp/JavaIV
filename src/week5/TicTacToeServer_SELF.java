package week5;

import java.awt.BorderLayout;
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
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class TicTacToeServer_SELF extends JFrame {
	
	private String[] board = new String[9];
	private JTextArea outputArea;
	private Player[] players;
	private ServerSocket server;
	private int currentPlayer;
	private final static int PLAYER_X = 0;
	private final static int PLAYER_Y = 1;
	private final static String[] MARKS = { "X", "O" };
	private ExecutorService runGame;
	private Lock gameLock;
	private Condition otherPlayerConnected;
	private Condition otherPlayerTurn;
	
	// constructor: set up tic-tac-toe server and GUI that displays messages
	public TicTacToeServer_SELF() {
		
		super("Tic-Tac-Toe Server");
		
		// create ExecutorService with a thread for each player
		runGame = Executors.newFixedThreadPool(2);
		gameLock = new ReentrantLock();
		
		// condition variable for both players
		otherPlayerConnected = gameLock.newCondition();
		
		// condition variable for the other player's turn
		otherPlayerTurn = gameLock.newCondition();
		
		for (int i = 0; i < 9; i++) {
			board[i] = new String("");	// create tic-tac-toe board
		}
		
		players = new Player[2];
		currentPlayer = PLAYER_X;	// set current player to first player
		
		try {
			server = new ServerSocket(12345, 2);	// set up ServerSocket
		}
		catch (IOException ioEx) {
			ioEx.printStackTrace();
			System.exit(1);
		}
		
		outputArea = new JTextArea(); 	// create JTextArea for output
		add(outputArea, BorderLayout.CENTER);
		outputArea.setText("Server awaiting connections\n");
		
		setSize(300, 300);
		setVisible(true);
	}	// end constructor
	
	// wait for two connections so game can be played
	public void execute() {
		
		// wait for each client to connect
		for (int i = 0; i < players.length; i++) {
			try {	//wait for connection, create player, start runnable
				players[i] = new Player(server.accept(), i);
				runGame.execute(players[i]);
			}
			catch (IOException ioEx) {
				ioEx.printStackTrace();
				System.exit(1);
			}
		}
		
		gameLock.lock();	// lock game to signal player X's thread
		
		try {
			players[PLAYER_X].setSuspended(false);	// resume player X
			otherPlayerConnected.signal(); 			// wake up player X's thread
		}
		finally {
			gameLock.unlock();
		}
	}	// end execute()
	
	// display message in outputArea
	private void displayMessage(final String messageToDisplay) {
		
		// display message from event-dispatch thread of execution
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {	// updates outputArea
				outputArea.append(messageToDisplay); // add message
			}
		});	// end runnable
	}	// end displayMessage()
	
	// determine if move is valid
	public boolean validateAndMove(int location, int player) {
		
		// while not current player, must wait for turn
		while (player != currentPlayer) {
			gameLock.lock();	// lock game to wait for other player to go
			
			try {
				otherPlayerTurn.await(); // wait for player's turn
			}
			catch (InterruptedException intEx) {
				intEx.printStackTrace();
			}
			finally {
				gameLock.unlock();
			}
		}
		
		// if location not occupied, make move
		if (!isOccupied(location)) {
			
			board[location] = MARKS[currentPlayer];	// set move on board
			currentPlayer = (currentPlayer + 1) % 2;	// change player
			
			// let new current player know that move occurred
			players[currentPlayer].otherPlayerMoved(location);
			
			gameLock.lock(); // lock game to signal other player to go
			
			try {
				otherPlayerTurn.signal(); 	// signal other player to continue
			}
			finally {
				gameLock.unlock(); 		// unlock game after signaling
			}
			
			return true;	// notify player that move was valid
		}
		else {
			return false; 	// notify player that move was invalid
		}
	}	// end validateAndMove()
	
	
	public boolean isOccupied(int location) {
		
		if (board[location].equals(MARKS[PLAYER_X]) || board[location].equals(MARKS[PLAYER_Y])) {
			return true;
		}
		else {
			return false;
		}
	}	// end isOccupied()
	
	// place code in the method to determine whether game over
	public boolean isGameOver() {
		return false; // this is left as an exercise
	}
	
	// private inner class Player manages each Player as a runnable
	private class Player implements Runnable {
		
		private Socket connection;
		private Scanner input;
		private Formatter output;
		private int playerNumber;	// tracks which player
		private String mark;		// mark for this player
		private boolean suspended = true;	// whether thread is suspended
		
		// set-up Player thread
		public Player(Socket socket, int number) {
			
			playerNumber = number;	// store this player's number
			mark = MARKS[playerNumber]; // specify player's mark
			connection = socket;	// store socket for client
			
			// obtain stream from Socket
			try {
				input = new Scanner(connection.getInputStream());
				output = new Formatter(connection.getOutputStream());
			}
			catch (IOException ioEx) {
				ioEx.printStackTrace();
				System.exit(1); 
			}
		}
		
		// send message that other player moved
		public void otherPlayerMoved(int location) {
			output.format("Opponent moved\n");
			output.format("%d\n", location); 	// send location of move
			output.flush(); // flush output
		}
		
		// control thread's execution
		public void run() {
			
			// send client its mark(X or O), process messages from client
			try {
				displayMessage("Player " + mark + " connected\n");
				output.format("%s\n", mark); 	// send player's mark
				output.flush(); 	// flush output
				
				// if player X, wait for another to arrive
				if (playerNumber == PLAYER_X) {
					
					output.format("%s\n%s", "Player X connected", "Waiting for another player\n");
					output.flush();
					
					gameLock.lock();	// lock game to wait for second player
					
					try {
						while(suspended) {
							otherPlayerConnected.await();	// wait for player 0
						}
					}
					catch (InterruptedException ex) {
						ex.printStackTrace();
					}
					finally {
						gameLock.unlock(); 	// unlock game after second player
					}
					
					// send message that other player connected
					output.format("Other player connected. Your move. \n");
					output.flush();
				}
				else {
					output.format("Player 0 connected, please wait\n");
					output.flush();
				}
				
				// while game not over
				while (!isGameOver()) {
					int location = 0;	// initialize move location
					
					if (input.hasNext()) {
						location = input.nextInt(); // get move location
					}
					
					// check for valid move
					if (validateAndMove(location, playerNumber)) {
						displayMessage("\nlocation: " + location);
						output.format("Valid move. \n"); // notify client
						output.flush();
					}
					else {
						output.format("Invalid move, try again\n");
						output.flush();
					}
				}
			}
			finally {
				try {
					connection.close();
				}
				catch (IOException ioEx) {
					ioEx.printStackTrace();
					System.exit(1);
				}
			}
		} // end run()
		
		// set whether or not thread is suspended
		public void setSuspended(boolean status) {
			suspended = status;	// set value of suspended
		}
	}	// end runnable private inner class
}	// end class TicTacToeServer






































