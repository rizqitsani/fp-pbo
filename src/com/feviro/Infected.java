package com.feviro;

import java.awt.Graphics;

import com.feviro.gfx.Textures;

public class Infected {

  float x;
  float y;
  float minX, minY, maxX, maxY;
  int width, height, radius;

  public Infected(float x, float y) {
    this.x = x;
    this.y = y;
    this.width = 20;
    this.height = 20;
    this.radius = 30;
    this.minX = x - this.radius;
    this.minY = y - this.radius;
    this.maxX = x + this.width - 1 + this.radius;
    this.maxY = y + this.height - 1 + this.radius;
  }

  public void render(Graphics g) {
    g.drawImage(Textures.infected, (int) this.x, (int) this.y, null);
  }

}
