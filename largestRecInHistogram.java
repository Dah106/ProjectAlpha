import java.util.*;
public class largestRecInHistogram
{	
	//Time: O(n^2)
	//Space: O(1)
	public int largestRecInHistogram(int[] height)
	{
		if(height == null || height.length == 0)
        {
            return 0;
        }
        int best = height[0];
		int n = height.length;
		for(int i = 0;i < n;i++)
		{
			int min = height[i];
			for(int j = i;j < n;j++)
			{
				min = Math.min(min, height[j]);
				best = Math.max(best, min * (j - i + 1));
			}
		}

		return best;
	}

	//Time: O(n)
	//Space: O(n)
	//http://www.cnblogs.com/lichen782/p/leetcode_Largest_Rectangle_in_Histogram.html
	public int largestRecInHistogramOptimized(int[] height)
	{	
		if(height == null || height.length == 0)
        {
            return 0;
        }
        
        Stack<Integer> stack = new Stack<Integer>();
        int maxArea = 0;
        int current = 0;
        for (int i = 0; i <= height.length; i++) 
        {	
        	//inserting a fake bar to to handle the last bar
        	if(i == height.length)
        	{
        		current = -1;
        	}
        	else
        	{
        		current = height[i];
        	}

        	//
            while (!stack.isEmpty() && current <= height[stack.peek()]) 
            {
                int h = height[stack.pop()];
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, h * w);
            }
            stack.push(i);
        }
        
        return maxArea;

	}

	public static void main(String[] args) 
	{
		largestRecInHistogram test = new largestRecInHistogram();
		int [] arr = {2,1,5,6,2,3};
		int largestArea = test.largestRecInHistogram(arr);
		System.out.println("the largest rec area in histogram is: " + largestArea);	
	}
}