package com.feviro.gfx;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Textures {

	public static BufferedImage infected, virusDead;
	public static BufferedImage[] playerUp, playerDown, playerLeft, playerRight, virusHit;

	private static List<SpriteSheet> spriteSheet = new ArrayList<SpriteSheet>();

	public static void init() {
		spriteSheet.add(new SpriteSheet(BufferedImageLoader.loadImage("/dante_0.png")));
		spriteSheet.add(new SpriteSheet(BufferedImageLoader.loadImage("/virus.png")));
		spriteSheet.add(new SpriteSheet(BufferedImageLoader.loadImage("/george.png")));

		playerUp = new BufferedImage[4];
		playerDown = new BufferedImage[4];
		playerLeft = new BufferedImage[4];
		playerRight = new BufferedImage[4];
		virusHit = new BufferedImage[10];

		playerUp[0] = spriteSheet.get(0).grabImage(3, 1, 48, 48);
		playerUp[1] = spriteSheet.get(0).grabImage(3, 2, 48, 48);
		playerUp[2] = spriteSheet.get(0).grabImage(3, 3, 48, 48);
		playerUp[3] = spriteSheet.get(0).grabImage(3, 4, 48, 48);

		playerDown[0] = spriteSheet.get(0).grabImage(1, 1, 48, 48);
		playerDown[1] = spriteSheet.get(0).grabImage(1, 2, 48, 48);
		playerDown[2] = spriteSheet.get(0).grabImage(1, 3, 48, 48);
		playerDown[3] = spriteSheet.get(0).grabImage(1, 4, 48, 48);

		playerLeft[0] = spriteSheet.get(0).grabImage(2, 1, 48, 48);
		playerLeft[1] = spriteSheet.get(0).grabImage(2, 2, 48, 48);
		playerLeft[2] = spriteSheet.get(0).grabImage(2, 3, 48, 48);
		playerLeft[3] = spriteSheet.get(0).grabImage(2, 4, 48, 48);

		playerRight[0] = spriteSheet.get(0).grabImage(4, 1, 48, 48);
		playerRight[1] = spriteSheet.get(0).grabImage(4, 2, 48, 48);
		playerRight[2] = spriteSheet.get(0).grabImage(4, 3, 48, 48);
		playerRight[3] = spriteSheet.get(0).grabImage(4, 4, 48, 48);

		virusHit[0] = spriteSheet.get(1).grabImage(1, 3, 48, 48);
		virusHit[1] = spriteSheet.get(1).grabImage(2, 3, 48, 48);
		virusHit[2] = spriteSheet.get(1).grabImage(3, 3, 48, 48);
		virusHit[3] = spriteSheet.get(1).grabImage(4, 3, 48, 48);
		virusHit[4] = spriteSheet.get(1).grabImage(5, 3, 48, 48);
		virusHit[5] = spriteSheet.get(1).grabImage(6, 3, 48, 48);
		virusHit[6] = spriteSheet.get(1).grabImage(7, 3, 48, 48);
		virusHit[7] = spriteSheet.get(1).grabImage(8, 3, 48, 48);
		virusHit[8] = spriteSheet.get(1).grabImage(9, 3, 48, 48);
		virusHit[9] = spriteSheet.get(1).grabImage(10, 3, 48, 48);

		infected = spriteSheet.get(2).grabImage(1, 1, 48, 48);
		virusDead = spriteSheet.get(1).grabImage(20, 2, 48, 48);
	}

}
