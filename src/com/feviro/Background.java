package com.feviro;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

public class Background extends JPanel implements KeyListener {

	private int areaWidth;
	private int areaHeight;
	private GameArea area;
	private Player player;
	private List<Infected> infectedList = new ArrayList<Infected>();
	private List<Virus> virusList = new ArrayList<Virus>();

	public Background(int width, int height) {
		this.areaWidth = width;
		this.areaHeight = height;
		this.setPreferredSize(new Dimension(areaWidth, areaHeight));

		this.player = new Player(areaWidth / 2, areaHeight - 40, 5);

		this.area = new GameArea(0, 0, width, height, Color.BLACK, Color.WHITE);

		for (int i = 0; i < 2; i++) {
			Random random = new Random();
			int x = random.nextInt(300) + 40;
			int y = random.nextInt(300) + 40;
			infectedList.add(new Infected(x, y));
		}

		for (int i = 0; i < 5; i++) {
			Random random = new Random();
			int x = random.nextInt(300) + 40;
			int y = random.nextInt(300) + 40;
			virusList.add(new Virus(x, y));
		}

		this.addKeyListener(this);
		this.setFocusable(true);
		startThread();
	}

	public void startThread() {
		Thread gameThread = new Thread() {
			public void run() {
				while (true) {
					player.collision(area);
					repaint();
					try {
						Thread.sleep(1000 / 30);
					} catch (InterruptedException e) {

					}
				}
			}
		};
		gameThread.start();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		player.draw(g);

		for (Infected infected : infectedList) {
			infected.draw(g);
		}

		for (Virus virus : virusList) {
			virus.draw(g);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case 38: {
				player.moveUp();
			}
				break;
			case 37: {
				player.moveLeft();
			}
				break;
			case 40: {
				player.moveDown();
			}
				break;
			case 39: {
				player.moveRight();
			}
				break;
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
