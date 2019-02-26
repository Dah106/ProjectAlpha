import java.util.*;

public class threeSum
{	
	// Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
	// Find all unique triplets in the array which gives the sum of zero.
	// For example, given array S = {-1 0 1 2 -1 -4},
	// A solution set is:
	// (-1, 0, 1)
	// (-1, -1, 2)
	public List<List<Integer>> threeSum(int[] nums) 
	{
        List<List<Integer>> resultList = new ArrayList<List<Integer>>();
       
        if(nums == null || nums.length <= 2)
        {	
        	return resultList;
        }

        Arrays.sort(nums);
        int length = nums.length;
        for(int i = 0;i < length;i++)
        {
        	if(i > 0 && nums[i - 1] == nums[i])
        	{
        		continue;
        	}

        	int target = 0 - nums[i];
        	int left = i + 1;
        	int right = length - 1;

        	while(left < right)
        	{	
        		int sum = nums[left] + nums[right];
        		if(sum == target)
        		{	
                    List<Integer> tempList = new ArrayList<Integer>();
                    tempList.add(nums[i]);
                    tempList.add(nums[left]);
                    tempList.add(nums[right]);
        			resultList.add(tempList);
                    left++;
                    right--;
        			while(left < right && nums[left] == nums[left - 1])
        			{
        				left++;
        			}
        			while(left < right && nums[right] == nums[right + 1])
        			{
        				right--;
        			}
        		}
        		else if(sum < target)
        		{
        			left++;
        		}
        		else
        		{
        			right--;
        		}
        	}
        }

        return resultList;
    }

 	// Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.
	// For example, given nums = [-2, 0, 1, 3], and target = 2.
	// Return 2. Because there are two triplets which sums are less than 2:
	// [-2, 0, 1]
	// [-2, 0, 3]
	public int threeSumSmaller(int[] nums, int target) 
	{	
        if(nums == null || nums.length <= 2)
        {	
        	return 0;
        }
        int count = 0;

        Arrays.sort(nums);
        int length = nums.length;
        for(int i = 0;i < length;i++)
        {
        	int left = i + 1;
        	int right = length - 1;
        	while(left < right)
        	{
        		int sum = nums[i] + nums[left] + nums[right];
        		if(sum < target)
        		{
        			count += right - left;//if the gap is 1, there is 1 combo, if the gap is 2, there are 2
        			left++;
        		}
        		else
        		{
        			right--;
        		}
        	}
        }

        return count;
    }

    // Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. 
    // Return the sum of the three integers. 
    // You may assume that each input would have exactly one solution.
    // For example, given array S = {-1 2 1 -4}, and target = 1.
    // The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
    public int threeSumClosest(int[] nums, int target) 
    {
        if(nums == null || nums.length <= 2)
        {	
        	return 0;
        }

        int result = nums[0] + nums[1] + nums[2];

        Arrays.sort(nums);
        int length = nums.length;
        for(int i = 0;i < length;i++)
        {
        	int left = i + 1;
        	int right = length - 1;
        	while(left < right)
        	{
        		int sum = nums[i] + nums[left] + nums[right];
        		if(sum < target)
        		{
        			left++;
        		}
        		else
        		{
        			right--;
        		}

        		if(Math.abs(sum - target) < Math.abs(result - target))
        		{
        			result = sum;
        		}
        	} 
        }

        return result;
    }
}