package NewsMap;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
		BBCRSS rss = new BBCRSS("http://feeds.bbci.co.uk/news/world/rss.xml");
		Path path = Paths.get(OUTPUT_FILE_NAME);
		try (BufferedWriter writer = Files.newBufferedWriter(path, ENCODING)) {
			writer.write(rss.getArticleXML());
			writer.newLine();
		}
	}
}
