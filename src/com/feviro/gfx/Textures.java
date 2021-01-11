package com.feviro.gfx;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Textures {

	private static final int width = 48, height = 48, frameWidth = 800, frameHeight = 600;

	public static BufferedImage infected, virusDead, menuBackground;
	public static BufferedImage[] playerUp, playerDown, playerLeft, playerRight, virusHit;

	private static List<SpriteSheet> spriteSheet = new ArrayList<SpriteSheet>();

	public static void init() {
		spriteSheet.add(new SpriteSheet(BufferedImageLoader.loadImage("/dante_0.png")));
		spriteSheet.add(new SpriteSheet(BufferedImageLoader.loadImage("/virus.png")));
		spriteSheet.add(new SpriteSheet(BufferedImageLoader.loadImage("/george.png")));
		spriteSheet.add(new SpriteSheet(BufferedImageLoader.loadImage("/Main Menu.png")));

		playerUp = new BufferedImage[4];
		playerDown = new BufferedImage[4];
		playerLeft = new BufferedImage[4];
		playerRight = new BufferedImage[4];
		virusHit = new BufferedImage[10];

		playerUp[0] = spriteSheet.get(0).grabImage(width * 2, 0, width, height);
		playerUp[1] = spriteSheet.get(0).grabImage(width * 2, height, width, height);
		playerUp[2] = spriteSheet.get(0).grabImage(width * 2, height * 2, width, height);
		playerUp[3] = spriteSheet.get(0).grabImage(width * 2, height * 3, width, height);

		playerDown[0] = spriteSheet.get(0).grabImage(0, 0, width, height);
		playerDown[1] = spriteSheet.get(0).grabImage(0, height, width, height);
		playerDown[2] = spriteSheet.get(0).grabImage(0, height * 2, width, height);
		playerDown[3] = spriteSheet.get(0).grabImage(0, height * 3, width, height);

		playerLeft[0] = spriteSheet.get(0).grabImage(width, 0, width, height);
		playerLeft[1] = spriteSheet.get(0).grabImage(width, height, width, height);
		playerLeft[2] = spriteSheet.get(0).grabImage(width, height * 2, width, height);
		playerLeft[3] = spriteSheet.get(0).grabImage(width, height * 3, width, height);

		playerRight[0] = spriteSheet.get(0).grabImage(width * 3, 0, width, height);
		playerRight[1] = spriteSheet.get(0).grabImage(width * 3, height, width, height);
		playerRight[2] = spriteSheet.get(0).grabImage(width * 3, height * 2, width, height);
		playerRight[3] = spriteSheet.get(0).grabImage(width * 3, height * 3, width, height);

		virusHit[0] = spriteSheet.get(1).grabImage(0, height * 2, width, height);
		virusHit[1] = spriteSheet.get(1).grabImage(width, height * 2, width, height);
		virusHit[2] = spriteSheet.get(1).grabImage(width * 2, height * 2, width, height);
		virusHit[3] = spriteSheet.get(1).grabImage(width * 3, height * 2, width, height);
		virusHit[4] = spriteSheet.get(1).grabImage(width * 4, height * 2, width, height);
		virusHit[5] = spriteSheet.get(1).grabImage(width * 5, height * 2, width, height);
		virusHit[6] = spriteSheet.get(1).grabImage(width * 6, height * 2, width, height);
		virusHit[7] = spriteSheet.get(1).grabImage(width * 7, height * 2, width, height);
		virusHit[8] = spriteSheet.get(1).grabImage(width * 8, height * 2, width, height);
		virusHit[9] = spriteSheet.get(1).grabImage(width * 9, height * 2, width, height);

		infected = spriteSheet.get(2).grabImage(0, 0, width, height);
		virusDead = spriteSheet.get(1).grabImage(width * 19, height, width, height);
		menuBackground = spriteSheet.get(3).grabImage(0, 0, frameWidth, frameHeight);
	}

}
