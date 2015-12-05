package com.github.shanehd.puzzlegame;

import java.security.InvalidParameterException;

import javax.swing.JOptionPane;

import com.github.shanehd.utilities.GuiUtils;
import com.github.shanehd.utilities.WindowsUtils;

/**
 * A launcher fit for release<br>
 * Prompts the user for the size of the puzzle, and an image to use in it
 * 
 * @author https://www.github.com/ShaneHD
 */
public class Launcher {
	public Launcher() {
		GuiUtils.setDefaultLookandfeel();
		PuzzleSelector selector = null;
		
		do {
			try {
				String ssize = JOptionPane.showInputDialog("Enter a size (E.G. '4' for a 4x4 16 grid)");
				
				if(ssize == null || ssize.length() == 0)
					throw new NullPointerException();
					
				int size = Integer.parseInt(ssize);
				
				if(size <= 1)
					throw new InvalidParameterException();
				
				selector = new PuzzleSelector(size); 
			} catch(InvalidParameterException e) {
				WindowsUtils.messagebox("The size MUST be above 1!", "Error!", JOptionPane.ERROR_MESSAGE);
			} catch(NullPointerException e) {
				System.exit(0);
			} catch(Exception e) {
				WindowsUtils.messagebox("Something went wrong! Enter a number (2-100 recommended)", "Error!", JOptionPane.ERROR_MESSAGE);
			}
		} while(selector == null);
		
	}
}
