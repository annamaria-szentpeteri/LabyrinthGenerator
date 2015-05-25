/**
 * 
 */
package implementation;

import interfaces.Load;
import interfaces.Save;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Random;

import com.eclipsesource.json.*;

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
public class Labyrinth implements Save, Load {
	/** Height of the labyrinth. */
	private Integer height;
	/** Width of the labyrinth. */
	private Integer width;
	/** Array which stores the horizontal positioned walls. */
	private ArrayList<BitSet> horizontalWalls;
	/** Array which stores the vertical positioned walls. */
	private ArrayList<BitSet> verticalWalls;

//-------------------------------------------------------------------
	/** Creates an initial labyrinth with the size 1x1. */
	public Labyrinth(){
		this(1, 1);
	}
	
	/**
	 * Creates an initial labyrinth with the given size.
	 * 
	 * @param w width of labyrinth
	 * @param h height of labyrinth
	 */
	public Labyrinth(int w, int h){
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
			
			/** Add top border. */
			result.add(horizontalWalls.get(y).get(x));
			/** Add left border. */
			result.add(verticalWalls.get(x).get(y));
			/** Add bottom border. */
			result.add(horizontalWalls.get(y + 1).get(x));
			/** Add right border. */
			result.add(verticalWalls.get(x + 1).get(y));
			
		} catch (Exception e) {
			// TODO loggba
			e.printStackTrace(); 
			result = null;
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
		if (h > 0){
			height = h;
			
			Init();
			Empty();
		}
	}
	
	/**
	 * Sets the width of the labyrinth
	 * 
	 * @param w the given width
	 */
	public void setWidth(int w){
		if (w > 0){
			width = w;
			
			Init();
			Empty();
		}
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
			/** Set top border. */
			horizontalWalls.get(y).set(x, borders.remove(0));
			/** Set left border. */
			verticalWalls.get(x).set(y, borders.remove(0));
			/** Set bottom border. */
			horizontalWalls.get(y + 1).set(x, borders.remove(0));
			/** Set right border. */
			verticalWalls.get(x + 1).set(y, borders.remove(0));
			
		} catch (Exception e) {
			//TODO loggba
			e.printStackTrace(); 
		}
	}

//-------------------------------------------------------------------

	/**
	 * Initialize the labyrinth.
	 * It's a completely raw structure, no wall information
	 * will be set here.
	 */
	public void Init(){		
		horizontalWalls = new ArrayList<BitSet>();
		verticalWalls = new ArrayList<BitSet>();
		
		for(int i = 0; i < height + 1; i++){
			horizontalWalls.add(new BitSet(width));
		}
		for(int i = 0; i < width + 1; i++){
			verticalWalls.add(new BitSet(height));
		}
	}
	
	/**
	 * Clears the labyrinth. Creates the borders of the
	 * labyrinth and deletes the inner walls making a
	 * clean "room".
	 */
	public void Empty(){
		/** Deleting all horizontal walls. */
		for(BitSet bs: horizontalWalls){
			bs.clear();
		}
		
		/** Deleting all vertical walls. */
		for(BitSet bs: verticalWalls){
			bs.clear();
		}
		
		/** Setting the borders of the labyrinth. */
		int bsLength = 0;
		
		/** Top side of the labyrinth. */
		bsLength = horizontalWalls.get(0).size();
		horizontalWalls.get(0).flip(0, bsLength);
		
		/** Bottom side of the labyrinth. */
		bsLength = horizontalWalls.get(horizontalWalls.size() - 1).size();
		horizontalWalls.get(horizontalWalls.size() - 1).flip(0, bsLength);
		
		/** Left side of the labyrinth. */
		bsLength = verticalWalls.get(0).size();
		verticalWalls.get(0).flip(0, bsLength);
		
		/** Right side of the labyrinth. */
		bsLength = verticalWalls.get(verticalWalls.size() - 1).size();
		verticalWalls.get(verticalWalls.size() - 1).flip(0, bsLength);
	}
	
	
	/**
	 * Generates a random labyrinth.
	 * First, it's cleans the labyrinth, then
	 * makes a new labyrinth randomly.
	 */
	public void Generate(){
		/** First needs a clean up to empty the labyrinth. */
		Empty();
		
		/** Random number generator to randomize the generating method. */
		Random rand = new Random();
		
		/**
		 * Vertical or horizontal walls will be generated
		 * depending on this variable. 
		 */
		Integer hORv;
		
		/** Decides that which line will be filled with walls. */
		Integer line;
		
		/** Arrays to handle which lines were filled and which wasn't. */
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
				/** Get line number */
				line = hIndexes.remove( rand.nextInt(hIndexes.size()) );
				
				/** Generating. */
				for(int i = 0; i < width ; i++){
					if (verticalWalls.get(i + hORv).get(line - 1) || verticalWalls.get(i + hORv).get(line))
						horizontalWalls.get(line).set(i, false);
					else
						horizontalWalls.get(line).set(i, true);
				}	
			}
			else{
				hORv -= 2;
				
				/** Get line number */
				line = vIndexes.remove( rand.nextInt(vIndexes.size()) );
				
				/** Generating. */
				for(int i = 0; i < height ; i++){
					if (horizontalWalls.get(i + hORv).get(line - 1) || horizontalWalls.get(i + hORv).get(line))
						verticalWalls.get(line).set(i, false);
					else
						verticalWalls.get(line).set(i, true);
				}
			}
		}
	}

//-------------------------------------------------------------------
	/* (non-Javadoc)
	 * @see interfaces.Load#LoadFromFile()
	 */
	public Boolean LoadFromFile() {
		return LoadFromFile(Load.FILENAME);
	}

	/* (non-Javadoc)
	 * @see interfaces.Save#SaveToFile()
	 */
	public Boolean SaveToFile() {
		return SaveToFile(Save.FILENAME);		
	}
	
	/* (non-Javadoc)
	 * @see interfaces.Load#LoadFromFile(java.lang.String)
	 */
	public Boolean LoadFromFile(String filename) {
		Boolean done = false;
		
		try (InputStream input = new FileInputStream(filename);){
			Reader in = new BufferedReader(new InputStreamReader(input));
			
			JsonObject jo = JsonObject.readFrom(in);
			
			this.setHeight( jo.get("height").asInt() );
			this.setWidth( jo.get("width").asInt() );
			
			Init();
			
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
		} catch (IOException e) {
			// TODO loggba		
			e.printStackTrace();
		}
		
		return done;
	}

	/* (non-Javadoc)
	 * @see interfaces.Save#SaveToFile(java.lang.String)
	 */
	public Boolean SaveToFile(String filename) {
		Boolean done = false;
		
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
		} catch (Exception e) {
			// TODO loggba
			e.printStackTrace();
		}	
		
		return done;
	}
}
