import java.util.*;
/*
	Given a singly linked list, determine if it is a palindrome.

    Follow up:
    Could you do it in O(n) time and O(1) space?
*/
public class removeElementLc27
{
	public static void main(String[] args) 
	{
		removeElementLc27 test = new removeElementLc27();
		
   
        int [] testArr = {1, 2, 3, 2, 3, 2, 2, 5};
        System.out.println(test.removeElement(testArr, 2));

        System.out.println(Arrays.toString(testArr));
	}

    public int removeElement(int[] nums, int val) 
    {
        int newLength = 0;

        for(int i = 0;i < nums.length;i++)
        {
            if(nums[i] != val)
            {
                nums[newLength] = nums[i];
                newLength++;
            }
        }    

        return newLength;
    }
}