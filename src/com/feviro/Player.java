package com.feviro;

import java.awt.Color;
import java.awt.Graphics;

public class Player {

  float x, y;
  float width, height;
  float speed;

  public Player(float x, float y, float speed) {
    this.x = x;
    this.y = y;
    this.speed = speed;
    this.width = 10;
    this.height = 10;
  }

  public void draw(Graphics g) {
    g.setColor(Color.RED);
    g.fillRect((int) this.x, (int) this.y, (int) this.width, (int) this.height);
  }

  public void moveLeft() {
    this.x -= speed;
  }

  public void moveUp() {
    this.y -= speed;
  }

  public void moveRight() {
    this.x += speed;
  }

  public void moveDown() {
    this.y += speed;
  }

  public void collision(GameArea area) {
    float playerMinX = area.minX;
    float playerMinY = area.minY;
    float playerMaxX = area.maxX - this.width;
    float playerMaxY = area.maxY - this.height;

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

}
