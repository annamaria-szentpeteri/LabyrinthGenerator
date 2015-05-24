/**
 * 
 */
package implementation;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/**
 * @author Mari
 * This class is the responsible for creating and 
 * handling labyrinths. With this class you are
 * able to create a random labyrinth with the 
 * sizes given by you.
 * 
 * You are also able to save and load the labyrinth.
 * 
 * >>Detailed descreption of functionality and structure comes here.<<
 */
public class Labyrinth {
	private Integer height;
	private Integer width;
	private ArrayList<BitSet> horizontalWalls;
	private ArrayList<BitSet> verticalWalls;
	
	
	/**
	 * @param x
	 * @param y
	 */
	public Labyrinth(int x, int y){
		width = x;
		height = y;
		
		/** Creating arrays which holds horizontal and vertical walls. */
		horizontalWalls = new ArrayList<BitSet>(height + 1);
		verticalWalls = new ArrayList<BitSet>(height);
		
		for(BitSet bs: horizontalWalls){
			bs = new BitSet(width);
		}
		for(BitSet bs: verticalWalls){
			bs = new BitSet(width + 1);
		}
		
		Init();
	}
	
	/**
	 * @return
	 */
	public int getHeight(){
		return this.height;
	}
	
	/**
	 * @return
	 */
	public int getWidth(){
		return this.width;
	}
	
	/**
	 * Gets coordinates of a field, returns the field's border
	 * information.
	 * 
	 * @param x : horizontal coordinate of the field
	 * @param y : vertical coordinate of the field
	 * @return  : a [top, left, bottom, right] array which are either 0 (no border) or 1 (border)
	 */
	public List<Integer> getFieldBorders(int x, int y){
		//out of border-e valamelyik -> Exception
		List<Integer> result = null; 
		
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
			// Loggold ha exception keletkezett 
		}
		
 		return result;
	}
	
	/**
	 * Clears the labyrinth. Creates the borders and
	 * deletes the inner walls making a clean "room".
	 */
	public void Init(){
		/** Deleting all horizontal walls. */
		for(BitSet bs: horizontalWalls){
			bs.clear();
		}
		
		/** Deleting all vertical walls. */
		for(BitSet bs: verticalWalls){
			bs.clear();
		}
		
		/** Setting the borders of the labyrinth. */
		
	}
	
	/**
	 * Generates a random labyrinth.
	 * First, it's cleans the labyrinth with calling Init(), then
	 * makes a new labyrinth randomly.
	 * 
	 */
	public void Generate(){
		this.Init();	/** First needs a clean up to empty the labyrinth. */
	}
	
	
}
