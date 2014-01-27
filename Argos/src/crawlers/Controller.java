package crawlers;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

// -------------------------------------------------------------------------
/**
 * The controller for the WebCrawler called MyCrawler.
 *
 * @author Rich Episcopo
 * @version Sep 11, 2013
 */
public class Controller
{

    // ----------------------------------------------------------
    /**
     * Runs the web crawler.
     *
     * @param args
     *            Arguments
     * @throws Exception
     *             If something goes wrong.
     */
    public static void main(String[] args)
        throws Exception
    {
        String crawlStorageFolder = "/data/crawl/root";
        int numberOfCrawlers = 8;

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);
        // config.setMaxDepthOfCrawling(100);

        /*
         * Instantiate the controller for this crawl.
         */
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer =
            new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller =
            new CrawlController(config, pageFetcher, robotstxtServer);

        /*
         * For each crawl, you need to add some seed urls. These are the first
         * URLs that are fetched and then the crawler starts following links
         * which are found in these pages
         */
        controller.addSeed("http://www.cnn.com/");

        /*
         * Start the crawl. This is a blocking operation, meaning that your code
         * will reach the line after this only when crawling is finished.
         */
        controller.start(MyCrawler.class, numberOfCrawlers);
    }
}
