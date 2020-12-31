package com.feviro;

import java.awt.Color;
import java.awt.Graphics;

public class Infected {

  float x;
  float y;

  public Infected(float x, float y) {
    this.x = x;
    this.y = y;
  }

  public void draw(Graphics g) {
    g.setColor(Color.BLUE);
    g.fillOval((int) this.x, (int) this.y, 20, 20);
  }

}
