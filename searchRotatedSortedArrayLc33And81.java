public class searchRotatedSortedArrayLc33And81
{	
	public static void main(String[] args) 
	{
		searchRotatedSortedArrayLc33And81 test = new searchRotatedSortedArrayLc33And81();
		int[] array = {4, 5, 6, 7, 0, 1, 2};

		System.out.println(test.searchRotatedArray(array, 1));
	}

    //No duplicate
	public int searchRotatedArray(int[] nums, int target)
	{
        if (nums == null || nums.length == 0) 
    	{
            return -1;
        }

        int start = 0;
        int end = nums.length - 1;

        while(start + 1 < end)
        {
        	int mid = start + (end - start) / 2;

        	if(nums[mid] == target)
        	{
        		return mid;
        	}

        	//if nums[left] <= nums[mid]
        	//Then the middle value falls into left portion [start:mid]
        	//If not, the middle value falls into right portion [mid:end]
        	if(nums[start] < nums[mid])
        	{	
        		//But we still need to check if target is within [start:mid]
        		//If it is within the boundary [start <= target <= mid]
        		//Move the end to mid
        		//Otherwise start = mid;
        		if(nums[start] <= target && target <= nums[mid])
        		{
        			end = mid;
        		}
        		else
        		{
        			start = mid;
        		}
        	}
        	else
        	{
        		//But we still need to check if target is within [mid:end]
        		//If it is within the boundary [mid <= target <= end]
        		if(nums[mid] <= target && target <= nums[end])
        		{
        			start = mid;
        		}
        		else
        		{
        			end = mid;
        		}
        	}
        }

        if(nums[start] == target)
        {
        	return start;
        }

        if(nums[end] == target)
        {
        	return end;
        }

        return -1;
    }

    //Have duplicate, For example 3 3 3 3 1 2 3, find 2
    //Therefore when A[start] = A[mid], we can't discard a half portion
    //Only we can do is increment start as doing so will not affecting us finding the target as A[start] = A[mid] 
    //Worst complextiy degrades to O(n), for example 2 2 2 2 2 2 2 2, find 1
    public boolean searchWithDupRotatedArray(int[] nums, int target)
    {
        if (nums == null || nums.length == 0) 
        {
            return -1;
        }

        int start = 0;
        int end = nums.length - 1;

        while(start + 1 < end)
        {
            int mid = start + (end - start) / 2;

            if(nums[mid] == target)
            {
                return mid;
            }

            //if nums[left] <= nums[mid]
            //Then the middle value falls into left portion [start:mid]
            //If not, the middle value falls into right portion [mid:end]
            if(nums[start] < nums[mid])
            {   
                //But we still need to check if target is within [start:mid]
                //If it is within the boundary [start <= target <= mid]
                //Move the end to mid
                //Otherwise start = mid;
                if(nums[start] <= target && target <= nums[mid])
                {
                    end = mid;
                }
                else
                {
                    start = mid;
                }
            }
            else if(nums[start] > nums[mid])
            {
                //But we still need to check if target is within [mid:end]
                //If it is within the boundary [mid <= target <= end]
                if(nums[mid] <= target && target <= nums[end])
                {
                    start = mid;
                }
                else
                {
                    end = mid;
                }
            }
            else
            {
                start++;
            }
        }

        if(nums[start] == target)
        {
            return start;
        }

        if(nums[end] == target)
        {
            return end;
        }

        return -1;
    }
}
