//Sang Park CS313-12

public class Stack {
	
	private Node top;
	
	Stack(){
		top = null;
	}
	
	public boolean isEmpty(){
		return (top == null);
	}
	
	public Object peek(){
		if(isEmpty()){
			throw new NullPointerException("There is no node to peek.");
		}
		return top.data;
	}
	
	public void push(Object d){
		top = new Node(d,top);
	}
	
	public Object pop(){
		if(isEmpty()){
			throw new NullPointerException("There is no node to pop.");
		}
		Object d = top.data;
		top = top.next;
		return d;
	}
}
