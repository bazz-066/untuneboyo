/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package untuneboyo.pathplanning;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author baskoro
 */
public class AdjacencyMatriks 
{
    private int[][] adjMatriks;
    private int size;
    
    public AdjacencyMatriks(int size)
    {
        this.size = size;
        this.adjMatriks = new int[size][size];
    }
    
    public int[][] GetMatriks()
    {
        return this.adjMatriks;
    }
    
    public int LoadMatriksFromFile()
    {
        InputStream ins = getClass().getResourceAsStream("/untuneboyo/resource/adjmatriks");
        
        if(ins != null)
        {
            try 
            {
                int c, row = 0, col = 0;
                while ((c = ins.read()) != -1) 
                {
                    char huruf = (char)c;
                    
                    if(huruf == ' ')
                    {
                        continue;
                    }
                    else if(c == 13)
                    {
                        row++;
                        col = 0;
                        c = ins.read();
                        
                        if(c == -1)
                        {
                            break;
                        }
                    }
                    else
                    {
                        this.adjMatriks[row][col] = Integer.parseInt(new String(new char[] { huruf }));
                        col++;
                    }
                }
            } 
            catch (IOException ex) 
            {
                ex.printStackTrace();
                return -1;
            }
        }
        else
        {
            return -1;
        }
        
        return 0;
    }
    
    public int[][] GetPower2Matriks()
    {
        int[][] adjMatriksPow2 = new int[size][size];
        
        for(int row=0; row<this.size; row++)
        {
            for(int col=0; col<this.size; col++)
            {
                int tmp = 0;
                for(int k=0; k<this.size; k++)
                {
                    tmp += this.adjMatriks[row][k] * this.adjMatriks[k][col];
                }
                
                adjMatriksPow2[row][col] = tmp;
            }
        }
        
        return adjMatriksPow2;
    }
    
    public int[][] GetPower3Matriks()
    {
        int[][] adjMatriksPow3 = this.GetPower2Matriks();
        
        for(int row=0; row<this.size; row++)
        {
            for(int col=0; col<this.size; col++)
            {
                int tmp = 0;
                for(int k=0; k<this.size; k++)
                {
                    tmp += this.adjMatriks[row][k] * this.adjMatriks[k][col];
                }
                
                adjMatriksPow3[row][col] = tmp;
            }
        }
        
        return adjMatriksPow3;
    }
}
