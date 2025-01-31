package com.feviro.objects;

import java.awt.Graphics;

import com.feviro.Game;

public abstract class GameObject {

	protected float x, y;
	protected Game game;

	public GameObject(float x, float y, Game game) {
		this.x = x;
		this.y = y;
		this.game = game;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public abstract void render(Graphics g);

}
