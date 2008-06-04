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
public class LastUpdate implements Persistable
{
    public String lastUpdateBTS, lastUpdateLoc;
    
    public LastUpdate()
    {
        
    }
}
