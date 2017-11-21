//Sang Park CS313-12

import javax.swing.*;
import java.awt.*;

/**
 * This class is the FileGUI has a constructor and
 * a method to create the menu.
 * 
 * @author spark
 *
 */
public class FileGUI extends JFrame{
	
	/**
	 * Constructor that sets the size, location, title, close operation, 
	 * visibility, and calls the createMenu method
	 */
	public FileGUI(){
		setSize(1000, 500);
		setLocation(100, 100);
		setTitle("FileGUI");
		createMenu();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	/**
	 * This method creates the menu for the GUI
	 */
	private void createMenu(){
	
		//Creates menu variables to be used
		JMenuItem item;
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		FileMenuHandler fmh = new FileMenuHandler(this);
		
		//Adds Open to the menu
		item = new JMenuItem("Open");
		item.addActionListener(fmh);
		fileMenu.add(item);
		
		//Horizontal line to separate items
		fileMenu.addSeparator();
		
		//Adds Quit to the menu
		item = new JMenuItem("Quit");
		item.addActionListener(fmh);
		fileMenu.add(item);
		
		//Sets Menu Bar and adds the file menu to the menu bar
		setJMenuBar(menuBar);
		menuBar.add(fileMenu);
	}
}
