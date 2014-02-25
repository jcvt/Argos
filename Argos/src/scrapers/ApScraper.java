package scrapers;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
/**
 * Pulls articles from AP
 *
 * @author John Cummins
 * @version 09.22.2013
 */
public class ApScraper
    extends AbstractNewsArticleScraper{
    
    public ApScraper(String url){
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
        Elements rawTitle = doc.select("span[class=headline entry-title]");
        return rawTitle.text();
    }

    @Override
    public String date(){
        Elements date = doc.select("div[class=timestamp updated]");
        String rawDate = date.attr("title").toString();
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
    public String publisher(){
        return "AP";
    }

    @Override
    public String snippet(){
        return super.snippet(8);
    }
}
