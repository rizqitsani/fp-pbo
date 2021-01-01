package com.feviro;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

public class Game extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	private int width;
	private int height;
	
	private boolean running = false;
	private Thread thread;
	
	private GameArea area;
	private Player player;
	private List<Infected> infectedList = new ArrayList<Infected>();
	private List<Virus> virusList = new ArrayList<Virus>();
	
	public Game(int width, int height) {
		this.width = width;
		this.height = height;
		
		this.setPreferredSize(new Dimension(width, height));
		this.setMaximumSize(new Dimension(width, height));
		this.setMinimumSize(new Dimension(width, height));
		
		this.addKeyListener(new KeyInput(this));
		this.setFocusable(true);
		
		start();
	}
	
	public void init() {
		this.player = new Player(width / 2, height - 40, 5);

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
	}
	
	public synchronized void start() {
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running)
			return;
		
		running = false;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.exit(1);
	}
	
	public void run() {
		init();
		
		long lastTime = System.nanoTime();
		final double FPS = 60;
		double ns = 1000000000 / FPS;
		double delta = 0;
		
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			repaint();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + "Ticks, FPS: " + frames);
				updates = 0;
				frames = 0;
			}
		}
		
		stop();
	}
	
	private void tick() {
		player.collision(area);
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT) {
			player.moveRight();
		} else if(key == KeyEvent.VK_UP) {
			player.moveUp();
		} else if(key == KeyEvent.VK_DOWN) {
			player.moveDown();
		} else if(key == KeyEvent.VK_LEFT) {
			player.moveLeft();
		}
	}
	
	public void keyReleased(KeyEvent e) {
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		player.render(g);

		for (Infected infected : infectedList) {
			infected.render(g);
		}

		for (Virus virus : virusList) {
			virus.render(g);
		}
	}

}
