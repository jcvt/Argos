package NewsMap;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 * Scrapes BBC RSS for title, description and, date published
 * Save everything into a string 
 * 
 * @author John Cummins
 * @version 01.26.2014
 */
public class BBCRSS {
	private Document doc;
	private static Elements titles;
	private static Elements articles;
	private static Elements dates;
	public BBCRSS(String url) {
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			Thread.currentThread().interrupt();
		}
		titles = doc.select("item title");
		articles = doc.select("item description");
		dates = doc.select("item pubDate");
	}
	public String getArticle(){
		String out = "";
		if (titles.size() != articles.size() || articles.size() != dates.size()){
			return "Error titles dates articles don't match";
		}
		for (int x = 0; x < articles.size(); x++){
			out += titles.get(x).text() + "\n";
			out += articles.get(x).text() + "\n";
			out += dates.get(x).text() + "\n";
			out += "\n";
		}
		return out;
	}
	
	public String getArticleXML(){
		String out = "";
		if (titles.size() != articles.size() || articles.size() != dates.size()){
			return "Error titles dates articles don't match";
		}
		for (int x = 0; x < articles.size(); x++){
			out += titles.get(x) + "\n";
			out += articles.get(x) + "\n";
			out += dates.get(x) + "\n";
			out += "\n";
		}
		return out;
	}
	final static String OUTPUT_FILE_NAME = "C:\\Development\\RSS\\bbcXML.txt";
	final static Charset ENCODING = StandardCharsets.UTF_8;

	public static void main(String[] args) throws IOException {
		Path path = Paths.get(OUTPUT_FILE_NAME);
		BBCRSS rss = new BBCRSS("http://feeds.bbci.co.uk/news/world/rss.xml");
		try (BufferedWriter writer = Files.newBufferedWriter(path, ENCODING)) {
			for (int x = 0; x < articles.size(); x++){
				writer.write(titles.get(x) + "\n");
				writer.newLine();
				writer.write(articles.get(x) + "\n");
				writer.newLine();
				writer.write(dates.get(x) + "\n");
				writer.newLine();
				writer.newLine();
			}
		}
	}
}
