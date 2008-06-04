/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package untuneboyo.connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

/**
 *
 * @author baskoro
 */
public class NetworkInfoUpdater 
{
    private static final String UPLOAD_URL = "http://127.0.0.1:6666/TA/upload.php";
    private static final String DOWNLOAD_URL = "http://127.0.0.1:6666/TA/download.php";
    public static final int UPLOAD = 0, DOWNLOAD = 1;
    
    public NetworkInfoUpdater()
    {
        
    }
    
    public void downloadUpdate()
    {
        HttpConnection hcDownload = null;
        OutputStream os = null;
        InputStream is = null;
        
        try 
        {
            hcDownload = (HttpConnection) Connector.open(NetworkInfoUpdater.DOWNLOAD_URL);
        } 
        catch (IOException ex) 
        {
            ex.printStackTrace();
        }
    }
    
    public int uploadNewData(String MCC, String MNC, String LAC, String CellId, String signal, String stoppoint)
    {
        HttpConnection hcUpload = null;
        OutputStream os = null;
        InputStream is = null;
        int status;
        
        try 
        {
            StringBuffer msg = new StringBuffer();
            msg.append("MCC=");msg.append(MCC);
            msg.append("&MNC=");msg.append(MNC);
            msg.append("&LAC=");msg.append(LAC);
            msg.append("&CellID=");msg.append(CellId);
            msg.append("&signal=");msg.append(signal);
            msg.append("&stoppoint=");msg.append(stoppoint);
            hcUpload = (HttpConnection) Connector.open(NetworkInfoUpdater.UPLOAD_URL);
            hcUpload.setRequestMethod(HttpConnection.POST);
            hcUpload.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            hcUpload.setRequestProperty("Content-Length",  Integer.toString(msg.length()));
            
            os = hcUpload.openDataOutputStream();
            os.write(msg.toString().getBytes());
            os.flush();
            
            is = hcUpload.openInputStream();
            long length = hcUpload.getLength();
            
            if(length > 0)
            {
                byte[] bResponse = new byte[(int)length];
                is.read(bResponse);
                String response = new String(bResponse);
                
                if(response.equals("sukses"))
                {
                    status = 0;
                }
                else
                {
                    status = -1;
                }
            }
            else status = -1;
        } 
        catch (IOException ex) 
        {
            ex.printStackTrace();
            status = -1;
        }
        finally
        {
            try
            {
                if(os != null) os.close();
                if(is != null) is.close();
                if(hcUpload != null) hcUpload.close();
            }
            catch(IOException ex)
            {
                ex.printStackTrace();
            }
        }
        
        return status;
    }
}
