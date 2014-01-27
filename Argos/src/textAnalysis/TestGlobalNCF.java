package textAnalysis;

import java.util.ArrayList;
import java.util.HashMap;

import junit.framework.TestCase;
/**
 * 
 * @author John Cummins
 * @version 11.18.2013
 *
 */
public class TestGlobalNCF extends TestCase{

	String article1;
	String article2;
	String article3;
	ArticleNCF ncf1;
	ArticleNCF ncf2;
	ArticleNCF ncf3;
	ArrayList<ArrayList<String>> listOfWordLists;
	ArrayList<HashMap<String, HashMap<String, Float>>> localNCFlist;
	GlobalNCF testGCF;
	HashMap<String, HashMap<String, Float>> cm;
	
	public void setUp(){
		listOfWordLists = new ArrayList<ArrayList<String>>();
		localNCFlist = new ArrayList<HashMap<String, HashMap<String, Float>>>();
		
		article1 = "my name is alex and this is a test";
		article2 = "his my name is rich and he needs to finish the buffer";
		article3 = "brian responds very quickly to messages the";
		
		ncf1 = new ArticleNCF(article1);
		ncf2 = new ArticleNCF(article2);
		ncf3 = new ArticleNCF(article3);
		
		localNCFlist.add(ncf1.getCfSum());
		localNCFlist.add(ncf2.getCfSum());
		localNCFlist.add(ncf3.getCfSum());
	
		
	}
	public void testNumApperances(){
		testGCF = new GlobalNCF(localNCFlist);
		cm = testGCF.getGlobalNCF();
		assertNull(cm.get("my").get("brian"));
		assertNull(cm.get("test").get("buffer"));
		assertNull(cm.get("his").get("brian"));
		assertNull(cm.get("rich").get("very"));
		assertNull(cm.get(" "));
		
		assertEquals(1.0, cm.get("his").get("his"), .001);
		assertEquals(1.0, cm.get("is").get("is"), .001);
		assertEquals(1.0, cm.get("the").get("the"), .001);
		
		assertEquals(1.0, cm.get("my").get("name"), .001);	}
}
