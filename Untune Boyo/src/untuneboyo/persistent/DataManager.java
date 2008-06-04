/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package untuneboyo.persistent;

import java.util.Vector;
import net.sourceforge.floggy.persistence.FloggyException;
import net.sourceforge.floggy.persistence.ObjectSet;
import net.sourceforge.floggy.persistence.PersistableManager;
import untuneboyo.connection.NetworkInfoConnector;
import untuneboyo.pathplanning.StopPoint;

/**
 *
 * @author baskoro
 */
public class DataManager 
{
    PersistableManager pmManager;
    
    public DataManager()
    {
        this.pmManager = PersistableManager.getInstance();
    }
    
    public StopPoint[] getLocation(String MCC, String MNC, String LAC, String CellId)
    {
        try 
        {
            ObjectSet cells = this.pmManager.find(BTSData.class, new CellFilter(MCC, MNC, LAC, CellId), new CellComparator());
            BTSData cell = (BTSData) cells.get(0);
            
            ObjectSet locations = this.pmManager.find(CellLocation.class, new CellLocationFilter(cell.key, "", true), new SignalComparator());
            
            StopPoint[] stops = new StopPoint[locations.size()];
            for(int i=0; i<locations.size(); i++)
            {
                CellLocation loc = (CellLocation) locations.get(i);
                stops[i] = StopPoint.GetStopPointFromName(loc.stopPoint);
            }
            
            return stops;
        } 
        catch (FloggyException ex) 
        {
            return null;
        }
    }
    
    public void updateData(Vector newDataBTS, Vector newDataLoc)
    {
        for(int i=0; i<newDataBTS.size(); i++)
        {
            try 
            {
                BTSData cell = new BTSData();
                String[] cellData = NetworkInfoConnector.split(newDataBTS.elementAt(i).toString(), ';');

                cell.key = cellData[0];
                cell.MCC = cellData[1];
                cell.MNC = cellData[2];
                cell.LAC = cellData[3];
                cell.CellID = cellData[4];

                int id = this.pmManager.save(cell);
            } 
            catch (FloggyException ex) 
            {
                ex.printStackTrace();
            }
        }
        
        for(int i=0; i<newDataLoc.size(); i++)
        {
            try 
            {
                CellLocation loc = new CellLocation();
                String[] cellData = NetworkInfoConnector.split(newDataLoc.elementAt(i).toString(), ';');

                loc.cellKey = cellData[0];
                loc.signal = Integer.parseInt(cellData[1]);
                loc.stopPoint = cellData[2];

                int id = this.pmManager.save(loc);
            } 
            catch (FloggyException ex) 
            {
                ex.printStackTrace();
            }
        }
    }
    
    public String[] getLastUpdate()
    {
        String lastUpdateBTS, lastUpdateLoc;
        
        try 
        {
            LastUpdate lu = new LastUpdate();
            this.pmManager.load(lu, 1);
            lastUpdateBTS = lu.lastUpdateBTS;
            lastUpdateLoc = lu.lastUpdateLoc;
        } 
        catch (FloggyException ex) 
        {
            lastUpdateBTS = "Jan 1 1900 00:00AM";
            lastUpdateLoc = "Jan 1 1900 00:00AM";
        }
        
        String[] lastUpdate = new String[2];
        lastUpdate[0] = lastUpdateBTS;
        lastUpdate[1] = lastUpdateLoc;
        
        return lastUpdate;
    }
}
