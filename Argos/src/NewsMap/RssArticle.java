package NewsMap;
/**
 * Represents an RSS news article
 * @author John Cummins
 * @version 2014.02.16
 *
 */
public class RssArticle {
    private String title;
    private String description;
    private String fullText;
    private String date;
    private String publisher; 
    public RssArticle(String title, String description, String fullText,
            String date, String publisher){
        this.title = title.replaceAll("\\p{P}", "");
        this.description = description.replaceAll("\\p{P}", "");
        this.fullText = fullText.replaceAll("\\p{P}", "");
        this.date = date;
        this.publisher = publisher;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getFullText() {
        return fullText;
    }
    public void setFullText(String fullText) {
        this.fullText = fullText;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    
}
