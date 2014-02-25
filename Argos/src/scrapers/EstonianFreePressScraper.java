package scrapers;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
/**
 * pulls articles from Estonian Free Press an Estonian news source
 *
 * @author John Cummins
 * @version 09.18.2013
 */
public class EstonianFreePressScraper
    extends AbstractNewsArticleScraper{

    public EstonianFreePressScraper(String url){
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
        return super.title(0, 22);
    }
    
    @Override
    public String date(){
        Elements date = doc.select("span[class=date]");
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
        return "Estonian Free Press";
    }

    @Override
    public String snippet(){
        return super.snippet(0);
    }
}
