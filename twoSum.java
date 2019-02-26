import java.util.*;

public class twoSum
{
    Map<Integer, Integer> twoSumMap = new HashMap<Integer, Integer>();

	// Given an array of integers, return indices of the two numbers such that they add up to a specific target.
	// You may assume that each input would have exactly one solution.
    public int[] twoSum(int[] numbers, int target) 
    {
	    int[] result = new int[2];
	    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	    for (int i = 0; i < numbers.length; i++) 
	    {
	        if (map.containsKey(target - numbers[i])) 
	        {
	            result[1] = i;
	            result[0] = map.get(target - numbers[i]);
	            return result;
	        }
	        map.put(numbers[i], i);
	    }
	    return result;
	}

	// Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
	// The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. 
	// Please note that your returned answers (both index1 and index2) are not zero-based.
	// You may assume that each input would have exactly one solution.
	// Input: numbers={2, 7, 11, 15}, target=9
	// Output: index1=1, index2=2
	public int[] twoSumII(int[] numbers, int target) 
	{	
		int [] resultArray = new int[2];
        if(numbers == null || numbers.length == 0)
        {
        	return resultArray;
        }

        int length = numbers.length;
        int left = 0;
        int right = length - 1;

        while(left < right)
        {
        	int sum = numbers[left] + numbers[right];
        	if(sum == target)
        	{
        		resultArray[0] = left + 1;
        		resultArray[1] = right + 1;
        		return resultArray;
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

        return resultArray;
    }

    // Add the number to an internal data structure.
    public void add(int number) 
    {
        if(!twoSumMap.containsKey(number))
        {
            twoSumMap.put(number, 1);
        }
        else
        {
            twoSumMap.put(number, twoSumMap.get(number) + 1); 
        }
    }

    // Find if there exists any pair of numbers which sum is equal to the value.
    public boolean find(int value) 
    {
        for(Integer number: twoSumMap.keySet())
        {   
            int another = value - number;
            //if two numbers are equal
            //make sure they appear twice
            if(another == number && twoSumMap.get(another) > 1)
            {
                return true;
            }
            else if(another != number && twoSumMap.containsKey(another))
            {
                return true;
            }
        }

        return false;
    }

    // Your TwoSum object will be instantiated and called as such:
    // TwoSum twoSum = new TwoSum();
    // twoSum.add(number);
    // twoSum.find(value);
}