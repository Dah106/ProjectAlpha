import java.util.*;

public class fourSum
{	
	// Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target?
	// Find all unique quadruplets in the array which gives the sum of target.
	// For example, given array S = {1 0 -1 0 -2 2}, and target = 0.
    // A solution set is:
    // (-1,  0, 0, 1)
    // (-2, -1, 1, 2)
    // (-2,  0, 0, 2)
	public List<List<Integer>> fourSum(int[] nums, int target) 
	{
        List<List<Integer>> resultList = new ArrayList<List<Integer>>();
       
        if(nums == null || nums.length <= 3)
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

        	for(int j = i + 1;j < length;j++)
        	{	
        		if(j > i + 1 && nums[j - 1] == nums[j])
	        	{
	        		continue;
	        	}

        		int left = j + 1;
        		int right = length - 1;
        		while(left < right)
        		{
        			int sum = nums[i] + nums[j] + nums[left] + nums[right];
        			if(sum == target)
        			{
        				List<Integer> tempList = new ArrayList<Integer>();
        				tempList.add(nums[i]);
        				tempList.add(nums[j]);
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
        }

        return resultList;
    }
}