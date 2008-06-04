/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package untuneboyo.persistent;

import net.sourceforge.floggy.persistence.Persistable;

/**
 *
 * @author baskoro
 */
public class CellLocation implements Persistable
{
    public String cellKey;
    public String stopPoint;
    public int signal;
    
    public CellLocation()
    {
        
    }
}
