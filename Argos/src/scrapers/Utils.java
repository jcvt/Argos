package scrapers;

// -------------------------------------------------------------------------
/**
 * Full of useful methods
 *
 * @author John Cummins
 * @version 09.22.2013
 */
public class Utils
{
    /**
     * finds the month in the date string if the month is in letters
     *
     * @param date
     *            the date your looking for the month in
     * @return String the date in form mm 00 if no month
     */
    public static String getMonth(String date)
    {
        String month = "00";

        // if else block to check for months in strings
        if (date.contains("jan") || date.contains("Jan")
            || date.contains("JAN"))
        {
            month = "01";
        }
        else if (date.contains("feb") || date.contains("Feb")
            || date.contains("FEB"))
        {
            month = "02";
        }
        else if (date.contains("mar") || date.contains("Mar")
            || date.contains("MAR"))
        {
            month = "03";
        }
        else if (date.contains("apr") || date.contains("Apr")
            || date.contains("APR"))
        {
            month = "04";
        }
        else if (date.contains("may") || date.contains("May")
            || date.contains("MAY"))
        {
            month = "05";
        }
        else if (date.contains("jun") || date.contains("Jun")
            || date.contains("JUN"))
        {
            month = "06";
        }
        else if (date.contains("jul") || date.contains("Jul")
            || date.contains("JUL"))
        {
            month = "07";
        }
        else if (date.contains("aug") || date.contains("Aug")
            || date.contains("AUG"))
        {
            month = "08";
        }
        else if (date.contains("sep") || date.contains("Sep")
            || date.contains("SEP"))
        {
            month = "09";
        }
        else if (date.contains("oct") || date.contains("Oct")
            || date.contains("OCT"))
        {
            month = "10";
        }
        else if (date.contains("nov") || date.contains("Nov")
            || date.contains("NOV"))
        {
            month = "11";
        }
        else if (date.contains("dec") || date.contains("Dec")
            || date.contains("DEC"))
        {
            month = "12";
        }
        return month;

    }


    /**
     * brute force finds the year in the date string only works from 2010 - 2021
     *
     * @param date
     *            the date your looking for the year in
     * @return String the date in form yyyy 0000 if no year
     */
    public static String getYear(String date)
    {
        String year = "0000";
        if (date.contains("2013"))
        {
            year = "2013";
        }
        else if (date.contains("2010"))
        {
            year = "2010";
        }
        else if (date.contains("2011"))
        {
            year = "2011";
        }
        else if (date.contains("2012"))
        {
            year = "2012";
        }
        else if (date.contains("2014"))
        {
            year = "2014";
        }
        else if (date.contains("2015"))
        {
            year = "2015";
        }
        else if (date.contains("2016"))
        {
            year = "2016";
        }
        else if (date.contains("2017"))
        {
            year = "2017";
        }
        else if (date.contains("2018"))
        {
            year = "2018";
        }
        else if (date.contains("2020"))
        {
            year = "2020";
        }
        else if (date.contains("2021"))
        {
            year = "2021";
        }
        return year;

    }

}
