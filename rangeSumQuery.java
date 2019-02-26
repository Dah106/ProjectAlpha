public class rangeSum
{
	int sums [];
	public NumArray(int[] nums) {

        for(int i = 1;i < nums.length;i++)
        {
        	nums[i] += nums[i - 1];
        }

        sums = nums;
    }

    public int sumRange(int i, int j) 
    {
    	if(i == 0)
    	{
    		return sums[j];
    	}    

    	return sums[j] - sums[i - 1];
    }

    public void update(int i, int val) 
    {
        
    }
}