package block;

import java.awt.Color; 
import java.awt.Graphics;

import javax.swing.JPanel;

import main.main;

public class air extends JPanel { 
	Color airColor;
	public air(Color airColor1) {
		airColor = airColor1;
	}
	
	@Override
	protected void paintComponent(Graphics g) { //Draws a large(fills the whole screen)
												//panel of the requested color
		g.setColor(airColor);
		g.fillRect(0, 0, main.screenWidth,
				main.screenHeight);
	}
}
