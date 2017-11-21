//Sang Park CS313-12

public class Data extends Object{
	
	private int quantity;
	private float price;
	
	Data(int q, float p){
		quantity = q;
		price = p;
	}
	
	public int getQ(){
		return this.quantity;
	}
	
	public float getP(){
		return this.price;
	}
	
	public void setQ(int q){
		this.quantity = q;
	}
	
	public void setP(float p){
		this.price = p;
	}
}
