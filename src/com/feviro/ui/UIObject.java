package com.feviro.ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public abstract class UIObject {
	
	protected float x, y;
	protected int width, height;
	protected Rectangle bounds;
	protected boolean isHovered = false;
	
	public UIObject(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.bounds = new Rectangle((int) x, (int) y, width, height);
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract void onClick();
	
	public void onMouseMoved(MouseEvent e) {
		if(bounds.contains(e.getX(), e.getY()))
			isHovered = true;
		else
			isHovered = false;
	}
	
	public void onMouseReleased(MouseEvent e) {
		if(isHovered)
			onClick();
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

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isHovered() {
		return isHovered;
	}

	public void setHovered(boolean isHovered) {
		this.isHovered = isHovered;
	}

}
