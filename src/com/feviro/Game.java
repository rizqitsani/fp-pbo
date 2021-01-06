package com.feviro;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.feviro.gfx.Textures;
import com.feviro.states.GameState;
import com.feviro.states.MenuState;
import com.feviro.states.State;

public class Game extends JPanel implements Runnable {

	private static final long serialVersionUID = 1L;
	private int width = 800;
	private int height = 600;

	private boolean running = false;
	private Thread thread;
	
	//States
	private State gameState;
	private State menuState;
	
	public Game(int width, int height) {
		this.width = width;
		this.height = height;

		this.setPreferredSize(new Dimension(width, height));
		this.setMaximumSize(new Dimension(width, height));
		this.setMinimumSize(new Dimension(width, height));

		this.addKeyListener(new KeyInput());
		this.setFocusable(true);

		start();
	}

	public void init() {
		Textures.init();
		
		gameState = new GameState(this);
		menuState = new MenuState(this);
		
		State.setCurrentState(menuState);

		
	}

	public synchronized void start() {
		if (running)
			return;

		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		if (!running)
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

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			repaint();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " Ticks, FPS: " + frames);
				updates = 0;
				frames = 0;
			}
		}

		stop();
	}

	private void tick() {
		if(State.getCurrentState() != null) {
			State.getCurrentState().tick();
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(State.getCurrentState() != null) {
			State.getCurrentState().render(g);
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public State getGameState() {
		return gameState;
	}

	public State getMenuState() {
		return menuState;
	}

	

}
