package scrapers;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

// -------------------------------------------------------------------------
/**
 * Scrapes for the CNN website
 *
 * @author John Cummins
 * @version 09.19.2013
 */
public class CNNScraper
    extends AbstractNewsArticleScraper
{
    public CNNScraper(String url)
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
        return super.title(0, 10);
    }


    public String snippet()
    {
        return super.snippet(1);
    }


    @Override
    public String date()
    {
        Elements date = doc.select("meta[http-equiv=last-modified]");
        String rawDate = date.attr("content").toString();
        if (rawDate.length() >= 11)
        {
            String month = rawDate.substring(5, 7);
            String year = rawDate.substring(0, 4);
            String day = rawDate.substring(8, 10);
            return year + month + day;
        }
        return "00000000";

    }


    @Override
    public String publisher()
    {
        return "CNN";
    }

}
