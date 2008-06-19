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
    
    public String getRute(int index)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(index);
        sb.append(". ");
        
        for(int i=0; i<this.rute.size(); i++)
        {
            Route r = (Route) this.rute.elementAt(i);
            sb.append(r.getNama());
            sb.append("-");
            sb.append(r.getArah());
            if(i != this.rute.size() - 1)
            {
                sb.append(",");
            }
        }
        
        return sb.toString();
    }
    
    public Vector getStopPoint() 
    {
        return stopPoint;
    }
}
