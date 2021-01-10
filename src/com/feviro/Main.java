package com.feviro;

import javax.swing.JFrame;

public class Main {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public final String TITLE = "Feviro";

	public static void main(String[] args) {
		Game game = new Game(WIDTH, HEIGHT);

		JFrame frame = new JFrame("Feviro");
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
