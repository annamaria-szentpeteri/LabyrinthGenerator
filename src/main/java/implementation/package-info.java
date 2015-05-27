/**
 * <p>Example of using the Labyrinth class.
 * 
 * <p>With
 *   <code>Labyrinth newone = new Labyrinth();</code>
 * you can create a new 1x1 sized labyrinth. If you want
 * to give the size of it you can use
 *   <code>Labyrinth newone = new Labyrinth(5, 7);</code>
 * which will create a labyrinth with 5 width and 7 height
 * or you can use
 *    <code>Labyrinth newone = new Labyrinth();</code>
 *    <code>newone.setWidth(5);</code>
 *    <code>newone.setHeight(7);</code>
 * to the same purpose.
 * 
 * <p>Please note that if you already created and generated
 * a labyrinth, using <code>setWidth(int w)</code> or
 * <code>setHeight(int h)</code> will delete your labyrinth
 * structure and all generated wall informations will be <b>lost</b>.
 * 
 * <p>If you want to reinitiate your labyrinth you should call
 *    <code>newone.Init()</code>
 * This will recreate your labyrinth, but no wall information will
 * be set. (Means, there will be no walls at all.)
 * 
 * <p>If you want to get a empty labyrinth, which means that only
 * the side walls exist, you should call
 *    <code>newone.Empty()</code>
 * 
 * <p>To generate a labyrinth you should call
 *    <code>newone.Generate()</code>
 * Having a <i>initialized</i>, <i>empty</i> or already <i>filled</i>
 * labyrinth will not influence the generating method.
 * 
 * <p>The used generating algorithm works at the following way:
 * <p>Choose a row or column of wall-line. Choose if you will
 * check before or after the current piece of wall if perpendicularly
 * there is wall already. If <i>yes</i>, don't put wall there, <i>otherwise</i>
 * put wall there, then proceed to the next piece of wall.  
 * 
 *    
 * @author Mari
 *
 */
package implementation;