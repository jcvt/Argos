package scrapers;

import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.extractors.ArticleExtractor;
import java.io.IOException;
import java.net.URL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.xml.sax.InputSource;

public class AlfadjrScraperTrial
{

    public static void main(String[] args)
    {
        String text = "No snippet found";
        Document doc;
        try
        {
            URL url = new URL("http://some-page-with-utf8-encodeing.tld");
            InputSource is = new InputSource();
            is.setEncoding("Windows-1256");
            is.setByteStream(url.openStream());
            doc =
                Jsoup.connect(
                    "http://www.al-fadjr.com/ar/international/242389.html").get();
            String html = doc.html();
            text = ArticleExtractor.INSTANCE.getText(is);
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

        // tests to make sure boilerpipe is correctly getting the article
        System.out.println(text);
    }
}
