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
public class Jalan 
{
    private String nama;
    private Vector tempatBerhenti;
    public static Vector JalanCollection = new Vector();
    
    public Jalan()
    {
        
    }
    
    public Jalan(String nama)
    {
        this.nama = nama;
        this.tempatBerhenti = new Vector();
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
    
    public static Jalan GetJalanFromName(String nama)
    {
        for(int i=0; i<Jalan.JalanCollection.size(); i++)
        {
            Jalan tmp = (Jalan)Jalan.JalanCollection.elementAt(i);
            if(tmp.getNama().equals(nama))
            {
                return tmp;
            }
        }
        
        return null;
    }
    
    public static int LoadJalanFromFile()
    {
        InputStream ins = new Jalan().getClass().getResourceAsStream("/untuneboyo/resource/jalan");
        
        if(ins != null)
        {
            try 
            {
                int c;
                StringBuffer buf = new StringBuffer();
                
                while ((c = ins.read()) != -1) 
                {
                    char huruf = (char)c;
                    
                    if(c == 13)
                    {
                        c = ins.read();
                        Jalan jalan = new Jalan(buf.toString());
                        Jalan.JalanCollection.addElement(jalan);
                        buf = new StringBuffer();
                        
                        if(c == -1)
                        {
                            break;
                        }
                    }
                    else
                    {
                        buf.append(huruf);
                    }
                }
                
                Jalan jalan = new Jalan(buf.toString());
                Jalan.JalanCollection.addElement(jalan);
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
}
