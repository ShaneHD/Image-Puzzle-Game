package ga.shane.puzzle16.differentsizes;

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

/** @author https://www.github.com/ShaneHD */
class PuzzleWindow extends JFrame implements MouseListener {
	/** The grid. Contains a size 16 array of {@link BufferedImage}s ({@link Grid#pieces}) and a default version of this (with normal order that isn't randomised) ({@link Grid#getPiecesInNormalOrder()})*/ 
	private final Grid grid;
	/** The index of the removed piece */
	private int takenOutIndex;
	/** The {@link BufferedImage} of the removed piece */
	private BufferedImage takenOut;
	/** The {@link JLabel} of the removed piece */
	private JLabel takenOutLabel;
	/** A random instance */
	private final Random random = new Random();
	/** A list of all {@link JLabel}s currently added to the frame (all pieces in label form))*/
	private final ArrayList<JLabel> labels = new ArrayList<JLabel>();
	/** Amount of moves the player has made */
	private int moves;
	/** Has the player won? */
	private boolean won;
	/** A list of all image formats (png, jpg, etc.) */
	public static final String[] FORMATS = {
		"png", "jpg", "jpeg", "gif", "bmp"
	};
	
	public PuzzleWindow(Grid grid) {
		this.grid = grid;
//		Assign this frame a 4x4 grid layout
		setLayout(new GridLayout(grid.size, grid.size));
		
//		Set up the board for the first time
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
//		Convert the grid to a List
		List<BufferedImage> all = Arrays.asList(grid.pieces);
//		Shuffle the grid
		Collections.shuffle(all, random);
		
//		Get a random piece to remove 
		int i = random.nextInt(grid.pieces.length);
//		Set the takenOut field to the removed piece
		takenOut = grid.pieces[i];
//		And its index
		takenOutIndex = i;
//		Set the piece to null so that it gets set to non-visible
		grid.pieces[i] = null;
			
		return grid.pieces;
	}
	
	/**
	 * Sets up the puzzle
	 */
	private void setupBoard() {
//		Remove any previously added pieces
		for(JLabel label : labels)
			remove(label);
		
		labels.clear();
		setTitle("Shane's 4x4 (now different sizes!) puzzle game [" + moves + " moves] [" + grid.size + " x " + grid.size + "]");		
		
//		When the player has won
		if(won) {
			setTitle(getTitle() + " YOU'VE WON!");
			
//			Add all the pieces in their correct places to show the full image
			for(BufferedImage piece : grid.getPiecesInNormalOrder())
				add(new JLabel(new ImageIcon(piece)));
			
//			Make sure that the screen is refreshed
			revalidate();
			repaint();
			
			return;
		}
		
//		Iterate through all pieces in the grid
		for(BufferedImage piece : grid.pieces) {
//			If the piece has been removed
			if(piece == null) {				
				takenOutLabel = new JLabel(new ImageIcon(takenOut));
				add(takenOutLabel);
				takenOutLabel.addMouseListener(this);
				labels.add(takenOutLabel);
//				Make sure it's not visible
				takenOutLabel.setVisible(false);
				
				continue;
			}
			
//			Create the piece in JLabel form
			JLabel label = new JLabel(new ImageIcon(piece));
			label.addMouseListener(this);
			add(label);
			labels.add(label);
		}
		
//		Make sure the screen is re-rendered
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
			
//			Make sure that we're not comparing nulls
			if(ao1 == null || ao2 == null)
				continue;
			
//			If any value inside array1 isn't the same as its index in array 2, they're not the same
			if(ao1 != ao2)
				return false;
		}
		
//		They're the same (hasn't returned false)
		return true;
	}
	
	/**
	 * Checks whether the player has won
	 */
	private boolean checkWon() {
		return won = isArraySame(grid.pieces, grid.getPiecesInNormalOrder());
	}
	
	/**
	 * All but one move completes the puzzle
	 */
	private void autoComplete() {
		for(int i = 0; i < grid.pieces.length; i++)
			grid.pieces[i] = grid.getPiecesInNormalOrder()[i];
		
		int margin = 1;
		
//		Make sure the margin will work
		try {
			Object o = grid.pieces[takenOutIndex + margin];
		} catch(Exception e) {
			margin = -1;
		}
		
		grid.pieces[takenOutIndex] = null;
		BufferedImage replace = grid.pieces[takenOutIndex + margin];
		grid.pieces[takenOutIndex + margin] = null;
		grid.pieces[takenOutIndex] = replace;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
//		Make sure the left mouse button is being pressed
		if(e.getButton() != 1) {
//			hax
			/*autoComplete();
			checkWon();
			setupBoard();*/
			
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
			final int[] check = {-1, 1, -grid.size, grid.size};
			
			for(int cur : check) {
//				This is in a try block because of index out of bounds being thrown in certain situations (which resulted in pieces not being able to move)
				try {
//					If the piece at these coordinates is null, then the player can move
//					change is the index of which to move the piece to
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
//			The index that this piece was before its move is set to null (blank)
			grid.pieces[index] = null;
			
//			Check if won and re-setup the board
			checkWon();
			setupBoard();
		} catch(Exception ex) {}		
	}

//	Methods that we don't need to use
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
