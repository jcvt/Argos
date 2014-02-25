package textAnalysis;
import java.util.ArrayList;
import java.util.HashMap;

import junit.framework.TestCase;
/**
 * @author John Cummins
 * @version 11.12.2013
 */
public class TestArticleNCF extends TestCase{
	String article; 
	String article2;
	String article3;
	
	ArticleNCF testNCF;
	ArticleNCF testNCF2;
	ArticleNCF testNCF3;
	ArrayList<String> wordList;
	HashMap<String, HashMap<String, Float>> cfSum;
	
	public void setUp(){
			article = "PCW chemical weapons experts have been in Syria since 1 October The worlds chemical weapons watchdog has said it will send a second team of inspectors to boost its programme to destroy Syrias stockpile.The Organisation for the Prohibition of Chemical Weapons  said it had made a constructive beginning to a long and difficult process. "
			+ "However, it did not say how many extra inspectors would be deployed, or when. Under a UN resolution, Syrias chemical weapons "
			+ "mixing and filling equipment must be destroyed by 1 November. In a statement OPCW chief Ahmet Uzumcu also said that the Syrian government had submitted additional information updating its initial disclosure, which would help the OPCW plan its future activities. "
			+ "Mr Uzumcu was delivering his first report to the 41 nation OPCW executive council. "
			+ "The BBCs Anna Holligan in the Hague says this report is significant because the content is based on evidence gathered by the OPCW experts on the ground in Damascus. "
			+ "They were part of an advance party sent out to hold talks with the Syrian authorities and smooth the way for the destruction process to begin, our correspondent says. "
			+ "On Sunday members of the joint OPCW and UN mission observed Syrian staff using cutting torches and angle grinders to destroy missile warheads, aerial bombs, and mixing equipment.";
			
			
			article = article.replaceAll("\\p{Punct}","").toLowerCase();
			
			article2 = "hello my name is alex and this is a test";
			testNCF = new ArticleNCF(article2.toCharArray());
			testNCF2 = new ArticleNCF(article.toCharArray());
			wordList =  testNCF.getWordList();
			cfSum = testNCF.getCfSum();
	}
	
	public void testCorrelationMatrix(){
		assertEquals(1.0, cfSum.get("test").get("test"), .001);
		assertEquals(1.0, cfSum.get("alex").get("alex"), .001);
		assertEquals(1.0, cfSum.get("is").get("is"), .001);
		assertEquals(1.0, cfSum.get("hello").get("hello"), .001);
	}
	
	public void testWordList(){
		assertTrue(wordList.contains("hello"));
		assertTrue(wordList.contains("alex"));
		assertTrue(wordList.contains("name"));
		assertTrue(wordList.contains("test"));
	}

}
