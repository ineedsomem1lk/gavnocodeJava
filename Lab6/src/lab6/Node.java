package lab6;


public class Node<T> 
{
	public T Data;
	public int Id;
	public int score;

	public Node(T Data,int id)
	{
		this.Data = Data; 
		this.Id = id;
	}
	
	public T getData() { return Data; }
	public int getId(){ return Id; }
	
}
