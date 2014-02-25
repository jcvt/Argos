package scrapers;

import de.l3s.boilerpipe.BoilerpipeProcessingException;
import java.net.MalformedURLException;
import java.net.URL;
import de.l3s.boilerpipe.extractors.ArticleExtractor;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

// -------------------------------------------------------------------------
/**
 * An abstract super class for all the other scrapers in addition to the
 * abstract methods it contains helpful methods such as title(int,int) and
 * snippet(int) to implement title() and snippet() respectively
 *
 * @author John Cummins
 * @version 09.23.2013
 */
public abstract class AbstractNewsArticleScraper
{
    protected String   url;
    protected Document doc;
    protected String   title;
    protected String   text;
    protected String   snippet;


    /**
     * scrapes the News articles title
     *
     * @return String the title of the article
     */
    public abstract String title();


    /**
     * aim is to remove extraneous info in the 
     * title calls substring on the raw title
     *
     * @param begin the number of digits to be removed from the beginning
     * @param end the number of digits to be removed from the end
     * @return String the modified title
     */
    protected String title(int begin, int end){
        String rawTitle = doc.title();
        if (rawTitle.length() > begin && rawTitle.length() > end){
            return rawTitle.substring(0 + begin, rawTitle.length() - end);
        }
        else{
            return " ";
        }
    }


    /**
     * scrapes the date in the form yyyymmdd
     *
     * @return String date the article was published
     */
    public abstract String date();


    /**
     * Scrapes the whole text of the article
     *
     * @return String the text of the article
     */
    public String text()
    {
        text = "No Text Found";
        try{
            text = ArticleExtractor.INSTANCE.getText(doc.html());
        }
        catch (BoilerpipeProcessingException e){
            Thread.currentThread().interrupt();
        }
        return text;
    }


    /**
     * scrapes a snippet of the article using the p tags
     * @return String the first few sentences of the article
     */
    public abstract String snippet();


    /**
     * returns the news articles publisher
     *
     * @return String the publisher of the article
     */
    public abstract String publisher();


    /**
     * returns the url of the article
     * @return String the url of the article
     */
    public String getUrl()
    {
        return url;
    }


    // TODO this method of finding the snippet is inefficient because p tags change
    protected String snippet(int y){
        snippet = "";
        Elements paragraphs = doc.select("p");
        if (paragraphs.size() > y + 10)
            for (int x = y; x < y + 10; x++){
                snippet += paragraphs.get(x).text();
            }
        return snippet;
    }
}
