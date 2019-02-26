/*

Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.

For example:

Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.

Follow up:
Could you do it without any loop/recursion in O(1) runtime?

*/

public class addDigitsLc258
{
	public static void main(String[] args) 
	{
		addDigitsLc258 test = new addDigitsLc258();
		int n = 38;	
		System.out.println("The digit is: " + test.addDigits(n));
	}

	public int addDigits(int n) 
	{
        if (n == 0)
        {
            return 0;
        }

        return n % 9 == 0 ? 9 : (n % 9);
    }
}