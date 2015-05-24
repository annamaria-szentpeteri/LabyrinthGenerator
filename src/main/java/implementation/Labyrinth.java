/**
 * 
 */
package implementation;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
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
	
	
	/**
	 * Creates an initial labyrinth with the given size.
	 * 
	 * @param x width of labyrinth
	 * @param y height of labyrinth
	 */
	public Labyrinth(int x, int y){
		/** Setting the labyrinth's sizes. */
		width = x;
		height = y;
		
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
	
	/**
	 * @return height of the labyrinth
	 */
	public int getHeight(){
		return height;
	}
	
	/**
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
	 * @return a [top, left, bottom, right] array which members are either 0 (no border) or 1 (border)
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
			 */
			if (hORv == 0){
				/**
				 * Get line number
				 */
				line = hIndexes.remove( rand.nextInt(hIndexes.size()) );
				
				/**
				 * Menj végig a kiválasztott vonalon, de hogy?
				 *  - ha "mögötte" fal van másik irányú falból, akkor hagyja ki
				 *    azt, ahol most tart
				 *  - ha nincs "mögötte" íly módon fel, akkor rakjon falat 
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
				 * Menj végig a kiválasztott vonalon, de hogy?
				 *  - ha "mögötte" fal van másik irányú falból, akkor hagyja ki
				 *    azt, ahol most tart
				 *  - ha nincs "mögötte" íly módon fel, akkor rakjon falat 
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
