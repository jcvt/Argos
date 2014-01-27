package textAnalysis;

import java.util.HashMap;
import java.util.ArrayList;

// -------------------------------------------------------------------------
/**
 * Calculates the normalized correlation factors for each unigram in an
 * individual article and puts them in a matrix
 * 
 * can take the article in either String or char[] format
 * 
 * precondition: all punctuation is removed and words are stemmed
 * 
 * postcondition: There is global access to the correlation matrix and a list of
 * unique words in the article
 * 
 * @author John Cummins
 * @version 11.18.2013
 */
public class ArticleNCF {
	private char[] article;
	private ArrayList<String> wordList;
	private HashMap<String, HashMap<String, Float>> cfSum;
	private HashMap<String, Float> numApperances;

	public ArticleNCF(char[] article) {
		this.article = article;
		this.initialize();
		this.mapCF();
		normalize();
	}

	public ArticleNCF(String s) {
		this.article = s.toCharArray();
		this.initialize();
		this.mapCF();
		this.normalize();
	}

	/**
	 * populates wordList initializes cfSum and numApperances
	 */
	private void initialize() {
		wordList = new ArrayList<String>();
		numApperances = new HashMap<String, Float>();
		this.popWordList();
		cfSum = new HashMap<String, HashMap<String, Float>>();
		for (String word2 : wordList) {
			// maps word2 and creates a new hash map for the key
			cfSum.put(word2, new HashMap<String, Float>());

			// Initializes cf between word1 and word2 too be 0 and populates map
			for (String word1 : wordList) {
				cfSum.get(word2).put(word1, (float) 0);
			}
		}
	}

	/**
	 * normalizes the correlation factors to reduce the bias of longer articles
	 * producing higher correlation factors
	 */
	private void normalize() {
		for (String word1 : wordList) {
			for (String word2 : wordList) {
				if(!word1.equals(word2))
						cfSum.get(word1).put(word2,
						cfSum.get(word1).get(word2)
						/ (numApperances.get(word1) 
						* numApperances.get(word2)));
			}
		}
	}

	/**
	 * maps all the correlation factors for the words
	 */
	private void mapCF() {
		// positions will be used to calculate the distance
		float posW1 = 0;
		int cwIdx = 0;
		int wsIdx = 0;
		while (wsIdx < article.length) {
			if (article[wsIdx] == ' ') {
				char[] currentWord = new char[wsIdx - cwIdx];
				for (int z = 0; cwIdx < wsIdx; z++) {
					currentWord[z] = article[cwIdx];
					cwIdx++;
				}
				String word1 = new String(currentWord);	
				posW1++;	
				processWord2(posW1, word1);
				cwIdx = wsIdx + 1;
			}		
			wsIdx++;
		}
		String word1 = this.getLastWord();
		posW1++;
		processWord2(posW1, word1);
	}

	/**
	 * Traverses a char array looking for unique words when a unique word is
	 * found it is added to the list that will be returned
	 * 
	 * precondition: text should have been run through process text method in utils
	 *
	 * @return ArrayList<String> a list of all the unique words
	 */
	private ArrayList<String> popWordList() {
		/*
		 * cw = current word :  ws = whitespace 
		 * loop looks for words as separated by whitespace
		 */
		int cwIdx = 0;
		int wsIdx = 0;
		while (wsIdx < article.length) {
			if (article[wsIdx] == ' ') {
				/* build the word */
				char[] currentWord = new char[wsIdx - cwIdx];
				for (int z = 0; cwIdx < wsIdx; z++) {
					currentWord[z] = article[cwIdx];
					cwIdx++;
				}
				String stringCW = new String(currentWord);
				if (!wordList.contains(stringCW)) {
					wordList.add(stringCW);
					numApperances.put(stringCW, (float) 1);
				}
				else{
					numApperances.put(stringCW, numApperances.get(stringCW) + (float)1.0);
				}
				cwIdx = wsIdx + 1;
			}
			wsIdx++;
		}
		String stringCW = this.getLastWord();
		if (!wordList.contains(stringCW)) {
			wordList.add(stringCW);
			numApperances.put(stringCW, (float) 1);
		}
		else{
			numApperances.put(stringCW, numApperances.get(stringCW) + (float)1.0);
		}
		return wordList;
	}

	/**
	 * maps values to the correlation matrix
	 * @param posW1 the current position of word 1
	 * @param word1 word 1
	 */
	private void processWord2(float posW1, String word1){
		float posW2 = 0;
		int cw2Idx = 0;
		int ws2Idx = 0;
		while (ws2Idx < article.length) {
		// find words by whitespace
			if (article[ws2Idx] == ' ') {
				char[] currentWord2 = new char[ws2Idx- cw2Idx];
				for (int i = 0; cw2Idx < ws2Idx; i++) {
					currentWord2[i] = article[cw2Idx];
					cw2Idx++;
				}
				String  word2 = new String(currentWord2);
				posW2++;
				if (word1.equals(word2)) {
					cfSum.get(word1).put(word2, (float) 1);
				} 
				else {
				// add the 1/distance to cfSum
					cfSum.get(word1).put(word2,cfSum.get(word1).get(word2)
						+ ((float)1.0 / (Math.abs(posW1- posW2))));
					
					/* TODO rm testing
					 * System.out.println("w1: " + word1 + " at: " + posW1 + " word2: " + word2+ " at " +posW2);
					 * System.out.println(cfSum.get(word1).get(word2));*/
				}
				cw2Idx = ws2Idx + 1;
			}
			ws2Idx++;
		}
		
		processLastWord2(word1, posW1, posW2);
		
	}
	/**
	 * handles end case for word 2 when mapping correlation factors
	 * @param word1 word1
	 * @param posW1 current position of word 1
	 * @param posW2 current position of word 2
	 */
	private void processLastWord2( String word1, float posW1, float posW2){
		String word2 = this.getLastWord();
		posW2++;
		if (word1.equals(word2)) {
					cfSum.get(word1).put(word2, (float) 1);
		} 
		else {
			cfSum.get(word1).put(word2,cfSum.get(word1).get(word2)
				+ ((float)1.0 / (Math.abs(posW1- posW2))));
			}
	}
	/**
	 * finds the last word of the article and returns it in string form
	 * @return String the last word
	 */
	private String getLastWord(){
		int wsIdx = article.length - 1;
		while (wsIdx >=0 && article[wsIdx] != ' '){
			wsIdx--;
		}
		if (wsIdx < 0){
			wsIdx = 0;
		}
			char[] word = new char[article.length - wsIdx - 1];
			for (int z = 0; wsIdx < article.length - 1; z++) {
				word[z] = article[wsIdx + 1];
				wsIdx++;
			}
		return new String(word);
	}
	// ----------------------------------------------------------
	/**
	 * gets the word list
	 * 
	 * @return ArrayList<String> all the unique words in the article
	 */
	public ArrayList<String> getWordList() {
		return wordList;
	}

	/**
	 * gets correlation factors
	 * 
	 * @return HashMap<String, HashMap<String, Float>> Correlation factors
	 */
	public HashMap<String, HashMap<String, Float>> getCfSum() {
		return cfSum;
	}

}
