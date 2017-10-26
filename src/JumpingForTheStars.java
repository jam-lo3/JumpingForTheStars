import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;

import javax.swing.*;

/**
   TODO Write a one-sentence summary of your class here.
   TODO Follow it with additional details about its purpose, what abstraction
   it represents, and how to use it.

   @author  TODO Jamie

 */
public class JumpingForTheStars extends JPanel implements KeyListener
{
  // TODO Your Instance Variables Here
	private Image starPic, starBack;
	private int xCoordinate, yCoordinate;
	private int numStars = 5;
	private Stars[] stars = new Stars[numStars];
	private Dude character;
	private MovingImage floor;
	private boolean leftKeyPressed, rightKeyPressed, upKeyPressed;
	private int score = 0;

	private static int DRAWING_WIDTH = 600;
	private static int DRAWING_HEIGHT = 800;
	
  public JumpingForTheStars () {
	  super();
	  xCoordinate = (int) (Math.random() * 610);
	  starPic = (new ImageIcon("Picture1.png")).getImage();
	  createStars();
	  
	  
	  starBack = (new ImageIcon("NightSky.png")).getImage();
	  int x = getX();
	  int y = getY();
	  
	  character = new Dude(380,0);
	  floor = new MovingImage("null", 0, 480, 680, 500);
  }

// Create 5 stars
  //pC: draw all of the stars (g, this)
  //run: fall (random yVel), check
  //check each Star
  private void createStars()
  {
	  for(int i = 0; i < numStars; i++)
	  {
		  stars[i] = new Stars((int)(Math.random()*DRAWING_WIDTH), 0);
	  }
  }
  
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);  

    int width = getWidth();
    int height = getHeight();

    double ratioX = width / 640.0;
    double ratioY = height / 480.0;
    
    Graphics2D g2 = (Graphics2D)g;
    AffineTransform at = g2.getTransform();
    g2.scale(ratioX, ratioY);

    //draws compoonents of the game
    g.drawImage(starBack, 0, 0, 640, 480, this);
    character.draw(g,this);
    for(int i = 0; i < numStars; i++)
    {
    	stars[i].draw(g, this);
    }
    g.setColor(Color.white);
    g.drawString("Stars: " + score, 10, 30);
    
    g2.setTransform(at);
  }


  public void run() {
	  	while(true) {
	  		
	  		if (leftKeyPressed) {
	  			character.walk(-1);
	  	  	} else if (rightKeyPressed) {
	  			character.walk(1);
	  	  	} else if (upKeyPressed) {
	  			character.jump();
	  	  	}
	  	  		
	  		
	  		character.fall(floor);
	  		checkDude();
	  		
	  		//repaint();
	  		
	  		
	  		for(int i = 0; i<numStars; i++)
	  		{
		  		stars[i].fallStars();
		  		checkCollisions(character, stars[i]);
		  		repaint();
	  		}
	  		
	  		// WAIT
	  		
	  		try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  		
	  	}
	  }
  
  public void checkDude() {
	  	int x = character.getX() + character.getWidth()/2;
	  	int y = character.getY() + character.getHeight()/2;
	  	if (y < 0 || y > DRAWING_HEIGHT)
	  		{character = new Dude(380,0);
	  		}
	  	
	  }
 
  
  public void checkCollisions(MovingImage x, MovingImage y) {

      Rectangle r3 = x.getBounds();

      Rectangle r2 = y.getBounds();

          if (r3.intersects(r2)) {
              y.moveToLocation((int)(Math.random() *640), 0);
              score++;
              System.out.println(score);
          }
      }

  // As your program grows, you may want to...
  //   1) Move this main method into its own 'main' class
  //   2) Customize the JFrame by writing a class that extends it, then creating that type of object in your main method instead

  

@Override
public void keyPressed(KeyEvent e) {
	// TODO Auto-generated method stub
	if (e.getKeyCode() == KeyEvent.VK_UP) {
  		character.jump();
  		upKeyPressed = true;
  	}
	else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
		leftKeyPressed = true;
  	} 
	
	else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
  		rightKeyPressed = true;
  	}
	

}

@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	 if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			leftKeyPressed = false;
	  	} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
	  		rightKeyPressed = false;
	  	} else if (e.getKeyCode() == KeyEvent.VK_UP) {
	  		upKeyPressed = false;
	  	}
}

@Override
public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
}

}
