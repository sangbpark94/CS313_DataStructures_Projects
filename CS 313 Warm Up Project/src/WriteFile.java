//Sang Park CS313-12

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * This class creates the ability to write onto a file.
 * 
 * @author spark
 *
 */
public class WriteFile {
	
	private static String path;
	private static boolean append_to_file = false;
	
	/**
	 * A constructor for a file to be overwritten.
	 * 
	 * @param file_path The path of the file
	 */
	public WriteFile(String file_path){
		path = file_path;
	}
	
	/**
	 * A constructor that appends to a file.
	 * 
	 * @param file_path The path of the file
	 * @param append_value Sets append_value to be true since you are going to append
	 */
	public WriteFile(String file_path, boolean append_value){
		path = file_path;
		append_to_file = append_value;
	}
	
	/**
	 * Writes to the file by appending it to the last thing written.
	 * 
	 * @param textLine Line to be appended
	 * @throws IOException Throws exception if the text line is invalid
	 */
	public static void writeToFile(String textLine) throws IOException{
		
		FileWriter write = new FileWriter(path, append_to_file);
		PrintWriter print_line = new PrintWriter(write);
		
		print_line.printf("%s" + "%n", textLine);
		
		print_line.close();
		
	}
}
