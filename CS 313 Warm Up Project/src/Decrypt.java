//Sang Park CS313-12

import java.lang.*;

/**
 * Decrypts a line given the number of columns by filling an 
 * array with a set number of columns column-wise and then reading 
 * it back row by row. If the line doesn't fit exactly into the 
 * array, the program is stopped.
 * 
 * @author spark
 *
 */
public class Decrypt {
	
	/**
	 * Decrypts a given line by filling in a array with a set
	 * number of columns column-wise and then reading the array 
	 * row by row.
	 * 
	 * @param line Line to be decrypted
	 * @param columns Number of columns used to decrypt
	 * @return Returns a decrypted string
	 */
	public static String decryptLine(String line, int columns){
		
		String returnLine = "";
		
		// If line doesn't divide evenly
		if(line.length() % columns != 0){
			System.out.println("The line to be decrypted does not divide evenly!");
			System.exit(0);
		}
		
		// Stores line column-wise and reads back row by row
		else{
			int rows = line.length() / columns;
			char array[][] = new char[rows][columns];
			int i = 0;
			for(int c = 0; c < columns; c++)
				for(int r = 0; r < rows; r++){
					array[r][c] = line.charAt(i);
					i++;
				}
			
			for(int r = 0; r < array.length; r++)
				for(int c = 0; c < array[0].length; c++)
					returnLine += array[r][c];
		}
		
		return returnLine;
	}
}
