/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package untuneboyo.connection;

import java.io.IOException;
import java.io.InputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.SocketConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.StringItem;
import untuneboyo.MainMidLet;

/**
 *
 * @author baskoro
 */
public class NetworkInfoConnector extends Thread
{
    private SocketConnection scSocket;
    private InputStream iStream;
    private String rawInfo;
    private boolean isDone;
    private MainMidLet midlet;
    
    public NetworkInfoConnector(MainMidLet midlet)
    {
        this.rawInfo = "";
        this.isDone = false;
        this.midlet = midlet;
    }
    
    public String GetNetworkInfo()
    {
        try 
        {
            StringBuffer sb = new StringBuffer();
            int c = 0;
            
            this.scSocket = (SocketConnection) Connector.open("socket://127.0.0.1:6666");
            this.iStream = this.scSocket.openInputStream();
            
            while((c = this.iStream.read()) != -1)
            {
                sb.append((char)c);
                sb.append(" ");
            }
            
            this.rawInfo = sb.toString();
            
            this.iStream.close();
            this.scSocket.close();
        } 
        catch (IOException ex) 
        {
            this.rawInfo = "exception : " + ex.getMessage();
        }
        
        /*Alert alert = this.midlet.getAlert();
        alert.setString("hasil : " + this.rawInfo);
        this.midlet.getDisplay().setCurrent(alert, alert);*/
        
        return this.rawInfo;
    }

    public void run() 
    {
        try 
        {
            StringBuffer sb = new StringBuffer();
            int c = 0;
            
            this.scSocket = (SocketConnection) Connector.open("socket://127.0.0.1:6666");
            this.iStream = this.scSocket.openInputStream();
            while((c = this.iStream.read()) != -1)
            {
                sb.append((char)c);
            }
            
            this.rawInfo = sb.toString();
            
            this.iStream.close();
            this.scSocket.close();
        } 
        catch (IOException ex) 
        {
            this.rawInfo = ex.getMessage();
        }
        
        //this.strItem = new StringItem("str", null, Item.PLAIN);
        //this.strItem.setText(this.rawInfo);
        this.isDone = true;
    }
}
