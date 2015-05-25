/**
 * 
 */
package interfaces;

/**
 * @author Mari
 *
 */
public interface Load {
	/**
	 * The default input name.
	 */
	String FILENAME = "./labyrinth.json";

	/**
	 * Loads information from the default
	 * file.
	 * 
	 * @return return value is true if the loading
	 *         process was successfull
	 */
	Boolean LoadFromFile();
	
	/**
	 * Loads information from the given
	 * file.
	 * 
	 * @param filename name of the file which will be
	 *                 processed
	 * @return return value is true if the loading
	 *         process was successfull
	 */
	Boolean LoadFromFile(String filename);
}
