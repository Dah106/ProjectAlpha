public class searchInsertPositionLc35
{	
	public static void main(String[] args) 
	{
		searchInsertPositionLc35 test = new searchInsertPositionLc35();

		int [] testArray = {1, 3, 5, 6};
		System.out.println(test.searchInsert(testArray, 5));
		System.out.println(test.searchInsert(testArray, 2));	
		System.out.println(test.searchInsert(testArray, 7));	
		System.out.println(test.searchInsert(testArray, 0));		
	}

	public int searchInsert(int[] nums, int target) 
    {
        if(nums == null || nums.length == 0)
        {
        	return 0;
        }

        return binarySearch(nums, 0, nums.length - 1, target);
    }

    public int binarySearch(int[] nums, int lower, int upper, int target)
    {
    	
    	if(lower > upper)
    	{
    		return lower;
    	}

    	int middle = lower + (upper - lower) / 2;

    	if(target == nums[middle])
    	{
    		return middle;
    	}
    	else if(target < nums[middle])
    	{
    		return binarySearch(nums, lower, middle - 1, target);
    	}
    	else
    	{
    		return binarySearch(nums, middle + 1, upper, target);
    	}
    }

    // version 1: find the first position >= target
    public int searchInsert(int[] A, int target) 
    {
        if (A == null || A.length == 0) {
            return 0;
        }
        int start = 0, end = A.length - 1;
        
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (A[mid] == target) {
                return mid;
            } else if (A[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        
        if (A[start] >= target) {
            return start;
        } else if (A[end] >= target) {
            return end;
        } else {
            return end + 1;
        }
    }
}