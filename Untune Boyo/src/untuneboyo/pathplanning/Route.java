/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package untuneboyo.pathplanning;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

/**
 *
 * @author baskoro
 */
public class Route {
    private Vector tempatBerhenti;
    private String nama, arah;
    public static Vector RouteCollection = new Vector();
        
    public Route()
    {
        
    }
    
    public Route(String nama, String arah)
    {
        this.nama = nama;
        this.arah = arah;
        this.tempatBerhenti = new Vector();
    }
    
    public void TambahTempatBerhenti(StopPoint sp)
    {
        this.getTempatBerhenti().addElement(sp);
        sp.TambahRuteLewat(this);
    }
    
    public void HapusTempatBerhenti(StopPoint sp)
    {
        this.getTempatBerhenti().removeElement(sp);
        sp.HapusRuteLewat(this);
    }
    
    public int GetIndexTempatBerhenti(StopPoint sp)
    {
        return this.getTempatBerhenti().indexOf(sp);
    }
    
    public Vector GetCommonStopsWith(String nama, String arah)
    {
        Route rute = Route.GetRoute(nama, arah);
        
        if(rute != null)
        {
            Vector commonStops = new Vector();
            
            for(int i=0; i<this.tempatBerhenti.size(); i++)
            {
                StopPoint awal, akhir;
                awal = (StopPoint) this.tempatBerhenti.elementAt(i);
                    
                for(int j=0; j<rute.getTempatBerhenti().size(); j++)
                {
                    akhir = (StopPoint) rute.getTempatBerhenti().elementAt(j);
                    if(awal.equals(akhir))
                    {
                        commonStops.addElement(akhir);
                    }
                }
            }
            
            return commonStops;
        }
        else
        {
            return new Vector();
        }
    }
    
    public static Route GetRoute(String nama, String arah)
    {
        for(int i=0; i<Route.RouteCollection.size(); i++)
        {
            Route tmp = (Route) Route.RouteCollection.elementAt(i);
            if(tmp.getNama().equals(nama) && tmp.getArah().equals(arah))
            {
                return tmp;
            }
        }
        
        return null;
    }
    
    public static int LoadRouteFromFile()
    {
        InputStream ins = new Route().getClass().getResourceAsStream("/untuneboyo/resource/rute");
        
        if(ins != null)
        {
            try 
            {
                int c, index = 0;
                String[] tmp = new String[2];
                StringBuffer buf = new StringBuffer();
                Route rute = new Route();
                
                while ((c = ins.read()) != -1) 
                {
                    char huruf = (char)c;
                    
                    if(c == 13)
                    {
                        c = ins.read();
                        
                        String strBuf = buf.toString();
                        StopPoint sp = StopPoint.GetStopPointFromName(strBuf);
                        rute.TambahTempatBerhenti(sp);
                        Route.RouteCollection.addElement(rute);
                        buf = new StringBuffer();
                        index = 0;
                        
                        if(c == -1)
                        {
                            break;
                        }
                    }
                    else if(huruf == ';')
                    {
                        if(index < 1)
                        {
                            tmp[index] = buf.toString();
                        }
                        else if(index == 1)
                        {
                            rute = new Route(tmp[0], buf.toString());
                        }
                        else
                        {
                            String strBuf = buf.toString();
                            StopPoint sp = StopPoint.GetStopPointFromName(strBuf);
                            rute.TambahTempatBerhenti(sp);
                        }
                        
                        index++;
                        buf = new StringBuffer();
                    }
                    else
                    {
                        buf.append(huruf);
                    }
                }
                
                String strBuf = buf.toString();
                StopPoint sp = StopPoint.GetStopPointFromName(strBuf);
                rute.TambahTempatBerhenti(sp);
                Route.RouteCollection.addElement(rute);
            } 
            catch (IOException ex) 
            {
                ex.printStackTrace();
                return -1;
            }
        }
        else
        {
            return -1;
        }
        
        return 0;
    }

    public Vector getTempatBerhenti() {
        return tempatBerhenti;
    }

    public String getNama() {
        return nama;
    }

    public String getArah() {
        return arah;
    }
}
