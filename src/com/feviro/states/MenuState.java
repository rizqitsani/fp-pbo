package com.feviro.states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.feviro.Game;
import com.feviro.gfx.Textures;
import com.feviro.ui.ClickListener;
import com.feviro.ui.UIImageButton;
import com.feviro.ui.UIManager;

public class MenuState extends State {
	
	private UIManager uiManager;

	public MenuState(Game game) {
		super(game);
		uiManager = new UIManager(game);
		game.getMouseManager().setUiManager(uiManager);
		
		init();
	}
	
	public void init() {
		uiManager.addObject(new UIImageButton((game.getWidth() / 2 - 100), 250, 200, 72, Textures.newGameButton, new ClickListener() {
			@Override
			public void onClick() {
				State.setCurrentState(new GameState(game));
			}
		}));
		
		uiManager.addObject(new UIImageButton((game.getWidth() / 2 - 100), 335, 200, 72, Textures.helpButton, new ClickListener() {
			@Override
			public void onClick() {
				State.setCurrentState(new HelpState(game));
			}
		}));
		
		uiManager.addObject(new UIImageButton((game.getWidth() / 2 - 100), 420, 200, 72, Textures.exitButton, new ClickListener() {
			@Override
			public void onClick() {
				System.exit(1);
			}
		}));
	}

	@Override
	public void tick() {
		uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Textures.menuBackground, 0, 0, null);
		uiManager.render(g);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
