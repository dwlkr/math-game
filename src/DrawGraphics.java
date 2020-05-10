import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DrawGraphics extends JPanel implements KeyListener{

	// TODO Fix so streak does not reset on UNPAUSE
	
	
	// Keypress variables
	boolean wIsPressed = false;
	boolean aIsPressed = false;
	boolean sIsPressed = false;
	boolean dIsPressed = false;
	
	// Current question
	private static String questionString = "";
	
	// Variables to hold which Rectangle the player is intersecting
	static boolean redS = false;
	static boolean purpleS = false;
	static boolean blueS = false;
	static boolean orangeS = false;
	
	// Questions answered correctly in a row
	static int streak = 0;
	
	// Standard amount of seconds until new question
	static int secondsLeft = 10;
	
	// Variables for the different choices
	static double rightAnswer = 0;
	static double fakeAnswer1 = 0;
	static double fakeAnswer2 = 0;
	static double fakeAnswer3 = 0;

	// Timer for game loop
	static TimerTask tt;
	
	// Interval for Timer
	static Timer timerInterval;
	
	// Rectangle that the user controls
	private static Rectangle player = new Rectangle(900 / 2 - Bounds.PLAYER_SIZE / 2, 900 / 2 - Bounds.PLAYER_SIZE / 2  + 25, Bounds.PLAYER_SIZE, Bounds.PLAYER_SIZE);
	
	// Boolean to check if the game is paused or not
	private static boolean pause = false;
	
	// To draw a black background
	private Rectangle background = new Rectangle(0, 0, Bounds.FRAME_WIDTH, Bounds.FRAME_HEIGHT);
	
	// Rectangles for each choice to intersect
	private static Rectangle red = new Rectangle(Bounds.FRAME_WIDTH / 2 - Bounds.SQUARE_SIZE / 2, Bounds.FRAME_HEIGHT - Bounds.SQUARE_SIZE / 2 - Bounds.SQUARE_SIZE / 2, Bounds.SQUARE_SIZE, Bounds.SQUARE_SIZE);
	private static Rectangle purple = new Rectangle(0, Bounds.FRAME_HEIGHT / 2 - Bounds.SQUARE_SIZE / 2, Bounds.SQUARE_SIZE, Bounds.SQUARE_SIZE);
	private static Rectangle blue = new Rectangle(	Bounds.FRAME_WIDTH - Bounds.SQUARE_SIZE, Bounds.FRAME_HEIGHT / 2 - Bounds.SQUARE_SIZE / 2, Bounds.SQUARE_SIZE, Bounds.SQUARE_SIZE);
	private static Rectangle orange = new Rectangle(Bounds.FRAME_WIDTH / 2 - Bounds.SQUARE_SIZE / 2, 0, Bounds.SQUARE_SIZE, Bounds.SQUARE_SIZE);
	
	// Rectangles for each choice to read from
	private Rectangle miniRed = new Rectangle(0, Bounds.FRAME_HEIGHT, Bounds.MINI_SIZE_X, Bounds.MINI_SIZE_Y);
	private Rectangle miniPurple = new Rectangle(0 + Bounds.MINI_SIZE_X, Bounds.FRAME_HEIGHT, Bounds.MINI_SIZE_X, Bounds.MINI_SIZE_Y);
	private Rectangle miniBlue = new Rectangle(0 + Bounds.MINI_SIZE_X * 2, Bounds.FRAME_HEIGHT, Bounds.MINI_SIZE_X, Bounds.MINI_SIZE_Y);
	private Rectangle miniOrange = new Rectangle(0  + Bounds.MINI_SIZE_X * 3, Bounds.FRAME_HEIGHT, Bounds.MINI_SIZE_X, Bounds.MINI_SIZE_Y);
	
	// Rectangles to show to pause icon
	private Rectangle pause1 = new Rectangle(Bounds.FRAME_WIDTH - Bounds.PAUSE_SIZE_X * 2 - Bounds.PAUSE_SPACE / 2, 0 + 10, Bounds.PAUSE_SIZE_X, Bounds.PAUSE_SIZE_Y);
	private Rectangle pause2 = new Rectangle(Bounds.FRAME_WIDTH - Bounds.PAUSE_SIZE_X * 1 - 10, 0 + 10, Bounds.PAUSE_SIZE_X, Bounds.PAUSE_SIZE_Y);

	// Fonts used in the project
	Font font = new Font("Courier", Font.BOLD,19);
	Font fontBig = new Font("Courier", Font.BOLD,25);
	
	// Main game loop to handle important methods
	public void gameLoop() {
		Timer t = new Timer();  
		TimerTask ttt = new TimerTask() {  
			@Override  
			public void run() {   
				if(secondsLeft == 0) {
					reset();
				}
				repaint();
				checkMovement();
				boundsChecker();
			} 
	};  
	t.scheduleAtFixedRate(ttt,0,17); // Interval for game updates, affects frames per second
}    
	
	// Method to reset board
	public static void reset() {
	    tt.cancel();
	    secondsLeft = 10;
		rightPositionCheck();
	}
	
	// Method to update time, runs once a second
	public static void displayTimeLeft() {
		timerInterval = new Timer();  
		tt = new TimerTask() {  
			@Override  
			public void run() {   
				if(pause == false) {
				secondsLeft--;
				}
			} 
	};  
	timerInterval.scheduleAtFixedRate(tt,0,1000); // Do not change this interval
}  

	// Checks if player rectangle is within given bounds
	public void boundsChecker() {
		
		if(player.x > Bounds.FRAME_WIDTH - 25) {
			player.x = Bounds.FRAME_WIDTH - 25;
		}
		if(player.x < 0) {
			player.x = 0;
		}
		if(player.y < 0) {
			player.y = 0;
		}
		if(player.y > Bounds.FRAME_HEIGHT - Bounds.PLAYER_SIZE / 2 - 10) {
			player.y = Bounds.FRAME_HEIGHT - Bounds.PLAYER_SIZE / 2 - 10;
		}
		
	}
	
	// Checks if player is on the right alternative
	public static void rightPositionCheck() {

		if(redS && player.intersects(red)) {
			MathQuestions.generateQuestion();
			player.y = Bounds.FRAME_HEIGHT / 2 - Bounds.PLAYER_SIZE / 2 + 25;
			player.x = Bounds.FRAME_WIDTH / 2 - Bounds.PLAYER_SIZE / 2;
			streak++;
		}else if(purpleS && player.intersects(purple)) {
			MathQuestions.generateQuestion();
			player.y = Bounds.FRAME_HEIGHT / 2 - Bounds.PLAYER_SIZE / 2 + 25;
			player.x = Bounds.FRAME_WIDTH / 2 - Bounds.PLAYER_SIZE / 2;
			streak++;
		}else if(blueS && player.intersects(blue)) {
			MathQuestions.generateQuestion();
			player.y = Bounds.FRAME_HEIGHT / 2 - Bounds.PLAYER_SIZE / 2 + 25;
			player.x = Bounds.FRAME_WIDTH / 2 - Bounds.PLAYER_SIZE / 2;
			streak++;
		}else if(orangeS && player.intersects(orange)) {
			MathQuestions.generateQuestion();
			player.y = Bounds.FRAME_HEIGHT / 2 - Bounds.PLAYER_SIZE / 2 + 25;
			player.x = Bounds.FRAME_WIDTH / 2 - Bounds.PLAYER_SIZE / 2;
			streak++;
		}else {
			streak = 0;
			MathQuestions.generateQuestion();
			player.y = Bounds.FRAME_HEIGHT / 2 - Bounds.PLAYER_SIZE / 2 + 25;
			player.x = Bounds.FRAME_WIDTH / 2 - Bounds.PLAYER_SIZE / 2;
		}		
	}

	// Handles movement variables
	public void checkMovement() {
		
		if(wIsPressed) {
			player.y = player.y - 10;
		}
		if(aIsPressed) {
			player.x = player.x - 10;
		}
		if(sIsPressed) {
			player.y = player.y + 10;
		}
		if(dIsPressed) {
			player.x = player.x + 10;
		}
		
	}
	
	// Calculates answers and updates variables
	public static void displayQuest(double fN, double sN, int operation) {
		
		switch(operation) {
			case 0: rightAnswer = fN + sN; questionString = fN + " + " + sN + " = ?"; break;
			case 1: rightAnswer = fN - sN; questionString = fN + " - " + sN + " = ?"; break;
			case 2: rightAnswer = fN * sN; questionString = fN + " * " + sN + " = ?"; break;
			case 3: rightAnswer = fN / sN; questionString = fN + " / " + sN + " = ?"; break;
		}			
		
		System.out.println(rightAnswer); // Prints the correct answer for debugging purposes
		
		Random one = new Random();
		Random two = new Random();
		Random three = new Random();
		int oneResult;
		int twoResult;
		int threeResult;
		
		if(rightAnswer % 1 == 0) {

			oneResult = one.nextInt(9);
			oneResult++;
			
			twoResult = two.nextInt(11);
			twoResult++;
			
			threeResult = three.nextInt(6);
			threeResult++;
			
			fakeAnswer1 = rightAnswer + -4;
			fakeAnswer2 = rightAnswer + 5;
			fakeAnswer3 = rightAnswer + 2;
			
		}else {
			
			oneResult = one.nextInt(9);
			oneResult++;
			
			twoResult = two.nextInt(11);
			twoResult++;
			
			threeResult = three.nextInt(6);
			threeResult++;
			
			fakeAnswer1 = rightAnswer * oneResult + 0.2;
			fakeAnswer2 = rightAnswer * twoResult + 0.6;
			fakeAnswer3 = rightAnswer * threeResult + 0.4;
			
		}
		
		Random rand = new Random();
		int randResult = rand.nextInt(4);
		
		switch(randResult) {
			case 0: redS = true; purpleS = false; orangeS = false; blueS = false; break;
			case 1: redS = false; purpleS = true; orangeS = false; blueS = false; break;
			case 2: redS = false; purpleS = false; orangeS = true; blueS = false; break;
			case 3: redS = false; purpleS = false; orangeS = false; blueS = true; break;
		}

	}
	
	// Method to draw all components
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g); // Resets board

		Graphics2D g2 = (Graphics2D)g;

		g2.setColor(Color.BLACK);
		g2.fill(background);
		
		g2.setColor(Color.RED);
		g2.fill(red);
		g2.fill(miniRed);
		
		g2.setColor(Color.BLUE);
		g2.fill(blue);
		g2.fill(miniBlue);
		
		g2.setColor(Color.MAGENTA);
		g2.fill(purple);
		g2.fill(miniPurple);
		
		g2.setColor(Color.ORANGE);
		g2.fill(orange);
		g2.fill(miniOrange);
				
		g2.setColor(Color.WHITE);
		g2.drawLine(0, Bounds.FRAME_HEIGHT, Bounds.FRAME_WIDTH, Bounds.FRAME_HEIGHT);
		
		if(pause == false) {
			g2.fill(player);
		}
		
		if(pause == true) {
			g2.fill(pause1);
			g2.fill(pause2);
		}
		
		if(pause == false) {
			
		g2.setFont(fontBig);
		g2.setColor(Color.GREEN);
		g2.drawString(questionString, Bounds.FRAME_WIDTH / 2 - Bounds.SQUARE_SIZE / 2 + 10, Bounds.FRAME_HEIGHT / 2 + 10);
		g2.setColor(Color.WHITE);
		g2.drawString("Seconds left: " + String.valueOf(secondsLeft), 0, 20);
		g2.drawString("Answer streak: : " + String.valueOf(streak), 0, 40);
		g2.setFont(font);
		
		if(redS) {
			g2.drawString(String.valueOf(rightAnswer), miniRed.x, miniRed.y + Bounds.MINI_SIZE_Y / 2);
			g2.drawString(String.valueOf(fakeAnswer1), miniPurple.x, miniPurple.y + Bounds.MINI_SIZE_Y / 2);
			g2.drawString(String.valueOf(fakeAnswer2), miniBlue.x, miniBlue.y + Bounds.MINI_SIZE_Y / 2);
			g2.drawString(String.valueOf(fakeAnswer3), miniOrange.x, miniOrange.y + Bounds.MINI_SIZE_Y / 2);
		}
		if(purpleS) {
			g2.drawString(String.valueOf(fakeAnswer3), miniRed.x, miniRed.y + Bounds.MINI_SIZE_Y / 2);
			g2.drawString(String.valueOf(rightAnswer), miniPurple.x, miniPurple.y + Bounds.MINI_SIZE_Y / 2);
			g2.drawString(String.valueOf(fakeAnswer1), miniBlue.x, miniBlue.y + Bounds.MINI_SIZE_Y / 2);
			g2.drawString(String.valueOf(fakeAnswer2), miniOrange.x, miniOrange.y + Bounds.MINI_SIZE_Y / 2);
		}
		if(blueS) {
			g2.drawString(String.valueOf(fakeAnswer2), miniRed.x, miniRed.y + Bounds.MINI_SIZE_Y / 2);
			g2.drawString(String.valueOf(fakeAnswer3), miniPurple.x, miniPurple.y + Bounds.MINI_SIZE_Y / 2);
			g2.drawString(String.valueOf(rightAnswer), miniBlue.x, miniBlue.y + Bounds.MINI_SIZE_Y / 2);
			g2.drawString(String.valueOf(fakeAnswer1), miniOrange.x, miniOrange.y + Bounds.MINI_SIZE_Y / 2);
		}
		if(orangeS) {
			g2.drawString(String.valueOf(fakeAnswer1), miniRed.x, miniRed.y + Bounds.MINI_SIZE_Y / 2);
			g2.drawString(String.valueOf(fakeAnswer2), miniPurple.x, miniPurple.y + Bounds.MINI_SIZE_Y / 2);
			g2.drawString(String.valueOf(fakeAnswer3), miniBlue.x, miniBlue.y + Bounds.MINI_SIZE_Y / 2);
			g2.drawString(String.valueOf(rightAnswer), miniOrange.x, miniOrange.y + Bounds.MINI_SIZE_Y / 2);
		}
		}

	}

	// Handles keypresses
	@Override
	public void keyPressed(KeyEvent e) {
		
		if(pause == false) { // If the game is not paused
						
			switch(e.getKeyCode()) {
				case KeyEvent.VK_W: wIsPressed = true; break;
				case KeyEvent.VK_A: aIsPressed = true; break;
				case KeyEvent.VK_S: sIsPressed = true; break;
				case KeyEvent.VK_D: dIsPressed = true; break;
			}
		}
	}

	// Handles keyreleases
	@Override
	public void keyReleased(KeyEvent e) {
		
		if(pause == false) {
			switch(e.getKeyCode()) {
				case KeyEvent.VK_W: wIsPressed = false; break;
				case KeyEvent.VK_A: aIsPressed = false; break;
				case KeyEvent.VK_S: sIsPressed = false; break;
				case KeyEvent.VK_D: dIsPressed = false; break;
			}	
		}
		
		if(e.getKeyCode() == KeyEvent.VK_P && pause == false) { // PROBLEMS : DOUBLE TICKING AND BEYOND !ALERT
			pause = true;

		}else if (e.getKeyCode() == KeyEvent.VK_P && pause == true){
			pause = false;
			reset();
		}
		
	}

	// Not used
	@Override
	public void keyTyped(KeyEvent e) {
		return;
	}

	
}
