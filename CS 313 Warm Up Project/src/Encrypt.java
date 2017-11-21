//Sang Park CS313-12

import java.lang.*;
import java.util.Random;

/**
 * Encrypts a line given the number of columns by filling an 
 * array with a set number of columns and then reading it back
 * column by column. If the line doesn't fit exactly into the 
 * array, it will replace the extra spaces with X, Z or Q 
 * 
 * @author spark
 *
 */
public class Encrypt {
	
	//Stores the  randomXYZ which is 0, 1 or 2
	private static Random rand = new Random();
	private static int randomXZQ = (rand.nextInt(3) + 1) % 3;
	
	/**
	 * Encrypts a given line by filling in a array with a set
	 * number of columns and then reading the array column by
	 * column.
	 * 
	 * @param line Line to be encrypted
	 * @param columns Number of columns used to encrypt
	 * @return Returns a encrypted string
	 */
	public static String encryptLine(String line, int columns){
		
		int rows = (int)Math.ceil(((float)line.length())/columns);
		char array[][] = new char[rows][columns];
		int i = 0;
		
		// Stores in array row by row
		for(int r = 0; r < rows; r++)
			for(int c = 0; c < columns; c++){
				// If blank spaces put X, Z or Q
				if(!(i < line.length())){
					if(randomXZQ == 0)
						array[r][c] = 'X';
					else if(randomXZQ == 1)
						array[r][c] = 'Z';
					else
						array[r][c] = 'Q';
					i++;
				}
				// If not a blank space
				else{
					array[r][c] = line.charAt(i);
					i++;
				}
			}
		
		String returnLine = "";
		
		// Reads in line column by column
		for(int c = 0; c < array[0].length; c++)
			for(int r = 0; r < array.length; r++)
				returnLine += array[r][c];
		
		return returnLine;
	}
}


