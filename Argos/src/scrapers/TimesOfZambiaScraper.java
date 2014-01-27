package scrapers;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

// -------------------------------------------------------------------------
/**
 * Scraper for the Times of Zambia
 * snippet needs work less than 10 p tags
 *  http://www.times.co.zm/
 *
 *  @author John Cummins
 *  @version 09.25.2013
 */
public class TimesOfZambiaScraper extends AbstractNewsArticleScraper
{

    public TimesOfZambiaScraper(String url)
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
        return super.title(0, 18);
    }

    @Override
    public String date()
    {
        Elements date = doc.select("div[class=section]");
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
                    Character.toString(c[x + -2])
                        + Character.toString(c[x -1]);
                break;
            }
        }
        return year+month+day;
    }

    @Override
    public String snippet()
    {
        //TODO Again not a lot of p tags use boilerpipe?
        return null;
    }

    @Override
    public String publisher()
    {
        return "Times of Zambia";
    }

}
