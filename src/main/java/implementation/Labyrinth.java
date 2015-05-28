package implementation;

import fileactions.Load;
import fileactions.Save;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonArray;

/**
 * <p>This class is the responsible for creating and 
 * handling labyrinths. With this class you are
 * able to create a random labyrinth with the 
 * sizes given by you.
 * 
 * <p>You are also able to save and load the labyrinth into JSON, 
 * because of the implementation of <code>Save</code> and 
 * <code>Load</code> interfaces.
 * 
 * <p>The used algorithm will generate a labyrinth which every
 * part can be reached from every field of it. There are no 
 * separeted parts.
 * 
 * @author Szentpéteri Annamária
 */
public class Labyrinth implements Save, Load {
	/** Height of the labyrinth. */
	private Integer height;
	/** Width of the labyrinth. */
	private Integer width;
	/** Array which stores the horizontal positioned walls. */
	private ArrayList<BitSet> horizontalWalls;
	/** Array which stores the vertical positioned walls. */
	private ArrayList<BitSet> verticalWalls;

	/** For logging purposes. */
	private final static Logger logger = LoggerFactory.getLogger(Labyrinth.class);
	
//-------------------------------------------------------------------
	/** Creates an initial labyrinth with the size 1x1. */
	public Labyrinth(){
		this(1, 1);
		
		logger.info("Default constructor has been called.");
	}
	
	/**
	 * Creates an initial labyrinth with the given size.
	 * 
	 * @param w width of labyrinth
	 * @param h height of labyrinth
	 */
	public Labyrinth(int w, int h){
		logger.info("Constructor called with " + w + " and " + h + " values.");
		
		/** 
		 * Setting the labyrinth's sizes while checking
		 * if they are correct.
		 */
		if (w > 0)
			width = w;
		else
			width = 1;
		
		if (h > 0)
			height = h;
		else
			height = 1;
		
		/** Creating the labyrinth in the given size. */
		Init();
		
		/** Creating an empty labyrinth. */
		Empty();
		
		logger.info("Constructor done.");
	}

//-------------------------------------------------------------------
	
	/**
	 * Returns the height of the labyrinth.
	 * 
	 * @return height of the labyrinth
	 */
	public int getHeight(){
		logger.info("Height asked.");
		
		return height;
	}
	
	/**
	 * Returns the width of the labyrinth.
	 * 
	 * @return width of the labyrinth
	 */
	public int getWidth(){
		logger.info("Width asked.");
		
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
		logger.info("getFieldBorders: start");
		logger.info("Field borders asked with " + x + " and " + y + " values.");
		
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
			result.add(verticalWalls.get(x + 1).get(y));
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			
			result = null;
		}
		
		logger.info("getFieldBorders: end");
		
 		return result;
	}

//-------------------------------------------------------------------
	
	/**
	 * Sets the height of the labyrinth.
	 * 
	 * @param h the given height
	 */
	public void setHeight(int h){
		logger.info("Setting height.");
		
		if (h > 0){
			height = h;
		
			Init();
			Empty();
			
			logger.info("Height setted.");
		}
	}
	
	/**
	 * Sets the width of the labyrinth.
	 * 
	 * @param w the given width
	 */
	public void setWidth(int w){
		logger.info("Setting width.");
		
		if (w > 0){
			width = w;
			
			Init();
			Empty();
			
			logger.info("Width setted.");
		}
	}
	
	/**
	 * Sets the field's borders by the given information.
	 * 
	 * Gets coordinates of a field and an array with information
	 * about the borders in this strict order: top,left,bottom,right
	 * 
	 * @param x horizontal coordinate of the field
	 * @param y vertical coordinate of the field
	 * @param borders a [top, left, bottom, right] array which contains
	 *                information about the field's borders
	 */
	public void setFieldBorders(int x, int y, ArrayList<Boolean> borders){
		logger.info("setFieldBorders: start");
		
		if ((x >= 0) && (y >= 0) && (x < width) && (y < height)){			
			/**
			 * Set top border.
			 */
			horizontalWalls.get(y).set(x, borders.get(0));
			/**
			 * Set left border.
			 */
			verticalWalls.get(x).set(y, borders.get(1));
			/**
			 * Set bottom border.
			 */
			horizontalWalls.get(y + 1).set(x, borders.get(2));
			/**
			 * Set right border.
			 */
			verticalWalls.get(x + 1).set(y, borders.get(3));
			
			logger.info("Filed borders set.");
		}
		else{
			logger.error("Indexes out of border. Set nothing.");
		}
		
		logger.info("setFieldBorders: end");
	}

//-------------------------------------------------------------------

	/**
	 * <p>Initialize the labyrinth.
	 * 
	 * <p>It's a completely raw structure, no wall information
	 * will be set here.
	 * 
	 * <p>This method is only suitable to set the correct sizes.
	 */
	public void Init(){		
		logger.info("Init: start");
		
		horizontalWalls = new ArrayList<BitSet>();
		verticalWalls = new ArrayList<BitSet>();
		
		for(int i = 0; i < height + 1; i++){
			horizontalWalls.add(new BitSet(width));
		}
		for(int i = 0; i < width + 1; i++){
			verticalWalls.add(new BitSet(height));
		}
		
		logger.info("Init: end");
	}
	
	/**
	 * <p>Clears the labyrinth.
	 * 
	 * <p>Creates the borders of the labyrinth and 
	 * deletes the inner walls making a clean "room".
	 */
	public void Empty(){
		logger.info("Empty: start");
		
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
		int bsLength = 0;
		
		/** 
		 * Top side of the labyrinth. 
		 */
		bsLength = horizontalWalls.get(0).size();
		horizontalWalls.get(0).flip(0, bsLength);
		
		/** 
		 * Bottom side of the labyrinth. 
		 */
		bsLength = horizontalWalls.get(horizontalWalls.size() - 1).size();
		horizontalWalls.get(horizontalWalls.size() - 1).flip(0, bsLength);
		
		/**
		 *  Left side of the labyrinth. 
		 */
		bsLength = verticalWalls.get(0).size();
		verticalWalls.get(0).flip(0, bsLength);
		
		/**
		 * Right side of the labyrinth. 
		 */
		bsLength = verticalWalls.get(verticalWalls.size() - 1).size();
		verticalWalls.get(verticalWalls.size() - 1).flip(0, bsLength);
		
		logger.info("Empty: end");
	}
	
	
	/**
	 * <p>Generates a random labyrinth.
	 * 
	 * <p>First, it empties the labyrinth, then
	 * makes a new one by random values.
	 */
	public void Generate(){
		logger.info("Generate: start");
		
		/**
		 * First needs a clean up to empty the labyrinth. 
		 */
		Empty();
		
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
			hORv = rand.nextInt(4);
			
			/**
			 * If one of the arrays is empty, it's fixed that which
			 * side needs to be chosen.
			 */
			if (hIndexes.isEmpty())
				hORv = rand.nextInt(2) + 2;
			if (vIndexes.isEmpty())
				hORv = rand.nextInt(2);
			
			/** 
			 * If hORv is 0 or 1 then generates a horizontal line.
			 * If it is 2 or 3 it's generates a vertical line.
			 * The number of line is choosed by random numbers too.
			 * 
			 * The generating method is:
			 * 	- check if perpendicularly have a border "behind" (hORv = 0 in 
			 *    the for cycle) or "before" (hORv = 1 in the for cycle) the 
			 *    current line
			 * 	     - yes: don't put wall there
			 *       - no: put wall there
			 */
			if ((hORv == 0) || (hORv == 1)){
				logger.info("Generating horizontal line.");
				
				/**
				 * Get line number
				 */
				line = hIndexes.remove( rand.nextInt(hIndexes.size()) );
				
				/**
				 * Generating.
				 */
				for(int i = 0; i < width ; i++){
					if (verticalWalls.get(i + hORv).get(line - 1) || verticalWalls.get(i + hORv).get(line))
						horizontalWalls.get(line).set(i, false);
					else
						horizontalWalls.get(line).set(i, true);
				}	
			}
			else{
				logger.info("Generating vertical line.");
				
				hORv -= 2;
				
				/**
				 * Get line number
				 */
				line = vIndexes.remove( rand.nextInt(vIndexes.size()) );
				
				/**
				 * Generating.
				 */
				for(int i = 0; i < height ; i++){
					if (horizontalWalls.get(i + hORv).get(line - 1) || horizontalWalls.get(i + hORv).get(line))
						verticalWalls.get(line).set(i, false);
					else
						verticalWalls.get(line).set(i, true);
				}
			}
		}
		
		logger.info("Generate: end");
	}

//-------------------------------------------------------------------
	/**
	 * Implementation of <code>Load</code> interface.
	 * 
	 * @return return value is true if the loading
	 *         process was successfull
	 * @see fileactions.Load#LoadFromJSON()
	 */
	public Boolean LoadFromJSON() {
		logger.info("Default loader called.");
		
		return LoadFromJSON(Load.FILENAME);
	}

	/**
	 * Implementation of <code>Save</code> interface.
	 * 
	 * @return return value is true if the saving
	 *         process was successfull      
	 * @see fileactions.Save#SaveToJSON()
	 */
	public Boolean SaveToJSON() {
		logger.info("Default saver called");
		
		return SaveToJSON(Save.FILENAME);		
	}
	
	/**
	 * Implementation of <code>Load</code> interface.
	 * 
	 * @param filename name of the file which will be
	 *                 processed
	 * @return return value is true if the loading
	 *         process was successfull
	 * @see fileactions.Load#LoadFromJSON(java.lang.String)
	 */
	public Boolean LoadFromJSON(String filename) {
		logger.info("LoadFromFile: start");
		
		Boolean done = false;
		
		/**
		 * Reading the informations of the labyrinth from the
		 * given file. It should be a json.
		 */
		try (InputStream input = new FileInputStream(filename);){
			Reader in = new BufferedReader(new InputStreamReader(input));
			
			JsonObject jo = JsonObject.readFrom(in);
			
			this.setHeight( jo.get("height").asInt() );
			this.setWidth( jo.get("width").asInt() );
			
			this.Init();
			
			JsonArray horizontal = jo.get("horizontal").asArray();
			JsonArray vertical = jo.get("vertical").asArray();
			
			for(Integer i = 0; i < (height + 1); i++){
				String value = horizontal.get(i).asObject().get(i.toString()).asString();
				
				for(int j = 0; j < width; j++){
					if (value.charAt(j) == '1')
						horizontalWalls.get(i).set(j);
					else
						horizontalWalls.get(i).clear(j);
				}
			}
			
			for(Integer i = 0; i < (width + 1); i++){
				String value = vertical.get(i).asObject().get(i.toString()).asString();
				
				for(int j = 0; j < height; j++){
					if (value.charAt(j) == '1')
						verticalWalls.get(i).set(j);
					else
						verticalWalls.get(i).clear(j);
				}
			}
			
			done = true;
			
			in.close();
			input.close();
			
			logger.info("Loaded successfully.");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		logger.info("LoadFromFile: end");
		
		return done;
	}

	/**
	 * Implementation of <code>Save</code> interface.
	 * @param filename name of the file which will be
	 *                 processed
	 * @return return value is true if the saving
	 *         process was successfull    
	 * @see fileactions.Save#SaveToJSON(java.lang.String)
	 */
	public Boolean SaveToJSON(String filename) {
		logger.info("SaveToFile: start");
		
		Boolean done = false;
		
		/**
		 * Saving the informations of the labyrinth to the
		 * given file. The output will be in json format.
		 */
		try (OutputStream output = new FileOutputStream(filename);){
			Writer out = new BufferedWriter(new OutputStreamWriter(output));
			
			JsonObject jo = new JsonObject();
			
			jo.add("width", width);
			jo.add("height", height);
			
			JsonArray horizontal = new JsonArray();
			JsonArray vertical = new JsonArray();
			
			for(Integer i = 0; i < horizontalWalls.size(); i++){
				String id = i.toString();
				String value = "";
				
				for(int j = 0; j < width; j++){
					if (horizontalWalls.get(i).get(j))
						value = value.concat("1");
					else
						value = value.concat("0");
				}
				
				horizontal.add( new JsonObject().add(id, value) );
			}
			
			for(Integer i = 0; i < verticalWalls.size(); i++){
				String id = i.toString();
				String value = "";
				
				for(int j = 0; j < height; j++){
					if (verticalWalls.get(i).get(j))
						value = value.concat("1");
					else
						value = value.concat("0");
				}
				
				vertical.add( new JsonObject().add(id, value) );
			}
			
			jo.add("horizontal", horizontal);
			jo.add("vertical", vertical);
			
			jo.writeTo(out);
			
			done = true;
			
			out.close();
			output.close();
			
			logger.info("Saved successfully.");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}	
		
		logger.info("SaveToFile: end");
		
		return done;
	}
}
