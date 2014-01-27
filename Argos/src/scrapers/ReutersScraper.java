package scrapers;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

// -------------------------------------------------------------------------
/**
 *  Scraper for Reuters News Source
 *
 *  @author John Cummins
 *  @version 09.22.2013
 */
public class ReutersScraper extends AbstractNewsArticleScraper
{
    public ReutersScraper(String url)
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
    @Override
    public String snippet()
    {
        return super.snippet(9);
    }
    @Override
    public String date()
    {
        Elements date = doc.select("META[name=REVISION_DATE]");
        String rawDate = date.attr("content").toString();
        String month = Utils.getMonth(rawDate);
        String year = Utils.getYear(rawDate);
        String day = rawDate.substring(8, 10);
        return year+month+day;
    }

    @Override
    public String publisher()
    {
        return "Reuters";
    }

}