package scrapers;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

// -------------------------------------------------------------------------
/**
 * Test environment to develop scrapers for specific sites
 *
 * @author John Cummins
 * @version 09.23.2013
 */
public class ScraperTrial
{
    public static void main(String[] args)
    {
        char[] c = new char[30];
        c[1] = 5;
        c[2] =3;
        System.out.println(c.toString());
        Document doc;
        try
        {
            // sets the document to the article url
            doc =
                Jsoup
                    .connect(
                        "http://www.cnn.com/2013/09/26/politics/us-iran/index.html?hpt=hp_inthenews")
                    .get();

            // outputs the articles title
/**
            String rawTitle = doc.title();
            System.out.println("title:" + rawTitle + "\n");

            System.out.println(rawTitle.substring(0, rawTitle.length() -18));
           **/

            /**
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
           System.out.println(rawTitle.substring(0, indexOfDash -1));
**/
            // outputs the beginning of the article using the <p> tags
            Elements paragraphs = doc.select("p");

            // prints out the whole text for analysis
            System.out.println(paragraphs.text());
            System.out.println("");
            if (paragraphs.size() > 5)
            {
                for (int x = 0; x < 1; x++)
                {
                    System.out.println(paragraphs.get(x).text());
                }
            }
            // outputs the date

            Elements date = doc.select("div[class=section]");
            System.out.println("\n Issue Date: \n" + date.text());
            String rawDate = date.text();


            String month = Utils.getMonth(rawDate);
            String year = Utils.getYear(rawDate);
            String day = "00";

            /*char[] c = rawDate.toCharArray();
            for (int x = 0; x < c.length; x++)
            {
                if (c[x] == ',')
                {
                    day =
                        Character.toString(c[x + -2])
                            + Character.toString(c[x -1]);
                    break;
                }
            }

            System.out.println(year + " " + month + " " + day);
*/

        }

        catch (IOException e)
        {
            e.printStackTrace();

        }
    }
}
