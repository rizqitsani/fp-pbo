package com.feviro;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame("Feviro");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setContentPane(new Background(800, 600));
				frame.pack();
				frame.setVisible(true);
				frame.setResizable(false);
			}
		});
	}

}
