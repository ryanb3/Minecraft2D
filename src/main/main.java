package main;

import java.awt.Toolkit;

import java.util.ArrayList;

import player.player;
import userControl.jump;
import block.block;
import map.map;
import userControl.movePlayer;

public class main {

	static map map;
	public static int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static int screenHeight = Toolkit.getDefaultToolkit()
			.getScreenSize().height;
	private static ArrayList<block> blocks;

	public static void main(String[] args) {
		screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		map = new map(false);
		map.pack();
		map.setBounds(0, 0, screenWidth, screenHeight);
		map.setVisible(true);
	}

	public static ArrayList<block> getBlocks(int height) {
		try {
		blocks = new ArrayList<block>();
		for (int i = 0; i < (map.mapWidth); i++) {
			blocks.add(map.getBlock(height, i));
		}
		return blocks;
		} catch(ArrayIndexOutOfBoundsException ex) {
			
		}
		return null;
	}
	
	public static Boolean getCreative() {
		return map.creative;
	}
	
	public static player getPlayer() {
		return map.player;
	}
	
	public static int getWalkSpeed() {
		return map.walkSpeed;
	}
	
	public static int getBlockHeight() {
		return map.blockHeight;
	}
	
	public static void movePlayer(int x, int y) {
		map.player.setBounds(map.player.getBounds().x + x,
				(map.player.getBounds().y) + y,
				map.player.getBounds().width,
				map.player.getBounds().height);
	}
	
	public static jump getJumpingObject() {
		return map.jump;
	}
	
	public static void startjumping() {
		map.jump = new jump();
		map.jump.start();
		map.jumping = true;
	}
	
	public static Boolean getJumping() {
		return map.jumping;
	}
	
	public static void doneJumping() {
		map.doneJumping();
	}
	
	public static void setJumpNull() {
		map.jump = null;
	}
	
	public static double getJumpDistance() {
		return map.jumpDistance;
	}

	public static double getJumpSpeed() {
		return map.jumpSpeed;
	}
	
	public static Boolean getCollisionTop() {
		return map.getCollisionTop();
	}
	
	public static Boolean getCollisionBottom() {
		return map.getCollisionBottom();
	}
	
	public static Boolean getCollisionLeft() {
		return map.getCollisionLeft();
	}
	
	public static Boolean getCollisionRight() {
		return map.getCollisionRight();
	}
	
	public static movePlayer getMoveLeft() {
		return map.moveLeft;
	}
	
	public static void startMoveLeft() {
		map.moveLeft = new movePlayer(true, null);
		map.moveLeft.start();
	}
	
	public static void stopMoveLeft() {
		map.moveLeft.stop();
		map.moveLeft = null;
	}
	
	public static movePlayer getMoveRight() {
		return map.moveRight;
	}
	
	
	public static void startMoveRight() {
		map.moveRight = new movePlayer(false, null);
		map.moveRight.start();
	}
	
	public static void stopMoveRight() {
		map.moveRight.stop();
		map.moveRight = null;
	}
	
	public static movePlayer getMoveDown() {
		return map.moveDown;
	}
	
	public static void startMoveDown() {
		map.moveDown = new movePlayer(null, false);
		map.moveDown.start();
	}
	
	public static void stopMoveDown() {
		map.moveDown.stop();
		map.moveDown = null;
	}
	
	public static movePlayer getMoveUp() {
		return map.moveUp;
	}
	
	public static void startMoveUp() {
		map.moveUp = new movePlayer(null, true);
		map.moveUp.start();
	}
	
	public static void stopMoveUp() {
		map.moveUp.stop();
		map.moveUp = null;
	}
	
}
