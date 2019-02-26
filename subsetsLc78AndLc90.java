import java.util.*;

public class subsetsLc78AndLc90
{
	public static void main(String[] args) {
		subsetsLc78AndLc90 test = new subsetsLc78AndLc90();
		
		int [] subArray = {1, 2, 3};
		System.out.println(test.subsets(subArray));

        int [] subArrayWithDup = {1, 2, 2};
        System.out.println(test.subsetsWithDup(subArrayWithDup));
	}

	public List<List<Integer>> subsets(int[] nums) 
	{
        List<List<Integer>> resultList = new ArrayList<List<Integer>>();
		List<Integer> tempList = new ArrayList<Integer>();

		//Sort the source array
		Arrays.sort(nums);
		//perform depth first search
		//[1,2,3] -> {} -> {1} -> {1,2} -> {1,2,3} -> {1,3} -> {2} -> {2,3} -> {3}
		dfs(resultList, tempList, nums, 0);

        return resultList;
    }

    public void dfs(List<List<Integer>> resultList, List<Integer> tempList, int [] nums, int position)
    {   
        //Save a copy of the temp list
        resultList.add(new ArrayList<Integer>(tempList));

    	for(int i = position;i < nums.length;i++)
        {
        	//add element to the list
        	tempList.add(nums[i]);
        	//Keep searching for the next element
        	dfs(resultList, tempList, nums, i + 1);
        	//Remove the last element once we are done with one level -> go back to one level up
        	tempList.remove(tempList.size() - 1);
        }
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) 
    {
        List<List<Integer>> resultList = new ArrayList<List<Integer>>();
        List<Integer> tempList = new ArrayList<Integer>();

        //Sort the source array
        Arrays.sort(nums);
        //perform depth first search
        //[1,2,3] -> {} -> {1} -> {1,2} -> {1,2,3} -> {1,3} -> {2} -> {2,3} -> {3}
        dfsWithDup(resultList, tempList, nums, 0);

        return resultList;
    }

    public void dfsWithDup(List<List<Integer>> resultList, List<Integer> tempList, int [] nums, int position)
    {   
        //Save a copy of the temp list
        resultList.add(new ArrayList<Integer>(tempList));

        for(int i = position;i < nums.length;i++)
        {      
            //If the two neighbor elements are the same, simply skip it
            if(i > position && nums[i] == nums[i - 1])
            {
                continue;
            }
            //add element to the list
            tempList.add(nums[i]);
            //Keep searching for the next element
            dfsWithDup(resultList, tempList, nums, i + 1);
            //Remove the last element once we are done with one level -> go back to one level up
            tempList.remove(tempList.size() - 1);
        }
    }

}