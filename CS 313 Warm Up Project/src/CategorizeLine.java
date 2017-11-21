//Sang Park CS 313-12

import java.io.IOException;
/**
 * Categorizes a line to decide what the two column values are
 * and whether it will be encrypted or decrypted.
 * 
 * @author spark
 *
 */
public class CategorizeLine {
	
	/**
	 * This function decides what will happen to the line: what the
	 * two column values are and whether it will be encrypted or 
	 * decrypted.
	 * 
	 * @param line The line to be encrypted or decrypted
	 * @param firstN The first column value
	 * @param secondN The second column value
	 * @param isEncrypt Decides whether you are encrypting or decrypting
	 */
	public static void categorizeLine(String line, int firstN, int secondN, boolean isEncrypt){
		
		// Line without the first three characters and the last '*'
		String shortenedLine = "";
		int i = 3;
		while(line.charAt(i) != '*'){
			shortenedLine += Character.toString(line.charAt(i));
			i++;
		}
		
		// If ?00
		if(firstN == 0 && secondN == 0){
			try{
				WriteFile.writeToFile(shortenedLine);
			}
			catch(IOException ioe){
				System.out.println("Not a valid line.");
			}
		}
		
		else{
			String printedLine = "";
			
			// If ?0?
			if(firstN == 0 && secondN >= 1 && secondN <= 9){
				
				// If E0?
				if(isEncrypt)
					printedLine = Encrypt.encryptLine(shortenedLine,secondN);
				
				// If D0?
				else if(!isEncrypt){
					printedLine = Decrypt.decryptLine(shortenedLine,secondN);
				}
				try{
					WriteFile.writeToFile(printedLine);
				}
				catch(IOException ioe){
					System.out.println("Not a valid line.");
				}
			}
		
			// If ??0
			else if(secondN == 0 && firstN >= 1 && firstN <= 9){
				
				// If E?0
				if(isEncrypt)
					printedLine = Encrypt.encryptLine(shortenedLine,firstN);
				
				// If D?0
				else if(!isEncrypt)
					printedLine = Decrypt.decryptLine(shortenedLine,firstN);
				
				try{
					WriteFile.writeToFile(printedLine);
				}
				catch(IOException ioe){
					System.out.println("Not a valid line.");
				}
			}
		
			// If ???
			else if (firstN >= 1 && firstN <= 9 && secondN >= 1 && secondN <= 9){
				
				// If E??
				if(isEncrypt)
					printedLine = Encrypt.encryptLine(Encrypt.encryptLine(shortenedLine,firstN),secondN);
				
				// If D??
				else if(!isEncrypt)
					printedLine = Decrypt.decryptLine(Decrypt.decryptLine(shortenedLine,firstN),secondN);
				
				try{
					WriteFile.writeToFile(printedLine);
				}
				catch(IOException ioe){
					System.out.println("Not a valid line.");
				}
			}
			
			// If not any of the above situations
			else{
				System.out.println("The columns you provided are not 0-9");
				System.exit(0);
			}
		}
	}
}	