package com.feviro;

import java.awt.Graphics;

public class Infected {

  float x;
  float y;
  float minX, minY, maxX, maxY;
  int width, height, radius;

  private Textures textures;

  public Infected(float x, float y, Textures textures) {
    this.x = x;
    this.y = y;
    this.width = 20;
    this.height = 20;
    this.radius = 30;
    this.minX = x - this.radius;
    this.minY = y - this.radius;
    this.maxX = x + this.width - 1 + this.radius;
    this.maxY = y + this.height - 1 + this.radius;

    this.textures = textures;
  }

  public void render(Graphics g) {
    g.drawImage(textures.infected, (int) this.x, (int) this.y, null);
  }

}
