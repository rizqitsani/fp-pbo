package com.feviro.objects;

import java.awt.Color;
import java.awt.Graphics;

import com.feviro.Game;
import com.feviro.gfx.Textures;

public class Cure extends GameObject {
	
	private int width, height;

	public Cure(float x, float y, Game game) {
		super(x, y, game);
		this.width = 24;
	    this.height = 24;
	}
	
	public void render(Graphics g) {
	    g.drawImage(Textures.cure, (int) this.x, (int) this.y, this.width, this.height, null);
	    g.setColor(Color.GREEN);
		g.drawRect((int) this.x, (int) this.y, width, height);
	}

}
