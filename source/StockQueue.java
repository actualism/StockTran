
public class StockQueue {
	
	private int head; // index of head
	private int tail; // index of tail
	private final static int CAPACITY = 32; // max capacity
	private StockNode[] circleQ; // circular queue

	public StockQueue(int cap)
	{
		circleQ = new StockNode[cap];
	}
	public StockQueue()	// default constructor
	{
		this(CAPACITY); // makes a new queue with size = CAPACITY = 32
		head = tail = 0; // initial indexes are both 0 => empty queue
	}
	public boolean isEmpty()
	{
		return (head == tail); // queue is empty when head and tail indices are the same
	}
	public int size()
	{
		return (CAPACITY - head + tail) % CAPACITY; // ex: (32 - 0 + 2) mod 32 = 2
	}
	public StockNode front() throws EmptyQueueException
	{
		if(this.isEmpty())
		{
			throw new EmptyQueueException("Cannot call front() on empty queue");
		}
		return circleQ[head];
	}
	public StockNode rear()
	{
		return circleQ[tail];
	}
	public StockNode dequeue() throws EmptyQueueException
	{
		if(this.isEmpty())
		{
			throw new EmptyQueueException("Cannot dequeue from empty queue");
		}
		StockNode tempNode = circleQ[head]; // temporarily store head node
		circleQ[head] = null;
		head = (head + 1) % CAPACITY;
		return tempNode;
	}
	public void enqueue(StockNode aNode) throws FullQueueException
	{
		if(this.size() == CAPACITY - 1)
		{
			throw new FullQueueException("Cannot enqueue: capacity reached");
		}
		circleQ[tail] = aNode;
		tail = (tail + 1) % CAPACITY;
	}
	public void printContents()
	{
		for(int i = head; i < tail; i++)
		{
			StockNode currentNode = circleQ[i];
			System.out.println("Index " + i + " contains " + currentNode.getShares() + " shares at $"
					+ currentNode.getPrice() + " per share.");
		}
	}
	public int getHead()
	{
		return head;
	}
	public int getTail()
	{
		return tail;
	}
	
	
	@SuppressWarnings("serial")
	public class EmptyQueueException extends Exception {
		public EmptyQueueException(String message){
			super(message);
		}
	}
	@SuppressWarnings("serial")
	public class FullQueueException extends Exception {
		public FullQueueException(String message){
			super(message);
		}
	}
}
