package textAnalysis;

import junit.framework.TestCase;
/**
 * @author John Cummins
 * @version 11.12.2013
 */
public class TestStemStop extends TestCase
{
    String test1;
    String test2;
    String test3;
    String test4;
    String test5;
    String test6;
    String article1;
    /**
     * sets up the test environment ie char[] for testing
     */
    public void setUp(){
        test1 = "it should remove all of the test stop words from this the ";
        test2 = "stemming the string should running           rapidly walking the";
        test3 = " known start stemming the string known running walking stemming";
        test4 = "stemming the string should running playful walking faster went space stemming ";
        test5 = " stemming the strings should running fastest walking white space both stemming ";
        test6 = "word";
        

    }

    /**
     * make sure all stop words like the are being removed
     * and all words are being stemmed
     */
    public void testStop(){
    	//TODO test one has whitespace at the end of it
    	System.out.println(new String(Utils.processText(test1.toCharArray())));
    	System.out.println(new String(Utils.processText(test2.toCharArray())));
    	System.out.println(new String(Utils.processText(test3.toCharArray())));
    	System.out.println(new String(Utils.processText(test4.toCharArray())));
    	System.out.println(new String(Utils.processText(test5.toCharArray())));
    	System.out.println(new String(Utils.processText(test6.toCharArray())));
        assertFalse(new String(Utils.processText(test1.toCharArray())).contains("the"));
        assertFalse(new String(Utils.processText(test2.toCharArray())).contains("the"));
        assertFalse(new String(Utils.processText(test3.toCharArray())).contains("known"));
        assertFalse(new String(Utils.processText(test4.toCharArray())).contains("went"));
        assertFalse(new String(Utils.processText(test5.toCharArray())).contains("yes"));
        
        
    }
    
}
