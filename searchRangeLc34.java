public class searchRangeLc34
{
	public static void main(String[] args) {
		searchRangeLc34 test = new searchRangeLc34();

		int[] testArray = {5, 7, 7, 8, 8, 10};
		System.out.println(test.searchRange(testArray, 8));
	}

	public int[] searchRange(int[] nums, int target) 
	{
        int [] result = new int[2];

        result[0] = binarySearchMinIndex(nums, target);
        result[1] = binarySearchMaxIndex(nums, target);

        return result;
    }

    //Ascending order, have duplicates, return the first index
	//If the array is [1, 2, 3, 3, 4, 5, 10], for given target 3, return 2
	public int binarySearchMinIndex(int[] nums, int target) 
	{
        //write your code here
        if (nums == null || nums.length == 0) 
        {
            return -1;
        }
        
        int start = 0;
        int end = nums.length - 1;
        
        //Prevent infinite loop
        while (start + 1 < end) 
        {
            int mid = start + (end - start) / 2;

            //if target is less than or equal to middle element
            //Simply reducing end pointer
            if (target <= nums[mid]) 
            {
                end = mid;
            } 
            else 
            {
                start = mid;
            }
        }
        
        if (nums[start] == target) {
            return start;
        }
        
        if (nums[end] == target) {
            return end;
        }
        
        return -1;
    }	

    //Ascending order, have duplicates, return the last index
    //If the array is [1, 2, 2, 4, 5, 5], for given target 2, return 2
    public int binarySearchMaxIndex(int[] nums, int target) 
    {
    	if (nums == null || nums.length == 0) 
    	{
            return -1;
        }

        int start = 0;
        int end = nums.length - 1;
        
        //Prevent infinite loop
        while (start + 1 < end) 
        {
            int mid = start + (end - start) / 2;

            //if target is greater than or equal to middle element
            //Simply increasing start pointer
            if (target >= nums[mid]) 
            {
                start = mid;
            } 
            else 
            {
                end = mid;
            }
        }
        
        if (nums[end] == target) {
            return end;
        }
        
        if (nums[start] == target) {
            return start;
        }
        
        return -1;
    }
}