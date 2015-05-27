package fileactions;

/**
 * @author Szentpéteri Annamária
 * 
 * <p>Interface which gives the methods
 * to save your class to a file.
 */
public interface Save {
	/** The default output name. */
	String FILENAME = "./default.json";
	
	/**
	 * Saves information to the default file.
	 * 
	 * @return return value is true if the saving
	 *         process was successfull
	 */
	Boolean SaveToJSON();
	
	/**
	 * Saves information to the given file.
	 * 
	 * @param filename name of the file which will be
	 *                 processed
	 * @return return value is true if the saving
	 *         process was successfull
	 */
	Boolean SaveToJSON(String filename);
}
