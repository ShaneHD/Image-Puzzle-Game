import ga.shane.puzzlegame.PuzzleSelector;
import ga.shane.puzzlegame.RandomPuzzleGetter;
import java.io.File;

/** @author http://www.shane.ga */
public class Main {
	public static void main(String[] args) { 
		//new PuzzleSelector(4, new File("images/tiger.jpg"));
		new RandomPuzzleGetter(4, new File("images"));
	}
}
