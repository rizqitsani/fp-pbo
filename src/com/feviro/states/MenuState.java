package com.feviro.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.feviro.Game;
import com.feviro.gfx.Textures;

public class MenuState extends State {

	public MenuState(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Textures.menuBackground, 0, 0, null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Roboto", Font.BOLD, 32));
		g.drawString("Press enter to continue", 200, 200);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_ENTER) {
			State.setCurrentState(new GameState(game));
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
