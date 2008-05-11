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
public class CommonStops 
{
    private Route ruteA, ruteB;
    private Vector tempatBerhenti;
    public static Vector CommonStopsCollection = new Vector();
    
    public CommonStops(Route ruteA, Route ruteB)
    {
        this.ruteA = ruteA;
        this.ruteB = ruteB;
        this.tempatBerhenti = ruteA.GetCommonStopsWith(ruteB.getNama(), ruteB.getArah());
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

    public Vector getTempatBerhenti() 
    {
        return tempatBerhenti;
    }
    
    public static CommonStops GetCommonStops(Route ruteA, Route ruteB)
    {
        for(int i=0; i<CommonStops.CommonStopsCollection.size(); i++)
        {
            CommonStops cs = (CommonStops) CommonStopsCollection.elementAt(i);
            if((cs.getRuteA().equals(ruteA) && cs.getRuteB().equals(ruteB)) || (cs.getRuteA().equals(ruteB) && cs.getRuteB().equals(ruteA)))
            {
                return cs;
            }
        }
        return null;
    }
    
    public static void LoadCommonStopsCollection()
    {
        int totRoute = Route.RouteCollection.size();
        for(int i=0; i<totRoute/2; i++)
        {
            for(int j=totRoute/2; j<totRoute; j++)
            {
                CommonStops cs = new CommonStops((Route)Route.RouteCollection.elementAt(i), (Route)Route.RouteCollection.elementAt(j));
                CommonStops.CommonStopsCollection.addElement(cs);
            }
        }
    }

    public Route getRuteA() {
        return ruteA;
    }

    public Route getRuteB() {
        return ruteB;
    }
}
