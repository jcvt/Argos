package parsers;
import info.bliki.wiki.dump.IArticleFilter;
import info.bliki.wiki.dump.Siteinfo;
import info.bliki.wiki.dump.WikiArticle;

import org.xml.sax.SAXException;

import junit.framework.TestCase;
/**
 * A structured test environment to ensure that the data
 * dump is being correctly processed for analysis
 * 
 * @author John Cummins
 * @version 11.13.2013 
 */
public class TestRegEx extends TestCase{

	/**
     * Print title an content of all the wiki pages in the dump.
     */
    static class DemoArticleFilter
        implements IArticleFilter
    {
        public void process(WikiArticle page, Siteinfo siteinfo)
            throws SAXException
        {
            System.out.println("----------------------------------------");

            if (page.isMain())
            {
                String superText = page.getText();
                int length = superText.length();
                System.out.print(length);
                System.out.println(superText.replaceAll("[t][h][e]", "RRRRRR"));

/*
 * .replaceAll("\\[\\[.+\\]\\]", "")
 * .replaceAll("\\{\\{.+\\}\\}", "")
 * .replaceAll("(?m)^==.+?\n", "")
 * .replaceAll("\\{.+\\}", "") 
 * .replaceAll("(?m)^(\\s){0, 2}\\|.+?\n", "")
 * .replaceAll("\\(.+\\)", "").replaceAll("\\[.+\\]", "")
 * .replaceAll("<ref.+?</ref>", "")
 * .replaceAll("<!--.+-->", "").replaceAll("\\p{P}", "")
 * .replaceAll("\\p{P}", "")
 * .trim());
 * */


            }

        }
    }
}
