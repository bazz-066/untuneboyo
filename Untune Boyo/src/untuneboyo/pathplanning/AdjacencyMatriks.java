/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package untuneboyo.pathplanning;

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
