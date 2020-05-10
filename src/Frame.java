import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame {

	// Only method in the project that is called immediately when the program is started
	public static void main(String[] args) {
		
		// Call the method to start the Window
		initializeVisuals();
		
		// Generate a math question
		MathQuestions.generateQuestion();
		
	}

	// Method for initializing the Window, and starting the game loop
	private static void initializeVisuals() {
		
		// Creates a new frame and panel
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		
		frame.setSize(Bounds.FRAME_WIDTH, Bounds.FRAME_HEIGHT + Bounds.CHOICES_BAR);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(Bounds.FRAME_TITLE);
		frame.add(panel);
		panel.setVisible(true);
		
		// Creates an object of the drawing class
		DrawGraphics dg = new DrawGraphics();
		
		// Adds the object to the frame
		frame.add(dg);
		frame.addKeyListener(dg);
		
		// Starts the essential game loop
		dg.gameLoop();
		
	}

}
