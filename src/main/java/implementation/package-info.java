/**
 * <h1>Example of using the Labyrinth class.</h1>
 * 
 * <p>With
 *    <br>&nbsp;&nbsp;&nbsp;<code>Labyrinth newone = new Labyrinth();</code>
 * <br>you can create a new 1x1 sized labyrinth. If you want
 * to give the size of it you can use
 *    <br>&nbsp;&nbsp;&nbsp;<code>Labyrinth newone = new Labyrinth(5, 7);</code>
 * <br>which will create a labyrinth with 5 width and 7 height
 * or you can use
 *    <br>&nbsp;&nbsp;&nbsp;<code>Labyrinth newone = new Labyrinth();</code>
 *    <br>&nbsp;&nbsp;&nbsp;<code>newone.setWidth(5);</code>
 *    <br>&nbsp;&nbsp;&nbsp;<code>newone.setHeight(7);</code>
 * <br>to the same purpose.
 * <br>Note that if you give an invalid size like
 *    <br>&nbsp;&nbsp;&nbsp;<code>Labyrinth newWrongOne = new Labyrinth(-1, -3);</code>
 * <br>and 1x1 labyrinth will be created.
 * 
 * <p>Please note that if you already created and generated
 * a labyrinth, using <code>setWidth(int w)</code> or
 * <code>setHeight(int h)</code> will delete your labyrinth
 * structure and all generated wall informations will be <b>lost</b>.
 * 
 * <p>If you want to reinitiate your labyrinth you should call
 *    <br>&nbsp;&nbsp;&nbsp;<code>newone.Init()</code>
 * <br>This will recreate your labyrinth, but no wall information will
 * be set. (Means, there will be no walls at all.)
 * 
 * <p>If you want to get a empty labyrinth, which means that only
 * the side walls exist, you should call
 *    <br>&nbsp;&nbsp;&nbsp;<code>newone.Empty()</code>
 * 
 * <p>To generate a labyrinth you should call
 *    <br>&nbsp;&nbsp;&nbsp;<code>newone.Generate()</code>
 * <br>Having an <i>initialized</i>, <i>empty</i> or already <i>filled</i>
 * labyrinth will not influence the generating method.
 * 
 * <p>The used generating algorithm works at the following way:
 * Choose a row or column of wall-line. Choose if you will
 * check before or after the current piece of wall if perpendicularly
 * there is wall already. If <i>yes</i>, don't put wall there, <i>otherwise</i>
 * put wall there, then proceed to the next piece of wall.  
 * 
 * <p>The Labyrinth class implements    <code>Load</code> and    <code>Save</code>
 * interfaces, which implementations allows you to save your labyrinth
 * into a JSON file. You can either give a name to your output/input file
 *    <br>&nbsp;&nbsp;&nbsp;<code>newone.SaveToJSON("output.json");</code>
 *    <br>&nbsp;&nbsp;&nbsp;<code>newone.LoadFromJSON("wasSaved");</code>
 * <br>or you can use the default filename given by the interfaces
 *    <br>&nbsp;&nbsp;&nbsp;<code>newone.SaveToJSON();</code>
 *    <br>&nbsp;&nbsp;&nbsp;<code>newone.LoadFromJSON();</code>
 * 
 * <p>All functions give back a boolean value which is true if the
 * operation was successful, and false if an error occured.
 *
 * @author Mari
 *
 */
package implementation;