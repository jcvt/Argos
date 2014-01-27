package main;

// -------------------------------------------------------------------------
/**
 * A wrapper class that wraps a URL along with what source the URL points to.
 * The "source" which the URL points to is typically a new source, hence being
 * defined by the Publisher enumerated type.
 *
 * @author Rich
 * @version Sep 11, 2013
 */
public class UrlWrapper
{
    private Publisher publisher;
    private String    URL;


    // ----------------------------------------------------------
    /**
     * Create a new UrlWrapper object.
     *
     * @param URL
     *            The URL address.
     * @param publisher
     *            The publisher of the corresponding URL.
     */
    public UrlWrapper(String URL, Publisher publisher)
    {
        this.publisher = publisher;
        this.URL = URL;
    }


    // ----------------------------------------------------------
    /**
     * Returns the publisher of the publication to which the URL points to.
     *
     * @return The publisher of the publication to which the URL points to.
     */
    public Publisher getPublisher()
    {
        return publisher;
    }


    // ----------------------------------------------------------
    /**
     * Returns the URL which typically points to a publication.
     *
     * @return The URL which typically points to a publication.
     */
    public String getURL()
    {
        return URL;
    }

}
