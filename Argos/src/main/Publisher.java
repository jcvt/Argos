package main;

// -------------------------------------------------------------------------
/**
 * Represents the different publishers from which article come from. These are
 * used to differentiate where articles come from. This is especially helpful
 * when having to choose which ArticleScraper to use for which URL.
 *
 * @author Rich Episcopo
 * @version Sep 11, 2013
 */
@SuppressWarnings("javadoc")
public enum Publisher
{

    CNN,
    REUTERS,
    BBC;
}
