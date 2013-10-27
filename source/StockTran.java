import java.util.Scanner;

public class StockTran {
	private char c;
	private String input;
	private String[] inputDigits;
	private StockQueue myQ;
	private int frontShares, frontPrice, shareDiff, transPrice = 0, profit, remShares, capitalGain = 0;
	private int sharesToSell, sellPrice;

	public StockTran()
	{
		myQ = new StockQueue();
		char firstChar = getInput();
		mySwitch(firstChar);
		
	}
	public char getInput()
	{
		@SuppressWarnings("resource")
		Scanner myScan = new Scanner(System.in);
		System.out.println("What would you like to do?");
		input = myScan.nextLine().trim();	 						// trim removes leading and trailing whitespace
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
		case 'b':	try
					{
						int buyShares = Integer.valueOf(inputDigits[1]); // first number from the input string
						int buyPrice = Integer.valueOf(inputDigits[2]); // second number from the input string	
						try 
						{
							myQ.enqueue(new StockNode(buyShares, buyPrice));
							System.out.println("Bought " +buyShares + " shares at $" + buyPrice + " per share.");
						} 
						catch (StockQueue.FullQueueException e) 
						{
							System.out.println("Cannot purchase, capacity reached!");
							System.out.println("Stock queue currently holds: " + myQ.size() + " transactions.");
						}
						//System.out.println("Transactions stored so far: " + myQ.size());
					}
					finally
					{
						char c1 = getInput();	// get next input after doing whatever
						mySwitch(c1);				
					}
					
					
		case 's':
					try
					{
						sharesToSell = Integer.valueOf(inputDigits[1]); // first number from the input string
						sellPrice = Integer.valueOf(inputDigits[2]); // second number from the input string
						if(myQ.size() == 0)
						{
							System.out.println("Can't sell from an empty queue!");
						}
						sellShares(sharesToSell, sellPrice);
					}
					finally
					{
						char c2 = getInput();
						mySwitch(c2);
					}
	
		case 'c':	System.out.println("Capital gain: " + capitalGain);
					char c3 = getInput();
					mySwitch(c3);
		
		case 'q':	System.exit(0); // quit
		
		case 'p':	// print size, head location, and tail location for debug purposes
					System.out.println(myQ.size() + ": size of array");
					System.out.println(myQ.getHead() + ": index of head");
					System.out.println(myQ.getTail() + ": index of tail");
					myQ.printContents();
					char c4 = getInput();
					mySwitch(c4);
						
		default: 	System.out.println("Invalid input. Please try again.");
					char c5 = getInput();
					mySwitch(c5);
		}
	}
	public void sellShares(int shares, int price)
	{
		try
		{
			frontShares = myQ.front().getShares(); // number of shares in first index
			frontPrice = myQ.front().getPrice(); // price per share in first index
			shareDiff = frontShares - shares; // # shares in first cell - # shares to sell
			
			if(shareDiff > 0) // if there are more shares in the front than being sold
			{
				myQ.front().setShares(shareDiff);
				transPrice = shares * sellPrice; // net price of selling shares
				profit = transPrice - (shares * frontPrice); // profit after subtracting price paid for shares
				capitalGain += profit;
				System.out.println("Sold " + shares + " shares for $" + transPrice);
			}
			else if(shareDiff == 0)
			{
				transPrice = shares * sellPrice;
				profit = transPrice - (shares * frontPrice);
				capitalGain += profit;
				myQ.dequeue(); // amount to sell = amount in first node, so dequeue it
				System.out.println("Sold " + shares + " for $" + transPrice);

			}
			else // shareDiff < 0
			{
				myQ.dequeue();
				remShares = shareDiff * -1; // remaining shares after clearing first index
				transPrice = ((shares - remShares) * sellPrice); // transaction so far
				System.out.println("Sold " + (shares - remShares) + " shares for $" + transPrice);
				System.out.println("Remaining shares: " + remShares);
				profit = transPrice - ((shares-remShares) * frontPrice);
				capitalGain += profit;
				if(remShares > 0)
				{
					sellShares(remShares, sellPrice);
					if(myQ.isEmpty())
					{
						System.out.println("You don't have enough shares to sell that many!");
					}
				}
			}

				
		}
		catch (Exception e)
		{
			//e.printStackTrace(); // throws if queue is empty
		}
	}
	public static void main(String[] args)
	{
		new StockTran();
	}
}
