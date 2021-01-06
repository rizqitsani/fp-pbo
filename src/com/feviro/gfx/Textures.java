package com.feviro.gfx;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Textures {

	public static BufferedImage player, virus, infected;

	private static List<SpriteSheet> spriteSheet = new ArrayList<SpriteSheet>();
	
	public static void init() {
		spriteSheet.add(new SpriteSheet(BufferedImageLoader.loadImage("/dante_0.png")));
		spriteSheet.add(new SpriteSheet(BufferedImageLoader.loadImage("/virus.png")));
		spriteSheet.add(new SpriteSheet(BufferedImageLoader.loadImage("/george.png")));
		
		player = spriteSheet.get(0).grabImage(3, 1, 48, 48);
		virus = spriteSheet.get(1).grabImage(1, 1, 48, 48);
		infected = spriteSheet.get(2).grabImage(1, 1, 48, 48);
	}

}
