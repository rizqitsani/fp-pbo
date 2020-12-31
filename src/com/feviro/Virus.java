package com.feviro;

import java.awt.Color;
import java.awt.Graphics;

public class Virus {

  float x;
  float y;

  public Virus(float x, float y) {
    this.x = x;
    this.y = y;
  }

  public void draw(Graphics g) {
    g.setColor(Color.GREEN);
    g.fillRect((int) this.x, (int) this.y, 20, 20);
  }

}
