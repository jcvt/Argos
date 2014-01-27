package crawlers;

import java.net.MalformedURLException;
import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.extractors.ArticleExtractor;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.url.WebURL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Pattern;

// -------------------------------------------------------------------------
/**
 * Web Crawler to index websites.
 *
 * @author Rich Episcopo
 * @version Sep 11, 2013
 */
public class MyCrawler
    extends WebCrawler
{

    private static final Pattern FILTERS =
                                             Pattern
                                                 .compile(".*(\\.(css|js|bmp|gif|jpe?g"
                                                     + "|png|tiff?|mid|mp2|mp3|mp4"
                                                     + "|wav|avi|mov|mpeg|ram|m4v|pdf"
                                                     + "|rm|smil|wmv|swf|wma|zip|rar|gz))$");


    // ----------------------------------------------------------
    /**
     * Create a new MyCrawler object.
     */
    public MyCrawler()
    {
        // do nothing, use Controller to run.
    }


    /**
     * You should implement this function to specify whether the given url
     * should be crawled or not (based on your crawling logic).
     */
    @Override
    public boolean shouldVisit(WebURL url)
    {
        String href = url.getURL().toLowerCase();

        return !FILTERS.matcher(href).matches()
            && href.startsWith("http://www.cnn.com/");
    }


    /**
     * This function is called when a page is fetched and ready to be processed
     * by your program.
     */
    @Override
    public void visit(Page page)
    {
        String url = page.getWebURL().getURL();

        if (isArticle(url))
        {
            try
            {
                String text = ArticleExtractor.INSTANCE.getText(new URL(url));
                System.out.println();
            }
            catch (BoilerpipeProcessingException e)
            {
                e.printStackTrace();
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }

            System.out.println("URL: " + url);
        }
    }


// ----------------------------------------------------------
    /**
     * Returns true if URL is pointing to an article, false otherwise.
     *
     * @param text
     *            The HTML code.
     * @return true if URL is pointing to an article, false if not or a
     *         connection cannot be made.
     * @throws BoilerpipeProcessingException
     * @throws IOException
     */
    private boolean isArticle(String url)
    {
        try
        {
            String htmlSource = getUrlSource(url);

            // Now ignoring article with gallery
            // assumption that articles that contain a gallery are
            // not "content heavy" therefore are not needed
            return htmlSource.contains("content=\"article\"")
                && !htmlSource.contains("template_type_content: \"gallery\"");
        }
        catch (IOException e)
        {
            return false;
        }
    }


    // ----------------------------------------------------------
    /**
     * Returns the HTML source of a website.
     *
     * @param url
     *            The url of the website.
     * @return The html source of the url page.
     * @throws IOException
     *             If connection cannot be established.
     */
    private String getUrlSource(String url)
        throws IOException
    {
        URL yahoo = new URL(url);
        URLConnection yc = yahoo.openConnection();
        BufferedReader in =
            new BufferedReader(new InputStreamReader(
                yc.getInputStream(),
                "UTF-8"));
        String inputLine;
        StringBuilder a = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
            a.append(inputLine);
        in.close();

        return a.toString();
    }
}
