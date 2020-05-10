import java.util.Random;

public class MathQuestions {
	
	// Random number for first integer in the equation
	static Random num1 = new Random();
	// Random number for second integer in the equation
	static Random num2 = new Random();
	
	// Random operator for the equation
	static Random operation = new Random();
	
	// Decimal variables for both numbers in the equation
	static double secondNum;
	static double firstNum;
	
	// Integer variable for the operation in the equation
	static int operator;
		
	// Generates the mathematical question
	public static void generateQuestion() {

		operator = operation.nextInt(4);
		
		firstNum = num1.nextInt(30);
		secondNum = num2.nextInt(30);
						
		DrawGraphics.displayQuest(firstNum, secondNum, operator);
		
		DrawGraphics.displayTimeLeft();
	}
	
}
