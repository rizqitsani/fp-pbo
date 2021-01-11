package com.feviro.objects;

import java.awt.Color;
import java.awt.Graphics;

import com.feviro.Game;
import com.feviro.gfx.Textures;

public class Cure extends GameObject {
	
	private int width, height;
	private boolean isObtained;

	public Cure(float x, float y, Game game) {
		super(x, y, game);
		this.width = 24;
	    this.height = 24;
	    
	    this.isObtained = false;
	}
	
	public void render(Graphics g) {
	    g.drawImage(Textures.cure, (int) this.x, (int) this.y, this.width, this.height, null);
	    g.setColor(Color.GREEN);
		g.drawRect((int) this.x, (int) this.y, width, height);
	}
	
	public boolean checkIsObtained() {
		return isObtained;
	}
	
	public void collision(Player player) {
		
		float playerMinX = player.getX();
	    float playerMinY = player.getY();
	    float playerMaxX = player.getX() + player.getWidth();
	    float playerMaxY = player.getY() + player.getHeight();

	    if (playerMinX < (this.x + this.height) && playerMaxX > (this.x) && playerMinY < (this.y + this.height) && playerMaxY > (this.y)) {
	    	System.out.println("Yay");
	        this.isObtained = true;
	    }
	}

}
