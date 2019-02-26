public class search2DMatrixLc74AndLc240
{
	
	public static void main(String[] args) 
	{
		search2DMatrixLc74AndLc240 test = new search2DMatrixLc74AndLc240();
		int[][] testMatrix = {{1, 2, 3, 8}, {10, 20, 30, 40}, {100, 200, 300, 500}};
		//System.out.println(test.searchMatrix(testMatrix, 3));
		//System.out.println(test.searchMatrix(testMatrix, 501));

		int[][] cornerCases = {{1}, {3}, {5}};
		//System.out.println(test.searchMatrix(cornerCases, 0));

		int[][] testMatrixII = {{1, 3, 5, 7}, {2, 4, 7, 8}, {3, 5, 9, 10}}; 
		System.out.println(test.searchMatrixII(testMatrixII, 12));
	}

	public boolean searchMatrix(int[][] matrix, int target) 
	{
        // check corner case
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        if (matrix[0] == null || matrix[0].length == 0) {
            return false;
        }
			
        //Starting from the top right element of the first row
        return binarySearchMatrix(matrix, 0, matrix[0].length - 1, target);
    }

    public boolean binarySearchMatrix(int[][] matrix, int row, int column, int target)
    {	
    	if(row >= matrix.length || column < 0)
    	{
    		return false;
    	}

    	if(target > matrix[row][column])
    	{
    		return binarySearchMatrix(matrix, row + 1, column, target);
    	}
    	else if(target < matrix[row][column])
    	{
    		return binarySearchMatrix(matrix, row, column - 1, target);
    	}
    	else
    	{
    		return true;
    	}
    }

    public boolean searchMatrixIterative(int[][] matrix, int target) 
	{
        // check corner case
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        if (matrix[0] == null || matrix[0].length == 0) {
            return false;
        }
			
        int row = 0;
        int column = matrix[0].length - 1;

        while(row < matrix.length && column >= 0)
        {
        	if(target > matrix[row][column])
        	{
        		row++;
        	}
        	else if(target < matrix[row][column])
        	{
        		column--;
        	}
        	else
        	{
        		return true;
        	}
        }

        return false;
    }


 	// position: 0   1   2   3   4   5   6   7   8   9   10   11   
	// values:   1   3   5   7   10  11  16  20  23  30  34   50
	// row:      0   0   0   0   1   1   1   1   2   2    2    2
	// column:   0   1   2   3   0   1   2   3   0   1    2    3 
    public boolean searchMatrixAsOneDimensional(int[][] matrix, int target)
    {
    	// check corner case
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        if (matrix[0] == null || matrix[0].length == 0) {
            return false;
        }
			
        int row =  matrix.length;
        int column = matrix[0].length;

        int low = 0;
        int high = row*column - 1;

        while(low <= high)
        {	
        	int middle = low + (high - low) / 2;
        	int midValue = matrix[middle/column][middle%column];

        	if(target > midValue)
        	{
        		low = middle + 1;
        	}
        	else if(target < midValue)
        	{
        		high = middle - 1;
        	}
        	else
        	{
        		return true;
        	}
        }

        return false;	
    }

    public int searchMatrixII(int[][] matrix, int target)
    {
    	// check corner case
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        if (matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
			
        int row =  matrix.length - 1;
        int column = matrix[0].length - 1;
        int count = 0;
        int rowStart = 0;
        int columnStart = column;

        while(rowStart <= row && columnStart >= 0)
        {
        	if(target > matrix[rowStart][columnStart])
        	{
        		rowStart++;
        	}
        	else if(target < matrix[rowStart][columnStart])
        	{
        		columnStart--;
        	}
        	else
        	{
        		count++;
        		rowStart++;
        		columnStart--;
        	}
        }

        return count;
    }
}