package scrapers;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
/**
 * Pulls articles from the Christian Science Monitor
 *
 * @author John Cummins
 * @version 09.23.2013
 */
public class CSMScraper
    extends AbstractNewsArticleScraper{

    public CSMScraper(String url){
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
        return super.title(0, 16);
    }
    @Override
    public String date(){
        Elements date = doc.select("meta[name=sailthru.date]");
        String rawDate = date.attr("content").toString();
        if (rawDate.length() >= 11){
            String month = rawDate.substring(5, 7);
            String year = rawDate.substring(0, 4);
            String day = rawDate.substring(8, 10);
            return year + month + day;
        }
        else{
            return "00000000";
        }
    }
    
    @Override
    public String snippet(){
        return super.snippet(6);
    }

    @Override
    public String publisher(){
        return "The Christian Science Monitor";
    }
}
