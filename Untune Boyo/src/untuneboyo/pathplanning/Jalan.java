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
public class Jalan 
{
    private String nama;
    private Vector tempatBerhenti;
    
    public Jalan(String nama)
    {
        this.nama = nama;
    }

    public String getNama() 
    {
        return nama;
    }
    
    public void TambahTempatBerhenti(StopPoint sp)
    {
        this.tempatBerhenti.addElement(sp);
    }
    
    public void HapusTempatBerhenti(StopPoint sp)
    {
        this.tempatBerhenti.removeElement(sp);
    }
    
    public Vector GetSemuaTempatBerhenti()
    {
        return this.tempatBerhenti;
    }
}
