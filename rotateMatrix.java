import java.util.*;

public class rotateMatrix
{
	// You are given an n x n 2D matrix representing an image.
    // Rotate the image by 90 degrees (clockwise).
    // Follow up:
    // Could you do this in-place?
    // Time: O(n * m)
    // Space: O(n * m)
    public void rotate(int[][] matrix) 
    {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) 
        {
            return;
        }

        int m = matrix.length;
        int n = matrix[0].length;
        
        int[][] result = new int[n][m];

        for(int i = 0; i < m; i++)
        {
            for(int j = 0; j < n; j++)
            {	
            	//copy first row to last column
            	//copy second row to second to last column
            	//...
                result[j][m - 1 - i] = matrix[i][j];
            }
        }
        
        //copy and back to original array
        for(int i=0;i<m;i++)
        {
            for(int j=0; j<n; j++)
            {
                 matrix[i][j] = result[i][j];
            }
        }
    }

    // Time: O(n * m)
    // Space: O(1)
    public void rotateOptimized(int[][] matrix) 
    {	
  		// The idea was firstly transpose the matrix and then flip it symmetrically. For instance,

		// 1  2  3             
		// 4  5  6
		// 7  8  9
		// after transpose, it will be swap(matrix[i][j], matrix[j][i])

		// 1  4  7
		// 2  5  8
		// 3  6  9
		// Then flip the matrix horizontally. (swap(matrix[i][j], matrix[i][matrix.length-1-j])


    	if (matrix == null || matrix.length == 0 || matrix[0].length == 0) 
        {
            return;
        }

        int m = matrix.length;
        int n = matrix[0].length;
        
        //transpose the matrix first
        for(int i = 0; i < m; i++)
        {
            for(int j = i; j < n; j++)
            {
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        //flip the matrix horizontally, swaping the first column with the last column
        for(int i =0 ; i < m; i++)
        {
            for(int j = 0; j < n / 2; j++)
            {
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[i][m - 1 - j];
                matrix[i][m - 1 - j] = temp;
            }
        }
    }
}