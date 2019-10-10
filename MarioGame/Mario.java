import java.awt.image.BufferedImage;
import java.awt.Graphics;

class Mario extends Sprite{
	View view;
	Model model;
	static BufferedImage[] image;

	public Mario(Model m){
		model = m;
		x = 200;
		y = 105;
		w = 60;
		h = 95;
		yVel = -12.0;
		image = new BufferedImage[5];
		if (image[0] == null) image[0] = view.loadImage("mario0.png");
		if (image[1] == null) image[1] = view.loadImage("mario1.png");
		if (image[2] == null) image[2] = view.loadImage("mario2.png");
		if (image[3] == null) image[3] = view.loadImage("mario3.png");
		if (image[4] == null) image[4] = view.loadImage("mario4.png");
	}

	void drawYourself(Graphics g){
		g.drawImage(image[frame], x - model.scrollPos(), y, null);
	}

	boolean isItAtube() {
		return false;
	}

	boolean isItAgoomba(){
		return false;
	}

	boolean isItAmario(){
		return true;
	}

	boolean isItAfireball(){
		return false;
	}
	
	void update(){
		yVel += 1.2;
		y += yVel;
		if(y > 405){
			yVel = 0.0;
			offGroundCount = 0;
			y = 405; // snap back to the ground
		}
		frame++;
		if (frame > 4){
			frame = 0;
		}
		offGroundCount++;
	}
	
	Json makeJson()
    {
        Json ob = Json.newObject();
        ob.add("x", x);
        ob.add("y", y);
        return ob;
    }

    Mario(Json ob)
    {
        x = (int)ob.getLong("x");
        y = (int)ob.getLong("y");
    }
}