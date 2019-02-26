public class singleNumber
{	


	// Given an array of integers, every element appears twice except for one. Find that single one.
	// Note:
	// Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
	// Time: O(N)
    // Space: O(N)
    public int singleNumberUsingHashSet(int[] A) 
    {
        HashSet<Integer> set = new HashSet<Integer>();
        for (int n : A) 
        {
            if (set.contains(n))
            {
                set.remove(n);
            }
            else
            {
                set.add(n);
            }
        }
        Iterator<Integer> it = set.iterator();
        return it.next();
    }

    // Time: O(N)
    // Space: O(1)
    public int singleNumber(int[] A) 
	{
        //A^A = 0
        //0^B = B
        int result = 0;
        for(int i = 0;i < A.length;i++)
        {
        	result = result ^ A[i];
        }
        return result;
    }

    // Given an array of integers, 
    // every element appears three times except for one.
    // Find that single one.
    public int singleNumberII(int[] nums) 
    {
        // 1110
        // 1110
        // 1110
        // 1001
        // _____
        // 4331    对每一位进行求和
        // 1001    对每一位的和做%3运算，来消去所有重复3次的数  

        for(int )  
    }
    
    // Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.
    // For example:
    // Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].
    // Note:
    // The order of the result is not important. So in the above example, [5, 3] is also correct.
    // Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
    public int[] singleNumberIII(int[] nums) 
    {
            
    }
}