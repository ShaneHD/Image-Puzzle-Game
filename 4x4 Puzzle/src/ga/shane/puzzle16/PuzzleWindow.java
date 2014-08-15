package ga.shane.puzzle16;

import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/** @author http://www.shane.ga */
public class PuzzleWindow extends JFrame {
	private final Grid grid;
	private BufferedImage takenOut;
	private final Random random = new Random();
	
	public PuzzleWindow(Grid grid) {
		this.grid = grid;
		setLayout(new GridLayout(4, 4));

		BufferedImage[] pieces = randomisePieces();
		
		for(BufferedImage piece : pieces) {
			if(piece == null) {
				JLabel invis = new JLabel(new ImageIcon(takenOut));
				invis.setVisible(false);
				add(invis);
				
				continue;
			}
			
			add(new JLabel(new ImageIcon(piece)));
		}
		
		setResizable(false);
		setTitle("Shane's 4x4 puzzle game");
		setAlwaysOnTop(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		pack();
		setLocationRelativeTo(null);
	}
	
	/**
	 * Randomise the locations of the pieces, and  remove one piece<br>
	 * {@link #takenOut}
	 */
	private BufferedImage[] randomisePieces() {
		List<BufferedImage> all = Arrays.asList(grid.pieces);
		BufferedImage[] pieces = new BufferedImage[15];
		
		Collections.shuffle(all);
		int i = random.nextInt(grid.pieces.length);
		takenOut = grid.pieces[i];
		grid.pieces[i] = null;
		
		return grid.pieces;
	}
}
