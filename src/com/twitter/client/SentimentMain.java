package com.twitter.client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class SentimentMain {

	private JFrame frame;
	private JTextField txtSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SentimentMain window = new SentimentMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SentimentMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblEnterSearchTerm = new JLabel("Enter Search Term");
		lblEnterSearchTerm.setBounds(10, 11, 96, 14);
		panel.add(lblEnterSearchTerm);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(111, 8, 313, 20);
		panel.add(txtSearch);
		txtSearch.setColumns(10);
		
		JTextPane txtOutput = new JTextPane();
		txtOutput.setBounds(10, 73, 414, 177);
		panel.add(txtOutput);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String topic = txtSearch.getText();
				ArrayList<String> tweets = TweetManager.getTweets(topic);
				NLP.init();
				int max = 0;
				int posi = 0;
				for(String tweet : tweets) {
					if(NLP.findSentiment(tweet) == 1){
						posi++;
					}
					max++;
				}

				String display = "Of the " + max + " tweets analysed, " + posi + " were positive.\nTweets:";
				for(String tweet : tweets) {
					display += "\n" + tweet;
				}
				txtOutput.setText(display);
			}
		});
		btnSearch.setBounds(170, 39, 88, 23);
		panel.add(btnSearch);
		
		
	}

	protected String getSentiment() {
		// TODO Auto-generated method stub
		return null;
	}

	protected void connectToTwitter() {
		// TODO Auto-generated method stub
		
	}
}
