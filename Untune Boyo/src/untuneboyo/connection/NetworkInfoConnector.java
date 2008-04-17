/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package untuneboyo.connection;

import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.SocketConnection;
import javax.microedition.lcdui.StringItem;

/**
 *
 * @author baskoro
 */
public class NetworkInfoConnector extends Thread
{
    private SocketConnection scSocket;
    private DataInputStream dis;
    private String rawInfo;
    private boolean isDone;
    private StringItem strItem;
    
    public NetworkInfoConnector(StringItem strItem)
    {
        this.rawInfo = "";
        this.isDone = false;
        this.strItem = strItem;
    }
    
    public String GetNetworkInfo()
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
        
        //if(this.isDone)
        {
            return this.rawInfo;
        }
        //else
        /*{
            return "blum slese";
        }*/
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
        
        this.strItem.setText(this.rawInfo);
        this.isDone = true;
    }
}
