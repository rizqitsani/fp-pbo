package com.feviro.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

import com.feviro.GameArea;
import com.feviro.gfx.Animation;
import com.feviro.gfx.Textures;

public class Player extends GameObject {
  private float width, height;
  private float baseSpeed;
  private float speedX;
  private float speedY;
  private int health;

  // Animations
  private Animation animUp;
  private Animation animDown;
  private Animation animLeft;
  private Animation animRight;

  public Player(float x, float y, float baseSpeed) {
    super(x, y);
    this.baseSpeed = baseSpeed;
    this.width = 10;
    this.height = 10;
    this.health = 100;

    // Animations
    animUp = new Animation(250, Textures.playerUp);
    animDown = new Animation(250, Textures.playerDown);
    animLeft = new Animation(250, Textures.playerLeft);
    animRight = new Animation(250, Textures.playerRight);
  }

  public void tick(GameArea area, List<Infected> infectedList) {
    animUp.tick();
    animDown.tick();
    animLeft.tick();
    animRight.tick();

    this.x += (speedX * baseSpeed);
    this.y += (speedY * baseSpeed);

    this.collision(area);
    for (Infected infected : infectedList) {
      this.collision(infected);
    }

    checkIsLive();
  }

  public void render(Graphics g) {
    g.drawImage(getCurrentAnimationFrame(), (int) this.x, (int) this.y, null);
  }

  private BufferedImage getCurrentAnimationFrame() {
    if (speedX > 0) {
      return animRight.getCurrentFrame();
    } else if (speedX < 0) {
      return animLeft.getCurrentFrame();
    } else if (speedY > 0) {
      return animDown.getCurrentFrame();
    } else if (speedY < 0) {
      return animUp.getCurrentFrame();
    }

    return animUp.getCurrentFrame();
  }

  public void checkIsLive() {
    if (this.health <= 0) {
      // TODO ubah kondisi
    }
  }

  public float getX() {
    return x;
  }

  public float getY() {
    return y;
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
    float playerMinX = area.getMinX();
    float playerMinY = area.getMinY();
    float playerMaxX = area.getMaxX();
    float playerMaxY = area.getMaxY();

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
    float playerMinX = infected.getMinX() - this.width;
    float playerMinY = infected.getMinY() - this.height;
    float playerMaxX = infected.getMaxX() + this.width;
    float playerMaxY = infected.getMaxY() + this.height;

    if (this.x > playerMinX && this.x < playerMaxX && this.y > playerMinY && this.y < playerMaxY) {
      this.health -= 1;
    }
  }

}
