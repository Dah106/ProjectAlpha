import java.util.*;

public class DFS 
{	
	// Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
	// Each element is either an integer, or a list -- whose elements may also be integers or other lists.
	// Example 1:
	// Given the list [[1,1],2,[1,1]], return 10. (four 1's at depth 2, one 2 at depth 1)
	// Example 2:
	// Given the list [1,[4,[6]]], return 27. (one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27)
    public int depthSum(List<NestedInteger> nestedList) 
    {	
    	int depth = 1;

    	if(nestedList == null || nestedList.size() == 0)
    	{
    		return 0;
    	}

    	return depthSumDFS(nestedList, depth);
    }

    public int depthSumDFS(List<NestedInteger> nestedList, int depth)
    {	
    	int sum = 0;
    	for(NestedInteger object : nestedList)
    	{
    		if(object.isInteger())
    		{
    			sum += object.getInteger() * depth;
    		}
    		else
    		{
    			depthSumDFS(object.getList(), depth + 1);
    		}
    	}

    	return sum;
    }

 	// Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

	// Example 1:

	// 11110
	// 11010
	// 11000
	// 00000
	// Answer: 1

	// Example 2:

	// 11000
	// 11000
	// 00100
	// 00011
	// Answer: 3
    public int numIslands(char[][] grid) 
    {
        if (grid == null || grid.length == 0 || grid[0].length == 0) 
        {
        	return 0;
        }

    	int count = 0;

    	for(int i = 0;i < grid.length;i++)
    	{
    		for(int j = 0;j < grid[0].length;j++)
    		{
    			//encounter an island
    			if(grid[i][j] == '1')
    			{
    				count++;//increase the counter as we will merge surrounding islands
    				numIslandsDFS(grid, i, j);
    			}
    		}
    	}

    	return count;
    }

    public void numIslandsDFS(char[][] grid, int i, int j)
    {	
    	//if the check is out of boudary, halt the search
    	if(i < 0 || j < 0 || i > grid.length - 1 || j > grid[0].length - 1)
    	{
    		return;
    	}

    	//if the cell is not an island
    	//halt the search
    	if(grid[i][j] != '1')
    	{
    		return;
    	}

    	grid[i][j] = '0';

    	//keep searching all four directions
    	numIslandsDFS(grid, i - 1, j);
    	numIslandsDFS(grid, i + 1, j);
    	numIslandsDFS(grid, i, j - 1);
    	numIslandsDFS(grid, i, j + 1);

    }
}

interface NestedInteger
{
	public boolean isInteger();

	public Integer getInteger();

	public List<NestedInteger> getList();
}