import java.io.File;

import ga.shane.puzzle16.PuzzleSelector;
import ga.shane.puzzle16.RandomPuzzleGetter;

import javax.swing.UIManager;


/** @author http://www.shane.ga */
public class Main {
	public static void main(String[] args) throws Exception {
		//new RandomPuzzleGetter(new File("images"));
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {}
		
		new RandomPuzzleGetter(new File("images"));
	}
}
