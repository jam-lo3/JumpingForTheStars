
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;

import javax.swing.*;

public class Frame extends JPanel implements KeyListener
{
  public static final int DRAWING_WIDTH = 800;
  public static final int DRAWING_HEIGHT = 600;
  
  private Dude character;
  private MovingImage floor;
  
  private boolean leftKeyPressed, rightKeyPressed, upKeyPressed;

  public Frame () {
	  super();
	  character = new Dude(380,0);
	  floor = new MovingImage("null", 0,600,800,500);
	  setBackground(Color.BLUE);
  }

  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);  // Call JPanel's paintComponent method to paint the background

    int width = getWidth();
    int height = getHeight();
    
    double ratioX = (double)width/DRAWING_WIDTH;
    double ratioY = (double)height/DRAWING_HEIGHT;
    
    Graphics2D g2 = (Graphics2D)g;
    AffineTransform at = g2.getTransform();
    g2.scale(ratioX,ratioY);
    
    g.setColor(Color.BLACK);
    character.draw(g,this);
    
    g2.setTransform(at);
  }

  
  public void keyPressed(KeyEvent e) {
  	if (e.getKeyCode() == KeyEvent.VK_UP) {
  		character.jump();
  		upKeyPressed = true;
  	} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
  		rightKeyPressed = true;
  	} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
		leftKeyPressed = true;
  	}
  }
    
  public void keyReleased(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
		leftKeyPressed = false;
  	} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
  		rightKeyPressed = false;
  	} else if (e.getKeyCode() == KeyEvent.VK_UP) {
  		upKeyPressed = false;
  	}
  }

  public void keyTyped(KeyEvent e) {
  	
  }
  
  
  public void run() {
  	while(true) {
  		// MAKE A CHANGE
  		if (leftKeyPressed) {
  			character.walk(-1);
  	  	} else if (rightKeyPressed) {
  			character.walk(1);
  	  	} else if (upKeyPressed) {
  			character.jump();
  	  	}
  	  		
  		
  		character.fall(floor);
  		checkMario();
  		
  		// SHOW THE CHANGE
  		repaint();
  		
  		
  		// WAIT
  		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  		
  	}
  }
  
  public void checkMario() {
  	int x = character.getX() + character.getWidth()/2;
  	int y = character.getY() + character.getHeight()/2;
  	if (x < 0 || x > DRAWING_WIDTH || y < 0 || y > DRAWING_HEIGHT)
  		character = new Dude(380,410);
  }


  // As your program grows, you may want to...
  //   1) Move this main method into its own 'main' class
  //   2) Customize the JFrame by writing a class that extends it, then creating that type of object in your main method instead
  public static void main(String[] args)
  {
    JFrame w = new JFrame("Jumping For the Stars");
    w.setBounds(100, 100, 640, 480);
    w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Frame panel = new Frame();
    w.addKeyListener(panel);
    w.add(panel);
    w.setResizable(true);
    w.setVisible(true);
    
    panel.run();
  }
}
