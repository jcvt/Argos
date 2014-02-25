package NewsMap;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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
	private ArrayList<RssArticle> list;
	public BBCRSS(String url) {
	    list = new ArrayList<RssArticle>();
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			Thread.currentThread().interrupt();
		}
		titles = doc.select("item title");
		articles = doc.select("item description");
		dates = doc.select("item pubDate");
		this.populateList();
	}
	private void populateList(){
		if (titles.size() == articles.size() && articles.size() == dates.size()){
		    for (int x = 0; x < articles.size(); x++){
		        RssArticle article = new RssArticle(
		            titles.get(x).text(), articles.get(x).text(), 
		            titles.get(x).text() + " " +articles.get(x).text(), 
		            processDate(dates.get(x).text()), "BBC");
		        list.add(article);
		    }
		}
	}
	
	private String processDate(String date){
	    String day = date.substring(5, 7);
	    String month = Utils.getMonth(date);
	    String year = Utils.getYear(date);
	    return year + "-" + month + "-" + day;
	}
	
	public ArrayList<RssArticle> getList(){
	    return list;
	}
	/*public String getArticleXML(){
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
	}*/
	
	/*final static String OUTPUT_FILE_NAME = "C:\\Development\\RSS\\bbcXML.txt";
	final static Charset ENCODING = StandardCharsets.UTF_8;*/

	/*public static void main(String[] args) throws IOException {
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
	}*/
}
