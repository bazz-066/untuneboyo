/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package untuneboyo.connection;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.SocketConnection;
import untuneboyo.UntuneBoyo;

/**
 *
 * @author baskoro
 */
public class NetworkInfoConnector
{
    private SocketConnection scSocket;
    private InputStream iStream;
    private boolean isDone;
    private UntuneBoyo midlet;
    
    private static final int BUFSIZE = 2048;
    
    public NetworkInfoConnector(UntuneBoyo midlet)
    {
        this.isDone = false;
        this.midlet = midlet;
    }
    
    private String GetRawNetworkInfo()
    {
        String coba;
        int index = 0;
        byte[] buf = new byte[NetworkInfoConnector.BUFSIZE];
        
        try 
        {
            StringBuffer sb = new StringBuffer();
            
            this.scSocket = (SocketConnection) Connector.open("socket://127.0.0.1:6666");
            this.iStream = this.scSocket.openInputStream();
            int read;
            
            while(true)
            {
                read = this.iStream.read(buf, index, BUFSIZE - index);
                                
                if(read == -1)
                {
                    break;
                }
                else
                {
                    index += read;
                    sb.append(new String(buf, 0, index));
                }
            }
            
            coba = sb.toString();
            
            this.iStream.close();
            this.scSocket.close();
        } 
        catch (IOException ex) 
        {
            coba = "exception : " + ex.getMessage();
        }
        
        return coba;
    }
    
    public static String[] split(String s, char c)
    {
        Vector parts = new Vector();
        if ( s != null )
        {  
            int lastfound = 0;
            int pos = 0;
            while ( (lastfound = s.indexOf(c,pos)) != - 1 )
            {
                parts.addElement(s.substring(pos,lastfound));
                pos = lastfound+1;
            }
            if ( pos <  s.length() ) parts.addElement(s.substring(pos));
        }
        
        String[] result = new String[parts.size()];
        for(int i=0; i<parts.size(); i++)
        {
            result[i] = parts.elementAt(i).toString();
        }
        
        return result;
    }
    
    public String[] GetNetworkInfo()
    {
        String rawInfo = this.GetRawNetworkInfo();
        String[] networkInfo = split(rawInfo, ' ');
        
        return networkInfo;
    }
}
