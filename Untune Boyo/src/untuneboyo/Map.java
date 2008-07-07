/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package untuneboyo;

import java.io.IOException;
import java.util.Vector;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.Sprite;
import untuneboyo.pathplanning.Path;
import untuneboyo.pathplanning.Route;
import untuneboyo.pathplanning.StopPoint;

/**
 * @author baskoro
 */
public class Map extends Canvas implements CommandListener {
    private int xMap, yMap;
    public static final int MAPWIDTH = 2828, MAPHEIGHT = 3894, PIECEWIDTH = 283, PIECEHEIGHT = 390;
    private Path path;
    
    /**
     * constructor
     */
    public Map() {
        try {
	    // Set up this canvas to listen to command events
	    setCommandListener(this);
	    // Add the Exit command
	    addCommand(new Command("Exit", Command.EXIT, 1));
            this.xMap = 1800;
            this.yMap = 1800;
            this.path = null;
        } catch(Exception e) {
            e.printStackTrace();
        }
    } 
    
    /**
     * paint
     */
    public void paint(Graphics g) {
        
        this.paintMap(g);
    }
    
    private void paintMap(Graphics g)
    {
        //tentukan kotak camera peta
        this.getCamera(g);
        this.getStopPoint(g);
        this.getRouteLine(g);
    }
    
    public void setPath(Path path)
    {
        this.path = path;
    }
    
    public void clearPath()
    {
        this.path = null;
    }
    
    private void getRouteLine(Graphics g)
    {
        if(this.path != null)
        {
            Vector routes = this.path.getRute();
            Vector stopPoint = this.path.getStopPoint();
            int[] warna = new int[] { 0x00FF0000, 0x0000FF00, 0x000000FF };

            for(int i=0; i<routes.size(); i++)
            {
                Route rute = (Route) routes.elementAt(i);
                StopPoint stop = (StopPoint) stopPoint.elementAt(i);
                int indexAwal = rute.GetIndexTempatBerhenti(stop);

                stop = (StopPoint) stopPoint.elementAt(i+1);
                int indexAkhir = rute.GetIndexTempatBerhenti(stop);
                g.setColor(warna[i]);

                for(int j=indexAwal; j<indexAkhir; j++)
                {
                    StopPoint s1, s2;
                    s1 = rute.getTempatBerhenti(j);
                    s2 = rute.getTempatBerhenti(j+1);
                    
                    int x1, y1, x2, y2;
                    
                    int jarak = Math.abs(s1.getX() - s2.getX());
                    int jarak2 = Math.abs(s1.getY() - s2.getY());
                    
                    if(jarak < jarak2)
                    {
                        x1 = s1.getX() + 1;
                        y1 = s1.getY();
                        x2 = s1.getX() - 1;
                        y2 = s1.getY();
                    }
                    else
                    {
                        x1 = s1.getX();
                        y1 = s1.getY() + 1;
                        x2 = s1.getX();
                        y2 = s1.getY() - 1;
                    }
                    
                    g.drawLine(x1 - this.xMap, y1 - this.yMap, s2.getX() - this.xMap, s2.getY() - this.yMap);
                    g.drawLine(x2 - this.xMap, y2 - this.yMap, s2.getX() - this.xMap, s2.getY() - this.yMap);
                    g.drawLine(s1.getX() - this.xMap, s1.getY() - this.yMap, s2.getX() - this.xMap, s2.getY() - this.yMap);
                }
            }
        }
    }
    
    private void getStopPoint(Graphics g)
    {
        Vector vStop = StopPoint.GetStopPointFromPosisi(this.xMap, this.yMap, this.xMap + this.getWidth(), this.yMap + this.getHeight());
        
        for(int i=0; i<vStop.size(); i++)
        {
            StopPoint stop = (StopPoint) vStop.elementAt(i);
            int x = stop.getX() - this.xMap;
            int y = stop.getY() - this.yMap;
            g.fillArc(x, y, 8, 8, 0, 360);
        }
    }
    
    private void getCamera(Graphics g)
    {
        try 
        {
            int indexGambarKiri = (this.xMap / Map.PIECEWIDTH + 1) + ((this.yMap / Map.PIECEHEIGHT) * 10);
            
            Image topleft = Image.createImage(this.getFilename(indexGambarKiri));
            
            if(this.getHeight() < topleft.getHeight() - this.yMap % Map.PIECEHEIGHT && this.getWidth() < topleft.getWidth() - this.xMap % Map.PIECEWIDTH)
            {
                g.drawRegion(topleft, this.xMap % Map.PIECEWIDTH, this.yMap % Map.PIECEHEIGHT, this.getWidth(), this.getHeight(), Sprite.TRANS_NONE, 0, 0, Graphics.TOP | Graphics.LEFT);
            }
            else
            {
                g.drawRegion(topleft, this.xMap % Map.PIECEWIDTH, this.yMap % Map.PIECEHEIGHT, topleft.getWidth() - this.xMap % Map.PIECEWIDTH, topleft.getHeight() - this.yMap % Map.PIECEHEIGHT, Sprite.TRANS_NONE, 0, 0, Graphics.TOP | Graphics.LEFT);
                
                if(indexGambarKiri % 10 != 0 && this.getWidth() - (topleft.getWidth() - this.xMap % Map.PIECEWIDTH) > 0)
                {
                    Image topright = Image.createImage(this.getFilename(indexGambarKiri + 1));
                    g.drawRegion(topright, 0, this.yMap % Map.PIECEHEIGHT, this.getWidth() - (topleft.getWidth() - this.xMap % Map.PIECEWIDTH), topleft.getHeight() - this.yMap % Map.PIECEHEIGHT, Sprite.TRANS_NONE, topleft.getWidth() - this.xMap % Map.PIECEWIDTH + 1, 0, Graphics.TOP | Graphics.LEFT);
                    
                    if(indexGambarKiri < 91 && this.getHeight() > topleft.getHeight() - this.yMap % Map.PIECEHEIGHT)
                    {
                        Image bottomright = Image.createImage(this.getFilename(indexGambarKiri + 11));
                        g.drawRegion(bottomright, 0, 0, this.getWidth() - (topleft.getWidth() - this.xMap % Map.PIECEWIDTH), this.getHeight() - (topleft.getHeight() - this.yMap % Map.PIECEHEIGHT), Sprite.TRANS_NONE, topleft.getWidth() - this.xMap % Map.PIECEWIDTH + 1, topleft.getHeight() - this.yMap % Map.PIECEHEIGHT + 1, Graphics.TOP | Graphics.LEFT);
                    }
                }
                    
                if(indexGambarKiri < 91 && this.getHeight() > topleft.getHeight() - this.yMap % Map.PIECEHEIGHT)
                {
                    Image bottomleft = Image.createImage(this.getFilename(indexGambarKiri + 10));
                    g.drawRegion(bottomleft, this.xMap % Map.PIECEWIDTH, 0, topleft.getWidth() - this.xMap % Map.PIECEWIDTH, this.getHeight() - (topleft.getHeight() - this.yMap % Map.PIECEHEIGHT), Sprite.TRANS_NONE, 0, topleft.getHeight() - this.yMap % Map.PIECEHEIGHT + 1, Graphics.TOP | Graphics.LEFT);
                }
            }
        } 
        catch (IOException ex) 
        {
            
        }
    }
    
    private String getFilename(int index)
    {
        StringBuffer sbFilename = new StringBuffer();
        sbFilename.append("/untuneboyo/resource/peta/peta_");
        sbFilename.append(index);
        sbFilename.append(".jpg");
        
        return sbFilename.toString();
    }
    
    /**
     * Called when a key is pressed.
     */
    protected  void keyPressed(int keyCode) 
    {
        
    }
    
    /**
     * Called when a key is released.
     */
    protected  void keyReleased(int keyCode) 
    {
        if(keyCode == Canvas.KEY_NUM2)
        {
            this.yMap -= 20;
            if(this.yMap < 0)
            {
                this.yMap = 0;
            }
        }
        else if(keyCode == Canvas.KEY_NUM8)
        {
            this.yMap += 20;
            if(this.yMap > Map.MAPHEIGHT - this.getHeight())
            {
                this.yMap = Map.MAPHEIGHT - this.getHeight();
            }
        }
        else if(keyCode == Canvas.KEY_NUM4)
        {
            this.xMap -= 20;
            if(this.xMap < 0)
            {
                this.xMap = 0;
            }
        }
        else if(keyCode == Canvas.KEY_NUM6)
        {
            this.xMap += 20;
            if(this.xMap > Map.MAPWIDTH - this.getWidth())
            {
                this.xMap = Map.MAPWIDTH - this.getWidth();
            }
        }
        
        this.repaint();
    }

    /**
     * Called when a key is repeated (held down).
     */
    protected  void keyRepeated(int keyCode) {
    }
    
    /**
     * Called when the pointer is dragged.
     */
    protected  void pointerDragged(int x, int y) {
    }

    /**
     * Called when the pointer is pressed.
     */
    protected  void pointerPressed(int x, int y) {
    }

    /**
     * Called when the pointer is released.
     */
    protected  void pointerReleased(int x, int y) {
    }
    
    /**
     * Called when action should be handled
     */
    public void commandAction(Command command, Displayable displayable) {
    }

}