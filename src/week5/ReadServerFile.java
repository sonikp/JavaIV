package week5;

// reading a file by opening a connection through a URL

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;


public class ReadServerFile extends JFrame {
	
	private JTextField enterField;	// enter site name
	private JEditorPane contentsArea;	// to display website contents
	
	// constructor :: set up GUI
	public ReadServerFile() {
		super("Simple Web Browser");
		
		// create enterField and register its listener
		enterField = new JTextField("Enter URL here");
		enterField.addMouseListener(new MouseAdapter() {
			
			@Override	// clears the text field message
			public void mouseClicked(MouseEvent e) {
				enterField.setText(""); // clears field when mouse clicked into field
				
				// user enters URL
				enterField.addActionListener(new ActionListener() {
					
					@Override	// get document specified by user
					public void actionPerformed(ActionEvent e) {
						
						getThePage(e.getActionCommand());
						
					}
				});	// end ActionListener
			}
		});
		
		
		add(enterField, BorderLayout.NORTH);
		
		contentsArea = new JEditorPane();	// create contents area
		contentsArea.setEditable(false);
		contentsArea.addHyperlinkListener(new HyperlinkListener() {
			
			@Override	// if user clicked hyperlink, go to specified page
			public void hyperlinkUpdate(HyperlinkEvent e) {
				
				if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
					getThePage(e.getURL().toString());
				}
				
			}
		});	// end hyperlink listener
		
		add(new JScrollPane(contentsArea), BorderLayout.CENTER);
		setSize(400, 300);
		setVisible(true);
		setLocationRelativeTo(null);
	}	// end constructor
	
	// load document
	private void getThePage(String location) {
		
		try {
			contentsArea.setPage(location);	
			enterField.setText(location);
		}
		catch (IOException ioEx) {
			JOptionPane.showMessageDialog(this, "Error retireving specified URL", "Bad URL", JOptionPane.ERROR_MESSAGE);
		}
	}
}	// end class ReadServerFile


















