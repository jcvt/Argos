package scrapers;

import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.extractors.ArticleExtractor;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.IOException;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;

// -------------------------------------------------------------------------
/**
 * Tests the functionality of getting html using jsoup then parisng into
 * boierpipe
 *
 * @author John Cummins
 * @version 09.12.2013
 */
public class JsoupToBoilerPipe
{
    public static void main(String[] args)
    {
        String text = "No snippet found";
        Document doc;
        try
        {
            doc =
                Jsoup.connect(
                    "http://www.al-fadjr.com/ar/international/242389.html")
                    .get();
            String html = doc.html();
            text = ArticleExtractor.INSTANCE.getText(html);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (BoilerpipeProcessingException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //tests to make sure boilerpipe is correctly getting the article
        System.out.println(text);
    }
}
