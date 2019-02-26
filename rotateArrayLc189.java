import java.util.*;
/*
	Rotate an array of n elements to the right by k steps.

    For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
*/
public class rotateArrayLc189
{
	public static void main(String[] args) 
	{
		rotateArrayLc189 test = new rotateArrayLc189();
		int testArr [] = {1, 2, 3, 4, 5, 6, 7};	

        int testArr1[] = {1, 2};
        //test.rotate(testArr, 3);
        test.rotateUsingReverse(testArr1, 3);
		System.out.println(Arrays.toString(testArr1));
	}

	public void rotate(int[] nums, int k) 
    {   
        int n = nums.length;

        if(n <= 1) 
        {
            return;
        }

        int index = 0;
        int distance = 0;
        int cur = 0;
        for (int i = 0; i < n; i++)
        {
            int next = (index + k) % n;
            
            System.out.println("cuurent index is " + cur + " next index is " + next);
            swap(nums, cur, next);
            
            index = next;

            distance = (distance + k) % n;
            if (distance == 0)
            {
                index = (index + 1) % n;
                cur = index;
            }
        } 
    }

    public void rotateUsingReverse(int [] nums, int k)
    {   
        int n = nums.length;
        
        if(n <= 1) 
        {
            return;
        }

        k = k % n;

        reverse(nums, 0, n -1);//reverse whole array
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    public void reverse(int[] arr, int start, int end)
    {
        while(start < end){
            swap(arr, start, end);
            start++;
            end--;
        }
    }

    public void swap(int [] arr, int indexA, int indexB)
    {
        int temp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = temp;
    }
}