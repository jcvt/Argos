package scrapers;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

// -------------------------------------------------------------------------
/**
 * Scrapes info from ProugePost a Czech Republic news source
 *  http://www.praguepost.com/
 *
 * @author John Cummins
 * @version 09.18.2013
 */
public class ProugePostScraper
    extends AbstractNewsArticleScraper
{

    public ProugePostScraper(String url)
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
    /**
     * starts from the beginning of the title and search for the first -
     * then calls substring to chop of the front of the title
     * -1 is to remove whitespace
     * @return String the modified title
     */
    public String title()
    {
        String rawTitle = doc.title();
        int indexOfDash = 0;
        char[] c = rawTitle.toCharArray();
        for (int x = 0; x < rawTitle.length(); x++)
        {
            if(c[x] =='-')
            {
                indexOfDash = x;
                break;
            }
        }
       return rawTitle.substring(0, indexOfDash -1);
    }

    @Override
    public String date()
    {
        Elements date = doc.select("[class=posted]");
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
                    Character.toString(c[x - 2]) + Character.toString(c[x - 1]);
                break;
            }
        }
        return (year + month + day);
    }


    @Override
    public String snippet()
    {
        return super.snippet(7);
    }


    @Override
    public String publisher()
    {
        return "ProugePost";
    }

}
