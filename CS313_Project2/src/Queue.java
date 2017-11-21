//Sang Park CS313-12

public class Queue {
	
	private Node rear;

	Queue(){
		rear = null;
	}
	
	public boolean isEmpty(){
		return (rear == null);
	}
	
	public void enQ(Object d){
		if(isEmpty()){
			rear = new Node(d);
			rear.next = rear;
		}
		else{
			rear.next = new Node(d,rear.next);
			rear = rear.next;
		}
	}
	
	public Object deQ(){
		if(isEmpty()){
			throw new NullPointerException("There is no node to deQ.");
		}
		Object d = (rear.next).data;
		if(rear.next == rear){
			rear = null;
		}
		else{
			rear.next = rear.next.next;
		}
		return d;
	}
	
	public Object peek(){
		if(isEmpty()){
			throw new NullPointerException("There is no node to peek.");
		}
		return (rear.next).data;
	}
}
