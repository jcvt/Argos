package textAnalysis;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
// -------------------------------------------------------------------------
/**
 *  Code to load the correlation matrix from a file for use at runtime
 *
 *  @author John Cummins
 *  @version 10.17.2013
 */
public class DeserializeCM{
    
    public static CorrelationMatrix main(String[] args){
    	CorrelationMatrix CM = null;
        try{
            FileInputStream fileIn =
                new FileInputStream("C:/Development/CorrelationMatrix.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            CM = (CorrelationMatrix)in.readObject();
            in.close();
            fileIn.close();
            return CM;
        }
        catch (IOException i){
            i.printStackTrace();
            return CM;
        }
        catch (ClassNotFoundException c){
            System.out.println("CorrelationMatrix class not found");
            c.printStackTrace();
            return CM;
        }
    }
}