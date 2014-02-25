package textAnalysis;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.ArrayList;

import parsers.DumpExample;
/**
 * saves a global correlation matrix to a file for later use
 * 
 * Buffers need to be implemented to handle the size of the file
 * See DumpExample in the parsers package for progress
 * 
 * @author John Cummins
 * @version 10.17.2013
 */
public class SerializeCM{
    public static void main(String[] args){
        /* calculates correlation factor for individual articles and stores
         * them in a list
         */
        ArrayList<HashMap<String, HashMap<String, Float>>> localNCFlist = new ArrayList<HashMap<String, HashMap<String, Float>>>();
        
        // constructs object to read file
        // assumption that args[0] is the file path
        DumpExample de = new DumpExample(args[0]);
        
        /*// when complete, get list of articles
        ArrayList<String> al = de.getArticles();
        
        // now feed into loop
        for(String article : al){     
            stems and removes stop words
            article = Utils.processText(article.toCharArray());
            ArticleNCF a = new ArticleNCF(article);
            localNCFlist.add(a.getCfSum());
        }*/
        /* takes lists and combines them creating the final matrix*/
       /* GlobalNCF GCF = new GlobalNCF(de.getNFCList());*/
        /*stores them in the correlation matrix class to remove all the fluf*/
       /* CorrelationMatrix CM = new CorrelationMatrix(GCF.getGlobalNCF());*/
        /*try{
            FileOutputStream fileOut =
                new FileOutputStream("C:/Development/CorrelationMatrix.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(CM);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in C:/Development/CorrelationMatrix.ser");
        }
        catch (IOException i){
            i.printStackTrace();
        }*/
    }
}
