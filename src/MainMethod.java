import java.awt.Color;

import javax.swing.JFrame;

public class MainMethod extends Frame{

	 public static void main(String[] args)
	  {
		Color c1 = new Color (27, 34, 53);
	    JFrame frame = new JFrame("Jumping For The Stars");
	    frame.add(new JumpingForTheStars());
	    frame.setBounds(100, 100, 640, 480);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    JumpingForTheStars panel = new JumpingForTheStars();
	    panel.setBackground(c1);
	    frame.addKeyListener(panel);
	    frame.add(panel);
	    frame.setResizable(true);
	    frame.setVisible(true);
	    frame.setLocationRelativeTo(frame);
	    panel.run();
	  }
	 
}
