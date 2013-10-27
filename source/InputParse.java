import java.util.Scanner;


public class InputParse {
	private char c;
	private String input;
	private String[] inputDigits;

	public InputParse()
	{
		char firstChar = getInput();
		mySwitch(firstChar);
		
	}
	public char getInput()
	{
		@SuppressWarnings("resource")
		Scanner myScan = new Scanner(System.in);
		System.out.println("What would you like to do?");
		input = myScan.nextLine();
		inputDigits = input.split("\\s+");
		try
		{
			c = input.charAt(0);									// if input is not blank
		}
		catch (Exception e)
		{
			c = 'x';												// blank input default character
		}															// evokes default 'invalid input' response from mySwitch
		return c;
	}
	public void mySwitch(char c)
	{
		switch(c)
		{
		case 'b':	System.out.println("You have selected 'B'!"); 	// todo: buy shares
					System.out.println(inputDigits[1] + " shares at $" + inputDigits[2] + " per share.");
					char c1 = getInput();							// get next input after doing whatever
					mySwitch(c1);				
					
					
		case 's':	System.out.println("You have selected 'S'!"); 	// todo: sell shares
					char c2 = getInput();
					mySwitch(c2);
	
		case 'c':	System.out.println("You have selected 'C'!") ; 	// todo: print capital gain
					char c3 = getInput();
					mySwitch(c3);
		
		case 'q':	System.exit(0); 								// quit
						
		default: 	System.out.println("Invalid input. Please try again.");
					char c4 = getInput();
					mySwitch(c4);
		}
	}
	public static void main(String[] args)
	{
		new InputParse();
	}
}
