package com.feviro;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

public class Virus {

  private float x, y;
  private int width, height;
  private float speedX, speedY;
  private float baseSpeed;
  private int health;
  private Color color;

  public Virus(float x, float y, float baseSpeed) {
    this.setX(x);
    this.y = y;
    this.baseSpeed = baseSpeed;
    this.setWidth(20);
    this.setHeight(20);
    this.health = 30;
    this.setColor(Color.GREEN);
  }

  public void render(Graphics g) {
    g.setColor(this.color);
    g.fillRect((int) this.getX(), (int) this.y, this.getWidth(), this.getHeight());
    g.setColor(Color.BLACK);
    g.drawString(String.valueOf(health), (int) this.x, (int) this.y - 10);
  }
  
  public void tick(Player player) {
	  this.setX(this.getX() + (speedX * baseSpeed));
	  this.setY(this.getY() + (speedY * baseSpeed));
	  
	  if (checkIsLive()) {
		  moveToPlayer(player);
	  } else {
		  this.speedX = 0;
		  this.speedY = 0;
	  }
  }
  
  public boolean checkIsLive() {
	  if(this.health <= 0) {
		  this.setColor(Color.BLACK);
		  return false;
	  }
	  return true;
  }
  
  public void moveToPlayer(Player player) {
	  float playerX = Player.getX();
	  float playerY = Player.getY();
	  
	  if(this.x < playerX) {
		  this.speedX = 1;
	  } else if(this.x > playerX) {
		  this.speedX = -1;
	  } else {
		  this.speedX = 0;
	  }
	  
	  if(this.y < playerY) {
		  this.speedY = 1;
	  } else if(this.y > playerY) {
		  this.speedY = -1;
	  } else {
		  this.speedY = 0;
	  }
//	  float angleInRadius = (float) Math.atan((playerY - this.getY()) / (playerX - this.getX()));
//	  System.out.println(angleInRadius);
//	  System.out.println(Math.sin(angleInRadius) + " " + Math.cos(angleInRadius));
	  
//	  if(this.x > playerX) {
//		  this.speedX = (float) (this.baseSpeed * Math.cos(angleInRadius) * -1);		  
//	  } else if(this.x < playerX) {
//		  this.speedX = (float) (this.baseSpeed * Math.cos(angleInRadius));		  
//	  } else {
//		  this.speedX = 0;
//	  }
//	  
//	  if(this.y > playerY) {
//		  this.speedY = (float) (this.baseSpeed * Math.sin(angleInRadius) * -1);
//	  } else if(this.y < playerY) {
//		  this.speedY = (float) (this.baseSpeed * Math.sin(angleInRadius));
//	  } else {
//		  this.speedY = 0;
//	  }
  }

  public boolean collision(Bullet bullet) {
	    float virusMinX = this.getX() + bullet.radius;
	    float virusMinY = this.getY() + bullet.radius;
	    float virusMaxX = this.getX() + this.getWidth() - bullet.radius;
	    float virusMaxY = this.getY() + this.getHeight() - bullet.radius;
	    
	    float virusCenterX = this.getX() + (this.getWidth() / 2);
	    float virusCenterY = this.getY() + (this.getHeight() / 2);
	    
	    float distanceX = Math.abs(bullet.x - virusCenterX);
		float distanceY = Math.abs(bullet.y - virusCenterY);
	    
		float maxDistX = this.getWidth() / 2;
		float maxDistY = this.getHeight() / 2;
	    
	    if(distanceX <= maxDistX && distanceY <= maxDistY) {
//	    	System.out.println(distanceX + " " + distanceY + " " + maxDistX + " " + maxDistY);
	    	this.health -= 10;
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

public Color getColor() {
	return color;
}

public void setColor(Color color) {
	this.color = color;
}
}
