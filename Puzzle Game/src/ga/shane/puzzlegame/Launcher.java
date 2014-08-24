package ga.shane.puzzlegame;

import ga.shane.utilities.GuiUtils;
import ga.shane.utilities.WindowsUtils;

import javax.swing.JOptionPane;

/**
 * A launcher fit for release<br>
 * Prompts the user for the size of the puzzle, and an image to use in it
 * 
 * @author http://www.shane.ga
 */
public class Launcher {
	public Launcher() {
		GuiUtils.setDefaultLookandfeel();
		PuzzleSelector selector = null;
		
		do {
			try {
				int size = Integer.parseInt(JOptionPane.showInputDialog("Enter a size (E.G. '4' for a 4x4 16 grid)"));
				selector = new PuzzleSelector(size); 
			} catch(Exception e) {
				WindowsUtils.messagebox("Something went wrong! Enter a number (2-100 recommended)", "Error!", JOptionPane.ERROR_MESSAGE);
			}
		} while(selector == null);
		
	}
}
