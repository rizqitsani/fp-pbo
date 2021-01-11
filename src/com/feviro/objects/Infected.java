package com.feviro.objects;

import java.awt.Graphics;

import com.feviro.Game;
import com.feviro.gfx.Textures;

public class Infected extends GameObject {

  private float minX, minY, maxX, maxY;
  private int width, height, radius;

  public Infected(float x, float y, Game game) {
    super(x, y, game);
    this.width = 48;
    this.height = 48;
    this.radius = 30;
    this.minX = x - this.radius;
    this.minY = y - this.radius;
    this.maxX = x + this.width - 1 + this.radius;
    this.maxY = y + this.height - 1 + this.radius;
  }

  public void render(Graphics g) {
    g.drawImage(Textures.infected, (int) this.x, (int) this.y, this.width, this.height, null);
    // g.setColor(Color.RED);
    // g.drawRect((int) this.x, (int) this.y, width, height);
  }

  public float getMinX() {
    return minX;
  }

  public float getMinY() {
    return minY;
  }

  public float getMaxX() {
    return maxX;
  }

  public float getMaxY() {
    return maxY;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public int getRadius() {
    return radius;
  }

}
