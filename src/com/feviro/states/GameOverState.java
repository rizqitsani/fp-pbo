package com.feviro.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.feviro.Game;

public class GameOverState extends State {
	
	public GameOverState(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.setFont(new Font("Roboto", Font.BOLD, 32));
		g.drawString("Game Over, press Enter to exit!", 200, 200);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_ENTER) {
//			System.exit(1);
			State.setCurrentState(game.getMenuState());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
