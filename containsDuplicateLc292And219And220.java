import java.util.*;
/*
	Given an array of integers, find if the array contains any duplicates. 
	Your function should return true if any value appears at least twice in the array, 
	and it should return false if every element is distinct.
*/
public class containsDuplicateLc292And219And220
{
	public static void main(String[] args) 
	{
		containsDuplicateLc292And219And220 test = new containsDuplicateLc292And219And220();
		int testArr [] = {1, 2, 13, 4, 5, 15, 14};	
		System.out.println(test.containsDuplicate(testArr));

		int testArr1 [] = {1, 2, 3, 2, 5, 15, 14};	
		System.out.println(test.containsNearbyDuplicate(testArr1, 2));
	}

	public boolean containsDuplicate(int[] nums) 
	{
        Arrays.sort(nums);
        for(int i = 1; i < nums.length;i++)
        {
        	if(nums[i - 1] == nums[i])
        	{
        		return true;
        	}
        }

        return false;
    }


    public boolean containsNearbyDuplicate(int[] nums, int k)
    {
    	HashMap<Integer, Integer> map = new HashMap<>();
    	for(int i = 0; i < nums.length;i++)
        {
        	if(map.containsKey(nums[i]))
        	{
        		if(i - map.get(nums[i]) <= k)
        		{
        			return true;
        		}
        	}

        	map.put(nums[i], i);
        }

        return false;
    }
}