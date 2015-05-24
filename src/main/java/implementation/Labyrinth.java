/**
 * 
 */
package implementation;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Random;

/**
 * @author Mari
 * 
 * This class is the responsible for creating and 
 * handling labyrinths. With this class you are
 * able to create a random labyrinth with the 
 * sizes given by you.
 * 
 * You are also able to save and load the labyrinth.
 * 
 * Detailed descreption of functionality and structure comes here.
 */
public class Labyrinth {
	/**
	 * Height of the labyrinth.
	 */
	private Integer height;
	/**
	 * Width of the labyrinth.
	 */
	private Integer width;
	/**
	 * Array which stores the horizontal positioned walls.
	 */
	private ArrayList<BitSet> horizontalWalls;
	/**
	 * Array which stores the vertical positioned walls.
	 */
	private ArrayList<BitSet> verticalWalls;

//-------------------------------------------------------------------
	
	/**
	 * Creates an initial labyrinth with the given size.
	 * 
	 * @param x width of labyrinth
	 * @param y height of labyrinth
	 */
	public Labyrinth(int w, int h){
		/** Setting the labyrinth's sizes. */
		width = w;
		height = h;
		
		/** Creating arrays which holds horizontal and vertical walls. */
		horizontalWalls = new ArrayList<BitSet>();
		verticalWalls = new ArrayList<BitSet>();
		
		for(int i = 0; i < height + 1; i++){
			horizontalWalls.add(new BitSet(width));
		}
		for(int i = 0; i < width + 1; i++){
			verticalWalls.add(new BitSet(height));
		}
		
		Init();
	}

//-------------------------------------------------------------------
	
	/**
	 * Returns the height of the labyrinth.
	 * 
	 * @return height of the labyrinth
	 */
	public int getHeight(){
		return height;
	}
	
	/**
	 * Returns the width of the labyrinth.
	 * 
	 * @return width of the labyrinth
	 */
	public int getWidth(){
		return width;
	}
	
	/**
	 * Gets coordinates of a field, returns the field's border
	 * information.
	 * 
	 * @param x horizontal coordinate of the field
	 * @param y vertical coordinate of the field
	 * @return a [top, left, bottom, right] array which members are either true or false
	 */
	public ArrayList<Boolean> getFieldBorders(int x, int y){
		ArrayList<Boolean> result = null; 
		
		try {
			result = new ArrayList<Boolean>();
			
			/**
			 * Add top border.
			 */
			result.add(horizontalWalls.get(y).get(x));
			/**
			 * Add left border.
			 */
			result.add(verticalWalls.get(x).get(y));
			/**
			 * Add bottom border.
			 */
			result.add(horizontalWalls.get(y + 1).get(x));
			/**
			 * Add right border.
			 */
			result.add(verticalWalls.get(x).get(y + 1));
			
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		
 		return result;
	}

//-------------------------------------------------------------------
	
	/**
	 * Sets the height of the labyrinth
	 * 
	 * @param h the given height
	 */
	public void setHeight(int h){
		height = h;
	}
	
	/**
	 * Sets the width of the labyrinth
	 * 
	 * @param w the given width
	 */
	public void setWidth(int w){
		width = w;
	}
	
	/**
	 * Gets coordinates of a field and an array with information
	 * about the borders in this strict order: top,left,bottom,right
	 * 
	 * Sets the field's borders by the given information.
	 * 
	 * @param x horizontal coordinate of the field
	 * @param y vertical coordinate of the field
	 * @param borders a [top, left, bottom, right] array which contains
	 *                information about the field's borders
	 */
	public void setFieldBorders(int x, int y, ArrayList<Boolean> borders){
		try {			
			/**
			 * Set top border.
			 */
			horizontalWalls.get(y).set(x, borders.remove(0));
			/**
			 * Set left border.
			 */
			verticalWalls.get(x).set(y, borders.remove(0));
			/**
			 * Set bottom border.
			 */
			horizontalWalls.get(y + 1).set(x, borders.remove(0));
			/**
			 * Set right border.
			 */
			verticalWalls.get(x).set(y + 1, borders.remove(0));
			
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}

//-------------------------------------------------------------------
	
	/**
	 * Clears the labyrinth. Creates the borders of the
	 * labyrinth and deletes the inner walls making a
	 * clean "room".
	 */
	public void Init(){
		/**
		 * Deleting all horizontal walls.
		 */
		for(BitSet bs: horizontalWalls){
			bs.clear();
		}
		
		/**
		 * Deleting all vertical walls. 
		 */
		for(BitSet bs: verticalWalls){
			bs.clear();
		}
		
		/**
		 * Setting the borders of the labyrinth.
		 */
		int bsSize = 0;
		
		/** 
		 * Top side of the labyrinth. 
		 */
		bsSize = horizontalWalls.get(0).size();
		horizontalWalls.get(0).flip(0, bsSize);
		
		/** 
		 * Bottom side of the labyrinth. 
		 */
		bsSize = horizontalWalls.get(horizontalWalls.size() - 1).size();
		horizontalWalls.get(horizontalWalls.size() - 1).flip(0, bsSize);
		
		/**
		 *  Left side of the labyrinth. 
		 */
		bsSize = verticalWalls.get(0).size();
		verticalWalls.get(0).flip(0, bsSize);
		
		/**
		 * Right side of the labyrinth. 
		 */
		bsSize = verticalWalls.get(verticalWalls.size() - 1).size();
		verticalWalls.get(verticalWalls.size() - 1).flip(0, bsSize);
	}
	
	
	/**
	 * Generates a random labyrinth.
	 * First, it's cleans the labyrinth with calling Init(), then
	 * makes a new labyrinth randomly.
	 * 
	 */
	public void Generate(){
		/**
		 * First needs a clean up to empty the labyrinth. 
		 */
		this.Init();
		
		/** 
		 * Random number generator to randomize the generating method. 
		 */
		Random rand = new Random();
		
		/**
		 * Vertical or horizontal walls will be generated
		 * depending on this variable. 
		 */
		Integer hORv;
		
		/**
		 * Decides that which line will be filled with walls.
		 */
		Integer line;
		
		/** 
		 * Arrays to handle which lines were filled and which wasn't.
		 */
		ArrayList<Integer> hIndexes = new ArrayList<Integer>();
		ArrayList<Integer> vIndexes = new ArrayList<Integer>();
		
		/**
		 * Filling the arrays with line numbers.
		 * 
		 * The first and last element will never be needed 
		 * because of the Init() function, which already
		 * filled them.
		 */
		for(int i = 1; i < horizontalWalls.size() - 1; i++){
			hIndexes.add(i);
		}
		for(int i = 1; i < verticalWalls.size() - 1; i++){
			vIndexes.add(i);
		}
		
		/**
		 * While both arrays have unfilled lines, decide randomly
		 * which side and line will filled. 
		 */
		while ( !(hIndexes.isEmpty() && vIndexes.isEmpty()) ){
			hORv = rand.nextInt(2);
			
			/**
			 * If one of the arrays is empty, it's fixed that which
			 * side needs to be chosen.
			 */
			if (hIndexes.isEmpty())
				hORv = 1;
			if (vIndexes.isEmpty())
				hORv = 0;
			
			/** 
			 * If hORv is 0 then generates a horizontal line otherwise it's
			 * generates a vertical line.
			 * The number of line is choosed by random numbers too.
			 * 
			 * The generating method is:
			 * 	- check if perpendicularly have a border "behind" the current line
			 * 	- if yes: then don't put wall there
			 *  - if no: then put a wall there
			 */
			if (hORv == 0){
				/**
				 * Get line number
				 */
				line = hIndexes.remove( rand.nextInt(hIndexes.size()) );
				
				/**
				 * Generating.
				 */
				for(int i = 0; i < horizontalWalls.get(line).size() ; i++){
					if (verticalWalls.get(i).get(line - 1) || verticalWalls.get(i).get(line)){
						horizontalWalls.get(line).set(i, false);
					}
					else{
						horizontalWalls.get(line).set(i, true);
					}
				}	
			}
			else{
				/**
				 * Get line number
				 */
				line = vIndexes.remove( rand.nextInt(vIndexes.size()) );
				
				/**
				 * Generating.
				 */
				for(int i = 0; i < verticalWalls.get(line).size() ; i++){
					if (horizontalWalls.get(i).get(line - 1) || horizontalWalls.get(i).get(line)){
						verticalWalls.get(line).set(i, false);
					}
					else{
						verticalWalls.get(line).set(i, true);
					}
				}	
			}
		}
	}
}
