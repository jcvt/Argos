package scrapers;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

// -------------------------------------------------------------------------
/**
 * Scraper for Aljazeera
 *
 * @author John Cummins
 * @version 09.27.2013
 */
public class AljazeeraScraper
    extends AbstractNewsArticleScraper
{

    public AljazeeraScraper(String url)
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
        return super.title(0, 21);
    }

    @Override
    public String date()
    {
        Elements date = doc.select("span[class=date]");
        String rawDate = date.text();
        String year = Utils.getYear(rawDate);
        String month = Utils.getMonth(rawDate);
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
        return year + month + day;
    }


    @Override
    public String publisher()
    {
        return ("Aljazeera");
    }


    @Override
    public String snippet()
    {
        return super.snippet(0);
    }

}
