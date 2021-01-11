package com.feviro.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

import com.feviro.Game;
import com.feviro.GameArea;
import com.feviro.gfx.Animation;
import com.feviro.gfx.Textures;
import com.feviro.states.State;

public class Player extends GameObject {
  private float width, height;
  private float baseSpeed, boostSpeed;
  private float speedX;
  private float speedY;
  private int health;
  private boolean isBoosted;

  // Animations
  private Animation currentAnim;
  private Animation animUp;
  private Animation animDown;
  private Animation animLeft;
  private Animation animRight;
  
  // Direction
  private String currentDirection;

  public Player(float x, float y, float baseSpeed, float boostSpeed, Game game) {
    super(x, y, game);
    this.baseSpeed = baseSpeed;
    this.boostSpeed = boostSpeed;
    this.width = 48;
    this.height = 48;
    this.health = 100;

    // Animations
    animUp = new Animation(250, Textures.playerUp);
    animDown = new Animation(250, Textures.playerDown);
    animLeft = new Animation(250, Textures.playerLeft);
    animRight = new Animation(250, Textures.playerRight);

    currentAnim = animUp;
    currentDirection = "UP";
  }

  public void tick(GameArea area, List<Infected> infectedList) {
    animUp.tick();
    animDown.tick();
    animLeft.tick();
    animRight.tick();

    if (isBoosted) {
    	speedX *= this.boostSpeed;
    	speedY *= this.boostSpeed;
    }
    System.out.println(speedX);
    
    this.x += (speedX * baseSpeed);
    this.y += (speedY * baseSpeed);

    this.collision(area);
    for (Infected infected : infectedList) {
      this.collision(infected);
    }

    checkIsLive();
  }

  public void render(Graphics g) {
    g.drawImage(getCurrentAnimationFrame(), (int) this.x, (int) this.y, (int) this.width, (int) this.height, null);
    g.drawString(String.valueOf(this.health), 20, 580);
  }

  private BufferedImage getCurrentAnimationFrame() {
    if (speedX > 0) {
      currentAnim = animRight;
    } else if (speedX < 0) {
      currentAnim = animLeft;
    } else if (speedY > 0) {
      currentAnim = animDown;
    } else if (speedY < 0) {
      currentAnim = animUp;
    }

    return currentAnim.getCurrentFrame();
  }

  public void checkIsLive() {
    if (this.health <= 0) {
      State.setCurrentState(game.getGameOverState());
    }
  }

  public float getX() {
    return x;
  }

  public float getY() {
    return y;
  }
  
  public float getWidth() {
	  return width;
  }
  
  public float getHeight() {
	  return height;
  }

  public void moveLeft() {
    this.speedX = -1;
    this.currentDirection = "LEFT";
  }

  public void moveUp() {
    this.speedY = -1;
    this.currentDirection = "UP";
  }

  public void moveRight() {
    this.speedX = 1;
    this.currentDirection = "RIGHT";
  }

  public void moveDown() {
    this.speedY = 1;
    this.currentDirection = "DOWN";
  }

  public void moveXStop() {
    this.speedX = 0;
  }

  public void moveYStop() {
    this.speedY = 0;
  }
  
  public void boost() {
	  this.isBoosted = true;
  }
  
  public void boostStop() {
	  this.isBoosted = false;
  }  

  public void shoot(List<Bullet> list) {
	int bulletAngle = 0;
	float startX = 0;
	float startY = 0;
	switch (currentDirection) {
	case "UP":
		bulletAngle = -90; 
		startX = this.x + (this.width / 2);
		startY = this.y;
		break;
	case "DOWN":
		bulletAngle = 90;
		startX = this.x + (this.width / 2);
		startY = this.y + this.height;
		break;
	case "LEFT":
		bulletAngle = 180; 
		startX = this.x;
		startY = this.y + (this.height / 2);
		break;
	case "RIGHT":
		bulletAngle = 0;
		startX = this.x + this.width;
		startY = this.y + (this.height / 2);
		break;
	default:
		break;
	}
	
	list.add(new Bullet(startX, startY, 5, 5, bulletAngle, Color.RED, game));
    
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

    // if (this.x < playerMinX) {
    // this.x = playerMinX;
    // } else if (this.x > playerMaxX) {
    // this.x = playerMaxX;
    // }
    //
    // if (this.y < playerMinY) {
    // this.y = playerMinY;
    // } else if (this.y > playerMaxY) {
    // this.y = playerMaxY;
    // }
  }

}
