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
public class CellFilter implements Filter
{
    private String MCC, MNC, LAC, CellId;
    
    public CellFilter(String MCC, String MNC, String LAC, String CellId)
    {
        this.MCC = MCC;
        this.MNC = MNC;
        this.LAC = LAC;
        this.CellId = CellId;
    }
    
    public boolean matches(Persistable arg0) 
    {
        BTSData cell = (BTSData)arg0;
        return (cell.MCC.equals(this.MCC) && cell.MNC.equals(this.MNC) && cell.LAC.equals(this.LAC) && cell.CellID.equals(this.CellId));
    }
    
}
