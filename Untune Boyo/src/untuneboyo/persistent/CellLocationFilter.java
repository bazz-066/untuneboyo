/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package untuneboyo.persistent;

import net.sourceforge.floggy.persistence.Filter;
import net.sourceforge.floggy.persistence.Persistable;

/**
 *
 * @author baskoro
 */
public class CellLocationFilter implements Filter
{
    private String cellKey, rp_ket;
    private boolean isKey;
    
    public CellLocationFilter(String cellKey, String rp_ket, boolean isKey)
    {
        this.cellKey = cellKey;
        this.rp_ket = rp_ket;
        this.isKey = isKey;
    }

    public boolean matches(Persistable obj) 
    {
        CellLocation loc = (CellLocation)obj;
        if(isKey)
        {
            return (loc.cellKey.equals(this.cellKey));
        }
        else
        {
            return (loc.stopPoint.equals(this.rp_ket));
        }
    }
}
