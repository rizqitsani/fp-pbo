package com.feviro.states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.feviro.Game;
import com.feviro.gfx.Textures;

public class HelpState extends State {

	private int index;

	public HelpState(Game game) {
		super(game);
	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Textures.helpBackground[index], 0, 0, null);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_SPACE) {
			if (index == 1)
				State.setCurrentState(game.getMenuState());
			else
				index++;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
