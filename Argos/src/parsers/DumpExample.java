package parsers;

import info.bliki.wiki.dump.IArticleFilter;
import info.bliki.wiki.dump.Siteinfo;
import info.bliki.wiki.dump.WikiArticle;
import info.bliki.wiki.dump.WikiXMLParser;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.xml.sax.SAXException;

import textAnalysis.ArticleNCF;
import textAnalysis.CorrelationMatrix;
import textAnalysis.GlobalNCF;
import textAnalysis.Utils;

/**
 * 
 * Test code 
 * 
 * Saves a small correlation matrix to a file
 * 
 * Demo application which reads a compressed or uncompressed Wikipedia XML dump
 * file (depending on the given file extension <i>.gz</i>, <i>.bz2</i> or
 * <i>.xml</i>) and prints the title and wiki text.
 */
public class DumpExample {

	/*private ArrayList<String> articles;*/
	ArrayList<HashMap<String, HashMap<String, Float>>> localNCFlist;
	private final int START_ARTICLE = 101;
	private final int STOP_ARTICLE = 200;
	private int counter = 0;
	private int counter2 = 0;
	/**
	 * Creates a new DumpExample object.
	 */
	public DumpExample(String file) {
		localNCFlist = new ArrayList<HashMap<String, HashMap<String, Float>>>();
		/*articles = new ArrayList<String>();*/
		populateList(file);
	}

	private void populateList(String file) {

		String args[] = new String[1];
		args[0] = file;
		notMain(args);
	}

	private void addToList(String s) {
		if (s.length() > 20){
			if (counter2 > START_ARTICLE && counter2 <= STOP_ARTICLE) {
				this.localNCFlist.add(new ArticleNCF(Utils.processText(s.toCharArray())).getCfSum());
			}
			counter2++;
		}
		if (counter2 == STOP_ARTICLE){
			GlobalNCF GCF = new GlobalNCF(this.localNCFlist);
			CorrelationMatrix CM = new CorrelationMatrix(GCF.getGlobalNCF());
			try{
	            FileOutputStream fileOut =
	                new FileOutputStream("C:/Development/CorrelationMatrix2.ser");
	            ObjectOutputStream out = new ObjectOutputStream(fileOut);
	            out.writeObject(CM);
	            out.close();
	            fileOut.close();
	            System.out.printf("Serialized data is saved in C:/Development/CorrelationMatrix2.ser");
	        }
	        catch (IOException i){
	            i.printStackTrace();
	        }
		}
		if (counter2 % 10 == 0){
			System.out.println(counter2/10);
		}
	}

	/**
	 * Print title an content of all the wiki pages in the dump.
	 */
	class DemoArticleFilter implements IArticleFilter {
		public void process(WikiArticle page, Siteinfo siteinfo)
				throws SAXException {
			// System.out.println("----------------------------------------");

			if (page.isMain()) {
				String superText = page.getText();

				addToList(superText
						.replaceAll(
								"\\[\\[.+\\]\\]|(\\{\\{.+\\}\\})|((?m)^=="
										+ ".+?\n)|(\\[.+\\])|(\\(.+\\))|(<ref.+?</ref>)"
										+ "|(<!--.+-->)|(\\d)|(<.*>)", "")
						.replaceAll("\\p{P}", "").replaceAll("(\n)", " ")
						.replaceAll("[\\|,=]", "").toLowerCase().trim());

				// System.out
				// .println(superText
				// .replaceAll(
				// "\\[\\[.+\\]\\]|(\\{\\{.+\\}\\})|((?m)^=="
				// + ".+?\n)|(\\[.+\\])|(\\(.+\\))|(<ref.+?</ref>)"
				// + "|(<!--.+-->)|(\\d)|(<.*>)",
				// "").replaceAll("\\p{P}", "")
				// .replaceAll("(\n)", " ")
				// .replaceAll("[\\|,=]", "").toLowerCase().trim());

				/*
				 * RegEx that need to be matched .replaceAll("\\?", "") ?
				 */
				/*
				 * works? (\\d) (<.*>) (\\[.+\\]) ((?m)^==.+?\n)
				 * (\\{\\{.+\\}\\})
				 */
				/*
				 * works .replaceAll("\\[\\[.+\\]\\]", "")
				 * .replaceAll("\\{\\{.+\\}\\}", "") .replaceAll("(?m)^==.+?\n",
				 * "") .replaceAll("\\[.+\\]", "") .replaceAll("\\(.+\\)", "")
				 * .replaceAll("<ref.+?</ref>", "") .replaceAll("\\p{P}", "")
				 * .replaceAll("<!--.+-->","") .replaceAll("\\p{Punct}","")
				 * .replaceAll("\\d","") .replaceAll("<.*>", "")
				 * .replaceAll("\n", " ") .replaceAll("[\\|,=]", "")
				 */

				/*
				 * chain of ones that work .replaceAll("\\[\\[.+\\]\\]",
				 * "").replaceAll("\\{\\{.+\\}\\}",
				 * "").replaceAll("(?m)^==.+?\n", "").replaceAll("\\[.+\\]",
				 * "").replaceAll("\\(.+\\)", "").replaceAll("<ref.+?</ref>",
				 * "")
				 * .replaceAll("<!--.+-->","").replaceAll("\\d","").replaceAll
				 * ("<.*>", "").replaceAll("\\p{P}", "").replaceAll("\n",
				 * " ").replaceAll("[\\|,=]", "").toLowerCase()
				 */
			}

		}
	}

	/*public ArrayList<String> getArticles() {
		return this.articles;
	}*/
	public ArrayList<HashMap<String, HashMap<String, Float>>> getNFCList(){
		return localNCFlist;
	}
	/**
	 * Print all titles of the wiki pages which have &quot;Real&quot; content
	 * (i.e. the title has no namespace prefix) (key == 0).
	 */
	static class DemoMainArticleFilter implements IArticleFilter {
		public void process(WikiArticle page, Siteinfo siteinfo)
				throws SAXException {
			if (page.isMain()) {
				System.out.println(page.getTitle());
			}
		}

	}

	/**
	 * Print all titles of the wiki pages which are templates (key == 10).
	 */
	static class DemoTemplateArticleFilter implements IArticleFilter {
		public void process(WikiArticle page, Siteinfo siteinfo)
				throws SAXException {
			if (page.isTemplate()) {
				System.out.println(page.getTitle());
			}
		}

	}

	/**
	 * Print all titles of the wiki pages which are categories (key == 14).
	 */
	static class DemoCategoryArticleFilter implements IArticleFilter {
		public void process(WikiArticle page, Siteinfo siteinfo)
				throws SAXException {
			if (page.isCategory()) {
				System.out.println(page.getTitle());
			}
		}

	}

	/**
	 * @param args
	 */
	public void notMain(String[] args) {
		if (args.length != 1) {
			System.err.println("Usage: Parser <XML-FILE>");
			System.exit(-1);
		}
		// String bz2Filename =
		// "c:\\temp\\dewikiversity-20100401-pages-articles.xml.bz2";
		String bz2Filename = args[0];
		try {
			IArticleFilter handler = new DemoArticleFilter();
			WikiXMLParser wxp = new WikiXMLParser(bz2Filename, handler);
			wxp.parse();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
