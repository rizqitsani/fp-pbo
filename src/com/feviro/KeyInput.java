package com.feviro;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.feviro.states.State;

public class KeyInput extends KeyAdapter {

	public void keyPressed(KeyEvent e) {
		State.getCurrentState().keyPressed(e);
	}

	public void keyReleased(KeyEvent e) {
		State.getCurrentState().keyReleased(e);
	}

}
