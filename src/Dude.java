
public class Dude extends MovingImage {
	// FIELDS
	private double velX, velY;
	private boolean onSurface;
	
	// CONSTRUCTOR
	public Dude(int x, int y) {
		//if(isBunny){
		super("bunny.png",250,310,40,60);
		//}
		//if(isKitty){
		//	super("kitty.png",350,410,40,60);
		//}
		//if(isSheep){
		//	super("sheep.png",350,410,40,60);
		//}    **idea*** create, method for getCharacter that returns is a character is true or not....(?)
		velX = 0;
		velY = 0;
		onSurface = false; 
	}
	
	// METHODS
	public void walk(int dir) {
		if (Math.abs(velX) < 10)
			velX += dir;
	}

	
	public void jump() {
		if (onSurface) {
			velY = -15;
			moveByAmount(0,(int)velY);
		}
	}
	
	public void fall(MovingImage floor) {
		if (velY < 15) {
			velY += 0.5; // Gravity
		}
		velX *= 0.9; // Friction
		
		onSurface = false;
		
		if ( floor.isPointInImage( getX()+getWidth()/2 , getY() + getHeight()+5 )) {
			velY = 0;
			onSurface = true;
		}
		
		moveByAmount((int)velX, (int)velY);
	}
	
	
}