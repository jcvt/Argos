package NewsMap;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Scrapes BBC RSS and outputs to a text file
 * 
 * @author John Cummins
 * @version 01.26.2014
 */
public class RSSTrial {
	// final static String FILE_NAME = "C:\\Temp\\input.txt";
	final static String OUTPUT_FILE_NAME = "C:\\Development\\RSS\\bbcXML.txt";
	final static Charset ENCODING = StandardCharsets.UTF_8;

	public static void main(String[] args) throws IOException {
		// System.out.print(new
		// BBCRSS("http://feeds.bbci.co.uk/news/world/rss.xml").getArticle());
		HaaretzRSS rss = new HaaretzRSS("http://feeds.feedburner.com/haaretz/LBao");
			for (RssArticle art : rss.getList()){
	            String sql = "INSERT INTO article " +
	                         "VALUES (null,'" +  art.getTitle() + "','" 
	                         + art.getDescription() + "','"
	                         + art.getFullText() + "','"
	                         + art.getDate() + "','"
	                         + art.getPublisher() + "')";
	            System.out.println(sql);
		}
	}
}
