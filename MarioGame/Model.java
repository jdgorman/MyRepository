import java.util.ArrayList;

class Model
{
	Mario mario;
	Fireball fireball;
	ArrayList<Sprite> sprites;

	Model()
	{
		sprites = new ArrayList<Sprite>();
		mario = new Mario(this);
		addSprites();
	}

	void addSprites(){
		Sprite m = mario;
		sprites.add(m);
		Sprite t1 = new Tube(400,350,this);
		sprites.add(t1);
		Sprite t2 = new Tube(900,350,this);
		sprites.add(t2);
		Sprite g = new Goomba(550,382,this);
		sprites.add(g);
	}

	void addFireballs(){
		Sprite f = new Fireball(this);
		sprites.add(f);
	}

	int scrollPos(){
		return mario.x - 200;
	}
	
	void rememberState(){
		mario.rememberState();
	}

	public void update()
	{
		for (int i = 0; i < sprites.size(); i++) {
			Sprite s = sprites.get(i);
			s.update();
			if (s.isItAtube()) {
				if (Sprite.doesCollide(mario.x, mario.y, mario.w, 
						mario.h, s.x, s.y, s.w, s.h)) {
						mario.getOutOfThis(s);
					}
			}
			if (s.isItAgoomba()){
				if (Sprite.doesCollide(mario.x, mario.y, mario.w, 
						mario.h, s.x, s.y, s.w, s.h)) {
						mario.getOutOfThis(s);
					}
			}
		}
	}
}