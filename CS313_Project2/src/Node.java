//Sang Park CS313-12

public class Node {
	
	public Object data;
	public Node next;
	
	public Node(Object d){
		data = d;
	}
	
	public Node(Object d, Node n){
		data = d;
		next = n;
	}
	
}
