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
public class Route {
    private Vector tempatBerhenti;
    private String nama;
    private int arah; //0 = pergi, 1 = pulang
    
    public Route(String nama, int arah)
    {
        this.nama = nama;
        this.arah = arah;
    }
    
    public void TambahTempatBerhenti(StopPoint sp)
    {
        this.tempatBerhenti.addElement(sp);
        sp.TambahRuteLewat(this);
    }
    
    public void HapusTempatBerhenti(StopPoint sp)
    {
        this.tempatBerhenti.removeElement(sp);
        sp.HapusRuteLewat(this);
    }
    
    public int GetIndexTempatBerhenti(StopPoint sp)
    {
        return this.tempatBerhenti.indexOf(sp);
    }
}
