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
public class ConnectingRoute 
{
    private Route source, dest;
    private Vector rutePerantara;
    private Vector sp1, sp2;
    
    public ConnectingRoute(Route source, Route dest)
    {
        this.source = source;
        this.dest = dest;
        this.rutePerantara = new Vector();
        this.sp1 = new Vector();
        this.sp2 = new Vector();
    }
    
    public void CariRutePerantara()
    {
        int iSource = Route.RouteCollection.indexOf(this.source);
        int iDest = Route.RouteCollection.indexOf(this.dest);
        
        for(int i=0; i<Route.RouteCollection.size(); i++)
        {
            Route rute = (Route) Route.RouteCollection.elementAt(i);
            if(rute.equals(this.source) || rute.equals(dest))
            {
                continue;
            }
            else
            {
                if(ConnectivityMatriks.ConnMatriks[iSource][i] * ConnectivityMatriks.ConnMatriks[i][iDest] > 0 && this.IsRuteValid(rute))
                {
                    this.rutePerantara.addElement(rute);
                }
            }
        }
    }
    
    public boolean IsRuteValid(Route perantara)
    {
        StopPoint spSource, spDest;
        int iSource, iDest;
        
        spSource = this.CariMinStopPoint(perantara);
        spDest = this.CariMaxStopPoint(perantara);
        
        if(spSource == null || spDest == null)
        {
            return false;
        }
        
        iSource = perantara.GetIndexTempatBerhenti(spSource);
        iDest = perantara.GetIndexTempatBerhenti(spDest);
        this.sp1.addElement(spSource);
        this.sp2.addElement(spDest);
        
        return iSource < iDest;
    }
    
    public StopPoint CariMinStopPoint(Route perantara)
    {
        CommonStops cs = CommonStops.GetCommonStops(this.source, perantara);
        StopPoint sp;
        if(cs.GetJumlahTempatBerhenti() > 0)
        {
            sp = (StopPoint) cs.getTempatBerhenti().elementAt(0);
            for(int i=0; i<cs.GetJumlahTempatBerhenti(); i++)
            {
                if(perantara.GetIndexTempatBerhenti(sp) > perantara.GetIndexTempatBerhenti((StopPoint)cs.getTempatBerhenti().elementAt(i)))
                {
                    sp = (StopPoint) cs.getTempatBerhenti().elementAt(i);
                }
            }
            
            return sp;
        }
        else
        {
            return null;
        }
    }
    
    public StopPoint CariMaxStopPoint(Route perantara)
    {
        CommonStops cs = CommonStops.GetCommonStops(this.dest, perantara);
        StopPoint sp;
        if(cs.GetJumlahTempatBerhenti() > 0)
        {
            sp = (StopPoint) cs.getTempatBerhenti().elementAt(0);
            for(int i=0; i<cs.GetJumlahTempatBerhenti(); i++)
            {
                if(perantara.GetIndexTempatBerhenti(sp) < perantara.GetIndexTempatBerhenti((StopPoint)cs.getTempatBerhenti().elementAt(i)))
                {
                    sp = (StopPoint) cs.getTempatBerhenti().elementAt(i);
                }
            }
            
            return sp;
        }
        else
        {
            return null;
        }
    }

    public Vector getRutePerantara() {
        return rutePerantara;
    }

    public Vector getSp1() {
        return sp1;
    }

    public Vector getSp2() {
        return sp2;
    }
}
