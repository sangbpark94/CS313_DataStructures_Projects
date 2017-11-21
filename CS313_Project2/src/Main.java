//Sang Park CS313-12

import java.util.StringTokenizer;
import javax.swing.*;

public class Main {
	
	public static void main(String args[]){
		
		//Initialize variable to TextFileInput
		String filename = "transactions.txt";
		TextFileInput inFile = new TextFileInput(filename);
		String line = inFile.readLine();
		
		//Initialize Stack and Queue
		Stack beanCaseStack = new Stack();
		Queue backOrderQueue = new Queue();
		
		//Initialize variable used to keep track of total beans in the beanCaseStack
		int totalBeans = 0;
		
		//While there is a line to read...
		while(line != null){
			
			//Initialize Tokenizer
			StringTokenizer myTokens = new StringTokenizer(line," ");
			String currentToken = myTokens.nextToken();
			
			//If R is read...
			if(currentToken.equals("R")){
				
				//Push beans onto beanCase Stack and print
				int q = Integer.parseInt(myTokens.nextToken());
				float p = Float.parseFloat(myTokens.nextToken());
				Data d = new Data(q,p);
				totalBeans += q;

				System.out.println("Shipment received:");
				System.out.println(q + " cases of cans @ " + p + "\n");
	
				beanCaseStack.push(d);
				
				//Check if there are enough beans for the backOrders
				if(!backOrderQueue.isEmpty() && (totalBeans >= ((Data)backOrderQueue.peek()).getQ()))
					
					//As long as there are enough beans and backOrders
					while(!backOrderQueue.isEmpty() && (totalBeans >= ((Data)backOrderQueue.peek()).getQ())){
						
						//Prints
						System.out.println("Store order for " + ((Data)backOrderQueue.peek()).getQ() + " cases.\n");
						
						//Initialize variables
						float totalCost = 0;
						float bOrderPrice = ((Data)backOrderQueue.peek()).getP();
						int bOrderQuantity = ((Data)backOrderQueue.peek()).getQ();
						float markUpPrice = bOrderPrice * (float) 1.3;
						float markUpTotal = markUpPrice * ((float) bOrderQuantity);						
						int sellAmount = bOrderQuantity;
						
						//Prints
						System.out.println("The store will be charged 30% over the highest cost, " + bOrderPrice + " for the " + sellAmount + " cases = ");
						System.out.println(sellAmount + " @ " + setprecision(markUpPrice) + " = " + setprecision(markUpTotal) + " for the Customer receipt.\n");
						System.out.println("Actual Cost:");
						
						//Subtract totalBeans by the amount of beans sold
						totalBeans -= sellAmount;
						
						//Remainder = remaining beans left to push back onto stack after back order is filled
						//Place the difference back onto the bean stack
						Data remainder = new Data(totalBeans, ((Data)beanCaseStack.peek()).getP());
						Data temp = new Data(((Data)beanCaseStack.peek()).getQ() - totalBeans, remainder.getP());
						beanCaseStack.pop();
						beanCaseStack.push(temp);
						
						//While there are still beans to sell for the back order...
						while(sellAmount > 0){
							
							//Print # of beans sold at what price (for bookkeeping)
							Data top = (Data)beanCaseStack.pop();
							System.out.println( top.getQ() + " @ " + setprecision(top.getP()) + " = " + setprecision((float)top.getQ() * top.getP()));
							sellAmount -= top.getQ();
							totalCost += ((float)top.getQ() * top.getP());
						}
						
						//Print
						System.out.println("Total Cost = $" + setprecision(totalCost) + " for the bookkeeper's records\n");
						
						//Push back the remainder onto the beanCaseStack
						beanCaseStack.push(remainder);
						
						//De-queue the back order
						backOrderQueue.deQ();
					}
			}
			
			//Else if you read an S...
			else if(currentToken.equals("S")){
				
				//Initialize amount to sell
				int sellAmount = Integer.parseInt(myTokens.nextToken());
				
				//Place in backOrderQueue if there isn't enough beans
				if(totalBeans < sellAmount){
					Data d = new Data(sellAmount, ((Data)beanCaseStack.peek()).getP());
					backOrderQueue.enQ(d);
				}
				
				//If enough beans...
				else{
					
					//Print
					System.out.println("Store order for " + sellAmount + " cases.\n");
					System.out.println("The store will be charged 30% over the highest cost, " + ((Data)beanCaseStack.peek()).getP() + " for the " + sellAmount + " cases = ");
					
					//Initialize variables
					float markUpPrice = ((Data)beanCaseStack.peek()).getP() * (float) 1.3;
					float markUpTotal = markUpPrice * ((float) sellAmount);
					
					//More printing
					System.out.println(sellAmount + " @ " + setprecision(markUpPrice) + " = " + setprecision(markUpTotal) + " for the Customer receipt.\n");		 
					System.out.println("Actual Cost:");
					
					//Initialize more variables
					float totalCost = 0;
					totalBeans -= sellAmount;
					
					//While there are beans to sell to fulfill order
					while(sellAmount > 0){
						
						Data top = (Data)beanCaseStack.pop();
						
						//If the # of beans to sell is greater than or equal to the amount of beans on top of the beanCase Stack
						if(sellAmount >= top.getQ()){
							
							//Print # of beans sold at what price (for bookkeeping)
							System.out.println( top.getQ() + " @ " + setprecision(top.getP()) + " = " + setprecision((float)top.getQ() * top.getP()));
							sellAmount -= top.getQ();
							totalCost += ((float)top.getQ() * top.getP());
						}
						
						//If the # of beans to sell is less than the amount of beans on top of the beanCase Stack
						else{
							
							//Print # of beans sold at what price (for bookkeeping) and push back the remaining beans onto the beanCaseStack
							System.out.println( sellAmount + " @ " + setprecision(top.getP()) + " = " + setprecision((float)sellAmount * top.getP()));
							Data remainder = new Data(top.getQ()-sellAmount, top.getP());
							beanCaseStack.push(remainder);
							totalCost += ((float)sellAmount * top.getP());
							sellAmount = 0;
						}
					}
					
					//Prints
					System.out.println("Total Cost = $" + setprecision(totalCost) + " for the bookkeeper's records\n");
					
				}
			}
			
			//Read next line in file
			line = inFile.readLine();

		}
	}

	//Function to set the precision of the float numbers
	public static float setprecision(float f){
		int temp = (int)(f * 100);
		return (((float)temp) / 100);
	}
	
}
