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
    
    public String[][] getBTSData(int start, int length)
    {
        try {
            ObjectSet cells = this.pmManager.find(Class.forName("untuneboyo.persistent.BTSData"), null, new CellComparator());
            ObjectSet cellLocs = this.pmManager.find(Class.forName("untuneboyo.persistent.CellLocation"), null, new SignalComparator());
            
            if(start >= cellLocs.size())
            {
                return new String[][]{ {" ", " "} };
            }
            else
            {
                int batas;
                String[][] result;
                
                if(start + length >= cellLocs.size())
                {
                    batas = cellLocs.size();
                    result = new String[batas-start][2];
                }
                else
                {
                    batas = start + length;
                    result = new String[length][2];
                }
                
                int index = 0, counter = 0;
                
                for(int i=0; i<cells.size(); i++)
                {
                    BTSData cell = (BTSData) cells.get(i);

                    ObjectSet locations = this.pmManager.find(Class.forName("untuneboyo.persistent.CellLocation"), new CellLocationFilter(cell.key, "", true), new SignalComparator());
                    for(int j=0; j<locations.size(); j++)
                    {
                        if(counter >= start && counter < start + length)
                        {
                            result[index][0] = cell.CellID;

                            CellLocation loc = (CellLocation) locations.get(j);

                            result[index][1] = loc.stopPoint;
                            index++;
                        }
                        
                        counter++;
                    }
                }
                
                return result;
            }
        } catch (FloggyException ex) {
            return new String[][]{ {" ", " "} };
        } catch (ClassNotFoundException ex) {
            return new String[][]{ {" ", " "} };
        }
    }
    
    public StopPoint[] getLocation(String MCC, String MNC, String LAC, String CellId)
    {
        try 
        {
            ObjectSet cells = this.pmManager.find(Class.forName("untuneboyo.persistent.BTSData"), new CellFilter(MCC, MNC, LAC, CellId), new CellComparator());
            if(cells.size() > 0)
            {
                BTSData cell = (BTSData) cells.get(0);

                ObjectSet locations = this.pmManager.find(Class.forName("untuneboyo.persistent.CellLocation"), new CellLocationFilter(cell.key, "", true), new SignalComparator());

                StopPoint[] stops = new StopPoint[locations.size()];
                for(int i=0; i<locations.size(); i++)
                {
                    CellLocation loc = (CellLocation) locations.get(i);
                    stops[i] = StopPoint.GetStopPointFromName(loc.stopPoint);
                }

                return stops;
            }
            else
            {
                return null;
            }
        } 
        catch (ClassNotFoundException ex) 
        {
            return null;
        }        
        catch (FloggyException ex) 
        {
            return null;
        }
    }
    
    public void updateData(Vector newDataBTS, Vector newDataLoc, String lastUpdateBTS, String lastUpdateLoc)
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
                LastUpdate lu = new LastUpdate();
                
                try
                {
                    this.pmManager.load(lu, 1);
                }
                catch(FloggyException ex)
                {
                    
                }
                
                lu.lastUpdateBTS = lastUpdateBTS;
                lu.lastUpdateLoc = lastUpdateLoc;
                
                this.pmManager.save(lu);
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
