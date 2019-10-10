import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class Controller implements ActionListener, MouseListener, KeyListener
{
	View view;
	Model model;

	boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;
	boolean keySpace;
	int queuedSpaces;
	boolean keyCtrl;
	int queuedCtrl;

	Controller(Model m)
	{
		model = m;
	}

	void meetView(View v)
	{
		view = v;
	}

	public void actionPerformed(ActionEvent e)
	{
		
	}

	public void mouseReleased(MouseEvent e) {    }
	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }
	public void mouseClicked(MouseEvent e) {    }

	public void mousePressed(MouseEvent e)
	{
		//model.mousePressed(e.getX() + model.scrollPos(), e.getY());
	}

	public void keyPressed(KeyEvent e)
	{	
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = true; break;
			case KeyEvent.VK_LEFT: keyLeft = true; break;
			case KeyEvent.VK_UP: keyUp = true; break;
			case KeyEvent.VK_DOWN: keyDown = true; break;
			case KeyEvent.VK_SPACE: keySpace = true; queuedSpaces++; break;
			case KeyEvent.VK_CONTROL: keyCtrl = true; break;

		}
	}

	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = false; break;
			case KeyEvent.VK_LEFT: keyLeft = false; break;
			case KeyEvent.VK_UP: keyUp = false; break;
			case KeyEvent.VK_DOWN: keyDown = false; break;
			case KeyEvent.VK_SPACE: keySpace = false; break;
			case KeyEvent.VK_CONTROL: keyCtrl = false; break;
		}
	}

	public void keyTyped(KeyEvent e)
	{
		
	}

	void update()
	{
		model.rememberState();
		if(keyRight) model.mario.x += 10;
		if(keyLeft) model.mario.x -= 10;
		if((keySpace || queuedSpaces > 0) && model.mario.offGroundCount < 3) 
			model.mario.jump();
			queuedSpaces = 0;
		if(keyCtrl || queuedCtrl > 0){
			model.addFireballs();
			queuedCtrl = 0;
		}
	}
}