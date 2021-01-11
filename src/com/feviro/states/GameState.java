package com.feviro.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.feviro.Game;
import com.feviro.GameArea;
import com.feviro.gfx.Textures;
import com.feviro.objects.Bullet;
import com.feviro.objects.Cure;
import com.feviro.objects.Infected;
import com.feviro.objects.Player;
import com.feviro.objects.Virus;

public class GameState extends State {

	private GameArea area;
	private Player player;
	private List<Infected> infectedList = new ArrayList<Infected>();
	private List<Virus> virusList = new ArrayList<Virus>();
	private List<Bullet> bulletList = new ArrayList<Bullet>();
	private List<Cure> cureList = new ArrayList<Cure>();

	public GameState(Game game) {
		super(game);

		this.player = new Player(game.getWidth() / 2, game.getHeight() - 40, 1.5f, 1.02f, this.game);

		this.area = new GameArea(0, 0, game.getWidth(), game.getHeight(), Color.BLACK, Color.WHITE);

		for (int i = 0; i < 5; i++) {
			Random random = new Random();
			int x = random.nextInt(game.getWidth());
			int y = random.nextInt(game.getHeight());
			infectedList.add(new Infected(x, y, this.game));
		}

		for (int i = 0; i < 7; i++) {
			Random random = new Random();
			int x = random.nextInt(game.getWidth());
			int y = random.nextInt(game.getHeight());
			virusList.add(new Virus(x, y, 0.3f, this.game));
		}
		
		for (int i = 0; i < 5; i++) {
			Random random = new Random();
			int x = random.nextInt(game.getWidth());
			int y = random.nextInt(game.getHeight());
			cureList.add(new Cure(x, y, this.game));
		}
	}

	@Override
	public void tick() {
		player.tick(area, infectedList);

		for (Bullet bullet : bulletList) {
			bullet.tick(area);
		}

		for (Virus virus : virusList) {
			virus.tick(player);
		}
		
		if(cureList.isEmpty()) {
			State.setCurrentState(game.getWinState());
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Textures.background, 0, 0, game.getWidth(), game.getHeight(), null);
		
		for (int i = 0; i < infectedList.size(); i++) {
			infectedList.get(i).render(g);
		}

		for (int i = 0; i < virusList.size(); i++) {
			for (int j = 0; j < bulletList.size(); j++) {
				if(virusList.get(i).checkIsLive()) {
					if (virusList.get(i).collision(bulletList.get(j))) {
						bulletList.remove(j);
					}					
				}
			}
			if(virusList.get(i).checkIsLive()) {
				player.collision(virusList.get(i));				
			}
			virusList.get(i).render(g);
		}

		for (int i = 0; i < bulletList.size(); i++) {
			if (bulletList.get(i).collision(area)) {
				bulletList.remove(i);
			}
			bulletList.get(i).render(g);
		}
		
		for (int i = 0; i < cureList.size(); i++) {
			cureList.get(i).collision(player);
			if(cureList.get(i).checkIsObtained()) {
				cureList.remove(i);
			}
			cureList.get(i).render(g);
		}
		
		player.render(g);
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT) {
			player.moveLeft();
		} else if (key == KeyEvent.VK_UP) {
			player.moveUp();
		} else if (key == KeyEvent.VK_RIGHT) {
			player.moveRight();
		} else if (key == KeyEvent.VK_DOWN) {
			player.moveDown();
		}
		if (key == KeyEvent.VK_SHIFT) {
			player.boost();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT) {
			player.moveXStop();
		} else if (key == KeyEvent.VK_UP) {
			player.moveYStop();
		} else if (key == KeyEvent.VK_RIGHT) {
			player.moveXStop();
		} else if (key == KeyEvent.VK_DOWN) {
			player.moveYStop();
		} else if (key == KeyEvent.VK_SPACE) {
			player.shoot(bulletList);
		}
		if (key == KeyEvent.VK_SHIFT) {
			player.boostStop();
		}
	}

}
