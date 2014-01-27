package textAnalysis;


import java.util.ArrayList;
import java.util.HashMap;

// -------------------------------------------------------------------------
/**
 * Takes the normalized correlation factor matrix from a list of articles and
 * combines them into one global matrix
 *
 * precondition:
 * needs a list of HashMaps created by Article NCF or by GlobalNCF
 * needs a list of word lists corresponding to said HashMaps
 *
 * postcondition:
 * global access to a list of all unique words across all articles
 * global access to a HashMap containing the averaged ncf values
 * that will be used at runtime
 *
 * @author John Cummins
 * @version 11.18.2013
 */
public class GlobalNCF{
    private HashMap<String, HashMap<String, Float>>            globalNCF;
    private HashMap<String, HashMap<String, Float>>            numApperances;
    private ArrayList<HashMap<String, HashMap<String, Float>>> localNCFlist;

    // ----------------------------------------------------------
    /**
     * @param listOfWordLists a list of all the local word lists
     * @param localNCFlist all the local ncf values
     */
    public GlobalNCF(ArrayList<HashMap<String, HashMap<String, Float>>> localNCFlist){
        this.localNCFlist = localNCFlist;
        globalNCF = new HashMap<String, HashMap<String, Float>>();  
        numApperances = new HashMap<String, HashMap<String, Float>>();
        this.mapNCF();
        this.average();
    }
    /**
     * sums the normalized correlation factors keeps track of how many article
     * word1 and word2 appear in together
     */
    private void mapNCF(){
        for (HashMap<String, HashMap<String, Float>> localMap : localNCFlist){
            for (String word1 : localMap.keySet()){
                for (String word2 : localMap.get(word1).keySet()){
                	this.nullCheck(word1, word2);
                	if (word1.equals(word2)){
            			globalNCF.get(word1).put(word2, (float)1);
            		}
            		else{
            			globalNCF.get(word1).put(word2, globalNCF.get(word1).get(word2) +
            				localMap.get(word1).get(word2));
            		}
                	this.incrementNumApp(word1, word2);
                }
            }
        }
    }
   
    /**
     * checks to see if the map is initialized for word 1 and word 2
     * if not then it initializes for the words
     * @param word1 first word
     * @param word2 second word
     */
    private void nullCheck (String word1, String word2){
    	if (globalNCF.get(word1) == null){
			globalNCF.put(word1, new HashMap<String, Float>());
			globalNCF.get(word1).put(word2, (float) 0);
		}
		else if (globalNCF.get(word1).get(word2) == null){
			globalNCF.get(word1).put(word2, (float) 0);
		}	
	}
    /**
     * increments the number of times a pair of words appears in articles
     * @param word1 one of the words
     * @param word2 the second of the words
     */
    private void incrementNumApp(String word1, String word2){
        if (numApperances.get(word1) != null){
        	if (numApperances.get(word1).get(word2) != null){
        		numApperances.get(word1).put(word2,
        				numApperances.get(word1).get(word2) + 1);
        	}
        	else{
        		numApperances.get(word1).put(word2, (float)1);
        	}
        }
        else{
        	numApperances.put(word1, new HashMap<String, Float>());
        	numApperances.get(word1).put(word2, (float)1);
        }
    }
    
    /**
     * averages all the normalized correlation factors by dividing by the number
     * of articles that word1 and word2 appeared in together
     */
    private void average(){
        for (String word1 : globalNCF.keySet()){
            for (String word2 : globalNCF.get(word1).keySet()){
            	if (!word1.equals(word2) && numApperances.get(word1).get(word2) != null){
            		/*System.out.println("word1: " + word1 + " word2: "+ word2);*/
                globalNCF.get(word1).put(
                    word2,
                    globalNCF.get(word1).get(word2)
                        / numApperances.get(word1).get(word2));
            	}
            }
        }
    }
    /**
     * provides access to the final correlation matrix
     * @return the correlation matrix
     */
    public HashMap<String, HashMap<String, Float>> getGlobalNCF(){
        return globalNCF;
    }
}
