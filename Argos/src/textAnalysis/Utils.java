package textAnalysis;

import java.util.ArrayList;

/**
 * Full of useful methods
 * 
 * @author John Cummins
 * @version 11.12.2013
 */

public class Utils {
	/**
	 * removes all of the stop words from char[] brute force through a list of
	 * stop words and replacing them with a zeros if found then if the word
	 * isn't a stop word it is stemmed. characters that are stemmed away are
	 * replaced with zeros at the end the zeros are removed
	 * 
	 * precondition: 
	 * all punctuation should be removed should be lower case
	 * should be trimmed so there is no white space on either end
	 * 
	 * @param text
	 *            text to have stop words removed from
	 * @return char[] containing all the text except stop words
	 */
	public static String processText(char[] text) {
		// creates a string of a bunch of stop words
		// list of every stop word ever
		String stopwords = "a able about above abst accordance according accordingly across act actually added adj affected affecting affects after afterwards again against ah all almost alone along already also although always am among "
				+ "amongst an and announce another any anybody anyhow anymore anyone anything anyway anyways anywhere apparently approximately are aren arent arise around as aside ask asking at auth available away awfully"
				+ " back be became because become becomes becoming been before beforehand begin beginning beginnings begins behind being believe below beside besides between beyond biol both brief briefly but by"
				+ " ca came can cannot can't cause causes certain certainly co com come comes contain containing contains could couldnt"
				+ " date did didn't different do does doesn't doing done don't down downwards due during "
				+ " each ed edu effect eg eight eighty either else elsewhere end ending enough especially et et-al etc even ever every everybody everyone everything everywhere ex except "
				+ "far few ff fifth first five fix followed following follows for former formerly forth found four from further furthermore "
				+ "gave get gets getting give given gives giving go goes gone got gotten "
				+ "had happens hardly has hasn't have haven't having he hed hence her here hereafter hereby herein heres hereupon hers herself hes hi hid him himself his hither home how howbeit however hundred "
				+ "id ie if i'll im immediate immediately importance important in inc indeed index information instead into invention inward is isn't it itd it'll its itself i've "
				+ "just keep keeps kept kg km know known knows "
				+ "largely last lately later latter latterly least less lest let lets like liked likely line little 'll look looking looks ltd "
				+ "made mainly make makes many may maybe me mean means meantime meanwhile merely mg might million miss ml more moreover most mostly mr mrs much mug must my myself "
				+ "na name namely nay nd near nearly necessarily necessary need needs neither never nevertheless new next nine ninety no nobody non none nonetheless noone nor normally nos not noted nothing now nowhere "
				+ "obtain obtained obviously of off often oh ok okay old omitted on once one ones only onto or ord other others otherwise ought our ours ourselves out outside over overall owing own "
				+ "page pages part particular particularly past per perhaps placed please plus poorly possible possibly potentially pp predominantly present previously primarily probably promptly proud provides put "
				+ "que quickly quite qv ran rather rd re readily really recent recently ref refs regarding regardless regards related relatively research respectively resulted resulting results right run "
				+ "said same saw say saying says sec section see seeing seem seemed seeming seems seen self selves sent seven several shall she shed she'll shes should shouldn't show showed shown showns shows significant significantly similar similarly since six slightly so some somebody somehow someone somethan something sometime sometimes somewhat somewhere soon sorry specifically specified specify specifying still stop strongly sub substantially successfully such sufficiently suggest sup sure "
				+ "take taken taking tell tends th than thank thanks thanx that that'll thats that've the their theirs them themselves then thence there thereafter thereby thered therefore therein there'llthereof therere theres thereto thereupon there've these they theyd they'll theyre they've think this those thou though thoughh thousand throug through throughout thru thus til tip to together too took toward towards tried tries truly try trying ts twice two "
				+ "un under unfortunately unless unlike unlikely until unto up upon ups us use used useful usefully usefulness uses using usually "
				+ "value various 've very via viz vol vols vs "
				+ "want wants was wasn't way we wed welcome we'll went were weren't we've what whatever what'll whatswhen whence whenever where whereafter whereas whereby wherein wheres whereupon wherever whether which while whim whither who whod whoever whole who'll whom whomever whos whose why widely willing wish with within without won't words world would wouldn't www "
				+ "yes yet you youd you'll your you're yours yourself yourselves you've zero";		
		
		/*
		 * cw = current word :  ws = whitespace 
		 * loop looks for words as separated by whitespace
		 */
		int cwIdx = 0;
		int wsIdx = 0;
		while (wsIdx < text.length) {

			
			if (text[wsIdx] == ' ') {
				/* build the word */
				char[] currentWord = new char[wsIdx - cwIdx];
				for (int z = 0; cwIdx < wsIdx; z++) {
					currentWord[z] = text[cwIdx];
					cwIdx++;
				}
				String stringCW = new String(currentWord);
				/* is the word is a stop word?
				 * replaces word and a wsIdx with 0's
				 */
				if (stopwords.contains(stringCW)) {
					for (int y = 0; y <= stringCW.length(); y++) {
						text[wsIdx - y] = '0';
					}
				} 
				else {
					// its not a stop word and it needs to be stemmed
					Stemmer s = new Stemmer();
					s.add(stringCW.toCharArray(), stringCW.length());
					s.stem();
					/*the word has been changed and needs to be replaced*/
					String stem = s.toString();
					char[] stemChar = stem.toCharArray();
					if(stringCW != stem);{
						int start = wsIdx - stringCW.length();
						for (int c = 0; c < stringCW.length(); c++){
							
							if (c < stem.length()){
								text[start + c] = stemChar[c];
							}
							else{
								text[start + c] = '0';
							}
						}
					}
				}
				/* check for double whitespace chars
				 * jump over whitespace char to next letter */
				while(wsIdx < text.length - 2 && text[wsIdx+1] == ' '){
					wsIdx++;
					text[wsIdx] = '0';
				}
				cwIdx = wsIdx + 1;
			}
			wsIdx++;
		}
		
		/** 
		 * handle the end cases
		 * 
		 * case 1: text ends in whitespace
		 * all words have been processed but there is extra whitespace
		 * char at the end of the word
		 * 
		 * case 2: text doesn't end in whitespace
		 * the last word needs to be processed
		 **/
		
		/* case 1 */
		if (text[text.length - 1] == ' '){
			int c = 1;
			while(text[text.length - c] == ' '){
				text[text.length - c] = '0';
				c++;
			}
		}
		/* case 2 */
		else {
			wsIdx = text.length - 1;
			while (wsIdx >=0 && text[wsIdx] != ' '){
				wsIdx--;
			}
			if (wsIdx < 0){
				wsIdx = 0;
			}
				char[] currentWord = new char[text.length - wsIdx];
				for (int z = 0; wsIdx < text.length; z++) {
					currentWord[z] = text[wsIdx];
					wsIdx++;
				}
				String stringCW = new String(currentWord);
				if (stopwords.contains(stringCW)) {
					for (int y = 1; y <= stringCW.length(); y++) {
						text[wsIdx - y] = '0';
					}
				} 
				else {
					Stemmer s = new Stemmer();
					s.add(stringCW.toCharArray(), stringCW.length());
					s.stem();
					/*the word has been changed and needs to be replaced*/
					String stem = s.toString();
					char[] stemChar = stem.toCharArray();
					if(stringCW != stem){
						int start = wsIdx - stringCW.length();
						for (int c = 0; c < stringCW.length(); c++){
							if (c < stem.length()){
								text[start + c] = stemChar[c];
							}
							else{
								text[start + c] = '0';
							}
						}
					}
				}			
		}
		return new String(text).replaceAll("0", "").trim();
	}
	

	// ----------------------------------------------------------
	/**
	 * Traverses a char array looking for unique words when a unique word is
	 * found it is added to the list that will be returned
	 * 
	 * precondition: all punctuation should be removed
	 * 
	 * @param text
	 *            text to get unique words from
	 * @return ArrayList<String> a list of all the unique words
	 */
	public static ArrayList<String> getWordList(char[] text) {
		ArrayList<String> wordList = new ArrayList<String>();

		/*
		 * cw = current word :  ws = whitespace 
		 * loop looks for words as separated by whitespace
		 */
		int cwIdx = 0;
		int wsIdx = 0;
		while (wsIdx < text.length) {
			if (text[wsIdx] == ' ') {
				/* build the word */
				char[] currentWord = new char[wsIdx - cwIdx];
				for (int z = 0; cwIdx < wsIdx; z++) {
					currentWord[z] = text[cwIdx];
					cwIdx++;
				}
				String stringCW = new String(currentWord);
				if (!wordList.contains(stringCW)) {
					wordList.add(stringCW);
				}
				/* check for double whitespace chars
				 * jump over whitespace char to next letter */
				while(wsIdx < text.length - 2 && text[wsIdx+1] == ' '){
					wsIdx++;
					text[wsIdx] = '0';
				}
				cwIdx = wsIdx + 1;
			}
			wsIdx++;
		}
		/* 
		 * handle the end cases
		 * 
		 * case: text doesn't end in whitespace
		 * the last word needs to be processed
		 */
			wsIdx = text.length - 1;
			while (wsIdx >=0 && text[wsIdx] != ' '){
				wsIdx--;
			}
			if (wsIdx < 0){
				wsIdx = 0;
			}
			char[] currentWord = new char[text.length - wsIdx];
			for (int z = 0; wsIdx < text.length; z++) {
				currentWord[z] = text[wsIdx];
				wsIdx++;
			}
			String stringCW = new String(currentWord);
			if (!wordList.contains(stringCW)) {
				wordList.add(stringCW);
			}
			
		return wordList;
	}

}
