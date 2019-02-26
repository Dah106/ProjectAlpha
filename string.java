import java.util.*;

public class string
{
	// 1, 11, 21, 1211, 111221, ...
	// 1 is read off as "one 1" or 11.
	// 11 is read off as "two 1s" or 21.
	// 21 is read off as "one 2, then one 1" or 1211.
	public String countAndSay(int n) 
	{
        if(n <= 0)
        {
        	return "";
        }

        String currentResult = "1";

        for(int i = 2;i <= n;i++)
        {
        	StringBuilder tempString = new StringBuilder();
        	int count = 1;
        	for(int j = 1; j < currentResult.length();j++)
        	{
        		if(currentResult.charAt(j - 1) == currentResult.charAt(j))
        		{
        			count++;
        		}
        		else
        		{
        			tempString.append(count);
        			tempString.append(currentResult.charAt(j - 1));
        			count = 1;
        		}
        	}

        	tempString.append(count);
        	tempString.append(currentResult.charAt(currentResult.length() - 1));
        	currentResult = tempString.toString();
        }

        return currentResult;
    }

    // Given a string s consists of upper/lower-case alphabets and empty space characters ' ', 
    // return the length of last word in the string.
    // If the last word does not exist, return 0.
    public int lengthOfLastWord(String s) 
    {	
    	if(s == null || s.length() == 0)
    	{
    		return 0;
    	}

     	int length = 0;

     	int endIndex = s.length() - 1;
     	//skipping trailing spaces
     	while(endIndex >= 0 && s.charAt(endIndex) == ' ')
     	{
     		endIndex--;
     	}

     	for(int i = endIndex;i >= 0;i--)
     	{
     		if(s.charAt(i) != ' ')
     		{
     			length++;
     		}
     		else
     		{
     			break;
     		}
     	}

     	return length;   
    }

    // Given a string, determine if it is a palindrome, 
    // considering only alphanumeric characters and ignoring cases.
    public static boolean isValidPalindromeTwoPointer(String s)
    {

		if(s == null||s.length() == 0) 
		{
			return true;
 		}

		s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
		System.out.println(s);
 		
 		int start = 0;
 		int end = s.length() - 1;
 		while(start <= end)
 		{
 			if(s.charAt(start) != s.charAt(end))
 			{
				return false;
			}
			start++;
			end--;
 		}
 
		return true;
	}

	// The string "PAYPALISHIRING" is written in a zigzag pattern 
	// on a given number of rows like this
	// P   A   H   N
	// A P L S I I G
	// Y   I   R
	// And then read line by line: "PAHNAPLSIIGYIR"
	// convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
	public String convert(String s, int numRows) 
	{	
		if(s == null || s.length() == 0 || numRows <= 0) 
		{
			return "";  
		} 
            
        if(numRows == 1)
        {
        	return s;
        }  
        
        StringBuilder result = new StringBuilder(); 
        int size = 2 * numRows - 2;

        for(int i = 0;i < numRows;i++)
        {
        	for(int j = i;j < s.length();j += size)
        	{	
        		//for every row
        		result.append(s.charAt(j));

        		//except the first row and the last row
        		if(i != 0 && i != numRows - 1)
        		{
        			int temp = j + size - 2 * i;
        			if(temp < s.length())
        			{
        				result.append(s.charAt(temp));
        			}
        		}
        	}
        }

        return result.toString();
    }

    // The API: int read4(char *buf) reads 4 characters at a time from a file.
    // The return value is the actual number of characters read. 
    // For example, it returns 3 if there is only 3 characters left in the file.
    // By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
    public int read(char[] buf, int n) 
    {
        char[] tempBuf = new char[4];
        boolean endOfFile = false;
        int totalBytes = 0;

        while(totalBytes < n && !endOfFile)
        {	
        	//potential count is from [0..4]
        	int count = read4(tempBuf);
        	int bytesLeft = n - totalBytes;

        	if(count < 4)
        	{
        		endOfFile = true;
        	}

        	count = Math.min(bytesLeft, count);//if bytes left is less than what read4 returns, only get the rest chars

        	for(int i = 0;i < count;i++)
        	{
        		buf[totalBytes] = tempBuf[i];
        		totalBytes++;
        	}

        }


        return totalBytes;
    }

    //mock method
    public int read4(char[] buf)
    {	
    	int count = 0;

    	return count;
    }

 	// Given a roman numeral, convert it to an integer.
	// Input is guaranteed to be within the range from 1 to 3999.
    public int romanToInt(String s) 
    {
        int res = 0;
	    for (int i = s.length() - 1; i >= 0; i--) 
	    {
	        char c = s.charAt(i);
	        if(c == 'I'){
	            if(res >= 5)//如果>=5, 说明之前肯定遍历过V了，所以这个I肯定在左边，减
	                res += -1;
	            else
	                res += 1;
	        }else if(c == 'V'){//遇见V,L,D,M,统统都加5，50，500，100
	            res += 5;
	        }else if(c == 'X'){
	            if(res >= 50)//说明肯定之前有过L，这个X肯定在左边，减
	                res += -10;
	            else 
	                res += 10;
	        }else if(c == 'L'){
	            res += 50;
	        }else if(c == 'C'){//说明之前有D，这个C肯定在左边，减。能被减的只有I X C
	            if(res >= 500)
	                res += -100;
	            else
	                res += 100;
	        }else if(c == 'D'){
	            res += 500;
	        }else if(c == 'M'){
	            res += 1000;
	        }
	    }
	    return res;
    }

 	// Given an integer, convert it to a roman numeral.
	// Input is guaranteed to be within the range from 1 to 3999.
    public String intToRoman(int num) 
    {
    	String str = "";    
        String [] symbol = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};    
        int [] value = {1000,900,500,400, 100, 90,  50, 40,  10, 9,   5,  4,   1};   
        for(int i = 0;num != 0;i++)
        {  
            while(num >= value[i])
            {  
                num -= value[i];  
                str += symbol[i];  
            }  
        }  
        return str;    
    }

    public String reverseString(String s) {

        char[] result = s.toCharArray();
        int strLength = result.length;
        for(int i = 0;i < strLength / 2;i++)
        {
            char tempChar = result[i];
            result[i] = result[strLength - i - 1];
            result[strLength - i - 1] = tempChar;
        }    

        return String.valueOf(result);
    }

    public String reverseWords(String s) 
    {
        String[] parts = s.trim().split("\\s+");
		StringBuilder result = new StringBuilder();
		
		if(parts == null || parts.length == 0)
		{
			return result.toString();
		}

		for (int i = parts.length - 1; i > 0; i--) 
	    {
		    result.append(parts[i]); 
		    result.append(" ");
		}

	    result.append(parts[0]);

		return result.toString();
    }

    // Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters.
    // The input string does not contain leading or trailing spaces and the words are always separated by a single space.
    public void reverseWordsII(char[] s) 
    {
        if(s.length == 0) 
        {
        	return;
        }

        reverse(s, 0, s.length - 1);
        int start = 0;
        for (int i = 0; i < s.length; i++) 
        {
            if (s[i] == ' ') 
            {
                reverse(s, start, i - 1);
                start = i + 1;
            }
        }

        // reverse the last word, if there is only one word this will solve the corner case
        reverse(s, start, s.length - 1);
    }

    public void reverse(char[] c, int start, int end)
    {
        while(start < end)
        {
            char tempChar = c[end];
            c[end] = c[start];
            c[start] = tempChar;
            start++;
            end--;
        }
    }

    // Given two strings s and t, determine if they are isomorphic.
    // Two strings are isomorphic if the characters in s can be replaced to get t.

    // Given "egg", "add", return true.

    // Given "foo", "bar", return false.

    // Given "paper", "title", return true.
    public boolean isIsomorphic(String s, String t) 
    {
        if(s == null || t == null)
        {
             return false;
        }
       
        if(s.length() != t.length())
        {
            return false;
        }
     
        if(s.length() == 0 && t.length() == 0)
        {
            return true;
        }
    
        HashMap<Character, Character> map = new HashMap<Character,Character>();
        for(int i = 0; i < s.length(); i++)
        {
            char a = s.charAt(i);
            char b = t.charAt(i);

            if(map.containsKey(a))
            {
                if(map.get(a).equals(b))
                {
                    continue;
                }
                else
                {
                    return false;
                }
            }
            else
            {
                if(!map.containsValue(b))
                {
                    map.put(a, b);
                }
                else
                {
                    return false;
                }
            }
        }

        return true;
    }

    public int strStr(String haystack, String needle) 
    {  
        if(haystack==null || needle == null || needle.length()==0)  
            return 0;  
        if(needle.length()>haystack.length())  
            return -1;  
        for(int i=0;i <= haystack.length() - needle.length();i++)  
        {  
            boolean successFlag = true;  
            for(int j = 0;j < needle.length();j++)  
            {  
                if(haystack.charAt(i + j) != needle.charAt(j))  
                {  
                    successFlag = false;  
                    break;  
                }  
            } 

            if(successFlag)
            {
                return i;  
            }  
        }  
        return -1;  
    } 

    // Given a string, find the length of the longest substring without repeating characters. 
    // For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3. 
    // For "bbbbb" the longest substring is "b", with the length of 1.
    // Time: O(n)
    // Space: O(1)
    public int lengthOfLongestSubstring(String s) 
    {
        if(s == null || s.length() == 0)
        {
            return 0;
        }   

        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int max=0;
        int newStart = 0;
        for (int i = 0; i < s.length();i++)
        {   
            //if the map has found an existing char
            //say we first encounter this char at occur1
            //then this position is marked as occur2
            //we set the newstart as the next char of occur1
            if (map.containsKey(s.charAt(i)))
            {
                newStart = Math.max(newStart, map.get(s.charAt(i))+1);
                //System.out.println("curentIndex: " + i + " newStart: " + newStart);
            }

            map.put(s.charAt(i),i);
            max = Math.max(max, i - newStart + 1);
        }
        return max;
    }

    public static void main(String[] args) 
    {
        string test = new string();
        String nthCountAndSay = test.countAndSay(4);    
        System.out.println("the nth string is: " + nthCountAndSay);

        int lengthLastWord = test.lengthOfLastWord("b a ");
        System.out.println("the length of last word is: " + lengthLastWord);

        String str2 = "qpxrjxkltzyx";
        int longestNonRepeatedSubstringLength = test.lengthOfLongestSubstring(str2);
        System.out.println("the length of longest nonrepeated substring is: " + longestNonRepeatedSubstringLength);
        
        String str3 = "hello";
        System.out.println(test.reverseString(str3));
    }   

}