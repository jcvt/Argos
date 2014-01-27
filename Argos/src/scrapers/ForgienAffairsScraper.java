package scrapers;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Just some test code to figure out how to scrape forgienaffairs.com
 *
 * Looks like maybe foreign affairs denies access to their web site?
 * getting a 403 error
 *
 *
 * @author John Cummins
 * @version 2013.09.10
 */
public class ForgienAffairsScraper
{
    public static void main(String[] args)
    {

        Document doc;
        try
        {
            //sets the document to the article url
            doc = Jsoup.connect(
                "http://www.foreignaffairs.com/articles/139907/david-runciman/how-david-cameron-saved-the-special-relationship").get();

            //outputs the articles title
            String title = doc.title();
            System.out.println("title:" + title + "\n");

            //outputs the beginning of the article using the <p> tags
            Elements paragraphs = doc.select("p");
            for (int x = 0; x < 10; x++)
            {
                System.out.println(paragraphs.get(x));
            }

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
