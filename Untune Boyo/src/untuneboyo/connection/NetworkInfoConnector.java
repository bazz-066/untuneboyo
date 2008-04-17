/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package untuneboyo.connection;

import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.SocketConnection;

/**
 *
 * @author baskoro
 */
public class NetworkInfoConnector implements Runnable
{
    private SocketConnection scSocket;
    private DataInputStream dis;
    private String rawInfo;
    private boolean isDone;
    
    public NetworkInfoConnector()
    {
        this.rawInfo = "";
        this.isDone = false;
    }
    
    public String GetNetworkInfo()
    {
        if(this.isDone)
        {
            return this.rawInfo;
        }
        else
        {
            return "blum slese";
        }
    }

    public void run() 
    {
        try 
        {
            this.scSocket = (SocketConnection) Connector.open("socket://localhost:6666");
            this.dis = this.scSocket.openDataInputStream();
            this.rawInfo = this.dis.readUTF();
            
            this.dis.close();
            this.scSocket.close();
        } 
        catch (IOException ex) 
        {
            ex.printStackTrace();
        }
        
        this.isDone = true;
    }
}
