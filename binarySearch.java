import java.util.*;

public class binarySearch
{
    public static void main(String[] args) 
    {
        binarySearch test = new binarySearch();

        int[] woodCutArray = {1, 3, 2};
        //System.out.println(test.woodCut(woodCutArray, 7));

        int[] kClosestArray = {1, 2, 3};
        int[] kClosestArrayTwo = {1, 4, 6, 8};
        int[] kClosestArrayThree = {2, 4, 5, 10, 20};
        System.out.println(Arrays.toString(test.kClosestNumbers(kClosestArray, 2, 3)));
        System.out.println(Arrays.toString(test.kClosestNumbers(kClosestArrayThree, 1, 10)));
    }
	//Basic case, no duplicate, ascending-order
	public int binarySearchStandard(int[] num, int target)
	{
        int start = 0;
        int end = num.length - 1;
        while(start <= end)
        {
            int mid = start + ((end - start) / 2);
            if(num[mid] == target)
            {
                return mid;
            }
            else if(num[mid] > target)
            {
                end = mid - 1;
            }
            else{
                start = mid + 1;
            }
        }
        return -1;
    }

    //No duplicate, find the first position to insert
    //Find the first position >= target
    //[1,3,5,6], 5 → 2
    // [1,3,5,6], 2 → 1
    // [1,3,5,6], 7 → 4
    // [1,3,5,6], 0 → 0
    public int searchInsertPosition(int[] A, int target) 
    {

        if (A == null || A.length == 0) 
        {
            return 0;
        }

        int start = 0, end = A.length - 1;
        
        while (start + 1 < end) 
        {
            int mid = start + (end - start) / 2;
            if (A[mid] == target) 
            {
                return mid;
            } 
            else if (A[mid] < target) 
            {
                start = mid;
            } 
            else 
            {
                end = mid;
            }
        }
        
        if (A[start] >= target) 
        {
            return start;
        } 
        else if (A[end] >= target) 
        {
            return end;
        } 
        else 
        {
            return end + 1;
        }

    }

    //Ascending order, have duplicates, return any of the indices with same value.
    // Given [1, 2, 3] and target = 2, return 1.

    // Given [1, 4, 6] and target = 3, return 1.

    // Given [1, 4, 6] and target = 5, return 1 or 2.

    // Given [1, 3, 3, 4] and target = 2, return 0 or 1 or 2.
    public int searchClosetNumber(int[] A, int target) 
    {
        // Write your code here 
        if (A == null || A.length == 0) 
        {
            return -1; 
        }

        int start = 0;
        int end = A.length - 1;

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
        if (Math.abs(A[start] - target) < Math.abs(A[end] - target)) {
            return start;
        } else { 
            return end;
        } 
    }

    // Given A = [1, 2, 3], target = 2 and k = 3, return [2, 1, 3].
    // Given A = [1, 4, 6, 8], target = 3 and k = 3, return [4, 1, 6].
    public int[] kClosestNumbers(int[] A, int target, int k) 
    {   

        int [] result = new int[k];

        if(k == 0)
        {
            return result;
        }

        int targetPosition = searchInsertPosition(A, target);
        
        int count = 0;
        int left = targetPosition - 1;
        int right = targetPosition;

        //System.out.println("target/insert Position:" + targetPosition + " left: " + left + " right: " + right);
        int lastIndex = A.length - 1;

        //if the target position is off the right boundary
        //simply adding the last k numbers of the array
        if(targetPosition > lastIndex)
        {   
            int tempPointer = lastIndex;
            while(count < k && tempPointer >= 0)
            {
                result[count] = A[tempPointer];
                count++;
                tempPointer--;
            }

            return result;
        }

        //if the target position is at the first position
        //simply adding the first k numbers of the array
        if(targetPosition == 0)
        {
            int tempPointer = 0;
            while(count < k && tempPointer <= lastIndex)
            {
                result[count] = A[tempPointer];
                count++;
                tempPointer++;
            }

            return result;
        }

        //if we found the exact target
        //we start searching from the next position
        //otherwise searching from current position
        if(A[targetPosition] == target)
        {
            result[count] = target;
            right = targetPosition + 1;
            count++;
        }

        while(left >= 0 && right <= lastIndex && count < k)
        {
            int leftDifference = target - A[left];
            int rightDifference = A[right] - target;

            //System.out.println("leftDiff: " + leftDifference + " rightDiff: " + rightDifference);
            //if the left element is closer
            if(leftDifference <= rightDifference)
            {
                result[count] = A[left];
                left--;
            }
            else
            {
                result[count] = A[right];
                right++;
            }
            count++;
        }

        //System.out.println("left: " + left + " right: " + right + " count: " + count);
        //if the result array has been filled up
        if(count < k)
        {
            //if the left portion is running off the boundary
            //check if right portion is running off the boundary
            //if not, keep searching right
            //otherwise do nothing
            if(left < 0)
            {
                while(count < k && right <= lastIndex)
                {
                    result[count] = A[right];
                    right++;
                    count++;
                }
            }

            //if the right portion is running out
            //check if left portion is running off the boundary
            //if not, keep searching left
            //otherwise do nothing
            if(right > lastIndex)
            {
                while(count < k && left >= 0)
                {
                    result[count] = A[left];
                    left--;
                    count++;
                }
            }
        }

        return result;
        
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
    public int searchWithDupRotatedArray(int[] nums, int target)
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
    
    //No duplicate
    //Need to consider 3 3 3 3 1 2 3
    public int findMinRotatedArray(int[] nums) 
    {

        if (nums == null || nums.length == 0) 
        {
            return 0;
        }

        int start = 0;
        int end = nums.length - 1;

        while(start + 1 < end)
        {
            int mid = start + (end - start) / 2;

            //Check if the nums[mid] <= nums[end]
            //If yes, it means that the min is not within [mid+1:end]
            //Move end = mid;
            //If not, it means that the min is not within [start:mid]
            //move start = mid
            if(nums[mid] <= nums[end])
            {
                end = mid;
            }
            else
            {
                start = mid;
            }
        }

        if(nums[start] < nums[end])
        {
            return nums[start];
        }
        else
        {
            return nums[end];
        }
    }

    //Have duplicate
    //Complexity downgrades to O(n)
    public int findMinWithDupRotatedArray(int[] nums)
    {
        if (nums == null || nums.length == 0) 
        {
            return 0;
        }

        int start = 0;
        int end = nums.length - 1;

        while(start + 1 < end)
        {
            int mid = start + (end - start) / 2;

            //Choose target as the last item in the array
            //Check if the nums[mid] < nums[end]
            //If yes, it means that the min is not within [mid+1:end]
            //Move end = mid;
            //If not, it means that the min is not within [start:mid]
            //move start = mid
            //if nums[mid] = nums[end]
            //we are not sure whether the target is on the left or right side of middle
            //We could only decrement end by 1 as doing so will not affecting us finding the min as A[mid] = A[end] 
            if(nums[mid] < nums[end])
            {
                end = mid;
            }
            else if(nums[mid] > nums[end])
            {
                start = mid;
            }
            else
            {
                end--;
            }
        }

        if(nums[start] < nums[end])
        {
            return nums[start];
        }
        else
        {
            return nums[end];
        }
    }

    // Given n pieces of wood with length L[i] (integer array). 
    // Cut them into small pieces to guarantee you could have equal or more than k pieces with the same length
    // For L=[232, 124, 456], k=7, return 114.
    // 232/114 = 2, 124/114 = 1, 456/114 = 3 => 2 + 1 + 3 = 7 
    public int woodCut(int[] L, int k) 
    {
        if (L == null || L.length == 0) 
        {
            return 0;
        }

        int maxLength = L[0];
        long sum = L[0];
        for(int i = 1;i < L.length;i++)
        {   
            sum += L[i];
            if(L[i] > maxLength)
            {
                maxLength = L[i];
            }
        }
    
        if(k > sum)
        {
            return 0;
        }

        //set the longest length of the wood at the max value of the wood existing in the source array
        int start = 0;
        int end = maxLength;

        while(start + 1 < end)
        {
            int mid = start + (end - start) / 2;

            int amount = countHowManyPiecesWood(L, mid);

            //if the current length results in an amount less than k
            //we need to find smaller length that results in increasing amount
            //otherwise, the amount is more than enough or just enough
            //we continue to search for larger value to see if the maxium is reached
            if(amount < k)
            {
                end = mid;
            }
            else if(amount > k)
            {
                start = mid;
            }
            else
            {
                start = mid;
            }
        }

        //need to check the end value first as it is bigger than start value
        if(countHowManyPiecesWood(L, end) >= k)
        {
            return end;
        }

        if(countHowManyPiecesWood(L, start) >= k)
        {
            return start;
        }

        //if the k value is larget than the total length of the source array
        //[1, 3, 2], k = 4. This means it is impossible to cut into k pieces with an integer wood length
        //simply return 0
        return 0;
    }

    public int countHowManyPiecesWood(int[] L, int woodLength)
    {   
        long result = 0;

        for(int i = 0;i < L.length;i++)
        {
            result += L[i] / woodLength;
        }

        return (int) result;
    }

    //find peak element for a stock
    public int findPeakElement(int[] A) 
    {
        int start = 0;
        int end = A.length - 1; 
        while(start + 1 <  end) 
        {
            int mid = start + (end - start) / 2;

            //if the current point is a bottom or just simply descending 
            //then [start:mid] contains peak element
            if(A[mid] < A[mid - 1]) 
            {
                end = mid;
            } 
            //if the current point is ascending
            //then [mid:end] contains peak element
            else if(A[mid] < A[mid + 1]) 
            {
                start = mid;
            } 
            else 
            {
                end = mid;
            }
        }

        if(A[start] < A[end]) 
        {
            return end;
        } 
        else 
        { 
            return start;
        }
    }
}	