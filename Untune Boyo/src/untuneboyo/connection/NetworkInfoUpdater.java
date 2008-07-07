/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package untuneboyo.connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import untuneboyo.persistent.DataManager;

/**
 *
 * @author baskoro
 */
public class NetworkInfoUpdater 
{
    private static final String UPLOAD_URL = "http://untuneboyo.ncc.its-sby.edu/upload.php";
    private static final String DOWNLOADBTS_URL = "http://untuneboyo.ncc.its-sby.edu/updatebts.php";
    private static final String DOWNLOADLOC_URL = "http://untuneboyo.ncc.its-sby.edu/updateloc.php";
    
    //private static final String UPLOAD_URL = "http://127.0.0.1:8080/TA/upload.php";
    //private static final String DOWNLOADBTS_URL = "http://127.0.0.1:8080/TA/updatebts.php";
    //private static final String DOWNLOADLOC_URL = "http://127.0.0.1:8080/TA/updateloc.php";
    
    public static final int UPLOAD = 0, DOWNLOAD = 1;
    
    public NetworkInfoUpdater()
    {
        
    }
    
    public void downloadUpdate() throws IOException
    {
        HttpConnection hcDownload = null;
        OutputStream os = null;
        InputStream is = null;
                
        try 
        {
            DataManager dm = new DataManager();
            String[] lastUpdate = dm.getLastUpdate();
            Vector newDataBTS = new Vector();
            Vector newDataLoc = new Vector();
            
            StringBuffer requestMsq = new StringBuffer();
            requestMsq.append("lastUpdate=");requestMsq.append(lastUpdate[0]);
            hcDownload = (HttpConnection) Connector.open(NetworkInfoUpdater.DOWNLOADBTS_URL);
            hcDownload.setRequestMethod(HttpConnection.POST);
            hcDownload.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            hcDownload.setRequestProperty("Content-Length",  Integer.toString(requestMsq.toString().trim().length()));
            
            os = hcDownload.openDataOutputStream();
            os.write(requestMsq.toString().trim().getBytes());
            os.flush();
            
            is = hcDownload.openInputStream();
            int read;
            StringBuffer respondMsg = new StringBuffer();
            String tglUpdateBTS = null, tglUpdateLoc = null;
            
            while((read = is.read()) != -1)
            {
                if((char)read != '\n')
                {
                    respondMsg.append((char)read);
                }
                
                else
                {
                    String data = respondMsg.toString();
                    String[] parsedData = NetworkInfoConnector.split(data,';');
                    
                    tglUpdateBTS = parsedData[5];
                    newDataBTS.addElement(data);
                    respondMsg = new StringBuffer();
                }
            }
            
            String data = respondMsg.toString();
            String[] parsedData = NetworkInfoConnector.split(data,';');

            if(parsedData.length == 6)
            {
                tglUpdateBTS = parsedData[5];
                newDataBTS.addElement(data);
            }
            respondMsg = new StringBuffer();
            
            is.close();
            os.close();
            hcDownload.close();
            
            requestMsq = new StringBuffer();
            requestMsq.append("lastUpdate=");requestMsq.append(lastUpdate[1]);
            hcDownload = (HttpConnection) Connector.open(NetworkInfoUpdater.DOWNLOADLOC_URL);
            hcDownload.setRequestMethod(HttpConnection.POST);
            hcDownload.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            hcDownload.setRequestProperty("Content-Length",  Integer.toString(requestMsq.length()));
            
            os = hcDownload.openDataOutputStream();
            os.write(requestMsq.toString().getBytes());
            os.flush();
            
            is = hcDownload.openInputStream();
            respondMsg = new StringBuffer();
            
            while((read = is.read()) != -1)
            {
                if((char)read != '\n')
                {
                    respondMsg.append((char)read);
                }
                else
                {
                    data = respondMsg.toString();
                    parsedData = NetworkInfoConnector.split(data,';');
                    
                    tglUpdateLoc = parsedData[3];
                    newDataLoc.addElement(data);
                    respondMsg = new StringBuffer();
                }
            }
            
            data = respondMsg.toString();
            parsedData = NetworkInfoConnector.split(data,';');
            
            if(parsedData.length == 4)
            {
                tglUpdateLoc = parsedData[3];
                newDataLoc.addElement(data);
            }
                        
            is.close();
            os.close();
            hcDownload.close();
            
            if(tglUpdateBTS != null && tglUpdateLoc != null)
            {
                dm.updateData(newDataBTS, newDataLoc, tglUpdateBTS, tglUpdateLoc);
            }
        } 
        catch (IOException ex) 
        {
            throw(ex);
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
