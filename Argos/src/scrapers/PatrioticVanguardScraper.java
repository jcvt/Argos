package scrapers;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

// -------------------------------------------------------------------------
/**
 *  Scraper for http://www.thepatrioticvanguard.com/
 *  I think its a Vancouver based news source that focuses on Africa
 *  The snippet needs work because the articles have less than 10 p tags
 *
 *  @author John Cummins
 *  @version 09.25.2013
 */
public class PatrioticVanguardScraper extends AbstractNewsArticleScraper
{

    public PatrioticVanguardScraper(String url)
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
        return super.title(0, 25);
    }

    @Override
    public String date()
    {
        Elements date = doc.select("div[style=font-size:smaller;]");
        String rawDate = date.get(0).text();
        String month = Utils.getMonth(rawDate);
        String year = Utils.getYear(rawDate);
        String day = "00";
        char[] c = rawDate.toCharArray();
        for (int x = 0; x < c.length; x++)
        {
            if (c[x] == 'y')
            {
                day =
                    Character.toString(c[x + 2])
                        + Character.toString(c[x + 3]);
                break;
            }
        }
        return year+month+day;
    }

    @Override
    public String snippet()
    {
        //TODO articles are too short?
        Elements paragraphs = doc.select("p");
        String out = "";
        if (paragraphs.size() > 5)
        {
            for (int x = 0; x < 5; x++)
            {
                out += paragraphs.get(x).text();
            }
        }
        return out;
    }

    @Override
    public String publisher()
    {
        return "The Patriotic Vanguard";
    }

}
