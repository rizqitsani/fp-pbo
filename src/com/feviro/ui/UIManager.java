package com.feviro.ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import com.feviro.Game;

public class UIManager {
	
	@SuppressWarnings("unused")
	private Game game;
	private List<UIObject> objects;
	
	public UIManager(Game game) {
		this.game = game;
		objects = new ArrayList<UIObject>();
	}
	
	public void tick() {
		for(UIObject o : objects)
			o.tick();
	}
	
	public void render(Graphics g) {
		for(UIObject o : objects)
			o.render(g);
	}
	
	public void onMouseMoved(MouseEvent e) {
		for(UIObject o : objects)
			o.onMouseMoved(e);
	}
	
	public void onMouseReleased(MouseEvent e) {
		for(UIObject o : objects)
			o.onMouseReleased(e);
	}
	
	public void addObject(UIObject o) {
		objects.add(o);
	}
	
	public void removeObject(UIObject o) {
		objects.remove(o);
	}


}
