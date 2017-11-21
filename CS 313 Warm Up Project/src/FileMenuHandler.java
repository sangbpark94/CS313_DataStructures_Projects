//Sang Park CS 313-12

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

/**
 * This class is used to handle the file menu actions
 * and it implements the ActionListener class.
 * 
 * @author spark
 *
 */
public class FileMenuHandler implements ActionListener {
	
	JFrame jframe;
   
	/**
	 * Constructor for a FileMenuHandler that sets
	 * the JFrame in the class.
	 * 
	 * @param jf A type JFrame used to set jFrame in the FileMenuHandler class
	 */
	public FileMenuHandler (JFrame jf) {
		jframe = jf;
	}
	
	/**
	 * This method is called when there is an event, namely
	 * open and quit.
	 */
	public void actionPerformed(ActionEvent event) {
		String  menuName;
		menuName = event.getActionCommand();
		if (menuName.equals("Open"))
			openFile(); 
		else if (menuName.equals("Quit"))
			System.exit(0);
	}
	
	/**
	 * This method opens the file and allows user to choose
	 * a file to read.
	 */
   	private void openFile() {
   		
    	// Sets chooser and status
   		JFileChooser chooser = new JFileChooser();
    	int status = chooser.showOpenDialog(null);
       
    	// If file is approved call readSource method
    	if (status == JFileChooser.APPROVE_OPTION) 
    		readSource(chooser.getSelectedFile());
    	else 
    		JOptionPane.showMessageDialog(null, "Open File dialog canceled");
    
    }
   	
   	/**
   	 * Reads the source file line by line and then categorizes it.
   	 * 
   	 * @param chosenFile Passes in the chosen file in type File
   	 */
   	private void readSource(File chosenFile) {
   		
		String path = chosenFile.getAbsolutePath();
		TextFileInput inFile = new TextFileInput(path);
		String line = inFile.readLine();
		
		while(line != null){
			
			// First column value
			int firstN = Integer.parseInt(Character.toString(line.charAt(1)));
			
			// Second column value
			int secondN = Integer.parseInt(Character.toString(line.charAt(2)));
			
			// If encrypt
			if(line.charAt(0) == 'E'){
				CategorizeLine.categorizeLine(line,firstN,secondN,true);
			}
			
			// If decrypt
			else if(line.charAt(0) == 'D')
				CategorizeLine.categorizeLine(line,firstN,secondN,false);
			
			// If neither
			else{
				System.out.println("Input does not start with E or D");
				System.exit(0);
			}
			
			line = inFile.readLine();
		}		
    }
}