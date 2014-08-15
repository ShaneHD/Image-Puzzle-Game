import ga.shane.puzzle16.Grid;
import ga.shane.puzzle16.PuzzleWindow;

import java.io.File;

import javax.imageio.ImageIO;


/** @author http://www.shane.ga */
public class Main {
	public static void main(String[] args) throws Exception {
		Grid grid = new Grid(ImageIO.read(new File("tiger.jpg")));
		PuzzleWindow puzzle = new PuzzleWindow(grid);
	}
}
