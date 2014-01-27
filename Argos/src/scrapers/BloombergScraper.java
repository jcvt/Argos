package scrapers;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

// -------------------------------------------------------------------------
/**
 *  Scraper for Bloomberg
 *
 *  @author John Cummins
 *  @version 09.23.2013
 */
public class BloombergScraper extends AbstractNewsArticleScraper
{

    public BloombergScraper(String url)
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
        return super.title(0, 12);
    }

    @Override
    public String date()
    {
        Elements date = doc.select("meta[name=pubdate]");
        String rawDate = date.attr("content").toString();
        String month = rawDate.substring(5,7);
        String year = rawDate.substring(0,4);
        String day = rawDate.substring(8,10);
        return (year + month + day);
    }

    @Override
    public String publisher()
    {
        return "Bloomberg";
    }

    @Override
    public String snippet()
    {
        return super.snippet(1);
    }

}
