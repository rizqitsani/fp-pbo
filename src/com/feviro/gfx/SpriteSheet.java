package com.feviro.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private BufferedImage sheet;

	public SpriteSheet(BufferedImage sheet) {
		this.sheet = sheet;
	}

	public BufferedImage grabImage(int col, int row, int width, int height) {
		return sheet.getSubimage((col * 48) - 48, (row * 48) - 48, width, height);
	}

}
