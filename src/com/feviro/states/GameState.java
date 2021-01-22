package com.feviro.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.feviro.Game;
import com.feviro.GameArea;
import com.feviro.gfx.Textures;
import com.feviro.objects.Bullet;
import com.feviro.objects.Cure;
import com.feviro.objects.GameObject;
import com.feviro.objects.Infected;
import com.feviro.objects.Player;
import com.feviro.objects.Virus;

public class GameState extends State {

	private GameArea area;
	private Player player;
	private List<GameObject> objectList = new ArrayList<GameObject>();

	private Random random;

	public GameState(Game game) {
		super(game);
		random = new Random();

		this.player = new Player(random.nextInt(game.getWidth()), random.nextInt(game.getHeight()), 1.5f, 1.02f, this.game);

		this.area = new GameArea(0, 0, game.getWidth(), game.getHeight(), Color.BLACK, Color.WHITE);

		for (int i = 0; i < 5; i++) {
			int x = random.nextInt(game.getWidth());
			int y = random.nextInt(game.getHeight());
			// infectedList.add(new Infected(x, y, this.game));
			objectList.add(new Infected(x, y, this.game));
		}

		for (int i = 0; i < 7; i++) {
			int x = random.nextInt(game.getWidth());
			int y = random.nextInt(game.getHeight());
			// virusList.add(new Virus(x, y, 0.3f, this.game));
			objectList.add(new Virus(x, y, 0.3f, this.game));
		}

		for (int i = 0; i < 5; i++) {
			int x = random.nextInt(game.getWidth());
			int y = random.nextInt(game.getHeight());
			// cureList.add(new Cure(x, y, this.game));
			objectList.add(new Cure(x, y, this.game));
		}

		objectList.add(player);
	}

	@Override
	public void tick() {
		int cureCounter = 0;

		for (GameObject o : objectList) {
			if (o instanceof Player) {
				player.tick(area, objectList);
			} else if (o instanceof Bullet) {
				Bullet bullet = (Bullet) o;
				bullet.tick(area);
			} else if (o instanceof Virus) {
				Virus virus = (Virus) o;
				virus.tick(player);
			} else if (o instanceof Cure) {
				cureCounter++;
			}
		}

		if (cureCounter == 0) {
			State.setCurrentState(game.getWinState());
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Textures.gameBackground, 0, 0, game.getWidth(), game.getHeight(), null);

		for (GameObject o : objectList) {
			if (o instanceof Player) {
				player.render(g);
			} else if (o instanceof Bullet) {
				Bullet bullet = (Bullet) o;
				if (bullet.collision(area)) {
					objectList.remove(bullet);
				}
				bullet.render(g);
			} else if (o instanceof Virus) {
				Virus virus = (Virus) o;

				for (GameObject b : objectList) {
					if (b instanceof Bullet) {
						if (virus.checkIsLive()) {
							if (virus.collision((Bullet) b)) {
								objectList.remove(b);
							}
						}
					}
				}

				if (virus.checkIsLive()) {
					player.collision(virus);
				}
				virus.render(g);
			} else if (o instanceof Cure) {
				Cure cure = (Cure) o;
				cure.collision(player);
				if (cure.checkIsObtained()) {
					objectList.remove(cure);
				}
				cure.render(g);
			} else if (o instanceof Infected) {
				Infected infected = (Infected) o;
				infected.render(g);
			}
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT) {
			player.moveLeft();
		} else if (key == KeyEvent.VK_UP) {
			player.moveUp();
		} else if (key == KeyEvent.VK_RIGHT) {
			player.moveRight();
		} else if (key == KeyEvent.VK_DOWN) {
			player.moveDown();
		}
		if (key == KeyEvent.VK_SHIFT) {
			player.boost();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT) {
			player.moveXStop();
		} else if (key == KeyEvent.VK_UP) {
			player.moveYStop();
		} else if (key == KeyEvent.VK_RIGHT) {
			player.moveXStop();
		} else if (key == KeyEvent.VK_DOWN) {
			player.moveYStop();
		} else if (key == KeyEvent.VK_SPACE) {
			player.shoot(objectList);
		}
		if (key == KeyEvent.VK_SHIFT) {
			player.boostStop();
		}
	}

}
