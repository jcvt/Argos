package NewsMap;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class HaaretzRSS {

    private Document doc;
    private static Elements titles;
    private static Elements articles;
    private static Elements dates;
    private ArrayList<RssArticle> list;
    public HaaretzRSS(String url) { //view-source:http://feeds.feedburner.com/haaretz/LBao
        list = new ArrayList<RssArticle>();
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            Thread.currentThread().interrupt();
        }
        titles = doc.select("h4 .itemtitle");
        articles = doc.select("div .itemcontent");
        dates = doc.select("h5 .itemposttime");
        this.populateList();
    }
    private void populateList(){
        if (titles.size() == articles.size() && articles.size() == dates.size()){
            for (int x = 0; x < articles.size(); x++){
                RssArticle article = new RssArticle(
                    titles.get(x).text(), articles.get(x).text(), 
                    titles.get(x).text() + " " +articles.get(x).text(), 
                    processDate(dates.get(x).text()), "Haaretz");
                list.add(article);
            }
        }
    }
    
    private String processDate(String date){
        String day = date.substring(5, 7);
        String month = Utils.getMonth(date);
        String year = Utils.getYear(date);
        return year + "-" + month + "-" + day;
    }
    
    public ArrayList<RssArticle> getList(){
        return list;
    }
}
