package scrapers;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

// -------------------------------------------------------------------------
/**
 *  Some test code to try out the Al-fadjr Scraper
 *
 *
 *  @author John Cummins
 *  @version 09.11.2013
 */
//TODO Boilerpipe out put is weird
public class AlfadjrScraper
{
    public static void main(String[] args)
    {

        Document doc;
        try
        {
            // sets the document to the article url
            doc =
                Jsoup.connect(
                    "").get();

            // outputs the articles title
            //String title = doc.title();
            //System.out.println("title:" + title + "\n");

            // outputs the beginning of the article using the <p> tags
            Elements paragraphs = doc.select("p");
            System.out.println(paragraphs.text());
            for (int x = 0; x < 10; x++)
            {
                System.out.println(paragraphs.get(x).text());
            }

            // outputs the date

            Elements date = doc.select("div[class=date]");
            System.out.println("\n Issue Date: \n"
                + date.text());

        }
        catch (IOException e)
        {
            e.printStackTrace();

        }
    }
}
