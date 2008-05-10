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
public class StopPoint {
    private String nama;
    private Jalan parent;
    private Vector routesList;
    
    public StopPoint(String nama, Jalan parent)
    {
        this.nama = nama;
        this.parent = parent;
        this.parent.TambahTempatBerhenti(this);
        this.routesList = new Vector();
    }
    
    public void TambahRuteLewat(Route rute)
    {
        this.routesList.addElement(rute);
    }
    
    public void HapusRuteLewat(Route rute)
    {
        this.routesList.removeElement(rute);
    }
}
