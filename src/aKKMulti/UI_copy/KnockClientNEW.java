package aKKMulti.UI_copy;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class KnockClientNEW extends JFrame implements ActionListener
{
    // KK protocol strings
    String KK_HELLO = "KK/1.0";
    String KK_OK = "Knock! Knock!";			//  // orig: KK OK:
    String KK_SETUP = "KK SETUP:";
    String KK_PUNCH = "KK PUNCH:";

    // GUI elements
    private JTextField hostField = new JTextField("127.0.0.1");
    private JTextField portField = new JTextField("4444");
    private JButton connectButton = new JButton("Get Joke");
    private JButton quitButton = new JButton("Quit");
    private JTextArea outputArea = new JTextArea(9, 30);
    
    // timeout for socket blocking calls
    static int timeout = 3000;

    /*
     * Constructor sets up the interface and shows the window
     */
    public KnockClientNEW()
    {
        super("Knock-Knock Client");
    
        // defining layout for the labels
        JPanel inputLabels = new JPanel(new GridLayout(2,1));
        inputLabels.add(new JLabel("Host: "));
        inputLabels.add(new JLabel("Port:" ));
        
        // defining layout for input fields
        JPanel inputFields = new JPanel(new GridLayout(2,1));
        inputFields.add(hostField);
        inputFields.add(portField);
        
        // combining labels and fields
        JPanel input = new JPanel(new BorderLayout());
        input.add("West", inputLabels);
        input.add("Center", inputFields);
        
        // defining layout for buttons
        JPanel buttons = new JPanel(new FlowLayout());
        buttons.add(connectButton);
        buttons.add(quitButton);
        
        // adding all layouts to the content of the main window
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add("North", input);
        getContentPane().add("Center", new JScrollPane(outputArea));
        getContentPane().add("South", buttons);
        
        // activating the buttons
        connectButton.addActionListener(this);
        quitButton.addActionListener(this);
        
        // showing the window on the screen
        validate();
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /*
     * Connecting to the server and retrieving random joke
     */
    public void getJoke()
    {
        try
        {
            // getting host/port parameters from input fields
            String host = "127.0.0.1";		// hostField.getText();
            int port = 4444;				// Integer.parseInt(portField.getText());
            
            // establishing a connection and setting timeout
            Socket socket = new Socket(host, port);		// host, port
            socket.setSoTimeout(timeout);
            
            // Setting up input and output streams as text streams
            BufferedReader in = new BufferedReader(
                                   new InputStreamReader(
                                           socket.getInputStream()));
            PrintWriter out = new PrintWriter(
                                 new OutputStreamWriter(
                                         socket.getOutputStream()));
            // setting up the empty variables
            int numJokes = -1;
            String response = "";
            String jokeSetup = "";
            String jokePunch = "";
            
            // sending protocol string
            printAndFlush(out, KK_HELLO);
            // waiting for response and checking for OK
            response = in.readLine();
            if (response.startsWith(KK_OK))
                numJokes = Integer.parseInt(response.substring(5));	// MF: KK_OK.length())
            else
                throw new Exception("Invalid connection");
            
            // sending random joke number
            printAndFlush(out, "" +(int)(numJokes*Math.random()));
            
            // checking for ok from server
            response = in.readLine();
            if (!response.equals("KK OK"))
                throw new Exception("invalid server response");
            
            // waiting for joke setup data
            jokeSetup = in.readLine();
            if (!jokeSetup.startsWith(KK_SETUP))
                throw new Exception("invalid joke setup");
            
            // waiting for joke punch line data
            jokePunch = in.readLine();
            if (!jokePunch.startsWith(KK_PUNCH))
                throw new Exception("invalid joke punch line");
            
            // disconnecting
            in.close();
            out.close();
            socket.close();
            
            // calling method to display the joke
            tellJoke(jokeSetup, jokePunch);
        }
        catch (UnknownHostException uhe)
        {
            JOptionPane.showMessageDialog(this, "Unknown host: " + uhe.getMessage());
        }
        catch (IOException ioe)
        {
            JOptionPane.showMessageDialog(this, "Error: " + ioe.getMessage());
        }
        catch(NumberFormatException nfe)
        {
            JOptionPane.showMessageDialog(this, "Invalid format: number must be an integer");
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void tellJoke(String jokeSetup, String jokePunch)
    {
        String setup = jokeSetup.substring(KK_SETUP.length());
        String punch = jokePunch.substring(KK_PUNCH.length());
        outputArea.append("Knock Knock\n");
        outputArea.append("Who's there?\n");
        outputArea.append(setup + "\n");
        outputArea.append(setup + " who?\n");
        outputArea.append(punch + "\n\n\n");
    }

    private void printAndFlush(PrintWriter out, String msg)
    {
        out.println(msg);
        out.flush();
    }
    
    public void actionPerformed(ActionEvent event)
    {
        if (event.getSource() == connectButton)
            getJoke();
        else if (event.getSource() == quitButton)
            System.exit(0);
    }
    
    public static void main(String args[])
    {
        KnockClientNEW kc = new KnockClientNEW();
        kc.setVisible(true);
    }
}
