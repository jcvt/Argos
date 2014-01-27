package textAnalysis;

import java.util.HashMap;
import java.io.Serializable;

// -------------------------------------------------------------------------
/**
 *  takes a global matrix and creates an object that stores only the
 *  important info (correlation matrix) so that it can be deserialized faster
 *
 *  @author John Cummins
 *  @version Oct 17, 2013
 */
public class CorrelationMatrix implements Serializable{
    private HashMap<String, HashMap<String, Float>> CM;
    public CorrelationMatrix(HashMap<String, HashMap<String, Float>> CM){
        this.CM = CM;
    }
    public HashMap<String, HashMap<String, Float>> getCM(){
        return CM;
    }

}
