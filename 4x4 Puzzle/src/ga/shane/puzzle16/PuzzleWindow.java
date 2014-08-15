package ga.shane.puzzle16;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/** @author http://www.shane.ga */
public class PuzzleWindow extends JFrame implements MouseListener {
	private final Grid grid;
	private int takenOutIndex;
	private BufferedImage takenOut;
	private JLabel takenOutLabel;
	private final Random random = new Random();
	private final ArrayList<JLabel> labels = new ArrayList<JLabel>();
	/** Amount of moves the player has made */
	private int moves;
	private boolean won;
	public static final String[] formats = {
		"png", "jpg", "jpeg", "gif", "bmp"
	};
	
	public PuzzleWindow(Grid grid) {
		this.grid = grid;
		setLayout(new GridLayout(4, 4));

		randomisePieces();
		setupBoard();
		
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		pack();
		setLocationRelativeTo(null);
	}
	
	/**
	 * Randomise the locations of the pieces, and  remove one piece<br>
	 * {@link #takenOut} {@link #takenOutIndex} {@link #takenOutLabel} are set to the invisible piece
	 */
	private BufferedImage[] randomisePieces() {
		List<BufferedImage> all = Arrays.asList(grid.pieces);
		
		Collections.shuffle(all, random);
		
		int i = random.nextInt(grid.pieces.length);
		takenOut = grid.pieces[i];	
		takenOutIndex = i;
		System.out.println(takenOutIndex);
		grid.pieces[i] = null;
			
		return grid.pieces;
	}
	
	/**
	 * Sets up the puzzle
	 */
	private void setupBoard() {
		for(JLabel label : labels)
			remove(label);
		
		labels.clear();
		setTitle("Shane's 4x4 puzzle game [" + moves + " moves]");		
		
		if(won) {
			setTitle(getTitle() + " YOU'VE WON!");
			
			for(BufferedImage piece : grid.getPiecesInNormalOrder())
				add(new JLabel(new ImageIcon(piece)));
			
			revalidate();
			repaint();
			return;
		}
		
		for(BufferedImage piece : grid.pieces) {
			if(piece == null) {
				takenOutLabel = new JLabel(new ImageIcon(takenOut));
				add(takenOutLabel);
				takenOutLabel.addMouseListener(this);
				labels.add(takenOutLabel);
				takenOutLabel.setVisible(false);
				
				continue;
			}
			
			JLabel label = new JLabel(new ImageIcon(piece));
			label.addMouseListener(this);
			add(label);
			labels.add(label);
		}
		
		revalidate();
		repaint();
	}
	
	/**
	 * Checks whether an array has the same contents as another
	 */
	private boolean isArraySame(Object[] a1, Object[] a2) {
		for(int i = 0; i < a1.length; i++) {
			Object ao1 = a1[i];
			Object ao2 = a2[i];
			
			if(ao1 == null || ao2 == null)
				continue;
			
			if(ao1 != ao2)
				return false;
		}
		
		return true;
	}
	
	/**
	 * Checks whether the player has won
	 */
	private boolean checkWon() {
		return won = isArraySame(grid.pieces, grid.getPiecesInNormalOrder());
	}
	
	/**
	 * Completes the puzzle
	 */
	private void autoComplete() {
		for(int i = 0; i < grid.pieces.length; i++)
			grid.pieces[i] = grid.getPiecesInNormalOrder()[i];
		
		grid.pieces[takenOutIndex] = null;
		BufferedImage replace = grid.pieces[takenOutIndex + 4];
		grid.pieces[takenOutIndex + 4] = null;
		grid.pieces[takenOutIndex] = replace;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
//		Make sure the left mouse button is being pressed
		if(e.getButton() != 1) {
//			hax
			autoComplete();
			checkWon();
			setupBoard();
			
			return;
		}
		
//		The piece that's been clicked
		JLabel clicked = (JLabel) e.getSource();
//		Its associated image
		BufferedImage img = (BufferedImage) ((ImageIcon) clicked.getIcon()).getImage();
		
//		If it's the invisible piece, gtfo
		if(!clicked.isVisible())
			return;
		
//		Its index
		int index = labels.indexOf(clicked);
		
		try {
//			Code to check whether the clicked piece can be moved or not
			int change = index;
			final int[] check = {-1, 1, -4, 4};
			
			for(int cur : check) {
//				This is in a try block because of index out of bounds being thrown in certain situations (which resulted in pieces not being able to move)
				try {
					if(grid.pieces[index + cur] == null)
						change+= cur;
				} catch(Exception ex) {}
			}
			
//			If it can't move, simply break out
			if(change == index)
				throw new Exception();
			
//			Increment player move count
			moves++;
//			Move the piece
			grid.pieces[change] = img;
			grid.pieces[index] = null;
			
//			Check if won and re-setup the board
			checkWon();
			setupBoard();
		} catch(Exception ex) {}		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
}
