/**
 * <h1>Example of using the {@link implementation.Labyrinth} class.</h1>
 * 
 * <p>With
 *    <pre>
 *    Labyrinth newone = new Labyrinth();</pre>
 * you can create a new 1x1 sized labyrinth. If you want
 * to give the size of it you can use
 *    <pre>
 *    Labyrinth newone = new Labyrinth(5, 7);</pre>
 * which will create a labyrinth with 5 width and 7 height
 * or you can use
 *    <pre>
 *    Labyrinth newone = new Labyrinth();
 *    newone.setWidth(5);
 *    newone.setHeight(7);</pre>
 * to the same purpose.
 * <p>Note that if you give an invalid size like
 *    <pre>
 *    Labyrinth newWrongOne = new Labyrinth(-1, -3);</pre>
 * a 1x1 labyrinth will be created.
 * 
 * <p>Please note that if you already created and generated
 * a labyrinth, using <code>setWidth(int w)</code> or
 * <code>setHeight(int h)</code> will delete your labyrinth
 * structure and all generated wall informations will be <b>lost</b>.
 * 
 * <p>If you want to reinitiate your labyrinth you should call
 *    <pre>
 *    newone.Init();</pre>
 * This will recreate your labyrinth, but no wall information will
 * be set. (Means, there will be no walls at all.)
 * 
 * <p>If you want to get a empty labyrinth, which means that only
 * the side walls exist, you should call
 *    <pre>
 *    newone.Empty();</pre>
 * 
 * <p>To generate a labyrinth you should call
 *    <pre>
 *    newone.Generate();</pre>
 * Having an <i>initialized</i>, <i>empty</i> or already <i>filled</i>
 * labyrinth will not influence the generating method.
 * 
 * <p>The used generating algorithm works at the following way:
 * Choose a row or column of wall-line. Choose if you will
 * check before or after the current piece of wall if perpendicularly
 * there is wall already. If <i>yes</i>, don't put wall there, <i>otherwise</i>
 * put wall there, then proceed to the next piece of wall.  
 * 
 * <p>The Labyrinth class implements {@link fileactions.Load} and {@link fileactions.Save}
 * interfaces, which implementations allows you to save your labyrinth
 * into a JSON file. You can either give a name to your output/input file
 *    <pre>
 *    newone.SaveToJSON("output.json");
 *    newone.LoadFromJSON("wasSaved");</pre>
 * or you can use the default filename given by the interfaces
 *    <pre>
 *    newone.SaveToJSON();
 *    newone.LoadFromJSON();</pre>
 * 
 * <p>All functions give back a boolean value which is true if the
 * operation was successful, and false if an error occured.
 *
 * @author Mari
 *
 */
package implementation;