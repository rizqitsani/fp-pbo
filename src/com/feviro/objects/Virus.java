package com.feviro.objects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.feviro.Game;
import com.feviro.gfx.Animation;
import com.feviro.gfx.Textures;

public class Virus extends GameObject {

	private int width, height;
	private float speedX, speedY;
	private float baseSpeed;
	private int health;

	// Animations
	private Animation virusHit;

	public Virus(float x, float y, float baseSpeed, Game game) {
		super(x, y, game);
		this.setX(x);
		this.y = y;
		this.baseSpeed = baseSpeed;
		this.width = 48;
		this.height = 48;
		this.health = 30;

		// Animations
		virusHit = new Animation(100, Textures.virusHit);
	}

	public void tick(Player player) {
		if (checkIsLive())
			virusHit.tick();

		this.setX(this.getX() + (speedX * baseSpeed));
		this.setY(this.getY() + (speedY * baseSpeed));

		if (checkIsLive()) {
			moveToPlayer(player);
		} else {
			this.speedX = 0;
			this.speedY = 0;
		}
	}

	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) this.x, (int) this.y, this.width, this.height, null);
//		g.setColor(Color.GREEN);
//		g.drawRect((int) this.x, (int) this.y, width, height);
	}

	private BufferedImage getCurrentAnimationFrame() {
		if (checkIsLive())
			return virusHit.getCurrentFrame();
		else
			return Textures.virusDead;
	}

	public boolean checkIsLive() {
		if (this.health <= 0) {
			return false;
		}
		return true;
	}

	public void moveToPlayer(Player player) {
		float playerX = player.getX();
		float playerY = player.getY();

		if (this.x < playerX) {
			this.speedX = 1;
		} else if (this.x > playerX) {
			this.speedX = -1;
		} else {
			this.speedX = 0;
		}

		if (this.y < playerY) {
			this.speedY = 1;
		} else if (this.y > playerY) {
			this.speedY = -1;
		} else {
			this.speedY = 0;
		}
		// float angleInRadius = (float) Math.atan((playerY - this.getY()) / (playerX -
		// this.getX()));
		// System.out.println(angleInRadius);
		// System.out.println(Math.sin(angleInRadius) + " " + Math.cos(angleInRadius));

		// if(this.x > playerX) {
		// this.speedX = (float) (this.baseSpeed * Math.cos(angleInRadius) * -1);
		// } else if(this.x < playerX) {
		// this.speedX = (float) (this.baseSpeed * Math.cos(angleInRadius));
		// } else {
		// this.speedX = 0;
		// }
		//
		// if(this.y > playerY) {
		// this.speedY = (float) (this.baseSpeed * Math.sin(angleInRadius) * -1);
		// } else if(this.y < playerY) {
		// this.speedY = (float) (this.baseSpeed * Math.sin(angleInRadius));
		// } else {
		// this.speedY = 0;
		// }
	}

	public boolean collision(Bullet bullet) {
		float virusMinX = this.getX() + bullet.getRadius();
		float virusMinY = this.getY() + bullet.getRadius();
		float virusMaxX = this.getX() + this.getWidth() - bullet.getRadius();
		float virusMaxY = this.getY() + this.getHeight() - bullet.getRadius();

		float virusCenterX = this.getX() + (this.getWidth() / 2);
		float virusCenterY = this.getY() + (this.getHeight() / 2);

		float distanceX = Math.abs(bullet.x - virusCenterX);
		float distanceY = Math.abs(bullet.y - virusCenterY);

		float maxDistX = this.getWidth() / 2;
		float maxDistY = this.getHeight() / 2;

		if (distanceX <= maxDistX && distanceY <= maxDistY) {
			// System.out.println(distanceX + " " + distanceY + " " + maxDistX + " " +
			// maxDistY);
			this.health -= bullet.getDamage();
			if (bullet.x < virusMinX || bullet.x > virusMaxX || bullet.y < virusMinY || bullet.y > virusMaxY) {
				return true;
			}
		}
		return false;
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
}
