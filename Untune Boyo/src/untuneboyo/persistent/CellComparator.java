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
public class CellComparator implements Comparator
{

    public int compare(Persistable arg0, Persistable arg1) 
    {
        BTSData cell1 = (BTSData)arg0;
        BTSData cell2 = (BTSData)arg1;
        
        long key1 = Integer.parseInt(cell1.key);
        long key2 = Integer.parseInt(cell2.key);
        
        if(key1 < key2)
        {
            return PRECEDES;
        }
        if(key1 > key2)
        {
            return FOLLOWS;
        }
        
        return EQUIVALENT;
    }

}
