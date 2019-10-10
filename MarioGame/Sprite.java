import java.awt.image.BufferedImage;
import java.awt.Graphics;

abstract class Sprite{
	int x;
	int y;
	int w;
	int h;
	int prevX;
	int prevY;
	double yVel;
	int frame;
	int offGroundCount = 0;
	BufferedImage image;
	
	abstract void update();
	abstract void drawYourself(Graphics g);
	abstract Json makeJson();
	abstract boolean isItAtube();
	abstract boolean isItAgoomba();
	abstract boolean isItAmario();
	abstract boolean isItAfireball();

	static boolean doesCollide(int x1, int y1, int w1, int h1, int x2, 
		int y2, int w2, int h2){
			if(x1 + w1 < x2)
				return false;
			if(x1 > x2 + w2)
				return false;
			if(y1 + h1 < y2) // assumes bigger is downward
				return false;
			if(y1 > y2 + h2) // assumes bigger is downward
				return false;
			return true;
		}
	
	void getOutOfThis(Sprite s){
		offGroundCount = 0;
		if(x + w > s.x && prevX + w <= s.x) 
			x = s.x - w - 1;
        else if(x < s.x + s.w && prevX >= s.x + s.w) 
        	x = s.x + s.w;
        else if(y + h >= s.y && prevY + h < s.y) 
        {
            y = s.y - h - 1;
            offGroundCount = 0;
            yVel = 0;
        }
        else if(y < s.y + s.h && prevY >= s.y + s.h) 
        	y = s.y + s.h;
        else
            System.out.println("How did I get in here?");
	}

	boolean boundary(int posX, int posY){
		if (posX < x) return false;
		if (posX > x + w) return false;
		if (posY < y) return false;
		if (posY > y + h) return false;
		return true;
	}
	
	void rememberState(){
		prevX = x;
		prevY = y;
	}

	void jump()
	{
		yVel -= 11.5;
	}
}	