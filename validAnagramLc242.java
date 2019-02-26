/*
	Given two strings s and t, 
	write a function to determine if t is an anagram of s.

	For example,
	s = "anagram", t = "nagaram", return true.
	s = "rat", t = "car", return false.

	Note:
	You may assume the string contains only lowercase alphabets.

	Follow up:
	What if the inputs contain unicode characters? 
	How would you adapt your solution to such case?
*/
public class validAnagramLc242
{
	public static void main(String[] args) 
	{
		validAnagramLc242 test = new validAnagramLc242();
		
		String s1 = "anagram";
		String t1 = "nagaram";

		String s2 = "rat";
		String t2 = "car";

		System.out.println(s1 + " and " + t1 + " are anagrams: " + test.isAnagram(s1, t1));
		System.out.println(s2 + " and " + t2 + " are anagrams: " + test.isAnagram(s2, t2));
	}

	public boolean isAnagram(String s, String t) 
	{

        if (s.length() != t.length())
            return false;

        char[] sChar = s.toCharArray();
        char[] tChar = t.toCharArray();
        int[] temp = new int[256];

        for (char c : sChar)
        {
            temp[c]++;
        }
        for (char c : tChar)
        {
            temp[c]--;

            if (temp[c] < 0)
                return false;
        }

        return true;
    }
}