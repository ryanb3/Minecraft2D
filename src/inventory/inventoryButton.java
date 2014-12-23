package inventory;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Image;

import main.main;

import javax.swing.JButton;

public class inventoryButton extends JButton { //The object that is the buttons in the inventory
	private int amount = 0;
	private int blockID;
	public double serialID;
	private Image image;

	public inventoryButton(Image image1, int amount1, Integer blockID1) {
		image = image1;
		serialID = Math.random();
		amount = amount1;
		blockID = blockID1;
	}

	@Override
	protected void paintComponent(Graphics g) { //Draws its image and the block amount it stores
		g.drawImage(image, 0, 0, main.blockHeight, main.blockHeight, null);
		g.setColor(Color.black);
		g.drawString(Integer.toString(amount), 5, main.blockHeight - 5);
	}

	public int addBlock(int add, int blockID1) { //Adds an amount of blocks to the variables	
		int space = 64 - amount;				 //Returns what it cannot add
		int extra = add - space;
		if (blockID == 0) {
			amount = add;
			blockID = blockID1;
		} else if (blockID == blockID1) {
			if (add < space && amount + add <= 64) {
				amount = amount + add;
			} else if (add <= 64 && space < 64) {
				amount = 64;
			}
		} else {
			return add;
		}
		if (extra > 0) {
			return extra;
		}
		repaint();
		return 0;
	}

	public void setValue(int value, int blockID1) { //Overwrites the before set variables
		amount = value;
		blockID = blockID1;
	}

	public int getBlockID() {
		return blockID;
	}

	public int getAmount() {
		return amount;
	}

	public void subtractOne() {
		amount--;
	}
}