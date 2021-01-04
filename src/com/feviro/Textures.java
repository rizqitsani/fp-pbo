package com.feviro;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Textures {

	public BufferedImage player, virus, infected;

	private List<SpriteSheet> ss = new ArrayList<SpriteSheet>();

	public Textures(Game game) {
		ss.add(new SpriteSheet(game.getSpriteSheet().get(0)));
		ss.add(new SpriteSheet(game.getSpriteSheet().get(1)));
		ss.add(new SpriteSheet(game.getSpriteSheet().get(2)));

		getTextures();
	}

	private void getTextures() {
		player = ss.get(0).grabImage(3, 1, 48, 48);
		virus = ss.get(1).grabImage(1, 1, 48, 48);
		infected = ss.get(2).grabImage(1, 1, 48, 48);
	}

}
