import java.util.*;
/*
	Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

    For example,
    "A man, a plan, a canal: Panama" is a palindrome.
    "race a car" is not a palindrome.
*/
public class validPalindromeLc125
{
	public static void main(String[] args) 
	{
		validPalindromeLc125 test = new validPalindromeLc125();
		String a = "11";
        System.out.println(test.isPalindrome(a));
	}

	public boolean isPalindrome(String s) 
    {
        s=s.toLowerCase().replaceAll("[^a-z0-9]", "");
        return new StringBuilder(s).reverse().toString().equals(s);   
    }
}