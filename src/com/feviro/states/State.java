package com.feviro.states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.feviro.Game;

public abstract class State {

	private static State currentState = null;

	public static State getCurrentState() {
		return currentState;
	}

	public static void setCurrentState(State currentState) {
		State.currentState = currentState;
	}

	protected Game game;

	public State(Game game) {
		this.game = game;
	}

	public abstract void tick();

	public abstract void render(Graphics g);

	public abstract void keyPressed(KeyEvent e);

	public abstract void keyReleased(KeyEvent e);

}
