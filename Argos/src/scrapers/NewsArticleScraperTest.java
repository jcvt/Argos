package scrapers;

import junit.framework.TestCase;


// -------------------------------------------------------------------------
/**
 * A test class for all of the scrapers tests to make sure they are behaving as
 * expected
 *
 * @author John Cummins
 * @version 09.27.2013
 */

public class NewsArticleScraperTest
    extends TestCase
{
    private AljazeeraScraper         aljazeeraTest         =
                                                               new AljazeeraScraper(
                                                                   "http://america.aljazeera.com/articles/2013/9/27/pacific-island-nationstounclimatechangeishere.html");
    private ApScraper                apTest                =
                                                               new ApScraper(
                                                                   "http://hosted.ap.org/dynamic/stories/U/UN_UNITED_NATIONS_IRAN?SITE=AP&SECTION=HOME&TEMPLATE=DEFAULT&CTIME=2013-09-27-13-04-08");
    private BbcScraper               bbcTest               =
                                                               new BbcScraper(
                                                                   "http://www.bbc.co.uk/news/world-africa-24306648");
    private BloombergScraper         bloombergTest         =
                                                               new BloombergScraper(
                                                                   "http://www.bloomberg.com/news/2013-09-27/house-without-plan-as-senate-poised-to-pass-spending-bill.html");
    private CNNScraper               cnnTest               =
                                                               new CNNScraper(
                                                                   "http://www.cnn.com/2013/09/26/politics/us-iran/index.html?hpt=hp_inthenews");
    private CSMScraper               csmTest               =
                                                               new CSMScraper(
                                                                   "http://www.csmonitor.com/World/Security-Watch/2013/0927/Iran-delivers-on-promises-for-a-new-tone-at-UN.-Results-to-follow");
    private EstonianFreePressScraper efpTest               =
                                                               new EstonianFreePressScraper(
                                                                   "http://estonianfreepress.com/2013/04/eesti-pank-warns-about-emigration-and-labor-market/");
    private HaaretzScraper           haaretzTest           =
                                                               new HaaretzScraper(
                                                                   "http://www.haaretz.com/news/middle-east/1.549269");
    private NYTScraper               nytTest               =
                                                               new NYTScraper(
                                                                   "http://www.nytimes.com/2013/09/28/science/global-climate-change-report.html?hp&_r=0");
    private ObserverScraper          observerTest          =
                                                               new ObserverScraper(
                                                                   "http://observer.ug/index.php?option=com_content&view=article&id=27716:fdc-set-for-new-elections&catid=78:topstories&Itemid=116");
    private PatrioticVanguardScraper patrioticvangaurdTest =
                                                               new PatrioticVanguardScraper(
                                                                   "http://www.thepatrioticvanguard.com/spip.php?article7384");
    private ProugePostScraper        prougepostTest        =
                                                               new ProugePostScraper(
                                                                   "http://www.praguepost.com/news/17181-friday-news-briefing.html");
    private ReutersScraper           reutersTest          =
                                                               new ReutersScraper(
                                                                   "http://www.reuters.com/article/2013/09/27/us-un-assembly-syria-idUSBRE98Q0Z820130927");
    private TheCopenhagenPostScraper thecopenhagenpostTest =
                                                               new TheCopenhagenPostScraper(
                                                                   "http://cphpost.dk/eu/deal-eurosceptics-could-stave-eu-patent-referendum");
    private TimesOfZambiaScraper     timesofzambiaTest     =
                                                               new TimesOfZambiaScraper(
                                                                   "http://www.times.co.zm/?p=34599");
    private VOAScraper               voaTest               =
                                                               new VOAScraper(
                                                                   "http://www.voanews.com/content/iran-holds-constructive-meeting-with-nuclear-watchdog/1758311.html");
    private WSJScraper               wsjTest               =
                                                               new WSJScraper(
                                                                   "http://online.wsj.com/article/SB10001424052702303796404579098302895368992.html?mod=WSJ_World_LEFTSecondNews");


    public void setUp()
    {
        //TODO probably don't need this
    }


    /**
     * makes sure the publisher method is returning a string related to the
     * publisher
     */
    public void testPublisher()
    {
        assertTrue(aljazeeraTest.publisher().contains("Aljazeera"));
        assertTrue(apTest.publisher().contains("AP"));
        assertTrue(bbcTest.publisher().contains("BBC"));
        assertTrue(bloombergTest.publisher().contains("Bloomberg"));
        assertTrue(cnnTest.publisher().contains("CNN"));
        assertTrue(csmTest.publisher().contains("Christian"));
        assertTrue(efpTest.publisher().contains("Estonian"));
        assertTrue(haaretzTest.publisher().contains("Haaretz"));
        assertTrue(nytTest.publisher().contains("New"));
        assertTrue(observerTest.publisher().contains("Observer"));
        assertTrue(patrioticvangaurdTest.publisher().contains("Patriotic"));
        assertTrue(prougepostTest.publisher().contains("Prouge"));
        assertTrue(reutersTest.publisher().contains("Reuters"));
        assertTrue(thecopenhagenpostTest.publisher().contains("Copenhagen"));
        assertTrue(timesofzambiaTest.publisher().contains("Zambia"));
        assertTrue(voaTest.publisher().contains("Voice"));
        assertTrue(wsjTest.publisher().contains("Wall"));

    }

    /**
     *  makes sure the title isn't whitespace (the default of the title method)
     *  and doesn't throw an error
     */
    public void testTitle()
    {
        assertFalse(aljazeeraTest.title().equals(" "));
        assertFalse(apTest.title().equals(" "));
        assertFalse(bbcTest.title().equals(" "));
        assertFalse(bloombergTest.title().equals(" "));
        assertFalse(cnnTest.title().equals(" "));
        assertFalse(csmTest.title().equals(" "));
        assertFalse(efpTest.title().equals(" "));
        assertFalse(haaretzTest.title().equals(" "));
        assertFalse(nytTest.title().equals(" "));
        assertFalse(observerTest.title().equals(" "));
        assertFalse(patrioticvangaurdTest.title().equals(" "));
        assertFalse(prougepostTest.title().equals(" "));
        assertFalse(reutersTest.title().equals(" "));
        assertFalse(thecopenhagenpostTest.title().equals(" "));
        assertFalse(timesofzambiaTest.title().equals(" "));
        assertFalse(voaTest.title().equals(" "));
        assertFalse(wsjTest.title().equals(" "));
    }

    /**
     * checks for abnormal characters white space and blank dates and the
     * correct length
     *
     * @return boolean true if the date is in the right format
     * @param date
     *            the date to be checked
     */
    private boolean dateChecker(String date)
    {
        // TODO improve by using regex? add more characters? check for letters?
        if (date.length() != 8 || date.contains(" ") || date.contains("|")
            || date.contains(",") || date.contains("-")
            || date.substring(0, 2).equals("00")
            || date.substring(2, 4).equals("00")
            || date.substring(4).equals("0000"))
        {
            return false;
        }
        return true;
    }

    /**
     * uses the date checker method to see if the dates are in the desired
     * format
     */
    public void testDate()
    {
        assertTrue(dateChecker(aljazeeraTest.date()));
        assertTrue(dateChecker(apTest.date()));
        assertTrue(dateChecker(bbcTest.date()));
        assertTrue(dateChecker(bloombergTest.date()));
        assertTrue(dateChecker(cnnTest.date()));
        assertTrue(dateChecker(csmTest.date()));
        assertTrue(dateChecker(efpTest.date()));
        assertTrue(dateChecker(haaretzTest.date()));
        assertTrue(dateChecker(nytTest.date()));
        assertTrue(dateChecker(observerTest.date()));
        assertTrue(dateChecker(patrioticvangaurdTest.date()));
        assertTrue(dateChecker(prougepostTest.date()));
        assertTrue(dateChecker(reutersTest.date()));
        assertTrue(dateChecker(thecopenhagenpostTest.date()));
        assertTrue(dateChecker(timesofzambiaTest.date()));
        assertTrue(dateChecker(voaTest.date()));
        assertTrue(dateChecker(wsjTest.date()));

    }

    /**
     * makes sure boilerpipe is returning at least some text for
     * analysis  ("No text Found" is the default)
     */
    public void testText()
    {
        assertFalse(aljazeeraTest.text().equals("No Text Found"));
        assertFalse(apTest.text().equals("No Text Found"));
        assertFalse(bbcTest.text().equals("No Text Found"));
        assertFalse(bloombergTest.text().equals("No Text Found"));
        assertFalse(cnnTest.text().equals("No Text Found"));
        assertFalse(csmTest.text().equals("No Text Found"));
        assertFalse(efpTest.text().equals("No Text Found"));
        assertFalse(haaretzTest.text().equals("No Text Found"));
        assertFalse(nytTest.text().equals("No Text Found"));
        assertFalse(observerTest.text().equals("No Text Found"));
        assertFalse(patrioticvangaurdTest.text().equals("No Text Found"));
        assertFalse(prougepostTest.text().equals("No Text Found"));
        assertFalse(reutersTest.text().equals("No Text Found"));
        assertFalse(thecopenhagenpostTest.text().equals("No Text Found"));
        assertFalse(timesofzambiaTest.text().equals("No Text Found"));
        assertFalse(voaTest.text().equals("No Text Found"));
        assertFalse(wsjTest.text().equals("No Text Found"));
    }


}
