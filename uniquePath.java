public class uniquePath
{
	
	public static void main(String[] args) 
	{
		uniquePath test = new uniquePath();

		int m = 3;
		int n = 7;
		int numOfUniquePaths = test.uniquePaths(m, n);
		System.out.println("num of unique paths: " + numOfUniquePaths);

		int[][] obstacleGrid = new int[3][3];
		obstacleGrid[1][1] = 1;
		int numOfUniquePathsWithObstacles = test.uniquePathsWithObstacles(obstacleGrid);
		System.out.println("num of unique paths with obstacles: " + numOfUniquePathsWithObstacles);

		int[][] grid = {{1,2},{1,1}};
		int minPathSum = test.minPathSum(grid);
		System.out.println("min path sum is: " + minPathSum);
	}

	// A robot is located at the top-left corner of a m x n grid 

	// The robot can only move either down or right at any point in time. 
	// The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

	// How many possible unique paths are there?

	//sum[m][n] = sum[m][n-1] + sum[m-1][n] 
	public int uniquePaths(int m, int n) 
	{
        if(m==0 || n==0) 
        {
        	return 0;  
        }
		if(m ==1 || n==1) 
		{
			return 1; 
		}

		int [][] sum = new int[m][n];

		//each grid in first row has only one unique path to get to
		for(int i = 0;i < n;i++)
		{
			sum[0][i] = 1;
		}

		//each grid in first columne has only one unique to get to
		for(int i = 0;i < m;i++)
		{
			sum[i][0] = 1;
		}

		for(int i = 1;i < m;i++)
		{
			for(int j = 1;j < n;j++)
			{
				sum[i][j] = sum[i][j - 1] + sum[i - 1][j];
			}
		}

		return sum[m - 1][n - 1];
    }

    //Use rolling array
    public int uniquePathsRollingArray(int m, int n)
    {
    	if(m==0 || n==0) 
        {
        	return 0;  
        }

		if(m ==1 || n==1) 
		{
			return 1; 
		}

		int[] dp = new int[n];  
        dp[0] = 1; 

		for(int i= 0; i<m; i++)  
		{
            for(int j = 1; j<n; j++)
            {  
                dp[j] = dp[j] + dp[j-1]; 
            } 
        }

        return dp[n - 1]; 
    }

 	// Now consider if some obstacles are added to the grids. 
 	// How many unique paths would there be?

	// An obstacle and empty space is marked as 1 and 0 respectively in the grid.

	// For example,
	// There is one obstacle in the middle of a 3x3 grid as illustrated below.

	// [
	//   [0,0,0],
	//   [0,1,0],
	//   [0,0,0]
	// ]
	// The total number of unique paths is 2.
    public int uniquePathsWithObstacles(int[][] obstacleGrid) 
    {

		if(obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0)
		{
			return 0; 
		}

		int m = obstacleGrid.length;
		int n = obstacleGrid[0].length;

		int [][] sum = new int[m][n];

		//each grid in first row has only one unique path to get to
		//if there is an obstacle, the following grids will be unreachable
		for(int i = 0;i < n;i++)
		{
			if(obstacleGrid[0][i] == 1)
			{
				break;
			}

			sum[0][i] = 1;
		}

		//each grid in first columne has only one unique to get to
		//if there is an obstacle, the following grids will be unreachable
		for(int i = 0;i < m;i++)
		{
			if(obstacleGrid[i][0] == 1)
			{
				break;
			}
			sum[i][0] = 1;
		}
        
        for(int i = 1;i < m;i++)
		{
			for(int j = 1;j < n;j++)
			{
				if(obstacleGrid[i][j] == 1)
				{
					sum[i][j] = 0;
				}
				else
				{
					sum[i][j] = sum[i][j - 1] + sum[i - 1][j];
				}
			}
		}

		return sum[m - 1][n - 1];
    }

    //Rolling array -> uniquePathsWithObstacles
    public int uniquePathsWithObstaclesRollingArray(int[][] obstacleGrid) 
    {
    	int m = obstacleGrid.length;  
        int n = obstacleGrid[0].length;  
        
        int[] dp = new int[n];  

        if(obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) 
        {
        	return 0;
        }

        dp[0] = 1;

        for(int i = 0; i < m; i++)  
        {	
        	dp[0] = obstacleGrid[i][0]== 1 ? 0 : dp[0]; 

            for(int j = 1; j < n; j++)  
            {
                dp[j] = obstacleGrid[i][j] == 1 ? 0 : dp[j] + dp[j - 1];  
            }
        }

        return dp[n - 1];
    }

 	// Given a m x n grid filled with non-negative numbers, 
 	// find a path from top left to bottom right which minimizes the sum of all numbers along its path.
	// Note: You can only move either down or right at any point in time.
    // dp equation: dp[m][n] = min(dp[m][n - 1], dp[m - 1][n])
    public int minPathSum(int[][] grid) 
    {	
    	if(grid == null || grid.length == 0 || grid[0].length == 0)
    	{
    		return 0;
    	}

    	int m = grid.length;
		int n = grid[0].length;

		int [][] dp = new int[m][n];

		//initialize first grid
		dp[0][0] = grid[0][0];

    	//each grid in first row has only one unique path to get to
		for(int i = 1;i < n;i++)
		{

			dp[0][i] = dp[0][i - 1] + grid[0][i];
		}

		//each grid in first columne has only one unique to get to
		for(int i = 1;i < m;i++)
		{

			dp[i][0] = dp[i - 1][0] + grid[i][0];
		}

		for(int i = 1;i < m;i++)
		{	
			for(int j = 1;j < n;j++)
			{
				dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + grid[i][j];
			}
		}
        return dp[m - 1][n - 1];
    }

    //Rolling array
    public int minPathSumRollingArray(int[][] grid) 
    {	
    	if(grid == null || grid.length == 0 || grid[0].length == 0)
    	{
    		return 0;
    	}

    	int m = grid.length, n = grid[0].length;  
        int[] dp = new int[n];  
        for(int i=0; i<m; i++)  
        {
            for(int j=0; j<n; j++)
            {  
                if(i==0 && j==0) dp[0] = grid[0][0];  
                else if(i == 0) dp[j] = grid[0][j] + dp[j-1];  
                else if(j == 0) dp[j] = grid[i][0] + dp[0];  
                else dp[j] = grid[i][j] + Math.min(dp[j], dp[j-1]);  
            }  
        }
        return dp[n-1]; 
    }
}