import javax.swing.JFrame;
import java.awt.Toolkit;

public class Game extends JFrame
{
	Model model;
	Controller controller;
	View view;

	public Game()
	{
		// Initialize member variables
		model = new Model();
		controller = new Controller(model);
		view = new View(controller, model);

		// Hook up the input devices
		this.addMouseListener(controller);
		this.addKeyListener(controller);
		controller.meetView(view);
		
		// Configure the window
		this.setFocusable(true);
		this.setTitle("Super Mario!");
		this.setSize(1000, 700);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void run()
	{
		while(true)
		{
			controller.update();
			model.update();
			view.repaint(); // Indirectly calls View.paintComponent
			Toolkit.getDefaultToolkit().sync(); // Updates screen
			// Go to sleep for 50 miliseconds
			try
			{
				Thread.sleep(50);
			} catch(Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}

	public static void main(String[] args)
	{
		Game g = new Game();
		g.run();
	}

}
