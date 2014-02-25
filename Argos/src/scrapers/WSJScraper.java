package scrapers;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

// -------------------------------------------------------------------------
/**
 *  A scraper for Wall Street Journal
 *  need to pay to use reprints commercially
 *
 *  @author John Cummins
 *  @version 09.23.2013
 */
public class WSJScraper extends AbstractNewsArticleScraper{
    
    public WSJScraper(String url){
        super.url = url;
        try{
            super.doc = Jsoup.connect(url).get();
        }
        catch (IOException e){
            Thread.currentThread().interrupt();
        }
    }
    
    @Override
    public String title(){
        return super.title(0, 10);
    }

    @Override
    public String date(){
        Elements date = doc.select("li[class=dateStamp]");
        String rawDate = date.text();
        String month = Utils.getMonth(rawDate);
        String year = Utils.getYear(rawDate);
        String day = "00";
        char[] c = rawDate.toCharArray();
        for (int x = 0; x < c.length; x++){
            if (c[x] == ','){
                day = Character.toString(c[x - 2])
                        + Character.toString(c[x - 1]);
                break;
            }
        }
        return year+month+day;
    }
    
    @Override
    public String publisher(){
        return "Wall Street Journal";
    }

    @Override
    public String snippet(){
        return super.snippet(27);
    }
}
