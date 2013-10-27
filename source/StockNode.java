
public class StockNode {

	private int stockNum;
	private int stockPrice;
	
	public StockNode(int s,int p)
	{
		stockNum = s; 				// number of shares held in this node
		stockPrice = p;				// price paid per share
	}
	public int getShares()
	{
		return stockNum;
	}
	public int getPrice()
	{
		return stockPrice;
	}
	public void setShares(int shareNum)
	{
		stockNum = shareNum;
	}
	public void setPrice(int newPrice)
	{
		stockPrice = newPrice;
	}
}
