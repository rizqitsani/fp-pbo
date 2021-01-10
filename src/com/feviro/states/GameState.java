package com.feviro.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.feviro.Game;
import com.feviro.GameArea;
import com.feviro.objects.Bullet;
import com.feviro.objects.Infected;
import com.feviro.objects.Player;
import com.feviro.objects.Virus;

public class GameState extends State {

	private GameArea area;
	private Player player;
	private List<Infected> infectedList = new ArrayList<Infected>();
	private List<Virus> virusList = new ArrayList<Virus>();
	private List<Bullet> bulletList = new ArrayList<Bullet>();

	public GameState(Game game) {
		super(game);

		this.player = new Player(game.getWidth() / 2, game.getHeight() - 40, 5);

		this.area = new GameArea(0, 0, game.getWidth(), game.getHeight(), Color.BLACK, Color.WHITE);

		for (int i = 0; i < 5; i++) {
			Random random = new Random();
			int x = random.nextInt(300) + 40;
			int y = random.nextInt(300) + 40;
			infectedList.add(new Infected(x, y));
		}

		for (int i = 0; i < 7; i++) {
			Random random = new Random();
			int x = random.nextInt(300) + 40;
			int y = random.nextInt(300) + 40;
			virusList.add(new Virus(x, y, 1));
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
	}

	@Override
	public void render(Graphics g) {
		player.render(g);

		for (int i = 0; i < infectedList.size(); i++) {
			infectedList.get(i).render(g);
		}

		for (int i = 0; i < virusList.size(); i++) {
			for (int j = 0; j < bulletList.size(); j++) {
				if (virusList.get(i).collision(bulletList.get(j))) {
					bulletList.remove(j);
				}
			}
			virusList.get(i).render(g);
		}

		for (int i = 0; i < bulletList.size(); i++) {
			if (bulletList.get(i).collision(area)) {
				bulletList.remove(i);
			}
			bulletList.get(i).render(g);
		}
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
	}

}
