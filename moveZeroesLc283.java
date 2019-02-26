/*
	Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

	For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
*/
import java.util.*;

public class moveZeroesLc283
{
	public static void main(String[] args) 
	{
		moveZeroesLc283 test = new moveZeroesLc283();	
		
		int [] testArray = {0, 1, 0, 3, 12};
		test.moveZeroes(testArray);
		System.out.println(Arrays.toString(testArray));
	}

	public void moveZeroes(int[] testArray) 
	{
		if(testArray == null || testArray.length <= 0)
		{
			return;
		}

		int nonZeroStartPosition = 0;
		
		for(int i = 0;i < testArray.length;i++)
		{
			if(testArray[i] != 0)
			{
				testArray[nonZeroStartPosition] = testArray[i];
				nonZeroStartPosition++;
			}
		}

		//Fill zeros at position where the last non-zero number locates
		for(int j = nonZeroStartPosition;j < testArray.length;j++)
		{
			testArray[j] = 0;
		}
    }

}