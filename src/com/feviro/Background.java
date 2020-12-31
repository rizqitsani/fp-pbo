package com.feviro;

import java.awt.Dimension;

import javax.swing.JPanel;

public class Background extends JPanel {

	private int areaWidth;
	private int areaHeight;

	public Background(int width, int height) {
		this.areaWidth = width;
		this.areaHeight = height;
		this.setPreferredSize(new Dimension(areaWidth, areaHeight));
	}

}
