package scrapers;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
/**
 * Pulls artilces from Haaretz 
 *
 * @author John Cummins
 * @version 2013.09.11
 */
public class HaaretzScraper
    extends AbstractNewsArticleScraper{

    /**
     * @param url the url of the desired article
     */
    public HaaretzScraper(String url){
        super.url = url;
        try{
            doc = Jsoup.connect(url).get();
        }
        catch (IOException e){
            Thread.currentThread().interrupt();
        }
    }

    @Override
    /**
     * searches for the index of a - then removes the end of the string
     * until that point plus one more char for a whitespace
     */
    public String title(){
        String rawTitle = doc.title();
        int indexOfDash = 0;
        char[] c = rawTitle.toCharArray();
        for (int x = rawTitle.length() - 1; x > 0; x--){
            if(c[x] =='-'){
                indexOfDash = rawTitle.length() - x;
                break;
            }
        }
        return rawTitle.substring(0, rawTitle.length() - indexOfDash + 1);
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
    /**
     * the publisher of the article
     * @return String Haaretz
     */
    public String publisher(){
        return ("Haaretz");
    }


    @Override
    public String snippet(){
        return super.snippet(0);
    }

}
