import ga.shane.puzzle16.RandomPuzzleGetter;

import java.io.File;


/** @author http://www.shane.ga */
public class Main {
	public static void main(String[] args) throws Exception {
		//Grid grid = new Grid(ImageIO.read(new File("tiger.jpg")));
		//PuzzleWindow puzzle = new PuzzleWindow(grid);
		new RandomPuzzleGetter(new File("images"));
	}
}
