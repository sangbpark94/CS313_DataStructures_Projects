//Sang Park CS313-12

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * Creates an output file called output.txt, overwrite any existing
 * output.txt, sets append_to_file to be true and instantiates a FileGUI.
 * 
 * @author spark
 *
 */
public class Main {
	
	public static void main(String[] args) throws IOException{
		
		String file_name = "output.txt";
		WriteFile data = new WriteFile(file_name);
		data = new WriteFile(file_name,true);
		FileGUI myFileGUI = new FileGUI();
	
	}
}
