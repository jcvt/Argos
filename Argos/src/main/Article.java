package main;

import java.sql.Date;

// -------------------------------------------------------------------------
/**
 * Represents a news article. By creating this object, you can use the
 * DatabaseConnector to easily insert an article into a database.
 *
 * @author Rich
 * @version Sep 11, 2013
 */
public class Article
{
    private String    title;
    private Publisher publisher;
    private Date      date;
    private String    snippet;
    private String    url;
    private String    pictureUrl;


    // ----------------------------------------------------------
    /**
     * Create a new Article object.
     *
     * @param url
     *            The exact address of the article.
     * @param publisher
     *            The publisher
     * @param date
     *            The date the article was written (not scraped).
     * @param title
     *            The title of the article.
     * @param snippet
     *            The first x number of words of the article.
     */
    public Article(
        String url,
        Publisher publisher,
        Date date,
        String title,
        String snippet)
    {
        this.title = title;
        this.publisher = publisher;
        this.date = date;
        this.snippet = snippet;
        this.url = url;
    }


    // ----------------------------------------------------------
    /**
     * Returns the title of the article.
     *
     * @return Returns the title of the article.
     */
    public String getTitle()
    {
        return title;
    }


    public Publisher getPublisher()
    {
        return publisher;
    }


    public Date getDate()
    {
        return date;
    }


    public String getSnippet()
    {
        return snippet;
    }


    public String getUrl()
    {
        return url;
    }
}
