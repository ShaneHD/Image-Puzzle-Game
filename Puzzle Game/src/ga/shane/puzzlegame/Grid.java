package ga.shane.puzzlegame;

import ga.shane.utilities.ArrayUtils;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/** @author http://www.shane.ga */
public class Grid {
	public final int size;
	public final BufferedImage[] pieces, piecesInDefaultOrder, piecesInDefaultOrderWithoutRemoved;
	/** The index of the piece that has been removed */
	public int removedIndex;
	/** The piece that has been removed (in {@link BufferedImage} form) */
	public BufferedImage removedPiece;
	private final Random random = new Random();
	private final int[] possibleMoves;
	private int moves;
	
	/**
	 * @param size The size of the puzzle (cannot be below 2)
	 */
	public Grid(int size, BufferedImage image) {
		if(size <= 1)
			throw new RuntimeException("Size must be above 1!");
		
		this.size = size;
		pieces = new BufferedImage[size*size];
		piecesInDefaultOrder = new BufferedImage[pieces.length];
		piecesInDefaultOrderWithoutRemoved = new BufferedImage[pieces.length];
		possibleMoves = new int[] {-1, 1, -size, size};
		
//		Get the images width
		final int width = image.getWidth();
//		And its height
		final int height = image.getHeight();
		
//		Initialise the splice variables
		int x = 0;
		int y = 0;
//		Width and height to use when splicing
		final int w = width / size;
		final int h = height / size;
		
//		Iterate from 0 to size
		for(int i = 0; i < pieces.length; i++) {
//			If at the end of the row, go to the next
			if(i != 0 && i % size == 0) {
				x = 0;
				y+= h;
			}
			
//			Create the piece
			pieces[i] = image.getSubimage(x, y, w, h);
			
//			Increment to the next column
			x+= w;
		}
		
//		Populate the default order arrays
		ArrayUtils.set(piecesInDefaultOrder, pieces);
		ArrayUtils.set(piecesInDefaultOrderWithoutRemoved, pieces);
	}
	
	/**
	 * Auto completes the puzzle
	 */
	public void complete() {
		ArrayUtils.set(pieces, piecesInDefaultOrder);
	}
	
	/**
	 * Checks if a piece is on the side or not
	 */
	private boolean isSidePiece(int id) {
//		If the id is odd (on the right hand side if it's a side piece)
		if(id % 2 != 0)	
//			Add 1 to this piece so id%4 returns 0 if it's a side piece
			id++;
		
		return id % 4 == 0;
	}
	
	/**
	 * Move a piece
	 * @param clicked The index to move
	 * @return Whether the move is valid or not (if the piece was moved)
	 */
	public boolean move(int clicked) {
		try {
			int change = clicked;
			
			for(int cur : possibleMoves) {
//				This is in a try block because of index out of bounds being thrown in certain situations (which resulted in pieces not being able to move)
				try {
////					If it's a side piece
//					if(isSidePiece(clicked)) {
////						Make sure that the move isn't invalid
//						int check = clicked % 4 == 0 ? -1 : 1;
//						
////						If it is
//						if(cur == (clicked % 4 == 0 ? -1 : 1))
////							Skip this move
//							continue;
//					}
					
//					If the piece at these coordinates is null, then the player can move
//					change is the index of which to move the piece to
					if(pieces[clicked + cur] == null)
						change+= cur;
				} catch(Exception e) {}
			}
			
//			If it can't move
			if(change == clicked)
//				Break out
				throw new Exception();
			
//			The move is valid
//			So increment the move counter
			moves++;
//			Move the piece
			pieces[change] = pieces[clicked];
//			The index that this piece was in before is set to null (the blank piece)
			pieces[clicked] = null;
			
			return true;
		} catch(Exception e) {}
		
		return false;
	}
	
	/**
	 * @return The player's move count
	 */
	public int moveCount() {
		return moves;
	}
	
//	Not using ArrayUtils for this 'cause it's not working for some reason and i cba to look into it now so deal with it
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
	 * If the puzzle is solved, the missing pieces gets added
	 * @return Is the puzzle is solved?
	 */
	public boolean solved() {
		if(isArraySame(pieces, piecesInDefaultOrderWithoutRemoved)) {
			pieces[removedIndex] = piecesInDefaultOrder[removedIndex];
			return true;
		}
		
		return false;
	}
	
	/**
	 * Randomise the locations of the pieces, and remove one piece<br>
	 * {@link #removedIndex} is set to the index of the removed piece<br>
	 * and {@link #removedPiece} is set to the {@link BufferedImage} of the removed piece
	 */
	public void randomise() {
//		Convert the grid to a List
		List<BufferedImage> all = Arrays.asList(pieces);
//		Shuffle the grid
		Collections.shuffle(all, random);
		
//		Get a random piece to remove 
		int i = random.nextInt(pieces.length);
//		Set the removedPiece field to the removed piece
		removedPiece = pieces[i];
//		And its index
		removedIndex = i;
//		Set the piece to null so that it gets set to non-visible
		pieces[i] = null;
		piecesInDefaultOrderWithoutRemoved[i] = null;
	}
}
