package com.feviro.objects;

import java.awt.Color;
import java.awt.Graphics;

import com.feviro.Game;
import com.feviro.GameArea;

public class Bullet extends GameObject {
	private float speedX, speedY;
	private float radius;
	private Color color;

	public Bullet(float x, float y, float radius, float speed, float angleInDegree, Color color, Game game) {
		super(x, y, game);
		this.speedX = (float) (speed * Math.cos(Math.toRadians(angleInDegree)));
		this.speedY = (float) (speed * Math.sin(Math.toRadians(angleInDegree)));
		this.radius = radius;
		this.color = color;
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

	// public void collide(Ball anotherBall) {
	// double distanceX, distanceY, slope;
	// float distance, minDistance;
	//
	// distanceX = (double) Math.abs(x - anotherBall.x);
	// distanceY = (double) Math.abs(y - anotherBall.y);
	// distance = (float) Math.sqrt((distanceX*distanceX) + (distanceY*distanceY));
	// minDistance = radius + anotherBall.radius;
	//
	// if(distance < minDistance) {
	// switchSpeed(anotherBall);
	//
	// if(anotherBall.x - this.x != 0) {
	// slope = (anotherBall.y - this.y)/(anotherBall.x - this.x);
	// } else {
	// slope = (anotherBall.y - this.y)/((anotherBall.x - this.x) + 0.0001);
	// }
	//
	// double c = anotherBall.y - (slope * anotherBall.x);
	// double alpha = Math.atan(slope);
	// double a = (double)(radius * Math.cos(alpha));
	// double x = this.x + (anotherBall.x - this.x)/2;
	//
	// if(this.x < anotherBall.x) {
	// this.x = (float)(x-a);
	// this.y = (float)(slope * this.x + c);
	//
	// anotherBall.x = (float)(x+a);
	// anotherBall.y = (float)(slope * anotherBall.x + c);
	// } else {
	// this.x = (float)(x+a);
	// this.y = (float)(slope * this.x + c);
	//
	// anotherBall.x = (float)(x-a);
	// anotherBall.y = (float)(slope * anotherBall.x + c);
	// }
	// }
	// }
	//
	// public void switchSpeed(Ball anotherBall) {
	// float temp;
	//
	// temp = this.speedX;
	// this.speedX = anotherBall.speedX;
	// anotherBall.speedX = temp;
	//
	// temp = this.speedY;
	// this.speedY = anotherBall.speedY;
	// anotherBall.speedY = temp;
	// }
}
