import ga.shane.puzzlegame.RandomPuzzleGetter;

import java.io.File;

/** @author http://www.shane.ga */
public class Main {
	public static void main(String[] args) { 
		new RandomPuzzleGetter(2, new File("images"));
	}
}
