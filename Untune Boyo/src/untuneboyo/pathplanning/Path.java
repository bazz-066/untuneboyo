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
public class Path 
{
    private Vector rute, stopPoint;
    
    public Path()
    {
        this.rute = new Vector();
        this.stopPoint = new Vector();
    }
    
    public void TambahRute(Route r)
    {
        this.rute.addElement(r);
    }
    
    public void TambahStopPoint(StopPoint sp)
    {
        this.stopPoint.addElement(sp);
    }

    public Vector getRute() 
    {
        return rute;
    }

    public Vector getStopPoint() 
    {
        return stopPoint;
    }
}
