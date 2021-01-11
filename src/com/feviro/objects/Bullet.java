package com.feviro.objects;

import java.awt.Color;
import java.awt.Graphics;

import com.feviro.Game;
import com.feviro.GameArea;

public class Bullet extends GameObject {
	private float speedX, speedY;
	private float radius, damage;
	private Color color;

	public Bullet(float x, float y, float radius, float speed, float angleInDegree, Color color, Game game) {
		super(x, y, game);
		this.speedX = (float) (speed * Math.cos(Math.toRadians(angleInDegree)));
		this.speedY = (float) (speed * Math.sin(Math.toRadians(angleInDegree)));
		this.radius = radius;
		this.color = color;
		this.damage = 5;
	}

	public void tick(GameArea area) {
		this.move();
	}

	public void render(Graphics g) {
		g.setColor(color);
		g.fillOval((int) (x - radius), (int) (y - radius), (int) (2 * radius), (int) (2 * radius));
	}

	public void flipDirectionX() {
		speedX = -speedX;
	}

	public void flipDirectionY() {
		speedY = -speedY;
	}

	private void move() {
		this.x += this.speedX;
		this.y += this.speedY;
	}

	public boolean collision(GameArea box) {
		float bulletMinX = box.getMinX() + radius;
		float bulletMinY = box.getMinY() + radius;
		float bulletMaxX = box.getMaxX() + radius;
		float bulletMaxY = box.getMaxY() + radius;

		if (x < bulletMinX || x > bulletMaxX || y < bulletMinY || y > bulletMaxY) {
			return true;
		}

		return false;
	}

	public float getRadius() {
		return radius;
	}

	public float getDamage() {
		return damage;
	}

}
