package scrapers;

import org.jsoup.nodes.Element;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

// -------------------------------------------------------------------------
/**
 * Just some test code to try and scrape Asian times.
 * need written permission to republish their stuff
 *
 *  @author John Cummins
 *  @version 9.10.2013
 */
public class AsianTimesScraper
{
    public static void main(String[] args)
    {

        Document doc;
        try
        {
            //sets the document to the article url
            doc = Jsoup.connect(
                "http://www.atimes.com/atimes/South_Asia/SOU-02-050913.html").get();

            //outputs the articles title
            String title = doc.title();
            System.out.println("title:" + title + "\n");

            //outputs the beginning of the article using the <p> tags
            Elements paragraphs = doc.select("table");
            for (Element e: paragraphs)
            {
                System.out.println(e.text());
            }
            /**
            for (int x = 0; x < 10; x++)
            {
                System.out.println(paragraphs.get(x));
            }
    **/
            //outputs the date
            Elements date = doc.select("meta[name=issuedate]");
            System.out.println("Issue Date: \n" + date.attr("content").toString());






        }
        catch (IOException e)
        {
            e.printStackTrace();

        }
    }
}
