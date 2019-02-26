import java.util.*;

public class permutations
{
	public static void main(String[] args) 
	{
		permutations test = new permutations();
		
		int [] subArray = {1, 2, 3};
		System.out.println(test.permute(subArray));

		int [] subArrayWithDup = {1, 1, 2};
		System.out.println(test.permuteUnique(subArrayWithDup));

        int[] array = {6, 8, 7, 4, 3, 2};
        test.nextPermutation(array);
        System.out.println("next permutations: " + Arrays.toString(array));

        String kthPermutationSequence = test.getPermutation(3, 6);
        System.out.println("the kth permutation sequence is: " + kthPermutationSequence);
	}

	public List<List<Integer>> permute(int[] nums) 
	{
        List<List<Integer>> resultList = new ArrayList<List<Integer>>();
        List<Integer> tempList = new ArrayList<Integer>();
        boolean[] visited = new boolean[nums.length];
        dfs(resultList, tempList, nums, visited);

        return resultList;
    }

    public void dfs(List<List<Integer>> resultList, List<Integer> tempList, int[] nums, boolean[] visited)
    {
   		if(tempList.size() == nums.length)
   		{
   			resultList.add(new ArrayList<Integer>(tempList));
   			return;
   		}

    	for(int i = 0;i < nums.length;i++)
    	{	
    		//Preserving a boolean array to mark visited element
    		//We check the visited array if algorithm already visited the element
    		//If yes, we simply skip it
    		//Note we have to mark it unvisited once we are done 
    		if(!visited[i])
    		{	
    			visited[i] = true;
    			tempList.add(nums[i]);
	    		dfs(resultList, tempList, nums, visited);
	    		tempList.remove(tempList.size() - 1);
	    		visited[i] = false;
    		}

    	}
    }

    public List<List<Integer>> permuteUnique(int[] nums) 
    {
    	Arrays.sort(nums);

        List<List<Integer>> resultList = new ArrayList<List<Integer>>();
        List<Integer> tempList = new ArrayList<Integer>();
        boolean[] visited = new boolean[nums.length];
        dfsWithDup(resultList, tempList, nums, visited);

        return resultList;
    }

    public void dfsWithDup(List<List<Integer>> resultList, List<Integer> tempList, int[] nums, boolean[] visited)
    {
   		if(tempList.size() == nums.length)
   		{
   			resultList.add(new ArrayList<Integer>(tempList));
   			return;
   		}

    	for(int i = 0;i < nums.length;i++)
    	{	
    		if (i > 0 && nums[i] == nums[i-1] && visited[i-1])
    		{	
    			System.out.println(tempList);
    			continue;
    		}

    		if(!visited[i])
    		{	
    			
    			visited[i] = true;
    			tempList.add(nums[i]);
	    		dfsWithDup(resultList, tempList, nums, visited);
	    		tempList.remove(tempList.size() - 1);
	    		visited[i] = false;
    		}
    	}
    }

    // The set [1,2,3,…,n] contains a total of n! unique permutations.
    // For given n and k, return the kth permutation sequence.
    // For example n = 3, k = 3
    // 123, 132, 213 -> 213 
    // every starting element has (n - 1)! permutations
    // If k = 9, 9/3! = numList.get(1) = 2, remove 2 from candidate {1, 2, 3, 4}
    // and add to the result
    // 
    // http://bangbingsyb.blogspot.com/2014/11/leetcode-permutation-sequence.html
    public String getPermutation(int n, int k) 
    {   
        StringBuilder result = new StringBuilder();
        List<Integer> numList = new ArrayList<Integer>(); 

        for(int i=1;i<=n;i++)  
        {  
            numList.add(i);  
        } 

        int factorial = 1;
        for(int i = 2;i < n;i++)
        {
            factorial *= i;
        }

        //decrement k by 1 as we make it start at 0th permutation
        k--;
        for(int i = n - 1;i >= 1;i--)
        {
            int index = k / factorial;
            int value = numList.get(index);

            result.append(value);
            numList.remove(index);

            k = k % factorial;
            factorial = factorial / i;
        }

        //need to handle the remaining value in the numlist
        result.append(numList.get(0));

        return result.toString();
    }

    //naive approach -> dfs
    public String getPermutationDfs(int n, int k) 
    {
        StringBuilder result = new StringBuilder();
        boolean [] visited = new boolean[n + 1];
        int[] count = {0};
        getPermutationHelper(n, k, 0, count, visited, result);

        return result.toString();
    }

    public void getPermutationHelper(int n, int k, int depth, int[] count, 
        boolean[] visited, StringBuilder result)
    {
        if(depth == n)
        {   
            count[0]++;
 
            if(count[0] == k)
            {  
                return;
            }
        }

        for(int i = 1;i <= n;i++)
        {
            if(!visited[i])
            {
                result.append(i);
                visited[i] = true;
                getPermutationHelper(n, k, depth + 1, count, visited, result);
                result.deleteCharAt(result.length() - 1);
                visited[i] = false;
            }
        }
    }


    // Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
    // If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
    // The replacement must be in-place, do not allocate extra memory.
    // Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
    // 6 8 7 4 3 2
    // ^    
    public void nextPermutation(int[] nums) 
    {
        if(nums == null || nums.length == 0)
        {
            return;
        } 

        int violateIndex = nums.length - 2;
        
        while(violateIndex >= 0)
        {
            //find the first value that violates ascending order from right to left
            if(nums[violateIndex] < nums[violateIndex + 1])
            {
                break;
            }

            violateIndex--;
        }

        //if the entire array is in descending order
        //simply reverse it 
        if(violateIndex < 0)
        {
            reverse(nums, 0, nums.length - 1);
            return;
        }

        int start = violateIndex + 1;
        //int end = nums.length - 1;
       
        //keep searching until we find a value that is less or equal to violate value
        while(start <= nums.length - 1 &&  nums[start] > nums[violateIndex])
        {
            start++;    
        }

        //exit searching, need to get back one position
        start--;

        //swap violate number and next big number
        swap(nums, violateIndex, start);

        //reverse the entire partition right to violate index
        reverse(nums, violateIndex + 1, nums.length - 1);
    }

    public void reverse(int[] array, int i, int j)
    {
        while(i < j)
        {
            swap(array, i, j);
            i++;
            j--;
        }
    }

    public void swap(int[] array, int i, int j)
    {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}