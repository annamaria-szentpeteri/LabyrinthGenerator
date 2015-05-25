/**
 * 
 */
package interfaces;

/**
 * @author Mari
 *
 */
public interface Save {
	/**
	 * The default output name.
	 */
	String FILENAME = "./labyrinth.json";
	
	/**
	 * Saves information to the default file.
	 * @return return value is true if the saving
	 *         process was successfull
	 */
	Boolean SaveToFile();
	
	/**
	 * Saves information to the given file.
	 * 
	 * @param filename
	 * @return return value is true if the saving
	 *         process was successfull
	 */
	Boolean SaveToFile(String filename);
}
