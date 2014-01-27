package scrapers;

import org.jsoup.select.Elements;
import java.io.IOException;
import org.jsoup.Jsoup;

// -------------------------------------------------------------------------
/**
 * Scraper for the bbc website
 *
 * @author John Cummins
 * @version 09.11.2013
 */
public class BbcScraper
    extends AbstractNewsArticleScraper
{

    public BbcScraper(String url)
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
        return super.title(11, 0);
    }

    public String date()
    {
        Elements date = doc.select("meta[name=OriginalPublicationDate]");
        String rawDate = date.attr("content").toString();
        String year = rawDate.substring(0, 4);
        String month = rawDate.substring(5, 7);
        String day = rawDate.substring(8, 10);
        return (year + month + day);
    }

    public String publisher()
    {
        return ("BBC");
    }


    public String snippet()
    {
        return super.snippet(3);
    }
}
