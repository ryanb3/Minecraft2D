package map;

import javax.swing.JFrame;

import physicsEngine.physicsEngine;
import player.player;
import block.block;
import block.air;
import userControl.*;
import main.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class map extends JFrame {
	ArrayList<ArrayList<block>> chunk; // Horizonatal Rows that are 1 tall
	public player player;
	public int blockHeight;
	int mapHeightUntilAir;
	public int mapWidth;
	public int mapHeight;
	public int mapAir;
	public int jumpSpeed;
	public int gravitySpeed;
	public double jumpDistance;
	public int walkSpeed;
	jump jump;
	movePlayer moveLeft;
	movePlayer moveRight;
	movePlayer moveUp;
	movePlayer moveDown;
	air air;
	public physicsEngine physics;
	public Boolean jumping;
	Boolean creative;
	double startTime = System.nanoTime();

	public map(Boolean creative) {
		initVar(creative);
		drawPlayer();
		drawMap();
		startUserControl();
		initPhysics();
		startPhysics();
		System.out.println("The Game Has Begun!");
	}

	public void initVar(Boolean creativ) {
		blockHeight = 64; // Sets Block Pixel Height
		mapHeightUntilAir = 5; // Sets Map Height From the Base to the grass in
								// block width
		mapWidth = (main.screenWidth) / blockHeight;
		mapHeight = (main.screenHeight) // Calculates
										// the
										// total(top
										// to
										// bottom)
										// map
										// height
										// in
										// blocks
				/ blockHeight;
		mapAir = mapHeight - mapHeightUntilAir; // Calculates the height of the
												// amount of air
		jumpSpeed = blockHeight * 2;// Pixels per Second
		gravitySpeed = blockHeight * 2;// Pixels per Second
		jumpDistance = 1.5; // In Block Width
		jumping = false;
		walkSpeed = blockHeight * 3;
		chunk = new ArrayList<ArrayList<block>>();
		for (int i = 0; i < mapHeight; i++) {
			chunk.add(new ArrayList<block>());
		}
		creative = creativ;
		player = new player();
		air = new air();
		System.out.println("Variables Initialized" + " In " + (System.nanoTime()-startTime) + " Nanoseconds");
	}

	public void drawMap() {
		drawAir();
		drawDirt();
		drawGrass();
		System.out.println("Map Drawn" + " In " + (System.nanoTime()-startTime) + " Nanoseconds");
	}

	public void drawDirt() {
		int current = 0;
		int rowID = (mapAir);
		for (int i = 0; i < mapHeight - mapAir; i++) {
			current = 0;
			for (int x = 0; x < mapWidth; x++) {
				chunk.get(rowID).add(new block("dirt.jpg"));
				chunk.get(rowID)
						.get(current)
						.setBounds((x * blockHeight), ((rowID) * blockHeight),
								blockHeight, blockHeight);
				add(chunk.get(rowID).get(current), 1);
				current++;
			}
			rowID = rowID + 1;

		}
		System.out.println("Dirt Drawn" + " In " + (System.nanoTime()-startTime) + " Nanoseconds");
//		int chunkNum = 5;
//		chunk.get(chunkNum).add(new block("dirt.jpg"));
//		chunk.get(chunkNum).get(0).setBounds(600, (chunkNum*blockHeight), blockHeight, blockHeight);
//		add(chunk.get(chunkNum).get(0),1);
	}

	public void drawGrass() {
		int current = 0;
		int rowID = (mapAir - 1);
		for (int x = 0; x < mapWidth; x++) {
			chunk.get(rowID).add(new block("grass.jpg"));
			chunk.get(rowID)
					.get(current)
					.setBounds((x * blockHeight), ((mapAir - 1) * blockHeight),
							blockHeight, blockHeight);
			add(chunk.get(rowID).get(current), 1);
			current++;
		}
		System.out.println("Grass Drawn" + " In " + (System.nanoTime()-startTime) + " Nanoseconds");
	}

	public void drawAir() {
		air.setBounds(0, 0, main.screenWidth, main.screenHeight);
		add(air, 1);
		System.out.println("Air Drawn" + " In " + (System.nanoTime()-startTime) + " Nanoseconds");
	}

	public void drawPlayer() {
		player.setBounds(((main.screenWidth) / 2),
				(((mapAir - 1) * 64) - player.getPlayerHeight()),
				player.getPlayerWidth(), player.getPlayerHeight());
		add(player, 0);
		System.out.println("Player Drawn" + " In " + (System.nanoTime()-startTime) + " Nanoseconds");
	}

	public void startUserControl() {
		keyListener keyListener = new keyListener();
		this.addKeyListener(keyListener);
		this.setLayout(null);
		System.out.println("User Controls Started" + " In " + (System.nanoTime()-startTime) + " Nanoseconds");
	}

	public void initPhysics() {
		physics = new physicsEngine(creative);
		System.out.println("Physics Initalized" + " In " + (System.nanoTime()-startTime) + " Nanoseconds");
	}

	public void startPhysics() {
		physics.start();
		System.out.println("Physics Started" + " In " + (System.nanoTime()-startTime) + " Nanoseconds");
	}

	public class keyListener implements KeyListener {
		public void keyPressed(KeyEvent e) {

			int key = e.getKeyCode();

			if (key == KeyEvent.VK_W && physics.getColisionTop() == false) {
				if (creative == false) {
					if (jump == null && jumping == false
							&& physics.getColisionBottom() == true) {
						jump = new jump();
						jump.start();
						jumping = true;
					}
				} else {
					if (moveUp != null) {
						if (moveUp.getRunning() == false) {
							moveUp = new movePlayer(null, true);
							moveUp.start();
						}
					} else {
						moveUp = new movePlayer(null, true);
						moveUp.start();
					}
				}
			}

			if (key == KeyEvent.VK_D && physics.getColisionRight() == false) {
				if (moveRight != null) {
					if (moveRight.getRunning() == false) {
						moveRight = new movePlayer(false, null);
						moveRight.start();
					}
				} else {
					moveRight = new movePlayer(false, null);
					moveRight.start();
				}
			}

			if (key == KeyEvent.VK_A && physics.getColisionLeft() == false) {
				if (moveLeft != null) {
					if (moveLeft.getRunning() == false) {
						moveLeft = new movePlayer(true, null);
						moveLeft.start();
					}
				} else {
					moveLeft = new movePlayer(true, null);
					moveLeft.start();
				}
			}

			if (key == KeyEvent.VK_S && physics.getColisionBottom() == false) {
				if (creative == true) {
					if (moveDown != null) {
						if (moveDown.getRunning() == false) {
							moveDown = new movePlayer(null, false);
							moveDown.start();
						}
					} else {
						moveDown = new movePlayer(null, false);
						moveDown.start();
					}
				}
			}
		}

		@SuppressWarnings("deprecation")
		public void keyReleased(KeyEvent e) {

			int key = e.getKeyCode();

			if (key == KeyEvent.VK_W) {
				if (creative == false) {
					jump = null;
				} else {
					if (moveUp != null) {
						moveUp.stop();
						moveUp = null;
					}
				}
			}

			if (key == KeyEvent.VK_D) {
				if (moveRight != null) {
					moveRight.stop();
					moveRight = null;
				}

			}

			if (key == KeyEvent.VK_A) {
				if (moveLeft != null) {
					moveLeft.stop();
					moveLeft = null;
				}
			}

			if (key == KeyEvent.VK_S) {
				if (creative == true) {
					if (moveDown != null) {
						moveDown.stop();
						moveDown = null;
					}
				}
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}
	}

	public void doneJumping() {
		jumping = false;
	}

	public block getBlock(int i, int x) {
		try {
			if (chunk.get(i).get(0).equals(null)) {
				return null;
			} else {
				return chunk.get(i).get(x);
			}
		} catch (IndexOutOfBoundsException ex) {

		}
		return null;
	}

	public Boolean getCollisionLeft() {
		return physics.getColisionLeft();
	}

	public Boolean getCollisionRight() {
		return physics.getColisionRight();
	}

	public Boolean getCollisionTop() {
		return physics.getColisionTop();
	}

	public Boolean getCollisionBottom() {
		return physics.getColisionBottom();
	}

}