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
public class StopPoint {
    private String nama;
    private Jalan parent;
    private int x, y;
    private Vector routesList;
    public static Vector StopPointCollection = new Vector();
    
    public StopPoint()
    {
        
    }
    
    public StopPoint(String nama, Jalan parent, int x, int y)
    {
        this.nama = nama;
        this.parent = parent;
        this.x = x;
        this.y = y;
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

    public int getX() 
    {
        return x;
    }

    public int getY() 
    {
        return y;
    }
    
    public static StopPoint GetStopPointFromName(String nama)
    {
        for(int i=0; i<StopPoint.StopPointCollection.size(); i++)
        {
            StopPoint tmp = (StopPoint)StopPoint.StopPointCollection.elementAt(i);
            if(tmp.getNama().equals(nama))
            {
                return tmp;
            }
        }
        
        return null;
    }
    
    public static int LoadStopPointFromFile()
    {
        InputStream ins = new StopPoint().getClass().getResourceAsStream("/untuneboyo/resource/stoppoint");
        
        if(ins != null)
        {
            try 
            {
                int c, index = 0;
                String[] tmp = new String[4];
                StringBuffer buf = new StringBuffer();
                
                while ((c = ins.read()) != -1) 
                {
                    char huruf = (char)c;
                    
                    if(c == 13)
                    {
                        c = ins.read();
                        tmp[index] = buf.toString();
                        index = 0;
                        
                        Jalan jalan = Jalan.GetJalanFromName(tmp[0]);
                        StopPoint sp = new StopPoint(tmp[1], jalan, Integer.parseInt(tmp[2]), Integer.parseInt(tmp[3]));
                        StopPointCollection.addElement(sp);
                        buf = new StringBuffer();
                        
                        if(c == -1)
                        {
                            break;
                        }
                    }
                    else if(huruf == ';')
                    {
                        tmp[index] = buf.toString();
                        index++;
                        buf = new StringBuffer();
                    }
                    else
                    {
                        buf.append(huruf);
                    }
                }
                
                tmp[index] = buf.toString();
                index = 0;

                Jalan jalan = Jalan.GetJalanFromName(tmp[0]);
                StopPoint sp = new StopPoint(tmp[1], jalan, Integer.parseInt(tmp[2]), Integer.parseInt(tmp[3]));
                StopPointCollection.addElement(sp);
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
    
    public static Vector Search(String keyword)
    {
        Vector searchResult = new Vector();
        
        for(int i=0; i<StopPointCollection.size(); i++)
        {
            StopPoint sp =  (StopPoint) StopPointCollection.elementAt(i);
            if(sp.getNama().toLowerCase().indexOf(keyword.toLowerCase()) >= 0  || sp.getParent().getNama().toLowerCase().indexOf(keyword.toLowerCase()) >= 0)
            {
                searchResult.addElement(sp);
            }
        }
        
        return searchResult;
    }
    
    public boolean equals(StopPoint sp)
    {
        if(this.nama.equals(sp.getNama()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public String getNama() 
    {
        return nama;
    }

    public Jalan getParent() 
    {
        return parent;
    }

    public Vector getRoutesList() {
        return routesList;
    }
}
