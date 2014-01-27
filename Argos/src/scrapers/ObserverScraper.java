package scrapers;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

// -------------------------------------------------------------------------
/**
 * A scraper for the Ugandan news source The Observer Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 * observer.ug
 *
 * @author John Cummins
 * @version 09.18.2013
 */
public class ObserverScraper
    extends AbstractNewsArticleScraper
{

    public ObserverScraper(String url)
    {
        super.url = url;
        try
        {
            super.doc = Jsoup.connect(url).get();
        }
        catch (IOException e)
        {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public String title()
    {
        return super.title(15, 0);
    }
    @Override
    public String date()
    {
        Elements date = doc.select("div[class=date]");
        String rawDate = date.text();
        String month = Utils.getMonth(rawDate);
        String year = Utils.getYear(rawDate);
        String day = "00";
        char[] c = rawDate.toCharArray();
        for (int x = 0; x < c.length; x++)
        {
            if (c[x] == ',')
            {
                day =
                    Character.toString(c[x + 2]) + Character.toString(c[x + 3]);
                break;
            }
        }
        return year + month + day;
    }


    @Override
    /**
     * scrapes a snippet of the article using the p tags
     * @return String the first few sentences of the article
     */
    public String snippet()
    {
        return super.snippet(1);
    }


    @Override
    public String publisher()
    {
        return "The Observer";
    }

}
