/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package untuneboyo.pathplanning;

/**
 *
 * @author baskoro
 */
public class ConnectivityMatriks 
{
    public static int[][] ConnMatriks;
    public static int size;
    
    public ConnectivityMatriks()
    {
        
    }
    
    public static void GenerateMatriks()
    {
        ConnMatriks = new int[size][size];
        
        for(int i=0; i<size; i++)
        {
            for(int j=0; j<=i; j++)
            {
                if(i == j)
                {
                    ConnMatriks[i][j] = 0;
                }
                else
                {
                    CommonStops cs = CommonStops.GetCommonStops((Route)Route.RouteCollection.elementAt(i), (Route)Route.RouteCollection.elementAt(j));
                    ConnMatriks[i][j] = cs.GetJumlahTempatBerhenti();
                    ConnMatriks[j][i] = cs.GetJumlahTempatBerhenti();
                }
            }
        }
    }
    
    public static int[][] GetPower2Matriks()
    {
        int[][] connMatriksPow2 = new int[size][size];
        
        for(int row=0; row<size; row++)
        {
            for(int col=0; col<size; col++)
            {
                if(row == col)
                {
                    connMatriksPow2[row][col] = 0;
                }
                else
                {
                    int tmp = 0;
                    for(int k=0; k<size; k++)
                    {
                        tmp += ConnMatriks[row][k] * ConnMatriks[k][col];
                    }

                    connMatriksPow2[row][col] = tmp;
                }
            }
        }
        
        return connMatriksPow2;
    }
}
