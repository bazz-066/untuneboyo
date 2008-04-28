/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package untuneboyo.connection;

import java.io.IOException;
import java.io.InputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.SocketConnection;
import untuneboyo.MainMidLet;

/**
 *
 * @author baskoro
 */
public class NetworkInfoConnector
{
    private SocketConnection scSocket;
    private InputStream iStream;
    private String rawInfo;
    private boolean isDone;
    private MainMidLet midlet;
    
    private static final int BUFSIZE = 2048;
    
    public NetworkInfoConnector(MainMidLet midlet)
    {
        this.rawInfo = "";
        this.isDone = false;
        this.midlet = midlet;
    }
    
    public String GetNetworkInfo()
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
}
