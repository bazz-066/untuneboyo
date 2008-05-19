/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package untuneboyo;

import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.Sprite;

/**
 * @author baskoro
 */
public class Map extends Canvas implements CommandListener {
    private int xMap, yMap;
    private static final int MAPWIDTH = 2828, MAPHEIGHT = 3894, PIECEWIDTH = 283, PIECEHEIGHT = 390;
    
    /**
     * constructor
     */
    public Map() {
        try {
	    // Set up this canvas to listen to command events
	    setCommandListener(this);
	    // Add the Exit command
	    addCommand(new Command("Exit", Command.EXIT, 1));
            this.xMap = 886;
            this.yMap = 666;
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
        
    }
    
    private void getCamera(Graphics g)
    {
        try 
        {
            int indexGambarKiri = (this.xMap / Map.PIECEWIDTH + 1) + ((this.yMap / Map.PIECEHEIGHT + 1) * 10);
            
            Image topleft = Image.createImage(this.getFilename(indexGambarKiri));
            
            if(this.getHeight() < topleft.getHeight() - this.xMap % Map.PIECEWIDTH && this.getWidth() < topleft.getWidth() - this.yMap % Map.PIECEHEIGHT)
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