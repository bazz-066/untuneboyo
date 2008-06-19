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
public class PathPlanning 
{
    public PathPlanning()
    {
        
    }
    
    public Vector FindRoute(StopPoint source, StopPoint dest)
    {
        Vector path = new Vector();
                
        if(source.equals(dest))
        {
            return path;
        }
        
        Vector routeSource, routeDest;
        routeSource = source.getRoutesList();
        routeDest = dest.getRoutesList();
        //direct
        this.FindDirectRoute(path, routeSource, routeDest, source, dest);
        if(path.size() > 0)
        {
            return path;
        }
        else
        {
            //1 transfer
            this.Find1TransferRoute(path, routeSource, routeDest, source, dest);
            if(path.size() > 0)
            {
                return path;
            }
            else
            {
                //2 transfer
                this.Find2TransferRoute(path, routeSource, routeDest, source, dest);
                return path;
            }
        }
    }
    
    private void FindDirectRoute(Vector route, Vector routeSource, Vector routeDest, StopPoint spSource, StopPoint spDest)
    {
        for(int i=0; i<routeSource.size(); i++)
        {
            for(int j=0; j<routeDest.size(); j++)
            {
                Route rSource = (Route) routeSource.elementAt(i);
                Route rDest = (Route) routeDest.elementAt(j);
                
                if(routeSource.elementAt(i).equals(routeDest.elementAt(j)) && rSource.GetIndexTempatBerhenti(spSource) < rDest.GetIndexTempatBerhenti(spDest))
                {
                    Path path = new Path();
                    path.TambahRute((Route)routeSource.elementAt(i));
                    path.TambahStopPoint(spSource);
                    path.TambahStopPoint(spDest);
                    
                    route.addElement(path);
                }
            }
        }
    }
    
    private void Find1TransferRoute(Vector route, Vector routeSource, Vector routeDest, StopPoint spSource, StopPoint spDest)
    {
        for(int i=0; i<routeSource.size(); i++)
        {
            for(int j=0; j<routeDest.size(); j++)
            {
                Route source, dest;
                source = (Route) routeSource.elementAt(i);
                dest = (Route) routeDest.elementAt(j);
                int status = 0;
                
                CommonStops cs = CommonStops.GetCommonStops(source, dest);
                
                if(cs != null)
                {
                    if(cs.GetJumlahTempatBerhenti() > 0)
                    {
                        Path path = new Path();
                        path.TambahRute((Route)routeSource.elementAt(i));
                        path.TambahStopPoint(spSource);
                        path.TambahRute((Route)routeDest.elementAt(j));
                        
                        for(int k=0; k<cs.GetJumlahTempatBerhenti(); k++)
                        {
                            StopPoint transfer = (StopPoint)cs.getTempatBerhenti().elementAt(k);
                            if(source.GetIndexTempatBerhenti(spSource) < source.GetIndexTempatBerhenti(transfer) && dest.GetIndexTempatBerhenti(transfer) < dest.GetIndexTempatBerhenti(spDest))
                            {
                                path.TambahStopPoint(transfer);
                                status = 1;
                                break;
                            }
                        }
                        path.TambahStopPoint(spDest);
                        
                        if(status == 1)
                        {
                            route.addElement(path);
                        }
                    }
                }
            }
        }
    }
    
    private void Find2TransferRoute(Vector route, Vector routeSource, Vector routeDest, StopPoint spSource, StopPoint spDest)
    {
        for(int i=0; i<routeSource.size(); i++)
        {
            for(int j=0; j<routeDest.size(); j++)
            {
                Route source, dest;
                source = (Route) routeSource.elementAt(i);
                dest = (Route) routeDest.elementAt(j);
                
                ConnectingRoute cr = new ConnectingRoute(source, dest, spSource, spDest);
                cr.CariRutePerantara();
                                
                for(int k=0; k<cr.getRutePerantara().size(); k++)
                {
                    Path path = new Path();
                    path.TambahRute((Route)routeSource.elementAt(i));
                    path.TambahStopPoint(spSource);
                    path.TambahStopPoint((StopPoint)cr.getSp1().elementAt(k));
                    path.TambahRute((Route)cr.getRutePerantara().elementAt(k));
                    path.TambahStopPoint((StopPoint)cr.getSp2().elementAt(k));
                    path.TambahRute((Route)routeDest.elementAt(j));
                    path.TambahStopPoint(spDest);
                    
                    route.addElement(path);
                }
            }
        }
    }
}
