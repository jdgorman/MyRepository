import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
//import java.io.IOException;
import java.io.File;
//import javax.swing.JButton;
import java.awt.Color;

class View extends JPanel
{
	Model model;

	static BufferedImage loadImage(String filename){
		BufferedImage image = null;
		try{
			image = ImageIO.read(new File(filename));
		} 
		catch(Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}
		return image;
	}

	View(Controller c, Model m)
	{
		model = m;
	}

	public void paintComponent(Graphics g)
	{
		g.setColor(new Color(128, 255, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		for (int i = 0; i < model.sprites.size(); i++) {
			Sprite s = model.sprites.get(i);
			s.drawYourself(g);
		}
		g.setColor(Color.gray);
		g.drawLine(0, 500, 2000, 500);
	}
}
