import java.util.*;

public class jumpGame
{	
	public static void main(String[] args) 
	{
		jumpGame test = new jumpGame();

		int [] array = {3, 2, 1, 1, 0};
		System.out.println("can jump " + Arrays.toString(array) + " : " + test.canJump(array));
	
		int [] array1 = {1, 2};
		System.out.println("can jump min step is: " + test.jump(array1));
	}
	// Given an array of non-negative integers, 
	// you are initially positioned at the first index of the array.
	// Each element in the array represents your maximum jump length at that position.
	// Determine if you are able to reach the last index.

	// For example:
	// A = [2,3,1,1,4], return true.
	// A = [3,2,1,0,4], return false.

	//use greedy approach
	public boolean canJump(int[] A) 
	{	
		if(A.length <= 1)
		{
        	return true;
        }
		  //largest index it
		  int maxCoverIndex = A[0];
   		for(int i = 0;i < A.length;i++)
   		{	
   			//in order to reach index i, the maxCoverIndex must at least >= i
   			if(i > maxCoverIndex || maxCoverIndex >= (A.length - 1)) 
   			{
   				break;
   			}

            maxCoverIndex = Math.max(maxCoverIndex, i + A[i]);
   		}

   		if(maxCoverIndex >= A.length - 1)
   		{
   			return true;
   		}

   		return false;
    }

 	// Given array A = [2,3,1,1,4]
	// The minimum number of jumps to reach the last index is 2. 
	// (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
	// http://www.cnblogs.com/lichen782/p/leetcode_Jump_Game_II.html
    public int jump(int[] A) 
    {
     	int stepCount = 0;
        int last = 0;//the last index that has been covered
        int curr = 0;//furtherst reachable index 

        int n = A.length;
        for (int i = 0; i < n; ++i) 
        {	
        	//if current position has not been covered yet
        	//update with the reachable index
        	//increase count
            if (i > last) 
            {
                last = curr;
                stepCount++;
            }
            curr = Math.max(curr, i+A[i]);
        }

        return stepCount;   
    }
}