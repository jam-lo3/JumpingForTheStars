import java.awt.Graphics;
import java.awt.Image;

import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

// Controls the movement of the stars and what they do when they are touched
public class Stars extends MovingImage {
		// FIELDS
		private double xVel, yVel;
		private static int SPEED = 7;
		private boolean start = true;
		//private Image star;

		
		// CONSTRUCTORS
		
		public Stars(int x, int y) {
			super("Picture1.png", x, y, 30, 30);
			//star = (new ImageIcon("Picture1.png")).getImage();
		}
		
		
		// METHODS
		
		public void fallStars() {
			
			//yVel += Math.random() * 0.5;
			moveByAmount(0, SPEED);//(int) (yVel));

			
			
			if(this.getY() >= 480) {
				moveToLocation((int)(Math.random() *640), 0);
				yVel = 0;
			}
		}



		
		 
	
}
