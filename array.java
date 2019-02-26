import java.util.*;
public class array
{	  
    // Given an array of size n, find the majority element. 
    // The majority element is the element that appears more than ⌊ n/2 ⌋ times.
    // You may assume that the array is non-empty and the majority element always exist in the array.
    // Time: O(n)
    // Space: O(1)
    public int majorityElement(int[] nums) 
    {   
        int candidate = nums[0];
        int count = 1;

        for(int i = 1;i < nums.length;i++)
        {   
            //if encounters a same number 
            //increase counter
            if(nums[i] == candidate)
            {
                count++;
            }
            else
            {
                count--;
            }

            //if counter reaches to 0
            //reset candidate and increase counter
            if(count == 0)
            {
                candidate = nums[i];
                count++;
            }
        }

        return candidate;
    }

    // Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. 
    // The algorithm should run in linear time and in O(1) space.
    public List<Integer> majorityElementII(int[] nums) 
    {
        List<Integer> resultList = new ArrayList<Integer>();

        if(nums == null || nums.length == 0)
        {
            return resultList;
        }    

        if(nums.length == 1)
        {
            resultList.add(nums[0]);
            return resultList;
        }

        if(nums.length == 2)
        {
            if(nums[0] == nums[1])
            {
                resultList.add(nums[0]);
            }
            else
            {
                resultList.add(nums[0]); 
                resultList.add(nums[1]); 
            }
            return resultList;
        }

        //there can be at most 2 candidates that appear more than [n / 3] times
        int candidate1 = nums[0];
        int candidate2 = nums[0];

        int count1 = 1;
        int count2 = 1;

        for(int i = 1;i < nums.length;i++)
        {   
            //if encounter an elmenet as same as the first candidate
            //increase cout1
            if(candidate1 == nums[i])
            {
                count1++;
            }
            //else if encounter an elmenet as same as the second candidate
            //increase cout2
            else if(candidate2 == nums[i])
            {
                count2++;
            }
            else if(count1 == 0)
            {
                candidate1 = nums[i];
                count1++;
            }
            else if(count2 == 0)
            {
                candidate2 = nums[i];
                count2++;
            }
            //if encounter an element that does not equal to either 2 candidates 
            //decrement the both counters
            else
            {
                count1--;
                count2--;
            }
        }


        count1 = 0;
        count2 = 0;

        for(int i = 0;i < nums.length;i++)
        {   
            if(candidate1 == nums[i])
            {
                count1++;
            }
            else if(candidate2 == nums[i])
            {
                count2++;
            }
        }

        // System.out.println("count1: " + count1 + " len / 3: " + nums.length / 3);
        // System.out.println("count2: " + count1 + " len / 3: " + nums.length / 3);
        // System.out.println("candidate1: " + candidate1);
        // System.out.println("candidate2: " + candidate2);
        if(count1 > nums.length / 3)
        {
            resultList.add(candidate1);
        }

        if(count2 > nums.length / 3)
        {
            resultList.add(candidate2);
        }

        return resultList;
    }

	public int findMissingElementFromOneToN(int [] arr, int n)
	{	
		int result = 0;

		//A ^ 0 = A
		//A ^ A = 0
		//A ^ B ^ C ^ A ^ B = C
		for(int i = 1;i <= n;i++)
		{
			result ^= i;
		}

		for(int i = 0;i < arr.length;i++)
		{
			result ^= arr[i];			
		}

		return (result);
	}

	// Given a sorted integer array without duplicates, return the summary of its ranges.
	// For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
	public List<String> summaryRanges(int[] nums) 
	{
        List<String> resultList = new ArrayList<String>();
        if(nums == null || nums.length == 0)
        {
        	return resultList;
        }

        int startIndex = 0;
        int endIndex = 0;
        StringBuilder tempString = new StringBuilder();
        for(int i = 1;i < nums.length;i++)
        {	
        	
        	if(nums[i] - nums[i - 1] == 1)
        	{
        		endIndex++;
        	}
        	else
        	{	
        		System.out.println("startIndex: " + startIndex + " endIndex: " + endIndex );
        		if(startIndex != endIndex)
        		{
        			tempString.append(nums[startIndex] + "->" + nums[endIndex]);
        		}
        		else
        		{	
        			tempString.append(nums[startIndex]);
        		}

        		if( i == nums.length - 1)
        		{

        		}

        		startIndex = i;
        		endIndex = i;
        		resultList.add(tempString.toString());
        		tempString = new StringBuilder();
        	}
        }

        if(startIndex != endIndex)
       	{
        	tempString.append(nums[startIndex] + "->" + nums[endIndex]);
        }
        else
        {
        	tempString.append(nums[startIndex]);
        }

        resultList.add(tempString.toString());

        return resultList;
    }

 	// Given a sorted integer array where the range of elements are [lower, upper] inclusive, return its missing ranges.
	// For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
    public List<String> findMissingRanges(int[] nums, int lower, int upper) 
    {
        List<String> resultList = new ArrayList<String>();

        int length = nums.length;
        for(int i = 0;i < length;i++)
        {	
        	if(nums[i] < lower)
        	{
        		continue;
        	}

        	int prev = nums[i] - 1;
        	if(lower == prev)
        	{
        		resultList.add(lower + "");
        	}
        	else if(lower < prev)
        	{
        		resultList.add(lower + "->" + prev);
        	}

        	lower = nums[i] + 1;
        }

        if(lower == upper)
        {
        	resultList.add(lower + "");
        }
        else if(lower < upper)
        {
        	resultList.add(lower + "->" + upper);
        }

        return resultList;
    }

    // Given a non-negative number represented as an array of digits, plus one to the number.
	// The digits are stored such that the most significant digit is at the head of the list.
    public int[] plusOne(int[] digits) 
    {
        for(int i = digits.length - 1;i >= 0;i--)
        {
        	if(digits[i] < 9)
        	{
        		digits[i]++;
        		return digits;
        	}

        	if(digits[i] == 9)
        	{
        		digits[i] = 0;
        		if(i == 0)
        		{
        			int [] resultDigits = new int[digits.length + 1];
        			resultDigits[0] = 1;
        			System.arraycopy(digits, 0, resultDigits, 1, digits.length);
        			return resultDigits;
        		}
        	}
        }

        return digits;
    }

    // Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
    // You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2. 
    // The number of elements initialized in nums1 and nums2 are m and n respectively.
    public void merge(int[] A, int m, int[] B, int n) 
    {
        if(A==null || B==null) 
        { 
            return;  
        }

        int a = m - 1;  
        int b = n-1;  
        int len = m + n-1;  
        while(a >= 0 && b >= 0)  
        {   
            //put the larger item in the end of the array
            if(A[a] > B[b])  
            {  
                A[len] = A[a];  
                len--;
                a--;
            }  
            else  
            {  
                A[len] = B[b];
                len--;
                b--;  
            }  
        }  
        while(b >= 0)  
        {  
            A[len] = B[b];
            len--;
            b--;  
        }    
    }

    //Given an array of n positive integers and a positive integer s, 
    //find the minimal length of a subarray of which the sum ≥ s. 
    //If there isn't one, return 0 instead.

    // For example, given the array [2,3,1,2,4,3] and s = 7,
    // the subarray [4,3] has the minimal length under the problem constraint.
    // If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
    public int minSubArrayLen(int s, int[] nums) 
    {   
        int start = 0;
        int end = 0;
        int sum = 0;
        int len = nums.length;
        int minLen = Integer.MAX_VALUE;

        //sliding window
        while(end < len)
        {
            sum += nums[end];
            end++;

            while(sum >= s)
            {
                minLen = Math.min(minLen, end - start);
                sum -= nums[start];
                start++;
            }
            
        }

        if(minLen == Integer.MAX_VALUE)
        {
            return 0;
        }
        else
        {
            return minLen;
        }
    }

    // Given an array nums and a target value k, 
    // find the maximum length of a subarray that sums to k. 
    // If there isn't one, return 0 instead.

    // Example 1:
    // Given nums = [1, -1, 5, -2, 3], k = 3,
    // return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)

    // Example 2:
    // Given nums = [-2, -1, 2, 1], k = 1,
    // return 2. (because the subarray [-1, 2] sums to 1 and is the longest)
    public int maxSubArrayLen(int[] nums, int k) 
    {   
        int sum = 0;
        int maxLength = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        
        for (int i = 0; i < nums.length; i++) 
        {
            sum = sum + nums[i];
            //if sum[0:i] equals k
            //simply increase the maxLength
            if(sum == k) 
            {
                maxLength = i + 1;
            }
            //if previousSum = sum - k exists in the array
            //it means the subarray[previousSum + 1: i] forms an array
            //thus the length of this subarray is i - map.get(previousValue)
            else if(map.containsKey(sum - k))
            {
                maxLength = Math.max(maxLength, i - map.get(sum - k));
            } 

            if (!map.containsKey(sum))
            {
                 map.put(sum, i);
            }
        }
        return maxLength;
    }


    //local stores maximal/optimal result including current element
    //It makes a decision whether to take local + A[i] or A[i] as the max value at index i
    //If say the A[i] is larger than local + A[i], we restart from A[i] 
    //global stores maximal result for the entire array
    public int maxSubArray(int[] A) 
    {
        if(A == null || A.length == 0)
        {
            return 0;
        }

        int local = A[0];
        int global = A[0];

        for(int i = 1;i < A.length;i++)
        {
            local = Math.max(A[i], local + A[i]);
            global = Math.max(local, global);
        }

        return global;
    }

    //Given an integer array, find a subarray where the sum of numbers is zero. 
    //Your code should return the index of the first number and the index of the last number.
    //Given [-3, 1, 2, -3, 4], return [0, 2] or [1, 3].
    public ArrayList<Integer> subarraySum(int[] nums) 
    {
        
        ArrayList<Integer> resultList = new ArrayList<Integer>();
        if (nums == null || nums.length == 0) 
        {
            return resultList;
        }
        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, -1);
        //we know that sub-array (a,b) has zero sum if SUM(0 ... a-1) = SUM(0 ... b)
        for (int i = 0; i < nums.length; i++) 
        {
            sum += nums[i];
            if (map.containsKey(sum)) 
            {
                resultList.add(map.get(sum) + 1);
                resultList.add(i);
                return resultList;
            }
            map.put(sum, i);
        }//for
        return resultList;
    }

    //Given a m x n matrix, if an element is 0, 
    //set its entire row and column to 0. Do it in place.
    //Time: O(m * n)
    //Space: O(m + n)
    public void setZeroes(int[][] matrix) 
    {   
        if(matrix==null || matrix.length==0 || matrix[0].length==0)
        {
            return; 
        }  
        
        int row = matrix.length;
        int col = matrix[0].length;

        boolean[] rowRecorder = new boolean[row];
        boolean[] colRecorder = new boolean[col];

        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                if (matrix[i][j] == 0)
                {
                    rowRecorder[i] = true;
                    colRecorder[j] = true;
                }
            }
        }

        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                if (rowRecorder[i]) matrix[i][j] = 0;
                else if (colRecorder[j]) matrix[i][j] = 0;
            }
        }    
    }

    //Time: O(m * n)
    //Space: O(1)
    public void setZeroesOptimized(int[][] matrix) 
    {   
        if(matrix==null || matrix.length==0 || matrix[0].length==0)  
        return;  
        boolean rowFlag = false;  
        boolean colFlag = false;  

        //mark colFlag as true if there is a zero in first column
        for(int i = 0;i < matrix.length;i++)  
        {  
            if(matrix[i][0] == 0)  
            {  
                colFlag = true;  
                break;  
            }  
        }  

        //mark rowFlag as true if there is a zero in first row
        for(int i = 0;i < matrix[0].length;i++)  
        {  
            if(matrix[0][i]==0)  
            {  
                rowFlag = true;  
                break;  
            }  
        }

        //set values of the first row and first column
        for(int i = 1;i < matrix.length;i++)  
        {  
            for(int j = 1;j < matrix[0].length;j++)  
            {  
                if(matrix[i][j] == 0)  
                {  
                    matrix[i][0] = 0;  
                    matrix[0][j] = 0;  
                }  
            }  
        }  

        //set every other values based on last step
        for(int i = 1;i < matrix.length;i++)  
        {  
            for(int j = 1;j < matrix[0].length;j++)  
            {  
                if(matrix[i][0] == 0 || matrix[0][j] == 0)  
                {
                    matrix[i][j] = 0;  
                }
            }  
        }  

        //update values of the first row and column based on two flags
        if(colFlag)  
        {  
            for(int i = 0;i < matrix.length;i++)  
            {  
                matrix[i][0] = 0;  
            }  
        }  
        if(rowFlag)  
        {  
            for(int i = 0;i < matrix[0].length;i++)  
            {  
                matrix[0][i] = 0;  
            }  
        }    
    }

    // Given an array with n objects colored red, white or blue, 
    // sort them so that objects of the same color are adjacent, 
    // with the colors in the order red, white and blue.
    // Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
    public void sortColors(int[] A) 
    {   
        if(A==null || A.length==0)  
        return;  
        int lastZeroPos = 0;  
        int lastOnePos = 0;  
        for(int i=0;i<A.length;i++)  
        {  
            if(A[i]==0)  
            {  
                A[i] = 2;  
                A[lastOnePos++] = 1;  
                A[lastZeroPos++] = 0;  
            }  
            else if(A[i]==1)  
            {  
                A[i] = 2;  
                A[lastOnePos++] = 1;  
            }  
        }
    }

    public void swap(int[] nums, int start, int end)
    {
        int temp = nums[end];
        nums[end] = nums[start];
        nums[start] = temp;
    }

    // Given an unsorted array nums, 
    // reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
    // For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].
    public void wiggleSort(int[] nums) 
    {
        //if index is odd (starting at 1), nums[i] >= nums[i - 1]
        //if index is even (starting at 2), nums[i] <= nums[i - 1]

        for(int i = 1;i < nums.length;i++)
        {
            if((i % 2 == 1 && nums[i] < nums[i - 1]) || (i % 2 == 0 && nums[i] > nums[i - 1]))
            {
                int temp = nums[i - 1];
                nums[i - 1] = nums[i];
                nums[i] = temp;
            }
        }
    }

    // There are two sorted arrays nums1 and nums2 of size m and n respectively. 
    // Find the median of the two sorted arrays. 
    // The overall run time complexity should be O(log (m+n)).
    // Given A=[1,2,3,4,5,6] and B=[2,3,4,5], the median is 3.5.
    // Given A=[1,2,3] and B=[4,5], the median is 3.
    public double findMedianSortedArrays(int A[], int B[]) 
    {
        int m = A.length;
        int n = B.length;
     
        if ((m + n) % 2 != 0) // odd
        {
            return (double) findKth(A, B, (m + n) / 2, 0, m - 1, 0, n - 1);
        }
        else 
        {   // even
            return (double) (findKth(A, B, (m + n) / 2, 0, m - 1, 0, n - 1) 
                + findKth(A, B, (m + n) / 2 - 1, 0, m - 1, 0, n - 1)) * 0.5;
        }
    }
 
    public int findKth(int A[], int B[], int k, int aStart, int aEnd, int bStart, int bEnd) 
    {
     
        int aLen = aEnd - aStart + 1;
        int bLen = bEnd - bStart + 1;
     
        // Handle special cases
        if (aLen == 0)
            return B[bStart + k];
        if (bLen == 0)
            return A[aStart + k];
        if (k == 0)
            return A[aStart] < B[bStart] ? A[aStart] : B[bStart];
     
        int aMid = aLen * k / (aLen + bLen); // a's middle count
        int bMid = k - aMid - 1; // b's middle count
     
        // make aMid and bMid to be array index
        aMid = aMid + aStart;
        bMid = bMid + bStart;
     
        if (A[aMid] > B[bMid]) {
            k = k - (bMid - bStart + 1);
            aEnd = aMid;
            bStart = bMid + 1;
        } else {
            k = k - (aMid - aStart + 1);
            bEnd = bMid;
            aStart = aMid + 1;
        }
     
        return findKth(A, B, k, aStart, aEnd, bStart, bEnd);
    }

    // Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity. 
    // The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.
    /* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */
    public int findCelebrity(int n) 
    {
        //initialize celebrity candidate as 0
        int candidate = 0;
        for(int i = 1;i < n;i++)
        {   
            //if candidate knows someone 
            //set candidate to i as celebrity should not know anybody
            if(knows(candidate, i))
            {
                candidate = i;
            }
        }

        for(int j = 0;j < n;j++)
        {   
            //if the candidate knows somebody or nobody knows the candidate
            //then no celebrity
            if(j != candidate && (knows(candidate, j) || !knows(j, candidate)))
            {
                return -1;
            }
        }

        return candidate;
    }

    public boolean knows(int A, int B)
    {
        //helper function just to make sure the program compiles
        return true;
    }


    //Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
    public TreeNode sortedArrayToBST(int[] nums) 
    {
        if(nums == null || nums.length == 0)
        {
            return null;
        }

        return sortedArrayToBSTHelper(nums, 0, nums.length - 1);
    }

    public TreeNode sortedArrayToBSTHelper(int[] nums, int start, int end)
    {   
        //getting down to either end of the array
        //means we it is a leaf
        if(start > end)
        {
            return null;
        }

        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(nums[mid]);//the root is always the middle element
        root.left = sortedArrayToBSTHelper(nums, start, mid - 1);
        root.right = sortedArrayToBSTHelper(nums, mid + 1, end);

        return root;
    }

    // Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.

    // Do not allocate extra space for another array, 
    // you must do this in place with constant memory.

    // For example,
    // Given input array nums = [1,1,2],

    // Your function should return length = 2, 
    // with the first two elements of nums being 1 and 2 respectively. It doesn't matter what you leave beyond the new length.
    public int removeDuplicates(int[] nums) 
    {
        if(nums == null || nums.length == 0)
        {
            return 0;
        }

        int lastUniqueIndex = 0;//pointer that points at the last unqiue element
        for(int i = 1;i < nums.length;i++)
        {   
            if(nums[i] != nums[lastUniqueIndex])
            {
                lastUniqueIndex++;
                nums[lastUniqueIndex] = nums[i];
            }
        }

        return lastUniqueIndex + 1;
    }  

    // Follow up for "Remove Duplicates":
    // What if duplicates are allowed at most twice?

    // For example,
    // Given sorted array nums = [1,1,1,2,2,3],

    // Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3. 
    // It doesn't matter what you leave beyond the new length.
    public int removeDuplicatesII(int[] nums) 
    {   
        if(nums == null || nums.length == 0)
        {
            return 0;
        }

        int n = nums.length;
        if(n <= 2)
        {
            return n;
        }

        int lastUniqueIndex = 1;
        for(int i = 2;i < n;i++)
        {      
            //if nums[lastUniqueIndex - 1] = nums[lastUniqueIndex] = nums[i], then nums[i] is a duplicate
            //thus if nums[i] != nums[lastUniqueIndex - 1], then we have a unique item
            if(nums[i] != nums[lastUniqueIndex - 1])
            {   
                lastUniqueIndex++;
                nums[lastUniqueIndex] = nums[i];
            }
        }

        return lastUniqueIndex + 1;
    } 

    // Given a matrix of m x n elements (m rows, n columns), 
    // return all elements of the matrix in spiral order.
    // For example,
    // Given the following matrix:

    // [
    //  [ 1, 2, 3 ],
    //  [ 4, 5, 6 ],
    //  [ 7, 8, 9 ]
    // ]
    // You should return [1,2,3,6,9,8,7,4,5].
    // Time: O(m * n)
    // Space: O(1)
    public List<Integer> spiralOrder(int[][] matrix) 
    {
        List<Integer> resultList = new ArrayList<Integer>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
        {
            return resultList;
        }

        int m = matrix.length;//row size
        int n = matrix[0].length;//column size

        int x = 0;//current row number
        int y = 0;//current col number

        //there are four directions to go
        //go right
        //go left
        //go top
        //go bottom
        while(m > 0 && n > 0)
        {   
            //if the current layer has only one row
            //simply add all elements in this row
            //once its done, quit searching
            if(m == 1)
            {
                for(int i = 0;i < n;i++)
                {
                    resultList.add(matrix[x][y]);
                    y++;
                }

                break;
            }
            //if the current layer has only one column
            //simply add all elements in this row
            //once its done, quit searching
            else if(n == 1)
            {
                for(int i = 0;i < m;i++)
                {
                    resultList.add(matrix[x][y]);
                    x++;
                }

                break;
            }


            //move right from the top left element in the matrix 
            for(int i = 0;i < n - 1;i++)
            {
                resultList.add(matrix[x][y]);
                y++;
            }

            //then move down from the top right element in the matrix
            for(int i = 0;i < m - 1;i++)
            {
                resultList.add(matrix[x][y]);
                x++;
            }

            //then move left from the low right element in the matrix
            for(int i = 0;i < n - 1;i++)
            {
                resultList.add(matrix[x][y]);
                y--;
            }

            //then move up from the low left element in the matrix
            for(int i = 0;i < m - 1;i++)
            {
                resultList.add(matrix[x][y]);
                x--;
            }


            //once one spiral has been down
            //get to the inner layer
            //decrease both dimension of the matrix by 2 as the inner layer size
            x++;
            y++;
            m = m - 2;
            n = n - 2;
        }

        return resultList;
    }

    // Given an integer n, 
    // // generate a square matrix filled with elements from 1 to n2 in spiral order.
    // For example,
    // Given n = 3,

    // You should return the following matrix:
    // [
    //  [ 1, 2, 3 ],
    //  [ 8, 9, 4 ],
    //  [ 7, 6, 5 ]
    // ]
    public int[][] generateMatrix(int n) 
    {   
        int [][] matrix = new int[n][n];

        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
        {
            return matrix;
        }

        int m = n;//row size

        int x = 0;//current row number
        int y = 0;//current col number

        int value = 1;

        //there are four directions to go
        //go right
        //go left
        //go top
        //go bottom
        while(m > 0 && n > 0)
        {   
            //if the current layer has only one row
            //simply add all elements in this row
            //once its done, quit searching
            if(m == 1)
            {
                for(int i = 0;i < n;i++)
                {
                    matrix[x][y] = value;
                    value++;
                    y++;
                }

                break;
            }
            //if the current layer has only one column
            //simply add all elements in this row
            //once its done, quit searching
            else if(n == 1)
            {
                for(int i = 0;i < m;i++)
                {
                    matrix[x][y] = value;
                    value++;
                    x++;
                }

                break;
            }


            //move right from the top left element in the matrix 
            for(int i = 0;i < n - 1;i++)
            {
                matrix[x][y] = value;
                value++;
                y++;
            }

            //then move down from the top right element in the matrix
            for(int i = 0;i < m - 1;i++)
            {
                matrix[x][y] = value;
                value++;
                x++;
            }

            //then move left from the low right element in the matrix
            for(int i = 0;i < n - 1;i++)
            {
                matrix[x][y] = value;
                value++;
                y--;
            }

            //then move up from the low left element in the matrix
            for(int i = 0;i < m - 1;i++)
            {
                matrix[x][y] = value;
                value++;
                x--;
            }


            //once one spiral has been down
            //get to the inner layer
            //decrease both dimension of the matrix by 2 as the inner layer size
            x++;
            y++;
            m = m - 2;
            n = n - 2;
        }

        return matrix;
    }

    // Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). 
    // n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

    // Note: You may not slant the container.
    public int maxArea(int[] height) 
    {
        int left = 0;
        int right = height.length - 1;
        int max = 0;

        while(left < right) 
        {   
            //since we cannot slant the container
            //the capacity is determined by the lower one of two end lines
            int h = Math.min(height[left], height[right]);
            int area = (right - left) * h;
            max = Math.max(max, area);

            //if the left bar is shorter
            //move right to look for a higher bar
            if(height[left] < height[right])
            {
                left++;
            }
            else
            {
                right--;
            }
        }

        return max;    
    }

    // Given n non-negative integers representing an elevation map where the width of each bar is 1, 
    // compute how much water it is able to trap after raining.

    // For example, 
    // Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
    public int trap(int[] height) 
    {
        int result = 0;

        return result;    
    }

    // According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

    // Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

    // Any live cell with fewer than two live neighbors dies, as if caused by under-population.
    // Any live cell with two or three live neighbors lives on to the next generation.
    // Any live cell with more than three live neighbors dies, as if by over-population..
    // Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
    // Write a function to compute the next state (after one update) of the board given its current state.

    // Follow up: 
    // Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first and then use their updated values to update other cells.
    // In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. How would you address these problems?
    public void gameOfLife(int[][] board) 
    {
        // 9
        // votes
        // 4,300 views
        // To solve it in place, we use 2 bits to store 2 states:

        // [2nd bit, 1st bit] = [next state, current state]

        // - 00  dead (next) <- dead (current)
        // - 01  dead (next) <- live (current)  
        // - 10  live (next) <- dead (current)  
        // - 11  live (next) <- live (current) 
        // In the beginning, every cell is either 00 or 01.
        // Notice that 1st state is independent of 2nd state.
        // Imagine all cells are instantly changing from the 1st to the 2nd state, at the same time.
        // Let's count # of neighbors from 1st state and set 2nd state bit.
        // Since every 2nd state is by default dead, no need to consider transition 01 -> 00.
        // In the end, delete every cell's 1st state by doing >> 1.
        // For each cell's 1st bit, check the 8 pixels around itself, and set the cell's 2nd bit.

        // Transition 01 -> 11: when board == 1 and lives >= 2 && lives <= 3.
        // Transition 00 -> 10: when board == 0 and lives == 3.
        // To get the current state, simply do

        // board[i][j] & 1
        // To get the next state, simply do

        // board[i][j] >> 1
        if(board == null || board.length == 0) return;
        int m = board.length, n = board[0].length;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                int lives = liveNeighbors(board, m, n, i, j);

                // In the beginning, every 2nd bit is 0;
                // So we only need to care about when the 2nd bit will become 1.
                if(board[i][j] == 1 && lives >= 2 && lives <= 3) {  
                    board[i][j] = 3; // Make the 2nd bit 1: 01 ---> 11
                }
                if(board[i][j] == 0 && lives == 3) {
                    board[i][j] = 2; // Make the 2nd bit 1: 00 ---> 10
                }
            }
        }

        for(int i = 0; i < m; i++) 
        {
            for(int j = 0; j < n; j++) 
            {
                board[i][j] >>= 1;  // Get the 2nd state.
            }
        }
    }

    public int liveNeighbors(int[][] board, int m, int n, int i, int j) 
    {
        int lives = 0;
        for(int x = Math.max(i - 1, 0); x <= Math.min(i + 1, m - 1); x++) 
        {
            for(int y = Math.max(j - 1, 0); y <= Math.min(j + 1, n - 1); y++) 
            {
                lives += board[x][y] & 1;
            }
        }
        lives -= board[i][j] & 1;
        return lives;
    }

    public static void main(String[] args) 
    {
        
        array test = new array();

        int arr [] = {2, 3, 8, 10, 11, 14, 15, 17};
        List<String> rangeList = test.summaryRanges(arr);
        System.out.println("summary ranges are: " + rangeList);


        int arr1 [] = {1, 1, 1, 2, 2, 3, 4};
        int length1 = test.removeDuplicates(arr1);
        System.out.println("the length of unique elements are: " + length1 + " after duplcates removal, array is: " + Arrays.toString(arr1));
        
        int arr2 [] = {1, 1, 1, 2, 2, 3, 4};
        int length2 = test.removeDuplicatesII(arr2);
        System.out.println("the length of unique elements are: " + length2 + " after duplcates removal II, array is: " + Arrays.toString(arr2));
    
        int majority [] = {1, 2, 1, 2, 3, 2};
        List<Integer> majoritylist = test.majorityElementII(majority);
        System.out.println("the majority element(s) is(are): " + majoritylist);
    
        int[][] matrix = test.generateMatrix(3);
        System.out.println("the spiral matrix generated is: " + Arrays.deepToString(matrix));
    }
}

class TreeNode
{
        
    public TreeNode left;
    public TreeNode right;
    public int val;

    public TreeNode(int x){ val = x; }
        
}