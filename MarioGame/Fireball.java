import java.awt.image.BufferedImage;
import java.awt.Graphics;

class Fireball extends Sprite{
	View view;
	Model model;
	static BufferedImage image;

	public Fireball(Model m) {
		model = m;
		x = model.mario.x;
		y = model.mario.y + 47;
		w = 47;
		h = 47;
		yVel = -12.0;
		if (image == null) {
			image = view.loadImage("fireball.png");
		}
	}

	void drawYourself(Graphics g){
		g.drawImage(image, x - model.scrollPos(), y, null);
	}

	void update(){
		yVel += 1.2;
		x += 15;
		y += yVel;
		if(y > 453){
			yVel = -10;
			offGroundCount = 0;
			y = 453; // snap back to the ground
		}
		offGroundCount++;
	}

	boolean isItAtube() {
		return false;
	}

	boolean isItAgoomba(){
		return false;
	}

	boolean isItAmario(){
		return false;
	}

	boolean isItAfireball(){
		return true;
	}

	Json makeJson()
    {
        Json ob = Json.newObject();
        ob.add("x", x);
        ob.add("y", y);
        return ob;
    }

    Fireball(Json ob, Model m)
    {
        x = (int)ob.getLong("x");
        y = (int)ob.getLong("y");
        model = m;
    }
}