package com.feviro.states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.feviro.Game;
import com.feviro.gfx.Textures;

public class GameOverState extends State {

	public GameOverState(Game game) {
		super(game);
	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Textures.gameOverBackground, 0, 0, null);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_SPACE) {
			State.setCurrentState(game.getMenuState());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
