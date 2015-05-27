package fileactions;

/**
 * @author Szentpéteri Annamária
 * 
 * <p>Interface which gives the methods
 * to load your class from a file.
 */
public interface Load {
	/** The default input name. */
	String FILENAME = "./default.json";

	/**
	 * Loads information from the default
	 * file.
	 * 
	 * @return return value is true if the loading
	 *         process was successfull
	 */
	Boolean LoadFromJSON();
	
	/**
	 * Loads information from the given
	 * file.
	 * 
	 * @param filename name of the file which will be
	 *                 processed
	 * @return return value is true if the loading
	 *         process was successfull
	 */
	Boolean LoadFromJSON(String filename);
}
