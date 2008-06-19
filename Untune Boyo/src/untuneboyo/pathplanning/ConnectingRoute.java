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
    private StopPoint spSource, spDest;
    private Vector rutePerantara;
    private Vector sp1, sp2;
    
    public ConnectingRoute(Route source, Route dest, StopPoint spSource, StopPoint spDest)
    {
        this.source = source;
        this.dest = dest;
        this.spSource = spSource;
        this.spDest = spDest;
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
        StopPoint tmpSource, tmpDest;
        int iSource, iDest;
        
        tmpSource = this.CariMinStopPoint(perantara);
        tmpDest = this.CariMaxStopPoint(perantara);
        
        if(tmpSource == null || tmpDest == null)
        {
            return false;
        }
        
        iSource = perantara.GetIndexTempatBerhenti(tmpSource);
        iDest = perantara.GetIndexTempatBerhenti(tmpDest);
        if(this.source.GetIndexTempatBerhenti(this.spSource) < this.source.GetIndexTempatBerhenti(tmpSource) && this.dest.GetIndexTempatBerhenti(tmpDest) < this.dest.GetIndexTempatBerhenti(spDest) && iSource < iDest)
        {
            this.sp1.addElement(tmpSource);
            this.sp2.addElement(tmpDest);
            
            return true;
        }
        else
        {
            return false;
        }
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
