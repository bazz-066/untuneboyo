/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package untuneboyo.pathplanning;

import java.util.Vector;

/**
 *
 * @author baskoro
 */
public class CommonStops {
    private Route ruteA, ruteB;
    private Vector tempatBerhenti;
    
    public CommonStops(Route ruteA, Route ruteB)
    {
        this.ruteA = ruteA;
        this.ruteB = ruteB;
        this.tempatBerhenti = new Vector();
    }
    
    public void TambahTempatBerhenti(StopPoint sp)
    {
        this.tempatBerhenti.addElement(sp);
    }
    
    public void HapusTempatBerhenti(StopPoint sp)
    {
        this.tempatBerhenti.removeElement(sp);
    }
    
    public int GetJumlahTempatBerhenti()
    {
        return this.tempatBerhenti.size();
    }
}
