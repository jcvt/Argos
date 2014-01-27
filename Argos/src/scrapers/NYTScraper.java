package scrapers;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

// -------------------------------------------------------------------------
/**
 * Scraper for the New York Times
 * might run into issues because you only get 10 articles per month
 * i think this is stored in cookies though
 * @author John Cummins
 * @version 09.23.2013
 */
public class NYTScraper
    extends AbstractNewsArticleScraper
{
    public NYTScraper(String url)
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
        String rawTitle = doc.title();
        return rawTitle.substring(0, rawTitle.length() - 14);
    }
    @Override
    public String snippet()
    {
        return super.snippet(4);
    }
    @Override
    public String date()
    {
        Elements date = doc.select("meta[itemprop=dateModified]");
        String rawDate = date.attr("content").toString();
        String month = rawDate.substring(5, 7);
        String year = rawDate.substring(0, 4);
        String day = rawDate.substring(8, 10);
        return year + month + day;
    }


    @Override
    public String publisher()
    {
        return "New York Times";
    }

}
