package com.feviro;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

public class Player {
  private static float x, y;
  private float width, height;
  private float baseSpeed;
  private float speedX;
  private float speedY;
  private Color color;
  private int health;

  public Player(float x, float y, float baseSpeed) {
    this.x = x;
    this.y = y;
    this.baseSpeed = baseSpeed;
    this.width = 10;
    this.height = 10;
    this.color = Color.RED;
    this.health = 100;
  }

  public void tick(GameArea area, List<Infected> infectedList) {
    this.x += (speedX * baseSpeed);
    this.y += (speedY * baseSpeed);

    this.collision(area);
    for (Infected infected : infectedList) {
      this.collision(infected);
    }
    
    checkIsLive();
  }
  
  public void checkIsLive() {
	  if(this.health <= 0) {
		  this.color = Color.BLACK;
	  }
  }
  
  public static float getX() {
	  return x;
  }
  
  public static float getY() {
	  return y;
  }
  
  public void render(Graphics g) {
    g.setColor(this.color);
    g.fillRect((int) this.x, (int) this.y, (int) this.width, (int) this.height);
    g.setColor(Color.BLACK);
    g.drawString(String.valueOf(health), (int) this.x, (int) this.y - 10);
  }

  public void moveLeft() {
    this.speedX = -1;
  }

  public void moveUp() {
    this.speedY = -1;
  }

  public void moveRight() {
    this.speedX = 1;
  }

  public void moveDown() {
    this.speedY = 1;
  }

  public void moveXStop() {
    this.speedX = 0;
  }

  public void moveYStop() {
    this.speedY = 0;
  }

  public void shoot(List<Bullet> list) {
    list.add(new Bullet(this.x, this.y, 5, 5, -90, Color.RED));
  }

  public void collision(GameArea area) {
    float playerMinX = area.minX;
    float playerMinY = area.minY;
    float playerMaxX = area.maxX;
    float playerMaxY = area.maxY;

    if (this.x < playerMinX) {
      this.x = playerMinX;
    } else if (this.x > playerMaxX) {
      this.x = playerMaxX;
    }

    if (this.y < playerMinY) {
      this.y = playerMinY;
    } else if (this.y > playerMaxY) {
      this.y = playerMaxY;
    }
  }

  public void collision(Infected infected) {
    float playerMinX = infected.minX - this.width;
    float playerMinY = infected.minY - this.height;
    float playerMaxX = infected.maxX + this.width;
    float playerMaxY = infected.maxY + this.height;
    
    if (this.x > playerMinX && this.x < playerMaxX && this.y > playerMinY && this.y < playerMaxY) {
      this.health -= 1; 
    }
  }

}
