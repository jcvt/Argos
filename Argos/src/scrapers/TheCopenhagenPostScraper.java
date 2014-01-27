package scrapers;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

// -------------------------------------------------------------------------
/**
 * Scraper for The Copenhagen Post a Denmark news source
 * http://cphpost.dk/
 *
 * @author John Cummins
 * @version 09.18.2013
 */
public class TheCopenhagenPostScraper
    extends AbstractNewsArticleScraper
{

    public TheCopenhagenPostScraper(String url)
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


    public String title()
    {
        return super.title(0, 51);
    }

    @Override
    public String date()
    {
        Elements date = doc.select("div[class=pane-content]");
        String rawDate = date.get(6).text();
        String month = Utils.getMonth(rawDate);
        String year = Utils.getYear(rawDate);
        String day = "00";
        char[] c = rawDate.toCharArray();
        for (int x = 0; x < c.length; x++)
        {
            if (c[x] == ',')
            {
                day =
                    Character.toString(c[x - 2]) + Character.toString(c[x - 1]);
                break;
            }
        }
        return (year + month + day);
    }


    @Override
    public String publisher()
    {
        return "CopenhagenPostScraper";
    }


    @Override
    public String snippet()
    {
        return super.snippet(2);
    }

}
