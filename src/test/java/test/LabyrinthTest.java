package test;


import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import implementation.Labyrinth;


public class LabyrinthTest{
	static Labyrinth labToTestCtor;
	static Labyrinth labToTestSaveLoad;
	static Labyrinth labToTestGetSet;
	
	static ArrayList<Boolean> FullTrue;
	static ArrayList<Boolean> FullFalse;
	
	@Rule
	public static TemporaryFolder tmp = new TemporaryFolder();
	
	@BeforeClass
	public static void beforeTest() throws IOException {
		labToTestGetSet = new Labyrinth(20, 15);
		labToTestSaveLoad = new Labyrinth(17, 23);

		labToTestGetSet.Generate();
		labToTestSaveLoad.Generate();
		
		FullTrue = new ArrayList<Boolean>();
		FullTrue.add(0, true);
		FullTrue.add(1, true);
		FullTrue.add(2, true);
		FullTrue.add(3, true);
		
		FullFalse = new ArrayList<Boolean>();
		FullFalse.add(0, false);
		FullFalse.add(1, false);
		FullFalse.add(2, false);
		FullFalse.add(3, false);
	}
    
    @Test
    public void testConstructors(){
    	assertNotNull("This should make a Labyrinth object with size 1x1", new Labyrinth());
    	assertNotNull("This should make a Labyrinth object with size 3x3", new Labyrinth(3,4));
    	assertNotNull("This should make a Labyrinth object with size 1x1", new Labyrinth(-10, -12));
    }
    
    @Test
    public void testSaveAndLoad() throws IOException{
    	tmp.create();
    	
    	assertTrue("This save should be successfull (Test2.json)", labToTestSaveLoad.SaveToJSON(tmp.getRoot().getPath() + "\\" + "Test2.json"));
    	assertTrue("This save should be successfull (Test2.json)", labToTestSaveLoad.SaveToJSON(tmp.getRoot().getPath() + "\\" + "Test1.json"));
    	
    	assertTrue("This load should be successfull (Test1.json).", labToTestSaveLoad.LoadFromJSON(tmp.getRoot().getPath() + "\\" + "Test1.json"));
    	assertFalse("This load should be unsuccessfull (Random.json).", labToTestSaveLoad.LoadFromJSON("Random.json"));
    	
    	tmp.delete();
    }
    
    @Test
    public void testGetSet(){
    	assertEquals("Weight should be 20.", 20, labToTestGetSet.getWidth());
    	assertEquals("Height should be 15.", 15, labToTestGetSet.getHeight());
    	
    	labToTestGetSet.setHeight(-10);
    	
    	assertEquals("Weight should be 20.", 20, labToTestGetSet.getWidth());
    	assertEquals("Height should be 15.", 15, labToTestGetSet.getHeight());
    	
    	labToTestGetSet.setWidth(0);
    	
    	assertEquals("Weight should be 20.", 20, labToTestGetSet.getWidth());
    	assertEquals("Height should be 15.", 15, labToTestGetSet.getHeight());
    	
    	labToTestGetSet.setHeight(10);
    	
    	assertEquals("Weight should be 20.", 20, labToTestGetSet.getWidth());
    	assertEquals("Height should be 10.", 10, labToTestGetSet.getHeight());
    	
    	labToTestGetSet.setWidth(12);
    	
    	assertEquals("Weight should be 12.", 12, labToTestGetSet.getWidth());
    	assertEquals("Height should be 10.", 10, labToTestGetSet.getHeight());
    	
    	assertNotNull("This should give back a not null object.", labToTestGetSet.getFieldBorders(3, 4));
    	assertNull("This should give back a null object.", labToTestGetSet.getFieldBorders(-1, 4));
    	
    	ArrayList<Boolean> copyTrue = new ArrayList<Boolean>(FullTrue.subList(0, 4));
    	ArrayList<Boolean> copyFalse = new ArrayList<Boolean>(FullFalse.subList(0, 4));
    	
    	labToTestGetSet.setFieldBorders(-2, 3, copyTrue);
    	labToTestGetSet.setFieldBorders(2, 120, copyTrue);
    	
    	labToTestGetSet.setFieldBorders(3, 3, copyTrue);
    	assertArrayEquals("The two arrays should be equal (FullTrue).", FullTrue.toArray(), labToTestGetSet.getFieldBorders(3, 3).toArray());
    	
    	labToTestGetSet.setFieldBorders(3, 3, copyFalse);
    	assertArrayEquals("The two arrays should be equal (FullFalse).", FullFalse.toArray(), labToTestGetSet.getFieldBorders(3, 3).toArray());
    }
}
