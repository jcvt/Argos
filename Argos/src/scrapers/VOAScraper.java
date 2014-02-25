package scrapers;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

// -------------------------------------------------------------------------
/**
 * Pulls articles from Voice of America
 * http://www.voanews.com/
 *  snippet issues
 *
 *  @author John Cummins
 *  @version 09.19.2013
 */
public class VOAScraper extends AbstractNewsArticleScraper{

    public VOAScraper(String url){
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
        return super.title(0, 0);
    }
    
    @Override
    public String snippet(){
        return null;
        //TODO implement so it pulls a snippet p tags doesn't work
    }
    
    @Override
    public String date(){
        Elements date = doc.select("p[class=article_date]");
        String rawDate = date.text();
        String month = Utils.getMonth(rawDate);
        String year = Utils.getYear(rawDate);
        String day = "00";
        char[] c = rawDate.toCharArray();
        for (int x = 0; x < c.length; x++){
            if (c[x] == ','){
                day = Character.toString(c[x - 2]) + 
                        Character.toString(c[x - 1]);
                break;
            }
        }
        return year + month + day;
    }

    @Override
    public String publisher(){
        return "Voice of America";
    }
}
