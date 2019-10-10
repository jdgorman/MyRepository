import java.awt.image.BufferedImage;
import java.awt.Graphics;

class Goomba extends Sprite{
	View view;
	Model model;
	static BufferedImage goombaImage;
	static BufferedImage fireImage;
	boolean movingRight;
	boolean goombaHit = false;
	int burnAmount;
	
	public Goomba(int posX, int posY, Model m){
		x = posX;
		y = posY;
		w = 99;
		h = 118;
		movingRight = true;
		if (goombaImage == null) {
			goombaImage = view.loadImage("gumba.png");
		}
		model = m;
	}

	void drawYourself(Graphics g){
		if (goombaHit == false) {
			g.drawImage(goombaImage, x - model.scrollPos(), y, null);
		}
		if (goombaHit == true) {
			fireImage = view.loadImage("gumba_fire.png");
			g.drawImage(fireImage, x - model.scrollPos(), y, null);
		}
	}
	
	boolean isItAtube() {
		return false;
	}

	boolean isItAgoomba(){
		return true;
	}

	boolean isItAmario(){
		return false;
	}

	boolean isItAfireball(){
		return false;
	}
	
	void update(){
		if (movingRight) {
			x += 3;
		}else{
			x -= 3;
		}
		if (x > 800) {
			movingRight = false;
		}
		if (x < 450) {
			movingRight = true;
		}
		for (int i = 0; i < model.sprites.size(); i++) {
			Sprite s = model.sprites.get(i);
			if (s.isItAfireball()) 
			{
				if (Sprite.doesCollide(x, y, w, h, s.x, s.y, s.w, s.h)) 
				{
					goombaHit = true;
					burnAmount++;
					if (burnAmount > 7) {
						model.sprites.remove(this);
					}
				}
			}
		}
	}

	Json makeJson()
    {
        Json ob = Json.newObject();
        ob.add("x", x);
        ob.add("y", y);
        return ob;
    }

    Goomba(Json ob, Model m)
    {
        x = (int)ob.getLong("x");
        y = (int)ob.getLong("y");
        model = m;
    }
}