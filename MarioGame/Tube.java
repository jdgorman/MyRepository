import java.awt.image.BufferedImage;
import java.awt.Graphics;

class Tube extends Sprite{
	View view;
	Model model;
	static BufferedImage image;
	
	public Tube(int posX, int posY, Model m){
		x = posX;
		y = posY;
		w = 55;
		h = 400;
		if (image == null) {
			image = view.loadImage("tube.png");
		}
		model = m;
	}

	void drawYourself(Graphics g){
		g.drawImage(image, x - model.scrollPos(), y, null);
	}
	
	boolean isItAtube() {
		return true;
	}
	
	boolean isItAgoomba(){
		return false;
	}

	boolean isItAmario(){
		return false;
	}

	boolean isItAfireball(){
		return false;
	}

	void update(){
		
	}

	Json makeJson()
    {
        Json ob = Json.newObject();
        ob.add("x", x);
        ob.add("y", y);
        return ob;
    }

    Tube(Json ob, Model m)
    {
        x = (int)ob.getLong("x");
        y = (int)ob.getLong("y");
        model = m;
    }
}