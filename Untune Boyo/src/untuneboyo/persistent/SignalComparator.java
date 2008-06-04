/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package untuneboyo.persistent;

import net.sourceforge.floggy.persistence.Comparator;
import net.sourceforge.floggy.persistence.Persistable;

/**
 *
 * @author baskoro
 */
public class SignalComparator implements Comparator
{

    public int compare(Persistable obj1, Persistable obj2) 
    {
        CellLocation cl1 = (CellLocation)obj1;
        CellLocation cl2 = (CellLocation)obj2;
        
        if(cl1.signal < cl2.signal)
        {
            return PRECEDES;
        }
        if(cl1.signal > cl2.signal)
        {
            return FOLLOWS;
        }
        
        return EQUIVALENT;
    }

}
