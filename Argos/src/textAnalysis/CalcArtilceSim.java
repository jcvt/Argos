package textAnalysis;

import java.util.ArrayList;
import java.util.HashMap;

// -------------------------------------------------------------------------
/**
 * Takes two blocks of text in the form of character arrays and calculates
 * the Stanford certainty factor that the two articles are equal to each other
 * the articles should be stemmed, punctuation removed, stop words removed, and sent to lower case.
 *
 * @author John Cummins
 * @version 10.16.2013
 */
public class CalcArtilceSim
{
    private  char[] article1;
    private char[] article2;
    private float scf;
    private HashMap<String, HashMap<String, Float>> correlationMatrix;




    public CalcArtilceSim(char[] article1, char[] article2)
    {
    	correlationMatrix = new DeserialzeCM().getCM();
        this.article1 = article1;
        this.article2 = article2;
        ArrayList<String[]> bigramList1 = this.findPhrases(article1);
        ArrayList<String[]> bigramList2 = this.findPhrases(article2);
        float sima1a2 = this.calcSim(bigramList1, bigramList2);
        float sima2a1 = this.calcSim(bigramList2, bigramList1);
        scf  = ((sima1a2 + sima2a1)/(1- Math.min(sima1a2, sima2a1)));
        


    }


    /**
     * finds all of the bigrams in a char[]
     */
    private ArrayList<String[]> findPhrases(char[] text)
    {
        ArrayList<String[]> bigrams = new ArrayList<String[]>();
        ArrayList<String> wordList = new ArrayList<String>();

        // (34) was chosen from a google of the longest English word
        char[] currentWord = new char[34];
        int cwIndex = 0;
        //creates a list of all the words in order that they appear
        for (int x = 0; x < text.length; x++)
        {
            // find words by whitespace
            if (text[x] != ' ')
            {
                currentWord[cwIndex] = text[x];
                cwIndex++;
            }

            else
            {
                // adds current word to list

                wordList.add(currentWord.toString());

                // resets the info for current word
                cwIndex = 0;
                currentWord = new char[34];
            }
        }

        //computes phrases from word list
        for (int x = 0; x < wordList.size() - 1; x++)
        {
            String[] phrase = new String[2];
            phrase[1] = wordList.get(x);
            phrase[2] = wordList.get(x + 1);
            bigrams.add(phrase);
        }
        return bigrams;
    }


    /**
     * creates a bigram phrase correlation value
     *
     * @return float the bigram correlation factor
     * @param phrase1
     *            words one and two from phrase 1
     * @param phrase2
     *            words one and two form phrase 2
     */
    private float calcBigram(String[] phrase1, String[] phrase2)
    {
        return ((correlationMatrix.get(phrase1[0]).get(phrase2[0]) * correlationMatrix
            .get(phrase1[1]).get(phrase2[1])) / (1 - (correlationMatrix.get(
            phrase1[0]).get(phrase2[0]) * correlationMatrix.get(phrase1[1])
            .get(phrase2[1]))));
    }


    /**
     * calculates the similarity of a1 to a2
     *
     * @param a
     *            list of all the bigrams in a1
     * @param a
     *            list of all the bigrams in a2
     * @return the similarity of a1 -> a2
     */
    private float calcSim(ArrayList<String[]> a1, ArrayList<String[]> a2)
    {
        float similarity = 0;
        for (String[] phrase1 : a1)
        {
            for (String[] phrase2 : a2)
            {
                similarity += this.calcBigram(phrase1, phrase2);
            }
        }
        similarity /= a1.size();
        return similarity;
    }

    /**
     * returns the similarity of article1 to article 2
     */
    public float getStanfordCerntaintyFactor()
    {
        return scf;
    }

}
